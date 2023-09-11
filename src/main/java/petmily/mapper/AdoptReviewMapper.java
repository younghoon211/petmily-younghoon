package petmily.mapper;

import petmily.domain.adopt_review.AdoptReview;
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

    int selectCountBymNumber(int mNumber);

    List<AdoptReview> selectIndexBymNumber(
            @Param("start") int start,
            @Param("end") int end,
            @Param("mNumber") int mNumber
    );

    List<AdoptReview> selectIndexByPkDesc(
            @Param("start") int start,
            @Param("end") int end,
            @Param("keyword") String keyword,
            @Param("condition") String condition
    );

    int selectCountWithCondition(
            @Param("keyword") String keyword,
            @Param("condition") String condition
    );

    List<AdoptReview> selectIndexWithCondition(
            @Param("start") int start,
            @Param("end") int end,
            @Param("sort") String sort,
            @Param("keyword") String keyword,
            @Param("condition") String condition
    );

    String selectMemberId(int pk);

    String selectName(int pk);

    int updateViewCount(int pk);
}