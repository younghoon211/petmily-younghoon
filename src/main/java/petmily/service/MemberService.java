package petmily.service;

import petmily.domain.admin_form.MemberInsertForm;
import petmily.domain.admin_form.MemberUpdateForm;
import petmily.domain.member.Member;
import petmily.domain.member.form.MemberChangeForm;
import petmily.domain.member.form.MemberJoinForm;
import petmily.domain.member.form.MemberPageForm;

import java.util.List;

public interface MemberService {

    void join(MemberJoinForm form);

    void insert(MemberInsertForm form);

    Member login(String id, String pw);

    boolean checkPwCorrect(int mNumber, String pw);

    boolean isPwEqualToConfirm(String pw, String confirmPw);

    Member getMemberByPk(int pk);

    List<Member> getMemberList();

    MemberPageForm getAdminListPage(int pageNo, String keyword);

    Member change(MemberChangeForm form);

    MemberUpdateForm getUpdateForm(int mNumber);

    void updateAdmin(MemberUpdateForm form);

    void withdraw(int mNumber);

    void delete(int mNumber);

    boolean checkDuplicatedId(String id);

    boolean checkDuplicatedEmail(String email);

    boolean checkDuplicatedPhone(String phone);

    int checkDuplicatedEmailChangeInfo(int mNumber, String email);

    int checkDuplicatedPhoneChangeInfo(int mNumber, String phone);
}