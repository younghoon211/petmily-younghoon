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


    // =========== 회원 페이지 ===========
    Member selectMemberById(String id);

    String selectName(int pk);

    String selectPwById(String id);

    void updatePw(Member obj);

    void resetPw(Member obj);

    // =========== 관리자 페이지 ===========
    int selectCount(String keyword);

    List<Member> selectIndex(
            @Param("start") int start,
            @Param("end") int end,
            @Param("keyword") String keyword
    );

    List<Member> selectAll();

    // 회원 아이디 조회 (타 DAO에서 사용)
    String selectMemberId(int pk);

    void updateAdmin(Member obj);

    // ============= 검증 =============
    int selectIdCheck(String id);

    int selectEmailCheck(String email);

    int selectPhoneCheck(String phone);

    int selectIdCheckAtChange(
            @Param("mNumber") int mNumber,
            @Param("id") String id
    );

    int selectEmailCheckAtChange(
            @Param("mNumber") int mNumber,
            @Param("email") String email
    );

    int selectPhoneCheckAtChange(
            @Param("mNumber") int mNumber,
            @Param("phone") String phone
    );

    Member selectMemberByEmail(String email);
}