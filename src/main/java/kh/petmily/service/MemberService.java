package kh.petmily.service;

import kh.petmily.domain.member.Member;
import kh.petmily.domain.member.form.*;

import java.util.List;

public interface MemberService {

    void join(JoinRequest joinReq);

    Member login(String id, String pw);

    void withdraw(int mNumber);

    boolean checkPwCorrect(int mNumber, String pw);

    Member modify(Member member, MemberChangeForm memberChangeForm);

    String findName(int mNumber);

    List<Member> selectAll();

    boolean isPwEqualToConfirm(String pw, String confirmPw);

    MemberPageForm getMemberPage(int pageNo);

    MemberModifyForm getMemberModify(int mNumber);

    void delete(int mNumber);

    void create(MemberCreateForm memberCreateForm);

    void modify(MemberModifyForm memberModifyForm);

    boolean checkDuplicatedId(String id);

    boolean checkDuplicatedEmail(String email);

    boolean checkDuplicatedPhone(String phone);

    boolean checkDuplicatedPhoneMemberChange(MemberChangeForm memberChangeForm);

    boolean checkDuplicatedEmailMemberChange(String email, String id);
}