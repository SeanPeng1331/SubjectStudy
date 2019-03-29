package cc.zenking.cloud.subojetstudy.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: chengjunchao
 * @Date: 2017/4/4 10:01
 * @Description:在不需要鉴权的控制器上增加
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoAuthorization {
}