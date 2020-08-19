<center>JQuery框架</center>



## 今日内容

+ JQuery介绍
+ JQuery引入
+ JQuery选择器
+ 操作DOM
+ 事件绑定



## 第一章 Jquery概述

### 1.简介

jQuery是一款优秀的javascript的轻量级框架之一，封装了dom操作、事件绑定、Ajax等功能。

特别值得一提的是基于jQuery平台的插件非常丰富，大多数前端业务场景都有其封装好的工具可直接使用。

**jQuery下载和版本介绍**

官网地址：http://jquery.com 

> 到了公司不是最新版本就是最好的，找到适合当前第三方插件的适用版本

```markdown
* jquery-xxx.js
			开发版本：有良好的缩进和注释格式，方便开发者阅读源代码

* jquery-xxx.min.js
			生产版本：对代码压缩、注释删除，易于网络传输
```



> **小结**：是一款javascript的类库，设计思想：写得少、做得多、我们使用时需要下载和引入



### 2.自定义JS框架

框架（Framework）是完成某种功能的半成品，抽取重复繁琐的代码，提供简洁强大的方法实现。

**mryhl.js**

```js
// 对获取js对方的方法进行抽取
function jQuery(id) {
    return document.querySelector(id);
}

function $(id) {
    return document.querySelector(id);
}
```



**html**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>自定义js框架</title>

    <script src="mryhl.js"></script>

</head>
<body>
<div id="myDiv">世界上最遥远的距离不是生与死，而是你亲手制造的BUG就在你眼前，你却怎么都找不到她。</div>

<script>
   /* // js对象
    let myDiv = document.querySelector("#myDiv");
    // 操作属性
    myDiv.innerHTML = 'js修改了内容。。。';*/



    $("#myDiv").innerHTML='js封装后修改了内容';


</script>
</body>
</html>
```







## 第二章 Jquery基础语法

### 1.HTML引入Jquery

> 注意使用第三方技术，一定要导入类库

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>01-HTML引入Jquery</title>
    <script src="../js/jquery-3.2.1.js"></script>
</head>
<body>
<div id="myDiv">世界上最远的距离，是我在if里你在else里，虽然经常一起出现，但却永不结伴执行。</div>
    <script>
        $("#myDiv").text("孙子,变了啊");
    </script>
</body>
</html>
```

### 2.jQuery与JS区别

jQuery虽然本质上也是js，但如果使用jQuery的属性和方法那么必须保证对象是jQuery对象而不是js对象。

通过js方式获取的是js对象，通过jQuery方式获取的是jQuery对象。两者的关系与区别总结如下：

```markdown
jQuery对象与js对象相互转换
            js -> jq
            $(js对象) or JQuery(js对象)
            jq -> js
            jq对象[索引] or jq对象.get(索引)
```

```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>02-jq与js对象相互转换</title>
        <script src="../js/jquery-3.2.1.min.js"></script>
    </head>
    <body>
        <div id="myDiv">通过不同方式获得文本内容</div>
        <script>
            // 通过js方式修改文本内容
            // document.getElementById("myDiv").innerText="js方式改变了";
            // 通过jq方式修改文本内容
            // $("#myDiv").text("jq方式改变内容.......")
            // js对象和jq对象的：属性和方法不同通用
            /*let elementById = document.getElementById("myDiv");
            $(elementById).text("js用jq的方法");*/
            $("#myDiv")[0].innerText = "jq转js...";

        </script>
    </body>
</html>
```

### 3.页面加载事件

```markdown
 	语法
		js
			window.onload=function(){}
		
		jq
			$(function(){})
			
	特点
		js：只能定义一次，后面的会覆盖前面的
		jq：可以定义多次...	
	
```

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>03-页面加载事件</title>
    <script src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>
    <script>
        $(function(){
           alert(typeof this);
        });
    </script>
</body>
</html>
```

## 第三章 Jquery选择器

> css、js、jquery选择器语法完全一样...

### 1.基本选择器

```markdown
1. 标签（元素）选择器
		* 语法：$("html标签名") 获得所有匹配标签名称的元素
		
2. id选择器
		* 语法：$("#id的属性值") 获得与指定id属性值匹配的元素
		
3. 类选择器
		* 语法：$(".class的属性值") 获得与指定的class属性值匹配的元素	
		
4. 并集选择器
		* 语法：$("选择器1,选择器2....") 获取多个选择器选中的所有元素
