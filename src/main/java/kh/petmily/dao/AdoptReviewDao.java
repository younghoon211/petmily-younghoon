package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.adopt_review.AdoptReview;
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

    public int selectCountBymNumber(int mNumber, String kindOfBoard) {
        return mapper.selectCountBymNumber(mNumber, kindOfBoard);
    }

    public List<AdoptReviewListForm> selectIndexBymNumber(int start, int end, int mNumber, String kindOfBoard) {
        List<AdoptReviewListForm> adoptReviewListForms = new ArrayList<>();
        List<AdoptReview> adoptReviews = mapper.selectIndexBymNumber(start, end, mNumber, kindOfBoard);

        addAdoptReviewListForms(adoptReviewListForms, adoptReviews);

        return adoptReviewListForms;
    }

    // 관리자
    public int selectCount(String kindOfBoard) {
        return mapper.selectCount(kindOfBoard);
    }

    public List<AdoptReviewListForm> selectIndex(int start, int end, String kindOfBoard) {
        List<AdoptReviewListForm> adoptReviewListForms = new ArrayList<>();
        List<AdoptReview> adoptReviews = mapper.selectIndex(start, end, kindOfBoard);

        for (AdoptReview adoptReview : adoptReviews) {
            AdoptReviewListForm listForm = new AdoptReviewListForm(
                    adoptReview.getBNumber(),
                    adoptReview.getMNumber(),
                    selectMemberId(adoptReview.getBNumber()),
                    selectName(adoptReview.getBNumber()),
                    adoptReview.getKindOfBoard(),
                    adoptReview.getTitle(),
                    adoptReview.getContent(),
                    adoptReview.getImgPath(),
                    adoptReview.getWrTime().format(getFormatter()),
                    adoptReview.getViewCount()
            );

            adoptReviewListForms.add(listForm);
        }

        return adoptReviewListForms;
    }

    public int selectCountWithCondition(String keyword, String searchType, String kindOfBoard) {
        return mapper.selectCountWithCondition(keyword, searchType, kindOfBoard);
    }

    public List<AdoptReviewListForm> selectIndexWithCondition(int start, int end, String sort, String keyword, String searchType, String kindOfBoard) {
        List<AdoptReviewListForm> adoptReviewListForms = new ArrayList<>();
        List<AdoptReview> adoptReviews = mapper.selectIndexWithCondition(start, end, sort, keyword, searchType, kindOfBoard);

        addAdoptReviewListForms(adoptReviewListForms, adoptReviews);

        return adoptReviewListForms;
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    public int updateViewCount(int pk) {
        return mapper.updateViewCount(pk);
    }


    // =============== private 메소드 ===============

    private String selectMemberId(int pk) {
        return mapper.selectMemberId(pk);
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    private void addAdoptReviewListForms(List<AdoptReviewListForm> adoptReviewListForms, List<AdoptReview> adoptReviews) {
        for (AdoptReview adoptReview : adoptReviews) {
            AdoptReviewListForm listForm = new AdoptReviewListForm(
                    adoptReview.getBNumber(),
                    adoptReview.getMNumber(),
                    selectName(adoptReview.getBNumber()),
                    adoptReview.getKindOfBoard(),
                    adoptReview.getTitle(),
                    adoptReview.getContent(),
                    adoptReview.getImgPath(),
                    adoptReview.getWrTime().format(getFormatter()),
                    adoptReview.getViewCount()
            );

            adoptReviewListForms.add(listForm);
        }
    }
}