<center>html&css</center>

## 今日内容

+ html概述
+ html入门
+ html预定义标签
+ html表单
+ css

## 第一章  HTML概述

![](https://img04.sogoucdn.com/app/a/100520146/1758154C4ACD08CD88D9EBA567DAD3CD)

超文本标记语言,作用:搭建网页整体结构,展示数据.

特点:标签预定义,展示数据.

现在学习的html5这个版本,它是w3c

## 第二章 html基础

### 1.初识

```html
<!DOCTYPE html><!-- 文档声明 -->
<html lang="zh-CN">
<head>
    <meta charset="UTF-8"><!-- 声明字符集 -->
    <title>html页面</title>
</head>
<body>
    html,hello!
</body>
</html>
```

### 2.HTML组成规范

```html
1. html文档声明：<!DOCTYPE html>
	要求：必须在页面的第一行，固定值
2. html预定义标签
    双标签：<html></HTML>
    单标签：<img>
	特点：html语法比较松散
3. html属性
    语法：<html 属性名="属性值" 属性名='属性值'></HTML>
    特点：属性必须在开始标签
4. html文本
	特点：写在指定的标签体中
5. html注释
	<!-- 注释内容 -->
```

### 第三章 HTML常用标签

### 1.标签语法

学习标签是有技巧的，重点记住每个标签的语义，简单的理解：就是这个标签是用来干嘛的。
根据标签的语义，在合适的地方给一个最为合理的标签，可以让页面结构更加清晰。

### 2.标题标签

```html
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="UTF-8">
        <title>01-标题</title>
    </head>
    <body>
        <!--
            语法：<hn></hn>
            n表示范围：1~6
            特点：数值越大，字体越小
            常用属性：
            align：对齐方式
            取值：left（默认值） 、 center 、 right
        -->

        <h1 align="center">标题一</h1>
        <h2 align="right">标题二</h2>
        <h3>标题三</h3>
        <h4>标题四</h4>
        <h5>标题五</h5>
        <h6>标题六</h6>

    </body>
</html>

```

### 3.水平线标签

```html
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="UTF-8">
        <title>02-水平线</title>
    </head>
    <body>
        <!--
        语法：<hr/>
        常用属性：
        color：颜色
        英语单词
        光的三原色：R（0~255）G（0~255）B（0~255） 通过6位十六进制数表示 #FFFFFF
        width：宽度
        像素：px
        百分比：%
        -->
        <hr color="red">
        <hr color="#dbecb4">
        <hr width="500">
        <hr width="10%">
        <hr>
    </body>
</html>
```

### 4.段落和换行

```html
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="UTF-8">
        <title>03-段落和换行</title>
    </head>
    <body>
        <!--
        换行标签：<br/>
        段落标签：<p></p>
        特点：上下留白
        -->

        <p>&emsp;传智专修学院是一所为互联网、人工智能、工业4.0培养高精尖科技人才的应用型大学。学校经江苏省宿迁市教育局批准，由江苏传智播客教育科技股份有限公司投资创办。<br></p>

        <p>
            当今世界已进入人工智能、机器人时代，人工智能、机器人已成为未来30年、甚至50年科技革命最重要的发展方向。世界各国越来越重视，我国政府也高度关注人工智能的发展，无论是党的十八大、十九大，还是2017、2018、2019的政府工作报告，均提出要重点发展人工智能、机器人，并提升到国家战略层面。</p>

        <p>
            技术发展首要是人才，而我国这方面人才非常紧缺。根据国家相关部门测算，目前我国人工智能人才的缺口就已经超过了500万，国内的供求比例仅为1:10，供需比例严重失衡。并且随着社会信息革命的进一步深入，相应人才的缺口会越来越大。但在这些新技术面前，目前我国关于这方面人才的规模化培养，无论是高校还是科研机构，均没有成熟的培养体系和方案，都还处于摸索阶段。</p>
    </body>
</html>
```

### 5.超链接

```html
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="UTF-8">
        <title>04-超链接</title>
    </head>
    <body>
        <!--
        常用属性：
        href：页面跳转地址
        绝对路径（URL）：https://www.baidu.com/
        相对路径（当前页面和目标页面的相对位置关系）
        ./（当前目录）
        ../（上级目录）
        ../../（上上级目录）
        target：页面打开方式
        取值：_self 默认值（自身）
        _blank（新窗口）
        -->
        <a href="https://www.mryhl.com.cn" target="_blank">mryhl博客</a>
        <a href="01-标题.html" target="_self">标题</a>
        <a href="./02-水平线.html" target="_blank">水平线</a>
        <a href="../index.html" target="_blank">作业</a>


    </body>
</html>
```

### 6.图像

```html
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="UTF-8">
        <title>05-图像</title>
    </head>
    <body>
        <!--
        语法：<img/>
        常用属性
        src：图片资源的路径（地址）
        绝对路径
        相对路径
        alt:图片丢失时，文字提示
        width：图片宽度（自动调整高度）
        像素
        百分比
        height：图片高度（了解）
        
        -->
        <img src="../img/cj.jpg" alt="写不完" width="300">


    </body>
</html>
```

### 7.列表

```html
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="UTF-8">
        <title>06-列表</title>
    </head>
    <body>
        <!--
        有序列表：<ol></ol>
        无序列表：<ul></ul>
        共同子标签：<li></li>
        -->

        <ol>
            <li>吱吱吱吱</li>
            <li>吱吱</li>
            <li>踩踩踩</li>
        </ol>
        <ul>
            <li>方便</li>
            <li>存储</li>
            <li>存储</li>
        </ul>

    </body>
</html>
```

### 8.容器标签

```html
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>07-容器标签</title>
</head>
<body>
<!--
    <div> 和 <span> 是没有语义的，它们就是一个盒子，用来装内容的

    div（块级元素）：独自占用一行
    span（行内元素）：内容有多大，就占用多少
-->
<div>嘿嘿</div>
<div>哈哈</div>
<span>NIH</span><spa>html</spa>
</body>
</html>
```

### 9.实体字符

```html
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="UTF-8">
        <title>08-实体字符</title>
    </head>
    <body>
        <!--
        在html中有两种特殊的转义符号
            1.与html语法有冲突 例如  a<b>c  小于号 &lt;
            2.输入法不方便输入 例如 ♥ &hearts;

        常用的实体字符(了解)


        -->

        半个英语字母英文空格 &nbsp;&nbsp;
        一个汉字中文空格 &emsp;&emsp;
        小于号 &lt;&lt;
        大于号 &gt;&gt;
        &符号 &amp;&amp;

    </body>
</html>
```

### 10.基本表格

```html
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="UTF-8">
        <title>09-基本表格</title>
    </head>
    <body>
        <!--
        html表格
            table：表格
                tr：行
                    td：单元格
                        th：标题单元格 特点：加粗且居中
            table常用属性
                border：边框
                width：宽度
                height：高度
                align：表格对齐方式
                cellspacing：单元格外边距，通常设置为0
                cellpadding：单元格内边距，通常设置为0
                bgcolor:背景色
            tr常用属性
                align:内容对齐方式
                height：行高
                bgcolor:背景色
            td常用属性
                bgcolor:背景色
                align:内容对齐方式
        -->
        <table border="1" width="400" height="200" align="center" cellspacing="0"
               cellpadding="0" bgcolor="#87ceeb">
            <tr height="30" bgcolor="#ffd700">/
                <th>1</th>
                <th>2</th>
                <th>3</th>
            </tr>
            <tr align="center">
                <td>4</td>
                <td>5</td>
                <td>6</td>
            </tr>
            <tr>
                <td>7</td>
                <td align="right" bgcolor="#ffc0cb">8</td>
                <td>9</td>
            </tr>
        </table>


    </body>
</html>
```

### 11.表单合并

```html
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="UTF-8">
        <title>10-表格合并</title>
    </head>
    <body>
        <!--
        td：单元格的属性
        colspan：列合并
        取值：colspan="2"
        rowspan：行合并
        取值：rowspan="2"
        -->

        <table width="300px" height="200px" align="center" border="1" cellpadding="0" cellspacing="0">
            <tr>
                <td colspan="2">1-1</td>
                <!--        <td>1-2</td>-->
                <td>1-3</td>
            </tr>
            <tr>
                <td rowspan="2">2-1</td>
                <td>2-2</td>
                <td>2-3</td>
            </tr>
            <tr>
                <!--        <td>3-1</td>-->
                <td>3-2</td>
                <td>3-3</td>
            </tr>
        </table>


        </table>
    </body>
</html>
```

## 第三章 form表单

### 1.概念

HTML表单：用于采集用户数据，并发送给后端服务器

应用场景：用户注册、用户登录、个人信息修改...

### 2.展示

```html
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="UTF-8">
        <title>11-表单控件</title>
    </head>
    <body>
        <!--
             表单：<form></form> ：它只是一个容器（我们看不到），所有的表单项必须被它包裹，才能采集和发送...
        常用属性：
            action：表单的提交地址，暂时使用#代替（#：表示当前页面）
            method：表单的提交方式，有二种：get（默认值）  | post
                get
                    格式：页面地址?参数名=参数值 & 参数名=参数值
                        请求参数在地址栏拼接
                        请求方式不太安全
                        请求大小是有限制
                post
                    格式：在http协议请求体；参数名=参数值 & 参数名=参数值
                        参数不在地址拼接
                        请求方式相对安全
                        请求大小是没有限制
    表单项
        i）文本框
            <input type="" name="" value=""/>
                type 类型，有很多种，不同类型有不同的功能和展示效果
                    1）text：普通文本框
                    2）password：密码框 特点：掩码
                    3）date：日期选择框
                    4）radio：单选框
                        特点：name属性相同分为一组，完成互斥效果
                        默认选中：checked
                    5）checkbox：复选框
                        特点：name属性相同分为一组，不会出现互斥的效果
                        默认选中：checked
                    6）file：文件上传
                        要求：表单的提交方式必须为post
                    ----------------------------------------------------
                    7）submit：提交按钮
                        功能：将表单提交到服务器
                        注意：此按钮不需要name属性，value是按钮显示名称
                    8）reset：重置按钮
                        功能：将表单元素重置
                    9）button：普通按钮，通常与js结合使用

                name 文本框名称，必须存在，不然此表单的不会被提交
                value 文本框的值 （1.用户输入  2.用户选择）
        ii）下拉框
            语法：<select name="" multiple><select>
                multiple ：多选
                下拉选项：<option value=""></option>
                    默认选中：selected
        iii）文本域
            语法：<textarea name=""> </textarea>
            常用属性：
                rows：行高
                cols：列宽
        -->
        <form action="#" method="get">
            <!--        <form action="#" method="get">-->
            用户名称: <input type="text" name="username"><br>
            用户密码: <input type="password" name="password"><br>
            出生日期: <input type="date" name="birthday"><br>
            用户性别: <input type="radio" name="sex" id="man" value="men" checked><label for="man">男</label>
            <input type="radio" name="sex" id="woman" value="women"><label for="woman">女</label><br>

            兴趣爱好: <input type="checkbox" name="hobby" value="chouyan">抽烟
            <input type="checkbox" name="hobby" value="hejiu" checked>喝酒
            <input type="checkbox" name="hobby" value="tangtou">烫头 <br>
            上传头像: <input type="file"><br>
            学历信息: <select name="xueli" id="" multiple>
            <option value="1">小学</option>
            <option value="2">初中</option>
            <option value="3">高中</option>
            <option value="4" selected>大学</option>
        </select><br>
            个人介绍:
            <textarea name="gerenjieshao" id="1111" cols="30" rows="10"></textarea> <br>
            <input type="submit" value="注册"> <input type="reset" value="重置"> <input type="button" value="没有的按钮">
        </form>

    </body>
</html>
```

## 第四章 css入门基础

### 1.css引入的三种方法

```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>01-HTML引入CSS</title>
        <style>
            h1{
                color: darkmagenta;
            }
        </style>
        <link rel="stylesheet" href="../css/mycss.css">

    </head>
    <body>
        <!--
        HTML引入CSS的三种方式
                1）行内样式：所有的标签都有一个共同的属性 style
                    语法：<h1 style="css样式"></h1>
                    作用范围：当前标签

                2）内部样式：通过style标签编写css样式
                    语法：<style type="text/css"> css样式 </style>
                            type="text/css" （默认值） 告知浏览器解析方式切换为css的方式
                    作用范围：当前页面

                3）外部样式：通过link标签引入外部css文件
                    语法：<link rel="stylesheet"  href="外部css文件"/>
                            rel="stylesheet"  告知浏览器引入的外部文件为css样式表


                css样式的优先级：就近原则
        -->
        <h1 style="color: burlywood">行内样式</h1>
        <h1>内部样式</h1>
        <h1>外部样式</h1>
    </body>
</html>
```

```css
h1{
    color: deeppink;
}
```



### 2.css的基本语法

```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>02-css语法</title>
        <style>
            span {
                color: darksalmon;
                font-style: italic;
                font-size: 40px;
                font-family: 华文行楷;
            }
        </style>
    </head>
    <body>
        <!--
        css注释

        css语法：
             选择器{
                样式名:样式值;
                样式名:样式值;
             }

       样式名不区分大小写，通常使用小写，
       样式名多个单词组成，中间以横杠分割
        -->
        <span>我用双手成就你的梦想</span>
    </body>
</html>
```

### 3.基本选择器

```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>03-基本选择器</title>
        <STYLE>
            SPAN{
                color: darkmagenta;
            }
            .male{
                color: aqua;
            }
            .female{
                color: hotpink;
            }
            .hero{
                font-family: 华文行楷;
            }
            #boss{
                color: blueviolet;
                font-size: 60px;

            }
        </STYLE>
    </head>
    <body>

        <!--
             1）标签选择器
        语法：标签名{css样式}
        特点：指定标签

        2）类选择器
            语法：.class{css样式}
            特点：分组

        3）id选择器
            语法：#id{css样式}
            特点：唯一


        选择器优先级：id > class > 标签
        -->
        <span class="female">古力娜扎</span>
        <span class="female">迪丽热巴</span>
        <span class="female hero">黑寡妇</span>

        <span class="male hero">钢铁侠</span>
        <span class="male hero">超人</span>


        <span id="boss">灭霸</span>


    </body>
</html>
```

### 4.其他选择器

```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>04-扩展选择器</title>
        <style>
            span,label{
                font-size: 20px;
            }

            div>span{
                color: red;
            }

            div span{
                border: 1px solid black;
            }

            input[name="username"]{
                background-color: gold;
            }
        </style>
    </head>
    <body>
        <!--
        1）并集选择器
            语法：选择器1,选择器2,选择器3....{css样式}

        2）父子选择器
            语法：父选择器 > 子选择器{css样式}

        3）后代选择器
            语法：父选择器 后代选择器{css样式}

        4)属性选择器
            语法：标签[属性名="属性值"]{css样式}
    -->

        <div>
            <span>烟幕弹</span>
            <p>
                <span id="gbl">高爆雷</span>
            </p>
        </div>
        <span id="jjx">急救箱</span>
        <br/>
        <label>姓名</label>
        <input type="text" name="username" value="Jack"/><br/>
        <label>密码</label>
        <input type="password" name="password" value="123456"/><br/>

    </body>
</html>
```

## 总结

```markdown
HTML
	第1章 概述
		HTML
			超文本标记语言
				用于搭建网页结构，展示数据
	第2章 基础
		开发工具
			idea
				要求统一环境
					jkd1.8
					字符集UTF-8
		书写规范
			文档声明
			标签
				语法松散
			属性
			文本
			注释
	第3章 常用标签
		标题
			hn
				h1~h6
		水平线
			hr
				color
		段落换行
			br
			p
		a
			超链接
				href
					绝对地址
					相对地址
						./当前目录
						../上一级目录
				target
					_self
					_blank
		img
			src
				相对地址
					./当前目录
					../上一级目录
				绝对地址
			width
			alt
		列表
			ul
			ol
			li
		容器
			div
				块级元素
			span
				行内元素
		转义字符
			&XXX;
				&nbsp;
				&emsp;
		表格
			table
				border
				bgcolor
				cellspacing
				cellpadding
				width
				height
				align
			tr
				height
				align
			td
				width
				align
				表格合并
					colspan
					rowspan
			th
				特点：加粗且居中
	第4章 表单
		表单容器
			form
				action
				method
					get
					post
		表单标签
			input
				name
				value
				type
					text
					password
					date
					radio
					checkbox
					file
					reset
					submit
					button
				checked
					用于设置单选和复选默认选中
			select
				option
					selected
						默认选中
			textarea
	第5章 案例：黑马旅游网注册页
css入门
	作用：美化网页
	CSS基础语法
		HTML引入CSS
			行内样式：所有标签都有一个style属性
			内部样式：使用style标签
			外部样式：通过link标签引入外部样式
		书写和规范
			选择器{样式名：样式值；}
		基本选择器
			标签
			id
			class
		扩展选择器
			并集
			后代
			属性
```

