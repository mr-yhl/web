<%@ page import="cn.com.mryhl.c_case.CookieUtils" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: 11063
  Date: 2020/8/26
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>bbs</title>
    </head>
    <body>
        <h1>yhl博客系统...</h1>
        <%
            // i.获取上次访问时间
            // 1.获取指定名称的cookie
            Cookie[] cookies = request.getCookies();
            Cookie cookie = CookieUtils.queryByName(cookies, "last_time");
            // 2.判断是否为第一次访问
            if (cookie == null) {
                out.write("<h1>您好，欢迎您的到来..</h1>");
            } else {
                String last_time = cookie.getValue();  // 2020-08-25 12:05:11
                // 解码
                last_time = URLDecoder.decode(last_time, "UTF-8");
                out.write("<h1>欢迎回来，" + last_time+"</h1>");
            }

            // ii.记录本次访问时间
            // 1.获取当前时间
            LocalDateTime now = LocalDateTime.now();
            String current_time = now.toString();// 默认获取 2020-08-25 12:05:11 格式
            // 编码
            current_time = URLEncoder.encode(current_time, "UTF-8");

            // 2.设置cookie并指定持久化
            cookie = new Cookie("last_time", current_time);
            cookie.setMaxAge(60 * 60 * 24 * 30);

            // 3.写入到浏览器
            response.addCookie(cookie);

        %>


        <table border="1">
            <tr>
                <td>我是jsp中定义的表格...</td>
            </tr>
        </table>
        <hr>



        <table border="1">
            <tr>
                <td>我是jsp中定义的表格...</td>
            </tr>
        </table>
        <hr>

        <table border="1">
            <tr>
                <td>我是jsp中定义的表格...</td>
            </tr>
        </table>
    </body>
</html>
