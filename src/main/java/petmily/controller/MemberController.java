package petmily.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import petmily.domain.adopt.Adopt;
import petmily.domain.adopt.form.MypageAdoptPageForm;
import petmily.domain.adopt_review.form.AdoptReviewPageForm;
import petmily.domain.board.form.BoardPageForm;
import petmily.domain.find_board.FindBoard;
import petmily.domain.find_board.form.FindBoardPageForm;
import petmily.domain.look_board.LookBoard;
import petmily.domain.look_board.form.LookBoardPageForm;
import petmily.domain.member.Member;
import petmily.domain.member.form.JoinForm;
import petmily.domain.member.form.MemberInfoChangeForm;
import petmily.domain.member.form.MemberPwChangeForm;
import petmily.domain.member.form.ResetPwForm;
import petmily.domain.temp.form.MypageTempPageForm;
import petmily.service.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final FindBoardService findBoardService;
    private final LookBoardService lookBoardService;
    private final AdoptTempService adoptTempService;
    private final BoardService boardService;
    private final AdoptReviewService adoptReviewService;
    private final MailService mailService;

    // 메인페이지
    @GetMapping("/")
    public String home() {
        return "/main/index";
    }

    // ============================ 로그인/아웃 =============================
    // 로그인
    @GetMapping("/login")
    public String loginPage(HttpServletRequest request) {
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referrer);

        return "/login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String pw, Model model, HttpServletRequest request) {
        Member authUser = memberService.login(id, pw);
        log.info("authUser= {}", authUser);

        // 없는 계정 또는 틀린 비번 입력 시
        if (authUser == null) {
            model.addAttribute("rejectedId", id);
            return "/login/login";
        }

        HttpSession session = request.getSession();
        setSessionAuthUser(authUser, session);

        String originalRequest = (String) session.getAttribute("originalRequest");
        String prevPage = (String) session.getAttribute("prevPage");

        removeSessions(session);

        if (originalRequest != null) { //로그인 인터셉터 후 원래 가려던 페이지로
            return canWriteAdoptReviewOnlyAuthUser(originalRequest, request);
        } else if ("http://localhost:8080/join".equals(prevPage)) { // 회원가입 후 로그인 시 메인페이지로
            return "redirect:/";
        } else if (prevPage != null && !"http://localhost:8080/".equals(prevPage)) { // 로그인 후 원래 있던 페이지로
            return "redirect:" + prevPage;
        }

        // 관리자 등급 회원: 메인 화면에서 로그인 시 관리자 페이지로
        if (isAdminUser(request)) {
            return "redirect:/admin";
        }

        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();

        return "redirect:/";
    }

    // ============================ 회원가입 ============================
    @GetMapping("/join")
    public String joinPage() {
        return "/login/join";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute JoinForm joinForm,
                       BindingResult bindingResult) {
        log.info("POST memberJoinForm= {}", joinForm);

        boolean isNotSamePwCheck = !joinForm.getPw().equals(joinForm.getConfirmPw());

        if (bindingResult.hasErrors() || isNotSamePwCheck) {
            if (bindingResult.hasErrors()) {
                log.info("join bindingResult= {}", bindingResult);
            }
            if (isNotSamePwCheck) {
                log.info("회원가입 서버 검증: 비밀번호와 확인 불일치");
            }

            return "redirect:/join";
        }

        memberService.join(joinForm);

        return "/alert/member/join";
    }

    // 아이디 중복체크
    @PostMapping("/join/idValid")
    @ResponseBody
    public String validDupIdAtJoin(@RequestParam String id) {
        log.info("id={}", id);

        if (memberService.checkDuplicatedId(id) == 0) {
            return "SUCCESS";
        }

        return "ALREADY";
    }

    // 이메일 중복체크
    @PostMapping("/join/emailValid")
    @ResponseBody
    public String validDupEmailAtJoin(@RequestParam String email) {
        log.info("email={}", email);

        if (memberService.checkDuplicatedEmail(email) == 0) {
            return "SUCCESS";
        }

        return "ALREADY";
    }

    // 이메일 인증코드 전송
    @PostMapping("/join/sendMailAuthCode")
    @ResponseBody
    public String sendEmailCodeAtJoin(@RequestParam String email) throws MessagingException, UnsupportedEncodingException {
        log.info("email={}", email);

        return mailService.sendMailAtJoin(email);
    }

    // 연락처 중복체크
    @PostMapping("/join/phoneValid")
    @ResponseBody
    public String validDupPhoneAtJoin(@RequestParam String phone) {
        log.info("phone={}", phone);

        if (memberService.checkDuplicatedPhone(phone) == 0) {
            return "SUCCESS";
        }

        return "ALREADY";
    }

    // ============================ 아이디 찾기 ============================
    @GetMapping("/findId")
    public String findIdPage() {
        return "/login/find_id";
    }

    @PostMapping("/findId")
    @ResponseBody
    public String findId(@RequestParam String email) {
        log.info("아이디 찾기: 입력받은 email={}", email);

        if (memberService.checkDuplicatedEmail(email) != 0) {
            log.info("POST SUCCESS");

            return "SUCCESS";
        }

        return "ERROR";
    }

    // 이메일 보내기
    @PostMapping("/findId/sendMail")
    @ResponseBody
    public void sendMailAtFindId(@RequestParam String email) throws MessagingException, UnsupportedEncodingException {
        log.info("send email={}", email);

        Member member = memberService.getMemberByEmail(email);
        mailService.sendMailAtFindId(email, member);
    }

    // ============================ 비밀번호 찾기 ============================
    @GetMapping("/resetPw")
    public String resetPwPage() {
        return "/login/reset_pw";
    }

    // 인증번호 입력 및 비밀번호 재설정
    @PostMapping("/resetPw")
    public String resetPw(@Validated @ModelAttribute ResetPwForm resetPwForm, BindingResult bindingResult) {
        log.info("POST resetPwForm= {}", resetPwForm);

        boolean isNotSameAuthCode = !resetPwForm.getAuthCode().equals(resetPwForm.getInputCode());
        boolean isNotSamePw = !resetPwForm.getPw().equals(resetPwForm.getPwCheck());

        if (bindingResult.hasErrors() || isNotSameAuthCode || isNotSamePw) {
            if (bindingResult.hasErrors()) {
                log.info("resetPw bindingResult= {}", bindingResult);
            }
            if (isNotSameAuthCode) {
                log.info("서버검증 : 인증코드 불일치");
            }
            if (isNotSamePw) {
                log.info("서버검증 : 비밀번호 불일치");
            }

            return "redirect:/resetPw";
        }

        memberService.resetPw(resetPwForm);

        return "/alert/member/reset_pw";
    }

    // ID, EMAIL 정보 검증
    @PostMapping("/resetPw/validIdAndEmail")
    @ResponseBody
    public String validIdAndEmail(@RequestBody Map<String, String> requestBody) {
        String id = requestBody.get("id");
        String email = requestBody.get("email");
        log.info("비번 찾기: 입력받은 id={}, email={}", id, email);

        boolean existId = memberService.checkDuplicatedId(id) != 0;
        boolean existEmail = memberService.checkDuplicatedEmail(email) != 0;

        if (existId && existEmail) {
            boolean equalsInfo = email.equals(memberService.getMemberById(id).getEmail());

            if (equalsInfo) {
                log.info("POST SUCCESS");

                return "SUCCESS";
            }
        }

        return "ERROR";
    }

    // 이메일 보내기
    @PostMapping("/resetPw/sendMail")
    @ResponseBody
    public String sendMailAtResetPw(@RequestParam String email) throws MessagingException, UnsupportedEncodingException {
        log.info("send email={}", email);

        Member member = memberService.getMemberByEmail(email);
        String authCode = mailService.sendMailAtResetPw(email, member);
        log.info("생성된 authCode={}", authCode);

        return authCode;
    }

    // 검증: 새 비번 != 기존 비번
    @PostMapping("/resetPw/validDupOldPw")
    @ResponseBody
    public String validDuplicatedOldPw(@RequestParam String id) {
        String oldPw = memberService.getPwById(id);

        log.info("id={}, oldPw={}", id, oldPw);

        return oldPw;
    }


    // ============================ 회원정보 변경 ============================
    // 마이페이지 홈
    @GetMapping("/member/auth/mypage")
    public String mypage() {
        return "/member/mypage";
    }

    @GetMapping("/member/auth/changeInfo")
    public String changeInfoPage(Model model, HttpServletRequest request) {
        Member member = memberService.getMemberByPk(getAuthMNumber(request));
        model.addAttribute("member", member);

        return "/member/member_info_change";
    }

    @PostMapping("/member/auth/changeInfo")
    public String changeInfo(@Validated @ModelAttribute MemberInfoChangeForm memberInfoChangeForm, BindingResult bindingResult) {
        log.info("POST memberChangeForm= {}", memberInfoChangeForm);

        if (bindingResult.hasErrors()) {
            log.info("changeInfo bindingResult= {}", bindingResult);
            return "redirect:/member/auth/changeInfo";
        }

        memberService.changeInfo(memberInfoChangeForm);

        return "/alert/member/member_info_change";
    }

    // 회원정보 변경 이메일 중복체크
    @PostMapping("/member/auth/changeInfo/emailValid")
    @ResponseBody
    public String changeInfoEmailValid(@RequestParam String email, HttpServletRequest request) {
        log.info("email={}", email);

        int dupEmail = memberService.checkDuplicatedEmailChangeInfo(getAuthMNumber(request), email);
        if (dupEmail == 0) {
            return "SUCCESS";
        }

        return "ALREADY";
    }

    // 회원정보 변경 연락처 중복체크
    @PostMapping("/member/auth/changeInfo/phoneValid")
    @ResponseBody
    public String changeInfoPhoneValid(@RequestParam String phone, HttpServletRequest request) {
        log.info("phone={}", phone);

        int dupPhone = memberService.checkDuplicatedPhoneChangeInfo(getAuthMNumber(request), phone);
        if (dupPhone == 0) {
            return "SUCCESS";
        }

        return "ALREADY";
    }

    // ============================== 비밀번호 변경 ==============================
    @GetMapping("/member/auth/changePw")
    public String changePwPage() {
        return "/member/member_pw_change";
    }

    @PostMapping("/member/auth/changePw")
    public String changePw(@Validated @ModelAttribute MemberPwChangeForm memberPwChangeForm, BindingResult bindingResult, HttpServletRequest request) {
        log.info("POST memberChangePwForm= {}", memberPwChangeForm);

        boolean isSameOldAndNewPw = memberPwChangeForm.getOldPw().equals(memberPwChangeForm.getNewPw());
        boolean isNotSameNewPwCheck = !memberPwChangeForm.getNewPw().equals(memberPwChangeForm.getNewPwCheck());

        if (bindingResult.hasErrors() || isSameOldAndNewPw || isNotSameNewPwCheck) {
            if (bindingResult.hasErrors()) {
                log.info("changePw bindingResult= {}", bindingResult);
            }
            if (isSameOldAndNewPw) {
                log.info("비밀번호 변경 서버 검증: 기존 비번 == 새 비번");
            }
            if (isNotSameNewPwCheck) {
                log.info("비밀번호 변경 서버 검증: 새 비번 != 새 비번 확인");
            }

            return "/member/member_pw_change";
        }

        memberService.changePw(memberPwChangeForm);

        return "/alert/member/member_pw_change";
    }

    // 기존 비번 입력값 틀릴 시
    @PostMapping("/member/auth/changePw/validOldPw")
    @ResponseBody
    public String validOldPwAtChangePw(@RequestBody Map<String, String> requestBody, HttpServletRequest request) {
        String oldPw = requestBody.get("oldPw");
        log.info("oldPw= {}", oldPw);

        String pw = memberService.getMemberByPk(getAuthMNumber(request)).getPw();

        if (!pw.equals(oldPw)) {
            return "ERROR";
        }

        return "SUCCESS";
    }

    // 새 비번에 기존 비번값 입력 시
    @PostMapping("/member/auth/changePw/validNewPw")
    @ResponseBody
    public String validNewPwAtChangePw(@RequestBody Map<String, String> requestBody, HttpServletRequest request) {
        String newPw = requestBody.get("newPw");
        log.info("newPw= {}", newPw);

        String pw = memberService.getMemberByPk(getAuthMNumber(request)).getPw();

        if (pw.equals(newPw)) {
            return "ERROR";
        }

        return "SUCCESS";
    }

    // ============================== 매칭 시스템 ==============================
    // 찾아요 매칭된 페이지
    @GetMapping("/member/auth/findMatching")
    public String findMatching(@RequestParam(defaultValue = "1") int pageNo,
                               HttpServletRequest request, Model model) {
        int mNumber = getAuthMNumber(request);
        FindBoardPageForm pageForm = findBoardService.getMatchingFindPage(pageNo, mNumber);

        model.addAttribute("pageForm", pageForm);

        return "/member/matched_find";
    }

    // 찾아요에 매칭된 봤어요 리스트
    @GetMapping("/member/auth/findMatching/lookList")
    public String findMatchingLookList(@RequestParam int faNumber,
                                       @RequestParam(defaultValue = "1") int pageNo,
                                       Model model) {
        FindBoard findBoard = findBoardService.getFindBoard(faNumber);
        LookBoardPageForm pageForm = lookBoardService.getLookListMatchedFind(pageNo, findBoard);

        model.addAttribute("pageForm", pageForm);

        return "/member/matched_find_looklist";
    }

    // 봤어요 매칭된 페이지
    @GetMapping("/member/auth/lookMatching")
    public String lookMatching(@RequestParam(defaultValue = "1") int pageNo,
                               HttpServletRequest request, Model model) {
        int mNumber = getAuthMNumber(request);
        LookBoardPageForm pageForm = lookBoardService.getMatchingLookPage(pageNo, mNumber);

        model.addAttribute("pageForm", pageForm);

        return "/member/matched_look";
    }

    // 봤어요에 매칭된 찾아요 리스트
    @GetMapping("/member/auth/lookMatching/findList")
    public String lookMatchingFindList(@RequestParam int laNumber,
                                       @RequestParam(defaultValue = "1") int pageNo,
                                       Model model) {
        LookBoard lookBoard = lookBoardService.getLookBoard(laNumber);
        FindBoardPageForm pageForm = findBoardService.getFindListMatchedLook(pageNo, lookBoard);

        model.addAttribute("pageForm", pageForm);

        return "/member/matched_look_findlist";
    }

    // ============================== 입양, 임보 신청 현황 ==============================
    @GetMapping("/member/auth/myApply/{type}")
    public String getMyApply(@PathVariable String type,
                             @RequestParam(defaultValue = "1") int pageNo,
                             HttpServletRequest request, Model model) {
        int mNumber = getAuthMNumber(request);

        if ("adopt".equals(type)) {
            MypageAdoptPageForm pageForm = adoptTempService.getMypageAdopt(pageNo, mNumber, type);
            model.addAttribute("pageForm", pageForm);
        } else {
            MypageTempPageForm pageForm = adoptTempService.getMypageTemp(pageNo, mNumber, type);
            model.addAttribute("pageForm", pageForm);
        }

        model.addAttribute("type", type);

        return "/member/adopt_temp_apply_list";
    }

    // ============================== 내가 쓴 게시글 ==============================
    @GetMapping("/member/auth/myPost/{type}")
    public String getMyPost(@PathVariable String type,
                            @RequestParam(defaultValue = "1") int pageNo,
                            @RequestParam(required = false) String kindOfBoard,
                            HttpServletRequest request, Model model) {
        int mNumber = getAuthMNumber(request);

        model.addAttribute("type", type);

        if ("find".equals(type)) {
            FindBoardPageForm findMyPost = findBoardService.getMyPost(pageNo, mNumber);

            model.addAttribute("myPost", findMyPost);

            return "/member/mypost_find_list";
        } else if ("look".equals(type)) {
            LookBoardPageForm lookMyPost = lookBoardService.getMyPost(pageNo, mNumber);
            model.addAttribute("myPost", lookMyPost);

            return "/member/mypost_look_list";
        } else if ("board".equals(type)) {
            BoardPageForm boardMyPost = boardService.getMyPost(pageNo, mNumber, type, kindOfBoard);
            model.addAttribute("myPost", boardMyPost);

            return "/member/mypost_board_list";
        } else if ("adoptReview".equals(type)) {
            AdoptReviewPageForm adoptReviewMyPost = adoptReviewService.getMyPost(pageNo, mNumber);
            model.addAttribute("myPost", adoptReviewMyPost);

            return "/member/mypost_adopt_review_list";
        } else {
            return null;
        }
    }

    // ============================== 회원 탈퇴 ==============================
    @GetMapping("/member/auth/withdraw")
    public String withdrawPage() {
        return "/member/withdraw";
    }

    @PostMapping("/member/auth/withdraw")
    public String withdraw(@RequestParam String pw, HttpServletRequest request) {
        log.info("POST pw = {}", pw);

        memberService.withdraw(getAuthMNumber(request));
        request.getSession().invalidate();

        return "/alert/member/withdraw";
    }

    // 비밀번호 검증
    @PostMapping("/member/auth/withdraw/validPw")
    @ResponseBody
    public String withdrawValid(@RequestBody Map<String, String> requestBody, HttpServletRequest request) {
        String pw = requestBody.get("pw");
        log.info("검증 pw = {}", pw);

        int mNumber = getAuthMNumber(request);

        if (pw != null && !pw.isEmpty()) {
            if (!memberService.checkPwCorrect(mNumber, pw)) {
                return "NOT_CORRECT";
            }
        }

        return "SUCCESS";
    }

    // ============================== 기타 메소드들 ==============================
    private Member getAuthUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member authUser = (Member) session.getAttribute("authUser");
        if (authUser == null) {
            log.info("authUser is null");
        }

        return authUser;
    }

    private int getAuthMNumber(HttpServletRequest request) {
        return getAuthUser(request).getMNumber();
    }

    private boolean isGeneralUser(HttpServletRequest request) {
        return "일반".equals(getAuthUser(request).getGrade());
    }

    private boolean isAdminUser(HttpServletRequest request) {
        return "관리자".equals(getAuthUser(request).getGrade());
    }

    // 로그인 된 회원정보 세션에 저장
    private void setSessionAuthUser(Member authUser, HttpSession session) {
        session.setAttribute("authUser", authUser);
    }

    private void removeSessions(HttpSession session) {
        session.removeAttribute("originalRequest"); // 원래 요청했던 URL (로그인 인터셉터에 걸린 경우)
        session.removeAttribute("prevPage"); // 로그인 전 있던 페이지
    }

    // 입양기록 없는 회원이 입양후기 글 못쓰게
    private String canWriteAdoptReviewOnlyAuthUser(String originalRequest, HttpServletRequest request) {
        Adopt adoptedLog = adoptTempService.getAdoptBymNumber(getAuthMNumber(request));
        boolean isGeneralUser = isGeneralUser(request);

        if (adoptedLog == null && isGeneralUser && containsAdoptReviewWriteURI(originalRequest)) {
            return "/alert/member/adopt_review_notAdopted";
        } else if (adoptedLog != null) {
            if (isGeneralUser(request) && !"완료".equals(adoptedLog.getStatus()) && containsAdoptReviewWriteURI(originalRequest)) {
                return "/alert/member/adopt_review_notAdopted";
            }
        }
        return "redirect:" + originalRequest;
    }

    // 비로그인 유저가 입양후기에 글 쓰려다 로그인 인터셉터에 걸렸을 경우
    private boolean containsAdoptReviewWriteURI(String originalRequest) {
        return originalRequest.contains("adoptReview/auth/write?kindOfBoard=adoptReview");
    }
}