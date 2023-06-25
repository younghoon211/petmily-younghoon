package kh.petmily.service;

import kh.petmily.dao.BoardDao;
import kh.petmily.dao.MemberDao;
import kh.petmily.domain.admin.form.AdminBoardListForm;
import kh.petmily.domain.board.Board;
import kh.petmily.domain.board.form.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;
    private final MemberDao memberDao;
    private int size = 5;

    @Override
    public BoardPageForm getBoardPage(int pageNum, String kindOfBoard, String condition, String keyword, String sort) {
        int total = boardDao.selectCountWithCondition(kindOfBoard, condition, keyword);
        List<BoardListForm> content = boardDao.selectIndexWithCondition((pageNum - 1) * size + 1, (pageNum - 1) * size + size, kindOfBoard, condition, keyword, sort);

        return new BoardPageForm(total, pageNum, size, content);
    }

    @Override
    public void write(BoardWriteForm boardWriteForm) {
        Board board = toBoard(boardWriteForm);
        boardDao.insert(board);
    }

    @Override
    public BoardDetailForm getBoard(int bNumber) {
        Board boardDetailForm = boardDao.findByPk(bNumber);
        String memberName = memberDao.selectName(boardDetailForm.getMNumber());
        boardDetailForm.setName(memberName);

        return new BoardDetailForm(
                boardDetailForm.getBNumber(),
                boardDetailForm.getMNumber(),
                boardDetailForm.getName(),
                boardDetailForm.getKindOfBoard(),
                boardDetailForm.getTitle(),
                boardDetailForm.getContent(),
                boardDetailForm.getWrTime(),
                boardDetailForm.getCheckPublic(),
                boardDetailForm.getViewCount(),
                boardDetailForm.getCondition(),
                boardDetailForm.getKeyword(),
                boardDetailForm.getSort()
        );
    }

    @Override
    public BoardModifyForm getBoardModify(int bNumber) {
        Board board = boardDao.findByPk(bNumber);
        BoardModifyForm modReq = toBoardModify(board);
        return modReq;
    }

    @Override
    public void modify(BoardModifyForm modReq) {
        Board board = toBoardModifyForm(modReq);
        boardDao.update(board);
    }

    @Override
    public void delete(int bNumber) {
        boardDao.delete(bNumber);
    }

    @Override
    public int updateViewCount(int bNumber) {
        return boardDao.updateViewCount(bNumber);
    }

    @Override
    public List<AdminBoardListForm> selectAll(String kindOfBoard) {
        List<AdminBoardListForm> list = new ArrayList<>();

        List<Board> boardList = boardDao.selectAll(kindOfBoard);

        for (Board b : boardList) {
            AdminBoardListForm ad = new AdminBoardListForm(b.getBNumber(), findName(b.getMNumber()), b.getWrTime(), b.getTitle());
            list.add(ad);
        }

        return list;
    }

    @Override
    public String findName(int mNumber) {
        return memberDao.selectName(mNumber);
    }

    private Board toBoard(BoardWriteForm req) {
        return new Board(
                req.getMNumber(),
                req.getKindOfBoard(),
                req.getTitle(),
                req.getContent(),
                req.getCheckPublic());
    }

    private Board toBoardModifyForm(BoardModifyForm modReq) {
        return new Board(modReq.getBNumber(), modReq.getTitle(), modReq.getContent(), modReq.getCheckPublic());
    }

    private BoardModifyForm toBoardModify(Board board) {
        return new BoardModifyForm(board.getBNumber(), board.getTitle(), board.getContent(), board.getCheckPublic());
    }
}