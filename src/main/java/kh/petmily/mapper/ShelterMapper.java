package kh.petmily.mapper;

import kh.petmily.domain.shelter.Shelter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShelterMapper {

    // =======BasicMapper 메소드=======
    Shelter selectByPk(int pk);

    void insert(Shelter shelter);

    void update(Shelter shelter);

    void delete(int pk);
    // ===============================

    int selectCount(String keyword);

    List<Shelter> selectIndex(
            @Param("start") int start,
            @Param("end") int end,
            @Param("keyword") String keyword
    );

    List<Shelter> selectAll();

    List<Shelter> selectAllNot0();

    Shelter selectAllByAbNumber(int abNumber);
}
