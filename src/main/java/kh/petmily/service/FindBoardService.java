package kh.petmily.service;

import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.find_board.form.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FindBoardService {

    void write(FindBoardWriteForm findBoardWriteForm);

    void modify(FindBoardModifyForm findBoardModifyForm);

    void delete(int faNumber);

    FindBoardDetailForm getDetailForm(int faNumber);

    FindBoardModifyForm getModifyForm(int faNumber);

    FindBoardPageForm getFindPage(FindBoardConditionForm findBoardConditionForm);

    FindBoardPageForm getAdminFindPage(int pageNo);

    FindBoardPageForm getMembersFindPage(int pageNo, int mNumber, String matched);

    String findName(int faNumber);

    int updateViewCount(int faNumber);

    FindBoard getFindBoard(int faNumber);

    String storeFile(MultipartFile file, String filePath) throws IOException;

    FindBoardPageForm getFindMyPost(int pageNo, int mNumber);
}