package kh.petmily.interceptor;

import kh.petmily.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("AdminCheckInterceptor 실행");

        String requestURI = request.getRequestURI();
        Member userGrade = getUserGrade(request);

        if (requestURI.contains("/admin") && userGrade.getGrade().equals("일반")) {
            response.sendRedirect("/error");

            return false;
        }

        return true;
    }

    private Member getUserGrade(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("authUser");

        return member;
    }
}
