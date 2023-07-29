package kh.petmily.dao;

import kh.petmily.domain.DomainObj;
import kh.petmily.domain.find_board.FindBoard;
import kh.petmily.domain.find_board.form.FindBoardConditionForm;
import kh.petmily.domain.find_board.form.FindBoardListForm;
import kh.petmily.mapper.FindBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

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

        List<Integer> list = mapper.selectMatchedLa(findBoard); // 종,품종,장소가 같은 laNumber를 가져옴. 단, 하나라도 모름일 경우 null 받아옴

        list.removeIf(Objects::isNull); // npe방지 위해 list중 null값 선택적 제거

        log.info("find insert : list = {}", list);

        if (!list.isEmpty()) { // selectMatchedLa(find랑 종,품종,장소가 일치하는 look리스트)가 존재하면
            mapper.insert(findBoard);

            int faNumber = mapper.selectByPkMax(); // 지금 insert된 find pk값 가져와서 faNumber에 초기화하고
            log.info("find insert : MAX faNumber = {}", faNumber);

            mapper.changeState(faNumber); // 최근 업로드하는 find pk값의 animalstate를 매칭됨 으로 바꿈

            for (Integer i : list) {
                mapper.changeStateLook(i); // look부분도 매칭됨 으로 바꿈
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
                    selectName(board.getFaNumber()),
                    board.getSpecies(),
                    board.getKind(),
                    board.getLocation(),
                    board.getAnimalState(),
                    board.getImgPath(),
                    board.getWrTime(),
                    board.getTitle(),
                    board.getViewCount()
            );

            result.add(li);
        }

        return result;
    }

    public int selectCountWithCondition(FindBoardConditionForm form) {
        return mapper.selectCountWithCondition(
                form.getSpecies(), form.getAnimalState(), form.getKeyword());
    }

    public List<FindBoardListForm> selectIndexWithCondition(int start, int end, FindBoardConditionForm form) {
        List<FindBoardListForm> result = new ArrayList<>();
        List<FindBoard> list = mapper.selectIndexWithCondition(
                start, end, form.getSpecies(), form.getAnimalState(), form.getKeyword(), form.getSort()
        );

        for (FindBoard board : list) {
            FindBoardListForm li = new FindBoardListForm(
                    board.getFaNumber(),
                    selectName(board.getFaNumber()),
                    board.getSpecies(),
                    board.getKind(),
                    board.getLocation(),
                    board.getAnimalState(),
                    board.getImgPath(),
                    board.getWrTime(),
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
                    selectMemberId(board.getFaNumber()),
                    selectName(board.getFaNumber()),
                    board.getSpecies(),
                    board.getKind(),
                    board.getLocation(),
                    board.getAnimalState(),
                    board.getImgPath(),
                    board.getWrTime(),
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
                    selectName(f.getFaNumber()),
                    f.getSpecies(),
                    f.getKind(),
                    f.getLocation(),
                    f.getAnimalState(),
                    f.getImgPath(),
                    f.getWrTime(),
                    f.getTitle(),
                    f.getViewCount()
            );

            result.add(li);
        }

        return result;
    }
}