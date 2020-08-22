package cn.com.mryhl.a_api;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MimeServlet")
public class MimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 下载文件名
        String fileName = "8.txt";

        // 获取ServletContext
        ServletContext servletContext = getServletContext();


        // 获得该文件的mime类型
        String mimeType = servletContext.getMimeType(fileName);

        System.out.println(mimeType);
    }

}