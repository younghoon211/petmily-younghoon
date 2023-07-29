package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.member.Member;
import kh.petmily.domain.temp.TempPet;
import kh.petmily.domain.temp.form.MypageTempListForm;
import kh.petmily.domain.temp.form.AdminTempDetailForm;
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
    private final AbandonedAnimalMapper abandonedAnimalMapper;
    private final MemberMapper memberMapper;

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
        List<AdminTempDetailForm> result = new ArrayList<>();
        List<TempPet> list = mapper.selectIndex(start, end);

        for (TempPet l : list) {
            AdminTempDetailForm li = new AdminTempDetailForm(
                    l.getTNumber(),
                    l.getAbNumber(),
                    l.getMNumber(),
                    l.getTempDate(),
                    l.getTempPeriod(),
                    l.getResidence(),
                    l.getMaritalStatus(),
                    l.getJob(),
                    l.getStatus(),
                    selectAnimalName(l.getAbNumber()),
                    selectMemberName(l.getMNumber()),
                    selectMemberId(l.getMNumber())
            );

            result.add(li);
        }

        return result;
    }

    public List<MypageTempListForm> selectIndex(int start, int end, int mNumber) {
        List<MypageTempListForm> result = new ArrayList<>();
        List<TempPet> list = mapper.selectIndexBymNumber(start, end, mNumber);

        for (TempPet t : list) {
            MypageTempListForm li = new MypageTempListForm(
                    t.getTNumber(), getAbNameByAbNumber(t.getAbNumber()), t.getStatus());

            result.add(li);
        }

        return result;
    }

    public List<AdminTempDetailForm> selectIndex(int start, int end, String status) {
        List<AdminTempDetailForm> result = new ArrayList<>();
        List<TempPet> list = mapper.selectIndexByStatus(start, end, status);

        for (TempPet l : list) {
            AdminTempDetailForm li = new AdminTempDetailForm(
                    l.getTNumber(),
                    l.getMNumber(),
                    l.getAbNumber(),
                    l.getTempDate(),
                    l.getTempPeriod(),
                    l.getResidence(),
                    l.getMaritalStatus(),
                    l.getJob(),
                    l.getStatus(),
                    selectAnimalName(l.getAbNumber()),
                    selectMemberName(l.getMNumber()),
                    selectMemberId(l.getMNumber())
            );

            result.add(li);
        }

        return result;
    }

    public void tempApprove(int pk) {
        mapper.tempApprove(pk);
    }

    public void tempRefuse(int pk) {
        mapper.tempRefuse(pk);
    }

    private String getAbNameByAbNumber(int abNumber) {
        return abandonedAnimalMapper.selectName(abNumber);
    }

    public String selectAnimalName(int abNumber) {
        return abandonedAnimalMapper.selectName(abNumber);
    }

    public String selectMemberName(int mNumber) {
        return memberMapper.selectName(mNumber);
    }

    public String selectMemberId(int mNumber) {
        return memberMapper.selectMemberId(mNumber);
    }

    public void updateStatusToTemp() {
        mapper.updateStatusToTemp();
    }

    public List<Member> selectAllMember() {
        return memberMapper.selectAll();
    }

    public List<AbandonedAnimal> selectAllExcludeTempStatus() {
        return mapper.selectAllExcludeTempStatus();
    }

    public List<AbandonedAnimal> selectAllAbandonedAnimal() {
        return abandonedAnimalMapper.selectAll();
    }

    public void adminInsert(DomainObj obj) {
        mapper.adminInsert((TempPet) obj);
    }
}
