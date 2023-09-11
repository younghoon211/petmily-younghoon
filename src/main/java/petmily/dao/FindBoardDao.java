package petmily.dao;

import petmily.domain.DomainObj;
import petmily.domain.admin_form.AdminBoardConditionForm;
import petmily.domain.find_board.FindBoard;
import petmily.domain.find_board.form.FindBoardConditionForm;
import petmily.domain.find_board.form.FindBoardListForm;
import petmily.domain.look_board.LookBoard;
import petmily.mapper.FindBoardMapper;
import petmily.mapper.LookBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FindBoardDao implements BasicDao {

    private final FindBoardMapper mapper;
    private final LookBoardMapper lookBoardMapper;

    @Override
    public FindBoard findByPk(int pk) {
        return mapper.selectByPk(pk);
    }

    @Override
    public void insert(DomainObj obj) {
        FindBoard findBoard = (FindBoard) obj;

        List<Integer> matchedLaList = mapper.selectMatchedLa(findBoard);
        log.info("insert : matchedLaList = {}", matchedLaList);

        if (matchedLaList.isEmpty()) {
            mapper.insert(findBoard);
            return;
        }

        mapper.insert(findBoard);

        int pk = mapper.selectByPkMax();
        matchOnlyNotSameUser(pk);
    }

    @Override
    public void update(DomainObj obj) {
        FindBoard findBoard = (FindBoard) obj;
        int pk = findBoard.getFaNumber();

        // (1) 수정할 find '실종'으로, 이 글에 매칭된 look '보호'로 전부 backState
        FindBoard initFindBoard = mapper.selectByPk(pk);

        List<Integer> initMatchedLaList = mapper.selectMatchedLa(initFindBoard);
        log.info("update : initMatchedLaList = {}", initMatchedLaList);

        mapper.backState(pk);

        if (!initMatchedLaList.isEmpty()) {
            for (Integer initLaNumber : initMatchedLaList) {
                mapper.backStateLook(initLaNumber);
            }
        }

        // (2) 수정된 내용에 따라 매칭여부 결정
        List<Integer> matchedLaList = mapper.selectMatchedLa(findBoard);
        log.info("update : matchedLaList = {}", matchedLaList);

        boolean existMatchedLa = !matchedLaList.isEmpty();

        mapper.update(findBoard);

        if (existMatchedLa) {
            matchOnlyNotSameUser(pk);
        }

        maintainMatchingForLook();
    }

    @Override
    public void delete(int pk) {
        FindBoard findBoard = mapper.selectByPk(pk);

        List<Integer> matchedLaList = mapper.selectMatchedLa(findBoard);
        log.info("delete : matchedLaList = {}", matchedLaList);

        boolean existMatchedLa = !matchedLaList.isEmpty();

        if (!existMatchedLa) {
            mapper.delete(pk);
            return;
        }

        if (isNotSameUser()) {
            for (Integer laNumber : matchedLaList) {
                mapper.backStateLook(laNumber);
            }
        }

        mapper.delete(pk);

        maintainMatchingForLook();
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

    // 게시판 - 총 게시글 수 조회
    public int selectCountWithCondition(String species, String animalState, String keyword) {
        return mapper.selectCountWithCondition(species, animalState, keyword);
    }

    // 게시판 - 리스트 페이지 index
    public List<FindBoardListForm> selectIndexWithCondition(int start, int end, FindBoardConditionForm form) {
        List<FindBoardListForm> findBoardListForms = new ArrayList<>();

        List<FindBoard> findBoards = mapper.selectIndexWithCondition(
                start, end,
                form.getSpecies(),
                form.getAnimalState(),
                form.getKeyword(),
                form.getSort()
        );

        addFindBoardListForms(findBoardListForms, findBoards);

        return findBoardListForms;
    }


    // ========================= 마이페이지 ==========================
    // 내가 쓴 게시글 - 총 게시글 수 조회
    public int selectCountBymNumber(int mNumber) {
        return mapper.selectCountBymNumber(mNumber);
    }

    // 내가 쓴 게시글 - 리스트 페이지 index
    public List<FindBoardListForm> selectIndexBymNumber(int start, int end, int mNumber) {
        List<FindBoardListForm> findBoardListForms = new ArrayList<>();
        List<FindBoard> findBoards = mapper.selectIndexBymNumber(start, end, mNumber);

        addFindBoardListForms(findBoardListForms, findBoards);

        return findBoardListForms;
    }


    // ======================== 매칭 관련 시스템 ==========================
    // 찾아요 매칭된 페이지 - 총 게시글 수 조회
    public int selectCountFindMatching(int mNumber) {
        return mapper.selectCountFindMatching(mNumber);
    }

    // 찾아요 매칭된 페이지 index
    public List<FindBoardListForm> selectIndexFindMatching(int start, int end, int mNumber) {
        List<FindBoardListForm> findBoardListForms = new ArrayList<>();
        List<FindBoard> findBoards = mapper.selectIndexFindMatching(start, end, mNumber);

        addFindBoardListForms(findBoardListForms, findBoards);

        return findBoardListForms;
    }

    // 봤어요에 매칭된 찾아요 리스트 - 총 게시글 수 조회
    public int selectCountFindMatchedLook(LookBoard lookBoard) {
        return lookBoardMapper.selectCountFindMatchedLook(
                lookBoard.getSpecies(),
                lookBoard.getKind(),
                lookBoard.getLocation()
        );
    }

    // 봤어요에 매칭된 찾아요 리스트 index
    public List<FindBoardListForm> selectIndexFindMatchedLook(int start, int end, LookBoard lookBoard) {
        List<FindBoard> findBoards = lookBoardMapper.selectIndexFindMatchedLook(start, end, lookBoard.getSpecies(), lookBoard.getKind(), lookBoard.getLocation());
        List<FindBoardListForm> findBoardListForms = new ArrayList<>();

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
                    findBoard.getTitle()
            );

            findBoardListForms.add(listForm);
        }

        return findBoardListForms;
    }


    // ======================== 관리자 페이지 ==========================
    // 리스트 페이지 index
    public List<FindBoardListForm> selectIndexByPkDesc(int start, int end, AdminBoardConditionForm form) {
        List<FindBoardListForm> findBoardListForms = new ArrayList<>();
        List<FindBoard> findBoards = mapper.selectIndexByPkDesc(start, end, form.getSpecies(), form.getAnimalState(), form.getKeyword());

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


    // ======================== private 메소드 ==========================

    // 다른 유저일 경우만 매치 (else: 오류로 인해 같은 유저끼리 매치돼 있다면 backState)
    private void matchOnlyNotSameUser(int pk) {
        List<FindBoard> findBoards = mapper.selectAll();

        for (FindBoard faBoard : findBoards) {
            List<Integer> matchedLa = mapper.selectMatchedLa(faBoard);

            for (Integer laNumber : matchedLa) {
                LookBoard matchedLaBoard = lookBoardMapper.selectByPk(laNumber);

                if (faBoard.getMNumber() != matchedLaBoard.getMNumber()) {
                    mapper.changeState(pk);
                    mapper.changeStateLook(laNumber);
                } else {
                    mapper.backState(pk);
                    mapper.backStateLook(laNumber);
                }
            }
        }
    }

    // look중 find와 매칭조건 일치하는 글 있으면 해당 look 매칭상태 유지
    private void maintainMatchingForLook() {
        List<FindBoard> findBoards = mapper.selectAll();

        for (FindBoard faBoard : findBoards) {
            List<Integer> matchedLa = mapper.selectMatchedLa(faBoard);

            for (Integer laNumber : matchedLa) {
                LookBoard matchedLaBoard = lookBoardMapper.selectByPk(laNumber);

                if (faBoard.getMNumber() != matchedLaBoard.getMNumber()) {
                    mapper.changeStateLook(laNumber);
                }
            }
        }
    }

    // find와 find에 매치된 look작성자가 다를 경우 true
    private boolean isNotSameUser() {
        List<FindBoard> findBoards = mapper.selectAll();

        for (FindBoard faBoard : findBoards) {
            List<Integer> matchedLa = mapper.selectMatchedLa(faBoard);

            for (Integer laNumber : matchedLa) {
                LookBoard matchedLaBoard = lookBoardMapper.selectByPk(laNumber);

                if (faBoard.getMNumber() != matchedLaBoard.getMNumber()) {
                    return true;
                }
            }
        }

        return false;
    }

    private void addFindBoardListForms(List<FindBoardListForm> findBoardListForms, List<FindBoard> findBoards) {
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
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    private String selectMemberId(int pk) {
        return mapper.selectMemberId(pk);
    }
}