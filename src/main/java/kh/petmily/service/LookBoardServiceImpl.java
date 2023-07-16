package kh.petmily.service;

import kh.petmily.dao.LookBoardDao;
import kh.petmily.dao.MemberDao;
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
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LookBoardServiceImpl implements LookBoardService {

    private final LookBoardDao lookBoardDao;
    private final MemberDao memberDao;
    private int size = 6;
    private int adminSize = 10;

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
        String storeFileName = uuid + "." + extractExt(originalFilename);
        String fullPath = getFullPath(storeFileName, filePath);
        file.transferTo(new File(fullPath));

        return storeFileName;
    }

    @Override
    public void write(LookBoardWriteForm lwForm) {
        LookBoard lookBoard = toLookFromLW(lwForm);
        lookBoardDao.insert(lookBoard);
    }

    @Override
    public void modify(LookBoardModifyForm lmForm) {
        LookBoard lookBoard = toLookFromLM(lmForm);
        lookBoard.setLaNumber(lmForm.getLaNumber());
        lookBoardDao.update(lookBoard);
    }

    @Override
    public void delete(int laNumber) {
        lookBoardDao.delete(laNumber);
    }

    @Override
    public LookBoardPageForm getLookPage(LookBoardConditionForm lc) {
        int total = lookBoardDao.selectCountWithCondition(lc);
        List<LookBoardListForm> content = lookBoardDao.selectIndexWithCondition((lc.getPageNo() - 1) * size + 1, (lc.getPageNo() - 1) * size + size, lc);

        return new LookBoardPageForm(total, lc.getPageNo(), size, content);
    }

    @Override
    public LookBoardPageForm getAdminLookPage(int pageNo) {
        int total = lookBoardDao.selectCount();
        List<LookBoardListForm> content = lookBoardDao.selectIndex((pageNo - 1) * adminSize + 1, (pageNo - 1) * adminSize + adminSize);

        return new LookBoardPageForm(total, pageNo, adminSize, content);
    }

    @Override
    public LookBoardDetailForm getDetailForm(int laNumber) {
        LookBoard lookBoard = lookBoardDao.findByPk(laNumber);
        LookBoardDetailForm detailForm = toDetailForm(lookBoard);

        return detailForm;
    }

    @Override
    public LookBoardModifyForm getModifyForm(int laNumber) {
        LookBoard lookBoard = lookBoardDao.findByPk(laNumber);
        LookBoardModifyForm modifyForm = toModifyForm(lookBoard);

        return modifyForm;
    }

    @Override
    public String findLookBoardName(int laNumber) {
        return lookBoardDao.selectName(laNumber);
    }

    @Override
    public String findMemberName(int mNumber) {
        return memberDao.selectName(mNumber);
    }

    @Override
    public int updateViewCount(int laNumber) {
        return lookBoardDao.updateViewCount(laNumber);
    }

    @Override
    public LookBoardPageForm getMatchedLookPage(int pageNo, FindBoard findBoard) {
        int total = lookBoardDao.selectMatchedCount(findBoard);

        List<LookBoardListForm> content = lookBoardDao.selectMatchedIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, findBoard);

        return new LookBoardPageForm(total, pageNo, size, content);
    }

    @Override
    public LookBoard getLookBoard(int laNumber) {
        return lookBoardDao.findByPk(laNumber);
    }

    @Override
    public LookBoardPageForm getLookMyPost(int pageNo, int mNumber) {
        int total = lookBoardDao.selectCountBymNumber(mNumber);
        List<LookBoardListForm> content = lookBoardDao.selectIndexBymNumber((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber);

        return new LookBoardPageForm(total, pageNo, size, content);
    }

    private String getFullPath(String filename, String filePath) {
        return filePath + filename;
    }

    private String extractExt(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");

        return originalFilename.substring(position + 1);
    }

    private LookBoard toLookFromLW(LookBoardWriteForm req) {
        return new LookBoard(req.getMNumber(), req.getSpecies(), req.getKind(), req.getLocation(), req.getFullPath(), req.getTitle(), req.getContent());
    }

    private LookBoard toLookFromLM(LookBoardModifyForm req) {
        return new LookBoard(req.getMNumber(), req.getSpecies(), req.getKind(), req.getLocation(), req.getFullPath(), req.getTitle(), req.getContent());
    }

    private LookBoardDetailForm toDetailForm(LookBoard lookBoard) {
        return new LookBoardDetailForm(lookBoard.getLaNumber(), lookBoard.getMNumber(), findLookBoardName(lookBoard.getLaNumber()), lookBoard.getSpecies(), lookBoard.getKind(), lookBoard.getLocation(), lookBoard.getAnimalState(), lookBoard.getImgPath(), lookBoard.getWrTime(), lookBoard.getTitle(), lookBoard.getContent(), lookBoard.getViewCount());
    }

    private LookBoardModifyForm toModifyForm(LookBoard lookBoard) {
        return new LookBoardModifyForm(lookBoard.getSpecies(), lookBoard.getKind(), lookBoard.getLocation(), lookBoard.getTitle(), lookBoard.getContent());
    }
}