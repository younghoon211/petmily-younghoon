package kh.petmily.service;

import kh.petmily.dao.FindBoardDao;
import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.find_board.form.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
public class FindBoardServiceImpl implements FindBoardService {

    private final FindBoardDao findBoardDao;
    private int size = 6;
    private int adminSize = 10;

    // ===================== Create =====================
    // 글쓰기
    @Override
    public void write(FindBoardWriteForm form) {
        FindBoard findBoard = toWrite(form);
        findBoardDao.insert(findBoard);
    }

    // 파일 업로드
    @Override
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
        String storeFileName = uuid + "." + extractExt(originalFilename); // 확장자 포함한 uuid명
        String fullPath = getFullPath(storeFileName, filePath);

        log.info("fullPath = {}", fullPath);

        file.transferTo(new File(fullPath));

        return storeFileName;
    }

    // ===================== Read =====================
    // 반려동물 찾아요 리스트 페이지
    @Override
    public FindBoardPageForm getListPage(FindBoardConditionForm form) {
        int total = findBoardDao.selectCountWithCondition(form);
        List<FindBoardListForm> content = findBoardDao.selectIndexWithCondition((form.getPageNo() - 1) * size + 1, (form.getPageNo() - 1) * size + size, form);

        return new FindBoardPageForm(total, form.getPageNo(), size, content);
    }

    // 반려동물 찾아요 리스트 페이지 (관리자)
    @Override
    public FindBoardPageForm getAdminListPage(int pageNo) {
        int total = findBoardDao.selectCount();
        List<FindBoardListForm> content = findBoardDao.selectIndex((pageNo - 1) * adminSize + 1, (pageNo - 1) * adminSize + adminSize);

        return new FindBoardPageForm(total, pageNo, adminSize, content);
    }

    // 상세보기 페이지
    @Override
    public FindBoardDetailForm getDetailPage(int pk) {
        FindBoard findBoard = getFindBoard(pk);

        return toDetailForm(findBoard);
    }

    // 찾아요 매칭된 페이지
    @Override
    public FindBoardPageForm getMatchingPage(int pageNo, int mNumber, String matched) {
        int total = findBoardDao.selectMemberCount(mNumber, matched);
        List<FindBoardListForm> content = findBoardDao.selectMemberIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber, matched);

        return new FindBoardPageForm(total, pageNo, size, content);
    }

    // 찾아요 글 조회
    @Override
    public FindBoard getFindBoard(int pk) {
        return findBoardDao.findByPk(pk);
    }

    // 내가 쓴 게시글 (마이페이지)
    @Override
    public FindBoardPageForm getMyPost(int pageNo, int mNumber) {
        int total = findBoardDao.selectCountBymNumber(mNumber);
        List<FindBoardListForm> content = findBoardDao.selectIndexBymNumber((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber);

        return new FindBoardPageForm(total, pageNo, size, content);
    }

    // ===================== Update =====================
    // 수정 폼
    @Override
    public FindBoardModifyForm getModifyForm(int pk) {
        FindBoard findBoard = getFindBoard(pk);

        return toModifyForm(findBoard);
    }

    // 수정하기
    @Override
    public void modify(FindBoardModifyForm form) {
        FindBoard findBoard = toModify(form);
        findBoardDao.update(findBoard);
    }

    // 조회수 업데이트
    @Override
    public int updateViewCount(int pk) {
        return findBoardDao.updateViewCount(pk);
    }

    // ===================== Delete =====================
    // 삭제
    @Override
    public void delete(int pk) {
        findBoardDao.delete(pk);
    }


    // ===================== CRUD 끝 =====================


    private FindBoard toWrite(FindBoardWriteForm form) {
        return new FindBoard(
                form.getSpecies(),
                form.getMNumber(),
                form.getKind(),
                form.getLocation(),
                form.getImgPath(),
                form.getTitle(),
                form.getContent()
        );
    }

    private String extractExt(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");

        return originalFilename.substring(position + 1);
    }

    private String getFullPath(String filename, String filePath) {
        return filePath + filename;
    }

    private FindBoardDetailForm toDetailForm(FindBoard domain) {
        return new FindBoardDetailForm(
                domain.getFaNumber(),
                domain.getMNumber(),
                getMemberName(domain.getFaNumber()),
                domain.getSpecies(),
                domain.getKind(),
                domain.getLocation(),
                domain.getAnimalState(),
                domain.getImgPath(),
                domain.getWrTime().format(getFormatter()),
                domain.getTitle(),
                domain.getContent(),
                domain.getViewCount()
        );
    }

    public String getMemberName(int pk) {
        return findBoardDao.selectName(pk);
    }

    private FindBoardModifyForm toModifyForm(FindBoard domain) {
        return new FindBoardModifyForm(
                domain.getFaNumber(),
                domain.getMNumber(),
                domain.getSpecies(),
                domain.getKind(),
                domain.getLocation(),
                domain.getImgPath(),
                domain.getTitle(),
                domain.getContent(),
                domain.getWrTime().format(getFormatter())
        );
    }

    private FindBoard toModify(FindBoardModifyForm form) {
        return new FindBoard(
                form.getFaNumber(),
                form.getMNumber(),
                form.getSpecies(),
                form.getKind(),
                form.getLocation(),
                form.getImgPath(),
                form.getTitle(),
                form.getContent(),
                LocalDateTime.parse(getReplaceWrTime(form), getFormatter()));
    }

    private String getReplaceWrTime(FindBoardModifyForm form) {
        return form.getWrTime().replace("T", " ");
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}