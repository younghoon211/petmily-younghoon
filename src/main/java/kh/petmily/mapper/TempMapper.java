package kh.petmily.mapper;

import kh.petmily.domain.abandoned_animal.AbandonedAnimal;
import kh.petmily.domain.temp.TempPet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TempMapper {

    TempPet selectByPk(int pk);

    void insert(TempPet obj);

    void update(TempPet obj);

    void delete(int pk);

    // ====== 관리자 페이지 ======

    int selectCount();

    int selectCountBymNumber(int mNumber);

    List<TempPet> selectIndex(@Param("start") int start, @Param("end") int end);

    List<TempPet> selectIndexBymNumber(@Param("start") int start, @Param("end") int end, @Param("mNumber") int mNumber);

    List<TempPet> selectIndexByStatus(@Param("start") int start, @Param("end") int end, @Param("status") String status);

    void tempApprove(int pk);

    void tempRefuse(int pk);

    List<AbandonedAnimal> selectAllExcludeTempStatus();

    void adminInsert(TempPet obj);

    void updateStatusToTemp();
}