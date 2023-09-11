package petmily.service;

import petmily.domain.admin_form.AdminBoardConditionForm;
import petmily.domain.board.Board;
import petmily.domain.board.form.*;

public interface BoardService {

    void write(BoardWriteForm form);

    BoardPageForm getListPage(BoardConditionForm form);

    BoardDetailForm getDetailPage(int bNumber);

    BoardPageForm getMyPost(int pageNo, int mNumber, String type, String kindOfBoard);

    Board getBoard(int pk);

    BoardPageForm getAdminListPage(AdminBoardConditionForm form);

    BoardModifyForm getModifyForm(int bNumber);

    void modify(BoardModifyForm form);

    int updateViewCount(int bNumber);

    void delete(int bNumber);
}