package kh.petmily.service;

import kh.petmily.dao.ReplyDao;
import kh.petmily.domain.reply.Reply;
import kh.petmily.domain.reply.form.ReplyListForm;
import kh.petmily.domain.reply.form.ReplyModifyForm;
import kh.petmily.domain.reply.form.ReplyWriteForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final ReplyDao replyDao;

    // ===================== Create =====================
    // 댓글 쓰기
    @Override
    public void write(ReplyWriteForm form) {
        Reply reply = toWrite(form);
        replyDao.insert(reply);
    }

    // ===================== Read =====================
    // 댓글 리스트
    @Override
    public List<ReplyListForm> getListPage(int bNumber) {
        return replyDao.selectIndexBybNumber(bNumber);
    }

    // ===================== Update =====================
    // 수정
    @Override
    public void modify(ReplyModifyForm form) {
        Reply reply = new Reply(form.getBrNumber(), form.getReply());
        replyDao.update(reply);
    }

    // ===================== Delete =====================
    // 삭제
    @Override
    public void delete(int pk) {
        replyDao.delete(pk);
    }


    // ===================== CRUD 끝 =====================


    private Reply toWrite(ReplyWriteForm form) {
        return new Reply(
                form.getbNumber(),
                form.getmNumber(),
                form.getReply()
        );
    }
}