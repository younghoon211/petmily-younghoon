package petmily.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import petmily.dao.FindBoardDao;
import petmily.domain.admin_form.AdminBoardConditionForm;
import petmily.domain.find_board.FindBoard;
import petmily.domain.find_board.form.*;
import petmily.domain.look_board.LookBoard;

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
    // 찾아요 리스트 페이지 (일반회원)
    @Override
    public FindBoardPageForm getListPage(FindBoardConditionForm form) {
        int total = findBoardDao.selectCountWithCondition(form.getSpecies(), form.getAnimalState(), form.getKeyword());
        List<FindBoardListForm> content = findBoardDao.selectIndexWithCondition((form.getPageNo() - 1) * size + 1, (form.getPageNo() - 1) * size + size, form);

        return new FindBoardPageForm(total, form.getPageNo(), size, content);
    }

    // 찾아요 리스트 페이지 (관리자)
    @Override
    public FindBoardPageForm getAdminListPage(AdminBoardConditionForm form) {
        int total = findBoardDao.selectCountWithCondition(form.getSpecies(), form.getAnimalState(), form.getKeyword());
        List<FindBoardListForm> content = findBoardDao.selectIndexByPkDesc(
                (form.getPageNo() - 1) * adminSize + 1, (form.getPageNo() - 1) * adminSize + adminSize, form
        );

        return new FindBoardPageForm(total, form.getPageNo(), adminSize, content);
    }

    // 상세보기 페이지
    @Override
    public FindBoardDetailForm getDetailPage(int pk) {
        FindBoard findBoard = getFindBoard(pk);

        return toDetailForm(findBoard);
    }

    // 찾아요 매칭된 페이지
    @Override
    public FindBoardPageForm getMatchingFindPage(int pageNo, int mNumber) {
        int total = findBoardDao.selectCountFindMatching(mNumber);
        List<FindBoardListForm> content = findBoardDao.selectIndexFindMatching((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber);

        return new FindBoardPageForm(total, pageNo, size, content);
    }

    // 봤어요에 매칭된 찾아요 리스트
    @Override
    public FindBoardPageForm getFindListMatchedLook(int pageNo, LookBoard lookBoard) {
        int total = findBoardDao.selectCountFindMatchedLook(lookBoard);
        List<FindBoardListForm> content = findBoardDao.selectIndexFindMatchedLook((pageNo - 1) * size + 1, (pageNo - 1) * size + size, lookBoard);

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

    private FindBoardDetailForm toDetailForm(FindBoard findBoard) {
        return new FindBoardDetailForm(
                findBoard.getFaNumber(),
                findBoard.getMNumber(),
                getMemberName(findBoard.getFaNumber()),
                findBoard.getSpecies(),
                findBoard.getKind(),
                findBoard.getLocation(),
                findBoard.getAnimalState(),
                findBoard.getImgPath(),
                findBoard.getWrTime().format(getFormatter()),
                findBoard.getTitle(),
                findBoard.getContent(),
                findBoard.getViewCount()
        );
    }

    public String getMemberName(int pk) {
        return findBoardDao.selectName(pk);
    }

    private FindBoardModifyForm toModifyForm(FindBoard findBoard) {
        return new FindBoardModifyForm(
                findBoard.getFaNumber(),
                findBoard.getMNumber(),
                findBoard.getSpecies(),
                findBoard.getKind(),
                findBoard.getLocation(),
                findBoard.getImgPath(),
                findBoard.getTitle(),
                findBoard.getContent(),
                findBoard.getWrTime().format(getFormatter())
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