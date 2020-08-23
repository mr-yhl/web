---

---


<center>Servlet入门</center>

##　今日目标

+ 概述
+ 快速入门【重点】
+ api
+ 体系结构
+ servlet路径
  + 绝对路径
  + 相对路径
+ 注解（3.0）
+ servletContext（表示当前运行web项目）



## 第一章 Servlet概述

- servlet= server+applet 运行在服务器端的java程序。
- Servlet是一个接口，一个类要想通过浏览器被访问到,那么这个类就必须直接或间接的实现Servlet接口

**作用**

接收请求，处理逻辑，响应结果

+ servlet与普通的java程序的区别

  1. 必须实现servlet接口

  2. 必须在servlet容器（服务器）中运行

  3. servlet程序可以接收用户请求参数以及向浏览器输出数据

## 第二章 Servlet快速入门

### 1 代码编写

##### ① 创建web项目

|-项目名

​	|-src

​	|-web

​		|-WEB-INF

```markdown
① 创建web项目
② 创建普通的java类，实现servlet接口，重写抽象方法
③ 配置web.xml
④ 部署web项目
⑤ 启动测试
```



##### ② 编写普通类，实现servlet接口

> 重写抽象方法，关注service()方法

```java
import javax.servlet.*;
import java.io.IOException;

public class QuickServlet implements Servlet {
    /**
    * servlet创建时,执行init初始化化方法
    */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
    /**
     *提供对外访问服务的方法
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("QuickServlet执行了");
    }
    /**
     * 用于描述当前servlet的功能(信息)
     */
    @Override
    public String getServletInfo() {
        return null;
    }
    /**
     * servlet销毁时
     */
    @Override
    public void destroy() {

    }
}
```





##### ③ 配置web.xml

> 给servlet配置网络访问地址

```xml
<!--
    QuickServlet 交给tomcat进行实例化对象
    servlet-name:当前实例化的类名
    servlet-class：当前实例化的全限定类名
    -->
    <servlet>
        <servlet-name>QuickServlet</servlet-name>
        <servlet-class>cn.com.mryhl.a_quick.QuickServlet</servlet-class>
    </servlet>
    <!--
    给 QuickServlet绑定一个网络地址
    servlet-name：需要绑定servlet的类名
    url-pattern：浏览器访问的网络地址
    -->
    <servlet-mapping>
        <servlet-name>QuickServlet</servlet-name>
        <url-pattern>/QuickServlet</url-pattern>
    </servlet-mapping>
```

### 2 访问流程

