## 今日内容

+ 会话介绍
+ cookie（客户端）
  - addCookie
  - getCookies
+ jsp初识
+ session（服务器）
  - setAttribute
  - getAttribute
+ 域对象

## 第一章 会话概述

### 1.什么是会话？

在日常生活中,从拨通电话到挂断电话之间的一连串的你问我答的过程就是一个会话。

B/S架构中:从浏览器第一次给服务器发送请求时建立会话;直到有一方断开,会话结束。

### 2 会话技术

**问题：**Http是一个无状态协议，同一个会话的连续两个请求相互独立，彼此并不了解

**作用**：用于<span style="color:red">存储</span>浏览器与服务器在请求和响应过程中产生的<span style="color:red">数据</span>

客户端会话技术：cookie

服务器端会话技术：session

## 第二章 Cookie

### 1.概述

Cookie作用：在一次会话的多次请求之间共享数据，将数据**保存到客户端（浏览器）**

> 应用场景：JD购物车

### 2 快速入门

**技术分析**

```markdown
1. SetCookie
	// 1.创建cookie
		Cookie cookie = new Cookie(String name,String value);
	// 2.写入到浏览器
		response.addCookie(cookie);

2. GetCookie
	// 1.从浏览器中获取cookie
		Cookie[] cookies = request.getCookies();
	// 2.遍历输出（非空判断....）
```





> SetCookie

```java
@WebServlet("/SetCookie")
public class SetCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建cookie
        Cookie cookie = new Cookie("product", "honorV30pro");
        // 写入到浏览器
        response.addCookie(cookie);
    }
}
```





> GetCookie

```java
@WebServlet("/GetCookie")
public class GetCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从浏览器中获取cookie
        Cookie[] cookies = request.getCookies();
        // 遍历cookie
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println(name + "=" + value);
            }
        }

    }
}
```







### 3.工作原理

>  基于HTTP协议：请求头cookie 和 响应头 set-cookie

### 4.Cookie细节

#### 4.1 服务器发送多个Cookie？

> 同一个会话域名（最多50个）

```markdown
* 答案是可以的
	// 1.创建二个cookie对象
		Cookie cookie1 = new Cookie("name","jack");
		Cookie cookie2 = new Cookie("age","18");
	// 2.response添加二个，响应
		response.addCookie(cookie1);
		response.addCookie(cookie2);
```

```java
@WebServlet("/ResponseMultipart")
public class ResponseMultipart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建cookie
        Cookie cookie1 = new Cookie("product", "honorV30pro");
        Cookie cookie2 = new Cookie("price", "2999");
        // 写入到浏览器
        response.addCookie(cookie1);
        response.addCookie(cookie2);

    }
}
```

#### 4.2 Cookie在浏览器保存时间？

```markdown
* 默认的情况下，会话（浏览器）关闭，cookie自动销毁

* 我们可以设置它的存活时间
		cookie.setMaxAge(int seconds);
			正数：可以设置浏览器的cookie持久化时间，保存到硬盘，到期后自动销毁
			负数：默认值（-1），会话（浏览器）关闭，cookie自动销毁
			零：立即删除当前cookie
```



```java
@WebServlet("/CookieMaxAge")
public class CookieMaxAge extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     *cookie.setMaxAge(int seconds);
     * 正数：可以设置浏览器的cookie持久化时间，保存到硬盘，到期后自动销毁
     * 负数：默认值（-1），会话（浏览器）关闭，cookie自动销毁
     * 零：立即删除当前cookie
     *
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建cookie
        Cookie cookie = new Cookie("name", "Haley");
        // 设置保存时间
        cookie.setMaxAge(10);
        //响应到浏览器
        response.addCookie(cookie);
    }
}
```

#### 4.3 Cookie是否可以存储中文？

```markdown
* 在tomcat8版本之前，不支持中文
		URLEncode 编码
		URLDecode 解码


* 在tomcat8版本之后，支持中文
		Rfc6265规范中，不支持特殊符号（空格、分号、逗号....）
```



> CookieCN

```java
@WebServlet("/CookieCN")
public class CookieCN extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        String value = "荣耀v30pro";
        value = URLEncoder.encode(value,"utf-8");

        // 创建 cookie
        Cookie aaa = new Cookie("aaa", value);
        response.addCookie(aaa);

    }
}
```



