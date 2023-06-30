package kh.petmily.service;

import kh.petmily.domain.adopt_review.form.AdoptReviewListForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewModifyForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewPageForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewWriteForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AdoptReviewService {

    public AdoptReviewPageForm getAdoptReviewPage(int pbNumber, String kindOfBoard, String searchType, String keyword, String sort);

    public AdoptReviewPageForm getAdminAdoptReviewPage(String kindOfBoard, int pageNo);

    public AdoptReviewListForm getAdoptReview(int bNumber);

    public void write(AdoptReviewWriteForm adoptReviewWriteForm);

    public AdoptReviewModifyForm getAdoptReviewModify(int bNumber);

    public void modify(AdoptReviewModifyForm modReq);

    public void delete(int bNumber);

    public String boardName(int bNumber);

    public int updateViewCount(int bNumber);

    public String storeFile(MultipartFile file, String filePath) throws IOException;

    public String findName(int mNumber);

    public AdoptReviewPageForm getAdoptReviewMyPost(int pageNo, int mNumber, String kindOfBoard);
}