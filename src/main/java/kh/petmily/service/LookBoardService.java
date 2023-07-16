package kh.petmily.service;

import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.look_board.LookBoard;
import kh.petmily.domain.look_board.form.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LookBoardService {

    void write(LookBoardWriteForm lwForm);

    void modify(LookBoardModifyForm lmForm);

    void delete(int laNumber);

    LookBoardPageForm getLookPage(LookBoardConditionForm lookBoardConditionForm);

    LookBoardPageForm getAdminLookPage(int pageNo);

    LookBoardDetailForm getDetailForm(int laNumber);

    LookBoardModifyForm getModifyForm(int laNumber);

    String findLookBoardName(int laNumber);

    String findMemberName(int mNumber);

    int updateViewCount(int laNumber);

    LookBoardPageForm getMatchedLookPage(int pageNo, FindBoard findBoard);

    String storeFile(MultipartFile file, String filePath) throws IOException;

    LookBoard getLookBoard(int laNumber);

    LookBoardPageForm getLookMyPost(int pageNo, int mNumber);
}