## 今日内容

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




## 第一章 JSP

### 1 概述

​	在很多动态网页中，绝大部分内容都是固定不变的，只有局部内容需要动态产生和改变。 为了弥补Servlet的缺陷，SUN公司在Servlet的基础上推出了JSP（Java Server Pages）

​	 JSP是简化Servlet编写的一种技术，它将Java代码和HTML语句混合在同一个文件中编写，页面动态资源使用java代码，页面静态资源使用html标签。

**简单来说：可以在html标签中嵌套java代码**

**作用：简化书写，展示动态页面**



### 2 快速入门

**需求**

​	在jsp页面，动态展示当前时间

```markdown
<%@ page import="java.time.LocalDateTime" %><%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 8:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>index</title>
  </head>
  <body>
   <%
       LocalDateTime now = LocalDateTime.now();
       out.write("当前时间"+now);
   %>


      <h3>el知识学习</h3>
      <a href="${pageContext.request.contextPath}/c_el/demo01.jsp">EL介绍和语法</a>
      <a href="${pageContext.request.contextPath}/c_el/demo02.jsp">EL取值练习</a>
      <a href="${pageContext.request.contextPath}/c_el/demo03.jsp">el表达式运算符</a>
  </body>
</html>

```





###  3工作原理

- JSP本质上就是一个Servlet

###  4脚本和注释

#### 4.1 脚本

**JSP通过脚本方式来定义java代码**

```markdown
1. <% 代码 %>
		脚本片段,生成在java文件中service方法中,每次请求的时候都会执行
		
2. <%! 代码 %>
		声明片段,生成在java文件中的成员位置

3. <%=代码 %>
		输出脚本片段,相当于 out.print("代码")方法，输出到jsp页面
```



#### 4.2 注释

```markdown
1. html注释
		<!-- 注释静态资源 -->
		
2. JSP注释
		<%-- 注释所有 --%>

3. java注释（JSP脚本内使用）
		// 单行
		/* 多行 */
		/**文档 */
```

**注释作用范围**

| 注释 | JSP源码 | java源码 | html源码 |
| ---- | ------- | -------- | -------- |
| html | 可见    | 可见     | 可见     |
| java | 可见    | 可见     | 不可见   |
| JSP  | 可见    | 不可见   | 不可见   |



> 注释是给程序员看的，可见的范围小，安全级别就高...

```jsp
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 8:59  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>demo01</title>
    </head>
    <body>
        <h3>脚本和注释</h3>
        <%--三个脚本声明方式--%>
        <%--
            <% 代码 %>:脚本片段,生成在java文件中service方法中,每次请求的时候都会执行
            <%! 代码  %>:声明片段,生成在java文件中的成员位置
            <%＝代码 　％＞:输出脚本片段,相当于 out.print("代码")方法，输出到jsp页面
        --%>
        <%
            System.out.println("hello jsp");
            out.write("获取的值:"+num);
        %>

        <%!
            int num = 10;

        %>
        <%=num %>
        <%--三种注释方式--%>
        <%--
            1.静态注释  <!-- -->
            2.jsp注释   < % -- --% >
            3.java注释  //  , /*  */,/**  */
        --%>
        <!--<h3>你好</h3>-->
        <%--<h3>你好</h3>--%>
        <%
            // Java注释
            /* 多行注释 */
            /**
             * 文档注释
             */

        %>

    </body>
</html>

```



### 5 指令

```markdown
* 作用
		用于配置JSP页面，导入资源文件
* 格式
		<%@ 指令名称 属性名1="属性值1" 属性名2="属性值2" ...%>
		
* 三大指令
	1. page：配置JSP页面
	
	2. include：页面包含（静态）
	
	3. taglib:导入资源文件
```

#### 5.1 page指令

```markdown
* 举例
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	
* 常用属性
	contentType：就相当于response.setContentType();
		用于设置响应体的类型和MIME类型
	language：指定jsp模板的编程语言
		属性值非常鸡肋，就是java
	import：导包，可以单独使用此属性
		<%@ page import="java.util.*" %>
	-----------------------------------------------
	errorPage：当前页面报错后，可以跳转到指定页面
		errorPage="500.jsp"
	isErrorPage：当前页面升级为错误处理页面，可以获取exception异常捕获信息
		isErrorPage="true" 默认关闭...
```

