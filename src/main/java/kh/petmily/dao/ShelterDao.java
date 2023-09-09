package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.shelter.Shelter;
import kh.petmily.domain.admin_form.ShelterListForm;
import kh.petmily.mapper.ShelterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShelterDao implements BasicDao {

    private final ShelterMapper mapper;

    @Override
    public Shelter findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((Shelter) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((Shelter) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }

    public int selectCount(String keyword) {
        return mapper.selectCount(keyword);
    }

    public List<ShelterListForm> selectIndex(int start, int end, String keyword) {
        List<ShelterListForm> shelterListForms = new ArrayList<>();
        List<Shelter> shelters = mapper.selectIndex(start, end, keyword);

        for (Shelter shelter : shelters) {
            ShelterListForm listForm = new ShelterListForm(
                    shelter.getSNumber(),
                    shelter.getGroupName(),
                    shelter.getLocation(),
                    shelter.getPhone()
            );

            shelterListForms.add(listForm);
        }

        return shelterListForms;
    }

    public List<Shelter> selectAll() {
        return mapper.selectAll();
    }
    public List<Shelter> selectAllNot0() {
        return mapper.selectAllNot0();
    }
}
