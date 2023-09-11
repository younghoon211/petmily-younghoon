package petmily.service;

import org.springframework.web.multipart.MultipartFile;
import petmily.domain.admin_form.AdminBoardConditionForm;
import petmily.domain.find_board.FindBoard;
import petmily.domain.look_board.LookBoard;
import petmily.domain.look_board.form.*;

import java.io.IOException;

public interface LookBoardService {

    void write(LookBoardWriteForm form);

    String storeFile(MultipartFile file, String filePath) throws IOException;

    LookBoardPageForm getListPage(LookBoardConditionForm form);

    LookBoardPageForm getAdminListPage(AdminBoardConditionForm form);

    LookBoardDetailForm getDetailPage(int laNumber);

    LookBoardPageForm getMatchingLookPage(int pageNo, int mNumber);

    LookBoardPageForm getLookListMatchedFind(int pageNo, FindBoard findBoard);

    LookBoard getLookBoard(int laNumber);

    LookBoardPageForm getMyPost(int pageNo, int mNumber);

    LookBoardModifyForm getModifyForm(int laNumber);

    void modify(LookBoardModifyForm form);

    int updateViewCount(int laNumber);

    void delete(int laNumber);
}