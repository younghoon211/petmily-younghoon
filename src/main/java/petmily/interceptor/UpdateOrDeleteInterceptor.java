package petmily.interceptor;

import petmily.domain.member.Member;
import petmily.service.AdoptReviewService;
import petmily.service.BoardService;
import petmily.service.FindBoardService;
import petmily.service.LookBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateOrDeleteInterceptor implements HandlerInterceptor {

    private final BoardService boardService;
    private final FindBoardService findBoardService;
    private final LookBoardService lookBoardService;
    private final AdoptReviewService adoptReviewService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("UpdateOrDeleteInterceptor 실행");

        Member authUser = getAuthUser(request);
        Integer boardWriter = getWriter(request, "bNumber", boardService);
        Integer adoptReviewWriter = getWriter(request, "bNumber", adoptReviewService);
        Integer findWriter = getWriter(request, "faNumber", findBoardService);
        Integer lookWriter = getWriter(request, "laNumber", lookBoardService);

        if (!hasPermission(authUser, boardWriter)|| !hasPermission(authUser, adoptReviewWriter) || !hasPermission(authUser, findWriter) || !hasPermission(authUser, lookWriter)) {
            log.info("일반 회원이 타 회원의 게시글 수정/삭제 시도");
            response.sendRedirect("/error");

            return false;
        }

        return true;
    }

    private boolean hasPermission(Member authUser, Integer writer) {
        return writer == null || authUser.getGrade().equals("관리자") || authUser.getMNumber() == writer;
    }

    private Integer getWriter(HttpServletRequest request, String pk, Object service) {
        String pkValue = request.getParameter(pk);
        if (pkValue == null) {
            return null;
        }

        int pkNumber = Integer.parseInt(pkValue);

        if (service instanceof BoardService) {
            return boardService.getBoard(pkNumber).getMNumber();
        } else if (service instanceof AdoptReviewService) {
            return adoptReviewService.getAdoptReview(pkNumber).getMNumber();
        } else if (service instanceof FindBoardService) {
            return findBoardService.getFindBoard(pkNumber).getMNumber();
        } else if (service instanceof LookBoardService) {
            return lookBoardService.getLookBoard(pkNumber).getMNumber();
        }

        return null;
    }

    private Member getAuthUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Member) session.getAttribute("authUser");
        }
        return null;
    }
}