> GetCookie

```java
@WebServlet("/GetCookieCN")
public class GetCookieCN extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.从浏览器中获取cookie
        Cookie[] cookies = request.getCookies();
        // 2.遍历输出
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                // 解码
                value = URLDecoder.decode(value, "utf-8");
                System.out.println(name + ":" + value);
            }
        }
    }
}
```

#### 4.4 Cookie共享数据的范围？

**① 在一个tomca服务器中，部署了多个web项目，那么这些web项目的Cookie能否共享？**

```markdown
* 默认情况下不行，因为我们在设置cookie时有一个默认携带路径（当前servlet的父路径）
		设置：http://localhost:8080/day08-cookie/SetCookie
		获取：http://localhost:8080/day08-cookie/xxxxxx

* 可以手动指定cookie的携带路径
	刚才案例的默认值：
		cookie.setPath("/day08-cookie"); 相当于：http://localhost:8080/day08-cookie
	如果你想让二个项目共享cookie：
		cookie.setPath("/"); 相当于：http://localhost:8080/
			http://localhost:8080/day08-cookie
			http://localhost:8080/day07-response
			
* cookie覆盖要求：路径+名称完全一致，就进行覆盖...


* 课下作业，在默认的情况下，是否能获取/aa/SetCookie
	设置cookie：http://localhost:8080/day08-cookie/aa/SetCookie
	获取cookie：http://localhost:8080/day08-cookie/bb/GetCookie
	
	解决方案：
			cookie.setPath("/day08-cookie"); 
```

> servlet设置多级路径
>
> ```java
> @WebServlet("/xx/Servlet")
> ```





**② 不同tomcat服务器之间Cookie能否共享？（了解）**

```markdown
* 默认情况下不能.....


* 我们可以指定同一个一级域名下，多台服务器共享 （项目）
		cookie.setDomain(".jd.com");
```

### 5 Cookie特点

```markdown
1. cookie存储数据在客户端（浏览器）

2. cookie只能存储字符串

3. cookie单个大小不能超过4KB

4. cookie在同一域名下数量不超过50个

5. cookie存储数据不太安全
```





## 第三章 Cookie案例

### 1 用户上次访问记录

**需求**

访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您的到来。

如果不是第一次访问，则提示：欢迎回来，您上次访问时间为: xxxx。



**代码实现**



```java
public class CookieUtils {

    public static Cookie queryByName(Cookie[] cookies, String last_time) {
        // 非空判断
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            // 对比名称
            if (last_time.equals(cookie.getName())) {
                return cookie;
            }

        }
        // 没有匹配上，返回null
        return null;

    }
}
```



```java
@WebServlet("/LastTimeServlet")
public class LastTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应编码
        response.setContentType("text/html;charset=utf-8");
        // i.获取上次访问时间
        // 1.获取指定名称的cookie
        Cookie[] cookies = request.getCookies();
        Cookie cookie = CookieUtils.queryByName(cookies, "last_time");
        // 2.判断是否为第一次访问
        if (cookie == null) {
            response.getWriter().write("您好，欢迎您的到来..");
        } else {
            String last_time = cookie.getValue(); // 2020-08-25 12:05:11
            // 解码
            last_time = URLDecoder.decode(last_time, "UTF-8");
            response.getWriter().write("欢迎回来，" + last_time);
        }
        // ii.记录本次访问时间
        // 1.获取当前时间
        LocalDateTime now = LocalDateTime.now();
        String current_time = now.toString();// 默认获取 2020-08-25 12:05:11 格式
        // 编码
        current_time = URLEncoder.encode(current_time, "UTF-8");
        // 2.设置cookie并指定持久化
        cookie = new Cookie("last_time", current_time);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        // 3.写入到浏览器
        response.addCookie(cookie);

    }
}
```



### 2 Jsp初体验

- Java服务器端页面（Java Server Pages）
- 简单来说：一个特殊的页面，即可定义html标签，又可以定义java代码
- 作用：简化书写，展示动态页面
- 本质：是servlet（明天翻源码...）

```markdown
* 脚本：Jsp通过脚本方式来定义java代码
		<% java代码 %> 相当于servlet中的service方法

* 内置对象：在Jsp页面中不需要获取和创建，可以直接使用的对象
		request
		response
		out（JspWriter）：在jsp页面中使用它，输出动态资源
```



