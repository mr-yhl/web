package cn.com.mryhl.web.servlet;

import cn.com.mryhl.domain.User;
import cn.com.mryhl.service.UserServic;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckUserServlet")
public class CheckUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.接收请求参数
        String username = request.getParameter("username");

        // 2.调用service查询
        UserServic userService = new UserServic();
        User user = userService.findByUsername(username);

        // 3.创建核心转换器对象
        ObjectMapper om = new ObjectMapper();

        // 4.将user转为json
        String json = om.writeValueAsString(user);

        // 5.通过response响应
        response.setContentType("applcation/json;charset=utf-8");
        response.getWriter().write(json);

    }
}