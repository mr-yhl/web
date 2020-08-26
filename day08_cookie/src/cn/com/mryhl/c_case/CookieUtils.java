package cn.com.mryhl.c_case;

import javax.servlet.http.Cookie;

public class CookieUtils {

    public static Cookie queryByName(Cookie[] cookies, String last_time) {
        // 非空判断
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            // 对比名称
            if (last_time.equals(cookie.getName())) {
                return cookie;
            }

        }
        // 没有匹配上，返回null
        return null;

    }
}