```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>demo</title>
</head>
<body>



<table border="1">
    <tr>
        <td>我是jsp中定义的表格...</td>
    </tr>
</table>


<%
    System.out.println("jsp中的脚本，相当于servlet的service方法..");

    out.write("jsp响应动态资源");
    // response.getWriter().write("response响应动态资源...");
%>

</body>
</html>
```

### 3 完善案例

**代码实现**

```jsp
<%@ page import="com.itheima.c_case.CookieUtils" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>bbs</title>
</head>
<body>
<h1>葬爱家族之，杨无敌的QQ空间...</h1>



<%
    // i.获取上次访问时间
    // 1.获取指定名称的cookie
    Cookie[] cookies = request.getCookies();
    Cookie cookie = CookieUtils.queryByName(cookies, "last_time");
    // 2.判断是否为第一次访问
    if (cookie == null) {
        out.write("<h1>您好，欢迎您的到来..</h1>");
    } else {
        String last_time = cookie.getValue();  // 2020-08-25 12:05:11
        // 解码
        last_time = URLDecoder.decode(last_time, "UTF-8");
        out.write("<h1>欢迎回来，" + last_time+"</h1>");
    }

    // ii.记录本次访问时间
    // 1.获取当前时间
    LocalDateTime now = LocalDateTime.now();
    String current_time = now.toString();// 默认获取 2020-08-25 12:05:11 格式
    // 编码
    current_time = URLEncoder.encode(current_time, "UTF-8");

    // 2.设置cookie并指定持久化
    cookie = new Cookie("last_time", current_time);
    cookie.setMaxAge(60 * 60 * 24 * 30);

    // 3.写入到浏览器
    response.addCookie(cookie);

%>


<table border="1">
    <tr>
        <td>我是jsp中定义的表格...</td>
    </tr>
</table>
<hr>



<table border="1">
    <tr>
        <td>我是jsp中定义的表格...</td>
    </tr>
</table>
<hr>

<table border="1">
    <tr>
        <td>我是jsp中定义的表格...</td>
    </tr>
</table>
</body>
</html>

```





## 第四章 Session

### 1 概述

**使用Cookie问题**

1. 最多存储4K字符串
2. 存储数据不太安全



session作用：在一次会话的多次请求之间共享数据，将数据**保存到服务器端**



### 2 快速入门

HttpSession也是一个域对象

```markdown
* API
	1. 存储数据
			void setAttribute(String name,Object value)
	2. 获取数据
			Object getAttribute(String name)
	3. 删除数据
			void removeAttribute(String name)
```





**技术分析**

```markdown
1. SetSession
	// 1.获取session空间（域对象）
		HttpSession session = request.getSession();
	// 2.存值
		session.setAttribute("product","oppoX20");

2. GetSession
	// 1.获取session空间（域对象）
		HttpSession session = request.getSession();
	// 2.取值
		session.getAttribute("product");
```



> SetSession

```java
@WebServlet("/SetSession")
public class SetSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取session空间(域对象)
        HttpSession session = request.getSession();
        // 存入值
        session.setAttribute("product","HONORV30");

    }
}
```

> GetSession

```java
@WebServlet("/GetSession")
public class GetSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取session空间（域对象）
        HttpSession session = request.getSession();
        // 2.取值
        String product = (String) session.getAttribute("product");
        System.out.println(product);
    }
}
```



### 3 工作原理

Session基于Cookie技术实现

HTTP协议是非连接性的，取完当前浏览器的内容，然后关闭浏览器后，链接就断开了，而没有任何机制去记录取出后的信息。而当需要访问同一个网站的另外一个页面时(就好比如在第一个页面选择购买的商品后，跳转到第二个页面去进行付款)这个时候取出来的信息，就读不出来了。所以必须要有一种机制让页面知道原理页面的session内容。

### 4 Session细节

#### 4.1 客户端关闭，服务器不关闭

两次获取的Session数据是否为相同？

```markdown
* 默认的情况下不相同
	因为session是基于cookie实现的，浏览器关闭，cookie销毁，jsessionId也就没了
	
* 我们可以手动设置jsessionid的这个cookie，存活时间
	我们可以覆盖它默认的操作，这样就可以持久化到浏览器磁盘中...
```



