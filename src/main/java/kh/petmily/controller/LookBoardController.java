package kh.petmily.controller;

import kh.petmily.domain.look_board.form.*;
import kh.petmily.domain.member.Member;
import kh.petmily.service.LookBoardService;
import kh.petmily.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequestMapping("/lookBoard")
@RequiredArgsConstructor
@Slf4j
public class LookBoardController {

    private final LookBoardService lookBoardService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(@Validated @ModelAttribute LookBoardConditionForm conditionForm, Model model) {
        log.info("LookBoardConditionForm = {}", conditionForm);
        LookBoardPageForm pageForm = lookBoardService.getListPage(conditionForm);

        model.addAttribute("pageForm", pageForm);

        return "/look.board/look_list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam int laNumber, Model model) {
        lookBoardService.updateViewCount(laNumber);
        LookBoardDetailForm detailForm = lookBoardService.getDetailPage(laNumber);

        model.addAttribute("detailForm", detailForm);

        return "/look.board/look_detail";
    }

    //=======작성=======
    @GetMapping("/auth/write")
    public String writeForm(Model model, HttpServletRequest request) {
        int mNumber = getAuthMNumber(request);
        List<Member> memberList = memberService.selectAll();

        model.addAttribute("memberList", memberList);
        model.addAttribute("mNumber", mNumber);

        return "/look.board/look_write";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute LookBoardWriteForm writeForm,
                        HttpServletRequest request) throws IOException {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        String filename = lookBoardService.storeFile(writeForm.getFile(), fullPath);

        writeForm.setMNumber(writeForm.getMNumber());
        writeForm.setImgPath(filename);

        log.info("lookBoardWriteForm = {}", writeForm);

        lookBoardService.write(writeForm);

        return "/look.board/alert_write";
    }

    //=======수정=======
    @GetMapping("/auth/modify")
    public String modifyForm(@RequestParam int laNumber, Model model) {
        LookBoardModifyForm modifyForm = lookBoardService.getModifyForm(laNumber);
        log.info("수정 전 LookBoardModifyForm = {}", modifyForm);

        List<Member> memberList = memberService.selectAll();

        model.addAttribute("modifyForm", modifyForm);
        model.addAttribute("memberList", memberList);

        return "/look.board/look_modify";
    }

    @PostMapping("/auth/modify")
    public String modify(@Validated @ModelAttribute LookBoardModifyForm modifyForm,
                         HttpServletRequest request,
                         Model model) throws IOException {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        String filename = lookBoardService.storeFile(modifyForm.getFile(), fullPath);
        modifyForm.setImgPath(filename);

        lookBoardService.modify(modifyForm);
        log.info("수정 후 LookBoardModifyForm = {}", modifyForm);

        model.addAttribute("laNumber", modifyForm.getLaNumber());

        return "/look.board/alert_modify";
    }

    //=======삭제=======
    @GetMapping("/auth/delete")
    public String delete(@RequestParam int laNumber, HttpServletRequest request) {
        lookBoardService.delete(laNumber);

        if (getAuthMember(request).getGrade().equals("관리자")) {
            return "redirect:/admin/board?kindOfBoard=look";
        } else {
            return "redirect:/lookBoard/list?sort=lno";
        }
    }

    @ResponseBody
    @GetMapping("/upload")
    public ResponseEntity<Resource> list(String filename, HttpServletRequest request) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";
        fullPath = fullPath + filename;

        log.info("fullPath = {}", fullPath);

        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new UrlResource("file:" + fullPath));
        } catch (MalformedURLException e) {
            log.info("fullPath = {}", fullPath);

            throw new RuntimeException(e);
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