```jsp
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="500.jsp" %>
<html>
    <head>
        <title>demo02</title>
    </head>
    <body>
        <%--
        contentType: 用于设置响应体的类型和MIME类型
        language: 指定jsp模版的编程语言   属性值鸡肋,只有java
        import: 导包,可以单起一行
        errorPage: 当前页面报错后可以跳转的页面
        isErrorPage: 升级为错误页面,可以捕获异常
        --%>
        <h3>page指令</h3>
        <%
            Date date = new Date();
            ArrayList<Object> objects = new ArrayList<>();
            int a = 1/0;
        %>
    </body>
</html>

```

> 500 友情提示页面

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 9:19  
--%>
<html>
    <head>
        <title>500</title>
    </head>
    <body>
        <h3>服务器忙.....</h3>
        <%
            System.out.println(exception.getMessage());
        %>
    </body>
</html>

```







#### 5.2 include指令

> （静态）包含技术

```markdown
* 举例
	<%@include file="被包含的页面"%>
```

> header.jsp
>
> 

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 9:44  
--%>
<html>
    <head>
        <title>header</title>
    </head>
    <body>
        <h3>头部</h3>
        <div>log</div>
        <div>banner</div>
    </body>
</html>

```

> demo.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 9:45  
--%>
<html>
    <head>
        <title>demo03</title>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <h3>include指令页面</h3>
    </body>
</html>

```

#### 5.3 taglib指令

> 用于导入apache提供的jstl标签库，为下午的内容做铺垫

#### ① 导入JSTL包

 /WEB-INF/lib/

> javax.servlet.jsp.jstl.jar
>
> jstl-impl.jar

#### ② 引入JSTL的标签库

 ```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 ```

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 9:50  
--%>
<html>
    <head>
        <title>demo04</title>
    </head>
    <body>
        <h3>taglib指令</h3>

        <c:if test="false">显示内容</c:if>
    </body>
</html>
```

#### 5.4 知识小结

```markdown
1. page指令
		contentType：指定响应体类型和MIME类型
		language：仅支持java语言
		import：导入jar包
		
2. include指令
		静态包含页面资源
		
3. taglib指令
		导入apache提供的jstl标签库
```



### 6 内置对象（了解）

```markdown
* 作用
		在JSP页面中不需要获取和创建，可以直接使用的对象
		
* JSP一共有9个内置对象
	变量名				  真实类型					作用
	pageContext		    PageContext			 	 当前页面中共享数据
    request				HttpServletRequest       一次请求中共享数据
    session             HttpSession				 一次会话中共享数据
    application			ServletContext			 整个web应用共享数据
    ---------------------------------------------------------------------
    response			HttpServletResponse		 响应对象
    page				Object					 当前页面(servlet)对象
    out                 JSPWriter				 输出对象
    config              ServletConfig			 servlet配置对象
    exception           Throwable				 异常对象【默认关闭】		

* 常用
	1. pageContext
			a）当前页面共享数据，域对象
			b）可以获取其他八个内置对象
	2. request
			a）接收请求
			b）一次请求中，共享数据
	3. response
			响应结果
	4. out
			jsp页面特有的字符输出对象
				☆ print()
				write()
```



```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 9:55  
--%>
<html>
    <head>
        <title>demo05</title>
    </head>
    <body>
        <%--
        内置对象: 一共有9个,不需要创建和获取,可以直接使用
        默认是8个,异常对象默认关闭
        常用的四个:
        pageContext:域对象,最小的域对象,当前页面中共享数据
            可以获取其他八个对象
        request:
            接收请求
            一次请求中,共享数据
        respond:
            响应结果
        out:
            jsp页面特有的字符输出对象
            ☆ print()
            write()
        --%>
        <%
            // pageContext域对象
            pageContext.setAttribute("username","lucy");
            // pageContext获取其他八个内置对象

        %>

        <%=pageContext.getAttribute("username") %>
    </body>
</html>

```





