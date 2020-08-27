<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="500.jsp" %>
<html>
    <head>
        <title>demo02</title>
    </head>
    <body>
        <%--
        contentType: 用于设置响应体的类型和MIME类型
        language: 指定jsp模版的编程语言   属性值鸡肋,只有java
        import: 导包,可以单起一行
        errorPage: 当前页面报错后可以跳转的页面
        isErrorPage: 升级为错误页面,可以捕获异常
        --%>
        <h3>page指令</h3>
        <%
            Date date = new Date();
            ArrayList<Object> objects = new ArrayList<>();
            int a = 1/0;
        %>
    </body>
</html>
