package cn.com.mryhl.b_rues;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/RequestParam")
public class RequestParam extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户名
        String username = request.getParameter("username");
        System.out.println("用户：" + username);
        // 获取年龄
        String age = request.getParameter("age");
        System.out.println("年龄：" + age);
        // 获取爱好
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println("爱好：" + Arrays.toString(hobbies));
        System.out.println("--------------------------");
        Map<String, String[]> parameterMap = request.getParameterMap();
        // map集合的lambda表达式遍历
        parameterMap.forEach((key, value) -> {
            System.out.println(key + "=" + Arrays.toString(value));
        });
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 指定服务器post提交的解码方式为utf-8
        request.setCharacterEncoding("utf-8");
        System.out.println("post提交");
        // 由于代码冗余，可以让post调用get
        this.doGet(request, response);
    }
}