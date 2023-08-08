package kh.petmily.controller;

import kh.petmily.domain.adopt.form.MypageAdoptPageForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewPageForm;
import kh.petmily.domain.board.form.BoardPageForm;
import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.find_board.form.FindBoardPageForm;
import kh.petmily.domain.look_board.form.LookBoardPageForm;
import kh.petmily.domain.member.Member;
import kh.petmily.domain.member.form.MemberJoinForm;
import kh.petmily.domain.member.form.MemberChangeForm;
import kh.petmily.domain.temp.form.MypageTempPageForm;
import kh.petmily.service.*;
import kh.petmily.validation.JoinValidator;
import kh.petmily.validation.MemberChangeValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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

    @InitBinder("joinRequest")
    public void joinInit(WebDataBinder dataBinder) {
        dataBinder.addValidators(joinValidator);
    }

    @InitBinder("memberChangeForm")
    public void memberChangeInit(WebDataBinder dataBinder) {
        dataBinder.addValidators(memberChangeValidator);
    }

    // 회원 가입
    @GetMapping("/join")
    public String joinForm() {
        return "/login/join";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("joinRequest") MemberJoinForm memberJoinForm,
                       BindingResult bindingResult) {
        log.info("memberJoinForm = {}", memberJoinForm);

        if (bindingResult.hasErrors()) {
            log.info("join bindingResult= {}", bindingResult);
            return "/login/join";
        }

        memberService.join(memberJoinForm);

        return "/login/alert_join";
    }

    // 로그인
    @GetMapping("/login")
    public String loginForm() {
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

    // 마이페이지
    @GetMapping("/member/auth/mypage")
    public String mypage(HttpServletRequest request, Model model) {
        Member member = getAuthMember(request);
        model.addAttribute("member", member);

        return "/member/mypage";
    }

    // 마이페이지 수정
    @GetMapping("/member/auth/change_info")
    public String changeInfo(HttpServletRequest request, Model model) {
        Member member = getAuthMember(request);
        model.addAttribute("member", member);

        return "/member/member_info_change";
    }

    @PostMapping("/member/auth/change_info")
    public String changeInfoPost(@Validated @ModelAttribute("memberChangeForm") MemberChangeForm memberChangeForm,
                                 BindingResult bindingResult, HttpServletRequest request) {
        log.info("memberChangeForm= {}", memberChangeForm);
        Member member = getAuthMember(request);

        if (bindingResult.hasErrors()) {
            log.info("changeInfo bindingResult= {}", bindingResult);
            return "/member/member_info_change";
        }

        memberService.change(member, memberChangeForm);

        return "/member/alert_change";
    }

    // 회원탈퇴
    @GetMapping("/member/auth/withdraw")
    public String withdrawForm() {
        return "/member/withdraw";
    }

    @PostMapping("/member/auth/withdraw")
    public String withdraw(@RequestParam String pw, @RequestParam String confirmPw,
                           HttpServletRequest request) {
        log.info("pw = {}, confirmPw = {}", pw, confirmPw);

        Map<String, Boolean> errors = new HashMap<>();
        request.setAttribute("errors", errors);

        int mNumber = getAuthMNumber(request);

        if (!memberService.isPwEqualToConfirm(pw, confirmPw)) {
            errors.put("notMatch", Boolean.TRUE);
            return "/member/withdraw";
        }
        else if (!memberService.checkPwCorrect(mNumber, pw)) {
            errors.put("notCorrect", Boolean.TRUE);
            return "/member/withdraw";
        }

        memberService.withdraw(mNumber);
        request.getSession().invalidate();

        return "/member/alert_withdraw";
    }

    @GetMapping("/member/auth/checkMatching")
    public String checkMatching(@RequestParam(required = false) String matched,
                                @RequestParam(defaultValue = "1") int pageNo,
                                HttpServletRequest request, Model model) {
        int mNumber = getAuthMNumber(request);

        FindBoardPageForm pageForm = findBoardService.getMatchingPage(pageNo, mNumber, matched);
        model.addAttribute("pageForm", pageForm);

        request.getSession().setAttribute("matched", matched);

        return "/member/matched_find_list";
    }

    @GetMapping("/member/auth/checkMatching/lookList")
    public String checkMatchingDetail(@RequestParam int faNumber,
                                      @RequestParam(defaultValue = "1") int pageNo,
                                      Model model) {
        FindBoard findBoard = findBoardService.getFindBoard(faNumber);

        LookBoardPageForm pageForm = lookBoardService.getMatchingPage(pageNo, findBoard);
        model.addAttribute("matchedPageForm", pageForm);

        return "/member/matched_look_list";
    }

    @GetMapping("/member/auth/myApply/{type}")
    public String getMyApply(@PathVariable String type,
                             @RequestParam(defaultValue = "1") int pageNo,
                             HttpServletRequest request, Model model) {
        Member member = getAuthMember(request);
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
        Member member = getAuthMember(request);
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
            AdoptReviewPageForm adoptReviewMyPost = adoptReviewService.getMyPost(pageNo, mNumber, type);
            model.addAttribute("myPost", adoptReviewMyPost);

            return "/member/mypost_adopt_review_list";
        }
        else {
            return null;
        }
    }

    private Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");

        return member;
    }

    private int getAuthMNumber(HttpServletRequest request) {
        return getAuthMember(request).getMNumber();
    }
}