```



```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>04-基本选择器</title>
    <script src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>

<span class="female">古力娜扎</span>
<span class="female">迪丽热巴</span>
<span class="female hero">黑寡妇</span>

<span class="male hero">钢铁侠</span>
<span class="male hero">超人</span>


<div id="boss">灭霸</div>

<script>
    // 1.获取span标签的jquery对象
    console.log($('span').length);

    // 2.获取class有hero的jquery对象
    console.log($('.hero').length);

    // 3.获得id="boss"的jquery对象
    console.log($('#boss').length);

    // 4.并集选择器
    console.log($('span,div').length);
</script>
</body>
</html>
```

### 2.层级关系选择器

```markdown
1. 后代选择器
		* 语法：$("A B") 选择A元素内部的所有B元素
		
2. 父子选择器
		* 语法：$("A > B") 选择A元素内部的所有B子元素
```

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>05-层级关系选择器</title>
    <script src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>
<div id="kangxi">
    <span>儿子：雍正</span>
    <b>
        <span>孙子：乾隆</span>
    </b>
</div>
<p>牛顿</p>

<script>
// 1. 获取所有的p,span的文本
            console.log("1" + $("p,span").text());
            // 2. 获取div的后代span的文本
            console.log("2" + $('#kangxi span').text());
            // 子
            console.log("3" + $('#kangxi>span').text());
</script>
</body>
</html>
```



### 3.属性选择器

```markdown
1. 属性选择器
		* 语法：$("A[属性名='值']") 包含指定属性等于指定值的选择器
		
2. 复合属性选择器
		* 语法：$("A[属性名='值'][]...") 包含多个属性条件的选择器
```

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>06-属性选择器</title>
    <script src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>

<input type="text" name="username" value="用户名"/><br/>

<input type="text" name="nickname" value="昵称"/><br/>

<script>
    // 1.获取type='text'的input标签
    console.log($('input[type="text"]').length);

    // 2.获取type='text' 且 name="nickname" 的input标签
    console.log($('input[type="text"][name="nickname"]').length);
</script>
</body>
</html>
```

### 4.过滤选择器

> <li></li>
>
> $('li')

```markdown
1. 首元素选择器
		* 语法： :first 获得选择的元素中的第一个元素
		
2. 尾元素选择器
		* 语法： :last 获得选择的元素中的最后一个元素

3. 偶数选择器（奇数行）
		* 语法： :even 偶数，从 0 开始计数
		
4. 奇数选择器（偶数行）
		* 语法： :odd 奇数，从 0 开始计数

5. 指定索引选择器
		* 语法： :eq(index) 指定索引元素
```

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>07-过滤选择器</title>
    <script src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>
<ul>
    <li>大娃（红娃）</li>
    <li>二娃（橙娃）</li>
    <li>三娃（黄娃）</li>
    <li>四娃（绿娃）</li>
    <li>五娃（青娃）</li>
    <li>六娃（蓝娃）</li>
    <li>七娃（紫娃）</li>
</ul>

<script>
    // 1.获取第一个元素
    console.log($('li:first').text());
    console.log($('li:last').text());

    // 2.获取偶数索引元素
    console.log($('li:even').text());
    console.log($('li:odd').text());
    // 3.获取指定索引2的元素
    console.log($('li:eq(2)').text());
</script>
</body>
</html>
```

### 5.对象遍历

```markdown
* 语法：
		jq对象.each(function(index,element){

		})
```



```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>08-对象遍历</title>
    <script src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>
<!--
jquery对象的遍历
    $.each() 用法示例
-->
<ul id="city">
    <li>北京</li>
    <li>上海</li>
    <li>天津</li>
</ul>

<script>

    // 获取ul的jq对象
    // let $city = $('#city li');
    let city = document.querySelectorAll('#city li');

    // js普通for
    for (let i = 0; i < city.length; i++) {
        console.log(city[i].innerText);
    }

    // js增强for
    for (let ele of city) {
        console.log(ele.innerText);
    }

    // jq遍历方法，集各家之所长...
    $(city).each(function (index,element) {
        // console.log(index);
        // console.log($city[index].innerText);
        // console.log(element);
        //console.log(element.innerText);

        // console.log($(element).text()); // js转jq，调用jq方法..
        console.log($(this).text()); // this表示当前 遍历的元素js对象
    })
</script>
</body>
</html>
```

## 第四章 Jquery的DOM操作

### 1.Jquery操作内容

