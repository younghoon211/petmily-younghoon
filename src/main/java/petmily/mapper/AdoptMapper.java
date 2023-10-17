package petmily.mapper;

import petmily.domain.adopt.Adopt;
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

    // ========== 회원 페이지 ==========
    int selectCountBymNumber(int mNumber);

    List<Adopt> selectIndexBymNumber(
            @Param("start") int start,
            @Param("end") int end,
            @Param("mNumber") int mNumber
    );

    // ========== 관리자 페이지 ==========
    void adminInsert(Adopt obj);

    int selectCount(String keyword);

    List<Adopt> selectIndex(
            @Param("start") int start,
            @Param("end") int end,
            @Param("keyword") String keyword
    );

    int selectCountByStatus(
            @Param("keyword") String keyword,
            @Param("status") String status
    );

    List<Adopt> selectIndexByStatus(
            @Param("start") int start,
            @Param("end") int end,
            @Param("keyword") String keyword,
            @Param("status") String status
    );

    void adoptApprove(int pk);

    void adoptRefuse(int pk);

    Adopt selectAllByAbNumber(int abNumber);

    Adopt selectAllCompleteByAbNumber(int abNumber);

    int selectCountAdoptedStatus(int mNumber);

    void deleteCompleteWhenUpdateAB(int abNumber);

    void deleteWaitingWhenUpdateAB(int abNumber);
}