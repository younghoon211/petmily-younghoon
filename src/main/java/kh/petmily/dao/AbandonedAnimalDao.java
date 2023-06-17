package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalListForm;
import kh.petmily.domain.pet.Pet;
import kh.petmily.mapper.AbandonedAnimalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AbandonedAnimalDao implements BasicDao {

    private final AbandonedAnimalMapper mapper;

    // =======BasicDao 메소드=======
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
    // =======BasicDao 메소드=======

    public int selectCount() {
        return mapper.selectCount();
    }

    public int selectCount(String species, String gender, String animalState, String keyword) {
        return mapper.selectCountWithCondition(species, gender, animalState, keyword);
    }

    public List<AbandonedAnimalListForm> selectIndex(int start, int end) {
        List<AbandonedAnimal> list = mapper.selectIndex(start, end);
        List<AbandonedAnimalListForm> abandonedAnimalListForm = new ArrayList<>();

        for (AbandonedAnimal ab : list) {
            AbandonedAnimalListForm form = new AbandonedAnimalListForm(ab.getAbNumber(), ab.getSNumber(), ab.getName(), ab.getSpecies(), ab.getKind(), ab.getGender(), ab.getAge(), ab.getWeight(), ab.getImgPath(), ab.getLocation(), ab.getAdmissionDate(), ab.getUniqueness(), ab.getDescription(), ab.getAnimalState(), ab.getSort());
            abandonedAnimalListForm.add(form);
        }

        return abandonedAnimalListForm;
    }

    public List<AbandonedAnimalListForm> selectIndex(int start, int end, String species, String gender, String animalState, String keyword, String sort) {
        List<AbandonedAnimal> list = mapper.selectIndexWithCondition(start, end, species, gender, animalState, keyword, sort);

        List<AbandonedAnimalListForm> abandonedAnimalListForm = new ArrayList<>();

        for (AbandonedAnimal ab : list) {
            AbandonedAnimalListForm form = new AbandonedAnimalListForm(ab.getAbNumber(), ab.getSNumber(), ab.getName(), ab.getSpecies(), ab.getKind(), ab.getGender(), ab.getAge(), ab.getWeight(), ab.getImgPath(), ab.getLocation(), ab.getAdmissionDate(), ab.getUniqueness(), ab.getDescription(), ab.getAnimalState(), ab.getSort());
            abandonedAnimalListForm.add(form);
        }

        return abandonedAnimalListForm;
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    public List<AbandonedAnimal> selectAll() {
        return mapper.selectAll();
    }

    public int selectsNumber(int pk) {
        return mapper.selectsNumber(pk);
    }

    public int selectPetCount() {
        return mapper.selectPetCount();
    }

    public List<Pet> selectPetIndex(int start, int end) {
        return mapper.selectPetIndex(start, end);
    }

    public void insertPet(Pet pet) {
        mapper.insertPet(pet);
    }

    public void updatePet(Pet pet) {
        mapper.updatePet(pet);
    }

    public void deletePet(int cpNumber) {
        mapper.deletePet(cpNumber);
    }
}
