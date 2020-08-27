<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 16:52  
--%>
<html>
    <head>
        <title>list</title>
    </head>
    <body>
        <table border="1" width="500px" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td>编号</td>
                <td>姓名</td>
                <td>性别</td>
                <td>年龄</td>
                <td>地址</td>
                <td>ＱＱ</td>
                <td>邮箱</td>
            </tr>

            <c:forEach items="${list}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.sex}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                </tr>

            </c:forEach>

        </table>


    </body>
</html>
