package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AbandonedAnimalService {

    AbandonedAnimalDetailForm getDetailForm(int abNumber);

    AbandonedAnimalPageForm getAbandonedAnimalPage(int pageNo);

    AbandonedAnimalPageForm getAbandonedAnimalPage(AbandonedAnimalConditionForm abandonedAnimalConditionForm);

    String findName(int abNumber);

    List<AbandonedAnimal> selectAll();

    void write(AdminAbandonedAnimalWriteForm adminAbandonedAnimalWriteForm);

    AdminAbandonedAnimalModifyForm getAbandonedModify(int abNumber);

    void modify(AdminAbandonedAnimalModifyForm adminAbandonedAnimalModifyForm);

    String storeFile(MultipartFile file, String filePath) throws IOException;

    void delete(int abNumber);

    AbandonedAnimal getAnimal(int abNumber);

    String getGroupName(int abNumber);

    String getPhone(int abNumber);
}
