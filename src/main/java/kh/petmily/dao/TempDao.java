package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.temp.TempPet;
import kh.petmily.domain.temp.form.AdminTempDetailForm;
import kh.petmily.domain.temp.form.MypageTempListForm;
import kh.petmily.mapper.AbandonedAnimalMapper;
import kh.petmily.mapper.MemberMapper;
import kh.petmily.mapper.TempMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TempDao implements BasicDao {

    private final TempMapper mapper;
    private final MemberMapper memberMapper;
    private final AbandonedAnimalMapper abandonedAnimalMapper;

    @Override
    public TempPet findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((TempPet) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((TempPet) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }


    // ================ 회원 페이지 ================

    public int selectCountBymNumber(int mNumber) {
        return mapper.selectCountBymNumber(mNumber);
    }

    public List<MypageTempListForm> selectIndexBymNumber(int start, int end, int mNumber) {
        List<MypageTempListForm> mypageTempListForms = new ArrayList<>();
        List<TempPet> tempPets = mapper.selectIndexBymNumber(start, end, mNumber);

        for (TempPet tempPet : tempPets) {
            MypageTempListForm listForm = new MypageTempListForm(
                    tempPet.getTNumber(),
                    selectAnimalName(tempPet),
                    tempPet.getStatus()
            );

            mypageTempListForms.add(listForm);
        }

        return mypageTempListForms;
    }


    // ================ 관리자 페이지 ================

    // 관리자 insert (status 선택 가능)
    public void adminInsert(DomainObj obj) {
        mapper.adminInsert((TempPet) obj);
    }

    // 총 게시글 수 조회
    public int selectCount() {
        return mapper.selectCount();
    }

    // 리스트 페이지 index
    public List<AdminTempDetailForm> selectIndex(int start, int end) {
        List<AdminTempDetailForm> adminTempDetailForms = new ArrayList<>();
        List<TempPet> tempPets = mapper.selectIndex(start, end);

        addAdminTempDetailForms(adminTempDetailForms, tempPets);

        return adminTempDetailForms;
    }

    // 임시보호 승인관리 리스트 페이지 index
    public List<AdminTempDetailForm> selectIndexByStatus(int start, int end, String status) {
        List<AdminTempDetailForm> adminTempDetailForms = new ArrayList<>();
        List<TempPet> tempPets = mapper.selectIndexByStatus(start, end, status);

        addAdminTempDetailForms(adminTempDetailForms, tempPets);

        return adminTempDetailForms;
    }

    // 임시보호 승인
    public void tempApprove(int pk) {
        mapper.tempApprove(pk);
    }

    // 임시보호 거절
    public void tempRefuse(int pk) {
        mapper.tempRefuse(pk);
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

    private AbandonedAnimal selectAbAnimalByPk(int abNumber) {
        return abandonedAnimalMapper.selectByPk(abNumber);
    }

    private String selectAnimalName(TempPet tempPet) {
        return selectAbAnimalByPk(tempPet.getAbNumber()).getName();
    }

    private String selectMemberId(int mNumber) {
        return memberMapper.selectMemberId(mNumber);
    }

    private String selectMemberName(int mNumber) {
        return memberMapper.selectName(mNumber);
    }

    private void addAdminTempDetailForms(List<AdminTempDetailForm> adminTempDetailForms, List<TempPet> tempPets) {
        for (TempPet tempPet : tempPets) {
            AdminTempDetailForm detailForm = new AdminTempDetailForm(
                    tempPet.getTNumber(),
                    tempPet.getAbNumber(),
                    tempPet.getMNumber(),
                    tempPet.getTempDate(),
                    tempPet.getTempPeriod(),
                    tempPet.getResidence(),
                    tempPet.getMaritalStatus(),
                    tempPet.getJob(),
                    tempPet.getStatus(),
                    selectAnimalName(tempPet),
                    selectMemberName(tempPet.getMNumber()),
                    selectMemberId(tempPet.getMNumber())
            );

            adminTempDetailForms.add(detailForm);
        }
    }
}
