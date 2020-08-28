## 今日内容

+ web的三大组件
	+ servlet：控制器
	+ filter：过滤器【重点】
	+ listener：监听器【了解】	
+ 文件上传（理解思想，了解代码...）




## 第一章 Filter概述

**生活中的过滤器**

净水器、空气净化器、地铁安检、山大王

**web中的过滤器**

当用户访问服务器资源时，过滤器将请求拦截下来，完成一些通用的操作

**应用场景**

如：登录验证、统一编码处理、敏感字符过滤

## 第二章 Filter快速入门

> 目标资源：quick.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/28
  Time: 8:57  
--%>
<html>
    <head>
        <title>quick</title>
    </head>
    <body>
        <h3>quick.jsp  目标资源</h3>
        <%
            System.out.println("quick执行了");
        %>
    </body>
</html>

```

### ① xml版本

> 普通类，实现Filter接口

```java
public class QuickFilter implements Filter {
    @Override
    public void destroy() {
    }

    /**
     * 实现拦截的方法...
     *
     * @param req：请求
     * @param resp：响应
     * @param chain：过滤器链（放行）
     */

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println("filter执行了....");
        // 放行
        chain.doFilter(req, resp);
        System.out.println("对响应结果增强...");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}

```

> 配置web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">

    <!--filter快速入门-->
    <filter>
        <filter-name>QuickFilter</filter-name>
        <filter-class>cn.com.mryhl.a_quick.QuickFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>QuickFilter</filter-name>
        <url-pattern>/quick.jsp</url-pattern>
    </filter-mapping>
</web-app>
```



#### ② 注解版本

```java
// @WebFilter(filterName = "QuickFilter",urlPatterns ="/quick.jsp" )
// @WebFilter(urlPatterns ="/quick.jsp" )
// @WebFilter(value ="/quick.jsp" )
@WebFilter("/quick.jsp" )
public class QuickFilter implements Filter {
    
    // 内容参考xml代码
}
```

## 第三章 Filter工作原理

> Filter接口中有一个doFilter方法，当我们编写好Filter，并配置对哪个web资源进行拦截后，WEB服务器每次在调用web资源的service方法之前， 都会先调用一下filter的doFilter方法，因此，在该方法内编写代码可达到如下目的：
>  	调用目标资源之前，让一段代码执行。
>  	是否调用目标资源（即是否让用户访问web资源）。
>  	调用目标资源之后，让一段代码执行。
>  　web服务器在调用doFilter方法时，会传递一个filterChain对象进来，filterChain对象是filter接口中最重要的一个对象，它也提供了一个doFilter方法，开发人员可以根据需求决定是否调用此方法，调用该方法，则web服务器就会调用web资源的service方法，即web资源就会被访问，否则web资源不会被访问。

## 第四章 Filter使用细节

### 1 生命周期

> 生命周期：指的是一个对象从生（创建）到死（销毁）的一个过程
>

```java
// 创建时，执行init方法
public void init(FilterConfig config);

// 用户访问被拦截资源时，执行doFilter方法
public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain);


// 销毁时，执行destroy方法
public void destroy();
```

```markdown
* 创建
		服务器启动时创建filter对象，并优于servlet资源的创建

* 运行（过滤拦截）
		用户每次访问被拦截资源时
		
* 销毁
		服务器关闭后，销毁filter对象
```



> FilterConfig获取init-param的数据内容

 

```xml
<init-param>
    <param-name>encoding</param-name>
    <param-value>utf-8</param-value>
</init-param>
```

> LifeFilter.java

```java
public class LifeFilter implements Filter {
    // 定义变量
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 创建时，执行
        /*
        FilterConfig：过滤器配置对象
        加载一些配置信息...
        */
        System.out.println("LifeFilter创建了");
        this.filterConfig = filterConfig;
    }
    /**
     * 执行拦截
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LifeFilter拦截了...");
        // 获取xml传来的设置参数
        String encoding = filterConfig.getInitParameter("encoding");
        // 输出打印
        System.out.println(encoding);
        // 放行
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("销毁了...");

    }
}

```