### 7 JSP动作标签（了解）

```markdown
* 作用
		简化JSP页面java代码
	
* 常用
	<jsp:include>：页面包含（动态）
		request.getRequestDispatcher("footer").include(request, response);
	<jsp:forward>：请求转发（页面跳转）
		request.getRequestDispatcher("b.jsp").forward(request, response);
	<jsp:param>：参数传递
```



#### 7.1 动态包含

> demo6.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 10:15  
--%>
<html>
    <head>
        <title>demo06</title>
    </head>
    <body>
        <h3>include动态包含</h3>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>

```



> footer.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 10:16  
--%>
<html>
    <head>
        <title>尾部</title>
    </head>
    <body>
        友情链接 <br>
        公司版权
    </body>
</html>

```





> 动态包含和静态包含区别...

 

```markdown
静态包含(在开发中jsp页面用,性能好)
	合并转换成一个文件,进行编译,只执行一次
	注意:使用静态包含,多个页面变量不能重名
动态包含
 	转换成多个Java文件,分别编译,将结果合并输出为一个页面
```



> 特点：在开发中jsp页面使用静态包含，性能好
>
> 注意：使用静态包含，多个jsp页面变量不能重名...





#### 7.2 请求转发

> a.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 10:06  
--%>
<html>
    <head>
        <title>a</title>
    </head>
    <body>
        <%
            request.setCharacterEncoding("utf-8");
            System.out.println("a执行了");
            // 请求转发
            // request.getRequestDispatcher("b.jsp").forward(request, response);
        %>
        <%--不能在内部加注释,传递中文需要在传参数的文件进行编码设置--%>
        <jsp:forward page="b.jsp">
            <jsp:param name="name" value="jack"/>
            <jsp:param name="age" value="18"/>
        </jsp:forward>
    </body>
</html>

```



> b.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 10:06  
--%>
<html>
    <head>
        <title>b</title>
    </head>
    <body>
        <%
            System.out.println("b.jsp执行了...");
            System.out.println(request.getParameter("name"));
            System.out.println(request.getParameter("age"));
        %>
    </body>
</html>

```



> 注意事项：<jsp:forward> 不允许出现注释，而且传递的<jsp:param>编码应该在，a.jsp 处理



## 第二章 MVC模式

### 1 JSP发展史

- 早期只有servlet，只能使用response输出html标签，非常麻烦。 
- 后来有了JSP，简化了servlet开发；如果过度使用JSP，在JSP页面中写了大量的java代码和html标签，造成难于维护，难于分工协作的场景。 
  
- 再后来为了弥补过度使用jsp的问题，我们使用servlet+jsp这套组合拳，利于分工协作。

 



### 2 MVC介绍

> MVC设计模式： Model-View-Controller简写。
>

MVC是软件工程中的一种软件架构模式，它是一种**分离业务逻辑**与**显示界面**的设计方法。

