package petmily.mapper;

import petmily.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    // =======BasicMapper 메소드=======
    Member selectByPk(int pk);

    void insert(Member obj);

    void update(Member obj);

    void delete(int pk);
    // ===============================

    Member selectMemberById(String id);

    String selectMemberId(int pk);

    String selectName(int pk);

    int selectCount();

    List<Member> selectIndex(
            @Param("start") int start,
            @Param("end") int end
    );

    List<Member> selectAll();

    int selectIdCheck(String id);

    int selectEmailCheck(String email);

    int selectPhoneCheck(String phone);

    int selectEmailCheckChangeInfo(
            @Param("email") String email,
            @Param("id") String id
    );

    int selectPhoneCheckChangeInfo(
            @Param("phone") String phone,
            @Param("id") String id
    );

    int selectCount(String keyword);

    List<Member> selectIndex(
            @Param("start") int start,
            @Param("end") int end,
            @Param("keyword") String keyword
    );
}