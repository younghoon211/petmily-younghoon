package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.reply.Reply;
import kh.petmily.domain.reply.form.ReplyListForm;
import kh.petmily.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDao implements BasicDao {

    private final ReplyMapper mapper;
    private final MemberDao memberDao;

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
        List<ReplyListForm> result = new ArrayList<>();
        List<Reply> list = mapper.selectIndexBybNumber(bNumber);

        for (Reply r : list) {
            String formattedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(r.getWrTime());

            ReplyListForm li = new ReplyListForm(
                    r.getBrNumber(),
                    r.getMNumber(),
                    r.getReply(),
                    formattedTime,
                    memberDao.selectName(r.getMNumber())
            );

            result.add(li);
        }

        return result;
    }
}