<%@ page import="java.time.LocalDateTime" %><%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 8:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>index</title>
  </head>
  <body>
   <%
       LocalDateTime now = LocalDateTime.now();
       out.write("当前时间"+now);
   %>


      <h3>el知识学习</h3>
      <a href="${pageContext.request.contextPath}/c_el/demo01.jsp">EL介绍和语法</a>
      <a href="${pageContext.request.contextPath}/c_el/demo02.jsp">EL取值练习</a>
      <a href="${pageContext.request.contextPath}/c_el/demo03.jsp">el表达式运算符</a>
  </body>
</html>
