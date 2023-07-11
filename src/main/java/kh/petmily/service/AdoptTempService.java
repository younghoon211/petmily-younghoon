package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AdoptTempSubmitForm;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.adopt.form.*;
import kh.petmily.domain.member.Member;
import kh.petmily.domain.temp.form.MypageTempPageForm;
import kh.petmily.domain.temp.form.TempDetailForm;
import kh.petmily.domain.temp.form.TempPageForm;

import java.util.List;

public interface AdoptTempService {

    void adopt(AdoptTempSubmitForm adoptTempSubmitForm);

    void temp(AdoptTempSubmitForm adoptTempSubmitForm);

    MypageAdoptPageForm getMypageAdopt(int pageNo, int mNumber, String type);

    MypageTempPageForm getMypageTemp(int pageNo, int mNumber, String type);

    Adopt findByPk(int adNumber);

    // 관리자 페이지
    AdoptPageForm getAdminAdoptListPage(int pageNo);

    AdoptPageForm getAdminAdoptWaitPage(int pageNo, String status);

    TempPageForm getTempWaitPage(int pageNo, String status);

    List<AdoptDetailForm> adoptApprove(int adNumber);

    List<AdoptDetailForm> adoptRefuse(int adNumber);

    List<TempDetailForm> tempApprove(int tNumber);

    List<TempDetailForm> tempRefuse(int tNumber);

    void adminAdoptWrite(AdminAdoptForm adminAdoptForm);

    void adminAdoptUpdate(AdminAdoptForm adminAdoptForm);

    void updateStatusToAdopt();

    List<Member> selectAllMember();

    List<AbandonedAnimal> selectAllExcludeAdoptStatus();

    void delete(int adNumber);

//     List<AdoptDetailForm> selectAllAdopt();

    List<AbandonedAnimal> selectAllAbandonedAnimal();

    TempPageForm getAdminTempListPage(int pageNo);
}