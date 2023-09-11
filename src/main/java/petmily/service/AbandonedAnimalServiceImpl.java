package petmily.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import petmily.dao.AbandonedAnimalDao;
import petmily.dao.AdoptDao;
import petmily.dao.TempDao;
import petmily.domain.abandoned_animal.AbandonedAnimal;
import petmily.domain.abandoned_animal.form.AbandonedAnimalConditionForm;
import petmily.domain.abandoned_animal.form.AbandonedAnimalDetailForm;
import petmily.domain.abandoned_animal.form.AbandonedAnimalListForm;
import petmily.domain.abandoned_animal.form.AbandonedAnimalPageForm;
import petmily.domain.admin_form.AbandonedAnimalInsertForm;
import petmily.domain.admin_form.AbandonedAnimalUpdateForm;
import petmily.domain.adopt.Adopt;
import petmily.domain.shelter.Shelter;
import petmily.domain.temp.TempPet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AbandonedAnimalServiceImpl implements AbandonedAnimalService {

    private final AbandonedAnimalDao abandonedAnimalDao;
    private final AdoptDao adoptDao;
    private final TempDao tempDao;
    private final int size = 12;

    // 유기동물 CUD : 관리자만 가능

    // ===================== Create =====================
    // 글쓰기 (보호)
    @Override
    public void insert(AbandonedAnimalInsertForm form) {
        AbandonedAnimal abandonedAnimal = toInsert(form);
        abandonedAnimalDao.insert(abandonedAnimal);
    }

    // 글쓰기 (입양)
    @Override
    public void insertWithAdopt(AbandonedAnimalInsertForm form) {
        Adopt adopt = toInsertAdopt(form);
        adoptDao.adminInsert(adopt);
    }

    // 글쓰기 (임보)
    @Override
    public void insertWithTemp(AbandonedAnimalInsertForm form) {
        TempPet temp = toInsertTemp(form);
        tempDao.adminInsert(temp);
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
        int total = abandonedAnimalDao.selectCountWithCondition(form);
        List<AbandonedAnimalListForm> content = abandonedAnimalDao.selectIndexWithCondition((form.getPageNo() - 1) * size + 1, (form.getPageNo() - 1) * size + size, form);

        return new AbandonedAnimalPageForm(total, form.getPageNo(), size, content);
    }

    // 입양 완료 리스트
    @Override
    public AbandonedAnimalPageForm getAdoptedListPage(AbandonedAnimalConditionForm form) {
        int total = abandonedAnimalDao.selectCountAdopted(form);
        List<AbandonedAnimalListForm> content = abandonedAnimalDao.selectIndexAdopted((form.getPageNo() - 1) * size + 1, (form.getPageNo() - 1) * size + size, form);

        return new AbandonedAnimalPageForm(total, form.getPageNo(), size, content);
    }

    // 글 상세보기
    @Override
    public AbandonedAnimalDetailForm getDetailPage(int pk) {
        AbandonedAnimal abandonedAnimal = abandonedAnimalDao.findByPk(pk);

        return toDetailForm(abandonedAnimal);
    }

    // pk로 입양 조회
    @Override
    public Adopt getAdoptByPk(int pk) {
        return abandonedAnimalDao.selectAdoptByAbNumber(pk);
    }

    // pk로 임보 조회
    @Override
    public TempPet getTempByPk(int pk) {
        return abandonedAnimalDao.selectTempByAbNumber(pk);
    }

    // pk로 유기동물 조회
    @Override
    public AbandonedAnimal getAbAnimal(int pk) {
        return abandonedAnimalDao.findByPk(pk);
    }

    // 유기동물 리스트 조회
    @Override
    public List<AbandonedAnimal> getAbAnimalList() {
        return abandonedAnimalDao.selectAll();
    }

    // 게시판 리스트 (관리자 페이지)
    @Override
    public AbandonedAnimalPageForm getAdminListPage(AbandonedAnimalConditionForm form, int pageNo) {
        int total = abandonedAnimalDao.selectCount(form);
        List<AbandonedAnimalListForm> content = abandonedAnimalDao.selectIndex(form, (pageNo - 1) * size, size);

        return new AbandonedAnimalPageForm(total, pageNo, size, content);
    }

    // pk로 입양 조회
    @Override
    public Adopt getAdoptCompleteByPk(int pk) {
        return abandonedAnimalDao.selectAdoptCompleteByPk(pk);
    }

    // pk로 임보 조회
    @Override
    public TempPet getTempCompleteByPk(int pk) {
        return abandonedAnimalDao.selectTempCompleteByPk(pk);
    }

    // 거주지 리스트
    @Override
    public List<String> getResidenceList() {
        List<String> residences = new ArrayList<>();
        residenceList(residences);

        return residences;
    }


    // ===================== Update =====================
    // 수정 폼
    @Override
    public AbandonedAnimalUpdateForm getUpdateForm(int pk) {
        AbandonedAnimal abandonedAnimal = abandonedAnimalDao.findByPk(pk);

        return toUpdateForm(abandonedAnimal);
    }

    // 수정 폼 제출
    @Override
    public void update(AbandonedAnimalUpdateForm form) {
        AbandonedAnimal abandonedAnimal = toUpdate(form);
        abandonedAnimalDao.update(abandonedAnimal);
    }

    // 수정 시 입양 insert 및 동일한 abNumber중 '처리중' 상태 + 기존 임보 정보 delete
    @Override
    public void updateWithAdopt(AbandonedAnimalUpdateForm form) {
        Adopt adopt = toUpdateAdopt(form);
        adoptDao.adminInsert(adopt);

        adoptDao.deleteWaitingWhenUpdateAB(form.getAbNumber());
        tempDao.deleteCompleteWhenUpdateAB(form.getAbNumber());
    }

    // 수정 시 임보 insert 및 동일한 abNumber중 '처리중' 상태 + 기존 입양 정보 delete
    @Override
    public void updateWithTemp(AbandonedAnimalUpdateForm form) {
        TempPet temp = toUpdateTemp(form);
        tempDao.adminInsert(temp);

        tempDao.deleteWaitingWhenUpdateAB(form.getAbNumber());
        adoptDao.deleteCompleteWhenUpdateAB(form.getAbNumber());
    }


    // ===================== Delete =====================
    // 삭제
    @Override
    public void delete(int pk) {
        abandonedAnimalDao.delete(pk);
    }

    // 유기동물 보호로 수정 시 기존 입양/임보테이블 삭제
    @Override
    public void deleteAdoptAndTemp(int pk) {
        adoptDao.deleteCompleteWhenUpdateAB(pk);
        tempDao.deleteCompleteWhenUpdateAB(pk);
    }


    // ===================== CRUD 끝 =====================


    private AbandonedAnimal toInsert(AbandonedAnimalInsertForm form) {
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
                form.getAnimalState()
        );
    }

    private Adopt toInsertAdopt(AbandonedAnimalInsertForm form) {
        return new Adopt(
                form.getMNumber(),
                abandonedAnimalDao.selectByPkMax(),
                form.getResidence(),
                form.getMaritalStatus(),
                form.getJob(),
                "완료"
        );
    }

    private TempPet toInsertTemp(AbandonedAnimalInsertForm form) {
        return new TempPet(
                abandonedAnimalDao.selectByPkMax(),
                form.getMNumber(),
                form.getTempDate(),
                form.getTempPeriod(),
                form.getResidence(),
                form.getMaritalStatus(),
                form.getJob(),
                "완료"
        );
    }

    private String getFullPath(String filename, String filePath) {
        return filePath + filename;
    }

    private String extractExt(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");

        return originalFilename.substring(position + 1);
    }

    private AbandonedAnimalDetailForm toDetailForm(AbandonedAnimal abAnimal) {
        return new AbandonedAnimalDetailForm(
                abAnimal.getAbNumber(),
                abAnimal.getSNumber(),
                abAnimal.getAge(),
                abAnimal.getWeight(),
                abAnimal.getGender(),
                abAnimal.getName(),
                abAnimal.getSpecies(),
                abAnimal.getKind(),
                abAnimal.getLocation(),
                abAnimal.getUniqueness(),
                abAnimal.getDescription(),
                abAnimal.getAnimalState(),
                abAnimal.getImgPath(),
                abAnimal.getAdmissionDate(),
                getShelter(abAnimal.getAbNumber()).getGroupName(),
                getShelter(abAnimal.getAbNumber()).getLocation(),
                getShelter(abAnimal.getAbNumber()).getPhone()
        );
    }

    private Shelter getShelter(int pk) {
        return abandonedAnimalDao.selectShelterByPk(pk);
    }

    private AbandonedAnimalUpdateForm toUpdateForm(AbandonedAnimal abAnimal) {
        return new AbandonedAnimalUpdateForm(
                abAnimal.getAbNumber(),
                abAnimal.getSNumber(),
                abAnimal.getName(),
                abAnimal.getSpecies(),
                abAnimal.getKind(),
                abAnimal.getGender(),
                abAnimal.getAge(),
                abAnimal.getWeight(),
                abAnimal.getImgPath(),
                abAnimal.getLocation(),
                abAnimal.getAdmissionDate(),
                abAnimal.getUniqueness(),
                abAnimal.getDescription(),
                abAnimal.getAnimalState()
        );
    }

    private AbandonedAnimal toUpdate(AbandonedAnimalUpdateForm form) {
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

    private Adopt toUpdateAdopt(AbandonedAnimalUpdateForm form) {
        return new Adopt(
                form.getMNumber(),
                form.getAbNumber(),
                form.getResidence(),
                form.getMaritalStatus(),
                form.getJob(),
                "완료"
        );
    }

    private TempPet toUpdateTemp(AbandonedAnimalUpdateForm form) {
        return new TempPet(
                form.getAbNumber(),
                form.getMNumber(),
                form.getTempDate(),
                form.getTempPeriod(),
                form.getResidence(),
                form.getMaritalStatus(),
                form.getJob(),
                "완료"
        );
    }

    private void residenceList(List<String> residences) {
        residences.add("서울특별시");
        residences.add("경기도");
        residences.add("인천광역시");
        residences.add("강원도");
        residences.add("충청북도");
        residences.add("충청남도");
        residences.add("경상북도");
        residences.add("경상남도");
        residences.add("전라북도");
        residences.add("전라남도");
        residences.add("부산광역시");
        residences.add("대구광역시");
        residences.add("부산광역시");
        residences.add("울산광역시");
        residences.add("광주광역시");
        residences.add("대전광역시");
        residences.add("세종특별자치시");
        residences.add("제주특별자치도");
    }
}