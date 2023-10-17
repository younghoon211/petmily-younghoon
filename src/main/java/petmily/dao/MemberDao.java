package petmily.dao;

import petmily.domain.DomainObj;
import petmily.domain.member.Member;
import petmily.domain.admin_form.MemberDetailForm;
import petmily.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

    public Member selectMemberById(String id) {
        return mapper.selectMemberById(id);
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    public void updatePw(DomainObj obj) {
        mapper.updatePw((Member) obj);
    }

    public void resetPw(DomainObj obj) {
        mapper.resetPw((Member) obj);
    }

    public List<Member> selectAll() {
        return mapper.selectAll();
    }

    public int selectIdCheck(String id) {
        return mapper.selectIdCheck(id);
    }

    public int selectEmailCheck(String email) {
        return mapper.selectEmailCheck(email);
    }

    public int selectPhoneCheck(String phone) {
        return mapper.selectPhoneCheck(phone);
    }

    public int selectIdCheckAtChange(int mNumber, String id) {
        return mapper.selectIdCheckAtChange(mNumber, id);
    }

    public int selectEmailCheckAtChange(int mNumber, String email) {
        return mapper.selectEmailCheckAtChange(mNumber, email);
    }

    public int selectPhoneCheckAtChange(int mNumber, String email) {
        return mapper.selectPhoneCheckAtChange(mNumber, email);
    }

    public Member selectMemberByEmail(String email) {
        return mapper.selectMemberByEmail(email);
    }

    public String selectPwById(String id) {
        return mapper.selectPwById(id);
    }

    // 관리자 페이지 조건부검색 회원 총 개수
    public int selectCount(String keyword) {
        return mapper.selectCount(keyword);
    }

    // 관리자 페이지 조건부검색 회원 리스트
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

    public void updateAdmin(DomainObj obj) {
        mapper.updateAdmin((Member) obj);
    }
}