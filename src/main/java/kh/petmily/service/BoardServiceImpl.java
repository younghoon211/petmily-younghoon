package kh.petmily.service;

import kh.petmily.dao.BoardDao;
import kh.petmily.dao.MemberDao;
import kh.petmily.domain.board.Board;
import kh.petmily.domain.board.form.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;
    private final MemberDao memberDao;
    private int size = 10;

    @Override
    public BoardPageForm getBoardPage(int pageNo, String kindOfBoard, String condition, String keyword, String sort) {
        int total = boardDao.selectCountWithCondition(kindOfBoard, condition, keyword);
        List<BoardListForm> content = boardDao.selectIndexWithCondition((pageNo - 1) * size + 1, (pageNo - 1) * size + size, kindOfBoard, condition, keyword, sort);

        return new BoardPageForm(total, pageNo, size, content);
    }

    @Override
    public BoardPageForm getAdminBoardPage(String kindOfBoard, int pageNo) {
        int total = boardDao.selectCount(kindOfBoard);
        List<BoardListForm> content = boardDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, kindOfBoard);

        return new BoardPageForm(total, pageNo, size, content);
    }

    @Override
    public void write(BoardWriteForm boardWriteForm) {
        Board board = toBoard(boardWriteForm);
        boardDao.insert(board);
    }

    @Override
    public BoardDetailForm getBoard(int bNumber) {
        Board board = boardDao.findByPk(bNumber);
        String memberName = memberDao.selectName(board.getMNumber());

        return new BoardDetailForm(
                board.getBNumber(),
                board.getMNumber(),
                memberName,
                board.getKindOfBoard(),
                board.getTitle(),
                board.getContent(),
                board.getWrTime(),
                board.getCheckPublic(),
                board.getViewCount()
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
    public String findName(int mNumber) {
        return memberDao.selectName(mNumber);
    }

    @Override
    public BoardPageForm getBoardMyPost(int pageNo, int mNumber, String type, String kindOfBoard) {
        int total = boardDao.selectCountBymNumber(mNumber, kindOfBoard);
        List<BoardListForm> content = boardDao.selectIndexBymNumber((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber, kindOfBoard);

        return new BoardPageForm(total, pageNo, size, content);
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