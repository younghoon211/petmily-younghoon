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
        List<LookBoardListForm> lookBoardListForms = new ArrayList<>();
        List<LookBoard> lookBoards = mapper.selectIndexBymNumber(start, end, mNumber);

        for (LookBoard lookBoard : lookBoards) {
            LookBoardListForm listForm = new LookBoardListForm(
                    lookBoard.getLaNumber(),
                    lookBoard.getMNumber(),
                    selectName(lookBoard.getLaNumber()),
                    lookBoard.getSpecies(),
                    lookBoard.getKind(),
                    lookBoard.getLocation(),
                    lookBoard.getAnimalState(),
                    lookBoard.getImgPath(),
                    lookBoard.getWrTime().format(getFormatter()),
                    lookBoard.getTitle(),
                    lookBoard.getViewCount()
            );

            lookBoardListForms.add(listForm);
        }

        return lookBoardListForms;
    }

    public int selectCountWithCondition(LookBoardConditionForm conditionForm) {
        return mapper.selectCountWithCondition(conditionForm);
    }

    public List<LookBoardListForm> selectIndexWithCondition(int start, int end, LookBoardConditionForm conditionForm) {
        List<LookBoardListForm> lookBoardListForms = new ArrayList<>();

        List<LookBoard> lookBoards = mapper.selectIndexWithCondition(
                start, end,
                conditionForm.getSpecies(),
                conditionForm.getAnimalState(),
                conditionForm.getKeyword(),
                conditionForm.getSort()
        );

        for (LookBoard lookBoard : lookBoards) {
            LookBoardListForm listForm = new LookBoardListForm(
                    lookBoard.getLaNumber(),
                    lookBoard.getMNumber(),
                    selectName(lookBoard.getLaNumber()),
                    lookBoard.getSpecies(),
                    lookBoard.getKind(),
                    lookBoard.getLocation(),
                    lookBoard.getAnimalState(),
                    lookBoard.getImgPath(),
                    lookBoard.getWrTime().format(getFormatter()),
                    lookBoard.getTitle(),
                    lookBoard.getViewCount()
            );

            lookBoardListForms.add(listForm);
        }

        return lookBoardListForms;
    }

    public int selectCount() {
        return mapper.selectCount();
    }

    public List<LookBoardListForm> selectIndex(int start, int end) {
        List<LookBoardListForm> lookBoardListForms = new ArrayList<>();
        List<LookBoard> lookBoards = mapper.selectIndex(start, end);

        for (LookBoard lookBoard : lookBoards) {
            LookBoardListForm listForm = new LookBoardListForm(
                    lookBoard.getLaNumber(),
                    lookBoard.getMNumber(),
                    selectMemberId(lookBoard.getLaNumber()),
                    selectName(lookBoard.getLaNumber()),
                    lookBoard.getSpecies(),
                    lookBoard.getKind(),
                    lookBoard.getLocation(),
                    lookBoard.getAnimalState(),
                    lookBoard.getImgPath(),
                    lookBoard.getWrTime().format(getFormatter()),
                    lookBoard.getTitle(),
                    lookBoard.getViewCount()
            );

            lookBoardListForms.add(listForm);
        }

        return lookBoardListForms;
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
        List<Integer> selectMatchedLaList = findBoardMapper.selectMatchedLa(findBoard);
        List<LookBoard> lookBoards = new ArrayList<>();

        for (Integer matchedLa : selectMatchedLaList) {
            lookBoards.add(mapper.selectByPk(matchedLa));
        }

        List<LookBoardListForm> lookBoardListForms = new ArrayList<>();

        for (LookBoard lookBoard : lookBoards) {
            LookBoardListForm listForm = new LookBoardListForm(
                    lookBoard.getLaNumber(),
                    lookBoard.getMNumber(),
                    selectName(lookBoard.getLaNumber()),
                    lookBoard.getSpecies(),
                    lookBoard.getKind(),
                    lookBoard.getLocation(),
                    lookBoard.getAnimalState(),
                    lookBoard.getImgPath(),
                    lookBoard.getWrTime().format(getFormatter()),
                    lookBoard.getTitle());

            lookBoardListForms.add(listForm);
        }

        return lookBoardListForms;
    }

    private String selectMemberId(int pk) {
        return mapper.selectMemberId(pk);
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}