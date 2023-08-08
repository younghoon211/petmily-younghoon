package kh.petmily.mapper;

import kh.petmily.domain.board.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    // =======BasicMapper 메소드=======
    Board selectByPk(int pk);

    void insert(Board obj);

    void update(Board obj);

    void delete(int pk);
    // ===============================

    int selectCountBymNumber(
            @Param("mNumber") int mNumber,
            @Param("kindOfBoard") String kindOfBoard
    );

    List<Board> selectIndexBymNumber(
            @Param("start") int start,
            @Param("end") int end,
            @Param("mNumber") int mNumber,
            @Param("kindOfBoard") String kindOfBoard
    );

    int selectCount(String kindOfBoard);

    List<Board> selectIndex(
            @Param("start") int start,
            @Param("end") int end,
            @Param("kindOfBoard") String kindOfBoard
    );

    int selectCountWithCondition(
            @Param("keyword") String keyword,
            @Param("condition") String condition,
            @Param("kindOfBoard") String kindOfBoard
    );

    List<Board> selectIndexWithCondition(
            @Param("start") int start,
            @Param("end") int end,
            @Param("sort") String sort,
            @Param("keyword") String keyword,
            @Param("condition") String condition,
            @Param("kindOfBoard") String kindOfBoard
    );

    String selectName(int pk);

    String selectMemberId(int pk);

    int updateViewCount(int pk);

    int selectReplyCount(int pk);
}