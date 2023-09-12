package petmily.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginCheckInterceptor 실행");

        HttpSession session = request.getSession(false);

        String originalURI = request.getRequestURI();
        String queryString = request.getQueryString();

        if (session == null || session.getAttribute("authUser") == null) {
            log.info("미인증 사용자");

            if (queryString != null) {
                originalURI += "?" + queryString;
            }

            request.getSession().setAttribute("originalRequest", originalURI);
            response.sendRedirect("/login");

            return false;
        }

        return true;
    }
}
