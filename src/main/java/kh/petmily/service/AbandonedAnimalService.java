package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.*;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.temp.TempPet;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AbandonedAnimalService {

    void write(AdminAbandonedAnimalWriteForm form);

    void writeWithAdopt(AdminAbandonedAnimalWriteForm form);

    void writeWithTemp(AdminAbandonedAnimalWriteForm form);

    String storeFile(MultipartFile file, String filePath) throws IOException;

    AbandonedAnimalPageForm getListPage(AbandonedAnimalConditionForm form);

    AbandonedAnimalDetailForm getDetailPage(int abNumber);

    AbandonedAnimal getAbAnimal(int abNumber);

    List<AbandonedAnimal> getAbAnimalList();

    AbandonedAnimalPageForm getAdminListPage(int pageNo);

    Adopt getAdoptByPk(int pk);

    TempPet getTempByPk(int pk);

    List<String> getResidenceList();

    AdminAbandonedAnimalModifyForm getModifyForm(int abNumber);

    void modify(AdminAbandonedAnimalModifyForm form);

    void modifyWithAdopt(AdminAbandonedAnimalModifyForm form);

    void modifyWithTemp(AdminAbandonedAnimalModifyForm form);

    void delete(int abNumber);

    void deleteAdoptAndTemp(int pk);
}
