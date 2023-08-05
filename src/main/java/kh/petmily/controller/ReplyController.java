package kh.petmily.controller;

import kh.petmily.domain.member.Member;
import kh.petmily.domain.reply.form.ReplyListForm;
import kh.petmily.domain.reply.form.ReplyModifyForm;
import kh.petmily.domain.reply.form.ReplyWriteForm;
import kh.petmily.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        Member authUser = (Member) request.getSession(false).getAttribute("authUser");

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
        log.info("replyWriteForm = {}", writeForm);
        replyService.write(writeForm);

        return "SUCCESS";
    }

    // 수정
    @PatchMapping("/{bNumber}")
    public String modify(@RequestBody ReplyModifyForm modifyForm) {
        log.info("replyModifyForm = {}", modifyForm);
        replyService.modify(modifyForm);

        return "SUCCESS";
    }

    // 삭제
    @DeleteMapping("/{brNumber}")
    public String delete(@PathVariable int brNumber) {
        replyService.delete(brNumber);

        return "SUCCESS";
    }
}