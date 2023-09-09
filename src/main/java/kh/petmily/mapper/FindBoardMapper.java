package kh.petmily.mapper;

import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.look_board.LookBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FindBoardMapper {

    FindBoard selectByPk(int pk);

    void insert(FindBoard obj);

    void update(FindBoard obj);

    void delete(int pk);


    // =========== 일반 회원 페이지 ===========
    String selectName(int pk);

    int updateViewCount(int pk);

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


    // ========= 마이페이지 - 내가 쓴 게시글 =========
    int selectCountBymNumber(int mNumber);

    List<FindBoard> selectIndexBymNumber(
            @Param("start") int start,
            @Param("end") int end,
            @Param("mNumber") int mNumber
    );


    // ============== 매칭 시스템  ==============
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

    // =============== 관리자 ===============
    List<FindBoard> selectIndexByPkDesc(
            @Param("start") int start,
            @Param("end") int end,
            @Param("species") String species,
            @Param("animalState") String animalState,
            @Param("keyword") String keyword
    );

    String selectMemberId(int pk);
}