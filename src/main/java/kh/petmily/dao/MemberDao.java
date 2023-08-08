package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.member.Member;
import kh.petmily.domain.member.form.MemberDetailForm;
import kh.petmily.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDao implements BasicDao {

    private final MemberMapper mapper;

    // =======BasicDao 메소드=======
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
    // =======BasicDao 메소드=======

    public Member selectMemberById(String id) {
        return mapper.selectMemberById(id);
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    public List<Member> selectAll() {
        return mapper.selectAll();
    }

    public int selectCount() {
        return mapper.selectCount();
    }

    public List<MemberDetailForm> selectIndex(int start, int end) {
        List<MemberDetailForm> memberDetailForms = new ArrayList<>();
        List<Member> members = mapper.selectIndex(start, end);

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

    public int selectIdCheck(String id) {
        return mapper.selectIdCheck(id);
    }

    public int selectEmailCheck(String email) {
        return mapper.selectEmailCheck(email);
    }

    public int selectPhoneCheck(String phone) {
        return mapper.selectPhoneCheck(phone);
    }

    public int selectEmailCheckMemberChange(String email, String id) {
        return mapper.selectEmailCheckMemberChange(email, id);
    }

    public int selectPhoneCheckMemberChange(String email, String id) {
        return mapper.selectPhoneCheckMemberChange(email, id);
    }
}