package petmily.dao;

import petmily.domain.DomainObj;
import petmily.domain.abandoned_animal.AbandonedAnimal;
import petmily.domain.adopt.Adopt;
import petmily.domain.admin_form.AdoptListForm;
import petmily.domain.adopt.form.MypageAdoptListForm;
import petmily.mapper.AbandonedAnimalMapper;
import petmily.mapper.AdoptMapper;
import petmily.mapper.MemberMapper;
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

    // 회원 pk로 입양 상태 '완료' 여부 조회
    public int selectCountAdoptedStatus(int mNumber) {
        return mapper.selectCountAdoptedStatus(mNumber);
    }

    // ========================= 마이페이지 ========================
    // 입양 신청 내역(마이페이지) - 총 게시글 수 (페이징)
    public int selectCountBymNumber(int mNumber) {
        return mapper.selectCountBymNumber(mNumber);
    }

    // 입양 신청 내역(마이페이지) - 글 index
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

    // 총 게시글 수 (페이징)
    public int selectCount(String keyword) {
        return mapper.selectCount(keyword);
    }

    // 글 index
    public List<AdoptListForm> selectIndex(int start, int end, String keyword) {
        List<AdoptListForm> adoptListForms = new ArrayList<>();
        List<Adopt> adopts = mapper.selectIndex(start, end, keyword);

        addAdminAdoptDetailForms(adoptListForms, adopts);

        return adoptListForms;
    }

    // 입양 승인관리 및 승인, 거절 페이지 총 게시글 수 (페이징)
    public int selectCountByStatus(String keyword, String status) {
        return mapper.selectCountByStatus(keyword, status);
    }

    // 입양 승인관리 및 승인, 거절 페이지 게시글 index
    public List<AdoptListForm> selectIndexByStatus(int start, int end, String keyword, String status) {
        List<AdoptListForm> adoptListForms = new ArrayList<>();
        List<Adopt> adopts = mapper.selectIndexByStatus(start, end, keyword, status);

        addAdminAdoptDetailForms(adoptListForms, adopts);

        return adoptListForms;
    }

    // 입양 승인버튼
    public void adoptApprove(int pk) {
        mapper.adoptApprove(pk);
    }

    // 입양 거절버튼
    public void adoptRefuse(int pk) {
        mapper.adoptRefuse(pk);
    }

    // 기존 입양정보 delete
    public void deleteCompleteWhenUpdateAB(int abNumber) {
        mapper.deleteCompleteWhenUpdateAB(abNumber);
    }

    // 입양 상태 '처리중' delete
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

    private void addAdminAdoptDetailForms(List<AdoptListForm> adoptListForms, List<Adopt> adopts) {
        for (Adopt adopt : adopts) {
            AdoptListForm detailForm = new AdoptListForm(
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

            adoptListForms.add(detailForm);
        }
    }
}
