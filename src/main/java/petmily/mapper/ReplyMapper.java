package petmily.mapper;

import petmily.domain.reply.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

    // =======BasicMapper 메소드=======
    Reply selectByPk(int pk);

    void insert(Reply obj);

    void update(Reply obj);

    void delete(int pk);

    // 댓글 조회
    List<Reply> selectIndexBybNumber(int bNumber);
}