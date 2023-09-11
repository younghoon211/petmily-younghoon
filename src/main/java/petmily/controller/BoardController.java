package petmily.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import petmily.domain.board.form.*;
import petmily.domain.member.Member;
import petmily.service.BoardService;
import petmily.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(@Validated @ModelAttribute BoardConditionForm conditionForm, Model model) {
        log.info("BoardConditionForm = {}", conditionForm);
        BoardPageForm pageForm = boardService.getListPage(conditionForm);

        model.addAttribute("pageForm", pageForm);

        return "/board/board_list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam int bNumber, Model model) {
        BoardDetailForm detailForm = boardService.getDetailPage(bNumber);
        boardService.updateViewCount(bNumber);

        model.addAttribute("detailForm", detailForm);

        return "/board/board_detail";
    }

    @GetMapping("/auth/write")
    public String writePage(Model model, HttpServletRequest request) {
        int mNumber = getAuthMNumber(request);
        List<Member> memberList = memberService.getMemberList();

        model.addAttribute("mNumber", mNumber);
        model.addAttribute("memberList", memberList);

        return "/board/board_write";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute BoardWriteForm writeForm) {
        log.info("BoardWriteForm = {}", writeForm);
        boardService.write(writeForm);

        return "/alert/common/board_write";
    }

    @GetMapping("/auth/modify")
    public String modifyPage(@RequestParam int bNumber, Model model) {
        BoardModifyForm modifyForm = boardService.getModifyForm(bNumber);
        log.info("수정 전 boardModifyForm = {}", modifyForm);

        List<Member> memberList = memberService.getMemberList();

        model.addAttribute("modifyForm", modifyForm);
        model.addAttribute("memberList", memberList);

        return "/board/board_modify";
    }

    @PostMapping("/auth/modify")
    public String modify(@Validated @ModelAttribute BoardModifyForm modifyForm) {
        log.info("수정 후 boardModifyForm = {}", modifyForm);
        boardService.modify(modifyForm);

        return "/alert/common/board_modify";
    }

    @GetMapping("/auth/delete")
    public String delete(@RequestParam int bNumber,
                         @RequestParam String kindOfBoard ,
                         RedirectAttributes redirectAttributes, HttpServletRequest request) {
        boardService.delete(bNumber);
        redirectAttributes.addAttribute("kindOfBoard", kindOfBoard);

        if (getAuthUser(request).getGrade().equals("관리자")) {
            return "redirect:/admin/board";
        } else {
            return "redirect:/board/list?sort=bno";
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