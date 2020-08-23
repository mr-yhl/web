package cn.com.mryhl.a_resquest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/RequestHeader")
public class RequestHeader extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取所有请求头的名称
        Enumeration<String> enumeration = request.getHeaderNames();
        // 2.迭代数据
        while (enumeration.hasMoreElements()) {
            String headName = enumeration.nextElement();
        // 3.获取指定名称的值
            String headValue = request.getHeader(headName);
            System.out.println(headName + "=" + headValue);
        }
    }
}