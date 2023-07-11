package kh.petmily.service;

import kh.petmily.domain.adopt_review.form.AdoptReviewListForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewModifyForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewPageForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewWriteForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AdoptReviewService {

    AdoptReviewPageForm getAdoptReviewPage(int pbNumber, String kindOfBoard, String searchType, String keyword, String sort);

    AdoptReviewPageForm getAdminAdoptReviewPage(String kindOfBoard, int pageNo);

    AdoptReviewListForm getAdoptReview(int bNumber);

    void write(AdoptReviewWriteForm adoptReviewWriteForm);

    AdoptReviewModifyForm getAdoptReviewModify(int bNumber);

    void modify(AdoptReviewModifyForm modReq);

    void delete(int bNumber);

    String boardName(int bNumber);

    int updateViewCount(int bNumber);

    String storeFile(MultipartFile file, String filePath) throws IOException;

    String findName(int mNumber);

    AdoptReviewPageForm getAdoptReviewMyPost(int pageNo, int mNumber, String kindOfBoard);
}