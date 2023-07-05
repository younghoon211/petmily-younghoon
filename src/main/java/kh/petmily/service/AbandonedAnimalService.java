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

    public AbandonedAnimalDetailForm getDetailForm(int abNumber);

    public AbandonedAnimalPageForm getAbandonedAnimalPage(int pageNo);

    public AbandonedAnimalPageForm getAbandonedAnimalPage(int pageNo, String species, String gender, String animalState, String keyword, String sort);

    String findName(int abNumber);

    List<AbandonedAnimal> selectAll();

    public void write(AbandonedAnimalWriteForm abandonedAnimalWriteForm);

    public AbandonedAnimalModifyForm getAbandonedModify(int abNumber);

    public void modify(AbandonedAnimalModifyForm abandonedAnimalModifyForm);

    public String storeFile(MultipartFile file, String filePath) throws IOException;

    public void delete(int abNumber);

    public AbandonedAnimal getAnimal(int abNumber);

    public String getGroupName(int abNumber);

    public String getPhone(int abNumber);
}
