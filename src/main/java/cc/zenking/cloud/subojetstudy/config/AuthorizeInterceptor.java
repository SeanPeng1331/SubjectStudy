package cc.zenking.cloud.subojetstudy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: chengjunchao
 * @Date: 2018/6/13 10:01
 * @Description:鉴权拦截器
 */
@Component
@Slf4j
public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    private void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.setStatus(499);
            response.getWriter().write("timeout");
        } else {
            response.sendRedirect("login.html");
        }
        response.getWriter().flush();
    }
}
