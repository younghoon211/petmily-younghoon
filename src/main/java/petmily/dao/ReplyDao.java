package petmily.dao;

import petmily.domain.DomainObj;
import petmily.domain.reply.Reply;
import petmily.domain.reply.form.ReplyListForm;
import petmily.mapper.MemberMapper;
import petmily.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDao implements BasicDao {

    private final ReplyMapper mapper;
    private final MemberMapper memberMapper;

    @Override
    public Reply findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((Reply) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((Reply) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }

    public List<ReplyListForm> selectIndexBybNumber(int bNumber) {
        List<ReplyListForm> replyListForms = new ArrayList<>();
        List<Reply> replies = mapper.selectIndexBybNumber(bNumber);

        for (Reply reply : replies) {
            ReplyListForm listForm = new ReplyListForm(
                    reply.getBrNumber(),
                    reply.getMNumber(),
                    reply.getReply(),
                    reply.getWrTime().format(getFormatter()),
                    memberMapper.selectName(reply.getMNumber())
            );

            replyListForms.add(listForm);
        }

        return replyListForms;
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}