```java
/**
 * 浏览器关闭，jsessionid不会销毁...
 */
@WebServlet("/ClientClose")
public class ClientClose extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取session对象
        HttpSession session = request.getSession();
        // 存数据
        session.setAttribute("product","HONORV9");
        // 通过session对象,获取jsessionid的值
        String sessionId = session.getId();
        // 我们把服务器默认创建的cookieid进行覆盖,指定存活时间
        Cookie cookie = new Cookie("JSESSIONID", sessionId);
        // 指定路径
        cookie.setPath("/day08_session_war_exploded");
        // 指定存活时间
        cookie.setMaxAge(100);
        // 响应到浏览器
        response.addCookie(cookie);
    }
}
```





#### 4.2 客户端不关闭，服务器关闭

两次获取的Session数据是否为相同？

```markdown
* 默认情况下是一样的....
	钝化：我们tomcat服务器在关闭时，将内存的session数据，序列化到磁盘文件
	活化：我们tomcat服务器在重新启动时，将磁盘文件，反序列化到，服务器内存中...
	前提条件，我们存放的对象，必须实现序列化接口...
```



> 我们在idea中配置tomcat，它会将tomcat做一个克隆，放到C盘目录
>
> 服务器关闭时，查看钝化文件
>
> idea有个坑，它在重新启动时，会把work目录删除
>
> 我们可以通过一个配置，修复



#### 4.3 生命周期

> 我们知道session是一个域对象，对于它的使用，需要掌握这哥们的生命周期

```markdown
* 何时创建
		用户请求携带的jsessionid与服务器不匹配时，创建
		
* 何时销毁
		1.用户非活跃状态30分钟，销毁
		2.服务器非正常关闭，销毁
		3.自杀,session.invalidate(); session.removeAttribute("jsessionid");

* 作用范围
		一次会话，多次请求之间，共享数据
```





#### 4.4 URL重写(了解)

**问题**

​	学习了Session的工作原理后，我们知道Session基于Cookie技术实现；浏览器的Cookie是可以禁用的，一旦禁用了之后，Session就会出现问题了。开发中,一般我们是不关注用户的Cookie是否禁用的，若用户禁用了Cookie，只能别用网站了。

**解决方案**

若真想处理用户端的Cookie禁用，我们可以使用url重写技术：



```java
@WebServlet("/OverrideURL")
public class OverrideURL extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        // 1.获取session
        HttpSession session = request.getSession();
        // 2.存数据
        session.setAttribute("product", "我使用了url重写技术，浏览器禁用了cookie，我也不怕哈哈哈");

        // 3.重写url（将jsessionid，写入到地址栏）
        String url = "/day08_session_war_exploded/GetSession";

        url = response.encodeURL(url);

        response.getWriter().write("<a href='"+ url +"'>使用了url重写技术</a>");
    }
}
```



### 5 Session特点

```markdown
1. session存储数据在服务器

2. session存储类型任意（Object）

3. session存储大小和数量没有限制（相对于内存）

4. session存储相对安全
```



**cookie和session的选择**

> 1. cookie：将数据保存在浏览器端，数据相对不安全，而且数据大小是有限制的，建议不太敏感的数据使用它
>
> 2. session：将数据保存在服务器端，数据相对安全，数据的大小要比cookie中数据灵活很多，但是会占用服务器内存，建议敏感且小量数据使用它
>





## 第五章 域对象总结