```xml
    <!--filter生命周期-->
    <filter>
        <filter-name>LifeFilter</filter-name>
        <filter-class>cn.com.mryhl.b_detail.LifeFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>LifeFilter</filter-name>
        <url-pattern>/show.jsp</url-pattern>
    </filter-mapping>
```

> 注解版

```java
@WebFilter(value = "/show.jsp",
           initParams = {@WebInitParam(name = "encoding",value = "GBK")})
public class LifeFilter implements Filter {
    
}
```

### 2 拦截路径

​	在开发时，我们可以指定过滤器的拦截路径来定义拦截目标资源的范围

```markdown
1. 精准匹配【了解】
	配置一个指定（/show.jsp）的拦截资源

2. 目录匹配
	配置一个指定目录（/user/*）下的资源
		/user/UserInfo
		/user/UserOrder
		/user/xxxx
		
3. 后缀名匹配
	配置一个指定后缀名（*.html、*.jsp、*.jpg....）的资源
	
4. 匹配所有
	配置拦截所有（/*）的资源
```

> 注解版实施拦截

```java
// 目录匹配
// @WebFilter("/user/*")
// 后缀名匹配
// @WebFilter("*.html")
// 拦截所有
@WebFilter("/*")
public class UrlPatternFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("UrlPatternFilter拦截执行了.....");
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
```

### 3 拦截方式

​	在开发时，我们可以指定过滤器的拦截方式来处理不同的应用场景，比如：只拦截从浏览器直接发送过来的请求，或者拦截内部转发的请求

```markdown
1. 拦截外部请求（默认）
	用户通过浏览器发送请求时，进行拦截


2. 拦截内部转发
	资源a转发到资源b时，拦截
	
3. 过滤器可以同时配置多种拦截方式
	forward、request
```

> 注解配置

```java
/*
* 指定过滤器拦截方式
* */

// @WebFilter(value = "/BServlet", dispatcherTypes = {DispatcherType.FORWARD}) // 内部转发时，进行拦截
@WebFilter(value = "/BServlet", dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD}) // 外部请求和内部转发时，都会拦截
public class ModelFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("ModelFilter拦截了...");
        // 向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 放行
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }
}
```

> xml配置

```xml
    <!--过滤器的拦截方式-->
    <filter>
        <filter-name>ModelFilter</filter-name>
        <filter-class>cn.com.mryhl.b_detail.ModelFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>ModelFilter</filter-name>
        <url-pattern>/BServlet</url-pattern>
        <dispatcher>REQUEST</dispatcher>
        <dispatcher>FORWARD</dispatcher>
    </filter-mapping>
```

> AServlet

```java
@WebServlet("/AServlet")
public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 打印
        System.out.println("AServlet执行了.....");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("AServlet执行了.....");

        // 转发到B
        request.getRequestDispatcher("/BServlet").forward(request,response);

    }
}
```

> BServlet

```java
@WebServlet("/BServlet")
public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("BServlet执行....");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("BServlet执行....");
    }
}
```



### 4 过滤器链

​	在一次请求中,若我们请求匹配到了多个filter,通过请求就相当于把这些filter串起来了，形成了过滤器链 

```markdown
* 执行顺序（先进后出）
        1.用户发送请求
        2.FilterA拦截请求
        3.FilterB拦截请求
        4.show.jsp 执行了
        5.FilterB响应增强
        6.FilterA响应增强
        7.服务器响应结果
        
* 拦截先后问题
	xml
		<filter-mapping> 谁先声明，谁先拦截
	注解
		根据类名的大小进行先后的排序，值小的先执行...
			FilterA和FilterB进行对比，所以A先执行
```

> FilterA拦截请求...
> FilterB拦截执行了...
> show.jsp 执行了
> FilterB增强了...
> FilterA增强了....

