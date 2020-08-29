package com.itheima.web.servlet;

import com.itheima.domain.Book;
import com.itheima.service.BookService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "UpdateServlet" ,urlPatterns = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1.获得浏览器传递的数据
            //1.1 创建 Book
            Book book = new Book();
            //1.2 获得请求的map数据
            Map<String, String[]> map = request.getParameterMap();
            //1.3 快速封装即可
            BeanUtils.populate(book, map);

            //2.处理数据
            //2.1 定义对象
            BookService bookService = new BookService();
            //2.2 调用方法
            bookService.update(book);

            //3.响应数据 - 重定向到查询的Servlet
            response.sendRedirect(request.getContextPath() + "/findAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
