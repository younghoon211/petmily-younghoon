package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.look_board.LookBoard;
import kh.petmily.domain.look_board.form.LookBoardConditionForm;
import kh.petmily.domain.look_board.form.LookBoardListForm;
import kh.petmily.mapper.FindBoardMapper;
import kh.petmily.mapper.LookBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LookBoardDao implements BasicDao {

    private final LookBoardMapper mapper;
    private final FindBoardMapper findBoardMapper;

    @Override
    public LookBoard findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        LookBoard lookBoard = (LookBoard) obj;

        List<Integer> list = mapper.selectMatchedFa(lookBoard);

        list.removeIf(Objects::isNull);

        log.info("look insert : list = {}", list);

        if (!list.isEmpty()) {
            mapper.insert(lookBoard);

            int laNumber = mapper.selectByPkMax();
            log.info("look insert : MAX laNumber = {}", laNumber);

            mapper.changeState(laNumber);

            for (Integer i : list) {
                mapper.changeStateFind(i);
            }
        } else {
            mapper.insert(lookBoard);
        }
    }

    @Override
    public void update(DomainObj obj) {
        LookBoard lookBoard = (LookBoard) obj;

        mapper.backState(lookBoard.getLaNumber());

        LookBoard old_lookBoard = mapper.selectByPk(lookBoard.getLaNumber());
        List<Integer> old_list = mapper.selectMatchedFa(old_lookBoard);
        log.info("update : old_list = {}", old_list);

        if (old_list.size() != 0) {
            for (Integer i : old_list) {
                mapper.backStateFind(i);
            }
        }

        List<Integer> list = mapper.selectMatchedFa(lookBoard);
        log.info("update : list = {}", list);

        if (list.size() != 0) {
            mapper.update(lookBoard);
            mapper.changeState(lookBoard.getLaNumber());

            for (Integer i : list) {
                mapper.changeStateFind(i);
            }
        } else {
            mapper.update(lookBoard);
        }
    }

    @Override
    public void delete(int pk) {
        LookBoard lookBoard = mapper.selectByPk(pk);
        List<Integer> list = mapper.selectMatchedFa(lookBoard);
        log.info("delete : list = {}", list);

        if (list.size() != 0) {
            for (Integer i : list) {
                mapper.backStateFind(i);
            }
        }

        mapper.delete(pk);
    }

    public int selectCountBymNumber(int mNumber) {
        return mapper.selectCountBymNumber(mNumber);
    }

    public List<LookBoardListForm> selectIndexBymNumber(int start, int end, int mNumber) {
        List<LookBoardListForm> result = new ArrayList<>();
        List<LookBoard> list = mapper.selectIndexBymNumber(start, end, mNumber);

        for (LookBoard board : list) {
            LookBoardListForm li = new LookBoardListForm(
                    board.getLaNumber(),
                    board.getMNumber(),
                    selectName(board.getLaNumber()),
                    board.getSpecies(),
                    board.getKind(),
                    board.getLocation(),
                    board.getAnimalState(),
                    board.getImgPath(),
                    board.getWrTime().format(getFormatter()),
                    board.getTitle(),
                    board.getViewCount()
            );

            result.add(li);
        }

        return result;
    }

    public int selectCountWithCondition(LookBoardConditionForm form) {
        return mapper.selectCountWithCondition(form);
    }

    public List<LookBoardListForm> selectIndexWithCondition(int start, int end, LookBoardConditionForm form) {
        List<LookBoardListForm> result = new ArrayList<>();
        List<LookBoard> list = mapper.selectIndexWithCondition(
                start, end, form.getSpecies(), form.getAnimalState(), form.getKeyword(), form.getSort()
        );

        for (LookBoard board : list) {
            LookBoardListForm li = new LookBoardListForm(
                    board.getLaNumber(),
                    board.getMNumber(),
                    selectName(board.getLaNumber()),
                    board.getSpecies(),
                    board.getKind(),
                    board.getLocation(),
                    board.getAnimalState(),
                    board.getImgPath(),
                    board.getWrTime().format(getFormatter()),
                    board.getTitle(),
                    board.getViewCount()
            );

            result.add(li);
        }

        return result;
    }

    public int selectCount() {
        return mapper.selectCount();
    }

    public List<LookBoardListForm> selectIndex(int start, int end) {
        List<LookBoardListForm> result = new ArrayList<>();
        List<LookBoard> list = mapper.selectIndex(start, end);

        for (LookBoard board : list) {
            LookBoardListForm li = new LookBoardListForm(
                    board.getLaNumber(),
                    board.getMNumber(),
                    selectMemberId(board.getLaNumber()),
                    selectName(board.getLaNumber()),
                    board.getSpecies(),
                    board.getKind(),
                    board.getLocation(),
                    board.getAnimalState(),
                    board.getImgPath(),
                    board.getWrTime().format(getFormatter()),
                    board.getTitle(),
                    board.getViewCount()
            );

            result.add(li);
        }

        return result;
    }

    public String selectMemberId(int pk) {
        return mapper.selectMemberId(pk);
    }

    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    public int updateViewCount(int pk) {
        return mapper.updateViewCount(pk);
    }

    public int selectMatchedCount(FindBoard findBoard) {
        List<Integer> list = findBoardMapper.selectMatchedLa(findBoard);
        return list.size();
    }

    public List<LookBoardListForm> selectMatchedIndex(int start, int end, FindBoard findBoard) {
        List<Integer> list = findBoardMapper.selectMatchedLa(findBoard);
        List<LookBoard> lookBoardList = new ArrayList<>();

        for (Integer i : list) {
            lookBoardList.add(mapper.selectByPk(i));
        }

        List<LookBoardListForm> result = new ArrayList<>();

        for (LookBoard l : lookBoardList) {
            LookBoardListForm li = new LookBoardListForm(
                    l.getLaNumber(),
                    l.getMNumber(),
                    selectName(l.getLaNumber()),
                    l.getSpecies(),
                    l.getKind(),
                    l.getLocation(),
                    l.getAnimalState(),
                    l.getImgPath(),
                    l.getWrTime().format(getFormatter()),
                    l.getTitle());

            result.add(li);
        }

        return result;
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}