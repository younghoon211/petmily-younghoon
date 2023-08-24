package kh.petmily.service;

import kh.petmily.dao.AdoptDao;
import kh.petmily.dao.TempDao;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AdoptTempSubmitForm;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.adopt.form.*;
import kh.petmily.domain.member.Member;
import kh.petmily.domain.temp.TempPet;
import kh.petmily.domain.temp.form.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdoptTempServiceImpl implements AdoptTempService {

    private final AdoptDao adoptDao;
    private final TempDao tempDao;
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
        int total = adoptDao.selectCount(mNumber);
        List<MypageAdoptListForm> content = adoptDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber);

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
        int total = tempDao.selectCount(mNumber);
        List<MypageTempListForm> content = tempDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber);

        return new MypageTempPageForm(total, pageNo, size, content);
    }


    // ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 관리자 페이지(입양) ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

    // ===================== Create =====================
    // 입양 글쓰기
    @Override
    public void adminAdoptWrite(AdminAdoptForm form) {
        Adopt adopt = adminToAdopt(form);
        adoptDao.adminInsert(adopt);
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
        List<AdminAdoptDetailForm> content = adoptDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, status);

        return new AdminAdoptPageForm(total, pageNo, size, content);
    }

    // 입양 조회
    @Override
    public Adopt getAdoptByPk(int adNumber) {
        return adoptDao.findByPk(adNumber);
    }

    // 모든 유기동물
    @Override
    public List<AbandonedAnimal> getAbAnimalListInAdopt() {
        return adoptDao.selectAllAbandonedAnimal();
    }

    // 입양 '완료'인 상태 제외한 유기동물 리스트
    @Override
    public List<AbandonedAnimal> getAbAnimalListExcludeAdopt() {
        return adoptDao.selectAllExcludeAdopt();
    }

    // 모든 회원 리스트
    @Override
    public List<Member> getMemberListInAdopt() {
        return adoptDao.selectAllMember();
    }

    // ===================== Update =====================
    // 입양 업데이트
    @Override
    public void adminAdoptUpdate(AdminAdoptForm form) {
        Adopt adopt = adminToAdopt(form);
        adoptDao.update(adopt);
    }

    // 입양 '완료'인 유기동물 '입양'으로 업데이트
    @Override
    public void updateStatusToAdopt() {
        adoptDao.updateStatusToAdopt();
    }

    // 입양 승인 버튼
    @Override
    public void adoptApproveButton(int adNumber) {
        adoptDao.adoptApprove(adNumber);
    }

    // 입양 거절 버튼
    @Override
    public void adoptRefuseButton(int adNumber) {
        adoptDao.adoptRefuse(adNumber);
    }

    // ===================== Delete =====================
    // 입양 삭제
    @Override
    public void deleteAdopt(int adNumber) {
        adoptDao.delete(adNumber);
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

    private Adopt adminToAdopt(AdminAdoptForm form) {
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
    public void adminTempWrite(AdminTempForm form) {
        TempPet tempPet = adminToTemp(form);
        tempDao.adminInsert(tempPet);
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
        List<AdminTempDetailForm> content = tempDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, status);

        return new AdminTempPageForm(total, pageNo, size, content);
    }

    // 임시보호 조회
    @Override
    public TempPet getTempByPk(int pk) {
        return tempDao.findByPk(pk);
    }

    // 모든 유기동물 리스트
    @Override
    public List<AbandonedAnimal> getAbAnimalListInTemp() {
        return tempDao.selectAllAbandonedAnimal();
    }

    // 임시보호 '완료'인 상태 제외한 유기동물 리스트
    @Override
    public List<AbandonedAnimal> getAbAnimalListExcludeTemp() {
        return tempDao.selectAllExcludeTemp();
    }

    // 모든 회원 리스트
    @Override
    public List<Member> getMemberListInTemp() {
        return tempDao.selectAllMember();
    }

    // ===================== Update =====================
    // 임시보호 업데이트
    @Override
    public void adminTempUpdate(AdminTempForm form) {
        TempPet tempPet = adminToTemp(form);
        tempDao.update(tempPet);
    }

    // 임보 '완료'인 유기동물 '임보'로 업데이트
    @Override
    public void updateStatusToTemp() {
        tempDao.updateStatusToTemp();
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
}