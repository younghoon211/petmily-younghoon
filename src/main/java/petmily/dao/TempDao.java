package petmily.dao;

import petmily.domain.DomainObj;
import petmily.domain.abandoned_animal.AbandonedAnimal;
import petmily.domain.temp.TempPet;
import petmily.domain.admin_form.TempListForm;
import petmily.domain.temp.form.MypageTempListForm;
import petmily.mapper.AbandonedAnimalMapper;
import petmily.mapper.MemberMapper;
import petmily.mapper.TempMapper;
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

    // 임보 신청 내역(마이페이지) - 총 게시글 수 (페이징)
    public int selectCountBymNumber(int mNumber) {
        return mapper.selectCountBymNumber(mNumber);
    }

    // 임보 신청 내역(마이페이지) - 글 index
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

    // 총 게시글 수 조회 (페이징)
    public int selectCount(String keyword) {
        return mapper.selectCount(keyword);
    }

    // 리스트 페이지 index
    public List<TempListForm> selectIndex(int start, int end, String keyword) {
        List<TempListForm> tempListForms = new ArrayList<>();
        List<TempPet> tempPets = mapper.selectIndex(start, end, keyword);

        addAdminTempDetailForms(tempListForms, tempPets);

        return tempListForms;
    }

    // 임보 승인관리 및 승인, 거절 페이지 총 게시글 수 조회 (페이징)
    public int selectCountByStatus(String keyword, String status) {
        return mapper.selectCountByStatus(keyword, status);
    }

    // 임보 승인관리 및 승인, 거절 페이지 글 index
    public List<TempListForm> selectIndexByStatus(int start, int end, String keyword, String status) {
        List<TempListForm> tempListForms = new ArrayList<>();
        List<TempPet> tempPets = mapper.selectIndexByStatus(start, end, keyword, status);

        addAdminTempDetailForms(tempListForms, tempPets);

        return tempListForms;
    }

    // 임시보호 승인버튼
    public void tempApprove(int pk) {
        mapper.tempApprove(pk);
    }

    // 임시보호 거절버튼
    public void tempRefuse(int pk) {
        mapper.tempRefuse(pk);
    }

    // 기존 임보 정보 delete
    public void deleteCompleteWhenUpdateAB(int abNumber) {
        mapper.deleteCompleteWhenUpdateAB(abNumber);
    }

    // 임보 상태 '처리중' delete
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

    private void addAdminTempDetailForms(List<TempListForm> tempListForms, List<TempPet> tempPets) {
        for (TempPet tempPet : tempPets) {
            TempListForm detailForm = new TempListForm(
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

            tempListForms.add(detailForm);
        }
    }
}
