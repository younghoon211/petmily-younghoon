package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.board.Board;
import kh.petmily.domain.board.form.BoardListForm;
import kh.petmily.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
        List<Board> list = mapper.selectIndexBymNumber(start, end, mNumber, kindOfBoard);
        List<BoardListForm> boardListForm = new ArrayList<>();

        for (Board b : list) {
            BoardListForm bd = new BoardListForm(b.getBNumber(), b.getMNumber(), selectName(b.getBNumber()), b.getKindOfBoard(), b.getTitle(), b.getContent(), b.getWrTime(), b.getCheckPublic(), b.getViewCount(), selectReplyCount(b.getBNumber()));
            boardListForm.add(bd);
        }

        return boardListForm;
    }

    // 조건부 검색 게시글 개수
    public int selectCountWithCondition(String kindOfBoard, String condition, String keyword) {
        return mapper.selectCountWithCondition(kindOfBoard, condition, keyword);
    }

    // 조건부 검색
    public List<BoardListForm> selectIndexWithCondition(int start, int end, String kindOfBoard, String condition, String keyword, String sort) {
        List<Board> list = mapper.selectIndexWithCondition(start, end, kindOfBoard, condition, keyword, sort);
        List<BoardListForm> boardListForm = new ArrayList<>();

        for (Board b : list) {
            BoardListForm bd = new BoardListForm(b.getBNumber(), b.getMNumber(), selectName(b.getBNumber()), b.getKindOfBoard(), b.getTitle(), b.getContent(), b.getWrTime(), b.getCheckPublic(), b.getViewCount(), b.getCondition(), b.getKeyword(), b.getSort(), selectReplyCount(b.getBNumber()));
            boardListForm.add(bd);
        }

        return boardListForm;
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    public int updateViewCount(int pk) {
        return mapper.updateViewCount(pk);
    }

    public List<Board> selectAll(String kindOfBoard) {
        return mapper.selectAll(kindOfBoard);
    }

    public int selectReplyCount(int bNumber) {
        return mapper.selectReplyCount(bNumber);
    }
}