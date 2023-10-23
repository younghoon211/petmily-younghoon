package petmily.service;

import petmily.dao.ReplyDao;
import petmily.domain.reply.Reply;
import petmily.domain.reply.form.ReplyListForm;
import petmily.domain.reply.form.ReplyModifyForm;
import petmily.domain.reply.form.ReplyWriteForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final ReplyDao replyDao;

    // 댓글 쓰기
    @Override
    public void write(ReplyWriteForm form) {
        Reply reply = toWrite(form);
        replyDao.insert(reply);
    }

    // 댓글 리스트
    @Override
    public List<ReplyListForm> getListPage(int bNumber) {
        return replyDao.selectIndexBybNumber(bNumber);
    }

    // 수정
    @Override
    public void modify(ReplyModifyForm form) {
        Reply reply = new Reply(form.getBrNumber(), form.getReply());
        replyDao.update(reply);
    }

    // 삭제
    @Override
    public void delete(int pk) {
        replyDao.delete(pk);
    }

    private Reply toWrite(ReplyWriteForm form) {
        return new Reply(
                form.getbNumber(),
                form.getmNumber(),
                form.getReply()
        );
    }
}