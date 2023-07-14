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


    //    ============================== 입양 ==============================

    @Override
    public void adopt(AdoptTempSubmitForm adoptTempSubmitForm) {
        Adopt adopt = toAdopt(adoptTempSubmitForm);
        adoptDao.insert(adopt);
    }

    private Adopt toAdopt(AdoptTempSubmitForm adoptTempSubmitForm) {
        return new Adopt(adoptTempSubmitForm.getMNumber(), adoptTempSubmitForm.getAbNumber(),
                adoptTempSubmitForm.getResidence(), adoptTempSubmitForm.getMaritalStatus(),
                adoptTempSubmitForm.getJob());
    }

    @Override
    public Adopt findByAdoptPk(int adNumber) {
        return adoptDao.findByPk(adNumber);
    }

    // 마이페이지 입양
    @Override
    public MypageAdoptPageForm getMypageAdopt(int pageNo, int mNumber, String type) {
        int total = adoptDao.selectCount(mNumber);
        List<MypageAdoptListForm> content = adoptDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber);

        return new MypageAdoptPageForm(total, pageNo, size, content);
    }


    //    ============================== 임보 ==============================

    @Override
    public void temp(AdoptTempSubmitForm adoptTempSubmitForm) {
        TempPet tempPet = toTempPet(adoptTempSubmitForm);
        tempDao.insert(tempPet);
    }

    private TempPet toTempPet(AdoptTempSubmitForm adoptTempSubmitForm) {
        return new TempPet(adoptTempSubmitForm.getAbNumber(), adoptTempSubmitForm.getMNumber(),
                adoptTempSubmitForm.getResidence(), adoptTempSubmitForm.getMaritalStatus(),
                adoptTempSubmitForm.getJob());
    }

    @Override
    public TempPet findByTempPk(int tNumber) {
        return tempDao.findByPk(tNumber);
    }

    // 마이페이지 임보
    @Override
    public MypageTempPageForm getMypageTemp(int pageNo, int mNumber, String type) {
        int total = tempDao.selectCount(mNumber);
        List<MypageTempListForm> content = tempDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber);

        return new MypageTempPageForm(total, pageNo, size, content);
    }


    //    ============================== 관리자 페이지(입양) ==============================

    // 입양 관리 페이지
    @Override
    public AdoptPageForm getAdminAdoptListPage(int pageNo) {
        int total = adoptDao.selectCount();
        List<AdoptDetailForm> content = adoptDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size);

        return new AdoptPageForm(total, pageNo, size, content);
    }

    // insert
    @Override
    public List<Member> selectAllMemberAdopt() {
        return adoptDao.selectAllMember();
    }

    @Override
    public List<AbandonedAnimal> selectAllExcludeAdoptStatus() {
        return adoptDao.selectAllExcludeAdoptStatus();
    }

    @Override
    public void adminAdoptWrite(AdminAdoptForm adminAdoptForm) {
        Adopt adopt = adminToAdopt(adminAdoptForm);
        adoptDao.adminInsert(adopt);
    }

    @Override
    public void updateStatusToAdopt() {
        adoptDao.updateStatusToAdopt();
    }

    // update
    @Override
    public List<AbandonedAnimal> selectAllAbandonedAnimalAdopt() {
        return adoptDao.selectAllAbandonedAnimal();
    }

    @Override
    public void adminAdoptUpdate(AdminAdoptForm adminAdoptForm) {
        Adopt adopt = adminToAdopt(adminAdoptForm);
        adoptDao.update(adopt);
    }

    // delete
    @Override
    public void deleteAdopt(int adNumber) {
        adoptDao.delete(adNumber);
    }

    private Adopt adminToAdopt(AdminAdoptForm adminAdoptForm) {
        return new Adopt(adminAdoptForm.getAdNumber(), adminAdoptForm.getMNumber(), adminAdoptForm.getAbNumber(),
                adminAdoptForm.getResidence(), adminAdoptForm.getMaritalStatus(),
                adminAdoptForm.getJob(), adminAdoptForm.getStatus());
    }

    // 입양 승인 관리 페이지
    @Override
    public AdoptPageForm getAdminAdoptWaitPage(int pageNo, String status) {
        int total = adoptDao.selectCount();
        List<AdoptDetailForm> content = adoptDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, status);

        return new AdoptPageForm(total, pageNo, size, content);
    }

    @Override
    public void adoptApproveButton(int adNumber) {
        adoptDao.adoptApprove(adNumber);
    }

    @Override
    public void adoptRefuseButton(int adNumber) {
        adoptDao.adoptRefuse(adNumber);
    }


    //    ============================== 관리자 페이지(임보) ==============================

    // 임보 관리 페이지
    @Override
    public TempPageForm getAdminTempListPage(int pageNo) {
        int total = tempDao.selectCount();
        List<TempDetailForm> content = tempDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size);

        return new TempPageForm(total, pageNo, size, content);
    }

    // insert
    @Override
    public List<Member> selectAllMemberTemp() {
        return tempDao.selectAllMember();
    }

    @Override
    public List<AbandonedAnimal> selectAllExcludeTempStatus() {
        return tempDao.selectAllExcludeTempStatus();
    }

    @Override
    public void adminTempWrite(AdminTempForm adminTempForm) {
        TempPet tempPet = adminToTemp(adminTempForm);
        tempDao.adminInsert(tempPet);
    }

    @Override
    public void updateStatusToTemp() {
        tempDao.updateStatusToTemp();
    }

    // update
    @Override
    public List<AbandonedAnimal> selectAllAbandonedAnimalTemp() {
        return tempDao.selectAllAbandonedAnimal();
    }

    @Override
    public void adminTempUpdate(AdminTempForm adminTempForm) {
        TempPet tempPet = adminToTemp(adminTempForm);
        tempDao.update(tempPet);
    }

    // delete
    @Override
    public void deleteTemp(int tNumber) {
        tempDao.delete(tNumber);
    }

    private TempPet adminToTemp(AdminTempForm adminTempForm) {
        return new TempPet(adminTempForm.getTNumber(), adminTempForm.getAbNumber(), adminTempForm.getMNumber(),
                adminTempForm.getTempDate(), adminTempForm.getTempPeriod(),
                adminTempForm.getResidence(), adminTempForm.getMaritalStatus(),
                adminTempForm.getJob(), adminTempForm.getStatus());
    }

    // 임보 승인 관리 페이지
    @Override
    public TempPageForm getAdminTempWaitPage(int pageNo, String status) {
        int total = tempDao.selectCount();
        List<TempDetailForm> content = tempDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, status);

        return new TempPageForm(total, pageNo, size, content);
    }

    @Override
    public void tempApproveButton(int tNumber) {
        tempDao.tempApprove(tNumber);
    }

    @Override
    public void tempRefuseButton(int tNumber) {
        tempDao.tempRefuse(tNumber);
    }
}