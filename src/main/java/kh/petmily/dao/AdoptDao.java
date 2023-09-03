package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.adopt.form.AdminAdoptDetailForm;
import kh.petmily.domain.adopt.form.MypageAdoptListForm;
import kh.petmily.mapper.AbandonedAnimalMapper;
import kh.petmily.mapper.AdoptMapper;
import kh.petmily.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdoptDao implements BasicDao {

    private final AdoptMapper mapper;
    private final MemberMapper memberMapper;
    private final AbandonedAnimalMapper abandonedAnimalMapper;

    @Override
    public Adopt findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    // status '처리중' default
    @Override
    public void insert(DomainObj obj) {
        mapper.insert((Adopt) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((Adopt) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }

    // ========================= 마이페이지 ========================
    // 내가 쓴 게시글 - 총 게시글 수 조회
    public int selectCountBymNumber(int mNumber) {
        return mapper.selectCountBymNumber(mNumber);
    }

    // 내가 쓴 게시글 - 리스트 페이지 index
    public List<MypageAdoptListForm> selectIndexBymNumber(int start, int end, int mNumber) {
        List<MypageAdoptListForm> mypageAdoptListForms = new ArrayList<>();
        List<Adopt> adopts = mapper.selectIndexBymNumber(start, end, mNumber);

        for (Adopt adopt : adopts) {
            MypageAdoptListForm listForm = new MypageAdoptListForm(
                    adopt.getAdNumber(),
                    selectAnimalName(adopt),
                    adopt.getStatus()
            );

            mypageAdoptListForms.add(listForm);
        }

        return mypageAdoptListForms;
    }

    // ======================== 관리자 페이지 ==========================
    // 관리자 insert (status 선택 가능)
    public void adminInsert(DomainObj obj) {
        mapper.adminInsert((Adopt) obj);
    }

    // 총 게시글 수 조회
    public int selectCount() {
        return mapper.selectCount();
    }

    // 리스트 페이지 index
    public List<AdminAdoptDetailForm> selectIndex(int start, int end) {
        List<AdminAdoptDetailForm> adminAdoptDetailForms = new ArrayList<>();
        List<Adopt> adopts = mapper.selectIndex(start, end);

        addAdminAdoptDetailForms(adminAdoptDetailForms, adopts);

        return adminAdoptDetailForms;
    }

    // 입양 승인관리 리스트 페이지 index
    public List<AdminAdoptDetailForm> selectIndexByStatus(int start, int end, String status) {
        List<AdminAdoptDetailForm> adminAdoptDetailForms = new ArrayList<>();
        List<Adopt> adopts = mapper.selectIndexByStatus(start, end, status);

        addAdminAdoptDetailForms(adminAdoptDetailForms, adopts);

        return adminAdoptDetailForms;
    }

    // 입양 승인
    public void adoptApprove(int pk) {
        mapper.adoptApprove(pk);
    }

    // 입양 거절
    public void adoptRefuse(int pk) {
        mapper.adoptRefuse(pk);
    }

    // 수정 시 동일한 abNumber 중 상태 '완료' 삭제
    public void deleteCompleteWhenUpdateAB(int abNumber) {
        mapper.deleteCompleteWhenUpdateAB(abNumber);
    }

    // 수정 시 동일한 abNumber 중 상태 '처리중' 삭제
    public void deleteWaitingWhenUpdateAB(int abNumber) {
        mapper.deleteWaitingWhenUpdateAB(abNumber);
    }


    // =============== private 메소드 ===============

    private String selectMemberName(int mNumber) {
        return memberMapper.selectName(mNumber);
    }

    private String selectMemberId(int mNumber) {
        return memberMapper.selectMemberId(mNumber);
    }

    private AbandonedAnimal selectAbAnimalByPk(int abNumber) {
        return abandonedAnimalMapper.selectByPk(abNumber);
    }

    private String selectAnimalName(Adopt adopt) {
        return selectAbAnimalByPk(adopt.getAbNumber()).getName();
    }

    private void addAdminAdoptDetailForms(List<AdminAdoptDetailForm> adminAdoptDetailForms, List<Adopt> adopts) {
        for (Adopt adopt : adopts) {
            AdminAdoptDetailForm detailForm = new AdminAdoptDetailForm(
                    adopt.getAdNumber(),
                    adopt.getMNumber(),
                    adopt.getAbNumber(),
                    adopt.getResidence(),
                    adopt.getMaritalStatus(),
                    adopt.getJob(),
                    adopt.getStatus(),
                    selectAnimalName(adopt),
                    selectMemberName(adopt.getMNumber()),
                    selectMemberId(adopt.getMNumber())
            );

            adminAdoptDetailForms.add(detailForm);
        }
    }
}
