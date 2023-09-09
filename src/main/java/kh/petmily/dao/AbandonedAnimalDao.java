package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalConditionForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalListForm;
import kh.petmily.domain.adopt.Adopt;
import kh.petmily.domain.shelter.Shelter;
import kh.petmily.domain.temp.TempPet;
import kh.petmily.mapper.AbandonedAnimalMapper;
import kh.petmily.mapper.AdoptMapper;
import kh.petmily.mapper.ShelterMapper;
import kh.petmily.mapper.TempMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AbandonedAnimalDao implements BasicDao {

    private final AbandonedAnimalMapper mapper;
    private final AdoptMapper adoptMapper;
    private final TempMapper tempMapper;
    private final ShelterMapper shelterMapper;

    @Override
    public AbandonedAnimal findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((AbandonedAnimal) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((AbandonedAnimal) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }

    // ========================= 회원 페이지 ========================
    // 조건부 검색 총 게시글 수 조회
    public int selectCountWithCondition(AbandonedAnimalConditionForm form) {
        return mapper.selectCountWithCondition(form);
    }

    // 조건부 검색 리스트 페이지 index
    public List<AbandonedAnimalListForm> selectIndexWithCondition(int start, int end, AbandonedAnimalConditionForm form) {
        List<AbandonedAnimalListForm> abandonedAnimalListForms = new ArrayList<>();

        List<AbandonedAnimal> abandonedAnimals = mapper.selectIndexWithCondition(
                start, end,
                form.getSpecies(),
                form.getGender(),
                form.getAnimalState(),
                form.getKeyword(),
                form.getSort()
        );

        for (AbandonedAnimal abAnimal : abandonedAnimals) {
            AbandonedAnimalListForm listForm = new AbandonedAnimalListForm(
                    abAnimal.getAbNumber(),
                    abAnimal.getName(),
                    abAnimal.getImgPath(),
                    abAnimal.getLocation(),
                    abAnimal.getAdmissionDate()
            );

            abandonedAnimalListForms.add(listForm);
        }

        return abandonedAnimalListForms;
    }

    // 입양 완료 리스트 : 조건부 검색 총 게시글 수 조회
    public int selectCountAdopted(AbandonedAnimalConditionForm form) {
        return mapper.selectCountAdopted(form);
    }

    // 입양 완료 리스트 : 조건부 검색 리스트 페이지 index
    public List<AbandonedAnimalListForm> selectIndexAdopted(int start, int end, AbandonedAnimalConditionForm form) {
        List<AbandonedAnimalListForm> adoptedListForms = new ArrayList<>();

        List<AbandonedAnimal> abandonedAnimals = mapper.selectIndexAdopted(
                start, end,
                form.getSpecies(),
                form.getGender(),
                form.getKeyword(),
                form.getSort()
        );

        for (AbandonedAnimal abAnimal : abandonedAnimals) {
            AbandonedAnimalListForm listForm = new AbandonedAnimalListForm(
                    abAnimal.getAbNumber(),
                    abAnimal.getName(),
                    abAnimal.getSpecies(),
                    abAnimal.getKind(),
                    abAnimal.getGender(),
                    abAnimal.getAge(),
                    abAnimal.getWeight(),
                    abAnimal.getImgPath()
            );

            adoptedListForms.add(listForm);
        }

        return adoptedListForms;
    }

    // ======================== 관리자 페이지 ==========================
    // 총 게시글 수 조회
    public int selectCount(AbandonedAnimalConditionForm form) {
        return mapper.selectCount(form);
    }

    // 리스트 페이지 index
    public List<AbandonedAnimalListForm> selectIndex(AbandonedAnimalConditionForm form, int start, int end) {
        List<AbandonedAnimalListForm> abandonedAnimalListForms = new ArrayList<>();
        List<AbandonedAnimal> abandonedAnimals = mapper.selectIndex(
                start, end, form.getSpecies(), form.getGender(), form.getAnimalState(), form.getKeyword()
        );

        for (AbandonedAnimal abAnimal : abandonedAnimals) {
            AbandonedAnimalListForm listForm = new AbandonedAnimalListForm(
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
                    abAnimal.getAnimalState(),
                    selectShelterByPk(abAnimal.getAbNumber()).getGroupName(),
                    selectShelterByPk(abAnimal.getAbNumber()).getLocation()
            );

            abandonedAnimalListForms.add(listForm);
        }

        return abandonedAnimalListForms;
    }

    // 유기동물 리스트
    public List<AbandonedAnimal> selectAll() {
        return mapper.selectAll();
    }

    // 보호중인 유기동물 리스트
    public List<AbandonedAnimal> selectAllOnlyProtect() {
        return mapper.selectAllOnlyProtect();
    }

    // 가장 최신 업로드 된 pk 조회
    public int selectByPkMax() {
        return mapper.selectByPkMax();
    }

    // pk로 보호소 조회
    public Shelter selectShelterByPk(int pk) {
        return shelterMapper.selectAllByAbNumber(pk);
    }

    // pk로 입양 조회
    public Adopt selectAdoptByPk(int pk) {
        return adoptMapper.selectAdoptByAbNumber(pk);
    }

    // pk로 임보 조회
    public TempPet selectTempByPk(int pk) {
        return tempMapper.selectTempByAbNumber(pk);
    }

    // ============================= 입양 ===============================
    // 입양 승인 대기중인 유기동물 리스트
    public List<AbandonedAnimal> selectAllAdoptWait() {
        return mapper.selectAllAdoptWait();
    }

    // 입양 '완료' 유기동물 리스트
    public List<AbandonedAnimal> selectAllAdoptComplete() {
        return mapper.selectAllAdoptComplete();
    }

    // 입양 '완료'인 유기동물 '입양'으로 업데이트
    public void updateToAdopt() {
        mapper.updateToAdopt();
    }

    // 입양 '처리중' 또는 '거절'인 유기동물 '보호'로 업데이트
    public void updateToProtectInAdopt() {
        mapper.updateToProtectInAdopt();
    }

    // 입양 '완료' 삭제 시 유기동물 상태 '입양'->'보호'
    public void updateToProtectForDeleteInAdopt(int pk) {
        mapper.updateToProtectForDeleteInAdopt(pk);
    }

    // ============================= 임시보호 ===============================
    // 임시보호 상태:'처리중'인 유기동물 리스트
    public List<AbandonedAnimal> selectAllTempWait() {
        return mapper.selectAllTempWait();
    }

    // 임시보호 중인 유기동물 리스트
    public List<AbandonedAnimal> selectAllTempComplete() {
        return mapper.selectAllTempComplete();
    }

    // 임보 '완료'인 유기동물 '임보'로 업데이트
    public void updateToTemp() {
        mapper.updateToTemp();
    }

    // 임보 '처리중' 또는 '거절'인 유기동물 '보호'로 업데이트
    public void updateToProtectInTemp() {
        mapper.updateToProtectInTemp();
    }

    // 임시보호 '완료' 삭제 시 유기동물 상태 '임보'->'보호'
    public void updateToProtectForDeleteInTemp(int pk) {
        mapper.updateToProtectForDeleteInTemp(pk);
    }
}
