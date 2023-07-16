package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.adopt_review.AdoptReview;
import kh.petmily.domain.adopt_review.form.AdoptReviewConditionForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewListForm;
import kh.petmily.mapper.AdoptReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
        List<AdoptReview> list = mapper.selectIndexBymNumber(start, end, mNumber, kindOfBoard);
        List<AdoptReviewListForm> adoptReviewListForm = new ArrayList<>();

        for (AdoptReview a : list) {
            AdoptReviewListForm ar = new AdoptReviewListForm(
                    a.getBNumber(),
                    a.getMNumber(),
                    selectName(a.getBNumber()),
                    a.getKindOfBoard(),
                    a.getTitle(),
                    a.getContent(),
                    a.getImgPath(),
                    a.getWrTime(),
                    a.getCheckPublic(),
                    a.getViewCount());

            adoptReviewListForm.add(ar);
        }

        return adoptReviewListForm;
    }

    // 관리자
    public int selectCount(String kindOfBoard) {
        return mapper.selectCount(kindOfBoard);
    }

    public List<AdoptReviewListForm> selectIndex(int start, int end, String kindOfBoard) {
        List<AdoptReview> list = mapper.selectIndex(start, end, kindOfBoard);
        List<AdoptReviewListForm> adoptReviewListForm = new ArrayList<>();

        for (AdoptReview a : list) {
            AdoptReviewListForm ar = new AdoptReviewListForm(
                    a.getBNumber(),
                    a.getMNumber(),
                    selectName(a.getBNumber()),
                    a.getKindOfBoard(),
                    a.getTitle(),
                    a.getContent(),
                    a.getImgPath(),
                    a.getWrTime(),
                    a.getCheckPublic(),
                    a.getViewCount());

            adoptReviewListForm.add(ar);
        }

        return adoptReviewListForm;
    }

    //====== 검색 추가 ======
    public int selectCountWithCondition(AdoptReviewConditionForm ac) {
        return mapper.selectCountWithCondition(ac.getKindOfBoard(), ac.getSearchType(), ac.getKeyword());
    }

    public List<AdoptReviewListForm> selectIndexWithCondition(int start, int end, AdoptReviewConditionForm ac) {
        List<AdoptReview> list = mapper.selectIndexWithCondition(start, end, ac.getKindOfBoard(), ac.getSearchType(), ac.getKeyword(), ac.getSort());
        List<AdoptReviewListForm> adoptReviewListForm = new ArrayList<>();

        for (AdoptReview a : list) {
            AdoptReviewListForm ar = new AdoptReviewListForm(
                    a.getBNumber(),
                    a.getMNumber(),
                    selectName(a.getBNumber()),
                    a.getKindOfBoard(),
                    a.getTitle(),
                    a.getContent(),
                    a.getImgPath(),
                    a.getWrTime(),
                    a.getCheckPublic(),
                    a.getViewCount());

            adoptReviewListForm.add(ar);
        }

        return adoptReviewListForm;
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    //====== 조회수 추가 ======
    public int updateViewCount(int pk) {
        return mapper.updateViewCount(pk);
    }
}