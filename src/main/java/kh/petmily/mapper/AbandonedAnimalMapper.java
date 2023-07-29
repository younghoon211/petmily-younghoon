package kh.petmily.mapper;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.shelter.Shelter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AbandonedAnimalMapper {

    // =======BasicMapper 메소드=======
    AbandonedAnimal selectByPk(int pk);

    void insert(AbandonedAnimal obj);

    void update(AbandonedAnimal obj);

    void delete(int pk);
    // ===============================

    int selectCount();

    int selectCountWithCondition(
            @Param("species") String species,
            @Param("gender") String gender,
            @Param("animalState") String animalState,
            @Param("keyword") String keyword);

    List<AbandonedAnimal> selectIndex(
            @Param("start") int start,
            @Param("end") int end);

    List<AbandonedAnimal> selectIndexWithCondition(
            @Param("start") int start,
            @Param("end") int end,
            @Param("species") String species,
            @Param("gender") String gender,
            @Param("animalState") String animalState,
            @Param("keyword") String keyword,
            @Param("sort") String sort);

    String selectName(int pk);

    List<AbandonedAnimal> selectAll();

    String selectGroupName(int pk);

    String selectPhone(int pk);

    List<Shelter> selectAllShelter();
}
