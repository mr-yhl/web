# web

<center><span style="font-family: Calibri;font-size: 30px">WEB学习代码笔记</span></center>
<div style="background:#86b0ed;width: 100%;">
<img src="ico.png" style="margin: 10px;width: 75px;float: left"  />
<center>
<span style="align:left">
业精于勤，荒于嬉；行成于思，毁于随。<br>
方今圣贤相逢，治具毕张。拔去凶邪，登崇畯良。
占小善者率以录，名一艺者无不庸。<br>爬罗剔抉，刮垢磨光。
盖有幸而获选，孰云多而不扬？
诸生业患不能精，无患有司之不明；行患不能成，无患有司之不公。
</span>
</center>
</div>

这是javaWeb阶段的工程笔记，保存了web阶段的代码与笔记。

note是笔记文件夹,里面有md格式的笔记,也有整理好的思维导图.

dayXX_xxx_xxx为文件夹下是代码也有笔记存在,根据readme文档的介绍,可直接找到对应的内容.

## [day01_html_css](https://github.com/mr-yhl/web/tree/master/day01_html%26css)

今天主要介绍了html的基础部分内容,报告文件的结构,常用的标签,重点是form表单,也简单的介绍了部分css的内容.

+ html
  + 文件结构
  + 基本标签
  + table
  + form表单
+ css
  + css格式
  + 三种引入方法
  + 选择器

## [day02css与js基础](https://github.com/mr-yhl/web/tree/master/day02_css%26js)

今天的内容讲的是css的基础内容,与js的入门基础内容,包括引入方法,函数等内容.

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

## [day03js高级](https://github.com/mr-yhl/web/tree/master/day03_js)

今天主要介绍了js的相关内容,报告对象,事件等内容,还有四个小练习

+ js的常用内置对象

  + String
  + Array
  + Date
  + Math

+ BOM对象

  + window
  + location

+ Dom对象

+ 正则表达式

## [day04JQuery](https://github.com/mr-yhl/web/tree/master/day04_jquery)

今天学习了JQuery的基础内容,简单的使用,还了解了json和ajax的内容.

+ JQuery介绍
+ JQuery引入
+ JQuery选择器
+ 操作DOM
+ 事件绑定
+ json基础
+ 格式转换
+ ajax入门

## [day05-tomcat](https://github.com/mr-yhl/web/tree/master/day05-tomcat)

今天主要讲了关于tomcat服务器的介绍，主要参考note文件夹里的笔记

## [day06-servlet-xml](https://github.com/mr-yhl/web/tree/master/day06-servlet-xml)  [day06-servlet-anno](https://github.com/mr-yhl/web/tree/master/day06-servlet-anno)  [day06-servlet-context](https://github.com/mr-yhl/web/tree/master/day06-servlet-context)

今天主要学习了servlet部分的内容，包括创建，相关api，生命周期等内容。

+ 概述
+ 快速入门【重点】
+ api
+ 体系结构
+ servlet路径
  + 绝对路径
  + 相对路径
+ 注解（3.0）
+ servletContext（表示当前运行web项目）

## [day07_request](https://github.com/mr-yhl/web/tree/master/day07_request)  [day07_response](https://github.com/mr-yhl/web/tree/master/day07_response)

+ Request请求对象，获取  http请求消息格式（行、头、体）
+ Response响应对象，设置  http响应消息格式（行、头、体）

## [day08_cookie](https://github.com/mr-yhl/web/tree/master/day08_cookie)  [day08_session](https://github.com/mr-yhl/web/tree/master/day08_session) [day08_case](https://github.com/mr-yhl/web/tree/master/day08_case)

今日主要是学习了关于会话相关的内容,有cookie和session,还简单的认识了jsp,练习了一些小的案例。

+ 会话介绍
+ cookie（客户端）
  - addCookie
  - getCookies
+ jsp初识
+ session（服务器）
  - setAttribute
  - getAttribute
+ 域对象

## [day09_jsp](https://github.com/mr-yhl/web/tree/master/day09_jsp)  [day09_case](https://github.com/mr-yhl/web/tree/master/day09_case)

今天主要学习了jsp相关的内容,包括jsp的一些基础内容,还是mvc模型,上传架构等内容.还有el 、jstl的使用.

+ Jsp
  + 内置对象
  + 指令
  + 动作标签
+ MVC模式【感知】
+ EL
  + 获取值
  + 运算符
+ JSTL
  + 导包
  + 引入
+ 三层架构【MVC升级版】
+ 用户列表查询

## [day10_filter](https://github.com/mr-yhl/web/tree/master/day10_filter)  [day10_listener](https://github.com/mr-yhl/web/tree/master/day10_listener)  [day10_fileupload](https://github.com/mr-yhl/web/tree/master/day10_fileupload)

今日内容主要是学习了过滤器和监听器,练习了文件上传
+ web的三大组件
  + servlet：控制器
  + filter：过滤器【重点】
  + listener：监听器【了解】	
+ 文件上传（理解思想，了解代码...）



## [day15_case](https://github.com/mr-yhl/web/tree/master/day15_case)  [day15_jdbc](https://github.com/mr-yhl/web/tree/master/day15_jdbc)

+ jdbc基础
  + 通过java代码实现对数据的增删改查
+ 用户登录（BUG）
+ PreparedStatement（重点）
+ 连接池（容器）
  + Druid：德鲁伊

## [day16_mybatis_quick](https://github.com/mr-yhl/web/tree/master/day16_mybatis_quick)  [day16_mybatis_dao_impl](https://github.com/mr-yhl/web/tree/master/day16_mybatis_dao_impl)  [day16_mybatis_dao_proxy](https://github.com/mr-yhl/web/tree/master/day16_mybatis_dao_proxy)  [day16_mybatis_condition](https://github.com/mr-yhl/web/tree/master/day16_mybatis_condition)

通过mybatis框架，实现对单表的增删改查

## [day17_mybatis_dynamic_sql](https://github.com/mr-yhl/web/tree/master/day17_mybatis_dynamic_sql)  [day17_mybatis_multi](https://github.com/mr-yhl/web/tree/master/day17_mybatis_multi)  [day17_mybatis_nest](https://github.com/mr-yhl/web/tree/master/day17_mybatis_nest) 

+ 动态sql
+ 多表关联查询
+ 多表嵌套查询
+ 延迟（懒）加载【了解】
+ 内置缓存【了解】
    + 一级缓存
    + 二级缓存
    
## [day18_config](https://github.com/mr-yhl/web/tree/master/day18_config) [day18_anno](https://github.com/mr-yhl/web/tree/master/day18_anno) [day18_anno_nest](https://github.com/mr-yhl/web/tree/master/day18_anno_nest) [day18-case](https://github.com/mr-yhl/web/tree/master/day18-case)
+ 回顾核心配置文件标签

+ mybatis注解开发
  + 单表【掌握】
  + 多表【了解】

+ mybatis案例练习
  + 基于B/S架构，做一个查询（分页）
