package kh.petmily.controller;

import kh.petmily.domain.board.form.BoardModifyForm;
import kh.petmily.domain.board.form.BoardPageForm;
import kh.petmily.domain.board.form.BoardDetailForm;
import kh.petmily.domain.board.form.BoardWriteForm;
import kh.petmily.domain.board.form.BoardConditionForm;
import kh.petmily.domain.member.Member;
import kh.petmily.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(@Validated @ModelAttribute BoardConditionForm conditionForm, Model model) {

        log.info("boardConditionForm = {}", conditionForm);

        BoardPageForm boardPageForm = boardService.getBoardPage(conditionForm);
        model.addAttribute("boardPageForm", boardPageForm);

        return "/board/boardList";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam int bNumber, Model model) {
        BoardDetailForm detailForm = boardService.getBoard(bNumber);
        boardService.updateViewCount(bNumber);

        model.addAttribute("detailForm", detailForm);

        return "/board/boardDetailForm";
    }

    @GetMapping("/auth/write")
    public String writeForm() {
        return "/board/writeBoardForm";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute BoardWriteForm boardWriteForm, HttpServletRequest request) {
        boardWriteForm.setMNumber(getAuthMember(request).getMNumber());
        boardService.write(boardWriteForm);

        return "/board/writeBoardSuccess";
    }

    @GetMapping("/auth/modify")
    public String modifyForm(@RequestParam int bNumber, HttpServletRequest request, Model model) {
        BoardModifyForm boardModifyForm = boardService.getBoardModify(bNumber);

        boardModifyForm.setMNumber(getAuthMember(request).getMNumber());
        boardModifyForm.setBNumber(bNumber);

        model.addAttribute("boardModifyForm", boardModifyForm);

        return "/board/modifyForm";
    }

    @PostMapping("/auth/modify")
    public String modify(@RequestParam int bNumber, @RequestParam String kindOfBoard, @ModelAttribute BoardModifyForm boardModifyForm, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        boardModifyForm.setMNumber(getAuthMember(request).getMNumber());
        boardModifyForm.setBNumber(bNumber);

        boardService.modify(boardModifyForm);
        
        redirectAttributes.addAttribute("bNumber", bNumber);
        redirectAttributes.addAttribute("kindOfBoard", kindOfBoard);

        return "redirect:/board/detail?kindOfBoard={kindOfBoard}&bNumber={bNumber}";
    }

    @GetMapping("/auth/delete")
    public String delete(@RequestParam int bNumber) {
        boardService.delete(bNumber);

        return "/board/deleteSuccess";
    }

    private Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");

        return member;
    }
}