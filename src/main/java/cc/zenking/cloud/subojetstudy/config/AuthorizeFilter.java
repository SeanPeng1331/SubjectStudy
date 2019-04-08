package cc.zenking.cloud.subojetstudy.config;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cc.zenking.cloud.redis.RedisService;
import cc.zenking.cloud.subojetstudy.core.auth.GateCurrentUser;
import cc.zenking.cloud.subojetstudy.core.constant.Const;
import cc.zenking.cloud.subojetstudy.util.JsonParserUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Author: chengjunchao
 * @Date: 2018/5/28 14:23
 * @Description:权限过滤器 用来过滤 js css html png jpg 这类资源，目前不需要
 */
@Component
@Slf4j
public class AuthorizeFilter implements Filter {
	
	@Autowired
	private RedisService redisService;
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        CurrentUser currentUser = new CurrentUser();

        // 获取redis的key
        String token = request.getHeader(Const.GATE_TOKEN);
        System.out.println("教育云平台可体验口管理系统 Filter中的token为 ===== " + token);
        String key = Const.LOGIN_GATE + token;
        String ssoValue = redisService.get(key);
        if (StringUtils.isBlank(ssoValue)) {
            log.info("没有登录");
            redirectLogin(request, response);
            return;
        }
        GateCurrentUser gateCurrentUser = JsonParserUtil.DEFAULT.toBean(ssoValue, GateCurrentUser.class);
        if (gateCurrentUser == null || gateCurrentUser.getUser() == null) {
            log.info("没有登录");
            redirectLogin(request, response);
            return;
        }
        // 放到ThreadLocal中
        currentUser.setUser(gateCurrentUser.getUser());
        currentUser.setTenantId(gateCurrentUser.getTenantId());
        currentUser.setApplicationFunctionList(gateCurrentUser.getApplicationFunctionList());
        System.out.print(gateCurrentUser.getUser()+"------");
        System.out.print(gateCurrentUser.getTenantId()+"======");
        System.out.print(gateCurrentUser.getApplicationFunctionList().size()+"======");
        CurrentUser.setCurrentUser(currentUser);
        
        filterChain.doFilter(request, response);
    }

    private void redirectLogin(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(499);
        try {
            response.getWriter().write("timeout");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void destroy() {

    }
}
