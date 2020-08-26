package cn.com.mryhl.c_case;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;

@WebServlet("/LastTimeServlet")
public class LastTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应编码
        response.setContentType("text/html;charset=utf-8");
        // i.获取上次访问时间
        // 1.获取指定名称的cookie
        Cookie[] cookies = request.getCookies();
        Cookie cookie = CookieUtils.queryByName(cookies, "last_time");
        // 2.判断是否为第一次访问
        if (cookie == null) {
            response.getWriter().write("您好，欢迎您的到来..");
        } else {
            String last_time = cookie.getValue(); // 2020-08-25 12:05:11
            // 解码
            last_time = URLDecoder.decode(last_time, "UTF-8");
            response.getWriter().write("欢迎回来，" + last_time);
        }
        // ii.记录本次访问时间
        // 1.获取当前时间
        LocalDateTime now = LocalDateTime.now();
        String current_time = now.toString();// 默认获取 2020-08-25 12:05:11 格式
        // 编码
        current_time = URLEncoder.encode(current_time, "UTF-8");
        // 2.设置cookie并指定持久化
        cookie = new Cookie("last_time", current_time);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        // 3.写入到浏览器
        response.addCookie(cookie);

    }
}