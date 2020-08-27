<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 9:55  
--%>
<html>
    <head>
        <title>demo05</title>
    </head>
    <body>
        <%--
        内置对象: 一共有9个,不需要创建和获取,可以直接使用
        默认是8个,异常对象默认关闭
        常用的四个:
        pageContext:域对象,最小的域对象,当前页面中共享数据
            可以获取其他八个对象
        request:
            接收请求
            一次请求中,共享数据
        respond:
            响应结果
        out:
            jsp页面特有的字符输出对象
            ☆ print()
            write()
        --%>
        <%
            // pageContext域对象
            pageContext.setAttribute("username","lucy");
            // pageContext获取其他八个内置对象

        %>

        <%=pageContext.getAttribute("username") %>
    </body>
</html>
