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
    private int size = 10;

    // ===================== Create =====================
    // 회원가입
    @Override
    public void join(MemberJoinForm form) {
        Member member = toJoin(form);
        memberDao.insert(member);
    }

    // 회원정보 추가 (관리자)
    @Override
    public void create(AdminMemberCreateForm form) {
        Member member = toCreate(form);
        memberDao.insert(member);
    }

    // ===================== Read =====================
    // 로그인
    @Override
    public Member login(String id, String pw) {
        Member member = memberDao.selectMemberById(id);

        if (!pw.equals(member.getPw())) {
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

    // 비밀번호, 확인 체크
    @Override
    public boolean isPwEqualToConfirm(String pw, String confirmPw) {
        return pw != null && pw.equals(confirmPw);
    }

    // 회원 이름
    @Override
    public String getMemberName(int pk) {
        return memberDao.selectName(pk);
    }

    // 모든 회원정보 조회 (관리자)
    @Override
    public List<Member> getMemberList() {
        return memberDao.selectAll();
    }

    // 회원 리스트 페이지 (관리자)
    @Override
    public MemberPageForm getAdminListPage(int pageNo) {
        int total = memberDao.selectCount();
        List<MemberDetailForm> content = memberDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size);

        return new MemberPageForm(total, pageNo, size, content);
    }

    // ===================== Update =====================
    // 수정
    @Override
    public Member change(Member member, MemberChangeForm form) {
        Member updateMember = toChange(member, form);
        memberDao.update(updateMember);

        return updateMember;
    }

    // 수정 폼 (관리자)
    @Override
    public AdminMemberModifyForm getModifyForm(int pk) {
        Member member = memberDao.findByPk(pk);

        return toModifyForm(member);
    }

    // 수정 (관리자)
    @Override
    public void modify(AdminMemberModifyForm form) {
        Member member = toModify(form);
        memberDao.update(member);
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
    public boolean checkDuplicatedId(String id) {
        int idCount = memberDao.selectIdCheck(id);
        
        return idCount == 1;
    }

    // 회원가입 검증 (이메일)
    @Override
    public boolean checkDuplicatedEmail(String email) {
        int emailCount = memberDao.selectEmailCheck(email);
        
        return emailCount == 1;
    }

    // 회원가입 검증 (연락처)
    @Override
    public boolean checkDuplicatedPhone(String phone) {
        int phoneCount = memberDao.selectPhoneCheck(phone);
        
        return phoneCount == 1;
    }

    // 회원정보 변경 검증 (이메일)
    @Override
    public boolean checkDuplicatedEmailChangeInfo(String email, String id) {
        int emailCount = memberDao.selectEmailCheckChangeInfo(email, id);

        return emailCount == 0;
    }

    // 회원정보 변경 검증 (연락처)
    @Override
    public boolean checkDuplicatedPhoneChangeInfo(MemberChangeForm form) {
        int phoneCount = memberDao.selectPhoneCheckChangeInfo(form.getPhone(), form.getId());
        
        return phoneCount == 1;
    }


    // ===================== CRUD / 검증 끝 =====================


    private Member toJoin(MemberJoinForm form) {
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

    private Member toCreate(AdminMemberCreateForm form) {
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

    private Member toChange(Member member, MemberChangeForm form) {
        return new Member(
                member.getMNumber(),
                form.getPw(),
                form.getName(),
                member.getBirth(),
                member.getGender(),
                form.getEmail(),
                form.getPhone()
        );
    }

    private AdminMemberModifyForm toModifyForm(Member member) {
        return new AdminMemberModifyForm(
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

    private Member toModify(AdminMemberModifyForm form) {
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