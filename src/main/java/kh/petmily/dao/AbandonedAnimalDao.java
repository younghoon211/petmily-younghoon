package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalConditionForm;
import kh.petmily.domain.abandoned_animal.form.AbandonedAnimalListForm;
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

    public int selectCount(AbandonedAnimalConditionForm ac) {
        return mapper.selectCountWithCondition(ac.getSpecies(), ac.getGender(), ac.getAnimalState(), ac.getKeyword());
    }

    public List<AbandonedAnimalListForm> selectIndex(int start, int end) {
        List<AbandonedAnimal> list = mapper.selectIndex(start, end);
        List<AbandonedAnimalListForm> abandonedAnimalListForm = new ArrayList<>();

        for (AbandonedAnimal ab : list) {
            AbandonedAnimalListForm form = new AbandonedAnimalListForm(ab.getAbNumber(), ab.getSNumber(), ab.getName(), ab.getSpecies(), ab.getKind(), ab.getGender(), ab.getAge(), ab.getWeight(), ab.getImgPath(), ab.getLocation(), ab.getAdmissionDate(), ab.getUniqueness(), ab.getDescription(), ab.getAnimalState());
            abandonedAnimalListForm.add(form);
        }

        return abandonedAnimalListForm;
    }

    public List<AbandonedAnimalListForm> selectIndex(int start, int end, AbandonedAnimalConditionForm ac) {
        List<AbandonedAnimal> list = mapper.selectIndexWithCondition(start, end, ac.getSpecies(), ac.getGender(), ac.getAnimalState(), ac.getKeyword(), ac.getSort());

        List<AbandonedAnimalListForm> abandonedAnimalListForm = new ArrayList<>();

        for (AbandonedAnimal ab : list) {
            AbandonedAnimalListForm form = new AbandonedAnimalListForm(ab.getAbNumber(), ab.getSNumber(), ab.getName(), ab.getSpecies(), ab.getKind(), ab.getGender(), ab.getAge(), ab.getWeight(), ab.getImgPath(), ab.getLocation(), ab.getAdmissionDate(), ab.getUniqueness(), ab.getDescription(), ab.getAnimalState());
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

    public String selectGroupName(int abNumber) {
        return mapper.selectGroupName(abNumber);
    }

    public String selectPhone(int abNumber) {
        return mapper.selectPhone(abNumber);
    }
}
