package petmily.service;

import petmily.domain.admin_form.MemberInsertForm;
import petmily.domain.admin_form.MemberPageForm;
import petmily.domain.admin_form.MemberUpdateForm;
import petmily.domain.member.Member;
import petmily.domain.member.form.JoinForm;
import petmily.domain.member.form.MemberInfoChangeForm;
import petmily.domain.member.form.MemberPwChangeForm;
import petmily.domain.member.form.ResetPwForm;

import java.util.List;

public interface MemberService {

    void join(JoinForm form);

    void insert(MemberInsertForm form);

    Member login(String id, String pw);

    boolean checkPwCorrect(int mNumber, String pw);

    Member getMemberByPk(int pk);

    Member getMemberById(String id);

    String getPwById(String id);

    List<Member> getMemberList();

    MemberPageForm getAdminListPage(int pageNo, String keyword);

    Member changeInfo(MemberInfoChangeForm form);

    Member changePw(MemberPwChangeForm form);

    Member resetPw(ResetPwForm form);

    MemberUpdateForm getUpdateForm(int mNumber);

    void updateAdmin(MemberUpdateForm form);

    void withdraw(int mNumber);

    void delete(int mNumber);

    int checkDuplicatedId(String id);

    int checkDuplicatedEmail(String email);

    int checkDuplicatedPhone(String phone);

    int checkDuplicatedIdAtChange(int mNumber, String id);

    int checkDuplicatedEmailAtChange(int mNumber, String email);

    int checkDuplicatedPhoneAtChange(int mNumber, String phone);

    Member getMemberByEmail(String email);
}