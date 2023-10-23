package petmily.mapper;

import petmily.domain.board.Board;
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

    // ========== 회원 페이지 ==========
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

    int updateViewCount(int pk);

    // ========== 관리자 페이지 ==========
    List<Board> selectIndexByPkDesc(
            @Param("start") int start,
            @Param("end") int end,
            @Param("keyword") String keyword,
            @Param("condition") String condition,
            @Param("kindOfBoard") String kindOfBoard
    );

    // 회원 페이지 DAO private 메소드
    String selectName(int pk);

    int selectReplyCount(int pk);
}