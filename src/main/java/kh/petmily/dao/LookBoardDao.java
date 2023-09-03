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

        List<Integer> matchedFaList = mapper.selectMatchedFa(lookBoard);
        log.info("insert : matchedFaList = {}", matchedFaList);

        if (matchedFaList.isEmpty()) {
            mapper.insert(lookBoard);
            return;
        }

        mapper.insert(lookBoard);

        int pk = mapper.selectByPkMax();
        matchOnlyNotSameUser(pk);
    }

    @Override
    public void update(DomainObj obj) {
        LookBoard lookBoard = (LookBoard) obj;
        int pk = lookBoard.getLaNumber();

        // (1) 수정할 look '보호'로, 이 글에 매칭된 find '실종'으로 전부 backState
        LookBoard initLookBoard = mapper.selectByPk(pk);

        List<Integer> initMatchedFaList = mapper.selectMatchedFa(initLookBoard);
        log.info("update : initMatchedFaList = {}", initMatchedFaList);

        mapper.backState(pk);

        if (!initMatchedFaList.isEmpty()) {
            for (Integer initFaNumber : initMatchedFaList) {
                mapper.backStateFind(initFaNumber);
            }
        }

        // (2) 수정된 내용에 따라 매칭여부 결정
        List<Integer> matchedFaList = mapper.selectMatchedFa(lookBoard);
        log.info("update : matchedFaList = {}", matchedFaList);

        boolean existMatchedFa = !matchedFaList.isEmpty();

        mapper.update(lookBoard);

        if (existMatchedFa) {
            matchOnlyNotSameUser(pk);
        }

        maintainMatchingForFind();
    }

    @Override
    public void delete(int pk) {
        LookBoard lookBoard = mapper.selectByPk(pk);

        List<Integer> matchedFaList = mapper.selectMatchedFa(lookBoard);
        log.info("delete : matchedFaList = {}", matchedFaList);

        boolean existMatchedFa = !matchedFaList.isEmpty();

        if (!existMatchedFa) {
            mapper.delete(pk);
            return;
        }

        if (isNotSameUser()) {
            for (Integer faNumber : matchedFaList) {
                mapper.backStateFind(faNumber);
            }
        }

        mapper.delete(pk);

        maintainMatchingForFind();
    }

    // ========================= 회원 페이지 ========================
    // 회원이름 조회
    public String selectName(int pk) {
        return mapper.selectName(pk);
    }

    // 조회수 증가
    public int updateViewCount(int pk) {
        return mapper.updateViewCount(pk);
    }

    // 총 게시글 수 조회
    public int selectCountWithCondition(LookBoardConditionForm conditionForm) {
        return mapper.selectCountWithCondition(conditionForm);
    }

    // 리스트 페이지 index
    public List<LookBoardListForm> selectIndexWithCondition(int start, int end, LookBoardConditionForm conditionForm) {
        List<LookBoardListForm> lookBoardListForms = new ArrayList<>();

        List<LookBoard> lookBoards = mapper.selectIndexWithCondition(
                start, end,
                conditionForm.getSpecies(),
                conditionForm.getAnimalState(),
                conditionForm.getKeyword(),
                conditionForm.getSort()
        );

        addLookBoardListForms(lookBoardListForms, lookBoards);

        return lookBoardListForms;
    }


    // ========================= 마이페이지 ==========================
    // 내가 쓴 게시글 - 총 게시글 수 조회
    public int selectCountBymNumber(int mNumber) {
        return mapper.selectCountBymNumber(mNumber);
    }

    // 내가 쓴 게시글 - 리스트 페이지 index
    public List<LookBoardListForm> selectIndexBymNumber(int start, int end, int mNumber) {
        List<LookBoardListForm> lookBoardListForms = new ArrayList<>();
        List<LookBoard> lookBoards = mapper.selectIndexBymNumber(start, end, mNumber);

        addLookBoardListForms(lookBoardListForms, lookBoards);

        return lookBoardListForms;
    }


    // ======================== 매칭 관련 시스템 ==========================
    // 봤어요 매칭된 페이지 - 총 게시글 수
    public int selectCountLookMatching(int mNumber) {
        return mapper.selectCountLookMatching(mNumber);
    }

    // 봤어요 매칭된 페이지 index
    public List<LookBoardListForm> selectIndexLookMatching(int start, int end, int mNumber) {
        List<LookBoardListForm> lookBoardListForms = new ArrayList<>();
        List<LookBoard> lookBoards = mapper.selectIndexLookMatching(start, end, mNumber);

        addLookBoardListForms(lookBoardListForms, lookBoards);

        return lookBoardListForms;
    }

    // 찾아요에 매칭된 봤어요 리스트 - 총 게시글 수 조회
    public int selectCountLookMatchedFind(FindBoard findBoard) {
        return findBoardMapper.selectCountLookMatchedFind(
                findBoard.getSpecies(),
                findBoard.getKind(),
                findBoard.getLocation()
        );
    }

    // 찾아요에 매칭된 봤어요 리스트 - index
    public List<LookBoardListForm> selectIndexLookMatchedFind(int start, int end, FindBoard findBoard) {
        List<LookBoard> lookBoards = findBoardMapper.selectIndexLookMatchedFind(start, end, findBoard.getSpecies(), findBoard.getKind(), findBoard.getLocation());
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
                    lookBoard.getTitle()
            );

            lookBoardListForms.add(listForm);
        }

        return lookBoardListForms;
    }


    // ======================== 관리자 페이지 ==========================
    // 총 게시글 수 조회
    public int selectCount() {
        return mapper.selectCount();
    }

    // 리스트 페이지 index
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



    // ======================== private 메소드 ==========================

    // 다른 유저일 경우만 매치 (else: 오류로 인해 같은 유저끼리 매치돼 있다면 backState)
    private void matchOnlyNotSameUser(int pk) {
        List<LookBoard> lookBoards = mapper.selectAll();

        for (LookBoard laBoard : lookBoards) {
            List<Integer> matchedFa = mapper.selectMatchedFa(laBoard);

            for (Integer faNumber : matchedFa) {
                FindBoard matchedFaBoard = findBoardMapper.selectByPk(faNumber);

                if (laBoard.getMNumber() != matchedFaBoard.getMNumber()) {
                    mapper.changeState(pk);
                    mapper.changeStateFind(faNumber);
                } else {
                    mapper.backState(pk);
                    mapper.backStateFind(faNumber);
                }
            }
        }
    }

    // find중 look과 매칭조건 일치하는 글 있으면 해당 find 매칭상태 유지
    private void maintainMatchingForFind() {
        List<LookBoard> lookBoards = mapper.selectAll();

        for (LookBoard laBoard : lookBoards) {
            List<Integer> matchedFa = mapper.selectMatchedFa(laBoard);

            for (Integer faNumber : matchedFa) {
                FindBoard matchedFaBoard = findBoardMapper.selectByPk(faNumber);

                if (laBoard.getMNumber() != matchedFaBoard.getMNumber()) {
                    mapper.changeStateFind(faNumber);
                }
            }
        }
    }

    // look과 look에 매치된 find작성자가 다를 경우 true
    private boolean isNotSameUser() {
        List<LookBoard> lookBoards = mapper.selectAll();

        for (LookBoard laBoard : lookBoards) {
            List<Integer> matchedFa = mapper.selectMatchedFa(laBoard);

            for (Integer faNumber : matchedFa) {
                FindBoard matchedFaBoard = findBoardMapper.selectByPk(faNumber);

                if (laBoard.getMNumber() != matchedFaBoard.getMNumber()) {
                    return true;
                }
            }
        }

        return false;
    }

    private void addLookBoardListForms(List<LookBoardListForm> lookBoardListForms, List<LookBoard> lookBoards) {
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
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    private String selectMemberId(int pk) {
        return mapper.selectMemberId(pk);
    }
}