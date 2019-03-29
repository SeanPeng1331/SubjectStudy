package cc.zenking.cloud.subojetstudy.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * cookie帮助类
 * Created by cjc on 2017/1/23.
 */

public class CookieHelper {

    private static String cookieName = "token";
    public static String vCode = "vcode";

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String key) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }


    public static void setCookie(HttpServletResponse response, String str) {
        Cookie cookie = new Cookie(cookieName, str.trim());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void setCookie(HttpServletResponse response, String key, String val) {
        Cookie cookie = new Cookie(key, val.trim());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    //获取cookie
    public static String getCookie(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(cookieName)) {
            Cookie cookie = (Cookie) cookieMap.get(cookieName);
            return cookie.getValue().trim();
        } else {
            return null;
        }
    }

    //获取cookie
    public static String getCookie(HttpServletRequest request, String key) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(key)) {
            Cookie cookie = (Cookie) cookieMap.get(key);
            return cookie.getValue().trim();
        } else {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
