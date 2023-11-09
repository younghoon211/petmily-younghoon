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
import petmily.domain.find_board.form.*;
import petmily.domain.member.Member;
import petmily.service.FindBoardService;
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
@RequestMapping("/findBoard")
@RequiredArgsConstructor
@Slf4j
public class FindBoardController {

    private final FindBoardService findBoardService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(@Validated @ModelAttribute FindBoardConditionForm conditionForm, Model model) {
        log.info("GET FindBoardConditionForm = {}", conditionForm);
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
    public String writePage(Model model) {
        List<Member> memberList = memberService.getMemberList();
        model.addAttribute("memberList", memberList);

        return "/find.board/find_write";
    }

    @PostMapping("/auth/write")
    public String write(@ModelAttribute FindBoardWriteForm writeForm, HttpServletRequest request) throws IOException {
        String fullPath = getFullPath(request);
        String newFile = findBoardService.storeFile(writeForm.getFile(), fullPath);

        if (newFile == null) {
            log.info("이미지파일 업로드 x");
        } else {
            log.info("업로드 될 newFile = {}", newFile);
        }

        writeForm.setImgPath(newFile);
        log.info("POST findBoardWriteForm = {}", writeForm);

        findBoardService.write(writeForm);

        return "/alert/member/find_write";
    }

    //=======수정=======
    @GetMapping("/auth/modify")
    public String modifyPage(@RequestParam int faNumber, Model model) {
        FindBoardModifyForm modifyForm = findBoardService.getModifyForm(faNumber);
        log.info("GET FindBoardModifyForm = {}", modifyForm);

        List<Member> memberList = memberService.getMemberList();

        model.addAttribute("modifyForm", modifyForm);
        model.addAttribute("memberList", memberList);

        return "/find.board/find_modify";
    }

    @PostMapping("/auth/modify")
    public String modify(@Validated @ModelAttribute FindBoardModifyForm modifyForm, HttpServletRequest request) throws IOException {
        String fullPath = getFullPath(request);

        String initFile = findBoardService.getFindBoard(modifyForm.getFaNumber()).getImgPath();
        log.info("initFile(기존 업로드 파일명) = {}", initFile);

        String newFile = findBoardService.storeFile(modifyForm.getFile(), fullPath);
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

        log.info("POST FindBoardModifyForm = {}", modifyForm);
        findBoardService.modify(modifyForm);

        return "/alert/member/find_modify";
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

    //=======삭제=======
    @GetMapping("/auth/delete")
    public String delete(@RequestParam int faNumber, HttpServletRequest request) {
        String filename = findBoardService.getFindBoard(faNumber).getImgPath();
        boolean ExistingInitFile = filename != null && !"no_image.png".equals(filename);

        if (ExistingInitFile) {
            String fullPath = getFullPath(request) + filename;
            deleteFile(fullPath);
        }

        findBoardService.delete(faNumber);

        if (isAdminUser(request)) {
            return "redirect:/admin/board?kindOfBoard=find";
        } else {
            return "redirect:/findBoard/list?sort=fno";
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