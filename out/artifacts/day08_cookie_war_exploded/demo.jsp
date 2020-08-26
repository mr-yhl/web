<%--
  Created by IntelliJ IDEA.
  User: 11063
  Date: 2020/8/26
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>demo</title>
    </head>
    <body>
        <table border="1">
            <tr>
                <td>我是jsp中定义的表格...</td>
            </tr>
        </table>
        <%
            System.out.println("jsp中的脚本，相当于servlet的service方法..");

            out.write("jsp响应动态资源");
            // response.getWriter().write("response响应动态资源...");
        %>
    </body>
</html>
