package petmily.service;

import petmily.domain.abandoned_animal.AbandonedAnimal;
import petmily.domain.abandoned_animal.form.AdoptTempSubmitForm;
import petmily.domain.adopt.Adopt;
import petmily.domain.admin_form.AdoptForm;
import petmily.domain.admin_form.AdoptPageForm;
import petmily.domain.adopt.form.MypageAdoptPageForm;
import petmily.domain.temp.TempPet;
import petmily.domain.admin_form.TempForm;
import petmily.domain.admin_form.TempPageForm;
import petmily.domain.temp.form.MypageTempPageForm;

import java.util.List;

public interface AdoptTempService {

    // 입양
    void adopt(AdoptTempSubmitForm form);

    MypageAdoptPageForm getMypageAdopt(int pageNo, int mNumber, String type);

    int getCountAdoptedStatus(int mNumber);

    // 임보
    void temp(AdoptTempSubmitForm form);

    MypageTempPageForm getMypageTemp(int pageNo, int mNumber, String type);

    // 입양 관리
    void adoptInsert(AdoptForm form);

    AdoptPageForm getAdminAdoptListPage(int pageNo, String keyword);

    AdoptPageForm getAdminAdoptWaitPage(int pageNo, String status, String keyword);

    Adopt getAdoptByPk(int adNumber);

    List<AbandonedAnimal> getAnimalListAdoptWait();

    List<AbandonedAnimal> getAnimalListAdoptComplete();

    void adminAdoptUpdate(AdoptForm form);

    void adoptApproveButton(int adNumber);

    void adoptRefuseButton(int adNumber);

    void deleteAdopt(int adNumber);

    // 임보 관리
    void tempInsert(TempForm form);

    TempPageForm getAdminTempListPage(int pageNo, String keyword);

    TempPageForm getAdminTempWaitPage(int pageNo, String keyword, String status);

    TempPet getTempByPk(int tNumber);

    List<AbandonedAnimal> getAnimalListTempWait();

    List<AbandonedAnimal> getAnimalListTempComplete();

    void adminTempUpdate(TempForm form);

    void tempApproveButton(int tNumber);

    void tempRefuseButton(int tNumber);

    void deleteTemp(int tNumber);

    // 입양/임보
    List<AbandonedAnimal> getAnimalListOnlyProtected();

    List<String> getResidenceList();
}