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
        List<FindBoardListForm> findBoardListForms = new ArrayList<>();
        List<FindBoard> findBoards = mapper.selectIndexBymNumber(start, end, mNumber);

        for (FindBoard findBoard : findBoards) {
            FindBoardListForm listForm = new FindBoardListForm(
                    findBoard.getFaNumber(),
                    findBoard.getMNumber(),
                    selectName(findBoard.getFaNumber()),
                    findBoard.getSpecies(),
                    findBoard.getKind(),
                    findBoard.getLocation(),
                    findBoard.getAnimalState(),
                    findBoard.getImgPath(),
                    findBoard.getWrTime().format(getFormatter()),
                    findBoard.getTitle(),
                    findBoard.getViewCount()
            );

            findBoardListForms.add(listForm);
        }

        return findBoardListForms;
    }

    public int selectCountWithCondition(FindBoardConditionForm conditionForm) {
        return mapper.selectCountWithCondition(conditionForm);
    }

    public List<FindBoardListForm> selectIndexWithCondition(int start, int end, FindBoardConditionForm conditionForm) {
        List<FindBoardListForm> findBoardListForms = new ArrayList<>();

        List<FindBoard> findBoards = mapper.selectIndexWithCondition(
                start, end,
                conditionForm.getSpecies(),
                conditionForm.getAnimalState(),
                conditionForm.getKeyword(),
                conditionForm.getSort()
        );

        for (FindBoard findBoard : findBoards) {
            FindBoardListForm listForm = new FindBoardListForm(
                    findBoard.getFaNumber(),
                    findBoard.getMNumber(),
                    selectName(findBoard.getFaNumber()),
                    findBoard.getSpecies(),
                    findBoard.getKind(),
                    findBoard.getLocation(),
                    findBoard.getAnimalState(),
                    findBoard.getImgPath(),
                    findBoard.getWrTime().format(getFormatter()),
                    findBoard.getTitle(),
                    findBoard.getViewCount()
            );

            findBoardListForms.add(listForm);
        }

        return findBoardListForms;
    }

    public int selectCount() {
        return mapper.selectCount();
    }

    public List<FindBoardListForm> selectIndex(int start, int end) {
        List<FindBoardListForm> findBoardListForms = new ArrayList<>();
        List<FindBoard> findBoards = mapper.selectIndex(start, end);

        for (FindBoard findBoard : findBoards) {
            FindBoardListForm listForm = new FindBoardListForm(
                    findBoard.getFaNumber(),
                    findBoard.getMNumber(),
                    selectMemberId(findBoard.getFaNumber()),
                    selectName(findBoard.getFaNumber()),
                    findBoard.getSpecies(),
                    findBoard.getKind(),
                    findBoard.getLocation(),
                    findBoard.getAnimalState(),
                    findBoard.getImgPath(),
                    findBoard.getWrTime().format(getFormatter()),
                    findBoard.getTitle(),
                    findBoard.getViewCount()
            );
            findBoardListForms.add(listForm);
        }

        return findBoardListForms;
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
        List<FindBoardListForm> findBoardListForms = new ArrayList<>();
        List<FindBoard> findBoards = mapper.selectMemberIndex(start, end, mNumber, matched);

        for (FindBoard findBoard : findBoards) {
            FindBoardListForm listForm = new FindBoardListForm(
                    findBoard.getFaNumber(),
                    findBoard.getMNumber(),
                    selectName(findBoard.getFaNumber()),
                    findBoard.getSpecies(),
                    findBoard.getKind(),
                    findBoard.getLocation(),
                    findBoard.getAnimalState(),
                    findBoard.getImgPath(),
                    findBoard.getWrTime().format(getFormatter()),
                    findBoard.getTitle(),
                    findBoard.getViewCount()
            );

            findBoardListForms.add(listForm);
        }

        return findBoardListForms;
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}