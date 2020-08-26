package cn.com.mryhl.b_detail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * cookie时长
 */
@WebServlet("/CookieMaxAge")
public class CookieMaxAge extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     *cookie.setMaxAge(int seconds);
     * 正数：可以设置浏览器的cookie持久化时间，保存到硬盘，到期后自动销毁
     * 负数：默认值（-1），会话（浏览器）关闭，cookie自动销毁
     * 零：立即删除当前cookie
     *
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建cookie
        Cookie cookie = new Cookie("name", "Haley");
        // 设置保存时间
        cookie.setMaxAge(10);
        //响应到浏览器
        response.addCookie(cookie);
    }
}