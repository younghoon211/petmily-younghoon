package petmily.service;

import org.springframework.web.multipart.MultipartFile;
import petmily.domain.admin_form.AdminBoardConditionForm;
import petmily.domain.adopt_review.AdoptReview;
import petmily.domain.adopt_review.form.*;

import java.io.IOException;

public interface AdoptReviewService {

    void write(AdoptReviewWriteForm form);

    String storeFile(MultipartFile file, String filePath) throws IOException;

    AdoptReviewPageForm getListPage(AdoptReviewConditionForm form);

    AdoptReviewDetailForm getDetailPage(int bNumber);

    AdoptReviewPageForm getMyPost(int pageNo, int mNumber);

    AdoptReviewPageForm getAdminListPage(AdminBoardConditionForm form);

    AdoptReview getAdoptReview(int pk);

    AdoptReviewModifyForm getModifyForm(int bNumber);

    void modify(AdoptReviewModifyForm form);

    int updateViewCount(int bNumber);

    void delete(int bNumber);
}