```java
@WebFilter("/show.jsp")
public class FilterA implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException,  IOException {

        // 向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("FilterA拦截请求...");
        // 放行
        filterChain.doFilter(request, response);
        System.out.println("FilterA增强了....");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
```

```java
@WebFilter("/show.jsp")
public class FilterB implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        // 向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("FilterB拦截执行了...");
        // 放行
        filterChain.doFilter(request, response);
        System.out.println("FilterB增强了...");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
```

## 第五章 Filter案例【大作业】

### 1 用户评论留言

**需求**

用户访问某论坛网站，可以对文章比赛等内容进行留言

#### 代码实现

#### ① bbs.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/28
  Time: 10:28  
--%>
<html>
    <head>
        <title>bbs</title>
    </head>
    <body>
        <h3>弹幕网站</h3>
        <form action="${pageContext.request.contextPath}/WordServlet" method="post">
            <textarea name="word" rows="5" cols="20">

            </textarea> <br>
            <input type="submit" value="发表">

        </form>
    </body>
</html>

```

#### ② WordServlet

```java
@WebServlet("/WordServlet")
public class WordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收请求
        String word = request.getParameter("word");
        // 2. 响应弹幕
        // 设置编码
        response.setContentType("text/html;charset=utf-8");
        // 输出
        response.getWriter().write("你发送的弹幕是:"+word);
    }
}
```

### 2 统一网站编码

**需求**

tomcat8.5版本中已经将get请求的中文乱码解决了,但是post请求还存在中文乱码

浏览器发出的任何请求，通过过滤器统一处理中文乱码 



#### 代码实现

> EncodingFilter

```java
/**
 * 统一编码
 */


@WebFilter("/*")
public class EncodingFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        // 向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 如果请求方式为post,指定request解码方式,解决乱码问题
        if (request.getMethod().equalsIgnoreCase("post")) {
            // 编码过滤
            request.setCharacterEncoding("utf-8");
        }


        // 放行
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
```

> 课下作业，可以通过FilterConfig对象，将字符串抽取加载，完成解耦合的操作。。

### 3 非法字符拦截

**需求**

当用户发出非法言论的时候，提示用户言论非法警告信息

#### 代码实现

#### ① word.properties

> ```properties
> keyword=大爷,二大爷,傻子,二逼,你妹的
> ```

#### ② WordFilter

```java
/**
 * 敏感字符拦截
 */

@WebFilter("/WordServlet")
public class WordFilter implements Filter {
    private String[] wordArray;
    @Override
    public void destroy() {
    }

