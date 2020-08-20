<center>Tomcat&Http</center>

## 今日内容

+ web知识科普

+ tomcat软件安装和使用【重点】

+ http协议介绍（感知）



## 第一章  Web知识概述

> JavaWeb：我们编写的代码（java和前端）发布到服务器，用户就可以通过互联网进行访问...

### 1.软件架构

- 网络中有很多的计算机，它们直接的信息交流，我们称之为：交互
- 在互联网交互的过程的有两个非常典型的交互方式——B/S  交互模型（架构）和 C/S  交互模型（架构）

**C/S架构**

> Client/Server 客户端/服务器
>
> 访问服务器资源必须安装客户端软件
>
> 例如: QQ，绝地求生，LOL

**B/S架构**

> Browser/Server 浏览器/服务器
>
> 访问服务器资源不需要专门安装客户端软件,而是直接通过浏览器访问服务器资源.
>
> 例如: 天猫、京东、知乎网站

### 2.Web服务器作用

- 开发者通过web服务器可以把<span style="color:red">本地资源</span>发布到互联网

- 用户就可以通过浏览器访问这些资源
### 3.资源的分类

资源：计算机中数据文件

**静态资源**

- 对于同一个页面,不同用户看到的内容是一样的。
  - 例如：体育新闻、网站门户等，常见后缀：`*.html、*.js、*.css`

**动态资源**

- 用对于同一个页面,不同用户看到的内容可能不一样。
  - 例如：购物车、我的订单等，常见后缀：`*.jsp、*.aspx、*.php`


### 4.常见的Web服务器
```markdown
* Tomcat: Apache组织开源免费的web服务器

-----------------------------------------------

* Jetty:Apache组织开源免费的小型web服务器

* JBoss: RedHat红帽公司的开源免费的web服务器

* Glass Fish:Sun公司开源免费的web服务器

* WebLogic: Oracle公司收费的web服务器

* WebSphere:IBM公司收费的web服务器
```

**JavaEE规范**

​	在Java中所有的服务器厂商都要实现一组Oracle公司规定的接口，这些接口是称为JavaEE规范。不同厂商的JavaWeb服务器都实现了这些接口，在JavaEE中一共有13种规范。实现的规范越多，功能越强。

> 目前仅使用二大规范：servlet/jsp



## 第二章 Tomcat服务器【重点】

### 1.Tomcat使用

#### 1.1下载

Tomcat 官网下载地址：https://tomcat.apache.org/download-80.cgi

#### 1.2 安装

绿色免安装版，解压即用（注意：不要有中文路径）

#### 1.3 启动和关闭
> 双击starup.bat启动
> 关闭补充：在tomcat的黑窗口内，按住 ctrl + c （也能正常关闭）
> 访问测试：localhost:8080

#### 1.4启动报错问题

##### ① Java环境变量

tomcat是由java编写的，如果没有配置环境变量

现象: 黑窗口一闪而过(不到半秒)

> 解决:
> 	配置好Java环境变量

##### ② 8080端口被占用

现象：启动时报错

**暴力：找到占用的端口号的对应进程，杀死进程**

cmd命令：`netstat -ano | findstr "8080"`

> 先通过dos查找占用8080端口的pid
> 在进入到任务管理器
> 此时你就可以启动tomcat了

**温柔：修改Tomcat端口号**

> 进入Tomcat安装目录/conf/server.xml 文件修改
> 启动测试
> 注意：端口的修改范围：1024~65535之间



#### 1.5 发布项目三种方式

##### ① webapps部署(最简单)

直接放置在 webapps 目录下

> ​	这种方式虽然简单，但是代码修改完毕，还要重新复制到webapps目录下，较为繁琐，通常项目开发完毕，使用此方案部署环境..



##### ② server.xml部署（了解）

在tomcat/conf/server.xml中找到<Host>标签，添加<Context/>标签

> 我们修改的是server.xml，这哥们是tomcat的核心配置文件，修改完毕不会立即生效，如果修改错误，tomcat将启动失败...

#####  ③  独立xml部署(开发时)

在tomcat/conf/Catalina/localhost 目录下创建一个xml文件，添加<Context/>标签

