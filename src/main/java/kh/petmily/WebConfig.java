package kh.petmily;

import kh.petmily.interceptor.AdminCheckInterceptor;
import kh.petmily.interceptor.LoginCheckInterceptor;
import kh.petmily.interceptor.BoardPermissionInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginCheckInterceptor loginCheckInterceptor;
    private final AdminCheckInterceptor adminCheckInterceptor;
    private final BoardPermissionInterceptor boardPermissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .order(1)
                .addPathPatterns("/**/auth/**", "/**/admin/**");

        registry.addInterceptor(adminCheckInterceptor)
                .order(2)
                .addPathPatterns("/**/admin/**");

        registry.addInterceptor(boardPermissionInterceptor)
                .order(3)
                .addPathPatterns("/**/auth/delete/**", "/**/auth/modify/**");
    }
}