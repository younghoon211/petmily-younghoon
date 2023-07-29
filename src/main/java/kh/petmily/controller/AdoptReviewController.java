package kh.petmily.controller;

import kh.petmily.domain.adopt_review.form.*;
import kh.petmily.domain.member.Member;
import kh.petmily.service.AdoptReviewService;
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

@Controller
@RequestMapping("/adopt_review")
@RequiredArgsConstructor
@Slf4j
public class AdoptReviewController {

    private final AdoptReviewService adoptReviewService;

    @GetMapping("/list")
    public String list(@Validated @ModelAttribute AdoptReviewConditionForm conditionForm, Model model) {

        AdoptReviewPageForm pageForm = adoptReviewService.getListPage(conditionForm);
        model.addAttribute("pageForm", pageForm);

        return "/adopt.review/adopt_review_list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam int bNumber, Model model) {
        AdoptReviewDetailForm detailForm = adoptReviewService.getDetailPage(bNumber);
        adoptReviewService.updateViewCount(bNumber);

        model.addAttribute("detailForm", detailForm);

        return "/adopt.review/adopt_review_detail";
    }

    @GetMapping("/auth/write")
    public String writeForm() {
        return "/adopt.review/adopt_review_write";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute AdoptReviewWriteForm writeForm, HttpServletRequest request) throws IOException {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        String filename = adoptReviewService.storeFile(writeForm.getFile(), fullPath);

        writeForm.setMNumber(getAuthMember(request).getMNumber());
        writeForm.setImgPath(filename);

        log.info("adoptReviewWriteForm = {}", writeForm);

        adoptReviewService.write(writeForm);

        return "/adopt.review/alert_write";
    }

    @GetMapping("/auth/modify")
    public String modifyForm(@RequestParam int bNumber, Model model) {
        AdoptReviewModifyForm modifyForm = adoptReviewService.getModifyForm(bNumber);
        log.info("수정 전 adoptReviewModifyForm={}", modifyForm);

        model.addAttribute("modifyForm", modifyForm);

        return "/adopt.review/adopt_review_modify";
    }

    @PostMapping("/auth/modify")
    public String modify(@Validated @ModelAttribute AdoptReviewModifyForm modifyForm,
                         HttpServletRequest request,
                         Model model) throws IOException {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        String filename = adoptReviewService.storeFile(modifyForm.getFile(), fullPath);
        modifyForm.setImgPath(filename);

        adoptReviewService.modify(modifyForm);
        log.info("수정 후 adoptReviewModifyForm = {}", modifyForm);

        model.addAttribute("bNumber", modifyForm.getBNumber());

        return "/adopt.review/alert_modify";
    }

    @GetMapping("/auth/delete")
    public String delete(@RequestParam int bNumber) {
        adoptReviewService.delete(bNumber);

        return "/adopt.review/alert_delete";
    }

    @ResponseBody
    @GetMapping("/upload")
    public ResponseEntity<Resource> list(String filename, HttpServletRequest request) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";
        fullPath = fullPath + filename;

        log.info("fullPath = {} ", fullPath);

        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new UrlResource("file:" + fullPath));
        } catch (MalformedURLException e) {
            log.info("fullPath = {} ", fullPath);

            throw new RuntimeException(e);
        }
    }

    private Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");

        return member;
    }
}