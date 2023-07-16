package kh.petmily.service;

import kh.petmily.domain.adopt_review.form.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AdoptReviewService {

    AdoptReviewPageForm getAdoptReviewPage(AdoptReviewConditionForm adoptReviewConditionForm);

    AdoptReviewPageForm getAdminAdoptReviewPage(String kindOfBoard, int pageNo);

    AdoptReviewDetailForm getAdoptReview(int bNumber);

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