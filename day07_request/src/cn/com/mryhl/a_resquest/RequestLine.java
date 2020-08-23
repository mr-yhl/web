package cn.com.mryhl.a_resquest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RequestLine")
public class RequestLine extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request);
        System.out.println("获取请求方式：" + request.getMethod());
        System.out.println("获取虚拟路径：" + request.getContextPath());
        System.out.println("获取URI：" + request.getRequestURI());
        System.out.println("获取URL：" + request.getRequestURL());
        System.out.println("获取请求协议和版本：" + request.getProtocol());
        System.out.println("获取客户端ip：" + request.getRemoteAddr());
    }
}