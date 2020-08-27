<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 10:06  
--%>
<html>
    <head>
        <title>a</title>
    </head>
    <body>
        <%
            request.setCharacterEncoding("utf-8");
            System.out.println("a执行了");
            // 请求转发
            // request.getRequestDispatcher("b.jsp").forward(request, response);
        %>
        <%--不能在内部加注释,传递中文需要在传参数的文件进行编码设置--%>
        <jsp:forward page="b.jsp">
            <jsp:param name="name" value="jack"/>
            <jsp:param name="age" value="18"/>
        </jsp:forward>
    </body>
</html>
