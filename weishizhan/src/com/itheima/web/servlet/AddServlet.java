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
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AddServlet执行了........");
        try {
            // 1. 获取浏览器的参数
            // 1.1 获取Map
            Map<String, String[]> parameterMap = request.getParameterMap();
            // 1.2 创建book对象
            Book book = new Book();
            // 1.3 使用BeanUtils封装
            BeanUtils.populate(book,parameterMap);
            // 2. 调用service进行保存数据
            BookService bookService = new BookService();
            // 保存数据
            bookService.save(book);
            //3.重定向再次查询数据
            response.sendRedirect(request.getContextPath()+"/findAll");

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}