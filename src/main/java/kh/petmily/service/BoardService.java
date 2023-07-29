package kh.petmily.service;

import kh.petmily.domain.board.form.BoardDetailForm;
import kh.petmily.domain.board.form.BoardModifyForm;
import kh.petmily.domain.board.form.BoardPageForm;
import kh.petmily.domain.board.form.BoardWriteForm;
import kh.petmily.domain.board.form.BoardConditionForm;

public interface BoardService {

    void write(BoardWriteForm form);

    BoardPageForm getListPage(BoardConditionForm form);

    BoardDetailForm getDetailPage(int bNumber);

    BoardPageForm getMyPost(int pageNo, int mNumber, String type, String kindOfBoard);

    BoardPageForm getAdminListPage(String kindOfBoard, int pageNo);

    BoardModifyForm getModifyForm(int bNumber);

    void modify(BoardModifyForm form);

    int updateViewCount(int bNumber);

    void delete(int bNumber);
}