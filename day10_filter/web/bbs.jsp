<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/28
  Time: 10:28  
--%>
<html>
    <head>
        <title>bbs</title>
    </head>
    <body>
        <h3>弹幕网站</h3>
        <form action="${pageContext.request.contextPath}/WordServlet" method="post">
            <textarea name="word" rows="5" cols="20">

            </textarea> <br>
            <input type="submit" value="发表">

        </form>
    </body>
</html>
