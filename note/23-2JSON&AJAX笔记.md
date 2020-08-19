

<center>JSON&AJAX</center>

## 今日内容

+ json基础
+ 格式转换
+ ajax入门

## 第一章 JSON

### 1.JSON概述

JavaScript对象表示形式（JavaScript Object Notation）

```markdown
* java对象表示形式
		User user = new User();
			user.setUsername("后羿");
			user.setAge(23);
			user.setSex("男");
			
* javaScript对象表示形式
		let user = {"username":"后羿","age":18,"sex":"男"};
			取值：user.username
			赋值：user.username="安其拉";
		let product = {};
		let car = {};
```



这哥们可以取代XML笨重的数据结构，和xml相比：更小、更快，更易解析

### 2.JSON基础语法

> 前端语法比较松散，name 可以不使用双引号
>
> 但是在java中，name必须使用双引号

```js
let user = {username:"后羿",age:18,sex:"男"};

String userJson = "{\"username\":\"后羿\",\"age\":18,\"sex\":\"男\"}";
```



```markdown
* 对象类型
		{name:value,name:value}
		
* 数组类型
		[
            {name:value,name:value},
            {name:value,name:value},
            {name:value,name:value}
		]
		
* 复杂对象
		{
            name:value,
            array:[{name:value},{},{}]
            user:{name:value}
		}
```



```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>01-JSON基础语法</title>

</head>
<body>

</body>
<script>
   // 1.描述用户对象（张三丰、男、32岁）
    let user = {"username":"张三丰","age":32,"sex":"男"};
    console.log(user.username);
    user.age=100;
    console.log(user.age);
    // 2.描述用户数组（张三丰、张翠山、张无忌）
    let array = [
        {"username": "张三丰", "sex": "男", "age": 32},
        {"username": "张翠山", "sex": "男", "age": 22},
        {"username": "张无忌", "sex": "男", "age": 12}
    ];
    for (let user of array) {
        console.log(user.username);
    }
    // 3.描述韦小宝（27岁，老婆、师傅）
    let xiaobao = {

        "username":"韦小宝",
        "age":27,
        "lapao":[
            {"username":"双儿","address":"扬州"},
            {"username":"建宁","address":"北京人"},
            {"username":"木婉清","address":"云南"},
        ],
        "shifu":{"username":"陈近南","address":"台湾"}

    };
    console.log(xiaobao.username);
    for (let laopom of xiaobao.lapao) {
        console.log(laopom.username + "-" + laopom.address);
    }
    console.log(xiaobao.shifu.username);
</script>
</html>
```





### 3.JSON格式转换

```markdown
* JSON对象与字符串转换的相关函数
	语法：
    	1. JSON.stringify(object) 把json对象转为字符串
    
    	2. JSON.parse(string) 把字符串转为json对象
```



```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>02-JSON格式转换</title>

</head>
<body>
<script>
    let userStr = "{\"username\":\"后羿\",\"age\":18,\"sex\":\"男\"}";
    console.log(typeof userStr);

    //2. JSON.parse(string) 把字符串转为json对象
    let user = JSON.parse(userStr);
    console.log(typeof user);
    console.log(user.username);


    //1. JSON.stringify(object) 把json对象转为字符串
    console.log(typeof JSON.stringify(user));

</script>
</body>
</html>
```







## 第二章 AJAX入门【感知..】

### 1.AJAX概述

**传统网站中存在的问题**

- 网速慢的情况下，页面加载时间长，用户只能等待

- 表单提交后，如果一项内容不合格，需要重新填写所有表单内容

- 页面跳转，重新加载页面，造成资源浪费，增加用户等待时间



**AJAX可以解决以上问题**

​	ajax这哥们是浏览器提供的一套方法，在无需重新加载整个网页情况下，能够更新部分网页的技术，从而提高用户浏览网站应用的体验。

> 中文音译：阿贾克斯





**应用场景**

- 搜索框提示
- 表单数据验证
- 无刷新分页





> ajax：无需加载整个网页，可以向服务器发送异步请求，实现页面的局部刷新...



### 2.快速入门

> **需求**
> 校验用户名是否存在







#### 1.js代码编写【抄二遍】

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>register</title>
    <script src="../js/jquery-3.2.1.min.js"></script>

</head>
<body>

<form action="#" method="post">
    用户名：<input id="username" type="text" name="username"> <span id="userInfo"></span>
</form>

<script>
    // 1.给用户名文本框绑定失去焦点事件
    $('#username').blur(function () {
       let value=  $(this).val();
       // alert(value)
        // 2.调用ajax发送请求
        $.ajax({
            type:'post',
            url:'http://localhost:8080/check',
            data:'username='+value,
            success:function (resp) {
                $('#userInfo').html(resp)
            }
        })
    });
</script>
</body>
</html>
```

### 3.介绍

> jQuery哥们为了简化ajax的操作，提供了 $.ajax() 函数

```markdown
* 语法
		$.ajax({name:value,name:value})
		
* 常用参数
		type:请求类型（方式）
			记住二种：get | post
		url：请求地址
		data：发送请求时，携带参数
			如果是get方式，在地址中拼接
			如果post方式，在请求体当中拼接
			例如：username=jack&password=123&age=18
		success：请求成功，服务器返回结果，帮你调用此函数
			resp：返回的数据变量
			
* 补充参数
		error:请求失败时，帮你调用次函数
		dataType:预期服务器返回的数据类型，一般不用写(由服务器来指向)
			常用：text、json
```

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>lx.html</title>

    <script src="../js/jquery-3.2.1.js"></script>
</head>
<body>


<select id="cityId"></select>

<script>
    // 页面加载成功后，向服务器发送请求，返回对应省份的城市
    $(function () {
        let province = prompt('请输入城市')
        if(province != null){
            // 发送ajax请求
            $.ajax({
                type:'post',
                url:'http://localhost:8080/cityList',
                data:'province='+province,
                success:function (resp) {
                    for (let city of resp) {
                        $('#cityId').append(`<option>${city}</option>`)
                    }
                },
                error:function () {
                    alert('服务器忙...')
                }
            })
        }
    })
</script>
</body>
</html>
```

## 总结

```markdown
json
	概述
		JavaScript对象表示形式
		这哥们可以取代XML笨重的数据结构，和xml相比：更小、更快，更易解析
	json数据格式
		对象类型
			{xx:xx}
		数组类型
			[{xxx:xx,xxx:xx},{xxx:xx,xxx:xx}]
		复杂对象
			{name:value,
            array:[{name:value},{},{}]
            user:{name:value}}
	JSON格式转换
		JSON.stringify()
			把json对象转为字符串
		JSON.parse()
			把字符串转为json对象
ajax
	概述
		ajax是：异步提交和局部刷新的技术
		应用场景
			搜索框提示
			表单数据验证
			无刷新分页
	JQuery的Ajax插件
		ajax函数
			语法
				$.ajax()
			参数
				url：请求地址
				type：请求方式
				data：请求参数
				success:请求成功后执行的回调函数
```

