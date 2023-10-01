package petmily.dao;

import petmily.domain.DomainObj;
import petmily.domain.member.Member;
import petmily.domain.member.form.MemberDetailForm;
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

    public int selectEmailCheckChangeInfo(int mNumber, String email) {
        return mapper.selectEmailCheckChangeInfo(mNumber, email);
    }

    public int selectPhoneCheckChangeInfo(int mNumber, String email) {
        return mapper.selectPhoneCheckChangeInfo(mNumber, email);
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