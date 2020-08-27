<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 8:59  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>demo01</title>
    </head>
    <body>
        <h3>脚本和注释</h3>
        <%--三个脚本声明方式--%>
        <%--
            <% 代码 %>:脚本片段,生成在java文件中service方法中,每次请求的时候都会执行
            <%! 代码  %>:声明片段,生成在java文件中的成员位置
            <%＝代码 　％＞:输出脚本片段,相当于 out.print("代码")方法，输出到jsp页面
        --%>
        <%
            System.out.println("hello jsp");
            out.write("获取的值:"+num);
        %>

        <%!
            int num = 10;

        %>
        <%=num %>
        <%--三种注释方式--%>
        <%--
            1.静态注释  <!-- -->
            2.jsp注释   < % -- --% >
            3.java注释  //  , /*  */,/**  */
        --%>
        <!--<h3>你好</h3>-->
        <%--<h3>你好</h3>--%>
        <%
            // Java注释
            /* 多行注释 */
            /**
             * 文档注释
             */

        %>

    </body>
</html>
