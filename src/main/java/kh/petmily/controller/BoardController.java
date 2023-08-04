package kh.petmily.controller;

import kh.petmily.domain.board.form.*;
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
    public String writeForm() {
        return "/board/board_write";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute BoardWriteForm writeForm, HttpServletRequest request) {
        writeForm.setMNumber(getAuthMember(request).getMNumber());
        log.info("BoardWriteForm = {}", writeForm);

        boardService.write(writeForm);

        return "/board/alert_write";
    }

    @GetMapping("/auth/modify")
    public String modifyForm(@RequestParam int bNumber, Model model) {
        BoardModifyForm modifyForm = boardService.getModifyForm(bNumber);
        log.info("수정 전 boardModifyForm = {}", modifyForm);

        model.addAttribute("modifyForm", modifyForm);

        return "/board/board_modify";
    }

    @PostMapping("/auth/modify")
    public String modify(@Validated @ModelAttribute BoardModifyForm modifyForm, Model model) {
        boardService.modify(modifyForm);
        log.info("수정 후 boardModifyForm = {}", modifyForm);

        model.addAttribute("modifyForm", modifyForm);

        return "/board/alert_modify";
    }

    @GetMapping("/auth/delete")
    public String delete(@RequestParam int bNumber,
                         @RequestParam String kindOfBoard ,
                         RedirectAttributes redirectAttributes) {
        boardService.delete(bNumber);
        redirectAttributes.addAttribute("kindOfBoard", kindOfBoard);

        return "redirect:/board/list?sort=bno";
    }

    private Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");

        return member;
    }
}