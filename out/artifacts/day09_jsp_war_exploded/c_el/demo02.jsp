<%@ page import="cn.com.mryhl.domain.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 14:52  
--%>
<html>
    <head>
        <title>demo02</title>
    </head>
    <body>
        <h3>EL获取User对象</h3>
        <%
            User user = new User("荆卡璜", 16, "荆州");
            request.setAttribute("user",user);
        %>
        ${user} <br> <%--执行默认的tostring方法--%>
        ${user.username} | ${user.age} | ${user.address}


        <h3>EL获取list集合</h3>
        <%
            ArrayList<Object> list = new ArrayList<>();
            list.add("东方盖书");
            list.add("公孙定");
            list.add("厍瑷高");
            list.add(user);
            request.setAttribute("list",list);
        %>
        ${list} <br><%-- 默认对象的toString方法--%>
        ${list[0]} <%-- 集合和数组都这样使用 --%>
        ${list[10]} <%-- 索引越界也不会出错 --%>
        ${list[3].username}


        <h3>el获取map集合</h3>
        <%
            Map<String,Object> map = new HashMap<>();
            map.put("key1","士大夫");
            map.put("key2","现场v");
            map.put("key2","公孙定");
            map.put("key.4","师范");

            request.setAttribute("map",map);
        %>

        ${map} <br><%-- 默认对象的toString方法--%>
        ${map.key1} | ${map.get("key.4")}


    </body>
</html>
