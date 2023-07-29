package kh.petmily.controller;

import kh.petmily.domain.find_board.form.FindBoardWriteForm;
import kh.petmily.domain.look_board.form.*;
import kh.petmily.domain.member.Member;
import kh.petmily.service.LookBoardService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;

@Controller
@RequestMapping("/lookBoard")
@RequiredArgsConstructor
@Slf4j
public class LookBoardController {

    private final LookBoardService lookBoardService;

    @GetMapping("/list")
    public String list(@Validated @ModelAttribute LookBoardConditionForm conditionForm, Model model) {
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
    public String writeForm() {
        return "/look.board/look_write";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute LookBoardWriteForm writeForm,
                        HttpServletRequest request) throws IOException {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        String filename = lookBoardService.storeFile(writeForm.getFile(), fullPath);

        writeForm.setMNumber(getAuthMember(request).getMNumber());
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

        model.addAttribute("modifyForm", modifyForm);

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
    public String delete(@RequestParam int laNumber) {
        lookBoardService.delete(laNumber);

        return "/look.board/alert_delete";
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
}