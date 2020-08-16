<center>CSS与js基础</center>

## 今日内容

+ css 
  + 段落格式
  + 文本背景
  + 显示属性
  + 浮动属性
  + 盒子模型
+ js基础
  + 引入方式
  + 变量
  + 输出
  + 函数
  + 事件

## 第一章 css常用样式

### 1.字体和文本属性

> 文本属性:设置文本的颜色,行高,缩进,文本的修饰(有无下划线)等
> 字体属性:设置文字的大小,字体,风格(倾斜),是否加粗

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>05-字体和文本属性</title>
    <style>
        /*
        需求：
            1.p段落文字绿色
            2.p段落文字大小20px
            3.p段落行高40px
            4.p段落字体加粗
            5.p段落字体楷体
            6.p段落文字倾斜
            7.超链接隐藏下划线
        */
        p{
            color: green;
            font-size: 20px;
            line-height: 40px;
            font-weight: bold;
            font-family: 楷体;
            font-style: italic;
        }
        a{
            text-decoration: none;
        }
    </style>

</head>
<body>
<div>

    <a href="#"> 学习的误区：</a><br/>
    <p>
        眼睛：看了一遍记住了<br/>
        耳朵：听了一遍明白了<br/>
        脑子：想了一遍搞懂了<br/>
        手：你们会个屁！^_^ <br>
    </p>
</div>
</body>
</html>
```

### 2.背景属性

> CSS3 包含多个新的背景属性，它们提供了对背景更强大的控制。
>
> background-color:设置颜色
>
> background-image:设置背景图片
>
> background-repeat:图片重复
>
> background-position:对齐方式

```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>07-背景属性</title>
        <style>
            div{
                width: 100%;
                height: 200px;
                border: 1px solid red;
            }
            #div1{
                background-color: lightpink;
            }
            #div2{
                background-image: url("../img/girl.jpg");
                background-repeat: no-repeat;
                background-position: center; /* center center */
                background-color: antiquewhite;
            }
        </style>
    </head>
    <body>
        <div id="div1">
            我可以输入一切内容。。。。
        </div>
        <div id="div2"></div>
    </body>
</html>
```

### 3.显示属性

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>08-显示属性</title>
    <style>
        div,span{
    background-color: red;
    }
    span{
    display: block; /*指定标签为块级元素*/
    text-align: center;
    }
    div{
    display: inline; /*指定标签为行内元素*/
    text-align: center;
    }
    ul li{
    display: inline;
    }
    </style>
</head>
<body>

<span>内联标签span1</span>
<span>内联标签span2</span>
<span>内联标签span3</span>
<div>块级标签div1</div>
<div>块级标签div2</div>
<div>块级标签div3</div>
    <ul>
        <li>萨瓦迪卡</li>
        <li>鼓励娜扎</li>
        <li>马尔扎哈</li>
    </ul>
</body>
</html>
```

### 4.浮动属性

> 打破常规，让一行内显示多个（块级元素）盒子

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>09-浮动属性</title>
    <style>
        .box{
            width: 150px;
            height: 150px;
            border: 1px solid red;
            text-align: center;
        }
        #long{
            float: left; /*左浮动*/
        }
        #hu{
            float: right;  /*右浮动*/
        }
    </style>
</head>
<body>
<div class="box" id="long">左青龙</div>
<div class="box" id="hu">右白虎</div>
<div style="clear: both"></div> <!--清除浮动效果（墙）-->
<div class="box">最后砍成米老鼠</div>
</body>
</html>
```

### 5.盒子模型

> 盒子模型：
>         边框：border
>             top、right、bottom、left
>         内边距：padding
>             top、right、bottom、left
>         外边距：margin
>              top、right、bottom、left
>         盒子水平居中：margin:auto;

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>10-盒子模型</title>
    <style>
        .box{
            width: 300px;
            height: 300px;
            border: 10px solid red; /*同时指定四个边框*/
            padding: 30px;
            margin: auto;
            margin-top: 50px;
        }
    </style>

</head>
<body>
<div class="box">
    <img src="../img/girl.jpg" width="300px" height="300px" alt="">
</div>
</body>
</html>
```