![](https://img03.sogoucdn.com/app/a/100520146/7AA1E7B671E0A7169CE9B971F88D1D62)





### 5.1 API

```markdown
1. 设置数据
		void setAttribute(String name, Object o)
		
2. 获取数据
		Object getAttribute(String name)
		
3. 删除数据
		void removeAttribute(String name)
```


### 5.2 生命周期

#### 5.2.1 ServletContext域对象

> 后面的Spring课程会使用它....

```markdown
* 何时创建
		服务器启动，项目加载成功后，创建
		
* 何时销毁
		服务器关闭，项目卸载后，销毁

* 作用范围
		整个web项目
```



#### 5.2.2 HttpSession域对象

```markdown
* 何时创建
		用户携带jsessionid与服务器不匹配时
		
* 何时销毁
		1.非活跃状态30分钟
		2.服务器非正常关闭
		3.自杀

* 作用范围
		一次会话，多次请求之间
```



#### 5.2.3 HttpServletRequest域对象

```markdown
* 何时创建
		用户发送请求时
		
* 何时销毁
		服务器做出响应后

* 作用范围
		一次请求中，多次转发之间
```



### 5.3 小结

> - 能用小的不用大的：request<session<servletContext
>
> - 常用的场景:
>
>   - request：一次查询的结果（servlet转发jsp）
>   - session：存放当前会话的私有数据
>     - 用户登录状态
>     - 验证码
>     - 购物车（后面会用redis...）
>
> - servletContext:若需要所有的servlet都能访问到,才使用这个域对象.
>
>   - spring课程中会涉及到
>





## 第六章 Session案例

### 1 用户登录（验证码）

**需求**

用户访问带有验证码的登录页面，输入用户名，密码以及验证码实现登录功能。



#### 代码实现

####  index.jsp(登录页)

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${NAME}</title>
</head>
<body>
<h3>用户登录</h3>

<form action="/day08-case/LoginServlet" method="post">
    用户名：<input type="text" name="username"><br>
    密&emsp;码：<input type="password" name="password"> <br>
    验证码：<input type="text" name="client_code"> <img src="/day08-case/CheckcodeServlet" alt=""><br>
    <span style="color: red">
        <%
            // 获取request域中的提示信息
            String error = (String) request.getAttribute("error");
            if (error != null) {
                out.write(error);
            }
        %>
    </span> <br>
    <input type="submit" value="登录">
</form>
</body>
</html>

```





####  LoginServlet

```java
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 判断验证码
        // 获取前端提交的验证码
        String client_code = request.getParameter("client_code");
        // 获取session中的
        HttpSession session = request.getSession();
        String code_session = (String) session.getAttribute("code_session");
        // 进行比较
        if (!client_code.equalsIgnoreCase(code_session)) {
            // 验证码错误提示
            request.setAttribute("error","验证码错误...");
            // 转发
            request.getRequestDispatcher("/index.jsp").forward(request,response);
            // 进行拦截
            return;
        }

        // 判断用户名和密码
        // 获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 判断 用户名和密码
        if (!("jack".equals(username) && "123".equals(password))) {
            // 友情提示
            request.setAttribute("error", "用户名或密码错误!!");
            // 转发
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            // 不一致拦截
            return;
        }
        // 将用户信息保存到session中
        session.setAttribute("user",username);
        // 重定向

        response.sendRedirect(request.getContextPath()+"/goods.jsp");

    }
}
```

####  goods.jsp（商品列表）

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>goods</title>
</head>
<body>
<h3>商品列表，
    <%
        String user = (String) session.getAttribute("user");
        if (user != null) {
            out.write("登录人:" + user);
        }
    %>
</h3>
</body>
</html>

```

### 2 商品购物车（重技术轻思想）

**需求**

​	有一个商品页面，可以点击超链接将商品添加到购物车,还有一个超链接,点击它的时候可以查看购物车中商品信息

#### ① goods.jsp

```jsp
<%--
  Created by IntelliJ IDEA.
  User: 11063
  Date: 2020/8/26
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>goods</title>
    </head>
    <body>
        <h3>商品别表,
            <%
                String user = (String) session.getAttribute("user");
                if (user != null) {
                    out.write("登录人:" + user);
                }

            %>

        </h3>

        <a href='/day08_case_war_exploded/cart.jsp'>查看购物车</a></h3>

        <ul>
            <li>
                <a href="/day08_case_war_exploded/AddCartServlet?goods=华为p40">华为p40--加入购物车</a>
            </li>
            <li>
                <a href="/day08_case_war_exploded/AddCartServlet?goods=小米10">小米10--加入购物车</a>
            </li>
            <li>
                <a href="/day08_case_war_exploded/AddCartServlet?goods=三星note20">三星note20--加入购物车</a>
            </li>
            <li>
                <a href="/day08_case_war_exploded/AddCartServlet?goods=oppox20">oppox20--加入购物车</a>
            </li>
        </ul>
    </body>
</html>

```



#### ② AddCartServlet