![](https://img04.sogoucdn.com/app/a/100520146/05CF84FA725568C8743622FFBAD98421)



## 第三章 Servlet相关API

### 1 生命周期相关

#### 1.1 思想介绍

- 生命周期：指的是一个对象从生（创建）到死（销毁）的一个过程

```java
// 1.servlet创建时，执行init初始化方法
public void init(ServletConfig servletConfig);

// 2.用户访问时，执行service服务方法
public void service(ServletRequest servletRequest, ServletResponse servletResponse);

// 3.servlet销毁时，执行destroy销毁方法
public void destroy();
```

```markdown
* 创建
	默认情况下：用户第一次访问时，servlet创建，只创建一次
	指定创建时机，在服务器启动时，创建
		<load-on-startup></load-on-startup>
			正数：4~ ，因为tomcat内部也有一些servlet需要启动时创建
			负数：-1（默认值），表示用户第一次访问时，创建

* 运行（提供服务）
	用户访问该servlet，都会执行service方法，执行多次

* 销毁
	服务器关闭，servlet销毁
```





> 扩展：通过观察servlet的创建时机，这哥们是一个单例对象
>
> 注意：在servlet中不要使用成员变量存值，会造成线程安全问题



#### 1.2 代码演示



##### ① LifeServlet

```java
import javax.servlet.*;
import java.io.IOException;

public class LifeServlet implements Servlet {
    /**
     * 创建,在创建时执行此方法
     */

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("LifeServlet创建了");
    }

        @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 每次运行此方法
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("LifeServlet运行");
    }

    @Override
    public String getServletInfo() {
        return null;
    }
    /**
    * 销毁执行此方法
    */
    @Override
    public void destroy() {
        System.out.println("LifeServlet销毁");

    }
}
```



##### ② web.xml

```xml
<!--生命周期相关api-->
    <servlet>
        <servlet-name>LifeServlet</servlet-name>
        <servlet-class>cn.com.mryhl.b_api.LifeServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>LifeServlet</servlet-name>
        <url-pattern>/LifeServlet</url-pattern>
    </servlet-mapping>
```



### 2 拓展：ServletConfig接口

Tomcat在Servlet对象创建时，执行init()方法，并创建一个ServletConfig 配置对象

- 主要作用：读取web.xml配置文件Servlet中<init-param>信息，实现参数和代码的解耦

##### ① EncodeServlet

```java
import javax.servlet.*;
import java.io.IOException;
public class EncodeServlet implements Servlet {
    // 声明变量
    private ServletConfig servletConfig;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig= servletConfig;
    }
    // 提供获取servletConfig公共方法
    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }
    // 对外提供服务器
    @Override
    public void service(ServletRequest servletRequest, ServletResponse
            servletResponse) throws ServletException, IOException {
// 对中文进行编码
        String encode = getServletConfig().getInitParameter("encode");
// 向浏览器输出结果
        servletResponse.getWriter().write(encode);
    }
    @Override
    public String getServletInfo() {
        return null;
    }
    @Override
    public void destroy() {
    }
}
```



##### ② web.xml

```xml
<!--servletConfig的相关api-->
    <servlet>
        <servlet-name>EncodeServlet</servlet-name>
        <servlet-class>cn.com.mryhl.b_api.EncodeServlet</servlet-class>
        <init-param>
            <param-name>encode</param-name>
            <param-value>gbk</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>EncodeServlet</servlet-name>
        <url-pattern>/EncodeServlet</url-pattern>
    </servlet-mapping>
```

## 第四章 Servlet体系结构

### 1 GenericServlet

##### ① 编写普通类，继承GenericServlet

> 我们只需要重写，service()方法即可



```java
public class ServletDemo01 extends GenericServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("我重写init方法...");
    }
    // 对外提供服务的方法
    @Override
    public void service(ServletRequest servletRequest, ServletResponse
            servletResponse) throws ServletException, IOException {
        System.out.println(getServletConfig());
        System.out.println("ServletDemo1 extends GenericServlet");
        servletResponse.getWriter().write("ServletDemo1 extends GenericServlet");
    }
    @Override
    public void destroy() {
        System.out.println("我重写destroy方法...");
    }
}
```



##### ② 配置web.xml

```xml
<!--ServletDemo1 extends GenericServlet -->
    <servlet>
        <servlet-name>ServletDemo1</servlet-name>
        <servlet-class>cn.com.mryhl.c_inherit.ServletDemo01</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>ServletDemo1</servlet-name>
        <url-pattern>/ServletDemo1</url-pattern>
    </servlet-mapping>
```

2 HttpServlet【开发中使用】

##### ① 编写普通类，继承HttpServlet

> 重写 doGet()、doPost()

```java
public class ServletDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get方式请求");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       System.out.println("post方式请求");
    }
}
```



##### ② 配置web.xml

```xml
<!--ServletDemo2 extends HttpServlet-->
    <servlet>
        <servlet-name>ServletDemo2</servlet-name>
        <servlet-class>cn.com.mryhl.c_inherit.ServletDemo2</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>ServletDemo2</servlet-name>
        <url-pattern>/ServletDemo2</url-pattern>
    </servlet-mapping>
```



##### ③ 编写表单

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>demo2</title>

</head>
<body>
<form action="http://localhost:8080/day06-servlet-xml/ServletDemo2" method="get">
    <input type="submit" value="get">
</form>

<form action="http://localhost:8080/day06-servlet-xml/ServletDemo2" method="post">
    <input type="submit" value="post">
</form>
</body>
</html>
```



> 注意，在web阶段业务场景不考虑特别复杂，doGet和doPost功能是一样的....

### 3 经验值

> 状态码405，请求方法未找到....
>
> 我们在开发时，没有重写httpServlet对应的doXxx方法，父类就会做一个友情提示...

> 状态500，服务器内部错误



## 第五章 Servlet路径

### 1 url-pattern

作用：将一个请求网络地址和servlet类建立一个映射关系

> 一个servlet可以映射多个网络地址...

```java
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDemo3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletDemo3的get方法执行了。。。");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```



```xml
 <!-- url-pattern 标签-->
    <servlet>
        <servlet-name>ServletDemo3</servlet-name>
        <servlet-class>cn.com.mryhl.c_inherit.ServletDemo3</servlet-class>
    </servlet>

    <!--映射的第一个地址-->
    <servlet-mapping>
        <servlet-name>ServletDemo3</servlet-name>
        <url-pattern>/haha</url-pattern>
    </servlet-mapping>

    <!--映射的第二个地址-->
    <servlet-mapping>
        <servlet-name>ServletDemo3</servlet-name>
        <url-pattern>/hehe</url-pattern>
    </servlet-mapping>
```

### 2 相对/绝对路径

- 现阶段我们访问资源的方式越来越多，请求路径在编写时难免出现混淆

1. 浏览器的地址栏

2. a标签的href属性

3. form表单的action属性

4. js的loation.href属性

5. ajax请求地址

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>path</title>

</head>
<body>
<h3>当前页面的地址：http://localhost:8080/day06_servlet_xml_war_exploded/static/path.html</h3>
<hr>
<!--
    绝对路径
        完整格式：
            http://域名:端口/项目网络地址/资源网络地址
        （推荐）简化：
            /项目网络地址/资源网络地址
 -->
<a href="http://localhost:8080/day06_servlet_xml_war_exploded/static/path.html">绝对路径完整版</a><br>
<a href="/day06_servlet_xml_war_exploded/QuickServlet">绝对路径简化版</a> <br>

<!--
    现阶段别用
    相对路径（当前页面和目标页面的相对位置关系）
        ./ 当前目录下
        ../ 上级目录下

-->
<hr>
<a href="../QuickServlet">相对路径</a>
</body>
</html>
```







## 第六章 Servlet3.0

- 通过注解配置Servlet，简化web.xml配置Servlet复杂性，提高开发效率，几乎所有的框架都在使用注解

##### ① 创建web项目

![](https://img02.sogoucdn.com/app/a/100520146/914723876F7EE162A7E93A535DA055B8)

##### ② 编写普通类，继承HttpServlet

```java
public class QucikServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("QucikServlet...get....");
    }
}
```





##### ③ 配置注解

```java
// @WebServlet(name = "QucikServlet",urlPatterns ="/QucikServlet" )
// @WebServlet(urlPatterns ="/QucikServlet" )
// @WebServlet(value ="/QucikServlet" )
@WebServlet("/QuickServlet")
public class QuickServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
```



## 第七章 ServletContext

### 1. 概述

- web容器（tomcat）在启动时，它会为每个web项目承建一个对应的ServletContext对象
- 它代表：当前web项目


**主要作用**

1. 获取全局的配置参数
2. 获取资源在服务器的真实（磁盘）地址
3. 域对象（共享数据）
4. 获取文件MIME类型



**获取ServletContext对象**

```markdown
1. 通过request对象获得
		ServletContext sc = request.getServletContext();
		
