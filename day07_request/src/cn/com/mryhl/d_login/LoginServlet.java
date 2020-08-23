package cn.com.mryhl.d_login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求解码方式
        request.setCharacterEncoding("utf-8");
        // 1.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 2.判断
        if ("jack".equals(username) && "123".equals(password)) {
            // 登录成功
        // 将用户名存储到request域
            request.setAttribute("username", username);
        // 转发到SuccessServlet
            request.getRequestDispatcher("/SuccessServlet").forward(request,
                    response);
        } else {// 登录失败
        // 转发到FailServlet
            request.getRequestDispatcher("/FailServlet").forward(request, response);
        }
    }
}