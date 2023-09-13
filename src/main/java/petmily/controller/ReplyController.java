package petmily.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petmily.domain.member.Member;
import petmily.domain.reply.form.ReplyListForm;
import petmily.domain.reply.form.ReplyModifyForm;
import petmily.domain.reply.form.ReplyWriteForm;
import petmily.service.ReplyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

    private final ReplyService replyService;

    // 댓글 리스트
    @GetMapping("/{bNumber}")
    public ResponseEntity<List<ReplyListForm>> list(@PathVariable int bNumber, HttpServletRequest request) {
        Member authUser = getAuthUser(request);

        List<ReplyListForm> list = replyService.getListPage(bNumber);

        if (authUser != null) {
            for (ReplyListForm r : list) {
                if (r.getMNumber() == authUser.getMNumber() || authUser.getGrade().equals("관리자")) {
                    r.setHasPermission(true);
                }
            }
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 작성
    @PostMapping("/{bNumber}")
    public String write(@RequestBody ReplyWriteForm writeForm) {
        log.info("POST replyWriteForm = {}", writeForm);
        replyService.write(writeForm);

        return "SUCCESS";
    }

    // 수정
    @PatchMapping("/{bNumber}")
    public String modify(@RequestBody ReplyModifyForm modifyForm) {
        log.info("Patch replyModifyForm = {}", modifyForm);
        replyService.modify(modifyForm);

        return "SUCCESS";
    }

    // 삭제
    @DeleteMapping("/{brNumber}")
    public String delete(@PathVariable int brNumber) {
        replyService.delete(brNumber);

        return "SUCCESS";
    }

    private Member getAuthUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Member) session.getAttribute("authUser");
        }
        return null;
    }
}