```markdown
* M：model（模型）
		JavaBean（普通java类）
			1.处理业务逻辑
			2.封装实体

* V：view（视图）
		Jsp
			展示数据（动态资源）

* C：controller（控制器）
		Servlet
			1.接收请求
			2.调用模型
			3.转发视图

* 优缺点
	优点
		降低耦合性，方便维护和拓展，利于分工协作
	缺点
		使得项目架构变得复杂，对开发人员要求高
```

 ![](https://img04.sogoucdn.com/app/a/100520146/618244609A0D6128B5634FA555025B7B)







## 第三章 （jsp）EL

### 1 概述

表达式语言（Expression Language）

**作用**：主要用来代替jsp中脚本的功能，简化对java代码的操作。 

**语法**：${表达式}



### 2 使用

#### 2.1 获取值

> EL表达式：只能从域对象（4个域）中获取数据
>



**语法**

```markdown
* 标准
	1. ${pageScope.键名} 
			从page域中获取指定键名对应的值

	2. ${requestScope.键名} 
			从request域中获取指定键名对应的值

	3. ${sessionScope.键名} 
			从session域中获取指定键名对应的值

	4. ${applicationScope.键名} 
			从servletContext域中获取指定键名对应的值
		
* 简化
	${键名}
			从最小的域开始匹配，匹配成功直接返回数据（值）
			注意：使用这种方式，尽量避免四大域的键名重复问题。。。
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 14:39  
--%>
<html>
    <head>
        <title>demo01</title>
    </head>
    <body>
        <h1>El表达式只能从域对象获取值</h1>
        <%
            // 模拟servlet,向四个域存值
            pageContext.setAttribute("username", "公孙代"); // 仅jsp中存在...
            pageContext.setAttribute("age", 22);
            request.setAttribute("age", 18);
            session.setAttribute("sex", "女");
            application.setAttribute("address", "北海郡");
        %>
        <h3>EL标准语法</h3>
        <%--el底层进行了非空处理--%>
        ${pageScope.username} <br>
        ${requestScope.age} <br>
        ${sessionScope.sex} <br>
        ${applicationScope.address} <br>



        <h3>EL的简单语法</h3>
        <%--${键名} 从最小的域开始匹配,匹配到返回数据--%>
        ${username} <br>
        ${age}<br>
        ${sex}<br>
        ${address} <br>




        <%--注意事项:使用这种方法,应避免变量名重复--%>
    </body>
</html>

```







**练习**

```markdown
1. 获取字符串
		${键名}
		
2. 获取User对象
		${对象.属性名}

3. 获取List集合、Array数组
		${list[索引]}

4. 获取Map集合
		${map.key} | ${map.get('key')}
```

```jsp
<%@ page import="cn.com.mryhl.domain.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 14:52  
--%>
<html>
    <head>
        <title>demo02</title>
    </head>
    <body>
        <h3>EL获取User对象</h3>
        <%
            User user = new User("荆卡璜", 16, "荆州");
            request.setAttribute("user",user);
        %>
        ${user} <br> <%--执行默认的tostring方法--%>
        ${user.username} | ${user.age} | ${user.address}


        <h3>EL获取list集合</h3>
        <%
            ArrayList<Object> list = new ArrayList<>();
            list.add("东方盖书");
            list.add("公孙定");
            list.add("厍瑷高");
            list.add(user);
            request.setAttribute("list",list);
        %>
        ${list} <br><%-- 默认对象的toString方法--%>
        ${list[0]} <%-- 集合和数组都这样使用 --%>
        ${list[10]} <%-- 索引越界也不会出错 --%>
        ${list[3].username}


        <h3>el获取map集合</h3>
        <%
            Map<String,Object> map = new HashMap<>();
            map.put("key1","士大夫");
            map.put("key2","现场v");
            map.put("key2","公孙定");
            map.put("key.4","师范");

            request.setAttribute("map",map);
        %>

        ${map} <br><%-- 默认对象的toString方法--%>
        ${map.key1} | ${map.get("key.4")}


    </body>
</html>

```

#### 2.2 执行运算

```markdown
* 算数运算符
		语法： + - * /(div) %(mod)
		
* 比较运算符
		语法：> < >= <= ==(eq) !=(ne)
		
* 逻辑运算符
		语法：&&(and) ||(or) !(not)
		
* 三元运算符
		语法：${条件表达式？为真:为假}

---------------------------------------------------
		
* 空运算符
 		语法：${not empty 对象}
 		功能：
 			可以判断字符串和对象是否为空
 			可以判断一个集合的长度是否为0
```

```jsp
<%@ page import="cn.com.mryhl.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 15:08  
--%>
<html>
    <head>
        <title>demo03</title>
    </head>
    <body>
        <h3>三元运算符</h3>

        <%
            Integer a = 2;

            Integer b = 5;

            request.setAttribute("a", a);
            request.setAttribute("b", b);

            User user = new User();
            request.setAttribute("user", user);

            List list = new ArrayList();
            list.add("哈哈");
            request.setAttribute("list", list);
        %>

        ${a > b ? "a大" : "b大" }


        <h3>非空判断</h3>
        ${not empty user} <br> <%-- if(user != null){xxx} --%>
        ${not empty list} <br> <%-- if(list !=null && list.size() > 0 ){xxx} --%>


        <h3>空值判断</h3>

        ${empty user} <br><%-- if(user == null){xxx} --%>
        ${empty list} <br><%-- if(list ==null || list.size() < 1 ){xxx} --%>


    </body>
</html>

```

#### 2.3 隐式对象（了解）

```markdown
* el表达式中有11个隐式对象

	目前阶段仅使用：pageContext
		这哥们也是jsp的九大内置对象之一，可以获得其他八个内置对象...
	可以动态获取该项目的网络地址	
		${pageContext.request.contextPath}
```



#### 2.4 EL补充知识

```markdown
* jsp在2.5版本之后，默认支持el表达式

* 如果要忽略el表达式
	1）忽略当前jsp页面中所有的el表达式
		设置jsp中page指令中：isELIgnored="true" 属性
	2）忽略单个el表达式
		\${表达式}
```



```jsp
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

```





## 第四章 JSTL

### 1 概述

Jsp 标准标签库（Jsp Standard Tag Library），是由Apache组织提供的开源的jsp标签库

作用：替换和简化jsp页面中java代码的编写

JSTL标准标签库有5个子库，但随着发展，目前常使用的是它的核心库

| **标签库**          | **标签库的URI**                        | **前缀** |
| ------------------- | -------------------------------------- | -------- |
| **Core**            | http://java.sun.com/jsp/jstl/core      | c        |
| 国际化(过时)        | http://java.sun.com/jsp/jstl/fmt       | fmt      |
| SQL(过时)           | http://java.sun.com/jsp/jstl/sql       | sql      |
| XML(过时)           | http://java.sun.com/jsp/jstl/xml       | x        |
| Functions(几乎不用) | http://java.sun.com/jsp/jstl/functions | fn       |



### 2 Core标签使用

#### 2.1 使用步骤

#### ① 导入JSTL包

 /WEB-INF/lib/

> javax.servlet.jsp.jstl.jar
>
> jstl-impl.jar 

#### ② 引入JSTL的标签库

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

 





#### 2.2 常用标签

#### ① c:if标签

```markdown
* 相当于java中: if(表达式内容){}

* 语法：
	<c:if test="表达式内容"></c:if>
		jstl标签库通常结合el一起使用，完成动态判断功能
	注意：c:if标签没有else功能，想要实现取反的效果，需要重写c:if标签，条件进行取反...
```

```jsp
<%@ page import="cn.com.mryhl.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 15:43  
--%>
<html>
    <head>
        <title>demo01</title>
    </head>
    <body>
        <h3>jstl的if标签</h3>
        <%
            User user = new User();
            user.setUsername("lucy");
            session.setAttribute("user",user);
        %>
        <c:if test="${not empty user}">
            欢迎您：${user.username}
        </c:if>
        <%--c:if标签没有else功能,需重写标签,条件取反--%>
        <c:if test="${empty user}">
            请您登录后浏览....
        </c:if>
    </body>
</html>

```





#### ② c:forEach标签  

```markdown
* 普通for
	java语法：
		for(int i=1;i<=5;i++){
			i
		}
	jstl语法：
		<c:forEach begin="1" end="5" step="1" var="i">
			${i}
		</c:forEach>
			begin：起始值
			end：结束值
			step：步长
			var：当前遍历的临时变量名


* 增强for
	java语法：
		for(User user : list){
			user
		}
	jstl语法：
		<c:forEach items="${list}" var="user" varStatus="vs">
			${user}
		</c:forEach>
			items：需要遍历的集合
			var：当前遍历的临时变量名
			varStatus：当前遍历元素的状态
				index：索引
				count：计数器
```



```jsp
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.com.mryhl.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 15:51  
--%>
<html>
    <head>
        <title>demo02</title>
    </head>
    <body>
        <h3>普通for循环</h3>
        <%--
        <c:forEach begin="1" end="5" step="1" var="i" >
            ${i}
        </c:forEach>
            begin:起始值
            end:结束值(包含)
            step:步长
            var:遍历的变量

        jstl使用pageContext这个域对象
        --%>
        <c:forEach begin="1" end="5" step="1" var="i">
            <div>${i}</div>
        </c:forEach>

        <h3>增强for</h3>
        <%--
        <c:forEach items="${list}" var="user" varStatus="v">
            ${user}
        </c:forEach>
            items:要遍历的集合
            var:遍历的变量
            varStatus:当前元素的状态
                index:索引
                count:计数器
        --%>
        <%
            List<User> list = new ArrayList<>();
            list.add(new User("王昭君", 18, "峡谷"));
            list.add(new User("电耗子", 22, "祖安"));
            list.add(new User("李白", 39, "大唐"));
            request.setAttribute("list",list);
        %>
        <c:forEach items="${list}" var="user" varStatus="vs">
            <div>${user.username} | 索引：${vs.index} | 计数器： ${vs.count}</div>
        </c:forEach>
    </body>
</html>

```

> 切记，在jsp当中使用jstl标签，务必先引入 core核心标签库

 





## 第五章三层架构

> 就是MVC升级版本...

 ![](https://img04.sogoucdn.com/app/a/100520146/2DBAC26807F0AC52A1AAF20E9D5074E6)



### １概述

通常意义上的三层架构就是将整个业务应用划分为：表示层、业务逻辑层、数据访问层。

区分层次的目的 为了**高内聚低耦合**的思想

> 表示层：又称为 web层，与浏览器（用户）进行数据交互的。
>
> 业务逻辑层：又称为service层，用于处理业务功能的。 
>
> 数据访问层：又称为dao层，与数据库进行数据交互的
>



![image-20200827162549729](assets/image-20200827162549729.png) 

### ２.包目录结构

```markdown
* com.itheima 公司域名倒写

* com.itheima.dao 持久层

* com.itheima.service 业务层

* ccom.itheima.web 表示层

* com.itheima.domain 实体（JavaBean）

* com.itheima.util 工具
```

## 第六章 案例：用户信息列表展示

**需求**

使用MVC模式升级版（三层架构）开发代码，完成用户显示列表功能。 

### 代码实现

#### ① 创建web模块

 day09_case

#### ② 导入jstl的jar包

 /WEB-INF/lib/

> javax.servlet.jsp.jstl.jar
>
> jstl-impl.jar



#### ③ 导入user实体

> 定义三层架构的包结构..
>
> user.java





#### ④ index.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 16:47  
--%>
<html>
  <head>
    <title>index</title>
  </head>
  <body>
      <h5>
          <a href="${pageContext.request.contextPath}/FindAllServlet">查看用户列表</a>
      </h5>
  </body>
</html>

```



#### ⑤ FindAllServlet

```java
@WebServlet("/FindAllServlet")
public class FindAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收请求(没有)

        // 2.调用service查询
        UserService userService = new UserService();
        // 创建对象集合
        List<User> list = userService.findAll();

        // 3.转发视图
        request.setAttribute("list",list);
        request.getRequestDispatcher("/list.jsp").forward(request,response);



    }
}
```

#### ⑥ UserService

```java
public class UserService {

