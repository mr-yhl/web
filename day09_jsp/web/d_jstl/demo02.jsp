<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.com.mryhl.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 15:51  
--%>
<html>
    <head>
        <title>demo02</title>
    </head>
    <body>
        <h3>普通for循环</h3>
        <%--
        <c:forEach begin="1" end="5" step="1" var="i" >
            ${i}
        </c:forEach>
            begin:起始值
            end:结束值(包含)
            step:步长
            var:遍历的变量

        jstl使用pageContext这个域对象
        --%>
        <c:forEach begin="1" end="5" step="1" var="i">
            <div>${i}</div>
        </c:forEach>

        <h3>增强for</h3>
        <%--
        <c:forEach items="${list}" var="user" varStatus="v">
            ${user}
        </c:forEach>
            items:要遍历的集合
            var:遍历的变量
            varStatus:当前元素的状态
                index:索引
                count:计数器
        --%>
        <%
            List<User> list = new ArrayList<>();
            list.add(new User("王昭君", 18, "峡谷"));
            list.add(new User("电耗子", 22, "祖安"));
            list.add(new User("李白", 39, "大唐"));
            request.setAttribute("list",list);
        %>
        <c:forEach items="${list}" var="user" varStatus="vs">
            <div>${user.username} | 索引：${vs.index} | 计数器： ${vs.count}</div>
        </c:forEach>
    </body>
</html>