```java
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理中文乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 接收请求参数
        String goods = request.getParameter("goods");
        // 响应添加成功
        response.getWriter().write(goods+"商品添加购物车成功....");

        // 从session中获取购物车对象(map集合是引用数据类型)
        Map<String,Integer> cart = (Map<String, Integer>) request.getSession().getAttribute("cart");
        // 判断购物车是否存在
        if (cart == null) {
            cart = new LinkedHashMap<>();
            // 向session添加购物车对象
            request.getSession().setAttribute("cart",cart);
        }
        // 判断商品是否存在
        if (cart.containsKey(goods)) {
            // 存在
            cart.put(goods,cart.get(goods)+1);
        }else{
            cart.put(goods,1);

        }
        //提供查看购物车a标签
        response.getWriter().write("<a href='/day08_case_war_exploded/cart.jsp'>查看购物车</a>");


    }
}
```

#### ③ cart.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>cart</title>
    </head>
    <body>
        <%
            Map<String, Integer> cart = (Map) session.getAttribute("cart");

            if (cart == null) { // 空车
                out.write("<h3>您还未购选商品哦，请选择您新换的商品 <a href='/day08_case_war_exploded/goods.jsp'>浏览商品</a></h3>");
            } else { // 有商品
                out.write("<h3>商品列表</h3>");
                for (String k : cart.keySet()) {
                    Integer v = cart.get(k);
                    out.write("<div>商品：" + k + ",数量：" + v + "</div>");
                }
            }

        %>

    </body>
</html>
```

## 总结

```markdown
一 会话概述
	会话
		浏览器与服务器的交互称为一次会话，包含多次请求和响应
	会话技术作用
		存储会话过程中产生的数据
	种类
		cookie
			会话数据保存在客户端
		session
			会话技术保存在服务器端
二 Cookie
	概述
		在一次会话的多次请求之间共享数据，将数据保存到客户端（浏览器）
	实现原理
		基于HTTP协议
			请求头
				cookie
			响应头
				set-cookie
	常用方法
		创建cookie
			Cookie cookie = new Cookie()
		发送cookie
			response.addCookie()
		获取cookie
			request.getCookies()
		细节
			设置cookie存活时间
				cookie.setMaxAge()
					正数
						持久化
					负数
						默认在内存中
					零
						自杀
			设置cookie携带路径
				cookie.setPath()
			设置cookie跨域共享
				cookie.setDomain()
	特点
		1. cookie在客户端存储数据
		2. cookie存储数据格式只能是字符串
		3. 客户端对单个cookie存储数据不能超多4kb
		4. 同一个域名下cookie总数量不能超过50个
		5. cookie存储数据不安全
		6. cookie路径不同，可以重名出现
三 cookie案例
	用户上次访问记录
	Jsp初体验
		既可以定义html标签，又可以定义java代码
四 Session
	概述
		在一次会话的多次请求之间共享数据，将数据保存到服务器端
	实现原理
		基于cookie技术
	api
		获取或创建session
			request.getSession()
		存值
			setAttribute()
		取值
			getAttribute()
		删除
			removeAttribute()
	细节
		浏览器关闭，jsessionid销毁，二次获取session数据不是同一份
		服务器关闭，session钝化，二次获取的session数据是同一份
		生命周期
			创建    用户请求携带的jsessionid与服务器不匹配时，创建
			销毁
				1.用户非活跃状态30分钟，销毁
				2.服务器非正常关闭，销毁
				3.自杀,session.invalidate(); session.removeAttribute("jsessionid");
			作用范围
				一次会话，多次请求之间，共享数据
	特点
		1. session存储数据在服务器
		2. session存储类型任意（Object）
		3. session存储大小和数量没有限制（相对于内存）
		4. session存储相对安全
五 域对象总结
	request
		生命周期
			* 何时创建
				服务器启动，项目加载成功后，创建
			* 何时销毁
				服务器关闭，项目卸载后，销毁
			* 作用范围
				整个web项目
	session
		生命周期
			* 何时创建
				用户携带jsessionid与服务器不匹配时
			* 何时销毁
				1.非活跃状态30分钟
				2.服务器非正常关闭
				3.自杀
			* 作用范围
				一次会话，多次请求之间
	servletContext
		生命周期
			* 何时创建
				用户发送请求时
			* 何时销毁
				服务器做出响应后
			* 作用范围
				一次请求中，多次转发之间
	
```

