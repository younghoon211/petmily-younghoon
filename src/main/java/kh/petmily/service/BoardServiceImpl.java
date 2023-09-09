package kh.petmily.service;

import kh.petmily.dao.BoardDao;
import kh.petmily.dao.MemberDao;
import kh.petmily.domain.admin_form.AdminBoardConditionForm;
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
        int total = boardDao.selectCountWithCondition(
                form.getKeyword(),
                form.getCondition(),
                engToKoKindOfBoard(form.getKindOfBoard())
        );
        
        List<BoardListForm> content = boardDao.selectIndexWithCondition(
                (form.getPageNo() - 1) * size + 1, 
                (form.getPageNo() - 1) * size + size, 
                form.getSort(), 
                form.getKeyword(), 
                form.getCondition(), 
                engToKoKindOfBoard(form.getKindOfBoard())
        );

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
        int total = boardDao.selectCountBymNumber(mNumber, engToKoKindOfBoard(kindOfBoard));

        List<BoardListForm> content = boardDao.selectIndexBymNumber(
                (pageNo - 1) * size + 1,
                (pageNo - 1) * size + size, mNumber,
                engToKoKindOfBoard(kindOfBoard)
        );

        return new BoardPageForm(total, pageNo, size, content);
    }

    // 게시글 조회
    @Override
    public Board getBoard(int pk) {
        return boardDao.findByPk(pk);
    }

    // 게시판 리스트 (관리자)
    @Override
    public BoardPageForm getAdminListPage(AdminBoardConditionForm form) {
        int total = boardDao.selectCountWithCondition(
                form.getKeyword(),
                form.getCondition(),
                engToKoKindOfBoard(form.getKindOfBoard())
        );

        List<BoardListForm> content = boardDao.selectIndexByPkDesc(
                (form.getPageNo() - 1) * size + 1,
                (form.getPageNo() - 1) * size + size,
                form.getKeyword(),
                form.getCondition(),
                engToKoKindOfBoard(form.getKindOfBoard())
        );

        return new BoardPageForm(total, form.getPageNo(), size, content);
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
                engToKoKindOfBoard(form.getKindOfBoard()),
                form.getTitle(),
                form.getContent(),
                form.getCheckPublic()
        );
    }

    private BoardDetailForm toDetailForm(Board board, String memberName) {
        return new BoardDetailForm(
                board.getBNumber(),
                board.getMNumber(),
                memberName,
                koToEngKindOfBoard(board.getKindOfBoard()),
                board.getTitle(),
                board.getContent(),
                board.getWrTime().format(getFormatter()),
                board.getCheckPublic(),
                board.getViewCount()
        );
    }

    private BoardModifyForm toModifyForm(Board board) {
        return new BoardModifyForm(
                board.getBNumber(),
                board.getMNumber(),
                board.getTitle(),
                board.getContent(),
                board.getCheckPublic(),
                koToEngKindOfBoard(board.getKindOfBoard()),
                board.getWrTime().format(getFormatter())
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

    private String engToKoKindOfBoard(String kindOfBoard) {
        if (kindOfBoard.equals("free")) {
            return "자유";
        } else {
            return "문의";
        }
    }

    private String koToEngKindOfBoard(String kindOfBoard) {
        if (kindOfBoard.equals("자유")) {
            return "free";
        } else {
            return "inquiry";
        }
    }
}