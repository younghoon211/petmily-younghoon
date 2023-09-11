package petmily.interceptor;

import petmily.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("AdminCheckInterceptor 실행");

        Member authUser = getAuthUser(request);

        if (authUser.getGrade().equals("일반")) {
            log.info("일반 등급 유저 관리자 페이지 진입 시도");
            response.sendRedirect("/error");

            return false;
        }

        return true;
    }

    private Member getAuthUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Member) session.getAttribute("authUser");
        }
        return null;
    }
}
