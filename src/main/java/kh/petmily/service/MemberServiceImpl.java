package kh.petmily.service;

import kh.petmily.dao.MemberDao;
import kh.petmily.domain.member.Member;
import kh.petmily.domain.member.form.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;
    private int size = 5;

    @Override
    public void join(JoinRequest joinReq) {
        Member member = toMember(joinReq);
        memberDao.insert(member);
    }

    @Override
    public Member login(String id, String pw) {
        Member member = memberDao.selectMemberById(id);

        if (!pw.equals(member.getPw())) {
            return null;
        }

        return member;
    }

    @Override
    public void withdraw(int mNumber) {
        memberDao.delete(mNumber);
    }

    @Override
    public boolean checkPwCorrect(int mNumber, String pw) {
        Member member = memberDao.findByPk(mNumber);

        return member.getPw().equals(pw);
    }

    @Override
    public boolean isPwEqualToConfirm(String pw, String confirmPw) {
        return pw != null && pw.equals(confirmPw);
    }

    @Override
    public Member modify(Member member, MemberChangeForm memberChangeForm) {
        Member mem = toMemberFromChange(member, memberChangeForm);

        memberDao.update(mem);

        log.info("Service - modify - member : {}", mem);

        return mem;
    }

    @Override
    public String findName(int mNumber) {
        return memberDao.selectName(mNumber);
    }

    @Override
    public List<Member> selectAll() {
        return memberDao.selectAll();
    }

    // 관리자 페이지
    @Override
    public MemberPageForm getMemberPage(int pageNo) {
        int total = memberDao.selectCount();
        List<MemberDetailForm> content = memberDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size);

        return new MemberPageForm(total, pageNo, size, content);
    }

    @Override
    public void delete(int mNumber) {
        memberDao.delete(mNumber);
    }

    @Override
    public MemberModifyForm getMemberModify(int mNumber) {
        Member member = memberDao.findByPk(mNumber);
        MemberModifyForm memberModifyForm = toMemberModify(member);

        return memberModifyForm;
    }

    @Override
    public void create(MemberCreateForm memberCreateForm) {
        Member member = toMember(memberCreateForm);

        memberDao.insert(member);
    }

    @Override
    public void modify(MemberModifyForm memberModifyForm) {
        Member member = toMember(memberModifyForm);

        memberDao.update(member);
    }

    @Override
    public boolean checkDuplicatedId(String id) {
        int idCount = memberDao.selectIdCheck(id);
        return idCount == 1;
    }

    @Override
    public boolean checkDuplicatedEmail(String email) {
        int emailCount = memberDao.selectEmailCheck(email);
        return emailCount == 1;
    }

    private Member toMember(MemberCreateForm memberCreateForm) {
        Member member = new Member(memberCreateForm.getMNumber(), memberCreateForm.getId(), memberCreateForm.getPw(), memberCreateForm.getName(), memberCreateForm.getBirth(), memberCreateForm.getGender(), memberCreateForm.getEmail(), memberCreateForm.getPhone(), memberCreateForm.getGrade());

        return member;
    }

    private Member toMember(MemberModifyForm memberModifyForm) {
        Member member = new Member(memberModifyForm.getMNumber(), memberModifyForm.getId(), memberModifyForm.getPw(), memberModifyForm.getName(), memberModifyForm.getBirth(), memberModifyForm.getGender(), memberModifyForm.getEmail(), memberModifyForm.getPhone(), memberModifyForm.getGrade());

        return member;
    }

    private MemberModifyForm toMemberModify(Member member) {
        return new MemberModifyForm(member.getMNumber(), member.getId(), member.getPw(), member.getName(), member.getBirth(), member.getGender(), member.getEmail(), member.getPhone(), member.getGrade());
    }

    private Member toMemberFromChange(Member member, MemberChangeForm memberChangeForm) {
        return new Member(member.getMNumber(), member.getId(), memberChangeForm.getPw(), memberChangeForm.getName(), member.getBirth(), member.getGender(), memberChangeForm.getEmail(), memberChangeForm.getPhone(), member.getGrade());
    }

    private Member toMember(JoinRequest joinReq) {
        String id = joinReq.getId();
        String pw = joinReq.getPw();
        String name = joinReq.getName();
        String birth = extractyyyymmdd(joinReq);
        String gender = joinReq.getGender();
        String email = joinReq.getEmail();
        String phone = extractDashPhoneNumber(joinReq);

        return new Member(id, pw, name, birth, gender, email, phone);
    }

    private String extractDashPhoneNumber(JoinRequest joinReq) {
        String phone = joinReq.getPhone();
        return phone.substring(0, 3) + "-" + phone.substring(3, 7) + "-" + phone.substring(7, 11);
    }

    private String extractyyyymmdd(JoinRequest joinReq) {
        String birth = joinReq.getBirth();
        return birth.substring(0, 10);
    }
}