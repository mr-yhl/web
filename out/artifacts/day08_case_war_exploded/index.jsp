<%--
  Created by IntelliJ IDEA.
  User: 11063
  Date: 2020/8/26
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>用户登陆</title>
    </head>
    <body>
        <h3>用户登录</h3>
        <form action="/day08_case_war_exploded/LoginServlet" method="post">
            用户名: <input type="text" id="username" name="username"> <br>
            密&emsp;码: <input type="password" id="password" name="password"> <br>
            验证码: <input type="text" id="client_code" name="client_code"><img
                src="/day08_case_war_exploded/CheckcodeServlet"/> <br>
            <span>
              <%
                  // 获取request域中的提示信息
                  String error = (String) request.getAttribute("error");
                  if (error != null) {
                      out.write(error);
                  }
              %>
      </span><br>
            <input type="submit" value="登陆">
        </form>
    </body>
</html>
