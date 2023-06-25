package kh.petmily.service;

import kh.petmily.domain.admin.form.AdminBoardListForm;
import kh.petmily.domain.board.form.BoardModifyForm;
import kh.petmily.domain.board.form.BoardPageForm;
import kh.petmily.domain.board.form.BoardDetailForm;
import kh.petmily.domain.board.form.BoardWriteForm;

import java.util.List;

public interface BoardService {

    public BoardPageForm getBoardPage(int pbNumber, String kindOfBoard, String condition, String keyword, String sort);

    public void write(BoardWriteForm boardWriteForm);

    public BoardDetailForm getBoard(int bNumber);

    public BoardModifyForm getBoardModify(int bNumber);

    public void modify(BoardModifyForm modReq);

    public void delete(int bNumber);

    public int updateViewCount(int bNumber);

    public List<AdminBoardListForm> selectAll(String kindOfBoard);

    public String findName(int mNumber);
}