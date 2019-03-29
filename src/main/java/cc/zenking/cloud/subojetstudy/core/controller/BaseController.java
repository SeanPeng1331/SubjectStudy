package cc.zenking.cloud.subojetstudy.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import cc.zenking.cloud.redis.RedisService;

/**
 * Created by chengjunchao on 2018/3/1 17:08.
 * 控制器基础类
 */
public class BaseController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    RedisService rs;

}
