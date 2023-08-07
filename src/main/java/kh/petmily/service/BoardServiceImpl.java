package kh.petmily.service;

import kh.petmily.dao.BoardDao;
import kh.petmily.dao.MemberDao;
import kh.petmily.domain.board.Board;
import kh.petmily.domain.board.form.*;
import kh.petmily.domain.board.form.BoardConditionForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;
    private final MemberDao memberDao;
    private int size = 10;

    // ===================== Create =====================
    // 글쓰기
    @Override
    public void write(BoardWriteForm form) {
        Board board = toWrite(form);
        boardDao.insert(board);
    }

    // ===================== Read =====================
    // 게시판 리스트
    @Override
    public BoardPageForm getListPage(BoardConditionForm form) {
        int total = boardDao.selectCountWithCondition(form);
        List<BoardListForm> content = boardDao.selectIndexWithCondition((form.getPageNo() - 1) * size + 1, (form.getPageNo() - 1) * size + size, form);

        return new BoardPageForm(total, form.getPageNo(), size, content);
    }

    // 글 상세보기
    @Override
    public BoardDetailForm getDetailPage(int pk) {
        Board board = boardDao.findByPk(pk);
        String memberName = memberDao.selectName(board.getMNumber());

        return toDetailForm(board, memberName);
    }

    // 내가 쓴 게시글 (마이페이지)
    @Override
    public BoardPageForm getMyPost(int pageNo, int mNumber, String type, String kindOfBoard) {
        int total = boardDao.selectCountBymNumber(mNumber, kindOfBoard);
        List<BoardListForm> content = boardDao.selectIndexBymNumber((pageNo - 1) * size + 1, (pageNo - 1) * size + size, mNumber, kindOfBoard);

        return new BoardPageForm(total, pageNo, size, content);
    }

    // 게시판 리스트 (관리자 페이지)
    @Override
    public BoardPageForm getAdminListPage(String kindOfBoard, int pageNo) {
        int total = boardDao.selectCount(kindOfBoard);
        List<BoardListForm> content = boardDao.selectIndex((pageNo - 1) * size + 1, (pageNo - 1) * size + size, kindOfBoard);

        return new BoardPageForm(total, pageNo, size, content);
    }

    // ===================== Update =====================
    // 수정 폼
    @Override
    public BoardModifyForm getModifyForm(int pk) {
        Board board = boardDao.findByPk(pk);

        return toModifyForm(board);
    }

    // 수정 폼 제출
    @Override
    public void modify(BoardModifyForm form) {
        Board board = toModify(form);
        boardDao.update(board);
    }

    // 조회수 업데이트
    @Override
    public int updateViewCount(int pk) {
        return boardDao.updateViewCount(pk);
    }

    // ===================== Delete =====================
    // 삭제
    @Override
    public void delete(int pk) {
        boardDao.delete(pk);
    }


    // ===================== CRUD 끝 =====================

    private Board toWrite(BoardWriteForm form) {
        return new Board(
                form.getMNumber(),
                form.getKindOfBoard(),
                form.getTitle(),
                form.getContent(),
                form.getCheckPublic());
    }

    private BoardDetailForm toDetailForm(Board domain, String memberName) {
        return new BoardDetailForm(
                domain.getBNumber(),
                domain.getMNumber(),
                memberName,
                domain.getKindOfBoard(),
                domain.getTitle(),
                domain.getContent(),
                domain.getWrTime().format(getFormatter()),
                domain.getCheckPublic(),
                domain.getViewCount()
        );
    }

    private BoardModifyForm toModifyForm(Board domain) {
        return new BoardModifyForm(
                domain.getBNumber(),
                domain.getMNumber(),
                domain.getTitle(),
                domain.getContent(),
                domain.getCheckPublic(),
                domain.getKindOfBoard(),
                domain.getWrTime().format(getFormatter())
        );
    }

    private Board toModify(BoardModifyForm form) {
        return new Board(
                form.getBNumber(),
                form.getMNumber(),
                form.getTitle(),
                form.getContent(),
                form.getCheckPublic(),
                LocalDateTime.parse(getReplaceWrTime(form), getFormatter())
        );

    }

    private String getReplaceWrTime(BoardModifyForm form) {
        return form.getWrTime().replace("T", " ");
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}