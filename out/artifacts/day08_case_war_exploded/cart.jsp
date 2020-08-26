<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: 11063
  Date: 2020/8/26
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>cart</title>
    </head>
    <body>
        <%
            Map<String, Integer> cart = (Map) session.getAttribute("cart");

            if (cart == null) { // 空车
                out.write("<h3>您还未购选商品哦，请选择您新换的商品 <a href='/day08_case_war_exploded/goods.jsp'>浏览商品</a></h3>");
            } else { // 有商品
                out.write("<h3>商品列表</h3>");
                for (String k : cart.keySet()) {
                    Integer v = cart.get(k);
                    out.write("<div>商品：" + k + ",数量：" + v + "</div>");
                }
            }

        %>

    </body>
</html>
