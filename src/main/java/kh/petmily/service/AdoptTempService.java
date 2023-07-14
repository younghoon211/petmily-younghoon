package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AdoptTempSubmitForm;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.adopt.form.*;
import kh.petmily.domain.member.Member;
import kh.petmily.domain.temp.TempPet;
import kh.petmily.domain.temp.form.AdminTempForm;
import kh.petmily.domain.temp.form.MypageTempPageForm;
import kh.petmily.domain.temp.form.TempDetailForm;
import kh.petmily.domain.temp.form.TempPageForm;

import java.util.List;

public interface AdoptTempService {

    // 입양
    void adopt(AdoptTempSubmitForm adoptTempSubmitForm);

    Adopt findByAdoptPk(int adNumber);

    MypageAdoptPageForm getMypageAdopt(int pageNo, int mNumber, String type);

    // 임보
    void temp(AdoptTempSubmitForm adoptTempSubmitForm);

    TempPet findByTempPk(int tNumber);

    MypageTempPageForm getMypageTemp(int pageNo, int mNumber, String type);

    // 입양 관리 페이지
    AdoptPageForm getAdminAdoptListPage(int pageNo);

    List<Member> selectAllMemberAdopt();

    List<AbandonedAnimal> selectAllExcludeAdoptStatus();

    void adminAdoptWrite(AdminAdoptForm adminAdoptForm);

    void updateStatusToAdopt();

    List<AbandonedAnimal> selectAllAbandonedAnimalAdopt();

    void adminAdoptUpdate(AdminAdoptForm adminAdoptForm);

    void deleteAdopt(int adNumber);

    AdoptPageForm getAdminAdoptWaitPage(int pageNo, String status);

    void adoptApproveButton(int adNumber);

    void adoptRefuseButton(int adNumber);


    // 임보 관리 페이지
    TempPageForm getAdminTempListPage(int pageNo);

    List<Member> selectAllMemberTemp();

    List<AbandonedAnimal> selectAllExcludeTempStatus();

    void adminTempWrite(AdminTempForm adminTempForm);

    void updateStatusToTemp();

    List<AbandonedAnimal> selectAllAbandonedAnimalTemp();

    void adminTempUpdate(AdminTempForm adminTempForm);

    void deleteTemp(int tNumber);

    TempPageForm getAdminTempWaitPage(int pageNo, String status);

    void tempApproveButton(int tNumber);

    void tempRefuseButton(int tNumber);
}