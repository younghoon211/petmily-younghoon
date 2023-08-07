package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.find_board.form.FindBoardConditionForm;
import kh.petmily.domain.find_board.form.FindBoardListForm;
import kh.petmily.mapper.FindBoardMapper;
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
public class FindBoardDao implements BasicDao {

    private final FindBoardMapper mapper;

    @Override
    public FindBoard findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        FindBoard findBoard = (FindBoard) obj;

        List<Integer> list = mapper.selectMatchedLa(findBoard);
        list.removeIf(Objects::isNull);

        log.info("find insert : list = {}", list);

        if (!list.isEmpty()) {
            mapper.insert(findBoard);

            int faNumber = mapper.selectByPkMax();
            log.info("find insert : MAX faNumber = {}", faNumber);

            mapper.changeState(faNumber);

            for (Integer i : list) {
                mapper.changeStateLook(i);
            }
        } else {
            mapper.insert(findBoard);
        }
    }

    @Override
    public void update(DomainObj obj) {
        FindBoard findBoard = (FindBoard) obj;

        mapper.backState(findBoard.getFaNumber());

        FindBoard old_findBoard = mapper.selectByPk(findBoard.getFaNumber());
        List<Integer> old_list = mapper.selectMatchedLa(old_findBoard);
        log.info("update : old_list = {}", old_list);

        if (old_list.size() != 0) {
            for (Integer i : old_list) {
                mapper.backStateLook(i);
            }
        }

        List<Integer> list = mapper.selectMatchedLa(findBoard);
        log.info("update : list = {}", list);

        if (list.size() != 0) {
            mapper.update(findBoard);
            mapper.changeState(findBoard.getFaNumber());

            for (Integer i : list) {
                mapper.changeStateLook(i);
            }
        } else {
            mapper.update(findBoard);
        }
    }

    @Override
    public void delete(int pk) {
        FindBoard findBoard = mapper.selectByPk(pk);
        List<Integer> list = mapper.selectMatchedLa(findBoard);
        log.info("delete : list = {}", list);

        if (list.size() != 0) {
            for (Integer i : list) {
                mapper.backStateLook(i);
            }
        }

        mapper.delete(pk);
    }

    public int selectCountBymNumber(int mNumber) {
        return mapper.selectCountBymNumber(mNumber);
    }

    public List<FindBoardListForm> selectIndexBymNumber(int start, int end, int mNumber) {
        List<FindBoardListForm> result = new ArrayList<>();
        List<FindBoard> list = mapper.selectIndexBymNumber(start, end, mNumber);

        for (FindBoard board : list) {
            FindBoardListForm li = new FindBoardListForm(
                    board.getFaNumber(),
                    board.getMNumber(),
                    selectName(board.getFaNumber()),
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

    public int selectCountWithCondition(FindBoardConditionForm form) {
        return mapper.selectCountWithCondition(form);
    }

    public List<FindBoardListForm> selectIndexWithCondition(int start, int end, FindBoardConditionForm form) {
        List<FindBoardListForm> result = new ArrayList<>();
        List<FindBoard> list = mapper.selectIndexWithCondition(
                start, end, form.getSpecies(), form.getAnimalState(), form.getKeyword(), form.getSort()
        );

        for (FindBoard board : list) {
            FindBoardListForm li = new FindBoardListForm(
                    board.getFaNumber(),
                    board.getMNumber(),
                    selectName(board.getFaNumber()),
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

    public List<FindBoardListForm> selectIndex(int start, int end) {
        List<FindBoardListForm> result = new ArrayList<>();
        List<FindBoard> list = mapper.selectIndex(start, end);

        for (FindBoard board : list) {
            FindBoardListForm li = new FindBoardListForm(
                    board.getFaNumber(),
                    board.getMNumber(),
                    selectMemberId(board.getFaNumber()),
                    selectName(board.getFaNumber()),
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

    public int selectMemberCount(int mNumber, String matched) {
        return mapper.selectMemberCount(mNumber, matched);
    }

    public List<FindBoardListForm> selectMemberIndex(int start, int end, int mNumber, String matched) {
        List<FindBoardListForm> result = new ArrayList<>();
        List<FindBoard> list = mapper.selectMemberIndex(start, end, mNumber, matched);

        for (FindBoard f : list) {
            FindBoardListForm li = new FindBoardListForm(
                    f.getFaNumber(),
                    f.getMNumber(),
                    selectName(f.getFaNumber()),
                    f.getSpecies(),
                    f.getKind(),
                    f.getLocation(),
                    f.getAnimalState(),
                    f.getImgPath(),
                    f.getWrTime().format(getFormatter()),
                    f.getTitle(),
                    f.getViewCount()
            );

            result.add(li);
        }

        return result;
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}