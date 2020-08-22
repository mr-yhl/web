package cn.com.mryhl.a_quick;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// @WebServlet(name = "QucikServlet",urlPatterns ="/QucikServlet" )
// @WebServlet(urlPatterns ="/QucikServlet" )
// @WebServlet(value ="/QucikServlet" )
@WebServlet("/QuickServlet")
public class QuickServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}