package petmily.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import petmily.domain.adopt_review.form.*;
import petmily.domain.member.Member;
import petmily.service.AdoptReviewService;
import petmily.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/adoptReview")
@RequiredArgsConstructor
@Slf4j
public class AdoptReviewController {

    private final AdoptReviewService adoptReviewService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(@Validated @ModelAttribute AdoptReviewConditionForm conditionForm, Model model, HttpServletRequest request) {
        log.info("GET AdoptReviewConditionForm = {}", conditionForm);
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
    public String writePage(Model model) {
        List<Member> memberList = memberService.getMemberList();
        model.addAttribute("memberList", memberList);

        return "/adopt.review/adopt_review_write";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute AdoptReviewWriteForm writeForm, HttpServletRequest request) throws IOException {
        String fullPath = getFullPath(request);
        String newFile = adoptReviewService.storeFile(writeForm.getFile(), fullPath);

        if (newFile == null) {
            log.info("이미지파일 업로드 x");
        } else {
            log.info("업로드 될 newFile = {}", newFile);
        }

        writeForm.setImgPath(newFile);
        log.info("POST adoptReviewWriteForm = {}", writeForm);

        adoptReviewService.write(writeForm);

        return "/alert/member/adopt_review_write";
    }

    // 입양기록 없는 회원이 후기글 작성 시도 시 알림창
    @GetMapping("/auth/write/alert/NotAdopted")
    public String alertNotAdopted() {
        return "/alert/member/adopt_review_notAdopted";
    }

    @GetMapping("/auth/modify")
    public String modifyPage(@RequestParam int bNumber, Model model) {
        AdoptReviewModifyForm modifyForm = adoptReviewService.getModifyForm(bNumber);
        log.info("GET adoptReviewModifyForm={}", modifyForm);

        List<Member> memberList = memberService.getMemberList();

        model.addAttribute("modifyForm", modifyForm);
        model.addAttribute("memberList", memberList);

        return "/adopt.review/adopt_review_modify";
    }

    @PostMapping("/auth/modify")
    public String modify(@Validated @ModelAttribute AdoptReviewModifyForm modifyForm,
                         HttpServletRequest request) throws IOException {
        String fullPath = getFullPath(request);

        String initFile = adoptReviewService.getAdoptReview(modifyForm.getBNumber()).getImgPath();
        log.info("initFile(기존 업로드 파일명) = {}", initFile);

        String newFile = adoptReviewService.storeFile(modifyForm.getFile(), fullPath);
        log.info("newFile(새로 업로드한 파일명) = {}", newFile);

        boolean hasExistingImage = !"no_image.png".equals(initFile);

        if (newFile != null && !hasExistingImage) {
            modifyForm.setImgPath(newFile);
        }

        if (newFile != null && hasExistingImage) {
            String deletePath = fullPath + initFile;
            deleteFile(deletePath); // 예전사진 삭제
            modifyForm.setImgPath(newFile);
        }

        log.info("POST adoptReviewModifyForm = {}", modifyForm);
        adoptReviewService.modify(modifyForm);

        return "/alert/member/adopt_review_modify";
    }

    // 수정 전 업로드됐던 이미지파일 삭제
    @ResponseBody
    @DeleteMapping("/{imgPath}")
    public String responseDeleteFile(@PathVariable String imgPath, HttpServletRequest request) {
        String fullPath = getFullPath(request) + imgPath;

        File file = new File(fullPath);

        if (file.exists()) {
            if (file.delete()) {
                log.info("이미지 파일 삭제 성공");
            } else {
                log.info("이미지 파일 삭제 실패");
            }
        } else {
            return "ERROR";
        }

        return "SUCCESS";
    }

    @GetMapping("/auth/delete")
    public String delete(@RequestParam int bNumber,
                         @RequestParam String kindOfBoard,
                         RedirectAttributes redirectAttributes, HttpServletRequest request) {
        String filename = adoptReviewService.getAdoptReview(bNumber).getImgPath();
        boolean ExistingInitFile = filename != null && !"no_image.png".equals(filename);

        if (ExistingInitFile) {
            String fullPath = getFullPath(request) + filename;
            deleteFile(fullPath);
        }

        adoptReviewService.delete(bNumber);

        redirectAttributes.addAttribute("kindOfBoard", kindOfBoard);

        if (isAdminUser(request)) {
            return "redirect:/admin/board";
        } else {
            return "redirect:/adoptReview/list?sort=adoptReviewNo";
        }
    }

    // 이미지파일 불러오기
    @ResponseBody
    @GetMapping("/upload")
    public ResponseEntity<Resource> getImage(@RequestParam String filename, HttpServletRequest request) {
        try {
            Path imagePath = Paths.get(getFullPath(request) + filename);
            log.info("imagePath = {} ", imagePath);

            Resource resource = new UrlResource(imagePath.toUri());
            MediaType mediaType = MediaTypeFactory.getMediaType(resource).orElse(MediaType.IMAGE_PNG);

            if (!resource.exists()) {
                throw new FileNotFoundException("존재하지 않는 파일입니다.");
            }

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(resource);
        } catch (MalformedURLException e) {
            log.error("Malformed URL = {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (FileNotFoundException e) {
            log.error("Image not found = {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Member getAuthUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member authUser = (Member) session.getAttribute("authUser");
        if (authUser == null) {
            log.info("authUser is null");
        }

        return authUser;
    }

    private boolean isAdminUser(HttpServletRequest request) {
        return "관리자".equals(getAuthUser(request).getGrade());
    }

    private String getFullPath(HttpServletRequest request) {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        return fullPath;
    }

    private void deleteFile(String deletePath) {
        File fileToDelete = new File(deletePath);
        if (fileToDelete.delete()) {
            log.info("이미지파일 삭제 성공 = {}", deletePath);
        }
    }
}