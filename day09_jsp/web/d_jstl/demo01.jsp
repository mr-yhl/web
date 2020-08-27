<%@ page import="cn.com.mryhl.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 15:43  
--%>
<html>
    <head>
        <title>demo01</title>
    </head>
    <body>
        <h3>jstl的if标签</h3>
        <%
            User user = new User();
            user.setUsername("lucy");
            session.setAttribute("user",user);
        %>
        <c:if test="${not empty user}">
            欢迎您：${user.username}
        </c:if>
        <%--c:if标签没有else功能,需重写标签,条件取反--%>
        <c:if test="${empty user}">
            请您登录后浏览....
        </c:if>
    </body>
</html>
