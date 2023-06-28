package kh.petmily.controller;

import kh.petmily.domain.adopt_review.form.AdoptReviewListForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewModifyForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewWriteForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewPageForm;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") Integer pbNumber,
                       @RequestParam String kindOfBoard,
                       @RequestParam(required = false) String searchType,
                       @RequestParam(required = false) String keyword,
                       @RequestParam String sort,
                       Model model) {

        AdoptReviewPageForm adoptReviewPageForm = adoptReviewService.getAdoptReviewPage(pbNumber, kindOfBoard, searchType, keyword, sort);
        model.addAttribute("boardList", adoptReviewPageForm);

        return "/adopt_review/listAdoptReview";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("bNumber") int bNumber, Model model) {
        AdoptReviewListForm detailForm = adoptReviewService.getAdoptReview(bNumber);

        // ====== 조회수 추가 ======
        adoptReviewService.updateViewCount(bNumber);

        model.addAttribute("detailForm", detailForm);

        return "/adopt_review/detailFormAdoptReview";
    }

    @GetMapping("/auth/write")
    public String writeForm() {
        return "/adopt_review/writeAdoptReviewForm";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute AdoptReviewWriteForm adoptReviewWriteForm, HttpServletRequest request) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        if (adoptReviewWriteForm.getmNumber() == 0) {
            Member member = getAuthMember(request);
            int mNumber = member.getMNumber();

            adoptReviewWriteForm.setmNumber(mNumber);
        }

        String filename = "";

        if (!adoptReviewWriteForm.getImgPath().isEmpty()) {

            try {
                log.info("ImgPath = {}, fullPath = {}", adoptReviewWriteForm.getImgPath(), fullPath);

                filename = adoptReviewService.storeFile(adoptReviewWriteForm.getImgPath(), fullPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            adoptReviewWriteForm.setFullPath(filename);
        } else {
            adoptReviewWriteForm.setFullPath("");
        }

        log.info("adoptReviewWriteForm = {}", adoptReviewWriteForm);

        adoptReviewService.write(adoptReviewWriteForm);

        return "/adopt_review/writeAdoptReviewSuccess";
    }

    @GetMapping("/auth/modify")
    public String modifyForm(@RequestParam("bNumber") int bNumber, HttpServletRequest request, Model model) {
        AdoptReviewModifyForm modReq = adoptReviewService.getAdoptReviewModify(bNumber);
        Member authUser = getAuthMember(request);

        int mNumber = authUser.getMNumber();
        modReq.setMNumber(mNumber);

        log.info("modReq={}", modReq);

        model.addAttribute("modReq", modReq);

        return "/adopt_review/modifyAdoptReviewForm";
    }

    @PostMapping("/auth/modify")
    public String modify(@RequestParam("bNumber") int bNumber, @RequestParam("kindOfBoard") String kindOfBoard, @ModelAttribute AdoptReviewModifyForm modReq, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        Member authUser = getAuthMember(request);

        int mNumber = authUser.getMNumber();
        modReq.setMNumber(mNumber);
        modReq.setBNumber(bNumber);

        log.info("BoardModifyForm = {}", modReq);
        String filename = null;

        try {
            filename = adoptReviewService.storeFile(modReq.getImgPath(), fullPath);
            modReq.setFullPath(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        adoptReviewService.modify(modReq);
        model.addAttribute("modReq", modReq);
        redirectAttributes.addAttribute("bNumber", bNumber);
        redirectAttributes.addAttribute("kindOfBoard", kindOfBoard);

        return "redirect:/adopt_review/detail?kindOfBoard={kindOfBoard}&bNumber={bNumber}";
    }

    @GetMapping("/auth/delete")
    public String delete(@RequestParam("bNumber") int bNumber) {
        adoptReviewService.delete(bNumber);

        return "/adopt_review/deleteSuccess";
    }

    private Member getAuthMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");

        return member;
    }
}