```markdown
1. text(): 获取/设置元素的标签体纯文本内容
		* 相当于js： innerText属性

2. html(): 获取/设置元素的标签体超文本内容
		* 相当于js： innerHTML属性
```

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>09-dom操作内容</title>
    <script src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>
<div id="myDiv"><p>天王盖地虎</p></div>
<script>
            // 1.text()操作内容
            // 1.1获取div的纯文本
            //console.log($('#myDiv').text());
            // 1.2设置div纯文本
            //console.log($('#myDiv').text('<h1>宝塔镇河妖</h1>'));
            // 2.html()操作内容
            console.log($('#myDiv').html());
            console.log($('#myDiv').html("<h1>宝塔镇河妖</h1>"));
            // 2.3追加
            //console.log($('#myDiv').html($('#myDiv').text()+'<h1>宝塔镇河妖</h1>'));

        </script>
</body>
</html>
```





### 2.Jquery操作属性

```markdown
1. val()： 获取/设置 （input标签）value属性值
		相当于：input的js对象.value属性
		
2. attr(): 获取/设置元素的属性
	removeAttr()	删除属性
		
3. prop():获取/设置元素的属性
	removeProp()	删除属性
	jq在1.6版本之后，提供另一组API prop 通常处理属性值为布尔类型操作
		例如：checked selected等
```

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>10-dom操作属性</title>
    <script src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>
<form action="#" method="get">
    姓名 <input type="text" name="username" id="username" value="德玛西亚"/> <br/>

    爱好
    <input id="hobby" type="checkbox" name="hobby" value="perm"  >烫头<br/>

    <input type="reset" value="清空按钮"/>
    <input type="submit" value="提交按钮"/><br/>
</form>

<script>
    // 1.获取文本框value属性
            // console.log($("#username").val());
            $('#username').val('草丛伦...');


            // 获取指定属性的值
            console.log($('#hobby').attr('value'));
            // 设置/更新指定属性的值
            $('#hobby').attr('value', '烫头');
            // 删除属性
            $('#hobby').removeAttr('value');

            // 2.获取爱好的checked属性
            // attr() 方法操作 boolean类型的属性比较麻烦...
            // console.log($('#hobby').attr('checked'));
            console.log($('#hobby').prop('checked'));
</script>
</body>
</html>
```



### 3.Jquery操作样式

```markdown
1. 直接修改jq对象样式属性
	语法：
		jq对象.css()
			css(样式名) 获取
			css(样式名,样式值) 设置
			兼容性好：样式名支持：fontSize | font-size
			
2. 添加/删除jq对象样式
	语法：
		jq对象.addClass()
		jq对象.removeClass()
		
3. 切换jq对象样式
	语法：
		jq对象.toggleClass()  无添加，有删除
```



```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>11-dom操作样式</title>
    <script src="../js/jquery-3.2.1.min.js"></script>
    <style>
        #p1{ background-color: red;}
        .mp {
            color: green
        }

        .mpp {
            background-color: lightgray;
        }
    </style>

</head>
<body>
<p id="p1">1. 设置一个css样式</p>
<p id="p2">2. 批量设置css样式</p>
<p id="p3">3. 通过class设置样式</p>
<p id="p4">4. <button id="toggle">切换</button>class样式 </p>

<script>
    let $p1 = $('#p1');//获取p1
    let $p2 = $('#p2');//获取p2
    let $p3 = $('#p3');//获取p3
	let $p4 = $('#p4');//获取p4
	
    // 1. 设置一个css样式   <p id="p1" style="xxxxxxx">1. 设置一个css样式</p>
    //$p1.css('backgroundColor','skyblue');
    // $p1.css('background-color','skyblue');

    // 2. 批量设置css样式
    $p2.css({"background-color":"gold","border":"1px solid red"})

    // 3. 通过class设置样式  <p id="p3" class="mp mpp">3. 通过class设置样式</p>
    $p3.addClass('mp mpp');
    $p3.removeClass('mpp')
	
	// 4. toggleClass() 切换一个class
    $('#toggle').click(function () {
        $p4.toggleClass('mp mpp')
    })



</script>

</body>
</html>
```





### 4.Jquery操作元素

```markdown
1. $(标签) 创建一个标签
		$("<a>xx</a>")  相当于 document.createElement('li')
		
2. prepend()  在当前元素内部，首部添加子元素
		父对象.prepend(子对象)

3. append() 在当前元素内部，尾部添加子元素
		父对象.append(子对象)
		
------------------------------------------------
4. empty() 清空子元素 （断子绝孙）
		对象.empty()

5. remove() 删除自己  （满门抄斩）
		对象.remove()
```



