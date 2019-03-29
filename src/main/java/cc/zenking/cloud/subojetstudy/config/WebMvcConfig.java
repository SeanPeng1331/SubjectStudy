package cc.zenking.cloud.subojetstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: wanghongbo
 * Date: 2018/5/16
 * Time: 11:45
 * Description:
 */
@Configuration
@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public AuthorizeInterceptor getAuthorizeInterceptor() {
        return new AuthorizeInterceptor();
    }

    /**
     * DefaultView
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    /**
     * 功能描述: 拦截器配置 用来做权限等，今后不在过滤器中做
     *
     * @param registry
     * @return: void
     * @author: chengjunchao
     * @date: 2018/6/13 9:46
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //registry.addInterceptor(authorizeInterceptor).addPathPatterns("/**").excludePathPatterns("/webapi/**");
        registry.addInterceptor(getAuthorizeInterceptor()).addPathPatterns("/**");

        super.addInterceptors(registry);
    }
}
