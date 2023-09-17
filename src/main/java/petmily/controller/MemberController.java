package petmily.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
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
import petmily.domain.member.form.MemberChangeForm;
import petmily.domain.member.form.MemberJoinForm;
import petmily.domain.temp.form.MypageTempPageForm;
import petmily.service.*;
import petmily.validation.JoinValidator;
import petmily.validation.MemberChangeValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private final JoinValidator joinValidator;
    private final MemberChangeValidator memberChangeValidator;

    @InitBinder("memberJoinForm")
    public void joinInit(WebDataBinder dataBinder) {
        dataBinder.addValidators(joinValidator);
    }

    @InitBinder("memberChangeForm")
    public void memberChangeInit(WebDataBinder dataBinder) {
        dataBinder.addValidators(memberChangeValidator);
    }

    // 회원 가입
    @GetMapping("/join")
    public String joinPage() {
        return "/login/join";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute MemberJoinForm memberJoinForm,
                       BindingResult bindingResult) {
        log.info("POST memberJoinForm= {}", memberJoinForm);

        if (bindingResult.hasErrors()) {
            log.info("join bindingResult= {}", bindingResult);
            return "/login/join";
        }

        memberService.join(memberJoinForm);

        return "/alert/member/join";
    }

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

        setSessionAuthUser(authUser, request);

        String originalRequest = (String) request.getAttribute("originalRequest");
        String prevPage = (String) request.getAttribute("prevPage");

        removeSessions(request);

        if (originalRequest != null) {
            return canWriteAdoptReviewOnlyAuthUser(originalRequest, request);
        } else if (prevPage != null && prevPage.equals("http://localhost:8080/join")) { //로그인 인터셉터 후 원래 가려던 페이지로
            return "redirect:/";
        } else if (prevPage != null && !prevPage.equals("http://localhost:8080/")) { // 로그인 후 원래 있던 페이지로
            return "redirect:" + prevPage;
        }

        // 관리자 등급 회원: 메인 화면에서 로그인 시 관리자 페이지로
        if (isAdminUser(request)) {
            return "redirect:/admin";
        }

        return "redirect:/";
    }

    // 로그아웃
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();

        return "redirect:/";
    }

    @RequestMapping("/")
    public String home() {
        return "/main/index";
    }

    // =================== 마이페이지 ===================
    // 마이페이지 홈
    @GetMapping("/member/auth/mypage")
    public String mypage() {
        return "/member/mypage";
    }

    // 회원정보 수정
    @GetMapping("/member/auth/changeInfo")
    public String changeInfoPage() {
        return "/member/member_info_change";
    }

    @PostMapping("/member/auth/changeInfo")
    public String changeInfo(@Validated @ModelAttribute MemberChangeForm memberChangeForm,
                             BindingResult bindingResult, HttpServletRequest request) {
        log.info("POST memberChangeForm= {}", memberChangeForm);

        if (bindingResult.hasErrors()) {
            log.info("changeInfo bindingResult= {}", bindingResult);
            return "/member/member_info_change";
        }

        memberService.change(getAuthUser(request), memberChangeForm);

        return "/alert/member/member_info_change";
    }

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

    // 입양, 임보 신청 현황
    @GetMapping("/member/auth/myApply/{type}")
    public String getMyApply(@PathVariable String type,
                             @RequestParam(defaultValue = "1") int pageNo,
                             HttpServletRequest request, Model model) {
        int mNumber = getAuthMNumber(request);

        if (type.equals("adopt")) {
            MypageAdoptPageForm pageForm = adoptTempService.getMypageAdopt(pageNo, mNumber, type);
            model.addAttribute("pageForm", pageForm);
        } else {
            MypageTempPageForm pageForm = adoptTempService.getMypageTemp(pageNo, mNumber, type);
            model.addAttribute("pageForm", pageForm);
        }

        model.addAttribute("type", type);

        return "/member/adopt_temp_apply_list";
    }

    // 내가 쓴 게시글
    @GetMapping("/member/auth/myPost/{type}")
    public String getMyPost(@PathVariable String type,
                            @RequestParam(defaultValue = "1") int pageNo,
                            @RequestParam(required = false) String kindOfBoard,
                            HttpServletRequest request, Model model) {
        int mNumber = getAuthMNumber(request);

        model.addAttribute("type", type);

        if (type.equals("find")) {
            FindBoardPageForm findMyPost = findBoardService.getMyPost(pageNo, mNumber);

            model.addAttribute("myPost", findMyPost);

            return "/member/mypost_find_list";
        } else if (type.equals("look")) {
            LookBoardPageForm lookMyPost = lookBoardService.getMyPost(pageNo, mNumber);
            model.addAttribute("myPost", lookMyPost);

            return "/member/mypost_look_list";
        } else if (type.equals("board")) {
            BoardPageForm boardMyPost = boardService.getMyPost(pageNo, mNumber, type, kindOfBoard);
            model.addAttribute("myPost", boardMyPost);

            return "/member/mypost_board_list";
        } else if (type.equals("adoptReview")) {
            AdoptReviewPageForm adoptReviewMyPost = adoptReviewService.getMyPost(pageNo, mNumber);
            model.addAttribute("myPost", adoptReviewMyPost);

            return "/member/mypost_adopt_review_list";
        } else {
            return null;
        }
    }

    // 회원탈퇴
    @GetMapping("/member/auth/withdraw")
    public String withdrawPage() {
        return "/member/withdraw";
    }

    @PostMapping("/member/auth/withdraw")
    @ResponseBody
    public String withdraw(@RequestBody Map<String, String> requestBody, HttpServletRequest request) {
        String pw = requestBody.get("pw");
        log.info("pw = {}", pw);

        int mNumber = getAuthMNumber(request);

        if (pw != null && !pw.isEmpty()) {
            if (!memberService.checkPwCorrect(mNumber, pw)) {
                return "NOT_CORRECT";
            }
        }

        memberService.withdraw(mNumber);
        request.getSession().invalidate();

        return "SUCCESS";
    }

    private Member getAuthUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Member) session.getAttribute("authUser");
        }
        return null;
    }

    private int getAuthMNumber(HttpServletRequest request) {
        return getAuthUser(request).getMNumber();
    }

    private boolean isGeneralUser(HttpServletRequest request) {
        return getAuthUser(request).getGrade().equals("일반");
    }

    private boolean isAdminUser(HttpServletRequest request) {
        return getAuthUser(request).getGrade().equals("관리자");
    }

    // 로그인 된 회원정보 세션에 저장
    private void setSessionAuthUser(Member authUser, HttpServletRequest request) {
        request.setAttribute("authUser", authUser);
    }

    private void removeSessions(HttpServletRequest request) {
        request.removeAttribute("rejectedPw"); // 로그인 시 잘못 입력한 비번
        request.removeAttribute("originalRequest"); // 원래 요청했던 URL (로그인 인터셉터에 걸린 경우)
        request.removeAttribute("prevPage"); // 로그인 전 있던 페이지
    }

    // 입양기록 없는 회원이 입양후기 글 못쓰게
    private String canWriteAdoptReviewOnlyAuthUser(String originalRequest, HttpServletRequest request) {
        Adopt adoptedLog = adoptTempService.getAdoptBymNumber(getAuthMNumber(request));
        boolean isGeneralUser = isGeneralUser(request);

        if (adoptedLog == null && isGeneralUser && containsAdoptReviewWriteURI(originalRequest)) {
            return "/alert/member/adopt_review_notAdopted";
        } else if (adoptedLog != null) {
            if (isGeneralUser(request) && !adoptedLog.getStatus().equals("완료") && containsAdoptReviewWriteURI(originalRequest)) {
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