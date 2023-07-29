package kh.petmily.controller;

import kh.petmily.domain.find_board.form.*;
import kh.petmily.domain.member.Member;
import kh.petmily.service.FindBoardService;
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
@RequestMapping("/findBoard")
@RequiredArgsConstructor
@Slf4j
public class FindBoardController {

    private final FindBoardService findBoardService;

    @GetMapping("/list")
    public String list(@Validated @ModelAttribute FindBoardConditionForm conditionForm, Model model) {
        FindBoardPageForm pageForm = findBoardService.getListPage(conditionForm);
        model.addAttribute("pageForm", pageForm);

        return "/find.board/find_list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam int faNumber, Model model) {
        findBoardService.updateViewCount(faNumber);
        FindBoardDetailForm detailForm = findBoardService.getDetailPage(faNumber);

        model.addAttribute("detailForm", detailForm);

        return "/find.board/find_detail";
    }

    //=======작성=======
    @GetMapping("/auth/write")
    public String writeForm() {
        return "/find.board/find_write";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute FindBoardWriteForm writeForm,
                        HttpServletRequest request) throws IOException {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        String filename = findBoardService.storeFile(writeForm.getFile(), fullPath);

        writeForm.setMNumber(getAuthMember(request).getMNumber());
        writeForm.setImgPath(filename);

        log.info("findBoardWriteForm = {}", writeForm);

        findBoardService.write(writeForm);

        return "/find.board/alert_write";
    }

    //=======수정=======
    @GetMapping("/auth/modify")
    public String modifyForm(@RequestParam int faNumber, Model model) {
        FindBoardModifyForm modifyForm = findBoardService.getModifyForm(faNumber);
        log.info("수정 전 FindBoardModifyForm = {}", modifyForm);

        model.addAttribute("modifyForm", modifyForm);

        return "/find.board/find_modify";
    }

    @PostMapping("/auth/modify")
    public String modify(@Validated @ModelAttribute FindBoardModifyForm modifyForm,
                         HttpServletRequest request, Model model) throws IOException {
        String fullPath = request.getSession().getServletContext().getRealPath("/");
        fullPath = fullPath + "resources/upload/";

        String filename = findBoardService.storeFile(modifyForm.getFile(), fullPath);
        modifyForm.setImgPath(filename);

        findBoardService.modify(modifyForm);
        log.info("수정 후 FindBoardModifyForm = {}", modifyForm);

        model.addAttribute("faNumber", modifyForm.getFaNumber());

        return "/find.board/alert_modify";
    }

    //=======삭제=======
    @GetMapping("/auth/delete")
    public String delete(@RequestParam int faNumber) {
        findBoardService.delete(faNumber);

        return "/find.board/alert_delete";
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