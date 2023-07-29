package kh.petmily.service;

import kh.petmily.dao.MemberDao;
import kh.petmily.dao.ReplyDao;
import kh.petmily.domain.reply.Reply;
import kh.petmily.domain.reply.form.ReadReplyForm;
import kh.petmily.domain.reply.form.ReplyModifyForm;
import kh.petmily.domain.reply.form.ReplyWriteForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final ReplyDao replyDao;
    private final MemberDao memberDao;

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
    public List<ReadReplyForm> getList(int bNumber) {
        List<ReadReplyForm> result = new ArrayList<>();
        List<Reply> list = replyDao.list(bNumber);

        for (Reply r : list) {
            String writer = memberDao.selectName(r.getMNumber());
            result.add(toReplyForm(r, writer));
        }

        return result;
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

    private ReadReplyForm toReplyForm(Reply r, String writer) {
        return new ReadReplyForm(
                r.getBrNumber(),
                r.getMNumber(),
                r.getReply(),
                r.getWrTime(),
                writer);
    }
}