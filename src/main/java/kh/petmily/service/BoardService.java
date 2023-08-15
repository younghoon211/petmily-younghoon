package kh.petmily.service;

import kh.petmily.domain.board.Board;
import kh.petmily.domain.board.form.*;

public interface BoardService {

    void write(BoardWriteForm form);

    BoardPageForm getListPage(BoardConditionForm form);

    BoardDetailForm getDetailPage(int bNumber);

    BoardPageForm getMyPost(int pageNo, int mNumber, String type, String kindOfBoard);

    BoardPageForm getAdminListPage(String kindOfBoard, int pageNo);

    Board getBoard(int pk);

    BoardModifyForm getModifyForm(int bNumber);

    void modify(BoardModifyForm form);

    int updateViewCount(int bNumber);

    void delete(int bNumber);
}