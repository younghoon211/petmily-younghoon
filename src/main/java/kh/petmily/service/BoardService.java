package kh.petmily.service;

import kh.petmily.domain.board.form.BoardDetailForm;
import kh.petmily.domain.board.form.BoardModifyForm;
import kh.petmily.domain.board.form.BoardPageForm;
import kh.petmily.domain.board.form.BoardWriteForm;

public interface BoardService {

    public BoardPageForm getBoardPage(int pbNumber, String kindOfBoard, String condition, String keyword, String sort);

    public BoardPageForm getAdminBoardPage(String kindOfBoard, int pageNo);

    public void write(BoardWriteForm boardWriteForm);

    public BoardDetailForm getBoard(int bNumber);

    public BoardModifyForm getBoardModify(int bNumber);

    public void modify(BoardModifyForm modReq);

    public void delete(int bNumber);

    public int updateViewCount(int bNumber);

    public String findName(int mNumber);

    public BoardPageForm getBoardMyPost(int pageNo, int mNumber, String type, String kindOfBoard);
}