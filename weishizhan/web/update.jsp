<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改书籍信息</h3>
    <form action="${pageContext.request.contextPath}/UpdateServlet" method="post">

        <%--隐藏域用于携带id属性--%>
        <input type="hidden" name="bookId" value="${book.bookId}">

        <div class="form-group">
            <label for="bookName">书名：</label>
            <input type="text" class="form-control" id="bookName" value="${book.bookName}" name="bookName" placeholder="请输入书名" />
        </div>


        <div class="form-group">
            <label for="author">作者：</label>
            <input type="text" class="form-control" id="author" value="${book.author}"  name="author" placeholder="请输入作者" />
        </div>


        <div class="form-group">
            <label for="price">价格：</label>
            <input type="text" class="form-control" id="price" name="price" value="${book.price}" placeholder="请输入价格"/>
        </div>



        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>