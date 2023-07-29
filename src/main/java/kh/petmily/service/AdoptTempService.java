package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AdoptTempSubmitForm;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.adopt.form.AdminAdoptForm;
import kh.petmily.domain.adopt.form.AdminAdoptPageForm;
import kh.petmily.domain.adopt.form.MypageAdoptPageForm;
import kh.petmily.domain.member.Member;
import kh.petmily.domain.temp.TempPet;
import kh.petmily.domain.temp.form.AdminTempForm;
import kh.petmily.domain.temp.form.AdminTempPageForm;
import kh.petmily.domain.temp.form.MypageTempPageForm;

import java.util.List;

public interface AdoptTempService {

    // 입양
    void adopt(AdoptTempSubmitForm form);

    MypageAdoptPageForm getMypageAdopt(int pageNo, int mNumber, String type);

    // 임보
    void temp(AdoptTempSubmitForm form);

    MypageTempPageForm getMypageTemp(int pageNo, int mNumber, String type);

    // 입양 관리
    void adminAdoptWrite(AdminAdoptForm form);

    AdminAdoptPageForm getAdminAdoptListPage(int pageNo);

    AdminAdoptPageForm getAdminAdoptWaitPage(int pageNo, String status);

    Adopt getAdoptByPk(int adNumber);

    List<AbandonedAnimal> selectAllAbandonedAnimalAdopt();

    List<AbandonedAnimal> selectAllExcludeAdoptStatus();

    List<Member> selectAllMemberAdopt();

    void adminAdoptUpdate(AdminAdoptForm form);

    void updateStatusToAdopt();

    void adoptApproveButton(int adNumber);

    void adoptRefuseButton(int adNumber);

    void deleteAdopt(int adNumber);

    // 임보 관리
    void adminTempWrite(AdminTempForm form);

    AdminTempPageForm getAdminTempListPage(int pageNo);

    AdminTempPageForm getAdminTempWaitPage(int pageNo, String status);

    TempPet getTempByPk(int tNumber);

    List<AbandonedAnimal> selectAllAbandonedAnimalTemp();

    List<AbandonedAnimal> selectAllExcludeTempStatus();

    List<Member> selectAllMemberTemp();

    void adminTempUpdate(AdminTempForm form);

    void updateStatusToTemp();

    void tempApproveButton(int tNumber);

    void tempRefuseButton(int tNumber);

    void deleteTemp(int tNumber);
}