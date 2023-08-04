package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.*;
import kh.petmily.domain.shelter.Shelter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AbandonedAnimalService {

    void write(AdminAbandonedAnimalWriteForm form);

    String storeFile(MultipartFile file, String filePath) throws IOException;

    AbandonedAnimalPageForm getListPage(AbandonedAnimalConditionForm form);

    AbandonedAnimalDetailForm getDetailPage(int abNumber);

    List<AbandonedAnimal> selectAll();

    AbandonedAnimal getAnimal(int abNumber);

    String getAnimalName(int abNumber);

    AbandonedAnimalPageForm getAdminListPage(int pageNo);

    AdminAbandonedAnimalModifyForm getModifyForm(int abNumber);

    void modify(AdminAbandonedAnimalModifyForm form);

    void delete(int abNumber);
}
