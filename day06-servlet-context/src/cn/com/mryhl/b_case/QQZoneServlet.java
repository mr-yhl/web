package cn.com.mryhl.b_case;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/QQZoneServlet")
public class QQZoneServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

        // 获取上下文对象
        ServletContext servletContext = getServletContext();

        // 向域中存值
        servletContext.setAttribute("count", 0);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<h1>QQ空间...</h1>");

        // 从域中获取访问人数+1，并更新到域中
        ServletContext servletContext = getServletContext();
        Integer count = (Integer) servletContext.getAttribute("count");
        servletContext.setAttribute("count", ++count);

        response.getWriter().write("<div>您是第"+count+"位访问此QQ空间的友人...</div>");
    }

}