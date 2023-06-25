package kh.petmily.mapper;

import kh.petmily.domain.board.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    Board selectByPk(int pk);

    void insert(Board board);

    void update(Board board);

    void delete(int pk);

    int selectCountWithCondition(@Param("kindOfBoard") String kindOfBoard, @Param("condition") String condition, @Param("keyword") String keyword);

    List<Board> selectIndexWithCondition(@Param("start") int start, @Param("end") int end, @Param("kindOfBoard") String kindOfBoard, @Param("condition") String condition, @Param("keyword") String keyword, @Param("sort") String sort);

    String selectName(int pk);

    int updateViewCount(int pk);

    List<Board> selectAll(String kindOfBoard);
}