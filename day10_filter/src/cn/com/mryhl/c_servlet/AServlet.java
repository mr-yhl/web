package cn.com.mryhl.c_servlet;

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
        // 打印
        System.out.println("AServlet执行了.....");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("AServlet执行了.....");

        // 转发到B
        request.getRequestDispatcher("/BServlet").forward(request,response);

    }
}