> 我们在开发时，不需要手动创建xml文件，idea工具会帮我们自动完成...
>
> 我们只关注代码的编写....

### 2.Web项目工程结构

```markdown
1. 前端项目
        |-- myapp(项目名称)
            |-- css 目录
            |-- js  目录
            |-- static目录
            |-- img 目录
            |-- index.html
            
2. web项目
		|-- myapp（项目名称）
			|-- 静态资源目录
			|-- WEB-INF 目录(动态资源目录，受保护的目录，外界无法访问...)
				|-- classes 目录（java代码）
				|-- lib 目录（依赖jar包）
				|-- web.xml (当前项目的核心配置文件，如果是servlet3.0版本可以省略)
			|-- index.html（index.jsp）
```

> 开发时我们不需要手动构建目录结构，idea会帮我们完成，我们看懂即可...


### 3.Idea中使用Tomcat【掌握】

#### 3.1 配置Tomcat

> 显示idea的菜单栏
> idea关联tomcat
> 验证是否关联成功


#### 3.2 创建Web项目

> 我们今天就开始创建javaEE的模块
>  给项目起一个名字（不要有中文）
>   有的时候idea创建web模块，可能出现一个bug，只有src
>    删除，重建就可以解决了...
>    开发工程结构，需要删除index.jsp 重新创建 index.html

#### 3.3发布Web项目

> 设置项目的网络地址
> tomcat控制面板
> 启动
> 浏览器会自动打开当前项目的默认访问页
> idea控制台




#### 3.4 页面资源热更新

> 作用：前端页面修改完毕，可以实现立即更新；俗称热部署
>
> 注意：每创建一个web工程，都需要手动指定...


#### 3.5 经验值

> 我们刚才提到一个概念，WEB-INF是一个受保护的目录外界无法访问



## 第三章 Http协议

### 1.Http协议概述

> 今天会对请求和响应格式的数据做一个介绍，后面的6天通过代码进行实操....

超文本传输协议（Hyper Text Transfer Protocol)是互联网上应用最为广泛的一种网络协议。

传输协议：在客户端和服务器端通信时，规范了<span style="color:red">传输数据的格式 </span>

> https://http2.akamai.com/demo

### chrome(谷歌)——按键（fn）f12
### 2.Http请求

####  HTTP请求消息格式

#### ① 请求行

```markdown
* 格式
		请求方式 请求路径 协议/版本号
		
* 例如
		POST /day05-tomcat/static/login.html HTTP/1.1（从请求体获得数据）
		GET  /day05-tomcat/static/login.html?username=jack&password=123 HTTP/1.1
		（从请求行获得数据）
		
* 请求方式区别
	get
		1）参数在请求路径上进行拼接
		2）大小有限制
		3）不太安全		
	post
		1）参数不在请求路径上拼接（在请求体中）
		2）大小没有限制
		3）相对安全
		
```
#### ② 请求头

```markdown
* 格式
		请求头名称：请求头的值
* 例如        
		Host: loalhost:8080
```

**常见请求头**：Accept开头的，都是浏览器告诉服务器的一些暗语

```markdown
		------------------------------------------（了解）
1. Host: localhost:8080         访问服务器的地址（域名+端口）
		

2. Connection: keep-alive       长连接（http1.1协议）
		

3. Cache-Control: max-age=0 	设置缓存数据的存活时间，单位秒
		
		
4. Upgrade-Insecure-Requests:1   客户端支持https加密协议  
			
		---------------------------------------（重点）

5. Referer: http://baidu.com    上一次请求的地址（请求来源）
		百度推广...		
		
6. User-Agent:        客户端系统和浏览器版本
		User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) Chrome/63.0 Safari/537.36
		浏览器兼容性
```



#### ③ 请求体（正文）

> post提交才会有请求体....

```markdown
* 格式
		参数名=参数值&参数名=参数值
* 例如
		username=jack&password=123
```
### 3.Http响应
#### HTTP响应消息格式

#### ① 响应行

```markdown
* 格式
		协议/版本号 状态码 
* 例如
		HTTP/1.1   200

* 常见状态码
	--------------- 成功
	200 ：表示成功
	
	302 ：表示重定向
	
	304 ：表示缓存
	--------------- 失败
	404：请求未找到
	
	405：请求方法未找到（后天讲）
	
	500：服务器内部错误，你的java代码写错了（后天讲）
```



