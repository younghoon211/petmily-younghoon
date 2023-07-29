package kh.petmily.service;

import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.look_board.LookBoard;
import kh.petmily.domain.look_board.form.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LookBoardService {

    void write(LookBoardWriteForm form);

    String storeFile(MultipartFile file, String filePath) throws IOException;

    LookBoardPageForm getListPage(LookBoardConditionForm form);

    LookBoardPageForm getAdminListPage(int pageNo);

    LookBoardDetailForm getDetailPage(int laNumber);

    LookBoardPageForm getMatchingPage(int pageNo, FindBoard domain);

    LookBoard getLookBoard(int laNumber);

    LookBoardPageForm getMyPost(int pageNo, int mNumber);

    LookBoardModifyForm getModifyForm(int laNumber);

    void modify(LookBoardModifyForm form);

    int updateViewCount(int laNumber);

    void delete(int laNumber);
}