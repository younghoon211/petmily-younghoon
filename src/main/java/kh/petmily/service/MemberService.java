package kh.petmily.service;

import kh.petmily.domain.member.Member;
import kh.petmily.domain.member.form.*;

import java.util.List;

public interface MemberService {

    void join(MemberJoinForm form);

    void create(AdminMemberCreateForm form);

    Member login(String id, String pw);

    boolean checkPwCorrect(int mNumber, String pw);

    boolean isPwEqualToConfirm(String pw, String confirmPw);

    String getMemberName(int mNumber);

    List<Member> selectAll();

    MemberPageForm getAdminListPage(int pageNo);

    Member change(Member member, MemberChangeForm form);

    AdminMemberModifyForm getModifyForm(int mNumber);

    void modify(AdminMemberModifyForm form);

    void withdraw(int mNumber);

    void delete(int mNumber);

    boolean checkDuplicatedId(String id);

    boolean checkDuplicatedEmail(String email);

    boolean checkDuplicatedPhone(String phone);

    boolean checkDuplicatedEmailMemberChange(String email, String id);

    boolean checkDuplicatedPhoneMemberChange(MemberChangeForm form);
}