package petmily.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import petmily.dao.AdoptReviewDao;
import petmily.domain.admin_form.AdminBoardConditionForm;
import petmily.domain.adopt_review.AdoptReview;
import petmily.domain.adopt_review.form.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdoptReviewServiceImpl implements AdoptReviewService {

    private final AdoptReviewDao adoptReviewDao;
    private int size = 6;
    private int adminSize = 10;

    // ===================== Create =====================
    // 글쓰기
    @Override
    public void write(AdoptReviewWriteForm form) {
        AdoptReview adoptReview = toWrite(form);
        adoptReviewDao.insert(adoptReview);
    }

    // 파일 업로드
    @Override
    public String storeFile(MultipartFile file, String filePath) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        log.info("원본 filename = {}", file.getOriginalFilename());

        File storeFolder = new File(filePath);

        if (!storeFolder.exists()) {
            storeFolder.mkdir();
        }

        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String storeFileName = uuid + "." + extractExt(originalFilename); // 확장자 포함한 uuid명
        String fullPath = getFullPath(storeFileName, filePath);

        log.info("fullPath = {}", fullPath);

        file.transferTo(new File(fullPath));

        return storeFileName;
    }

    // ===================== Read =====================
    // 게시판 리스트
    @Override
    public AdoptReviewPageForm getListPage(AdoptReviewConditionForm form) {
        int total = adoptReviewDao.selectCountWithCondition(form.getKeyword(), form.getCondition());
        
        List<AdoptReviewListForm> content = adoptReviewDao.selectIndexWithCondition(
                (form.getPageNo() - 1) * size + 1, 
                (form.getPageNo() - 1) * size + size, 
                form.getSort(),
                form.getKeyword(),
                form.getCondition()
        );

        return new AdoptReviewPageForm(total, form.getPageNo(), size, content);
    }

    // 글 상세보기
    @Override
    public AdoptReviewDetailForm getDetailPage(int pk) {
        AdoptReview adoptReview = adoptReviewDao.findByPk(pk);

        return toDetailForm(adoptReview);
    }

    // 내가 쓴 게시글 (마이페이지)
    @Override
    public AdoptReviewPageForm getMyPost(int pageNo, int mNumber) {
        int total = adoptReviewDao.selectCountBymNumber(mNumber);
        
        List<AdoptReviewListForm> content = adoptReviewDao.selectIndexBymNumber(
                (pageNo - 1) * size + 1, 
                (pageNo - 1) * size + size,
                mNumber
        );

        return new AdoptReviewPageForm(total, pageNo, size, content);
    }

    // 게시판 리스트 (관리자 페이지)
    @Override
    public AdoptReviewPageForm getAdminListPage(AdminBoardConditionForm form) {
        int total = adoptReviewDao.selectCountWithCondition(form.getKeyword(), form.getCondition());
        
        List<AdoptReviewListForm> content = adoptReviewDao.selectIndexByPkDesc(
                (form.getPageNo() - 1) * adminSize + 1,
                (form.getPageNo() - 1) * adminSize + adminSize,
                form.getKeyword(),
                form.getCondition()
        );

        return new AdoptReviewPageForm(total, form.getPageNo(), adminSize, content);
    }

    @Override
    public AdoptReview getAdoptReview(int pk) {
        return adoptReviewDao.findByPk(pk);
    }

    // ===================== Update =====================
    // 수정 폼
    @Override
    public AdoptReviewModifyForm getModifyForm(int pk) {
        AdoptReview adoptReview = adoptReviewDao.findByPk(pk);

        return toModifyForm(adoptReview);
    }

    // 수정하기
    @Override
    public void modify(AdoptReviewModifyForm form) {
        AdoptReview adoptReview = toModify(form);
        adoptReviewDao.update(adoptReview);
    }

    // 조회수 업데이트
    @Override
    public int updateViewCount(int pk) {
        return adoptReviewDao.updateViewCount(pk);
    }

    // ===================== Delete =====================
    @Override
    public void delete(int pk) {
        adoptReviewDao.delete(pk);
    }


    // ===================== CRUD 끝 =====================



    // ===================== private 메소드 =====================

    private AdoptReview toWrite(AdoptReviewWriteForm form) {
        return new AdoptReview(
                form.getMNumber(),
                form.getTitle(),
                form.getContent(),
                form.getImgPath()
        );
    }

    private String extractExt(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");

        return originalFilename.substring(position + 1);
    }

    private String getFullPath(String filename, String filePath) {
        return filePath + filename;
    }

    private AdoptReviewDetailForm toDetailForm(AdoptReview adoptReview) {
        return new AdoptReviewDetailForm(
                adoptReview.getBNumber(),
                adoptReview.getMNumber(),
                getMemberName(adoptReview.getBNumber()),
                "adoptReview",
                adoptReview.getTitle(),
                adoptReview.getContent(),
                adoptReview.getImgPath(),
                adoptReview.getWrTime().format(getFormatter()),
                adoptReview.getViewCount()
        );
    }

    private String getMemberName(int pk) {
        return adoptReviewDao.selectName(pk);
    }

    private AdoptReviewModifyForm toModifyForm(AdoptReview adoptReview) {
        return new AdoptReviewModifyForm(
                adoptReview.getMNumber(),
                adoptReview.getBNumber(),
                adoptReview.getTitle(),
                adoptReview.getContent(),
                adoptReview.getImgPath(),
                adoptReview.getWrTime().format(getFormatter())
        );
    }

    private AdoptReview toModify(AdoptReviewModifyForm form) {
        return new AdoptReview(
                form.getBNumber(),
                form.getMNumber(),
                form.getTitle(),
                form.getContent(),
                form.getImgPath(),
                LocalDateTime.parse(getReplaceWrTime(form), getFormatter())
        );
    }

    private String getReplaceWrTime(AdoptReviewModifyForm form) {
        return form.getWrTime().replace("T", " ");
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}