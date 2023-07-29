package kh.petmily.mapper;

import kh.petmily.domain.adopt_review.AdoptReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdoptReviewMapper {

    // =======BasicMapper 메소드=======
    AdoptReview selectByPk(int pk);

    void insert(AdoptReview obj);

    void update(AdoptReview obj);

    void delete(int pk);
    // ===============================

    int selectCountBymNumber(
            @Param("mNumber") int mNumber,
            @Param("kindOfBoard") String kindOfBoard);

    List<AdoptReview> selectIndexBymNumber(
            @Param("start") int start,
            @Param("end") int end,
            @Param("mNumber") int mNumber,
            @Param("kindOfBoard") String kindOfBoard);

    int selectCount(String kindOfBoard);

    List<AdoptReview> selectIndex(
            @Param("start") int start,
            @Param("end") int end,
            @Param("kindOfBoard") String kindOfBoard);

    int selectCountWithCondition(
            @Param("kindOfBoard") String kindOfBoard,
            @Param("searchType") String searchType,
            @Param("keyword") String keyword);

    List<AdoptReview> selectIndexWithCondition(
            @Param("start") int start,
            @Param("end") int end,
            @Param("kindOfBoard") String kindOfBoard,
            @Param("searchType") String searchType,
            @Param("keyword") String keyword,
            @Param("sort") String sort);

    String selectMemberId(int pk);

    String selectName(int pk);

    int updateViewCount(int pk);
}