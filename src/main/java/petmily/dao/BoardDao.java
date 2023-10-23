package petmily.dao;

import petmily.domain.DomainObj;
import petmily.domain.board.Board;
import petmily.domain.board.form.BoardListForm;
import petmily.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDao implements BasicDao {

    private final BoardMapper mapper;

    @Override
    public Board findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        mapper.insert((Board) obj);
    }

    @Override
    public void update(DomainObj obj) {
        mapper.update((Board) obj);
    }

    @Override
    public void delete(int pk) {
        mapper.delete(pk);
    }

    // 내가 쓴 게시글 (마이페이지) - 총 게시글 수 (페이징)
    public int selectCountBymNumber(int mNumber, String kindOfBoard) {
        return mapper.selectCountBymNumber(mNumber, kindOfBoard);
    }

    // 내가 쓴 게시글 (마이페이지) - 글 index
    public List<BoardListForm> selectIndexBymNumber(int start, int end, int mNumber, String kindOfBoard) {
        List<BoardListForm> boardListForms = new ArrayList<>();
        List<Board> boards = mapper.selectIndexBymNumber(start, end, mNumber, kindOfBoard);

        addBoardListForms(boardListForms, boards);

        return boardListForms;
    }

    // 조건부 검색 - 총 게시글 수 (페이징)
    public int selectCountWithCondition(String keyword, String condition, String kindOfBoard) {
        return mapper.selectCountWithCondition(keyword, condition, kindOfBoard);
    }

    // 조건부 검색 - 글 index
    public List<BoardListForm> selectIndexWithCondition(int start, int end, String sort, String keyword, String condition, String kindOfBoard) {
        List<BoardListForm> boardListForms = new ArrayList<>();
        List<Board> boards = mapper.selectIndexWithCondition(start, end, sort, keyword, condition, kindOfBoard);

        addBoardListForms(boardListForms, boards);

        return boardListForms;
    }

    // 조회수 증가
    public int updateViewCount(int pk) {
        return mapper.updateViewCount(pk);
    }

    // 관리자 페이지 - 글 index
    public List<BoardListForm> selectIndexByPkDesc(int start, int end, String keyword, String condition, String kindOfBoard) {
        List<BoardListForm> boardListForms = new ArrayList<>();
        List<Board> boards = mapper.selectIndexByPkDesc(start, end, keyword, condition, kindOfBoard);

        addBoardListForms(boardListForms, boards);

        return boardListForms;
    }


    // =============== private 메소드 ===============

    private String selectName(int pk) {
        return mapper.selectName(pk);
    }

    private int selectReplyCount(int pk) {
        return mapper.selectReplyCount(pk);
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    private void addBoardListForms(List<BoardListForm> boardListForms, List<Board> boards) {
        for (Board board : boards) {
            BoardListForm listForm = new BoardListForm(
                    board.getBNumber(),
                    board.getMNumber(),
                    selectName(board.getBNumber()),
                    board.getKindOfBoard(),
                    board.getTitle(),
                    board.getContent(),
                    board.getWrTime().format(getFormatter()),
                    board.getCheckPublic(),
                    board.getViewCount(),
                    selectReplyCount(board.getBNumber())
            );

            boardListForms.add(listForm);
        }
    }
}