## 第二章 JavaScript概述

> 作用：页面交互



**JavaScript历史**

- **起源：**上世纪末1995年时，Netscape（网景）公司推出Navigator浏览器。发布后用的人不多，这咋整啊？这家公司就想了一个好方案，不仅在浏览器实现静态HTML，还想要有动态效果，比如：在前端处理表单验证。
- **动手：**有目标就去做，网景公司大牛多，Brendan Eich（布兰登·艾奇）据说就用了10天就把JavaScript搞了出来，刚出来时叫LiveScript，为了蹭蹭当红明星Java热度，就改成JavaScript了（瞬间就火了），事实上他们两没啥关系。（北大和北大青鸟）
- **竞争：**看到网景公司有了js，微软感觉不行啊，我的IE要被干掉啊，同时也感到js的前途无量，于是参考JavaScript弄了个名为JScript浏览器脚本语言。
- **标准：** Netscape和微软竞争出现的js导致版本的不一致，随着业界的担心，JavaScript的标准化被提上议事日程。ECMA（欧洲计算机制造商协会）组织就去干这事，最后在1997年弄出了ECMAScript作为标准。这里ECMAscript和JavaScript可以看做表达的同一个东西。



**JavaScript特点**

1. js源码不需要编译，浏览器可以直接解释运行
2. js是弱类型语言，js变量声明不需要指明类型

> js是弱类型的脚本语言，可以直接在浏览器解释运行

 **JavaScript组成**

| **组成部分**    | **作用**                                                     |
| --------------- | ------------------------------------------------------------ |
| **ECMA Script** | 构成了JS核心的语法基础                                       |
| **BOM**         | Browser Object Model 浏览器对象模型，用来操作浏览器上的对象  |
| **DOM**         | Document Object Model 文档对象模型，用来操作网页中的元素（标签） |

### 1.HTML引入JS有二种方式

>     HTML引入JS有二种方式
>         内部脚本
>             语法：
>     ```html
>             <script type="text/javascript"> js代码 </script>
>     ```
>     作用范围：当前页面
>     外部脚本
>     语法：
>
>     ```html
>             <script type="text/javascript" src="外部js文件"></script>
>     ```
>     作用范围：引入的所有html页面
>
>     注意事项：如果script标签引入了外部脚本，那么标签体不能自闭合，而且不允许有js代码
>
>
>     扩展:在开发时 style和link标签通常在head头部，script标签在body尾部...

```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>01-HTML引入JS</title>

    </head>
    <body>

        <script>
            document.write('<h1>你好,这是内部脚本</h1>');
        </script>
        <script src="../js/myjs.js"></script>
    </body>
</html>
```

```js
document.write('<h1>你好,这是外部脚本</h1>');
```

### 2.JS三种输出方式

1. 浏览器弹框输出字符
2. 输出html内容到页面
3. 输出到浏览器控制台

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>02-JS三种输出方式</title>

</head>
<body>

    <script>
        // 1. 浏览器弹框输出字符
        alert("浏览器弹框输出字符");
        // 2. 输出html内容到页面
        document.write('<h1> 输出html内容到页面</h1>');
        // 3. 输出到浏览器控制台
        console.log("输出到浏览器控制台");

    </script>
