package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.*;
import kh.petmily.domain.admin_form.AbandonedAnimalUpdateForm;
import kh.petmily.domain.admin_form.AbandonedAnimalInsertForm;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.temp.TempPet;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AbandonedAnimalService {

    void insert(AbandonedAnimalInsertForm form);

    void insertWithAdopt(AbandonedAnimalInsertForm form);

    void insertWithTemp(AbandonedAnimalInsertForm form);

    String storeFile(MultipartFile file, String filePath) throws IOException;

    AbandonedAnimalPageForm getListPage(AbandonedAnimalConditionForm form);

    AbandonedAnimalPageForm getAdoptedListPage(AbandonedAnimalConditionForm form);

    AbandonedAnimalDetailForm getDetailPage(int abNumber);

    Adopt getAdoptByPk(int pk);

    TempPet getTempByPk(int pk);

    AbandonedAnimal getAbAnimal(int abNumber);

    List<AbandonedAnimal> getAbAnimalList();

    AbandonedAnimalPageForm getAdminListPage(AbandonedAnimalConditionForm form, int pageNo);

    Adopt getAdoptCompleteByPk(int pk);

    TempPet getTempCompleteByPk(int pk);

    List<String> getResidenceList();

    AbandonedAnimalUpdateForm getUpdateForm(int abNumber);

    void update(AbandonedAnimalUpdateForm form);

    void updateWithAdopt(AbandonedAnimalUpdateForm form);

    void updateWithTemp(AbandonedAnimalUpdateForm form);

    void delete(int abNumber);

    void deleteAdoptAndTemp(int pk);
}
