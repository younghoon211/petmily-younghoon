package kh.petmily.mapper;

import kh.petmily.domain.find_board.FindBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FindBoardMapper {

    FindBoard selectByPk(int pk);

    void insert(FindBoard obj);

    void update(FindBoard obj);

    void delete(int pk);

    int selectCountBymNumber(int mNumber);

    List<FindBoard> selectIndexBymNumber(@Param("start") int start, @Param("end") int end, @Param("mNumber") int mNumber);

    int selectCountWithCondition(@Param("species") String species, @Param("animalState") String animalState, @Param("keyword") String keyword);

    List<FindBoard> selectIndexWithCondition(@Param("start") int start, @Param("end") int end, @Param("species") String species, @Param("animalState") String animalState, @Param("keyword") String keyword, @Param("sort") String sort);

    int selectCount();

    List<FindBoard> selectIndex(@Param("start") int start, @Param("end") int end);

    String selectName(int pk);

    int updateViewCount(int pk);

    List<Integer> selectMatchedLa(FindBoard obj);

    void changeState(int faNumber);

    void backState(int faNumber);

    void changeStateLook(int laNumber);

    void backStateLook(int laNumber);

    int selectByPkMax();

    int selectMemberCount(@Param("mNumber") int mNumber, @Param("matched") String matched);

    List<FindBoard> selectMemberIndex(@Param("start") int start, @Param("end") int end, @Param("mNumber") int mNumber, @Param("matched") String matched);
}