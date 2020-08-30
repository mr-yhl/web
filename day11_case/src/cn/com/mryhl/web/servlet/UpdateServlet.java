package cn.com.mryhl.web.servlet;

import cn.com.mryhl.domain.User;
import cn.com.mryhl.service.UserServic;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.接收请求参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            // 2.封装到user实体
            User newUser = new User();
            BeanUtils.populate(newUser, parameterMap);
            // 3.调用UserService
            UserServic userService = new UserServic();
            userService.update(newUser);
            // 4.重定向
            response.sendRedirect(request.getContextPath() + "/FindAllServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}