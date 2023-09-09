package kh.petmily.service;

import kh.petmily.dao.ShelterDao;
import kh.petmily.domain.shelter.Shelter;
import kh.petmily.domain.admin_form.ShelterListForm;
import kh.petmily.domain.admin_form.ShelterUpdateForm;
import kh.petmily.domain.admin_form.ShelterPageForm;
import kh.petmily.domain.admin_form.ShelterInsertForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ShelterServiceImpl implements ShelterService {

    private final ShelterDao shelterDao;
    private int size = 10;

    // ===================== Create =====================
    // 보호소 추가
    @Override
    public void insert(ShelterInsertForm form) {
        Shelter shelter = toInsert(form);
        shelterDao.insert(shelter);
    }

    // ===================== Read =====================
    // 보호소 리스트 페이지
    @Override
    public ShelterPageForm getListPage(int pageNo, String keyword) {
        int total = shelterDao.selectCount(keyword);
        List<ShelterListForm> content = shelterDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, keyword);

        return new ShelterPageForm(total, pageNo, size, content);
    }

    @Override
    public List<Shelter> getShelterListNotSNumber0() {
        return shelterDao.selectAllNot0();
    }

    // ===================== Update =====================
    // 수정 폼
    @Override
    public ShelterUpdateForm getUpdateForm(int pk) {
        Shelter shelter = shelterDao.findByPk(pk);

        return toUpdateForm(shelter);
    }

    // 수정하기
    @Override
    public void update(ShelterUpdateForm form) {
        Shelter shelter = toUpdate(form);
        shelterDao.update(shelter);
    }

    // ===================== Delete =====================
    // 삭제
    @Override
    public void delete(int pk) {
        shelterDao.delete(pk);
    }


    // ===================== CRUD 끝 =====================

    private Shelter toInsert(ShelterInsertForm form) {
        return new Shelter(
                form.getGroupName(),
                form.getLocation(),
                form.getPhone()
        );
    }

    private ShelterUpdateForm toUpdateForm(Shelter shelter) {
        return new ShelterUpdateForm(
                shelter.getSNumber(),
                shelter.getGroupName(),
                shelter.getLocation(),
                shelter.getPhone()
        );
    }

    private Shelter toUpdate(ShelterUpdateForm form) {
        return new Shelter(
                form.getSNumber(),
                form.getGroupName(),
                form.getLocation(),
                form.getPhone()
        );
    }
}
