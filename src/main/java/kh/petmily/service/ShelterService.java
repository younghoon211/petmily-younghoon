package kh.petmily.service;

import kh.petmily.domain.shelter.Shelter;
import kh.petmily.domain.admin_form.ShelterUpdateForm;
import kh.petmily.domain.admin_form.ShelterPageForm;
import kh.petmily.domain.admin_form.ShelterInsertForm;

import java.util.List;

public interface ShelterService {

    void insert(ShelterInsertForm form);

    ShelterPageForm getListPage(int pageNo, String keyword);

    List<Shelter> getShelterListNotSNumber0();

    ShelterUpdateForm getUpdateForm(int sNumber);

    void update(ShelterUpdateForm form);

    void delete(int sNumber);
}
