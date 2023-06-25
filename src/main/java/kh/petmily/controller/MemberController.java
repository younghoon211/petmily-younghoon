package kh.petmily.controller;

import kh.petmily.domain.adopt.form.AdoptApplyPageForm;
import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.find_board.form.FindBoardPageForm;
import kh.petmily.domain.look_board.form.LookBoardPageForm;
import kh.petmily.domain.member.Member;
import kh.petmily.domain.member.form.JoinRequest;
import kh.petmily.domain.member.form.MemberChangeForm;
import kh.petmily.domain.temp.form.TempApplyPageForm;
import kh.petmily.service.AdoptTempService;
import kh.petmily.service.FindBoardService;
import kh.petmily.service.LookBoardService;
import kh.petmily.service.MemberService;
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
        return "/login/joinForm";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("joinRequest") JoinRequest joinRequest, BindingResult bindingResult) {
        log.info("넘어온 joinRequest : {}", joinRequest);

        if (bindingResult.hasErrors()) {
            log.info("join bindingResult= {}", bindingResult);
            return "/login/joinForm";
        }

        memberService.join(joinRequest);

        return "/login/joinSuccess";
    }

    // 로그인
    @GetMapping("/login")
    public String loginForm() {
        return "/login/loginForm";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("id") String id,
            @RequestParam("pw") String pw,
            HttpServletRequest request, RedirectAttributes redirectAttributes) {

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

        model.addAttribute("memberInfo", member);

        return "/member/changeMemberInfo";
    }

    @PostMapping("/member/auth/change_info")
    public String changeInfoPost(HttpServletRequest request, @Validated @ModelAttribute("memberChangeForm") MemberChangeForm memberChangeForm, BindingResult bindingResult) {
        Member member = getAuthMember(request);

        if (bindingResult.hasErrors()) {
            log.info("changeInfo bindingResult= {}", bindingResult);
            return "/member/changeMemberInfo";
        }

        memberService.modify(member, memberChangeForm);

        return "/member/changeMemberSuccess";
    }

    // 회원탈퇴
    @GetMapping("/member/auth/withdraw")
    public String withdrawForm() {
        return "/member/withdrawForm";
    }

    @PostMapping("/member/auth/withdraw")
    public String withdraw(HttpServletRequest request, @RequestParam String pw, @RequestParam String confirmPw) {

        log.info("pw = {}", pw);
        log.info("confirmPw = {}", confirmPw);

        Member member = getAuthMember(request);
        int mNumber = member.getMNumber();

        Map<String, Boolean> errors = new HashMap<>();
        request.setAttribute("errors", errors);

        if (!memberService.isPwEqualToConfirm(pw, confirmPw)) {
            errors.put("notMatch", Boolean.TRUE);
            return "/member/withdrawForm";
        } else if (!memberService.checkPwCorrect(mNumber, pw)) {
            errors.put("notCorrect", Boolean.TRUE);
            return "/member/withdrawForm";
        }

        memberService.withdraw(mNumber);
        request.getSession().invalidate();

        return "/member/withdrawSuccess";
    }

    @GetMapping("/member/auth/checkMatching")
    public String checkMatching(@RequestParam(required = false) String matched, HttpServletRequest request, Model model) {
        String pageNoVal = request.getParameter("pageNo");

        int pageNo = 1;

        if (pageNoVal != null) {
            pageNo = Integer.parseInt(pageNoVal);
        }

        Member member = getAuthMember(request);
        int mNumber = member.getMNumber();

        FindBoardPageForm Finds = findBoardService.getMembersFindPage(pageNo, mNumber, matched);
        model.addAttribute("Finds", Finds);

        request.getSession().setAttribute("matched", matched);

        return "/member/listFindBoard";
    }

    @GetMapping("/member/auth/checkMatching/lookList")
    public String checkMatchingDetail(@RequestParam("faNumber") int faNumber, HttpServletRequest request, Model model) {
        FindBoard findBoard = findBoardService.getFindBoard(faNumber);
        String pageNoVal = request.getParameter("pageNo");

        int pageNo = 1;

        if (pageNoVal != null) {
            pageNo = Integer.parseInt(pageNoVal);
        }

        LookBoardPageForm boardPage = lookBoardService.getMatchedLookPage(pageNo, findBoard);
        model.addAttribute("matchedLookBoardForm", boardPage);

        return "/member/listLookBoard";
    }

    @GetMapping("/member/auth/myApply/{type}")
    public String getMyApply(@PathVariable("type") String type, HttpServletRequest request, Model model) {
        String pageNoVal = request.getParameter("pageNo");

        int pageNo = 1;

        if (pageNoVal != null) {
            pageNo = Integer.parseInt(pageNoVal);
        }

        Member member = getAuthMember(request);
        int mNumber = member.getMNumber();

        log.info("type : {}", type);

        if (type.equals("adopt")) {
            AdoptApplyPageForm applyPage = adoptTempService.getAdoptApplyPage(pageNo, mNumber, type);
            model.addAttribute("applyListForm", applyPage);
        } else {
            TempApplyPageForm applyPage = adoptTempService.getTempApplyPage(pageNo, mNumber, type);
            model.addAttribute("applyListForm", applyPage);
        }

        request.getSession().setAttribute("type", type);

        return "/member/applyList";
    }

    private Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");

        return member;
    }
}