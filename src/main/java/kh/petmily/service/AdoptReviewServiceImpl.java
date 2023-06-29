package kh.petmily.service;

import kh.petmily.dao.AdoptReviewDao;
import kh.petmily.dao.MemberDao;
import kh.petmily.domain.admin.form.AdminBoardListForm;
import kh.petmily.domain.adopt_review.AdoptReview;
import kh.petmily.domain.adopt_review.form.AdoptReviewListForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewModifyForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewPageForm;
import kh.petmily.domain.adopt_review.form.AdoptReviewWriteForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdoptReviewServiceImpl implements AdoptReviewService {

    private final AdoptReviewDao adoptReviewDao;
    private final MemberDao memberDao;
    private int size = 6;

    @Override
    public AdoptReviewPageForm getAdoptReviewPage(int pageNo, String kindOfBoard, String searchType, String keyword, String sort) {
        int total = adoptReviewDao.selectCountWithCondition(kindOfBoard, searchType, keyword);
        List<AdoptReviewListForm> content = adoptReviewDao.selectIndexWithCondition((pageNo - 1) * size + 1, (pageNo - 1) * size + size, kindOfBoard, searchType, keyword, sort);

        return new AdoptReviewPageForm(total, pageNo, size, content);
    }

    @Override
    public AdoptReviewListForm getAdoptReview(int bNumber) {
        AdoptReview arForm = adoptReviewDao.findByPk(bNumber);

        return new AdoptReviewListForm(
                arForm.getBNumber(),
                arForm.getMNumber(),
                boardName(arForm.getBNumber()),
                arForm.getKindOfBoard(),
                arForm.getTitle(),
                arForm.getContent(),
                arForm.getImgPath(),
                arForm.getWrTime(),
                arForm.getCheckPublic(),
                arForm.getViewCount()
        );
    }

    @Override
    public void write(AdoptReviewWriteForm adoptReviewWriteForm) {
        AdoptReview adoptReview = toAdoptReview(adoptReviewWriteForm);
        adoptReviewDao.insert(adoptReview);
    }

    @Override
    public AdoptReviewModifyForm getAdoptReviewModify(int bNumber) {
        AdoptReview adoptReview = adoptReviewDao.findByPk(bNumber);
        AdoptReviewModifyForm modReq = toAdoptReviewModify(adoptReview);
        return modReq;
    }

    @Override
    public void modify(AdoptReviewModifyForm modReq) {
        AdoptReview adoptReview = toAdoptReviewModifyForm(modReq);

        log.info("adoptReview.getImgPath() = {} ", adoptReview.getImgPath());

        adoptReviewDao.update(adoptReview);
    }

    @Override
    public void delete(int bNumber) {
        adoptReviewDao.delete(bNumber);
    }

    @Override
    public String boardName(int bNumber) {
        return adoptReviewDao.selectName(bNumber);
    }

    @Override
    public int updateViewCount(int bNumber) {
        return adoptReviewDao.updateViewCount(bNumber);
    }

    @Override
    public AdoptReviewPageForm getAdoptReviewMyPost(int pageNo, int mNumber, String kindOfBoard) {
        int total = adoptReviewDao.selectCountBymNumber(mNumber, kindOfBoard);
        List<AdoptReviewListForm> content = adoptReviewDao.selectIndexBymNumber((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber, kindOfBoard);

        return new AdoptReviewPageForm(total, pageNo, size, content);
    }

    private AdoptReview toAdoptReview(AdoptReviewWriteForm req) {
        return new AdoptReview(
                req.getmNumber(),
                req.getKindOfBoard(),
                req.getTitle(),
                req.getContent(),
                req.getFullPath(),
                req.getCheckPublic()
        );
    }

    private AdoptReviewModifyForm toAdoptReviewModify(AdoptReview adoptReview) {
        return new AdoptReviewModifyForm(adoptReview.getBNumber(), adoptReview.getTitle(), adoptReview.getContent(), adoptReview.getCheckPublic());
    }

    private AdoptReview toAdoptReviewModifyForm(AdoptReviewModifyForm modReq) {
        return new AdoptReview(
                modReq.getBNumber(),
                modReq.getTitle(),
                modReq.getContent(),
                "Y",
                modReq.getFullPath());
    }

    private String getFullPath(String filename, String filePath) {
        return filePath + filename;
    }

    private String extractExt(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");

        return originalFilename.substring(position + 1);
    }

    public String storeFile(MultipartFile file, String filePath) throws IOException {
        log.info("storeFile = {} ", file.getOriginalFilename());

        if (file.isEmpty()) {
            return null;
        }

        File storeFolder = new File(filePath);

        if (!storeFolder.exists()) {
            storeFolder.mkdir();
        }

        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String storeFileName = uuid + "." + extractExt(originalFilename);
        String fullPath = getFullPath(storeFileName, filePath);

        log.info("fullPath = {}", fullPath);

        file.transferTo(new File(fullPath));

        return storeFileName;
    }

    @Override
    public List<AdminBoardListForm> selectAll(String kindOfBoard) {
        List<AdminBoardListForm> list = new ArrayList<>();
        List<AdoptReview> adoptReviewList = adoptReviewDao.selectAll(kindOfBoard);

        for (AdoptReview b : adoptReviewList) {
            AdminBoardListForm ad = new AdminBoardListForm(b.getBNumber(), findName(b.getMNumber()), b.getWrTime(), b.getTitle());
            list.add(ad);
        }

        return list;
    }

    @Override
    public String findName(int mNumber) {
        return memberDao.selectName(mNumber);
    }
}