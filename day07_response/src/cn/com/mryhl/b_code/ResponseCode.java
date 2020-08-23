package cn.com.mryhl.b_code;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ResponseCode")
public class ResponseCode extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       /* // 指定服务器响应编码
        response.setCharacterEncoding("gbk");*/

       // 统一服务器和客户端编码和解码
        response.setContentType("text/html;charset=utf-8");

        // 获取response的字符输出流
        PrintWriter writer = response.getWriter();
        writer.write("公司计划收购金额38+10+8");

    }

}