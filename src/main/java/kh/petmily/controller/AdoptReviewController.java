package kh.petmily.controller;

import kh.petmily.domain.adopt_review.form.*;
import kh.petmily.domain.member.Member;
import kh.petmily.service.AdoptReviewService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequestMapping("/adoptReview")
@RequiredArgsConstructor
@Slf4j
public class AdoptReviewController {

    private final AdoptReviewService adoptReviewService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(@Validated @ModelAttribute AdoptReviewConditionForm conditionForm, Model model) {
        log.info("AdoptReviewConditionForm = {}", conditionForm);
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
    public String writeForm(Model model, HttpServletRequest request) {
        List<Member> memberList = memberService.selectAll();
        int mNumber = getAuthMNumber(request);

        model.addAttribute("memberList", memberList);
        model.addAttribute("mNumber", mNumber);

        return "/adopt.review/adopt_review_write";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute AdoptReviewWriteForm writeForm, HttpServletRequest request) throws IOException {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";
        String filename = adoptReviewService.storeFile(writeForm.getFile(), fullPath);

        writeForm.setImgPath(filename);
        log.info("adoptReviewWriteForm = {}", writeForm);

        adoptReviewService.write(writeForm);

        return "/adopt.review/alert_write";
    }

    @GetMapping("/auth/modify")
    public String modifyForm(@RequestParam int bNumber, Model model, HttpServletRequest request) {
        AdoptReviewModifyForm modifyForm = adoptReviewService.getModifyForm(bNumber);
        log.info("수정 전 adoptReviewModifyForm={}", modifyForm);

        List<Member> memberList = memberService.selectAll();
        int mNumber = getAuthMNumber(request);

        model.addAttribute("modifyForm", modifyForm);
        model.addAttribute("memberList", memberList);
        model.addAttribute("mNumber", mNumber);

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
        log.info("수정 후 adoptReviewModifyForm = {}", modifyForm);

        adoptReviewService.modify(modifyForm);
        model.addAttribute("bNumber", modifyForm.getBNumber());

        return "/adopt.review/alert_modify";
    }

    @GetMapping("/auth/delete")
    public String delete(@RequestParam int bNumber,
                         @RequestParam String kindOfBoard,
                         RedirectAttributes redirectAttributes, HttpServletRequest request) {
        adoptReviewService.delete(bNumber);
        redirectAttributes.addAttribute("kindOfBoard", kindOfBoard);

        if (getAuthMember(request).getGrade().equals("관리자")) {
            return "redirect:/admin/board";
        } else {
            return "redirect:/adoptReview/list?sort=adoptReviewNo";
        }
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

    private int getAuthMNumber(HttpServletRequest request) {
        return getAuthMember(request).getMNumber();
    }
}