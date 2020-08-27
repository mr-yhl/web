<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 15:22  
--%>
<html>
    <head>
        <title>demo04</title>
    </head>
    <body>
        <h3>补充知识</h3>
       <%-- 1.忽略当前jsp页面中所有的el表达式
        设置jsp中page指令中：isELIgnored="true" 属性
        --%>

       <%-- 2.忽略单个el 加 \--%>
        \${pageContext.request.contextPath} <br>

        <script>
            // 我们在es6中讲过模板字符串

            let a = 10;

            let str = `哈哈，您购买了\${a}件商品.....`

            document.write(str)
        </script>

    </body>
</html>
