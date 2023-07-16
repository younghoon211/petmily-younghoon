package kh.petmily.controller;

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
        LookBoardPageForm lookBoardPageForm = lookBoardService.getLookPage(conditionForm);
        model.addAttribute("Looks", lookBoardPageForm);

        return "/look_board/listLookBoard";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam int laNumber, Model model) {
        lookBoardService.updateViewCount(laNumber);

        LookBoardDetailForm detailForm = lookBoardService.getDetailForm(laNumber);
        log.info("LookDetailForm = {}", detailForm);

        model.addAttribute("lookIn", detailForm);

        return "/look_board/detailLookBoard";
    }

    //=======작성=======
    @GetMapping("/auth/write")
    public String writeForm() {
        return "/look_board/writeLookBoardForm";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute LookBoardWriteForm lookBoardWriteForm, HttpServletRequest request) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        Member member = getAuthMember(request);

        int mNumber = member.getMNumber();
        lookBoardWriteForm.setMNumber(mNumber);
        String filename = "";

        if (!lookBoardWriteForm.getImgPath().isEmpty()) {
            try {
                filename = lookBoardService.storeFile(lookBoardWriteForm.getImgPath(), fullPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            lookBoardWriteForm.setFullPath(filename);
        } else {
            lookBoardWriteForm.setFullPath("");
        }


        log.info("LookWriteForm = {}", lookBoardWriteForm);

        lookBoardService.write(lookBoardWriteForm);

        return "/look_board/submitSuccess";
    }

    //=======수정=======
    @GetMapping("/auth/modify")
    public String modifyForm(@RequestParam int laNumber, HttpServletRequest request, Model model) {
        LookBoardModifyForm lmForm = lookBoardService.getModifyForm(laNumber);

        Member member = getAuthMember(request);

        int mNumber = member.getMNumber();
        lmForm.setMNumber(mNumber);
        lmForm.setLaNumber(laNumber);

        model.addAttribute("lookMod", lmForm);

        return "/look_board/modifyLookBoardForm";
    }

    @PostMapping("/auth/modify")
    public String modify(@RequestParam int laNumber,
                         @ModelAttribute LookBoardModifyForm lookBoardModifyForm,
                         HttpServletRequest request,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        Member member = getAuthMember(request);

        int mNumber = member.getMNumber();
        lookBoardModifyForm.setMNumber(mNumber);
        lookBoardModifyForm.setLaNumber(laNumber);

        log.info("LookModifyForm = {}", lookBoardModifyForm);
        String filename = null;

        try {
            filename = lookBoardService.storeFile(lookBoardModifyForm.getImgPath(), fullPath);
            lookBoardModifyForm.setFullPath(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        lookBoardService.modify(lookBoardModifyForm);

        model.addAttribute("lookMod", lookBoardModifyForm);

        redirectAttributes.addAttribute("laNumber", laNumber);

        return "redirect:/lookBoard/detail?laNumber={laNumber}";
    }

    //=======삭제=======
    @GetMapping("/auth/delete")
    public String delete(@RequestParam int laNumber) {
        lookBoardService.delete(laNumber);

        return "/look_board/submitSuccess";
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