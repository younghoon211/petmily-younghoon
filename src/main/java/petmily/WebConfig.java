package petmily;

import petmily.interceptor.AdminInterceptor;
import petmily.interceptor.LoginInterceptor;
import petmily.interceptor.UpdateOrDeleteInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import petmily.interceptor.WriteAdoptReviewInterceptor;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;
    private final AdminInterceptor adminInterceptor;
    private final UpdateOrDeleteInterceptor updateOrDeleteInterceptor;
    private final WriteAdoptReviewInterceptor writeAdoptReviewInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .order(1)
                .addPathPatterns("/**/auth/**", "/**/admin/**");

        registry.addInterceptor(adminInterceptor)
                .order(2)
                .addPathPatterns("/**/admin/**");

        registry.addInterceptor(updateOrDeleteInterceptor)
                .order(3)
                .addPathPatterns("/**/auth/delete/**", "/**/auth/modify/**");

        registry.addInterceptor(writeAdoptReviewInterceptor)
                .order(4)
                .addPathPatterns("/**/adoptReview/auth/write");
    }
}