package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.board.Board;
import kh.petmily.domain.board.form.BoardListForm;
import kh.petmily.domain.board.form.BoardConditionForm;
import kh.petmily.mapper.BoardMapper;
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

    public int selectCountBymNumber(int mNumber, String kindOfBoard) {
        return mapper.selectCountBymNumber(mNumber, kindOfBoard);
    }

    public List<BoardListForm> selectIndexBymNumber(int start, int end, int mNumber, String kindOfBoard) {
        List<BoardListForm> result = new ArrayList<>();
        List<Board> list = mapper.selectIndexBymNumber(start, end, mNumber, kindOfBoard);

        for (Board b : list) {
            BoardListForm li = new BoardListForm(
                    b.getBNumber(),
                    b.getMNumber(),
                    selectName(b.getBNumber()),
                    b.getKindOfBoard(),
                    b.getTitle(),
                    b.getContent(),
                    b.getWrTime().format(getFormatter()),
                    b.getCheckPublic(),
                    b.getViewCount(),
                    selectReplyCount(b.getBNumber())
            );

            result.add(li);
        }

        return result;
    }

    // 관리자 페이지 게시글 개수
    public int selectCount(String kindOfBoard) {
        return mapper.selectCount(kindOfBoard);
    }

    // 관리자 페이지 리스트
    public List<BoardListForm> selectIndex(int start, int end, String kindOfBoard) {
        List<BoardListForm> result = new ArrayList<>();
        List<Board> list = mapper.selectIndex(start, end, kindOfBoard);

        for (Board b : list) {
            BoardListForm li = new BoardListForm(
                    b.getBNumber(),
                    b.getMNumber(),
                    selectMemberId(b.getBNumber()),
                    selectName(b.getBNumber()),
                    b.getKindOfBoard(),
                    b.getTitle(),
                    b.getContent(),
                    b.getWrTime().format(getFormatter()),
                    b.getCheckPublic(),
                    b.getViewCount(),
                    selectReplyCount(b.getBNumber())
            );

            result.add(li);
        }

        return result;
    }

    // 조건부 검색 게시글 개수
    public int selectCountWithCondition(BoardConditionForm form) {
        return mapper.selectCountWithCondition(form);
    }

    // 조건부 검색
    public List<BoardListForm> selectIndexWithCondition(int start, int end, BoardConditionForm form) {
        List<BoardListForm> result = new ArrayList<>();
        List<Board> list = mapper.selectIndexWithCondition(
                start, end, form.getKindOfBoard(), form.getCondition(), form.getKeyword(), form.getSort()
        );

        for (Board b : list) {
            BoardListForm li = new BoardListForm(
                    b.getBNumber(),
                    b.getMNumber(),
                    selectName(b.getBNumber()),
                    b.getKindOfBoard(),
                    b.getTitle(),
                    b.getContent(),
                    b.getWrTime().format(getFormatter()),
                    b.getCheckPublic(),
                    b.getViewCount(),
                    selectReplyCount(b.getBNumber())
            );

            result.add(li);
        }

        return result;
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    public String selectMemberId(int pk) {
        return mapper.selectMemberId(pk);
    }

    public int updateViewCount(int pk) {
        return mapper.updateViewCount(pk);
    }

    public int selectReplyCount(int pk) {
        return mapper.selectReplyCount(pk);
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}