</body>
</html>
```

### 3.JS变量声明

> js是弱类型语言，不注重变量的定义，所以在js中定义变量的类型方式如下
>
> javaScript：目前使用的ECMAScript 6的版本（优化了很多问题）
>         之前js定义变量的声明符 var，这哥们天生残疾，有很多设计缺陷，坑了不少前端好汉...
>         es6版本推出了新的变量声明符 let（变量） const（常量）

```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>03-JS变量声明</title>

    </head>
    <body>
        <script>
            // String str = "用心做教育";
            let str = "用心做教育";
            console.log(str);
            // int i = 1314;
            let i = 1314;
            console.log(i);
            // double d = 521.1314;
            let d = 521.1314;
            console.log(d);
            // final Integer PI = 3.14;
            const PI = 3.14;
            console.log(PI);
            // boolean b = true;
            let b = true;
            console.log(b);
            console.log('-------------------------------')
            // 让大家感知弱类型脚本语言
            let a;
            console.log(a);
            a = null;
            console.log(a);
            a = "哈哈";
            console.log(a);
            a = 123;
            console.log(a);
            a = true;
            console.log(a);
            a = new Date();
            console.log(a);
        </script>
    </body>
</html>
```

### 4.js的数据类型

> js与java一样，数据类型也分为基本数据类型（原始数据类型）和引用数据类型
>
> 1.基本（原始）数据类型
>             string：字符串（单引号、双引号）
>             number：数值（整型、浮点）
>             boolean：布尔
>             undefined：未定义
>   2.引用数据类型
>             null：空（bug）
>             new 出来的东东
>             typeof 关键字判断变量的数据类型

```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>04-JS数据类型</title>

    </head>
    <body>
        <script>
            let str = 'c';
            console.log(typeof str); // string
            let i = 1314.1;
            console.log(typeof i); // number
            let b = true;
            console.log(typeof b); // boolean
            let u;
            console.log(typeof u); // undefined
            let n = null;
            console.log(typeof n); // object
            let date = new Date();
            console.log(typeof date); // object


        </script>

    </body>
</html>
```

### 5.JS运算符

> js与java的运算符一样，什么算数运算符、赋值运算符、逻辑运算符等等，不需要死记硬背，写程序会用就行：

```markdown
1. 算数运算符
		+ - * / % ++ --
2. 赋值运算符
		= += -= *= /=
3. 比较运算符
		> < ==(===) !=(!==)
4. 逻辑运算符
		&& ||  !
5. 三元（目）运算符
		条件表达式？为真:为假
```

```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>01-JS运算符</title>

    </head>
    <body>

        <script>
            // 算数运算符
            // js的数值可以与字符串参与数学运算，底层进行了隐式转换
            // NaN not a number
            let a = 10;
            let b = '4';
            console.log(a + b); // 这是字符串拼接
            console.log(a / b);
            // 比较运算符
            let c = '10';
            console.log(a == c); // true （比较内容）
            console.log(a === c); // false （比较的是类型和内容）
        </script>
    </body>
</html>
```

### 6.JS流程控制

> 高级语言中的三种基本结构：顺序、分支、循环 

#### ① 条件判断

```markdown
1. if判断
		if (条件表达式) {
            代码块;
        } else if(条件表达式) {
            代码块;
        } else {
            代码块;
        }
		
2. switch判断	
		switch(条件表达式){
            case 满足条件1 :
            	代码块 break;
            case 满足条件2 :
            	代码块 break;
            default:
            	默认代码块;
		}
```



```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>02-条件判断</title>

</head>
<body>

<script>
    // let flag = false;
    // let flag = ' '; // 非空串为真
    // let flag = 0; // 非零为真
    // let flag = new Date();  // 有对象为真
    // let flag ; // 未定义未假
    let flag = null; // 空值为假
    if (flag) {
        console.log("真的");
    } else {
        console.log("假的");
    }
</script>

</body>
</html>
```

#### ② 循环语句

```markdown
1. 普通for循环
		for(let i= 0; i<10; i++){
    		需要执行的代码;
		}

2. 增强for循环
		for(let obj of array){
            需要执行的代码;
		}
		
3. 索引for循环
		for(let index in array){
            需要执行的代码;
		}

4. while循环
		while (条件表达式) {
			需要执行的代码;
		}
		
5. do..while循环
		do{
            需要执行的代码;
		}while(条件表达式);
		
6. break和continue
		break: 跳出整个循环
		continue：跳出本次循环
```



```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>03-循环语句</title>

</head>
<body>

