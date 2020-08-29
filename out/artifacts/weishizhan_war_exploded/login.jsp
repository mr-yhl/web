<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>login</title>
    <script src="js/jquery-2.1.0.min.js"></script>
    <script>
        $(function () {
            $("#img").on("click",function (event) {
                var x = event.offsetX;//获取点击时鼠标相对图片x坐标
                var y = event.offsetY;//获取点击时鼠标相对图片y坐标
                $.ajax({
                    //请求的路径

                    //传递参数 坐标x和y

                    success: function (data) {
                        //alert(data);
                        if("验证成功"==data){
                            $("#msg").html(data).css("color","green");
                            $("[type='submit']").prop("disabled",false);
                            $("#img").off();
                            return;
                        }else{
                            $("#msg").html(data).css("color","red");
                            changeImg();
                        }
                    }
                })
            });
        });

        function changeImg() {
            //追加参数是用来骗过服务器,说明它是一个动态资源
            document.querySelector("#img").src="${pageContext.request.contextPath}/captcha?r="+Math.random();
        }
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <table align="center">
        <tr>
            <td>用户名：</td><td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码：</td><td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="登陆" disabled></td>
        </tr>
        <tr>
            <td colspan="2" id="msg">${msg}</td>
        </tr>
    </table>
</form>
</body>
</html>