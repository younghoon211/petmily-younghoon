package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.adopt.form.AdminAdoptDetailForm;
import kh.petmily.domain.adopt.form.MypageAdoptListForm;
import kh.petmily.domain.member.Member;
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
    private final AbandonedAnimalMapper abandonedAnimalMapper;
    private final MemberMapper memberMapper;

    @Override
    public Adopt findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

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

    public int selectCount() {
        return mapper.selectCount();
    }

    public int selectCount(int mNumber) {
        return mapper.selectCountBymNumber(mNumber);
    }

    public List<AdminAdoptDetailForm> selectIndex(int start, int end) {
        List<AdminAdoptDetailForm> result = new ArrayList<>();
        List<Adopt> list = mapper.selectIndex(start, end);

        for (Adopt l : list) {
            AdminAdoptDetailForm li = new AdminAdoptDetailForm(
                    l.getAdNumber(),
                    l.getMNumber(),
                    l.getAbNumber(),
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

    public List<MypageAdoptListForm> selectIndex(int start, int end, int mNumber) {
        List<MypageAdoptListForm> result = new ArrayList<>();
        List<Adopt> list = mapper.selectIndexBymNumber(start, end, mNumber);

        for (Adopt a : list) {
            MypageAdoptListForm li = new MypageAdoptListForm(
                    a.getAdNumber(),
                    getAbNameByAbNumber(a.getAbNumber()),
                    a.getStatus()
            );

            result.add(li);
        }

        return result;
    }

    public List<AdminAdoptDetailForm> selectIndex(int start, int end, String status) {
        List<AdminAdoptDetailForm> result = new ArrayList<>();
        List<Adopt> list = mapper.selectIndexByStatus(start, end, status);

        for (Adopt l : list) {
            AdminAdoptDetailForm li = new AdminAdoptDetailForm(
                    l.getAdNumber(),
                    l.getMNumber(),
                    l.getAbNumber(),
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

    public void adoptApprove(int pk) {
        mapper.adoptApprove(pk);
    }

    public void adoptRefuse(int pk) {
        mapper.adoptRefuse(pk);
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

    public void updateStatusToAdopt() {
        mapper.updateStatusToAdopt();
    }

    public List<Member> selectAllMember() {
        return memberMapper.selectAll();
    }

    public List<AbandonedAnimal> selectAllExcludeAdoptStatus() {
        return mapper.selectAllExcludeAdoptStatus();
    }

    public List<AbandonedAnimal> selectAllAbandonedAnimal() {
        return abandonedAnimalMapper.selectAll();
    }

    public void adminInsert(DomainObj obj) {
        mapper.adminInsert((Adopt) obj);
    }
}
