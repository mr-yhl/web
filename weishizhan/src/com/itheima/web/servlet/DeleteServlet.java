package com.itheima.web.servlet;

import com.itheima.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得需要删除的id
        String bookId = request.getParameter("bookId");

        //2.调用service删除
        //2.1 创建对象
        BookService bookService = new BookService();
        //2.2 删除数据
        bookService.deleteById(   Integer.valueOf(bookId)  );
        //3.重定向到查询的servlet
        response.sendRedirect(request.getContextPath() +"/findAll");
    }
}