package petmily.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
        log.info("memberJoinForm = {}", memberJoinForm);

        if (bindingResult.hasErrors()) {
            log.info("join bindingResult= {}", bindingResult);
            return "/login/join";
        }

        memberService.join(memberJoinForm);

        return "/alert/member/join";
    }

    // 로그인
    @GetMapping("/login")
    public String loginPage() {
        return "/login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String id,
                        @RequestParam String pw,
                        HttpServletRequest request, RedirectAttributes redirectAttributes) {
        log.info("id= {}, pw= {}", id, pw);
        Member authUser;

        try {
            authUser = memberService.login(id, pw);
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", true);
            redirectAttributes.addAttribute("id", id);

            return "redirect:/login";
        }

        if (authUser == null) {
            redirectAttributes.addAttribute("error", true);
            redirectAttributes.addAttribute("id", id);

            return "redirect:/login";
        }

        request.getSession().setAttribute("authUser", authUser);

        if (authUser.getGrade().equals("관리자")) {
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
    public String mypage(HttpServletRequest request, Model model) {
        Member member = getAuthUser(request);
        model.addAttribute("member", member);

        return "/member/mypage";
    }

    // 회원정보 수정
    @GetMapping("/member/auth/changeInfo")
    public String changeInfoPage(HttpServletRequest request, Model model) {
        Member member = getAuthUser(request);
        model.addAttribute("member", member);

        return "/member/member_info_change";
    }

    @PostMapping("/member/auth/changeInfo")
    public String changeInfo(@Validated @ModelAttribute MemberChangeForm memberChangeForm,
                                 BindingResult bindingResult, HttpServletRequest request) {
        log.info("memberChangeForm= {}", memberChangeForm);
        Member member = getAuthUser(request);

        if (bindingResult.hasErrors()) {
            log.info("changeInfo bindingResult= {}", bindingResult);
            return "/member/member_info_change";
        }

        memberService.change(member, memberChangeForm);

        return "/alert/member/member_info_change";
    }

    // 회원탈퇴
    @GetMapping("/member/auth/withdraw")
    public String withdrawPage(HttpServletRequest request, Model model) {
        Member member = getAuthUser(request);
        model.addAttribute("member", member);

        return "/member/withdraw";
    }

//    @PostMapping("/member/auth/withdraw")
//    public String withdraw(@RequestParam String pw, HttpServletRequest request) {
//        log.info("pw = {}, confirmPw = {}", pw, confirmPw);
//
//        Map<String, Boolean> errors = new HashMap<>();
//        request.setAttribute("errors", errors);
//
//        int mNumber = getAuthMNumber(request);
//
//        if (!memberService.isPwEqualToConfirm(pw, confirmPw)) {
//            errors.put("notMatch", Boolean.TRUE);
//            return "/member/withdraw";
//        }
//        else if (!memberService.checkPwCorrect(mNumber, pw)) {
//            errors.put("notCorrect", Boolean.TRUE);
//            return "/member/withdraw";
//        }
//
//        memberService.withdraw(mNumber);
//        request.getSession().invalidate();
//
//        return "/alert/member/withdraw";
//    }

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
        Member member = getAuthUser(request);
        int mNumber = member.getMNumber();

        if (type.equals("adopt")) {
            MypageAdoptPageForm pageForm = adoptTempService.getMypageAdopt(pageNo, mNumber, type);
            model.addAttribute("pageForm", pageForm);
        } else {
            MypageTempPageForm pageForm = adoptTempService.getMypageTemp(pageNo, mNumber, type);
            model.addAttribute("pageForm", pageForm);
        }

        request.getSession().setAttribute("type", type);

        return "/member/adopt_temp_apply_list";
    }

    // 내가 쓴 게시글
    @GetMapping("/member/auth/myPost/{type}")
    public String getMyPost(@PathVariable String type,
                            @RequestParam(defaultValue = "1") int pageNo,
                            @RequestParam(required = false) String kindOfBoard,
                            HttpServletRequest request, Model model) {
        Member member = getAuthUser(request);
        int mNumber = member.getMNumber();

        model.addAttribute("type", type);

        if (type.equals("find")) {
            FindBoardPageForm findMyPost = findBoardService.getMyPost(pageNo, mNumber);

            model.addAttribute("myPost", findMyPost);

            return "/member/mypost_find_list";
        }
        else if (type.equals("look")) {
            LookBoardPageForm lookMyPost = lookBoardService.getMyPost(pageNo, mNumber);
            model.addAttribute("myPost", lookMyPost);

            return "/member/mypost_look_list";
        }
        else if (type.equals("board")) {
            BoardPageForm boardMyPost = boardService.getMyPost(pageNo, mNumber, type, kindOfBoard);
            model.addAttribute("myPost", boardMyPost);

            return "/member/mypost_board_list";
        }
        else if (type.equals("adoptReview")) {
            AdoptReviewPageForm adoptReviewMyPost = adoptReviewService.getMyPost(pageNo, mNumber);
            model.addAttribute("myPost", adoptReviewMyPost);

            return "/member/mypost_adopt_review_list";
        }
        else {
            return null;
        }
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
}