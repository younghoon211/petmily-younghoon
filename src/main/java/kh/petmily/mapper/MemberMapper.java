package kh.petmily.mapper;

import kh.petmily.domain.member.Member;
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

    String selectName(int pk);

    int selectCount();

    List<Member> selectIndex(@Param("start") int start, @Param("end") int end);

    String selectMemberId(int pk);

    List<Member> selectAll();

    int selectIdCheck(String id);

    int selectEmailCheck(String email);

    int selectPhoneCheck(String phone);

    int selectEmailCheckMemberChange(@Param("email") String email, @Param("id") String id);

    int selectPhoneCheckMemberChange(@Param("phone") String phone, @Param("id") String id);
}