package cn.com.mryhl.web.servlet;

import cn.com.mryhl.c_util.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/LoginServletPro")
public class LoginServletPro extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // 连接数据库
            Connection connection = JdbcUtils.getConnection();
            // 编写sql
            String sql = "select * from user where username = ? and password = ?";
            // 获取sql执行对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            // 发送sql并接收返回结果
            ResultSet resultSet = preparedStatement.executeQuery();
            // 进行判断
            if (resultSet.next()) {
                // 从数据库返回结果中获取结果
                String loginUsername = resultSet.getString("username");
                // 存入session中
                request.getSession().setAttribute("loginUsername", loginUsername);
                response.sendRedirect(request.getContextPath() + "/list.jsp");

            } else {
                // 登陆失败
                request.setAttribute("error", "用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }


        } catch (SQLException e) {
            throw new RuntimeException("数据库连接失败");
        }
    }
}