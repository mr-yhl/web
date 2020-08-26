package cn.com.mryhl.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 判断验证码
        // 获取前端提交的验证码
        String client_code = request.getParameter("client_code");
        // 获取session中的
        HttpSession session = request.getSession();
        String code_session = (String) session.getAttribute("code_session");
        // 进行比较
        if (!client_code.equalsIgnoreCase(code_session)) {
            // 验证码错误提示
            request.setAttribute("error","验证码错误...");
            // 转发
            request.getRequestDispatcher("/index.jsp").forward(request,response);
            // 进行拦截
            return;
        }

        // 判断用户名和密码
        // 获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 判断 用户名和密码
        if (!("jack".equals(username) && "123".equals(password))) {
            // 友情提示
            request.setAttribute("error", "用户名或密码错误!!");
            // 转发
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            // 不一致拦截
            return;
        }
        // 将用户信息保存到session中
        session.setAttribute("user",username);
        // 重定向

        response.sendRedirect(request.getContextPath()+"/goods.jsp");

    }
}