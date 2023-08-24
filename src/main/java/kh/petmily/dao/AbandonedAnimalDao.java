package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalConditionForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalListForm;
import kh.petmily.domain.shelter.Shelter;
import kh.petmily.mapper.AbandonedAnimalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AbandonedAnimalDao implements BasicDao {

    private final AbandonedAnimalMapper mapper;

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

    public int selectCount() {
        return mapper.selectCount();
    }

    public int selectCount(AbandonedAnimalConditionForm conditionForm) {
        return mapper.selectCountWithCondition(conditionForm);
    }

    public List<AbandonedAnimalListForm> selectIndex(int start, int end) {
        List<AbandonedAnimalListForm> abandonedAnimalListForms = new ArrayList<>();
        List<AbandonedAnimal> abandonedAnimals = mapper.selectIndex(start, end);

        addListForms(abandonedAnimalListForms, abandonedAnimals);

        return abandonedAnimalListForms;
    }

    public List<AbandonedAnimalListForm> selectIndex(int start, int end, AbandonedAnimalConditionForm conditionForm) {
        List<AbandonedAnimalListForm> abandonedAnimalListForms = new ArrayList<>();

        List<AbandonedAnimal> abandonedAnimals = mapper.selectIndexWithCondition(
                start, end,
                conditionForm.getSpecies(),
                conditionForm.getGender(),
                conditionForm.getAnimalState(),
                conditionForm.getKeyword(),
                conditionForm.getSort()
        );

        addListForms(abandonedAnimalListForms, abandonedAnimals);

        return abandonedAnimalListForms;
    }

    public List<AbandonedAnimal> selectAll() {
        return mapper.selectAll();
    }

    private void addListForms(List<AbandonedAnimalListForm> abandonedAnimalListForms, List<AbandonedAnimal> abandonedAnimals) {
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
    }

    public Shelter selectShelterByPk(int pk) {
        return mapper.selectShelterByPk(pk);
    }
}