#### ② 响应头

```markdown
* 格式
		响应头名称：响应头的值
* 例如
		Last-Modified: Thu, 20 Aug 2020 07:45:20 GMT
```

**常见响应头**：Content开头都是服务器告诉客户端一些暗语

```markdown
1. Location：通常与状态码302一起使用，实现重定向操作
		Location:www.jd.com
		
2. Content-Type：服务器告诉客户端，返回响应体的数据类型和编码方式【重点】
		http协议传输的内容都是字符串（文本）
		content-type: application/javascript
		
3. Content-Disposition：服务器告诉客户端，以什么样方式打开响应体
		* in-line（默认）：浏览器直接打开相应内容，展示给用户
		* attachment;filename=文件名：浏览器以附件的方式保存文件 【文件下载】
		
4. Refresh：：在指定间隔时间后，跳转到某个页面【了解】
	
5. Last-Modified：通常与状态码304一起使用，实现缓存机制
		
```


#### ③ 响应体

```markdown
* 服务器返回的数据，由浏览器解析后展示给用户
```
## 第四章 用户登录案例【感知】

**login.html**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>login</title>

</head>
<body>
<h3>用户登录</h3>
<form action="http://localhost:8080/day05-tomcat/UserServlet" method="post">
    用户名：<input type="text" name="username"> <br>
    密&emsp;码：<input type="password" name="password"> <br>
    <input type="submit" value="登录">
</form>
</body>
</html>
```

**UserServlet**

```java
package com.itheima.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "/UserServlet")
public class UserServlet implements Servlet {// 实现此接口，才能被外界访问

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    // 对外提供服务的方法
    /*
        servletRequest：请求 （获取）
        servletResponse：响应（返回）
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // System.out.println("hello,servlet");
        // request用于接收请求
        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");

        // response用于返回响应
        servletResponse.getWriter().print(username + "   " + password);

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
##　总结

```markdown
web知识概述
	架构分类
		C/S
			客户端专门安装软件
		B/S
			浏览器作为客户端
	web服务器作用
		提供网上信息浏览服务
	资源分类
		静态
			.html .css .js .jpg
		动态
			.jsp
				servlet
	常见服务器
		Tomcat     最常见
		Jetty
		JBoss
		Glass
		WebLogic
		WebSphere
tomcat服务器
	下载
		apache-tomcat-8.5.31-windows-x64.zip
	安装
		解压缩即可
	目录结构
		bin  存放了可执行文件(启动关闭)
		conf  存放配置文件
		lib  存放依赖的jar包
		logs   日志
		temp  临时文件
		webapps  存放web项目,此目录下浏览器可以访问....
		work   运行时编译目录
	启停
		startup.bat
		shutdown.bat
	启动有问题
		Java环境变量
		端口占用
	tomcat发布项目方式
		webapps目录
			热部署
		conf/server.xml
			不推荐
		conf/catalina/localhost/xxx.xml
			热部署
	web项目目录结构
		WEB-INF
			classes
			lib  依赖
			web.xml
				web3.0之后可选
		静态资源
		index.html或index.jsp
	idea中使用tomcat
		配置tomcat
		部署项目
		启动
			重启tomcat
			重新部署项目
http协议
	概述
		在客户端和服务器端通信时，规范了传输数据的格式
	构成
		请求报文
			行
				请求方式
					get
					post
				URL
				协议
			头
				请求的描述信息
					Referer  上一次请求地址
					User-Agent  客户端浏览器版本
			体
				数据内容
					get方式没有，post才有
		响应报文
			行
				协议
				状态码
					200   正常
					302  重定向
					304  缓存
					404  未找到文件
					405  请求方法未找到
					500  服务器内部错误
			头
				响应的描述信息
					location  通常与状态码302一起使用，实现重定向操作
					Content-Type  服务器告诉客户端，返回响应体的数据类型和编码方式【重点】
					Content-Disposition  服务器告诉客户端，以什么样方式打开响应体
					refresh  在指定间隔时间后，跳转到某个页面【了解】
					Last-Modified  通常与状态码304一起使用，实现缓存机制
			体
				数据内容
```

