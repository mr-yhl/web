<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/30
  Time: 20:11  
--%>
<html>
    <head>
        <title>register</title>
        <%--引入jq类库--%>
        <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    </head>
    <body>
        用户名: <input type="text" id="username" name="username"> <sapn id="userInfo"></sapn>

        <script>

            $("#username").blur(function () {

                let url = "${pageContext.request.contextPath}/CheckUserServlet";
                let data = 'username=' + this.value;
                $.get(url,data,function (resp) {
                    alert(resp);
                    if (resp == null) {
                        $('#userInfo').css('color', 'green').html('√');
                    } else {
                        $('#userInfo').css('color', 'red').html('此用户名太受欢迎,请更换一个');

                    }});
            });
        </script>
    </body>
</html>
