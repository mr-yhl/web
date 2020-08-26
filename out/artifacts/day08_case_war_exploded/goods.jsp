<%--
  Created by IntelliJ IDEA.
  User: 11063
  Date: 2020/8/26
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>goods</title>
    </head>
    <body>
        <h3>商品别表,
            <%
                String user = (String) session.getAttribute("user");
                if (user != null) {
                    out.write("登录人:" + user);
                }

            %>

        </h3>

        <a href='/day08_case_war_exploded/cart.jsp'>查看购物车</a></h3>

        <ul>
            <li>
                <a href="/day08_case_war_exploded/AddCartServlet?goods=华为p40">华为p40--加入购物车</a>
            </li>
            <li>
                <a href="/day08_case_war_exploded/AddCartServlet?goods=小米10">小米10--加入购物车</a>
            </li>
            <li>
                <a href="/day08_case_war_exploded/AddCartServlet?goods=三星note20">三星note20--加入购物车</a>
            </li>
            <li>
                <a href="/day08_case_war_exploded/AddCartServlet?goods=oppox20">oppox20--加入购物车</a>
            </li>
        </ul>
    </body>
</html>
