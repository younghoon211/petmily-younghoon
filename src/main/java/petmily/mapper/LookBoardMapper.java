package petmily.mapper;

import petmily.domain.find_board.FindBoard;
import petmily.domain.look_board.LookBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LookBoardMapper {

    // =======BasicMapper 메소드=======
    LookBoard selectByPk(int pk);

    void insert(LookBoard obj);

    void update(LookBoard obj);

    void delete(int pk);

    // =========== 회원 페이지 ===========
    String selectName(int pk);

    int selectCountWithCondition(
            @Param("species") String species,
            @Param("animalState") String animalState,
            @Param("keyword") String keyword
    );

    List<LookBoard> selectIndexWithCondition(
            @Param("start") int start,
            @Param("end") int end,
            @Param("species") String species,
            @Param("animalState") String animalState,
            @Param("keyword") String keyword,
            @Param("sort") String sort
    );

    int selectCountBymNumber(int mNumber);

    List<LookBoard> selectIndexBymNumber(
            @Param("start") int start,
            @Param("end") int end,
            @Param("mNumber") int mNumber
    );

    // =========== 매칭 시스템 ===========
    List<Integer> selectMatchedFa(LookBoard obj);

    int selectCountLookMatching(int mNumber);

    List<LookBoard> selectIndexLookMatching(
            @Param("start") int start,
            @Param("end") int end,
            @Param("mNumber") int mNumber
    );

    int selectCountFindMatchedLook(
            @Param("species") String species,
            @Param("kind") String kind,
            @Param("location") String location
    );

    List<FindBoard> selectIndexFindMatchedLook(
            @Param("start") int start,
            @Param("end") int end,
            @Param("species") String species,
            @Param("kind") String kind,
            @Param("location") String location
    );

    List<LookBoard> selectAll();

    int selectByPkMax();

    void changeState(int pk);

    void backState(int pk);

    void changeStateFind(int faNumber);

    void backStateFind(int faNumber);

    int updateViewCount(int pk);

    // =============== 관리자 페이지 ===============
    List<LookBoard> selectIndexByPkDesc(
            @Param("start") int start,
            @Param("end") int end,
            @Param("species") String species,
            @Param("animalState") String animalState,
            @Param("keyword") String keyword
    );

    String selectMemberId(int pk);
}