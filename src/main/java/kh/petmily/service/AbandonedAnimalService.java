package kh.petmily.service;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalDetailForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalModifyForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalWriteForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AbandonedAnimalService {

    AbandonedAnimalDetailForm getDetailForm(int abNumber);

    AbandonedAnimalPageForm getAbandonedAnimalPage(int pageNo);

    AbandonedAnimalPageForm getAbandonedAnimalPage(int pageNo, String species, String gender, String animalState, String keyword, String sort);

    String findName(int abNumber);

    List<AbandonedAnimal> selectAll();

    void write(AbandonedAnimalWriteForm abandonedAnimalWriteForm);

    AbandonedAnimalModifyForm getAbandonedModify(int abNumber);

    void modify(AbandonedAnimalModifyForm abandonedAnimalModifyForm);

    String storeFile(MultipartFile file, String filePath) throws IOException;

    void delete(int abNumber);

    AbandonedAnimal getAnimal(int abNumber);

    String getGroupName(int abNumber);

    String getPhone(int abNumber);
}
