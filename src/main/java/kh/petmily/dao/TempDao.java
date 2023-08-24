package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.member.Member;
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

    public int selectCount() {
        return mapper.selectCount();
    }

    public int selectCount(int mNumber) {
        return mapper.selectCountBymNumber(mNumber);
    }

    public List<AdminTempDetailForm> selectIndex(int start, int end) {
        List<AdminTempDetailForm> adminTempDetailForms = new ArrayList<>();
        List<TempPet> tempPets = mapper.selectIndex(start, end);

        addAdminTempDetailForms(adminTempDetailForms, tempPets);

        return adminTempDetailForms;
    }

    public List<MypageTempListForm> selectIndex(int start, int end, int mNumber) {
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

    public List<AdminTempDetailForm> selectIndex(int start, int end, String status) {
        List<AdminTempDetailForm> adminTempDetailForms = new ArrayList<>();
        List<TempPet> tempPets = mapper.selectIndexByStatus(start, end, status);

        addAdminTempDetailForms(adminTempDetailForms, tempPets);

        return adminTempDetailForms;
    }

    public void tempApprove(int pk) {
        mapper.tempApprove(pk);
    }

    public void tempRefuse(int pk) {
        mapper.tempRefuse(pk);
    }

    public void updateStatusToTemp() {
        mapper.updateStatusToTemp();
    }

    public List<Member> selectAllMember() {
        return memberMapper.selectAll();
    }

    public List<AbandonedAnimal> selectAllExcludeTemp() {
        return mapper.selectAllExcludeTemp();
    }

    public List<AbandonedAnimal> selectAllAbandonedAnimal() {
        return abandonedAnimalMapper.selectAll();
    }

    public void adminInsert(DomainObj obj) {
        mapper.adminInsert((TempPet) obj);
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
