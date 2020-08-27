<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 10:06  
--%>
<html>
    <head>
        <title>b</title>
    </head>
    <body>
        <%
            System.out.println("b.jsp执行了...");
            System.out.println(request.getParameter("name"));
            System.out.println(request.getParameter("age"));
        %>
    </body>
</html>