    /**
     * 拦截非法词汇
     *
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        // 向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1. 获取请求参数
        String word = request.getParameter("word");

        // 2.校验词库
        for (String illegqlity : wordArray) {
            if (word.contains(illegqlity)){
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("言论非法...");
                return;
            }
        }

        // 放行
        filterChain.doFilter(request, response);
    }
    /**
     * 加载非法词库
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        // 1.专门加载src目录下的properties配置文件，sun公司提供了一个工具类（ResourceBundle）
        ResourceBundle word = ResourceBundle.getBundle("word");
        // 2.获取关键字信息
        String keyword = word.getString("keyword");

        // 3.转为数组
        wordArray = keyword.split(",");


        System.out.println("非法词库加载成功：" + Arrays.toString(wordArray));
    }

}
```



### 4 非法字符过滤【抄一遍..】

**需求**

当用户发出非法言论的时候，在servlet中输出的时候:用"*" 替代

你是个笨蛋 --> 你是个**

#### 技术分析

> 需要对此方法进行增强

```java
request.getParameter(String name);
```

> 对方法增强的方式，目前学习了几种？
>
> 1）子类继承：如果被增强的类是一个接口，无法实现（开发者很少使用...）
>
> 2）☆代理模式：一般使用动态代理，这是使用最普遍的方案（后面会讲..）
>
> 3）装饰器模式： 一般在io流中大量使用，今天们就用这种方案



**使用装饰器模式的要求：**

```markdown
1. 装饰类和被装饰类，需要实现同一个接口（或者继承同一个类）

2. 装饰类需要有被装饰类的，对象引用

3. 装饰类需要重写增强的方法，完成具体的功能

4. 装饰类对不需要增强的方法，调用原有的功能
```

>  包装类的复习

```java
public class TestDecorator {

    public static void main(String[] args) {

        // 多态（面向接口）
        Phone phone = new Lvjing(new Meiyan(new Huawei()));
        phone.take();
        System.out.println("-------------");
        phone.call();
    }
}

// Phone接口
interface Phone {

    // 拍照
    void take();

    // 打电话
    void call();
}

// 华为厂商实现类
class Huawei implements Phone {

    @Override
    public void take() {
        System.out.println("3200W高清摄像");
    }

    @Override
    public void call() {
        System.out.println("5G视频通话");
    }
}

// 抽取PhoneWrapper包装类

class PhoneWrapper implements Phone {

    private Phone phone;

    public PhoneWrapper(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void take() {
        this.phone.take();
    }

    @Override
    public void call() {
        this.phone.take();
    }
}

// 美颜增强
class Meiyan extends PhoneWrapper {

    public Meiyan(Phone phone) {
        super(phone);
    }


    // 对拍照增强
    @Override
    public void take() {
        super.take();
        System.out.println("磨皮、瘦脸、大眼、美白");
    }
}

// 滤镜增强
class Lvjing implements Phone {

    private Phone phone;

    public Lvjing(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void take() {
        this.phone.take();
        System.out.println("对背景虚化...");
    }

    @Override
    public void call() {
        this.phone.call();
    }
}
```

### 代码实现

#### ① MyRequest

```java
/*
    对 getParameter方法进行增强
 */

public class MyRequest extends HttpServletRequestWrapper {

    // 获取非法词库
    private String[] wordArray;

    public MyRequest(HttpServletRequest request, String[] wordArray) {
        super(request);
        this.wordArray = wordArray;

    }

    // 重写getProameter方法

    @Override
    public String getParameter(String name) {
        // 获取用户输入的内容
        String parameter = super.getParameter(name);
        // 遍历和判断的
        for (String s : wordArray) {
            // 替换
            if (parameter.contains(s)) {
                parameter = parameter.replaceAll(s,"***");
            }
        }

        return parameter;
    }
}
```

#### ② WordProFilter

```java
@WebFilter("/WordServlet")
public class WordProFilter implements Filter {
    private String[] wordArray;
    @Override
    public void destroy() {
    }

    /**
     * 拦截非法词汇
     *
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        // 向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 对request对象进行装饰


        HttpServletRequest requestDecorator = new MyRequest(request, wordArray);
        // 放行
        filterChain.doFilter(requestDecorator, response);
    }
    /**
     * 加载非法词库
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        // 1.专门加载src目录下的properties配置文件，sun公司提供了一个工具类（ResourceBundle）
        ResourceBundle word = ResourceBundle.getBundle("word");
        // 2.获取关键字信息
        String keyword = word.getString("keyword");

        // 3.转为数组
        wordArray = keyword.split(",");


        System.out.println("非法词库加载成功：" + Arrays.toString(wordArray));
    }

}
```

## 第六章 Listener

### 1 概述

**生活中的监听器**

我们很多商场有摄像头，监视着客户的一举一动。如果客户有违法行为，商场可以采取相应的措施。

**javaweb中的监听器**

在我们的java程序中，有时也需要监视某些事情，一旦被监视的对象发生相应的变化，我们应该采取相应的操作。

监听web三大域对象：HttpServletRequest、HttpSession、ServletContext  

**场景**

历史访问次数、统计在线人数、系统启动时初始化配置信息



### 2 快速入门

​	监听器在web开发中使用的比较少,见的机会就更少了,今天我们使用**ServletContextListenner**来带领大家学习下监听器,因为这个监听器是监听器中使用率最高的一个,且监听器的使用方式都差不多。

> 我们使用这个监听器可以在项目启动和销毁的时候做一些事情,例如,在项目启动的时候加载配置文件。
>



#### ① xml版本

> 普通Java类，实现ServletContextListenner接口
>
> 重写：监听ServletContext创建、监听ServletContext销毁

```java
public class MyListener implements ServletContextListener {
    /*
        监听：ServletContext创建
            servletContextEvent：监听器事件对象..可以获取servletContext域对象
     */

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener创建了....");
        ServletContext servletContext = servletContextEvent.getServletContext();
    }