```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>12-dom操作元素</title>
    <script src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>
<ul id="star">
    <li>古力娜扎</li>
    <li>迪丽热巴</li>
</ul>

<script>
    let $star = $('#star'); // 无序列表
            // 1.前面添加马尔扎哈
            $star.prepend($('<li>马尔扎哈</li>'));
            // 2.后面添加萨瓦迪卡
            $star.append($('<li>萨瓦迪卡</li>'));
            // 3.移出所有列表项
            //$star.empty();
            // 4.删除无序列表
            $star.remove();
</script>
</body>
</html>
```





## 第五章 Jquery事件绑定

jQuery的事件与js的事件的功能和作用一样，只是在使用语法上稍微有些差异。

```js
js对象.事件属性=function(){}

jq对象.事件函数(function(){})
```

**常见事件**

```markdown
1. 点击事件：
        1. click()：单击事件
        2. dblclick()：双击事件
        
2. 焦点事件
        1. blur()：失去焦点
        2. focus():元素获得焦点。

3. 鼠标事件：
        1. mousedown()	鼠标按钮被按下。
        2. mouseup()	鼠标按键被松开。
        3. mousemove()	鼠标被移动。
        4. mouseover()	鼠标移到某元素之上。
        5. mouseout()	鼠标从某元素移开。
        
4. 键盘事件：
		1. keydown()	某个键盘按键被按下。	
		2. keyup()		某个键盘按键被松开。
		3. keypress()	某个键盘按键被按下并松开。

5. 改变事件
        1. change()	域的内容被改变。

6. 表单事件：
        1. submit()	提交按钮被点击。
        
7. 页面加载事件：
		1. $(function(){})
```





```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>13-jq事件绑定</title>
    <script src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>
<input type="button" value="js方式" id="jsBtn"> <br>
<input type="button" value="jq方式" id="jqBtn"> <br>


<script>
    // js方式
    document.getElementById('jsBtn').onclick=function () {
        alert('js方式')
    }

    // jq方式
    $('#jqBtn').click(function () {
        alert('jq方式')
    })
</script>
</body>
</html>
```





## 第六章 综合案例

### 1.隔行换色

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>01-案例-隔行变色</title>
    <script src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>
<table id="tab1" border="1" width="800" align="center">
    <tr>
        <th width="100px"><input type="checkbox" id="checkId">全/<input type="checkbox" id="fan">反选</th>
        <script>
            $("#checkId").click(function () {
                $('input[class="checkbox"]').prop("checked",$(this).prop("checked"));
            });
            $("#fan").click(function () {
                $('input[class="checkbox"]').each(function () {
                    $(this).prop("checked",!$(this).prop("checked"));
                })
            })
        </script>
        <th>分类ID</th>
        <th>分类名称</th>
        <th>分类描述</th>
        <th>操作</th>
    </tr>
    <tr>
        <td><input type="checkbox" class="checkbox"></td>
        <td>1</td>
        <td>手机数码</td>
        <td>手机数码类商品</td>
        <td><a href="">修改</a>|<a href="">删除</a></td>
    </tr>
    <tr>
        <td><input type="checkbox" class="checkbox"></td>
        <td>2</td>
        <td>电脑办公</td>
        <td>电脑办公类商品</td>
        <td><a href="">修改</a>|<a href="">删除</a></td>
    </tr>
    <tr>
        <td><input type="checkbox" class="checkbox"></td>
        <td>3</td>
        <td>鞋靴箱包</td>
        <td>鞋靴箱包类商品</td>
        <td><a href="">修改</a>|<a href="">删除</a></td>
    </tr>
    <tr>
        <td><input type="checkbox" class="checkbox"></td>
        <td>4</td>
        <td>家居饰品</td>
        <td>家居饰品类商品</td>
        <td><a href="">修改</a>|<a href="">删除</a></td>
    </tr>
    <tr>
        <td><input type="checkbox" class="checkbox"></td>
        <td>5</td>
        <td>牛奶制品</td>
        <td>牛奶制品类商品</td>
        <td><a href="">修改</a>|<a href="">删除</a></td>
    </tr>
    <tr>
        <td><input type="checkbox" class="checkbox"></td>
        <td>6</td>
        <td>大豆制品</td>
        <td>大豆制品类商品</td>
        <td><a href="">修改</a>|<a href="">删除</a></td>
    </tr>
    <tr>
        <td><input type="checkbox" class="checkbox"></td>
        <td>7</td>
        <td>海参制品</td>
        <td>海参制品类商品</td>
        <td><a href="">修改</a>|<a href="">删除</a></td>
    </tr>
    <tr>
        <td><input type="checkbox" class="checkbox"></td>
        <td>8</td>
        <td>羊绒制品</td>
        <td>羊绒制品类商品</td>
        <td><a href="">修改</a>|<a href="">删除</a></td>
    </tr>
    <tr>
        <td><input type="checkbox" class="checkbox"></td>
        <td>9</td>
        <td>海洋产品</td>
        <td>海洋产品类商品</td>
        <td><a href="">修改</a>|<a href="">删除</a></td>
    </tr>
    <tr>
        <td><input type="checkbox" class="checkbox"></td>
        <td>10</td>
        <td>奢侈用品</td>
        <td>奢侈用品类商品</td>
        <td><a href="">修改</a>|<a href="">删除</a></td>
    </tr>