    public List<User> findAll() {

        // 调用dao查询
        UserDao userDao = new UserDao();
        return userDao.findAll();
    }
}
```





#### ⑦ UserDao

```java
public class UserDao {

    private static List<User> list = new ArrayList<>();

    static {
        list.add(new User("1", "西施", "女", 28, "香港", "7766521", "7766521@qq.com"));
        list.add(new User("2", "甄姬", "女", 12, "天津", "7766541", "7766541@qq.com"));
        list.add(new User("3", "宋轶", "女", 33, "大连", "7726521", "7726521@qq.com"));
        list.add(new User("4", "蔡文姬", "女", 19, "釜山", "7736521", "7736521@qq.com"));
        list.add(new User("5", "安妮", "女", 19, "釜山", "7736521", "7736521@qq.com"));
        list.add(new User("6", "貂蝉", "女", 19, "釜山", "7736521", "7736521@qq.com"));
    }

    public List<User> findAll() {

        return list;
    }
}

```

#### ⑧ list.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: mr_yhl
  Date: 2020/8/27
  Time: 16:52  
--%>
<html>
    <head>
        <title>list</title>
    </head>
    <body>
        <table border="1" width="500px" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td>编号</td>
                <td>姓名</td>
                <td>性别</td>
                <td>年龄</td>
                <td>地址</td>
                <td>ＱＱ</td>
                <td>邮箱</td>
            </tr>

            <c:forEach items="${list}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.sex}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                </tr>

            </c:forEach>

        </table>


    </body>
</html>

```

