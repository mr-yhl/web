<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/28
  Time: 15:11  
--%>
<html>
  <head>
    <title>监听练习</title>
  </head>
  <body>
      <h3>当前在线人数：${applicationScope.countPerson}</h3>

      <a href="${pageContext.request.contextPath}/LogoutServlet">退出</a>
  </body>
</html>
