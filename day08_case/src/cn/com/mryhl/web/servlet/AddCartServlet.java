package cn.com.mryhl.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理中文乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 接收请求参数
        String goods = request.getParameter("goods");
        // 响应添加成功
        response.getWriter().write(goods+"商品添加购物车成功....");

        // 从session中获取购物车对象(map集合是引用数据类型)
        Map<String,Integer> cart = (Map<String, Integer>) request.getSession().getAttribute("cart");
        // 判断购物车是否存在
        if (cart == null) {
            cart = new LinkedHashMap<>();
            // 向session添加购物车对象
            request.getSession().setAttribute("cart",cart);
        }
        // 判断商品是否存在
        if (cart.containsKey(goods)) {
            // 存在
            cart.put(goods,cart.get(goods)+1);
        }else{
            cart.put(goods,1);

        }
        //提供查看购物车a标签
        response.getWriter().write("<a href='/day08_case_war_exploded/cart.jsp'>查看购物车</a>");


    }
}