package petmily.service;

import org.springframework.web.multipart.MultipartFile;
import petmily.domain.admin_form.AdminBoardConditionForm;
import petmily.domain.find_board.FindBoard;
import petmily.domain.find_board.form.*;
import petmily.domain.look_board.LookBoard;

import java.io.IOException;

public interface FindBoardService {

    void write(FindBoardWriteForm form);

    String storeFile(MultipartFile file, String filePath) throws IOException;

    FindBoardPageForm getListPage(FindBoardConditionForm form);

    FindBoardPageForm getAdminListPage(AdminBoardConditionForm form);

    FindBoardDetailForm getDetailPage(int faNumber);

    FindBoardPageForm getMatchingFindPage(int pageNo, int mNumber);

    FindBoardPageForm getFindListMatchedLook(int pageNo, LookBoard lookBoard);

    FindBoard getFindBoard(int faNumber);

    FindBoardPageForm getMyPost(int pageNo, int mNumber);

    FindBoardModifyForm getModifyForm(int faNumber);

    void modify(FindBoardModifyForm form);

    int updateViewCount(int faNumber);

    void delete(int faNumber);
}