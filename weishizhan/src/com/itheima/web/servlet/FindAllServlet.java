package com.itheima.web.servlet;

import com.itheima.domain.Book;
import com.itheima.service.BookService;
import sun.misc.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindAllServlet" , urlPatterns = "/findAll")
public class FindAllServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("findall执行了...");
        //1.获得浏览器传输的数据
        //2.创建service对象
        BookService bookService = new BookService();

        //查询书籍
        List<Book> bookList = bookService.findAll();

        System.out.println(bookList);
        //3.响应数据-> 请求转发给list.jsp 显示数据
        request.setAttribute("bookList" , bookList);
        request.getRequestDispatcher("list.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
