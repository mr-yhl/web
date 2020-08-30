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
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 接收请求参数
            Map<String, String[]> parameterMap = request.getParameterMap();

            // 封装到user
            User user = new User();
            BeanUtils.populate(user,parameterMap);

            // 调用UserService保存
            UserServic userServic = new UserServic();
            userServic.add(user);

            // 重定向
            response.sendRedirect(request.getContextPath()+"/FindAllServlet");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}