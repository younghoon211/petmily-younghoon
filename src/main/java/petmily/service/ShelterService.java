package petmily.service;

import petmily.domain.shelter.Shelter;
import petmily.domain.admin_form.ShelterUpdateForm;
import petmily.domain.admin_form.ShelterPageForm;
import petmily.domain.admin_form.ShelterInsertForm;

import java.util.List;

public interface ShelterService {

    void insert(ShelterInsertForm form);

    ShelterPageForm getListPage(int pageNo, String keyword);

    List<Shelter> getShelterList();

    ShelterUpdateForm getUpdateForm(int sNumber);

    void update(ShelterUpdateForm form);

    void delete(int sNumber);
}
