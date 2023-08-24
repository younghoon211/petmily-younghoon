package kh.petmily.mapper;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.adopt.Adopt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdoptMapper {

    // =======BasicMapper 메소드=======
    Adopt selectByPk(int pk);

    void insert(Adopt obj);

    void update(Adopt obj);

    void delete(int pk);
    // ===============================

    // ========== 관리자 페이지 ==========
    int selectCount();

    int selectCountBymNumber(int mNumber);

    List<Adopt> selectIndex(
            @Param("start") int start,
            @Param("end") int end);

    List<Adopt> selectIndexBymNumber(
            @Param("start") int start,
            @Param("end") int end,
            @Param("mNumber") int mNumber);

    List<Adopt> selectIndexByStatus(
            @Param("start") int start,
            @Param("end") int end,
            @Param("status") String status);

    void adoptApprove(int pk);

    void adoptRefuse(int pk);

    List<AbandonedAnimal> selectAllExcludeAdopt();

    void adminInsert(Adopt obj);

    void updateStatusToAdopt();
}