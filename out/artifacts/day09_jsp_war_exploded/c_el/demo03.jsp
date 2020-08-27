<%@ page import="cn.com.mryhl.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 15:08  
--%>
<html>
    <head>
        <title>demo03</title>
    </head>
    <body>
        <h3>三元运算符</h3>

        <%
            Integer a = 2;

            Integer b = 5;

            request.setAttribute("a", a);
            request.setAttribute("b", b);

            User user = new User();
            request.setAttribute("user", user);

            List list = new ArrayList();
            list.add("哈哈");
            request.setAttribute("list", list);
        %>

        ${a > b ? "a大" : "b大" }


        <h3>非空判断</h3>
        ${not empty user} <br> <%-- if(user != null){xxx} --%>
        ${not empty list} <br> <%-- if(list !=null && list.size() > 0 ){xxx} --%>


        <h3>空值判断</h3>

        ${empty user} <br><%-- if(user == null){xxx} --%>
        ${empty list} <br><%-- if(list ==null || list.size() < 1 ){xxx} --%>


    </body>
</html>
