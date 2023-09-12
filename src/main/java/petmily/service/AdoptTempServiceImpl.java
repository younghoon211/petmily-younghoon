package petmily.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petmily.dao.AbandonedAnimalDao;
import petmily.dao.AdoptDao;
import petmily.dao.TempDao;
import petmily.domain.abandoned_animal.AbandonedAnimal;
import petmily.domain.abandoned_animal.form.AdoptTempSubmitForm;
import petmily.domain.admin_form.*;
import petmily.domain.adopt.Adopt;
import petmily.domain.adopt.form.MypageAdoptListForm;
import petmily.domain.adopt.form.MypageAdoptPageForm;
import petmily.domain.temp.TempPet;
import petmily.domain.temp.form.MypageTempListForm;
import petmily.domain.temp.form.MypageTempPageForm;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdoptTempServiceImpl implements AdoptTempService {

    private final AdoptDao adoptDao;
    private final TempDao tempDao;
    private final AbandonedAnimalDao abandonedAnimalDao;
    private int size = 10;

    // ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 회원 페이지(입양) ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
    // 입양 신청
    @Override
    public void adopt(AdoptTempSubmitForm form) {
        Adopt adopt = toAdopt(form);
        adoptDao.insert(adopt);
    }

    // 입양 신청 내역 (마이페이지)
    @Override
    public MypageAdoptPageForm getMypageAdopt(int pageNo, int mNumber, String type) {
        int total = adoptDao.selectCountBymNumber(mNumber);
        List<MypageAdoptListForm> content = adoptDao.selectIndexBymNumber((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber);

        return new MypageAdoptPageForm(total, pageNo, size, content);
    }

    // mNumber로 입양 조회
    @Override
    public Adopt getAdoptBymNumber(int mNumber) {
        return adoptDao.selectAllBymNumber(mNumber);
    }

    // ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 회원 페이지(임시보호) ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
    // 임시보호 신청
    @Override
    public void temp(AdoptTempSubmitForm form) {
        TempPet tempPet = toTempPet(form);
        tempDao.insert(tempPet);
    }

    // 임시보호 신청 내역 (마이페이지)
    @Override
    public MypageTempPageForm getMypageTemp(int pageNo, int mNumber, String type) {
        int total = tempDao.selectCountBymNumber(mNumber);
        List<MypageTempListForm> content = tempDao.selectIndexBymNumber((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber);

        return new MypageTempPageForm(total, pageNo, size, content);
    }


    // ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 관리자 페이지(입양) ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

    // ===================== Create =====================
    // 입양 글쓰기
    @Override
    public void adoptInsert(AdoptForm form) {
        Adopt adopt = toInsert(form);

        adoptDao.adminInsert(adopt);
        abandonedAnimalDao.updateToAdopt();
    }

    // ===================== Read =====================

    // 입양 관리 리스트 페이지
    @Override
    public AdoptPageForm getAdminAdoptListPage(int pageNo, String keyword) {
        int total = adoptDao.selectCount(keyword);
        List<AdoptListForm> content = adoptDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, keyword);

        return new AdoptPageForm(total, pageNo, size, content);
    }

    // 입양 승인관리 및 승인, 거절된 리스트 페이지
    @Override
    public AdoptPageForm getAdminAdoptWaitPage(int pageNo, String keyword, String status) {
        int total = adoptDao.selectCountByStatus(keyword, status);
        List<AdoptListForm> content = adoptDao.selectIndexByStatus((pageNo - 1) * size + 1, (pageNo - 1) * size + size, keyword, status);

        return new AdoptPageForm(total, pageNo, size, content);
    }

    // 입양 조회
    @Override
    public Adopt getAdoptByPk(int pk) {
        return adoptDao.findByPk(pk);
    }

    // 입양 '처리중' 상태 유기동물 리스트
    @Override
    public List<AbandonedAnimal> getAnimalListAdoptWait() {
        return abandonedAnimalDao.selectAllAdoptWait();
    }

    // '입양' 상태 유기동물 리스트
    @Override
    public List<AbandonedAnimal> getAnimalListAdoptComplete() {
        return abandonedAnimalDao.selectAllAdoptComplete();
    }


    // ===================== Update =====================
    // 입양 업데이트
    @Override
    public void adminAdoptUpdate(AdoptForm form) {
        Adopt adopt = toModify(form);
        adoptDao.update(adopt);

        abandonedAnimalDao.updateToAdopt();
        abandonedAnimalDao.updateToProtectInAdopt();
    }

    // 입양 승인 버튼
    @Override
    public void adoptApproveButton(int pk) {
        adoptDao.adoptApprove(pk);
    }

    // 입양 거절 버튼
    @Override
    public void adoptRefuseButton(int pk) {
        adoptDao.adoptRefuse(pk);
    }

    // ===================== Delete =====================
    // 입양 삭제
    @Override
    public void deleteAdopt(int pk) {
        abandonedAnimalDao.updateToProtectForDeleteInAdopt(pk);
        adoptDao.delete(pk);
    }

    // ===================== CRUD 끝 =====================


    private Adopt toAdopt(AdoptTempSubmitForm form) {
        return new Adopt(
                form.getMNumber(),
                form.getAbNumber(),
                form.getResidence(),
                form.getMaritalStatus(),
                form.getJob()
        );
    }

    private Adopt toInsert(AdoptForm form) {
        return new Adopt(
                form.getMNumber(),
                form.getAbNumber(),
                form.getResidence(),
                form.getMaritalStatus(),
                form.getJob(),
                form.getStatus()
        );
    }

    private Adopt toModify(AdoptForm form) {
        return new Adopt(
                form.getAdNumber(),
                form.getMNumber(),
                form.getAbNumber(),
                form.getResidence(),
                form.getMaritalStatus(),
                form.getJob(),
                form.getStatus()
        );
    }


    // ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 관리자 페이지(임시보호) ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

    // ===================== Create =====================
    // 임시보호 글쓰기
    @Override
    public void tempInsert(TempForm form) {
        TempPet tempPet = adminToTemp(form);

        tempDao.adminInsert(tempPet);
        abandonedAnimalDao.updateToTemp();
    }

    // ===================== Read =====================
    // 임보 관리 리스트 페이지
    @Override
    public TempPageForm getAdminTempListPage(int pageNo, String keyword) {
        int total = tempDao.selectCount(keyword);
        List<TempListForm> content = tempDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, keyword);

        return new TempPageForm(total, pageNo, size, content);
    }

    // 임보 승인 관리 페이지
    @Override
    public TempPageForm getAdminTempWaitPage(int pageNo, String keyword, String status) {
        int total = tempDao.selectCountByStatus(keyword, status);
        List<TempListForm> content = tempDao.selectIndexByStatus((pageNo - 1) * size + 1, (pageNo - 1) * size + size, keyword, status);

        return new TempPageForm(total, pageNo, size, content);
    }

    // 임시보호 조회
    @Override
    public TempPet getTempByPk(int pk) {
        return tempDao.findByPk(pk);
    }

    // 임시보호 상태:'처리중'인 유기동물 리스트
    @Override
    public List<AbandonedAnimal> getAnimalListTempWait() {
        return abandonedAnimalDao.selectAllTempWait();
    }

    // 임시보호 중인 유기동물 리스트
    @Override
    public List<AbandonedAnimal> getAnimalListTempComplete() {
        return abandonedAnimalDao.selectAllTempComplete();
    }

    // ===================== Update =====================
    // 임시보호 업데이트
    @Override
    public void adminTempUpdate(TempForm form) {
        TempPet tempPet = adminToTemp(form);
        tempDao.update(tempPet);

        abandonedAnimalDao.updateToTemp();
        abandonedAnimalDao.updateToProtectInTemp();
    }

    // 임보 승인 버튼
    @Override
    public void tempApproveButton(int pk) {
        tempDao.tempApprove(pk);
    }

    // 임보 거절 버튼
    @Override
    public void tempRefuseButton(int pk) {
        tempDao.tempRefuse(pk);
    }

    // ===================== Delete =====================
    // 임시보호 삭제
    @Override
    public void deleteTemp(int pk) {
        abandonedAnimalDao.updateToProtectForDeleteInTemp(pk);
        tempDao.delete(pk);
    }


    // ===================== CRUD 끝 =====================

    private TempPet toTempPet(AdoptTempSubmitForm form) {
        return new TempPet(
                form.getAbNumber(),
                form.getMNumber(),
                form.getTempDate(),
                form.getTempPeriod(),
                form.getResidence(),
                form.getMaritalStatus(),
                form.getJob()
        );
    }

    private TempPet adminToTemp(TempForm form) {
        return new TempPet(
                form.getTNumber(),
                form.getAbNumber(),
                form.getMNumber(),
                form.getTempDate(),
                form.getTempPeriod(),
                form.getResidence(),
                form.getMaritalStatus(),
                form.getJob(),
                form.getStatus()
        );
    }

    // ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 관리자 페이지(입양/임보 관련) ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
    // 입양,임보 '처리중' 상태 제외한 유기동물 리스트
    @Override
    public List<AbandonedAnimal> getAnimalListOnlyProtect() {
        return abandonedAnimalDao.selectAllOnlyProtect();
    }

    // 거주지 리스트
    @Override
    public List<String> getResidenceList() {
        List<String> residences = new ArrayList<>();
        residenceList(residences);

        return residences;
    }

    private void residenceList(List<String> residences) {
        residences.add("서울특별시");
        residences.add("경기도");
        residences.add("인천광역시");
        residences.add("강원도");
        residences.add("충청북도");
        residences.add("충청남도");
        residences.add("경상북도");
        residences.add("경상남도");
        residences.add("전라북도");
        residences.add("전라남도");
        residences.add("부산광역시");
        residences.add("대구광역시");
        residences.add("부산광역시");
        residences.add("울산광역시");
        residences.add("광주광역시");
        residences.add("대전광역시");
        residences.add("세종특별자치시");
        residences.add("제주특별자치도");
    }
}