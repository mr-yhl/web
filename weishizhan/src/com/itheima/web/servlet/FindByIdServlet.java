package com.itheima.web.servlet;

import com.itheima.domain.Book;
import com.itheima.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FindByIdServlet" , urlPatterns = "/updateFindById")
public class FindByIdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * 修改-查询用户数据
     *  目标: 根据id查询到数据 并且返回到update.jsp 展示数据
     *  //1.获得用户id
     *  //2.调用service 并且传入id  返回一个User
     *  //3.1 将user存入到request域
     *  //3.2 请求转发到update.jsp 展示user数据
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得用户id
        String id = request.getParameter("bookId");

        //2.创建service对象
        BookService bookService = new BookService();
        //根据选择的id找查找指定的数据
        Book book = bookService.findById(  Integer.valueOf(id) );

        //3.1 将查询的数据放入域中
        request.setAttribute("book" , book);

        //3.2 请求转发到update.jsp 展示book数据
        request.getRequestDispatcher("/update.jsp").forward(request,response);

    }
}
