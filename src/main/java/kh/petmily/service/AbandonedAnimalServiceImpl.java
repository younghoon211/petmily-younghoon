package kh.petmily.service;

import kh.petmily.dao.AbandonedAnimalDao;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.*;
import kh.petmily.domain.shelter.Shelter;
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

    // 유기동물 CUD : 관리자만 가능

    // ===================== Create =====================
    // 글쓰기
    @Override
    public void write(AdminAbandonedAnimalWriteForm form) {
        AbandonedAnimal abandonedAnimal = toWrite(form);
        abandonedAnimalDao.insert(abandonedAnimal);
    }

    // 파일 업로드
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
        String storeFileName = uuid + "." + extractExt(originalFilename); // 확장자 포함한 uuid명
        String fullPath = getFullPath(storeFileName, filePath);

        log.info("fullPath = {}", fullPath);

        file.transferTo(new File(fullPath));

        return storeFileName;
    }

    // ===================== Read =====================
    // 게시판 리스트
    @Override
    public AbandonedAnimalPageForm getListPage(AbandonedAnimalConditionForm form) {
        int total = abandonedAnimalDao.selectCount(form);
        List<AbandonedAnimalListForm> content = abandonedAnimalDao.selectIndex((form.getPageNo() - 1) * size + 1, (form.getPageNo() - 1) * size + size, form);

        return new AbandonedAnimalPageForm(total, form.getPageNo(), size, content);
    }

    // 글 상세보기
    @Override
    public AbandonedAnimalDetailForm getDetailPage(int pk) {
        AbandonedAnimal abandonedAnimal = abandonedAnimalDao.findByPk(pk);

        return toDetailForm(abandonedAnimal);
    }

    @Override
    public List<AbandonedAnimal> selectAll() {
        return abandonedAnimalDao.selectAll();
    }

    @Override
    public AbandonedAnimal getAnimal(int pk) {
        return abandonedAnimalDao.findByPk(pk);
    }

    @Override
    public String getAnimalName(int pk) {
        return abandonedAnimalDao.selectName(pk);
    }

    // 게시판 리스트 (관리자 페이지)
    @Override
    public AbandonedAnimalPageForm getAdminListPage(int pageNo) {
        int total = abandonedAnimalDao.selectCount();
        List<AbandonedAnimalListForm> content = abandonedAnimalDao.selectIndex((pageNo - 1) * size, size);

        return new AbandonedAnimalPageForm(total, pageNo, size, content);
    }

    // ===================== Update =====================
    // 수정 폼
    @Override
    public AdminAbandonedAnimalModifyForm getModifyForm(int pk) {
        AbandonedAnimal abandonedAnimal = abandonedAnimalDao.findByPk(pk);

        return toModifyForm(abandonedAnimal);
    }

    // 수정 폼 제출
    @Override
    public void modify(AdminAbandonedAnimalModifyForm form) {
        AbandonedAnimal abandonedAnimal = toModify(form);
        abandonedAnimalDao.update(abandonedAnimal);
    }

    // ===================== Delete =====================
    // 삭제
    @Override
    public void delete(int pk) {
        abandonedAnimalDao.delete(pk);
    }


    // ===================== CRUD 끝 =====================


    private AbandonedAnimal toWrite(AdminAbandonedAnimalWriteForm form) {
        return new AbandonedAnimal(
                form.getSNumber(),
                form.getName(),
                form.getSpecies(),
                form.getKind(),
                form.getGender(),
                form.getAge(),
                form.getWeight(),
                form.getImgPath(),
                form.getLocation(),
                form.getAdmissionDate(),
                form.getUniqueness(),
                form.getDescription(),
                form.getAnimalState());
    }

    private String getFullPath(String filename, String filePath) {
        return filePath + filename;
    }

    private String extractExt(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");

        return originalFilename.substring(position + 1);
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

    private String getGroupName(int pk) {
        return abandonedAnimalDao.selectGroupName(pk);
    }

    private String getPhone(int pk) {
        return abandonedAnimalDao.selectPhone(pk);
    }

    private AdminAbandonedAnimalModifyForm toModifyForm(AbandonedAnimal domain) {
        return new AdminAbandonedAnimalModifyForm(
                domain.getAbNumber(),
                domain.getSNumber(),
                domain.getName(),
                domain.getSpecies(),
                domain.getKind(),
                domain.getGender(),
                domain.getAge(),
                domain.getWeight(),
                domain.getImgPath(),
                domain.getLocation(),
                domain.getAdmissionDate(),
                domain.getUniqueness(),
                domain.getDescription(),
                domain.getAnimalState()
        );
    }

    private AbandonedAnimal toModify(AdminAbandonedAnimalModifyForm form) {
        return new AbandonedAnimal(
                form.getAbNumber(),
                form.getSNumber(),
                form.getName(),
                form.getSpecies(),
                form.getKind(),
                form.getGender(),
                form.getAge(),
                form.getWeight(),
                form.getImgPath(),
                form.getLocation(),
                form.getAdmissionDate(),
                form.getUniqueness(),
                form.getDescription(),
                form.getAnimalState()
        );
    }
}