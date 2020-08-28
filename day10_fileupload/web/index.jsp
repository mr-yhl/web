<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/28
  Time: 20:30  
--%>
<html>
  <head>
    <title>文件上传</title>
  </head>
  <body>
    <h3>个人信息</h3>

    <form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
      昵称：<input type="text" name="nickname"> <br>
      头像：<input type="file" name="pic"> <br>
      <input type="submit" value="提交">
    </form>
  </body>
</html>
