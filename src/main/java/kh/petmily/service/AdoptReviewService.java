package kh.petmily.service;

import kh.petmily.domain.adopt_review.AdoptReview;
import kh.petmily.domain.adopt_review.form.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AdoptReviewService {

    void write(AdoptReviewWriteForm form);

    String storeFile(MultipartFile file, String filePath) throws IOException;

    AdoptReviewPageForm getListPage(AdoptReviewConditionForm form);

    AdoptReviewDetailForm getDetailPage(int bNumber);

    AdoptReviewPageForm getMyPost(int pageNo, int mNumber, String kindOfBoard);

    AdoptReviewPageForm getAdminListPage(String kindOfBoard, int pageNo);

    AdoptReview getAdoptReview(int pk);

    AdoptReviewModifyForm getModifyForm(int bNumber);

    void modify(AdoptReviewModifyForm form);

    int updateViewCount(int bNumber);

    void delete(int bNumber);
}