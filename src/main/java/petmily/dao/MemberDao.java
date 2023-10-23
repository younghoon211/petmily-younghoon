package petmily.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import petmily.domain.DomainObj;
import petmily.domain.admin_form.MemberDetailForm;
import petmily.domain.member.Member;
import petmily.mapper.MemberMapper;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDao implements BasicDao {

    private final MemberMapper mapper;

    @Override
    public Member findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((Member) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((Member) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }

    // ===================== Read =====================
    // 아이디로 회원 정보 조회
    public Member selectMemberById(String id) {
        return mapper.selectMemberById(id);
    }

    // 닉네임 조회
    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    // 아이디로 비번 조회
    public String selectPwById(String id) {
        return mapper.selectPwById(id);
    }


    // ===================== Update =====================
    // 비밀번호 변경 (마이페이지)
    public void updatePw(DomainObj obj) {
        mapper.updatePw((Member) obj);
    }

    // 비밀번호 재설정 (비번찾기)
    public void resetPw(DomainObj obj) {
        mapper.resetPw((Member) obj);
    }

    // ===================== 관리자 =====================
    // 조건부 검색 - 총 회원 수 (페이징)
    public int selectCount(String keyword) {
        return mapper.selectCount(keyword);
    }

    // 조건부 검색 - 회원 index
    public List<MemberDetailForm> selectIndex(int start, int end, String keyword) {
        List<MemberDetailForm> memberDetailForms = new ArrayList<>();
        List<Member> members = mapper.selectIndex(start, end, keyword);

        for (Member member : members) {
            MemberDetailForm detailForm = new MemberDetailForm(
                    member.getMNumber(),
                    member.getId(),
                    member.getPw(),
                    member.getName(),
                    member.getBirth(),
                    member.getGender(),
                    member.getEmail(),
                    member.getPhone(),
                    member.getGrade()
            );

            memberDetailForms.add(detailForm);
        }

        return memberDetailForms;
    }

    // 모든 회원 리스트 조회
    public List<Member> selectAll() {
        return mapper.selectAll();
    }

    // 회원정보 수정
    public void updateAdmin(DomainObj obj) {
        mapper.updateAdmin((Member) obj);
    }

    // ===================== 검증 =====================
    // 회원가입 검증 (아이디)
    public int selectIdCheck(String id) {
        return mapper.selectIdCheck(id);
    }

    // 회원가입 검증 (이메일)
    public int selectEmailCheck(String email) {
        return mapper.selectEmailCheck(email);
    }

    // 회원가입 검증 (연락처)
    public int selectPhoneCheck(String phone) {
        return mapper.selectPhoneCheck(phone);
    }

    // 회원정보 변경 검증 (아이디) : 관리자
    public int selectIdCheckAtChange(int mNumber, String id) {
        return mapper.selectIdCheckAtChange(mNumber, id);
    }

    // 회원정보 변경 검증 (이메일) : 관리자+회원
    public int selectEmailCheckAtChange(int mNumber, String email) {
        return mapper.selectEmailCheckAtChange(mNumber, email);
    }

    // 회원정보 변경 검증 (연락처) : 관리자+회원
    public int selectPhoneCheckAtChange(int mNumber, String email) {
        return mapper.selectPhoneCheckAtChange(mNumber, email);
    }

    // 아이디, 비번 찾기 검증 (이메일)
    public Member selectMemberByEmail(String email) {
        return mapper.selectMemberByEmail(email);
    }
}