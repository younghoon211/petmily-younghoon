package kh.petmily.service;

import kh.petmily.domain.board.form.BoardDetailForm;
import kh.petmily.domain.board.form.BoardModifyForm;
import kh.petmily.domain.board.form.BoardPageForm;
import kh.petmily.domain.board.form.BoardWriteForm;
import kh.petmily.domain.board.form.BoardConditionForm;

public interface BoardService {

    BoardPageForm getBoardPage(BoardConditionForm boardConditionForm);

    BoardPageForm getAdminBoardPage(String kindOfBoard, int pageNo);

    void write(BoardWriteForm boardWriteForm);

    BoardDetailForm getBoard(int bNumber);

    BoardModifyForm getBoardModify(int bNumber);

    void modify(BoardModifyForm modReq);

    void delete(int bNumber);

    int updateViewCount(int bNumber);

    String findName(int mNumber);

    BoardPageForm getBoardMyPost(int pageNo, int mNumber, String type, String kindOfBoard);
}