</table>
    <script>
        $('tr:even').css("backgroundColor","skyblue");
        $('tr:odd').css("backgroundColor","pink");
        // 给所有行绑定事件
        let oldColor;
        /*$("tr").mouseover(function () {
            oldColor=$(this).css("backgroundColor");
            $(this).css("backgroundColor","gold");
        }).mouseout(function () {
            $(this).css("backgroundColor",oldColor);
        });*/
        $("tr").hover(function () {
            oldColor=$(this).css("backgroundColor");
            $(this).css("backgroundColor","gold");
        },function () {
                $(this).css("backgroundColor",oldColor);
            }
        );
    </script>
</body>
</html>

```





### 2.商品全选

```html
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>案例-商品全选</title>
    <script src="../js/jquery-3.2.1.min.js"></script>
</head>
<body>
<!--
商品全选
    1. 全选 点击全选按钮,所有复选框都被选中
    2. 反选 点击反选按钮,所有复选框状态取反
-->
<button id="btn1">1. 全选</button>
<button id="btn2">2. 反选</button>
<br/>
<input type="checkbox">电脑
<input type="checkbox">手机
<input type="checkbox">汽车
<input type="checkbox">别墅
<input type="checkbox" checked="checked">笔记本
<script>
    $('#btn1').click(function () {
        // alert(1);
        $('input').prop('checked',true);
    });
    $('#btn2').click(function () {
        // alert(1);
       $("input").each(function () {
            $(this).prop("checked",!$(this).prop("checked"));
       })
    });

</script>
</body>
</html>
```

## 总结

```markdown
Jquery概述
	jQuery是一个优秀的javascript的轻量级框架
		写得少、做得多
Jquery基础语法
	HTML引入Jquery
		<script src="../js/jquery-3.2.1.min.js"></script>
	jQuery与JS区别
		jQuery对象与js对象相互转换
			$(js对象) 或 jQuery(js对象)
			jq对象(索引) 或 jq对象.get(索引)
		页面加载事件
			window.onload=function(){}
				只能定义一次
			$(function(){})
				可以定义多次
Jquery选择器
	1 基本选择器
		$("html标签名")  标签选择器
		$("#id的属性值")  id选择器
		$(".class的属性值")
		$("选择器1,选择器2....")
	2 层级选择器
		$("A B")
		$("A > B")
	3 属性选择器
		$("A[属性名='值']")
		$("A[属性名='值'][]...")
	4 基本过滤选择器
		:first  第一个
		:last 最后一个
		:even   索引偶数
		:odd  索引奇数
		:eq(index)  指定索引
	5 对象遍历
		jq对象.each(function(index,element){})
Jquery的DOM操作
	Jquery操作内容
		html()  设置获取
		text()
	Jquery操作属性
		val() 获取设置值
		attr()
			removeAttr()
		prop()
			removeProp()   布尔类型推荐用
				checked
				selected
	Jquery操作样式
		jq对象.css()
		jq对象.addClass()
			jq对象.removeClass()
	Jquery操作元素
		$("<a></a>")
		prepend()   首位添加
		append()  末尾添加
		remove()  删除内部所以
		empty()  删除自己
Jquery事件绑定
	js对象.事件属性=function(){}
	jq对象.事件函数(function(){})
综合案例
	隔行换色
	商品全选
```



