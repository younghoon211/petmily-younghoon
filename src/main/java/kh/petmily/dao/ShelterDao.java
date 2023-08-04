package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.shelter.Shelter;
import kh.petmily.domain.shelter.form.ShelterListForm;
import kh.petmily.mapper.ShelterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShelterDao implements BasicDao {

    private final ShelterMapper mapper;

    // =======BasicDao 메소드=======
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
    // =======BasicDao 메소드=======

    public int selectCount() {
        return mapper.selectCount();
    }

    public List<ShelterListForm> selectIndex(int start, int end) {
        List<ShelterListForm> result = new ArrayList<>();
        List<Shelter> list = mapper.selectIndex(start, end);

        for (Shelter s : list) {
            ShelterListForm li = new ShelterListForm(
                    s.getSNumber(),
                    s.getGroupName(),
                    s.getLocation(),
                    s.getPhone()
            );

            result.add(li);
        }

        return result;
    }

    public List<Shelter> selectAll() {
        return mapper.selectAll();
    }
}