2. 继承HttpServlet后，可以直接调用
		ServletContext sc = getServletContext();
```





### 2 域对象（共享数据）

- 在当前项目范围内，共享数据（多个servlet都可以获取）

> API

```markdown
1. 存储数据
		void setAttribute(String name,Object value)
2. 获取数据
		Object getAttribute(String name)
3. 删除数据
		void removeAttribute(String name)
```





##### ① OneServlet（存）

```java
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/OneServlet")
public class OneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取ServletContext
        ServletContext servletContext = getServletContext();
        // 向ServletContext存数据
        servletContext.setAttribute("user","Harley");
        System.out.println("向ServletContext里存入了一个数据");
    }
}
```

##### ② TwoServlet（取）

```java
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TwoServlet")
public class TwoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取ServletContext
        ServletContext servletContext = getServletContext();
        // 获取数据
        String user = (String) servletContext.getAttribute("user");
        System.out.println("获取到了:" + user);
    }
}
```





##### ③ 生命周期

```markdown
1. 创建
		项目成功加载后，创建
2. 销毁
		项目关闭后，销毁

3. 作用范围
		整个wab项目（与项目共存亡...）
```





### 3 获取资源在服务器的真实地址

> api

```java
String getRealPath(String path);
```

```java
package cn.com.mryhl.a_api;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PathServlet")
public class PathServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取ServletContext
        ServletContext servletContext = getServletContext();

        // 获取 8.jpg真实路径
        String picPath = servletContext.getRealPath("/download/8.jpg");
        System.out.println(picPath);
        // 获取 web.xml真实路径
        String webPath = servletContext.getRealPath("/WEB-INF/web.xml");
        System.out.println(webPath);
    }
}
```

### 4 获取全局的配置参数

- 读取web.xml配置文件中<context-param>标签信息，实现参数和代码的解耦（多个servlet都可以获取）api

```java
 String getInitParameter(String key);
