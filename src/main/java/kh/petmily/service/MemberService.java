package kh.petmily.service;

import kh.petmily.domain.admin_form.MemberInsertForm;
import kh.petmily.domain.admin_form.MemberUpdateForm;
import kh.petmily.domain.member.Member;
import kh.petmily.domain.member.form.*;

import java.util.List;

public interface MemberService {

    void join(MemberJoinForm form);

    void insert(MemberInsertForm form);

    Member login(String id, String pw);

    boolean checkPwCorrect(int mNumber, String pw);

    boolean isPwEqualToConfirm(String pw, String confirmPw);

    String getMemberName(int mNumber);

    List<Member> getMemberList();

    MemberPageForm getAdminListPage(int pageNo, String keyword);

    Member change(Member member, MemberChangeForm form);

    MemberUpdateForm getUpdateForm(int mNumber);

    void update(MemberUpdateForm form);

    void withdraw(int mNumber);

    void delete(int mNumber);

    boolean checkDuplicatedId(String id);

    boolean checkDuplicatedEmail(String email);

    boolean checkDuplicatedPhone(String phone);

    boolean checkDuplicatedEmailChangeInfo(String email, String id);

    boolean checkDuplicatedPhoneChangeInfo(MemberChangeForm form);
}