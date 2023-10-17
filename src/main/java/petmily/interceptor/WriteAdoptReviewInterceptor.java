package petmily.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import petmily.domain.member.Member;
import petmily.service.AdoptTempService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
@Slf4j
public class WriteAdoptReviewInterceptor implements HandlerInterceptor {

    private final AdoptTempService adoptTempService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("WriteAdoptReviewInterceptor 실행");

        // 회원이 입양한 동물의 수
        int adoptedCount = adoptTempService.getCountAdoptedStatus(getAuthUser(request).getMNumber());

        if (adoptedCount == 0 && isGeneralUser(request)) {
            log.info("입양한 적 없는 회원이 후기글 작성 시도");

            response.sendRedirect("/adoptReview/auth/write/alert/NotAdopted");

            return false;
        }

        return true;
    }

    private Member getAuthUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member authUser = (Member) session.getAttribute("authUser");
        if (authUser == null) {
            log.info("authUser is null");
        }

        return authUser;
    }

    private boolean isGeneralUser(HttpServletRequest request) {
        return "일반".equals(getAuthUser(request).getGrade());
    }
}