<script>
    let arr = ['jack','lucy','rose'];

    // 普通for  fori快捷键
    for (let i = 0; i < arr.length; i++) {
        console.log(arr[i]);
    }
    // 增强for  iter快捷键
    for (let ele of arr) {
        console.log(ele);
    }

    // 索引for
    for(let index in arr){
        console.log(index);
        console.log(arr[index]);
    }
</script>

</body>
</html>
```

### 7.经验值

> var是js的设计缺陷...

```html
<script>

    // 使用var声明的变量没有块级作用域
    let a = 10;

    {
        let a = 15;
    }
    console.log(a);

    for (var i = 0 ; i  < 5 ; i++){
        console.log("haha");
    }
    console.log(i);

    for (let j = 0; j < 5; j++) {
        console.log('hehe');
    }
    console.log(j);


</script>
```

## 第三章 函数

> js函数用于执行特定功能的代码块，为了方便理解也可以称为js方法

### 1.普通函数

```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>04-普通函数</title>

    </head>
    <body>
        <script>
            /*
            语法：
                function 函数名(参数1,参数2){
                    return xxx;
                }

            调用：
                函数名(实际参数1,实际参数2);

           特点：
                1.声明的函数不需要定义返回值
                2.声明的函数形参不需要使用变量修饰符声明
                3.如果有返回值就加上return
                4.js没有方法重载，重名的会被覆盖...
                5.js的函数参数个数不太严谨【了解】
                6.所有的函数体内置了arguments数组，用于接收调用时传递的实际参数
                7.js函数支持可变参数
            */
            // 两个数求和
            function sum(a, b) {
                return a + b;
            }
            // 三个数求和
            function sum(a, b, c) {
                console.log(arguments);
                return a + b + c;
            }

            // 求N个数的和
            function sum(...args){
                console.log(args);

                let result = 0;
                for (let arg of args) {
                    result+= arg;
                }
                return result;
            }


            // NaN not a number 这不是一个数
            let result = sum(1, 2, 3, 4);
            console.log(result);
        </script>
    </body>
</html>
```

### 2.匿名函数

> 先记住语法格式，一会讲到js的事件，它就有用了！！！！

```js
function (a,b){
    return a+b;
}
```

##  第四章 JS事件【重点】

> JS可以监听用户的行为,得到一个事件，并调用函数来完成用户交互功能.

### 1.常用事件

> 这些足够我们后面的学习使用了，大家慢慢消化....

```markdown
1. 点击事件：
        1. onclick：单击事件
        2. ondblclick：双击事件
        
2. 焦点事件
        1. onblur：失去焦点
        2. onfocus:元素获得焦点。

3. 加载事件：
        1. onload：页面加载完成后立即发生。

4. 鼠标事件：
        1. onmousedown	鼠标按钮被按下。
        2. onmouseup	鼠标按键被松开。
        3. onmousemove	鼠标被移动。
        4. onmouseover	鼠标移到某元素之上。
        5. onmouseout	鼠标从某元素移开。
        
5. 键盘事件：
		1. onkeydown	某个键盘按键被按下。	
		2. onkeyup		某个键盘按键被松开。
		3. onkeypress	某个键盘按键被按下并松开。

6. 改变事件
        1. onchange	域的内容被改变。

7. 表单事件：
        1. onsubmit	提交按钮被点击。
```



### 2.事件绑定

>  将事件与html标签进行绑定，实现交互功能

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>08-事件绑定</title>

</head>
<body>
<input type="button" value="普通函数" onclick="fun1()"> <br>
<input type="button" value="匿名函数" id="btn"> <br>


<script>
    // 普通函数
    function fun1() {
        alert('我是普通函数...')
    }


    // 匿名函数
   /* // 1.获取button按钮的js对象
    let btn = document.getElementById('btn');
    // 2.给onclick属性绑定匿名函数
    btn.onclick=function () {
        alert('我是匿名函数')
    }
*/
    document.getElementById('btn').onclick=function () {
        alert('我是匿名函数')
    }
</script>
</body>
</html>
```





