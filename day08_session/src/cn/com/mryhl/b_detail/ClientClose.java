package cn.com.mryhl.b_detail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 浏览器关闭，jsessionid不会销毁...
 */
@WebServlet("/ClientClose")
public class ClientClose extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取session对象
        HttpSession session = request.getSession();
        // 存数据
        session.setAttribute("product","HONORV9");
        // 通过session对象,获取jsessionid的值
        String sessionId = session.getId();
        // 我们把服务器默认创建的cookieid进行覆盖,指定存活时间
        Cookie cookie = new Cookie("JSESSIONID", sessionId);
        // 指定路径
        cookie.setPath("/day08_session_war_exploded");
        // 指定存活时间
        cookie.setMaxAge(100);
        // 响应到浏览器
        response.addCookie(cookie);
    }
}