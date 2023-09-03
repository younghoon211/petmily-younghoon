package kh.petmily.service;

import kh.petmily.domain.shelter.Shelter;
import kh.petmily.domain.shelter.form.ShelterModifyForm;
import kh.petmily.domain.shelter.form.ShelterPageForm;
import kh.petmily.domain.shelter.form.ShelterWriteForm;

import java.util.List;

public interface ShelterService {

    void create(ShelterWriteForm form);

    ShelterPageForm getListPage(int pageNo);

    List<Shelter> getShelterList();

    List<Shelter> getShelterListNotSNumber0();

    ShelterModifyForm getModifyForm(int sNumber);

    void modify(ShelterModifyForm form);

    void delete(int sNumber);
}
