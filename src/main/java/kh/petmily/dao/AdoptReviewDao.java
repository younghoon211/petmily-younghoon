package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.adopt_review.AdoptReview;
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

    //====== 검색 추가 ======
    public int selectCountWithCondition(String kindOfBoard, String searchType, String keyword) {
        return mapper.selectCountWithCondition(kindOfBoard, searchType, keyword);
    }

    public List<AdoptReviewListForm> selectIndexWithCondition(int start, int end, String kindOfBoard, String searchType, String keyword, String sort) {
        List<AdoptReview> list = mapper.selectIndexWithCondition(start, end, kindOfBoard, searchType, keyword, sort);
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
                    a.getViewCount(),
                    a.getReplyCount());

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

    public List<AdoptReview> selectAll(String kindOfBoard) {
        return mapper.selectAll(kindOfBoard);
    }
}