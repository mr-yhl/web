<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 14:39  
--%>
<html>
    <head>
        <title>demo01</title>
    </head>
    <body>
        <h1>El表达式只能从域对象获取值</h1>
        <%
            // 模拟servlet,向四个域存值
            pageContext.setAttribute("username", "公孙代"); // 仅jsp中存在...
            pageContext.setAttribute("age", 22);
            request.setAttribute("age", 18);
            session.setAttribute("sex", "女");
            application.setAttribute("address", "北海郡");
        %>
        <h3>EL标准语法</h3>
        <%--el底层进行了非空处理--%>
        ${pageScope.username} <br>
        ${requestScope.age} <br>
        ${sessionScope.sex} <br>
        ${applicationScope.address} <br>



        <h3>EL的简单语法</h3>
        <%--${键名} 从最小的域开始匹配,匹配到返回数据--%>
        ${username} <br>
        ${age}<br>
        ${sex}<br>
        ${address} <br>




        <%--注意事项:使用这种方法,应避免变量名重复--%>
    </body>
</html>
