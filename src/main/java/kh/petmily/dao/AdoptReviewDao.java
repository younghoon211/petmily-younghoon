package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.adopt_review.AdoptReview;
import kh.petmily.domain.adopt_review.form.AdoptReviewConditionForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewListForm;
import kh.petmily.mapper.AdoptReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdoptReviewDao implements BasicDao {

    private final AdoptReviewMapper mapper;

    // =======BasicDao 메소드=======
    @Override
    public AdoptReview findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((AdoptReview) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((AdoptReview) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }
    // =======BasicDao 메소드=======

    public int selectCountBymNumber(int mNumber, String kindOfBoard) {
        return mapper.selectCountBymNumber(mNumber, kindOfBoard);
    }

    public List<AdoptReviewListForm> selectIndexBymNumber(int start, int end, int mNumber, String kindOfBoard) {
        List<AdoptReviewListForm> result = new ArrayList<>();
        List<AdoptReview> list = mapper.selectIndexBymNumber(start, end, mNumber, kindOfBoard);

        for (AdoptReview a : list) {
            AdoptReviewListForm li = new AdoptReviewListForm(
                    a.getBNumber(),
                    a.getMNumber(),
                    selectName(a.getBNumber()),
                    a.getKindOfBoard(),
                    a.getTitle(),
                    a.getContent(),
                    a.getImgPath(),
                    a.getWrTime().format(getFormatter()),
                    a.getViewCount()
            );

            result.add(li);
        }

        return result;
    }

    // 관리자
    public int selectCount(String kindOfBoard) {
        return mapper.selectCount(kindOfBoard);
    }

    public List<AdoptReviewListForm> selectIndex(int start, int end, String kindOfBoard) {
        List<AdoptReviewListForm> result = new ArrayList<>();
        List<AdoptReview> list = mapper.selectIndex(start, end, kindOfBoard);

        for (AdoptReview a : list) {
            AdoptReviewListForm li = new AdoptReviewListForm(
                    a.getBNumber(),
                    a.getMNumber(),
                    selectMemberId(a.getBNumber()),
                    selectName(a.getBNumber()),
                    a.getKindOfBoard(),
                    a.getTitle(),
                    a.getContent(),
                    a.getImgPath(),
                    a.getWrTime().format(getFormatter()),
                    a.getViewCount()
            );

            result.add(li);
        }

        return result;
    }

    //====== 검색 추가 ======
    public int selectCountWithCondition(AdoptReviewConditionForm form) {
        return mapper.selectCountWithCondition(form);
    }

    public List<AdoptReviewListForm> selectIndexWithCondition(int start, int end, AdoptReviewConditionForm form) {
        List<AdoptReviewListForm> result = new ArrayList<>();
        List<AdoptReview> list = mapper.selectIndexWithCondition(
                start, end, form.getKindOfBoard(), form.getSearchType(), form.getKeyword(), form.getSort()
        );

        for (AdoptReview a : list) {
            AdoptReviewListForm li = new AdoptReviewListForm(
                    a.getBNumber(),
                    a.getMNumber(),
                    selectName(a.getBNumber()),
                    a.getKindOfBoard(),
                    a.getTitle(),
                    a.getContent(),
                    a.getImgPath(),
                    a.getWrTime().format(getFormatter()),
                    a.getViewCount()
            );

            result.add(li);
        }

        return result;
    }

    public String selectMemberId(int pk) {
        return mapper.selectMemberId(pk);
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    public int updateViewCount(int pk) {
        return mapper.updateViewCount(pk);
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}