```

```java
@WebServlet("/GlobalServlet")
public class GlobalServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取ServletContext
        ServletContext servletContext = getServletContext();

        // 获取全局配置参数
        String encode = servletContext.getInitParameter("encode");

        System.out.println("获取的字符集：" + encode);
    }

}
```

```xml
<!--全局参数-->
<context-param>
    <param-name>encode</param-name>
    <param-value>UTF-8</param-value>
</context-param>
```

### 5 获取文件MIME类型

- 在互联网通信过程中定义的一种文件数据类型
- 格式：`大类型/小类型` 例如：`text/html text/xml text/plain image/jpeg`

> api

```java
String getMimeType("1.jpg")
```

```java
@WebServlet("/MimeServlet")
public class MimeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 下载文件名
        String fileName = "8.txt";

        // 获取ServletContext
        ServletContext servletContext = getServletContext();


        // 获得该文件的mime类型
        String mimeType = servletContext.getMimeType(fileName);

        System.out.println(mimeType);
    }

}
```





### 7.6 扩展：统计网站的访问次数

> 统计当前网站访问人数

```java
package cn.com.mryhl.b_case;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/QQZoneServlet")
public class QQZoneServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

        // 获取上下文对象
        ServletContext servletContext = getServletContext();

        // 向域中存值
        servletContext.setAttribute("count", 0);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<h1>QQ空间...</h1>");

        // 从域中获取访问人数+1，并更新到域中
        ServletContext servletContext = getServletContext();
        Integer count = (Integer) servletContext.getAttribute("count");
        servletContext.setAttribute("count", ++count);

        response.getWriter().write("<div>您是第"+count+"位访问此QQ空间的友人...</div>");
    }

}
```

## 附录

> 下午讲完课，大家再去修改模板

### Servlet模板设置

```java
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")
@javax.servlet.annotation.WebServlet("/${Class_Name}")
public class ${Class_Name} extends javax.servlet.http.HttpServlet {
@Override
protected void doGet(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
this.doPost(request,response);
}
@Override
protected void doPost(javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
}
}
```

## 总结

```markdown
一 Servlet概述
	运行在服务器端的java程序
	一个类要想通过浏览器被访问到,那么这个类就必须直接或间接的实现Servlet接口
二 Servlet快速入门
	① 创建web项目
	② 创建普通的java类，实现servlet接口，重写抽象方法
	③ 配置web.xml
	④ 部署web项目
	⑤ 启动测试
三 Servlet相关API
	生命周期相关
		创建
			init
				servlet创建时,执行init初始化化方法
		运行（提供服务）
			service
		销毁
			destroy servlet销毁时
	ServletConfig接口
		加载web.xml配置文件信息，实现参数和代码的解耦
			<init-param>
四 Servlet体系结构
	Servlet
		GenericServlet
			HttpServlet
				doPost()
				doGet()
五 Servlet路径
	url-pattern
		Servlet映射多个url
	相对/绝对路径
		简单绝对路径
			1. 浏览器的地址栏
			2. a标签的href属性
			3. form表单的action属性
			4. js的loation.href属性
			5. ajax请求地址
			简化： /项目网络地址/资源网络地址
六 Servlet3.0
	@WebServlet("/网络访问地址")
七 ServletContext
	概述
		代表当前web项目对象
	主要作用
		共享数据（域对象）
			api
				1. 存储数据
					void setAttribute(String name,Object value)
				2. 获取数据
					Object getAttribute(String name)
				3. 删除数据
					void removeAttribute(String name)
			生命周期
				1. 创建
					项目成功加载后，创建
				2. 销毁
					项目关闭后，销毁
				3. 作用范围
					整个wab项目（与项目共存亡...）
		获取资源文件在服务器上真实路径
			String getRealPath(String path);
		获取全局的配置参数
			<context-param>
			String getInitParameter(String key);
		获取文件MIME类型
			互联网传输数据时，识别文件的类型
				`大类型/小类型`
	案例：统计网站的访问次数
```

