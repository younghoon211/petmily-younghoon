package kh.petmily.service;

import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.look_board.LookBoard;
import kh.petmily.domain.look_board.form.LookBoardDetailForm;
import kh.petmily.domain.look_board.form.LookBoardModifyForm;
import kh.petmily.domain.look_board.form.LookBoardPageForm;
import kh.petmily.domain.look_board.form.LookBoardWriteForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LookBoardService {

    void write(LookBoardWriteForm lwForm);

    void modify(LookBoardModifyForm lmForm);

    void delete(int laNumber);

    LookBoardPageForm getLookPage(int pageNo, String animalType, String stateType, String keyword, String sort);

    LookBoardPageForm getAdminLookPage(int pageNo);

    LookBoardDetailForm getDetailForm(int laNumber);

    LookBoardModifyForm getModifyForm(int laNumber);

    String findLookBoardName(int laNumber);

    String findMemberName(int mNumber);

    int updateViewCount(int laNumber);

    LookBoardPageForm getMatchedLookPage(int pageNo, FindBoard findBoard);

    String storeFile(MultipartFile file, String filePath) throws IOException;

    LookBoard getLookBoard(int laNumber);

    LookBoardPageForm getLookMyPost(int pageNo, int mNumber, String type);
}