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

    public int selectCount(AbandonedAnimalConditionForm form) {
        return mapper.selectCountWithCondition(
                form.getSpecies(),
                form.getGender(),
                form.getAnimalState(),
                form.getKeyword()
        );
    }

    public List<AbandonedAnimalListForm> selectIndex(int start, int end) {
        List<AbandonedAnimalListForm> result = new ArrayList<>();
        List<AbandonedAnimal> list = mapper.selectIndex(start, end);

        for (AbandonedAnimal ab : list) {
            AbandonedAnimalListForm li = new AbandonedAnimalListForm(
                    ab.getAbNumber(),
                    ab.getSNumber(),
                    ab.getName(),
                    ab.getSpecies(),
                    ab.getKind(),
                    ab.getGender(),
                    ab.getAge(),
                    ab.getWeight(),
                    ab.getImgPath(),
                    ab.getLocation(),
                    ab.getAdmissionDate(),
                    ab.getUniqueness(),
                    ab.getDescription(),
                    ab.getAnimalState(),
                    selectGroupName(ab.getAbNumber())
            );

            result.add(li);
        }

        return result;
    }

    public List<AbandonedAnimalListForm> selectIndex(int start, int end, AbandonedAnimalConditionForm form) {
        List<AbandonedAnimalListForm> result = new ArrayList<>();

        List<AbandonedAnimal> list = mapper.selectIndexWithCondition(
                start, end,
                form.getSpecies(),
                form.getGender(),
                form.getAnimalState(),
                form.getKeyword(),
                form.getSort()
        );

        for (AbandonedAnimal ab : list) {
            AbandonedAnimalListForm li = new AbandonedAnimalListForm(
                    ab.getAbNumber(),
                    ab.getSNumber(),
                    ab.getName(),
                    ab.getSpecies(),
                    ab.getKind(),
                    ab.getGender(),
                    ab.getAge(),
                    ab.getWeight(),
                    ab.getImgPath(),
                    ab.getLocation(),
                    ab.getAdmissionDate(),
                    ab.getUniqueness(),
                    ab.getDescription(),
                    ab.getAnimalState()
            );

            result.add(li);
        }

        return result;
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    public List<AbandonedAnimal> selectAll() {
        return mapper.selectAll();
    }

    public String selectGroupName(int pk) {
        return mapper.selectGroupName(pk);
    }

    public String selectPhone(int pk) {
        return mapper.selectPhone(pk);
    }

    public List<Shelter> selectAllShelter() {
        return mapper.selectAllShelter();
    }
}
