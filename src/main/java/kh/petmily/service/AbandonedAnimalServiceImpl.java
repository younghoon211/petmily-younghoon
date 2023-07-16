package kh.petmily.service;

import kh.petmily.dao.AbandonedAnimalDao;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AbandonedAnimalServiceImpl implements AbandonedAnimalService {

    private final AbandonedAnimalDao abandonedAnimalDao;
    private int size = 12;

    private String getFullPath(String filename, String filePath) {
        return filePath + filename;
    }

    private String extractExt(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");

        return originalFilename.substring(position + 1);
    }

    @Override
    public String storeFile(MultipartFile file, String filePath) throws IOException {
        log.info("storeFile = {} ", file.getOriginalFilename());

        if (file.isEmpty()) {
            return null;
        }

        File storeFolder = new File(filePath);
        if (!storeFolder.exists()) {
            storeFolder.mkdir();
        }
        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String storeFileName = uuid + "." + extractExt(originalFilename);
        String fullPath = getFullPath(storeFileName, filePath);
        file.transferTo(new File(fullPath));

        return storeFileName;
    }

    @Override
    public AbandonedAnimalPageForm getAbandonedAnimalPage(int pageNo) {
        int total = abandonedAnimalDao.selectCount();
        List<AbandonedAnimalListForm> content = abandonedAnimalDao.selectIndex((pageNo - 1) * size, size);

        return new AbandonedAnimalPageForm(total, pageNo, size, content);
    }

    @Override
    public AbandonedAnimalPageForm getAbandonedAnimalPage(AbandonedAnimalConditionForm ac) {
        int total = abandonedAnimalDao.selectCount(ac);
        List<AbandonedAnimalListForm> content = abandonedAnimalDao.selectIndex((ac.getPageNo() - 1) * size + 1, (ac.getPageNo() - 1) * size + size, ac);

        return new AbandonedAnimalPageForm(total, ac.getPageNo(), size, content);
    }

    @Override
    public String findName(int abNumber) {
        return abandonedAnimalDao.selectName(abNumber);
    }

    @Override
    public AbandonedAnimal getAnimal(int abNumber) {
        return abandonedAnimalDao.findByPk(abNumber);
    }

    @Override
    public List<AbandonedAnimal> selectAll() {
        return abandonedAnimalDao.selectAll();
    }

    @Override
    public AbandonedAnimalDetailForm getDetailForm(int abNumber) {
        AbandonedAnimal findABAnimal = abandonedAnimalDao.findByPk(abNumber);
        AbandonedAnimalDetailForm detailForm = toDetailForm(findABAnimal);

        return detailForm;
    }

    private AbandonedAnimalDetailForm toDetailForm(AbandonedAnimal domain) {
        return new AbandonedAnimalDetailForm(
                domain.getAbNumber(),
                domain.getSNumber(),
                domain.getAge(),
                domain.getWeight(),
                domain.getGender(),
                domain.getName(),
                domain.getSpecies(),
                domain.getKind(),
                domain.getLocation(),
                domain.getUniqueness(),
                domain.getDescription(),
                domain.getAnimalState(),
                domain.getImgPath(),
                domain.getAdmissionDate(),
                getGroupName(domain.getAbNumber()),
                getPhone(domain.getAbNumber()));
    }

    @Override
    public void write(AdminAbandonedAnimalWriteForm adminAbandonedAnimalWriteForm) {
        AbandonedAnimal abandonedAnimal = toAbandonedAnimalWriteForm(adminAbandonedAnimalWriteForm);
        abandonedAnimalDao.insert(abandonedAnimal);
    }

    @Override
    public AdminAbandonedAnimalModifyForm getAbandonedModify(int abNumber) {
        AbandonedAnimal abandonedAnimal = abandonedAnimalDao.findByPk(abNumber);
        AdminAbandonedAnimalModifyForm modReq = toAbandonedAnimalModify(abandonedAnimal);

        return modReq;
    }

    @Override
    public void modify(AdminAbandonedAnimalModifyForm modReq) {
        AbandonedAnimal abandonedAnimal = toAbandonedAnimalModifyForm(modReq);
        log.info("Service - abandonedAnimal : {}", abandonedAnimal);
        abandonedAnimalDao.update(abandonedAnimal);
    }

    @Override
    public void delete(int abNumber) {
        abandonedAnimalDao.delete(abNumber);
    }

    @Override
    public String getGroupName(int abNumber) {
        return abandonedAnimalDao.selectGroupName(abNumber);
    }

    @Override
    public String getPhone(int abNumber) {
        return abandonedAnimalDao.selectPhone(abNumber);
    }

    private AbandonedAnimal toAbandonedAnimalWriteForm(AdminAbandonedAnimalWriteForm req) {
        return new AbandonedAnimal(
                req.getSNumber(),
                req.getName(),
                req.getSpecies(),
                req.getKind(),
                req.getGender(),
                req.getAge(),
                req.getWeight(),
                req.getFullPath(),
                req.getLocation(),
                req.getAdmissionDate(),
                req.getUniqueness(),
                req.getDescription(),
                req.getAnimalState());
    }

    private AdminAbandonedAnimalModifyForm toAbandonedAnimalModify(AbandonedAnimal abandonedAnimal) {
        return new AdminAbandonedAnimalModifyForm(
                abandonedAnimal.getAbNumber(),
                abandonedAnimal.getSNumber(),
                abandonedAnimal.getName(),
                abandonedAnimal.getSpecies(),
                abandonedAnimal.getKind(),
                abandonedAnimal.getGender(),
                abandonedAnimal.getAge(),
                abandonedAnimal.getWeight(),
                abandonedAnimal.getLocation(),
                abandonedAnimal.getAdmissionDate(),
                abandonedAnimal.getUniqueness(),
                abandonedAnimal.getDescription(),
                abandonedAnimal.getAnimalState()
        );
    }

    private AbandonedAnimal toAbandonedAnimalModifyForm(AdminAbandonedAnimalModifyForm modReq) {
        return new AbandonedAnimal(
                modReq.getAbNumber(),
                modReq.getSNumber(),
                modReq.getName(),
                modReq.getSpecies(),
                modReq.getKind(),
                modReq.getGender(),
                modReq.getAge(),
                modReq.getWeight(),
                modReq.getFullPath(),
                modReq.getLocation(),
                modReq.getAdmissionDate(),
                modReq.getUniqueness(),
                modReq.getDescription(),
                modReq.getAnimalState()
        );
    }
}