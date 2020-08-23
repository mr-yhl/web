package cn.com.mryhl.a_redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AServlet")
public class AServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AServlet执行了....");

       /* // 1.设置响应状态码
        response.setStatus(302);
        // 2.设置响应头Location跳转的地址
        response.setHeader("location","/day07-response/BServlet");*/

        // 专门提供了一个重定向的方法

         response.sendRedirect(request.getContextPath() + "/BServlet");

        //response.sendRedirect("https://www.baidu.com");
    }

}