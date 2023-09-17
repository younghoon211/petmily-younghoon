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
import petmily.domain.look_board.form.*;
import petmily.domain.member.Member;
import petmily.service.LookBoardService;
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
@RequestMapping("/lookBoard")
@RequiredArgsConstructor
@Slf4j
public class LookBoardController {

    private final LookBoardService lookBoardService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(@Validated @ModelAttribute LookBoardConditionForm conditionForm, Model model) {
        log.info("GET LookBoardConditionForm = {}", conditionForm);
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
    public String writePage(Model model) {
        List<Member> memberList = memberService.getMemberList();
        model.addAttribute("memberList", memberList);

        return "/look.board/look_write";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute LookBoardWriteForm writeForm,
                        HttpServletRequest request) throws IOException {
        String fullPath = getFullPath(request);
        String newFile = lookBoardService.storeFile(writeForm.getFile(), fullPath);

        if (newFile == null) {
            log.info("이미지파일 업로드 x");
        } else {
            log.info("업로드 될 newFile = {}", newFile);
        }

        writeForm.setImgPath(newFile);
        log.info("POST lookBoardWriteForm = {}", writeForm);

        lookBoardService.write(writeForm);

        return "/alert/member/look_write";
    }

    //=======수정=======
    @GetMapping("/auth/modify")
    public String modifyPage(@RequestParam int laNumber, Model model) {
        LookBoardModifyForm modifyForm = lookBoardService.getModifyForm(laNumber);
        log.info("GET LookBoardModifyForm = {}", modifyForm);

        List<Member> memberList = memberService.getMemberList();

        model.addAttribute("modifyForm", modifyForm);
        model.addAttribute("memberList", memberList);

        return "/look.board/look_modify";
    }

    @PostMapping("/auth/modify")
    public String modify(@Validated @ModelAttribute LookBoardModifyForm modifyForm,
                         HttpServletRequest request) throws IOException {
        String fullPath = getFullPath(request);

        String initFile = lookBoardService.getLookBoard(modifyForm.getLaNumber()).getImgPath();
        log.info("initFile(기존 업로드 파일명) = {}", initFile);

        String newFile = lookBoardService.storeFile(modifyForm.getFile(), fullPath);
        log.info("newFile(새로 업로드한 파일명) = {}", newFile);

        boolean hasExistingImage = !initFile.equals("no_image.png");

        if (newFile != null && !hasExistingImage) {
            modifyForm.setImgPath(newFile);
        }

        if (newFile != null && hasExistingImage) {
            String deletePath = fullPath + initFile;
            deleteFile(deletePath); // 예전사진 삭제
            modifyForm.setImgPath(newFile);
        }

        log.info("POST LookBoardModifyForm = {}", modifyForm);
        lookBoardService.modify(modifyForm);

        return "/alert/member/look_modify";
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
        }

        return "SUCCESS";
    }

    //=======삭제=======
    @GetMapping("/auth/delete")
    public String delete(@RequestParam int laNumber, HttpServletRequest request) {
        String filename = lookBoardService.getLookBoard(laNumber).getImgPath();
        boolean ExistingInitFile = filename != null && !filename.equals("no_image.png");

        if (ExistingInitFile) {
            String fullPath = getFullPath(request) + filename;
            deleteFile(fullPath);
        }

        lookBoardService.delete(laNumber);

        if (isAdminUser(request)) {
            return "redirect:/admin/board?kindOfBoard=look";
        } else {
            return "redirect:/lookBoard/list?sort=lno";
        }
    }

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
        if (session != null) {
            return (Member) session.getAttribute("authUser");
        }
        return null;
    }

    private boolean isAdminUser(HttpServletRequest request) {
        return getAuthUser(request).getGrade().equals("관리자");
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