### 3.案例：页面交互

**需求**

>  给页面表单控件绑定对应事件，实现交互功能



```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>09-案例：页面交互</title>

</head>
<body>
<!--
常用事件
    1. onload 页面加载完成：此事件是页面加载成功后执行的...
    2. onfocus 获取焦点
    3. onblur 失去焦点
    4. onchange 表单控件的值改变时
    5. onclick 鼠标单击

-->

姓名 <input type="text" id="username"><br/>
学历
<select name="edu" id="edu">
    <option value="0">请选择</option>
    <option value="1">本科</option>
    <option value="2">大专</option>
</select>
<br/>
<button id="btn">按钮</button>
<hr>
<form action="#" method="get" id="myForm">
    文本框：<input type="text" name="text"> <br>
    <input type="submit" value="注册">
</form>

<script>
    //1. onload 页面加载完成
    window.onload = function () {  // 当前窗口加载完毕后，触发的事件，执行函数完成某些功能
        //  alert('页面加载完毕。。。')
    }

    // alert('haha')

    //2. onfocus 获取焦点
    document.getElementById('username').onfocus = function () {
        //  document.getElementById('username').value='哈哈';
        // js对象绑定事件时，也有this的概念，this表示当前的js对象
        this.value = '呵呵';
    }

    //3. onblur 失去焦点
    document.getElementById('username').onblur = function () {
        this.value = ''
    }

    //4. onchange 表单控件的值改变时
    document.getElementById('edu').onchange = function () {
        alert(this.value)
    }


    //5. onclick 鼠标单击
    document.getElementById('btn').ondblclick = function () {
        alert('哎呀妈呀，我被点了》。。')
    }


    // 6. onsubmit 表单事件
    document.getElementById('myForm').onsubmit=function () {


       // return true; // 表单正常提交
        return false;// 表单拦截
    }
</script>
</body>
</html>
```

## 总结

```markdown
css&js基础
	css
		CSS常用样式
			字体
				font-size
				font-weight
				font-style
				font-family
			文本
				color
				line-height
				text-decoration
			背景
				background
					color
					img
					repeat
			显示
				display
					block
						块元素
					inline
						内联元素
					none
						隐藏
			浮动
				float
					left
					right
			盒子模型
				组成
					边框
						border
					内边距
						padding
					外边距
						margin
		作业：
			公司简介
			黑马旅游网注册页面
	js基础
		概述
			作用：网页交互
			js组成
				ECMAScript
					基础语法
				BOM
					浏览器对象模型
				DOM
					文档对象模型
		基础语法
			HTML引入JS
				内部脚本
				外部脚本
			JS三种输出方式
				alert(）
				 document.write()
				console.log()
			JS变量声明
				let
				const
			JS数据类型
				基本数据类型
					number
					string
					boolean
					undefined
				 引用数据类型
					object
			运算符
				算数运算符
					数值可以与字符串进行数学运算，底层进行了转换
				比较运算符
					=== 恒等 先比较类型后比较内容
					!== 恒不等 先比较类型后比较内容
			流程控制语句
				顺序
					代码自上而下，逐行执行
				分支
					条件语句
						和java一样
							if...else if...else
							switch...case..default
				循环
					和java一样的
						while
						do...while
						for...i
					和java不一样的
						for...in
							数组的索引index
						for...of
							数组的元素
		js函数
			普通函数
				在js中，没有方法重载，参数个数可以与调用时个数不同...
			匿名函数
			案例:轮播图
				setInterval(函数,间隔时间)
				document.getElementById(id属性值)
		js事件
			作用
				JS可以监听用户的行为,并调用函数来完成用户交互功能
			常用事件
				页面加载完毕事件
					window.onload
				获取焦点
					onfocus
				失去焦点
					onblur
				值改变时
					onchange
				单击时
					onclick
			事件绑定
				普通函数
				匿名函数
```