## 总结

```markdown
jsp
	概述
		运行在服务器的java页面
			既可以编写java代码，又可以编写html标签
	本质
		就是servlet
	脚本
		<% 脚本片段，相当于service方法中的代码 %>
		<%! 脚本声明，相当于成员位置 %>
			注意：servlet是一个单例对象
		<%=脚本表达式 %>
			相当于out.print()方法
	注释
		html
			<!--  只能注释静态资源，不太安全 -->
		jsp
			<%--  可以注释一切，相对安全 --%>
		java（脚本内部）
			// 单行注释
			/*
			多行注释
			*/
	指令
		page：配置jsp页面
			contentType
				设置响应体的MIME类型和编码方式
			language
				只能是java语言
			import
				导包
			errorPage
				跳转到友情提示页面
			isErrorPage
				捕获异常信息
					true
					false
		include：页面包含（静态）
			生成一个java文件，编译为class，执行
		taglib：导入外部资源
			apache的jstl标签库
	九大内置对象
		pageContext
			1）当前页面中共享数据（域对象）
			2）获取其他八个内置对象
		request
		response
		out
			在jsp中，只能使用out输出字符
				print()
					可以输出一切类型
				write()
					只能输出字符类型
	jsp动作标签
		jsp:include
			页面包含（动态）
				推荐使用静态包含
		jsp:forward
			请求转发
		jsp:param
			就是include和forward子标签，用于数据的传递
				可以通过request.getParameter()获取参数
mvc
	jsp发展史
		早期还有servlet
		过度使用jsp
		servlet+jsp完成互补
	mvc设计模式
		高内聚，低耦合
		M
			model（模型）
				javaBean（1.处理业务逻辑   2.封装实体）
		V
			view（视图）
				jsp（展示数据）
		C
			controller（控制器）
				servlet（1.接收请求  2.调用模型  3.转发视图）
el
	概述
		专门从域中获取数据
	语法
		从指定域中获取
			了解
		从最小域开始查找，若匹配上则停止
			${键名}
	练习
		字符串
		实体对象
			${键名.属性名}
				属性：setter/getter方法截取后的产物
					一般情况下域成员变量命名一致
		list集合
			${键名[索引]}
		map集合
			${键名.key}
			${键名["key"]}
	运算符
		非空判断
			${not empty 键名}
				字符串、对象、集合（非空和长度）
	隐式对象
		有11个隐式对象
			${pageContext.request.contextPath}
				动态获取项目名（虚拟路径）
jstl
	概述
		apache提供的jsp标准标签库
	使用
		if标签
			<c:if test="boolean值"></c:if>
				为真，显示标签体的内容
				为假，不显示内容
				通常与el表达式结合使用
				没有else功能，需要通过条件取反模拟
		forEach标签
			1）普通for
				begin 起始值
				end  结束值
				step  步长
				var  临时变量
			2）增强for
				items  被遍历的集合
				var  临时变量
				varStatus  遍历状态
					index 索引
					count  计数器
			补充 遍历map集合
				底层 entrySet
					entry.key
					entry.value
三层架构
	在mvc设计模式基础之上，进行了细化
	在程序的设计过程中，分层三块
		表示层
			web层
				与浏览器交互
		业务层
			service层
				处理业务逻辑
		持久层
			dao层
				与数据库交互
大作业：用户列表查询
	使用三层架构和MVC模式开发代码，完成用户显示列表功能
```

