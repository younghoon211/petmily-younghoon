package kh.petmily.service;

import kh.petmily.dao.ShelterDao;
import kh.petmily.domain.shelter.Shelter;
import kh.petmily.domain.shelter.form.ShelterListForm;
import kh.petmily.domain.shelter.form.ShelterModifyForm;
import kh.petmily.domain.shelter.form.ShelterPageForm;
import kh.petmily.domain.shelter.form.ShelterWriteForm;
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
    public void create(ShelterWriteForm form) {
        Shelter shelter = toWrite(form);
        shelterDao.insert(shelter);
    }

    // ===================== Read =====================
    // 보호소 리스트 페이지
    @Override
    public ShelterPageForm getListPage(int pageNo) {
        int total = shelterDao.selectCount();
        List<ShelterListForm> content = shelterDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size);

        return new ShelterPageForm(total, pageNo, size, content);
    }

    @Override
    public List<Shelter> getShelterList() {
        return shelterDao.selectAll();
    }

    @Override
    public List<Shelter> getShelterListNotSNumber0() {
        return shelterDao.selectAllNot0();
    }

    // ===================== Update =====================
    // 수정 폼
    @Override
    public ShelterModifyForm getModifyForm(int pk) {
        Shelter shelter = shelterDao.findByPk(pk);

        return toModifyForm(shelter);
    }

    // 수정하기
    @Override
    public void modify(ShelterModifyForm form) {
        Shelter shelter = toModify(form);
        shelterDao.update(shelter);
    }

    // ===================== Delete =====================
    // 삭제
    @Override
    public void delete(int pk) {
        shelterDao.delete(pk);
    }


    // ===================== CRUD 끝 =====================

    private Shelter toWrite(ShelterWriteForm form) {
        return new Shelter(
                form.getGroupName(),
                form.getLocation(),
                form.getPhone()
        );
    }

    private ShelterModifyForm toModifyForm(Shelter shelter) {
        return new ShelterModifyForm(
                shelter.getSNumber(),
                shelter.getGroupName(),
                shelter.getLocation(),
                shelter.getPhone()
        );
    }

    private Shelter toModify(ShelterModifyForm form) {
        return new Shelter(
                form.getSNumber(),
                form.getGroupName(),
                form.getLocation(),
                form.getPhone()
        );
    }
}
