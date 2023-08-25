package kh.petmily.service;

import kh.petmily.dao.LookBoardDao;
import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.look_board.LookBoard;
import kh.petmily.domain.look_board.form.*;
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
public class LookBoardServiceImpl implements LookBoardService {

    private final LookBoardDao lookBoardDao;
    private int size = 6;
    private int adminSize = 10;

    // ===================== Create =====================
    // 글쓰기
    @Override
    public void write(LookBoardWriteForm form) {
        LookBoard lookBoard = toWrite(form);
        lookBoardDao.insert(lookBoard);
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
    // 유기동물 봤어요 리스트 페이지
    @Override
    public LookBoardPageForm getListPage(LookBoardConditionForm form) {
        int total = lookBoardDao.selectCountWithCondition(form);
        List<LookBoardListForm> content = lookBoardDao.selectIndexWithCondition((form.getPageNo() - 1) * size + 1, (form.getPageNo() - 1) * size + size, form);

        return new LookBoardPageForm(total, form.getPageNo(), size, content);
    }

    // 유기동물 봤어요 리스트 페이지 (관리자)
    @Override
    public LookBoardPageForm getAdminListPage(int pageNo) {
        int total = lookBoardDao.selectCount();
        List<LookBoardListForm> content = lookBoardDao.selectIndex((pageNo - 1) * adminSize + 1, (pageNo - 1) * adminSize + adminSize);

        return new LookBoardPageForm(total, pageNo, adminSize, content);
    }

    // 상세보기 페이지
    @Override
    public LookBoardDetailForm getDetailPage(int pk) {
        LookBoard lookBoard = lookBoardDao.findByPk(pk);

        return toDetailForm(lookBoard);
    }

    // 봤어요 매칭된 페이지
    @Override
    public LookBoardPageForm getMatchingLookPage(int pageNo, int mNumber) {
        int total = lookBoardDao.selectCountLookMatching(mNumber);
        List<LookBoardListForm> content = lookBoardDao.selectIndexLookMatching((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber);

        return new LookBoardPageForm(total, pageNo, size, content);
    }

    // 찾아요에 매칭된 봤어요 리스트
    @Override
    public LookBoardPageForm getLookListMatchedFind(int pageNo, FindBoard findBoard) {
        int total = lookBoardDao.selectCountLookMatchedFind(findBoard);
        List<LookBoardListForm> content = lookBoardDao.selectIndexLookMatchedFind((pageNo - 1) * size + 1, (pageNo - 1) * size + size, findBoard);

        return new LookBoardPageForm(total, pageNo, size, content);
    }

    // 봤어요 글 조회
    @Override
    public LookBoard getLookBoard(int pk) {
        return lookBoardDao.findByPk(pk);
    }

    // 내가 쓴 게시글 (마이페이지)
    @Override
    public LookBoardPageForm getMyPost(int pageNo, int mNumber) {
        int total = lookBoardDao.selectCountBymNumber(mNumber);
        List<LookBoardListForm> content = lookBoardDao.selectIndexBymNumber((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber);

        return new LookBoardPageForm(total, pageNo, size, content);
    }

    // ===================== Update =====================
    // 수정 폼
    @Override
    public LookBoardModifyForm getModifyForm(int pk) {
        LookBoard lookBoard = lookBoardDao.findByPk(pk);

        return toModifyForm(lookBoard);
    }

    // 수정
    @Override
    public void modify(LookBoardModifyForm form) {
        LookBoard lookBoard = toModify(form);
        lookBoardDao.update(lookBoard);
    }


    // 조회수 업데이트
    @Override
    public int updateViewCount(int pk) {
        return lookBoardDao.updateViewCount(pk);
    }

    // ===================== Delete =====================
    // 삭제
    @Override
    public void delete(int pk) {
        lookBoardDao.delete(pk);
    }


    // ===================== CRUD 끝 =====================


    private LookBoard toWrite(LookBoardWriteForm form) {
        return new LookBoard(
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

    private LookBoardDetailForm toDetailForm(LookBoard lookBoard) {
        return new LookBoardDetailForm(
                lookBoard.getLaNumber(),
                lookBoard.getMNumber(),
                getMemberName(lookBoard.getLaNumber()),
                lookBoard.getSpecies(),
                lookBoard.getKind(),
                lookBoard.getLocation(),
                lookBoard.getAnimalState(),
                lookBoard.getImgPath(),
                lookBoard.getWrTime().format(getFormatter()),
                lookBoard.getTitle(),
                lookBoard.getContent(),
                lookBoard.getViewCount()
        );
    }

    public String getMemberName(int pk) {
        return lookBoardDao.selectName(pk);
    }

    private LookBoardModifyForm toModifyForm(LookBoard lookBoard) {
        return new LookBoardModifyForm(
                lookBoard.getLaNumber(),
                lookBoard.getMNumber(),
                lookBoard.getSpecies(),
                lookBoard.getKind(),
                lookBoard.getLocation(),
                lookBoard.getImgPath(),
                lookBoard.getTitle(),
                lookBoard.getContent(),
                lookBoard.getWrTime().format(getFormatter())
        );
    }

    private LookBoard toModify(LookBoardModifyForm form) {
        return new LookBoard(
                form.getLaNumber(),
                form.getMNumber(),
                form.getSpecies(),
                form.getKind(),
                form.getLocation(),
                form.getImgPath(),
                form.getTitle(),
                form.getContent(),
                LocalDateTime.parse(getReplaceWrTime(form), getFormatter())
        );
    }

    private String getReplaceWrTime(LookBoardModifyForm form) {
        return form.getWrTime().replace("T", " ");
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}