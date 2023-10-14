package petmily.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petmily.dao.MemberDao;
import petmily.domain.admin_form.MemberDetailForm;
import petmily.domain.admin_form.MemberInsertForm;
import petmily.domain.admin_form.MemberPageForm;
import petmily.domain.admin_form.MemberUpdateForm;
import petmily.domain.member.Member;
import petmily.domain.member.form.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;
    private int size = 10;

    // ===================== Create =====================
    // 회원가입
    @Override
    public void join(JoinForm form) {
        Member member = toJoin(form);
        memberDao.insert(member);
    }

    // 회원정보 추가 (관리자)
    @Override
    public void insert(MemberInsertForm form) {
        Member member = toInsert(form);
        memberDao.insert(member);
    }

    // ===================== Read =====================
    // 로그인
    @Override
    public Member login(String id, String pw) {
        Member member = memberDao.selectMemberById(id);

        if (member == null || !pw.equals(member.getPw())) {
            return null;
        }

        return member;
    }

    // 비밀번호 체크
    @Override
    public boolean checkPwCorrect(int pk, String pw) {
        Member member = memberDao.findByPk(pk);

        return member.getPw().equals(pw);
    }

    // pk로 회원정보 조회
    @Override
    public Member getMemberByPk(int pk) {
        return memberDao.findByPk(pk);
    }

    // 아이디로 멤버 정보 조회
    @Override
    public Member getMemberById(String id) {
        return memberDao.selectMemberById(id);
    }

    // 아이디로 비번 조회
    @Override
    public String getPwById(String id) {
        return memberDao.selectPwById(id);
    }

    // 회원 리스트 조회 (관리자)
    @Override
    public List<Member> getMemberList() {
        return memberDao.selectAll();
    }

    // 회원 리스트 페이지 (관리자)
    @Override
    public MemberPageForm getAdminListPage(int pageNo, String keyword) {
        int total = memberDao.selectCount(keyword);
        List<MemberDetailForm> content = memberDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, keyword);

        return new MemberPageForm(total, pageNo, size, content);
    }

    // ===================== Update =====================
    // 회원정보 변경
    @Override
    public Member changeInfo(MemberInfoChangeForm form) {
        Member updateMember = toChangeInfo(form);
        memberDao.update(updateMember);

        return updateMember;
    }

    // 비밀번호 변경
    @Override
    public Member changePw(MemberPwChangeForm form) {
        Member updatePw = toChangePw(form);
        memberDao.updatePw(updatePw);

        return updatePw;
    }

    // 비밀번호 재설정
    @Override
    public Member resetPw(ResetPwForm form) {
        Member resetPw = toResetPw(form);
        memberDao.resetPw(resetPw);

        return resetPw;
    }

    // 수정 폼 (관리자)
    @Override
    public MemberUpdateForm getUpdateForm(int pk) {
        Member member = memberDao.findByPk(pk);

        return toUpdateForm(member);
    }

    // 수정 (관리자)
    @Override
    public void updateAdmin(MemberUpdateForm form) {
        Member member = toUpdate(form);
        memberDao.updateAdmin(member);
    }

    // ===================== Delete =====================
    // 회원 탈퇴
    @Override
    public void withdraw(int pk) {
        memberDao.delete(pk);
    }

    // 삭제 (관리자)
    @Override
    public void delete(int pk) {
        memberDao.delete(pk);
    }

    // ===================== 검증 =====================
    // 회원가입 검증 (아이디)
    @Override
    public int checkDuplicatedId(String id) {
        return memberDao.selectIdCheck(id);
    }

    // 회원가입 검증 (이메일)
    @Override
    public int checkDuplicatedEmail(String email) {
        return memberDao.selectEmailCheck(email);
    }

    // 회원가입 검증 (연락처)
    @Override
    public int checkDuplicatedPhone(String phone) {
        return memberDao.selectPhoneCheck(phone);
    }

    // 회원정보 변경 검증 (이메일)
    @Override
    public int checkDuplicatedEmailChangeInfo(int mNumber, String email) {
        return memberDao.selectEmailCheckChangeInfo(mNumber, email);
    }

    // 회원정보 변경 검증 (연락처)
    @Override
    public int checkDuplicatedPhoneChangeInfo(int mNumber, String phone) {
        return memberDao.selectPhoneCheckChangeInfo(mNumber, phone);
    }

    // 아이디 찾기 검증 (이메일)
    @Override
    public Member getMemberByEmail(String email) {
        return memberDao.selectMemberByEmail(email);
    }


    // ===================== CRUD / 검증 끝 =====================


    private Member toJoin(JoinForm form) {
        return new Member(
                form.getId(),
                form.getPw(),
                form.getName(),
                form.getBirth().substring(0, 10),
                form.getGender(),
                form.getEmail(),
                form.getPhone()
        );
    }

    private Member toInsert(MemberInsertForm form) {
        return new Member(
                form.getId(),
                form.getPw(),
                form.getName(),
                form.getBirth().substring(0, 10),
                form.getGender(),
                form.getEmail(),
                form.getPhone()
        );
    }

    private Member toChangeInfo(MemberInfoChangeForm form) {
        return new Member(
                form.getMNumber(),
                form.getName(),
                form.getEmail(),
                form.getPhone()
        );
    }

    private Member toChangePw(MemberPwChangeForm form) {
        return new Member(
                form.getMNumber(),
                form.getNewPw()
        );
    }

    private Member toResetPw(ResetPwForm form) {
        return new Member(
                form.getId(),
                form.getPw()
        );
    }

    private MemberUpdateForm toUpdateForm(Member member) {
        return new MemberUpdateForm(
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
    }

    private Member toUpdate(MemberUpdateForm form) {
        return new Member(
                form.getMNumber(),
                form.getPw(),
                form.getName(),
                form.getBirth(),
                form.getGender(),
                form.getEmail(),
                form.getPhone()
        );
    }
}