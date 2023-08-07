package kh.petmily.mapper;

import kh.petmily.domain.look_board.LookBoard;
import kh.petmily.domain.look_board.form.LookBoardConditionForm;
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
    // ===============================

    int selectCount();

    int selectCountWithCondition(LookBoardConditionForm form);

    int selectCountBymNumber(int mNumber);

    List<LookBoard> selectIndex(
            @Param("start") int start,
            @Param("end") int end);

    List<LookBoard> selectIndexBymNumber(
            @Param("start") int start,
            @Param("end") int end,
            @Param("mNumber") int mNumber);

    List<LookBoard> selectIndexWithCondition(
            @Param("start") int start,
            @Param("end") int end,
            @Param("species") String species,
            @Param("animalState") String animalState,
            @Param("keyword") String keyword,
            @Param("sort") String sort);

    String selectMemberId(int pk);

    String selectName(int pk);

    int updateViewCount(int pk);

    List<Integer> selectMatchedFa(LookBoard obj);

    void changeState(int pk);

    void backState(int pk);

    void changeStateFind(int faNumber);

    void backStateFind(int faNumber);

    int selectByPkMax();
}