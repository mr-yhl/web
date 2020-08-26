package cn.com.mryhl.b_detail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 发送多个cookie
 */
@WebServlet("/ResponseMultipart")
public class ResponseMultipart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建cookie
        Cookie cookie1 = new Cookie("product", "honorV30pro");
        Cookie cookie2 = new Cookie("price", "2999");
        // 写入到浏览器
        response.addCookie(cookie1);
        response.addCookie(cookie2);

    }
}