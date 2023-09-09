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

    public int selectCountBymNumber(int mNumber) {
        return mapper.selectCountBymNumber(mNumber);
    }

    public List<AdoptReviewListForm> selectIndexBymNumber(int start, int end, int mNumber) {
        List<AdoptReviewListForm> adoptReviewListForms = new ArrayList<>();
        List<AdoptReview> adoptReviews = mapper.selectIndexBymNumber(start, end, mNumber);

        addAdoptReviewListForms(adoptReviewListForms, adoptReviews);

        return adoptReviewListForms;
    }

    public int selectCountWithCondition(String keyword, String condition) {
        return mapper.selectCountWithCondition(keyword, condition);
    }

    public List<AdoptReviewListForm> selectIndexWithCondition(int start, int end, String sort, String keyword, String condition) {
        List<AdoptReviewListForm> adoptReviewListForms = new ArrayList<>();
        List<AdoptReview> adoptReviews = mapper.selectIndexWithCondition(start, end, sort, keyword, condition);

        addAdoptReviewListForms(adoptReviewListForms, adoptReviews);

        return adoptReviewListForms;
    }

    // 관리자
    public List<AdoptReviewListForm> selectIndexByPkDesc(int start, int end, String keyword, String condition) {
        List<AdoptReviewListForm> adoptReviewListForms = new ArrayList<>();
        List<AdoptReview> adoptReviews = mapper.selectIndexByPkDesc(start, end, keyword, condition);

        for (AdoptReview adoptReview : adoptReviews) {
            AdoptReviewListForm listForm = new AdoptReviewListForm(
                    adoptReview.getBNumber(),
                    adoptReview.getMNumber(),
                    selectMemberId(adoptReview.getBNumber()),
                    selectName(adoptReview.getBNumber()),
                    "입양후기",
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
                    "입양후기",
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