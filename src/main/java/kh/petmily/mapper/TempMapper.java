package kh.petmily.mapper;

import kh.petmily.domain.temp.TempPet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TempMapper {

    // =======BasicMapper 메소드=======
    TempPet selectByPk(int pk);

    void insert(TempPet obj);

    void update(TempPet obj);

    void delete(int pk);

    // ========== 회원 페이지 ==========
    int selectCountBymNumber(int mNumber);

    List<TempPet> selectIndexBymNumber(
            @Param("start") int start,
            @Param("end") int end,
            @Param("mNumber") int mNumber);

    // ========== 관리자 페이지 ==========
    void adminInsert(TempPet obj);

    int selectCount();

    List<TempPet> selectIndex(
            @Param("start") int start,
            @Param("end") int end);

    List<TempPet> selectIndexByStatus(
            @Param("start") int start,
            @Param("end") int end,
            @Param("status") String status);

    void tempApprove(int pk);

    void tempRefuse(int pk);

    TempPet selectTempByAbNumber(int abNumber);

    void deleteCompleteWhenUpdateAB(int abNumber);

    void deleteWaitingWhenUpdateAB(int abNumber);
}