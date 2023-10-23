package petmily.dao;

import petmily.domain.DomainObj;
import petmily.domain.shelter.Shelter;
import petmily.domain.admin_form.ShelterListForm;
import petmily.mapper.ShelterMapper;
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

    // 조건부 검색 - 총 보호소 수 (페이징)
    public int selectCount(String keyword) {
        return mapper.selectCount(keyword);
    }

    // 조건부 검색 - 보호소 index
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

    // 보호소 리스트
    public List<Shelter> selectAll() {
        return mapper.selectAll();
    }
}
