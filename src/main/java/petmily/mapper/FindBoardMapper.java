package petmily.mapper;

import petmily.domain.find_board.FindBoard;
import petmily.domain.look_board.LookBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FindBoardMapper {

    // =======BasicMapper 메소드=======
    FindBoard selectByPk(int pk);

    void insert(FindBoard obj);

    void update(FindBoard obj);

    void delete(int pk);

    // =========== 회원 페이지 ===========
    String selectName(int pk);

    int selectCountWithCondition(
            @Param("species") String species,
            @Param("animalState") String animalState,
            @Param("keyword") String keyword
    );

    List<FindBoard> selectIndexWithCondition(
            @Param("start") int start,
            @Param("end") int end,
            @Param("species") String species,
            @Param("animalState") String animalState,
            @Param("keyword") String keyword,
            @Param("sort") String sort
    );

    int selectCountBymNumber(int mNumber);

    List<FindBoard> selectIndexBymNumber(
            @Param("start") int start,
            @Param("end") int end,
            @Param("mNumber") int mNumber
    );

    // =========== 매칭 시스템 ===========
    List<Integer> selectMatchedLa(FindBoard obj);

    int selectCountFindMatching(int mNumber);

    List<FindBoard> selectIndexFindMatching(
            @Param("start") int start,
            @Param("end") int end,
            @Param("mNumber") int mNumber
    );

    int selectCountLookMatchedFind(
            @Param("species") String species,
            @Param("kind") String kind,
            @Param("location") String location
    );

    List<LookBoard> selectIndexLookMatchedFind(
            @Param("start") int start,
            @Param("end") int end,
            @Param("species") String species,
            @Param("kind") String kind,
            @Param("location") String location
    );

    List<FindBoard> selectAll();

    int selectByPkMax();

    void changeState(int pk);

    void backState(int pk);

    void changeStateLook(int laNumber);

    void backStateLook(int laNumber);

    int updateViewCount(int pk);

    // =============== 관리자 페이지 ===============
    List<FindBoard> selectIndexByPkDesc(
            @Param("start") int start,
            @Param("end") int end,
            @Param("species") String species,
            @Param("animalState") String animalState,
            @Param("keyword") String keyword
    );

    String selectMemberId(int pk);
}