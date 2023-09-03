package kh.petmily.service;

import kh.petmily.dao.AbandonedAnimalDao;
import kh.petmily.dao.AdoptDao;
import kh.petmily.dao.TempDao;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AdoptTempSubmitForm;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.adopt.form.*;
import kh.petmily.domain.temp.TempPet;
import kh.petmily.domain.temp.form.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void adoptWrite(AdminAdoptForm form) {
        Adopt adopt = toWrite(form);

        adoptDao.adminInsert(adopt);
        abandonedAnimalDao.updateToAdopt();
    }

    // ===================== Read =====================

    // 입양 관리 리스트 페이지
    @Override
    public AdminAdoptPageForm getAdminAdoptListPage(int pageNo) {
        int total = adoptDao.selectCount();
        List<AdminAdoptDetailForm> content = adoptDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size);

        return new AdminAdoptPageForm(total, pageNo, size, content);
    }

    // 입양 승인 관리 페이지
    @Override
    public AdminAdoptPageForm getAdminAdoptWaitPage(int pageNo, String status) {
        int total = adoptDao.selectCount();
        List<AdminAdoptDetailForm> content = adoptDao.selectIndexByStatus((pageNo - 1) * size + 1, (pageNo - 1) * size + size, status);

        return new AdminAdoptPageForm(total, pageNo, size, content);
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
    public void adminAdoptUpdate(AdminAdoptForm form) {
        Adopt adopt = toModify(form);
        adoptDao.update(adopt);

        abandonedAnimalDao.updateToAdopt();
        abandonedAnimalDao.updateToProtectInAdopt();
    }

    // 입양 '완료'인 유기동물 '입양'으로 업데이트
    @Override
    public void updateStatusToAdopt() {
        abandonedAnimalDao.updateToAdopt();
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

    private Adopt toWrite(AdminAdoptForm form) {
        return new Adopt(
                form.getMNumber(),
                form.getAbNumber(),
                form.getResidence(),
                form.getMaritalStatus(),
                form.getJob(),
                form.getStatus()
        );
    }

    private Adopt toModify(AdminAdoptForm form) {
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
    public void tempWrite(AdminTempForm form) {
        TempPet tempPet = adminToTemp(form);

        tempDao.adminInsert(tempPet);
        abandonedAnimalDao.updateToTemp();
    }

    // ===================== Read =====================
    // 임보 관리 리스트 페이지
    @Override
    public AdminTempPageForm getAdminTempListPage(int pageNo) {
        int total = tempDao.selectCount();
        List<AdminTempDetailForm> content = tempDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size);

        return new AdminTempPageForm(total, pageNo, size, content);
    }

    // 임보 승인 관리 페이지
    @Override
    public AdminTempPageForm getAdminTempWaitPage(int pageNo, String status) {
        int total = tempDao.selectCount();
        List<AdminTempDetailForm> content = tempDao.selectIndexByStatus((pageNo - 1) * size + 1, (pageNo - 1) * size + size, status);

        return new AdminTempPageForm(total, pageNo, size, content);
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
    public void adminTempUpdate(AdminTempForm form) {
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
                form.getResidence(),
                form.getMaritalStatus(),
                form.getJob()
        );
    }

    private TempPet adminToTemp(AdminTempForm form) {
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