    /*
        监听：ServletContext销毁
     */

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener销毁了...");
    }
}

```

```xml
<!--配置listener监听器-->
    <listener>
        <listener-class>cn.com.mryhl.a_listener.MyListener</listener-class>
    </listener>
```

#### ② 注解版本

```java
@WebListener
public class MyListener implements ServletContextListener {
    // 代码，省略
}
```

### 3 案例:统计在线人数

**需求**

有用户使用网站,在线人数就+1;用户退出网站,在线人数就-1

#### 代码实现

#### ① InintCountPersonListener

```java
/**
 * 初始化在线人数
 * @author mryhl
 */
@WebListener
public class InintCountPersonListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // 获取servletContext
        ServletContext servletContext = servletContextEvent.getServletContext();
        // 初始化人数0
        servletContext.setAttribute("countPerson",0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
```

#### ② ChangeCountPersonListener

```java
@WebListener
public class ChangeCountPersonListener implements HttpSessionListener {

    /**
     * 会话建立,人数+1
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        // 获取最大的域对象
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        // 取出原来的人数
        Integer oldCountPerson = (Integer) servletContext.getAttribute("countPerson");
        // 加一覆盖
        servletContext.setAttribute("countPerson", oldCountPerson + 1);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        // 获取最大的域对象
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        // 取出原来的人数
        Integer oldCountPerson = (Integer) servletContext.getAttribute("countPerson");
        // 减一覆盖
        servletContext.setAttribute("countPerson", oldCountPerson - 1);
    }
}
```

##### ③ index.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/28
  Time: 15:11  
--%>
<html>
  <head>
    <title>监听练习</title>
  </head>
  <body>
      <h3>当前在线人数：${applicationScope.countPerson}</h3>

      <a href="${pageContext.request.contextPath}/LogoutServlet">退出</a>
  </body>
</html>
```



#### ④ LogoutServlet

```java
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前session，自杀
        request.getSession().invalidate();
    }
}
```

## 第七章 web核心的相关知识

```markdown
1. tomcat基本使用

2. http协议
	请求
		行、头、体
	响应
		行、头、体
		
3. servlet


4. servletContext
	应用上下文
		域对象
		获取文件MIME类型
		获取资源真实路径
		
5. request 和 response
		转发和重定向
		
6. cookie 和 session	
		会话技术
		
7. jsp el jstl
		展示动态资源
		
8. MVC架构设计思想

9. 三层架构【使用】

10. filter 和 listener
```



## 第八章 文件上传【了解底层代码】

###  1 浏览器将文件转为文本

```markdown
1. 表单提交方式为 method="post"
	<form method="post"></form>
	
2. 表单项需要提供 type="file"
	<input type="file" name="pic">
	
3. 表单的提交类型 enctype="multipart/form-data"
	<form enctype="multipart/form-data"> 
```

### 2 服务器将图片文本转为io流

> 通过：request.getInputStream()，此方法获取的整个请求体的数据，需要自己操作获取文件的io流，效率非常低

```markdown
1. 使用apache的工具类：commons-fileupload（10行代码），今天讲


2. servlet3.0 提供注解，在web综合去讲...


3. springMVC实现文件上传解析（底层：commons-fileupload） 1行代码 
```

### 3 代码实现

#### ① 导入jar包

> 1	commons-fileupload-1.3.3.jar	
> 2	commons-io-2.6.jar	
> 3	hutool-all-5.2.3.jar	



#### ② 编写代码【了解】

**步骤分析**

```markdown
1. 创建文件解析器工厂

2. 创建文件解析器对象

3. 通过解析器对象，解析request请求（请求头和请求体），返回上传项集合

4. 遍历上传项集合
	普通文本
		值
	文件
		文件名
		io流
```

> 前台页面

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  User: mr_yhl
  Date: 2020/8/28
  Time: 20:30  
--%>
<html>
  <head>
    <title>文件上传</title>
  </head>
  <body>
    <h3>个人信息</h3>

    <form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
      昵称：<input type="text" name="nickname"> <br>
      头像：<input type="file" name="pic"> <br>
      <input type="submit" value="提交">
    </form>
  </body>
</html>
```



```java
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        // 1. 创建文件解析器（磁盘文件项）工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 2. 创建文件解析器对象
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

        try {
            // 3. 通过解析器对象，解析request请求（请求头和请求体），返回上传项集合
            List<FileItem> fileItemList = servletFileUpload.parseRequest(request);

            //  System.out.println(fileItemList.size());
            // 4. 遍历上传项集合
            for (FileItem fileItem : fileItemList) {
                if (fileItem.isFormField()) { // 普通文本项
                    String name = fileItem.getFieldName(); // nickname
                    String value = fileItem.getString(); // lucy
                    System.out.println(name + "=" + value);
                } else { // 上传文件项
                    String filename = fileItem.getName(); // 获取原文件名

                    // 新文件名
                    String uuid = IdUtil.simpleUUID();

                    // 获取扩展名
                    String ext = FileUtil.extName(filename);

                    // 组成完整文件名
                    filename = uuid + "." + ext;

                    InputStream in = fileItem.getInputStream(); // 获取文件的io流
                    System.out.println("文件名：" + filename);
                    // System.out.println(in);
                    // 指定文件的保存路径
                    FileOutputStream out = new FileOutputStream(new File("d:/" + filename));

                    // 拷贝
                    IoUtil.copy(in, out);

                    // 释放资源
                    out.close();
                    in.close();
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        response.getWriter().write("上传成功...");

    }

}
```

## 总结

```mark
filter
	概述
		作用
			拦截用户请求
		应用场景
			如：登录验证、统一编码处理、敏感字符过滤...
	快速入门
		1. 定义一个类实现Filter接口
		2. 重写filter方法
			doFilter
		3. 配置
			web.xml
			注解
	工作原理
		用户发送请求
			执行Filter拦截请求
				放行（执行放行后的资源）
					执行Filter拦截响应
	细节
		生命周期
			何时创建
				在服务器启动时，创建fitler对象，执行init方法，只执行一次
			何时销毁
				服务器正常关闭时，销毁filter对象，执行destroy方法，只执行一次
			创建优先级
				ServletContext
					Filter
						Servlet
		拦截路径
			精准匹配
				/show.jsp
			目录匹配
				/user/*
			后缀匹配
				*.html
			拦截所有
				/*
		拦截方式
			REQUEST
				客户端直接访问资源时，执行Filter
			FORWARD
				服务器内部资源跳转时，执行Filter
		过滤器链
			拦截顺序
				先进后出
			执行先后
				web.xml
					<filter-mapping></filter-mapping>谁先声明，谁先执行
				注解
					按照自定义过滤器类名的字符串比较规则，值小的先执行
	filter案例
listener
	概述
		监听web三大域对象：Request、Session、ServletContext（创建和销毁）
	作用
		历史访问次数
		统计在线人数
		系统启动时初始化配置信息
	快速入门
		1. 定义一个类，实现ServletContextListener接口
		2. 重写接口中的方法
		3. 配置
			web.xml
				别人写好的监听器，只能通过配置文件进行配置
			注解
	案例:统计在线人数
		1）初始化在线人数
		2）创建会话时人数+1，关闭会话时人数-1
		3）servlet实现用户退出
文件上传
	1）浏览器如何将文件转为文本发送
		表单三要素
	2）服务器如何将文件文本转为io流
		fileupload工具类
```

