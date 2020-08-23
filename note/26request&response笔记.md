<center>Request&Response</center>

## 今日目标

+ Request请求对象，获取  http请求消息格式（行、头、体）
+ Response响应对象，设置  http响应消息格式（行、头、体）



## 第一章 Request概述

- 用户通过浏览器访问服务器时，Tomcat将HTTP请求中所有的信息都封装在Request对象中
- 作用：开发人员可以通过request对象方法，来获取浏览器发送的所有信息.

**Request体系结构**

```markdown
		ServletRequest
		 	|
		HttpServletRequest
			|
		org.apache.catalina.connector.RequestFacade（由tomcat厂商提供）
```



## 第二章 Request获取Http请求信息

### 1 获取请求行信息

```markdown
* 例如：
		GET /day07-request/RequestLine HTTP/1.1

* 相关API：
	1.获取请求方式 GET
		String getMethod()  
	【☆】2.获取请求项目的网络地址（虚拟路径）/day07-request
		String getContextPath()  
	3.获取请求的URI（统一资源标识符） /day07-request/RequestLine
		共和国
		String getRequestURI() 
	4.获取请求的URL（统一资源定位符） http://localhost:8080/day07-request/RequestLine
		中华人民共和国
		StringBuffer getRequestURL()  
	5.获取请求协议和版本 HTTP/1.1
		String getProtocol()	
	------------------------
	6.获取客户端访问ip
		String getRemoteAddr()
		
		注意：
			如果localhost访问，显示IPV6
			如果127.0.0.1访问，显示IPV4
```



```java
@WebServlet("/RequestLine")
public class RequestLine extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request);
        System.out.println("获取请求方式：" + request.getMethod());
        System.out.println("获取虚拟路径：" + request.getContextPath());
        System.out.println("获取URI：" + request.getRequestURI());
        System.out.println("获取URL：" + request.getRequestURL());
        System.out.println("获取请求协议和版本：" + request.getProtocol());
        System.out.println("获取客户端ip：" + request.getRemoteAddr());
    }
}
```





### 2 获取请求头信息

```markdown
* 例如：
		Host: localhost:8080
* 相关API：
	1.根据请求头的名称获取对应的值【不区分大小写】
			String getHeader(String name)  		
	2.获取所有请求头的名称
			Enumeration<String> getHeaderNames()
            	是Iterator的前身，用于数据的遍历
```



```java
@WebServlet("/RequestHeader")
public class RequestHeader extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取所有请求头的名称
        Enumeration<String> enumeration = request.getHeaderNames();
        // 2.迭代数据
        while (enumeration.hasMoreElements()) {
            String headName = enumeration.nextElement();
        // 3.获取指定名称的值
            String headValue = request.getHeader(headName);
            System.out.println(headName + "=" + headValue);
        }
    }
}
```







#### ① 案例：视频防盗链

> referer : 请求来源
>
> 注意：如果是浏览器地址栏直接访问，没有referer这个头



#### QQVideo

```java
@WebServlet("/QQVideo")
public class QQVideo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        // 1.获取请求来源
        String referer = request.getHeader("referer");
        // 2.判断
        if (referer != null && referer.startsWith("http://localhost:8080/day07_request_war_exploded")) {
                response.getWriter().write("<h1>林绿茶表白许山炮..</h1>");
    } else {
        response.getWriter().write("<h1>想看最新剧集吗，请来腾讯把~~~</h1>");
    }
    }
}
```

#### 内部页面

```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>请求来源</title>
    </head>
    <body>
        <h3>request知识学习</h3>
        <a href="/day07_request_war_exploded/QQVideo">观看三十而已....</a>
    </body>
    </body>
</html>
```

#### 外部网站页面

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>bofang</title>

</head>
<body>
<h3>立群影音</h3>
<a href="http://localhost:8080/day07_request_war_exploded/QQVideo">观看三十而已....</a>
</body>
</html>
```

#### ② 案例：浏览器兼容性

> user-agent：浏览器版本信息

#### 代码实现

```java
@WebServlet("/UserAgent")
public class UserAgent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        // 1.获取user-agent头信息
        String userAgent = request.getHeader("User-Agent");
        // 2.判断厂商
        if (userAgent.contains("Chrome")) {
            response.getWriter().write("这是谷歌浏览器");
        } else if (userAgent.contains("Firefox")) {
            response.getWriter().write("这是火狐浏览器");
        } else {
            response.getWriter().write("其他浏览器");
        }
    }
}
```



### 3 获取请求参数【重点】

- 不论get还是post请求方式，都可以使用下列方法来获取请求参数

```markdown
* 格式
		username=jack&age=18&hobby=drink&hobby=perm

* api
	1.根据参数名获取单个值
			String getParameter(String name)
	2.根据参数名获取多个值【用在复选框】
			String[] getParameterValues(String name)
	3.获取所有参数名和参数值数组
			Map<String,String[]> getParameterMap()
			
			
* 中文乱码
	get：在tomcat8版本之后，已经处理了乱码问题
		浏览器提交UTF-8编码，服务器接收UTF-8解码
	post：出现了中文乱码
		浏览器提交UTF-8编码，服务器接收ISO-8859-1解码
```



**表单提交页面**

```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>form</title>
    </head>
    <body>
        <h3>get请求</h3>
        <form action="/day07_request_war_exploded/RequestParam" method="get">
            用户：<input type="text" name="username"> <br>
            年龄：<input type="text" name="age"> <br>
            爱好：
            <input type="checkbox" name="hobby" value="smoking"> 抽烟
            <input type="checkbox" name="hobby" value="drink"> 喝酒
            <input type="checkbox" name="hobby" value="perm"> 烫头 <br>
            <input type="submit" value="get提交">
        </form>
        <h3>post请求</h3>
        <form action="/day07_request_war_exploded/RequestParam" method="post">
            用户：<input type="text" name="username"> <br>
            年龄：<input type="text" name="age"> <br>
            爱好：
            <input type="checkbox" name="hobby" value="smoking"> 抽烟
            <input type="checkbox" name="hobby" value="drink"> 喝酒
            <input type="checkbox" name="hobby" value="perm"> 烫头 <br>
            <input type="submit" value="post提交">
        </form>
       
    </body>
</html>
```



**java接收参数**

```java
/*
    获取请求参数
 */
@WebServlet("/RequestParam")
public class RequestParam extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户名
        String username = request.getParameter("username");
        System.out.println("用户：" + username);
        // 获取年龄
        String age = request.getParameter("age");
        System.out.println("年龄：" + age);
        // 获取爱好
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println("爱好：" + Arrays.toString(hobbies));
        System.out.println("--------------------------");
        Map<String, String[]> parameterMap = request.getParameterMap();
        // map集合的lambda表达式遍历
        parameterMap.forEach((key, value) -> {
            System.out.println(key + "=" + Arrays.toString(value));
        });
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 指定服务器post提交的解码方式为utf-8
        request.setCharacterEncoding("utf-8");
        System.out.println("post提交");
        // 由于代码冗余，可以让post调用get
        this.doGet(request, response);
    }
}
```





### 2.4 BeanUtils

​	BeanUtils 是 Apache 提供的一套工具包，可以将一个表单提交的所有数据封装到JavaBean（User对象）中；主要用于简化封装数据的操作

#### ① 导入jar包

commons-logging-1.1.1.jar

commons-beanutils-1.8.3.jar

#### ② 准备user实体（JavaBean）

```java
public class User {

    private String username;

    private Integer age;

    private String[] hobby;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String[] getHobby() {
        return hobby;
    }

    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", hobby=" + Arrays.toString(hobby) +
                '}';
    }
}
```



#### ③ 前端表单页面

```html
<h3>BeanUtils请求</h3>
        <form action="/day07_request_war_exploded/BeanUtilsTest" method="post">
            用户：<input type="text" name="username"> <br>
            年龄：<input type="text" name="age"> <br>
            爱好：
            <input type="checkbox" name="hobby" value="smoking"> 抽烟
            <input type="checkbox" name="hobby" value="drink"> 喝酒
            <input type="checkbox" name="hobby" value="perm"> 烫头 <br>
            <input type="submit" value="post提交">
        </form>
```

#### ④ 前置条件

表单内字段名字与JavaBean内字段的名字相对应.

#### ⑤ 代码实现

```java
/*
    将前端表单提交的参数，封装到 User实体中
 */
@WebServlet("/BeanUtilsTest")
public class BeanUtilsTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 1.获取所有表单的参数和值
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 2.准备user实体
        User user = new User();
        try {
        // 3.调用apache工具类，实现快速封装
            BeanUtils.populate(user, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }
}
```





## 第三章 Request其他功能

### 1 请求转发

- 一种在服务器内部的资源跳转方式

```markdown
* API
 	方式一：
 	1.通过request对象，获取转发器对象
 		RequestDispatcher getRequestDispatcher(String path)
 	2.通过RequestDispatcher对象，实现请求转发功能
 		void forward(ServletRequest request, ServletResponse response)  
 		
 	方式二：链式编程
 		 request.getRequestDispatcher("/BServlet").forward(request, response);
 		 
 		 
* 特点
	1）地址栏没有发生改变
	2）浏览器只发送一次请求
	3）request和response只创建一次（AServlet和BServlet共享这二个对象）
	4）转发是服务器内部行为，浏览器不知道
	
```





### 3.2 域对象（共享数据）

- 域对象：一个有作用范围的对象，可以在范围内共享数据
- reqest域：代表一次请求的范围，一般用于一次请求中转发的多个资源中共享数据

```markdown
* API
	1.存数据
			void setAttribute(String name,Object o);
	2.取数据
			Object getAttribute(String name);
	3.删数据
			void removeAttribute(String name);
			
* 生命周期
	1.何时创建？
		用户发送请求时
	2.何时销毁？
		服务器做出响应后		
	3.作用范围？
		一次请求，多次转发之间
```





## 第四章  Request案例：用户登录

### 1 需求

实现用户的登录功能

登录成功跳转到SuccessServlet展示：登录成功！xxx,欢迎您

登录失败跳转到FailServlet展示：登录失败，用户名或密码错误

### 2 代码实现

#### ① login.html

```html
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <title>login<</title>
    </head>
    <body>
        <h3>用户登录</h3>
        <form action="/day07_request_war_exploded/LoginServlet" method="post">
            用户名：<input type="text" name="username"> <br>
            密&emsp;码：<input type="password" name="password"> <br>
            <input type="submit" value="登录">
        </form>
    </body>
</html>
```



> 前端页面写完，先测试，没问题再继续写后端代码...



#### ② LoginServlet

```java
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求解码方式
        request.setCharacterEncoding("utf-8");
        // 1.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 2.判断
        if ("jack".equals(username) && "123".equals(password)) {
            // 登录成功
        // 将用户名存储到request域
            request.setAttribute("username", username);
        // 转发到SuccessServlet
            request.getRequestDispatcher("/SuccessServlet").forward(request,
                    response);
        } else {// 登录失败
        // 转发到FailServlet
            request.getRequestDispatcher("/FailServlet").forward(request, response);
        }
    }
}
```

#### ③ SuccessServlet

```java
@WebServlet("/SuccessServlet")
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        // 获取request域中的值
        String username = (String) request.getAttribute("username");
        response.getWriter().write("登录成功！，" + username + "，欢迎您");
    }
}
```

#### ④ FailServlet

```java
@WebServlet("/FailServlet")
public class FailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("登录失败，用户名或密码错误");
    }
}
```



## 第五章 Response概述

- response对象表示web服务器给浏览器返回的响应信息
- 作用：开发人员可以使用response对象的方法，设置要返回给浏览器的响应信息

**Response体系结构**

```markdown
	ServletResponse 接口
			|	
	HttpServletResponse 接口
			|	
	org.apache.catalina.connector.ResponseFacade 实现类（由tomcat提供的）
```





## 第六章 Response设置Http响应消息

### 1 API

**响应行**

```markdown
* 格式
	 	协议/版本号 状态码
	 
* 例如
		HTTP/1.1 200
		
* API
	1.设置响应状态码
		void setStatus(int sc)  
```



**响应头**

```markdown
* 格式
		响应头名称：响应头的值
		
* 例如
		Location:http://www.itcast.cn
		
* API
	1.设置响应名称和对应的值
		void setHeader(String name, String value)  
```



**响应体**

```markdown
* API
	1.设置字符输出流
		PrintWriter getWriter()
			print()：将其他类型转为字符，再进行输出
			write()：只能输出字符
	2.设置字节输出流
		ServletOutputStream getOutputStream()
		
		
* 注意：
	在同一个servlet内，只能同时使用一种流对象，这哥俩是互斥....
```



### 2 响应重定向

**需求**

用户访问AServlet后，服务器告诉浏览器重定向到BServlet

**技术分析**

```markdown
* 方式一
	// 1.设置响应状态码
		response.setStatus(302);
	// 2.设置响应头Location跳转的地址
		response.setHeader("location","/项目网络地址/资源网络地址");
		
* 方式二
	// 专门提供了一个重定向的方法
		response.sendRedirect("/项目网络地址/资源网络地址");
		
* 特点
	1）地址栏发生改变
	2）浏览器发送二次请求
	3）request和response创建二次（AServlet和BServlet不能共享这二个对象）
	4）重定向是客户端行为，浏览器清楚发生的变化
	
```

**代码实现**

```java
@WebServlet("/AServlet")
public class AServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AServlet执行了....");

       /* // 1.设置响应状态码
        response.setStatus(302);
        // 2.设置响应头Location跳转的地址
        response.setHeader("location","/day07-response/BServlet");*/

        // 专门提供了一个重定向的方法

         response.sendRedirect(request.getContextPath() + "/BServlet");

        //response.sendRedirect("https://www.baidu.com");
    }

}
```

```java
@WebServlet("/BServlet")
public class BServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("BServlet执行了....");
    }

}
```

### 3 转发与重定向的区别【面试题】

```markdown
1. 哪个对象
	转发（request对象的方法）		
		request.getRequestDispatcher("/BServlet").forward(request,response);
	重定向（response对象的方法）		
		response.sendRedirect("/day07-response/BServlet");
		
2. 几次请求
	转发
		地址栏： 没有改变
		浏览器： 发了一次请求
		服务器： 只有一对请求和响应对象
		发生的位置： 服务器内部
	重定向
		地址栏： 发生了改变
		浏览器： 发了两次请求
		服务器： 有两对请求和响应对象
		发生的位置： 浏览器外部
```



### 响应中文

**需求**

向页面输出中文数据没有乱码 



**技术分析**

```markdown
1. 指定服务器响应编码（忘掉）
		void setCharacterEncoding(String charset)
		
2. 统一服务器和客户端编码和解码（掌握）
		void setHeader("Content-Type","text/html;charset=utf-8");
		void setContentType("text/html;charset=utf-8");
```



**代码实现**

```java
@WebServlet("/ResponseCode")
public class ResponseCode extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       /* // 指定服务器响应编码
        response.setCharacterEncoding("gbk");*/

       // 统一服务器和客户端编码和解码
        response.setContentType("text/html;charset=utf-8");

        // 获取response的字符输出流
        PrintWriter writer = response.getWriter();
        writer.write("公司计划收购金额38+10+8");

    }

}
```















## 第七章 Response案例

### 1 点击切换验证码

**需求**

在页面展示登录验证码,点击此验证码可以更换新的验证码 



> 作用：识别机器，防止恶意提交
>
> 本质：就是一张随机图片

```java
@WebServlet("/CheckcodeServlet")
public class CheckcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//  创建画布
		int width = 120;
		int height = 40;
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//  获得画笔
		Graphics g = bufferedImage.getGraphics();
		//  填充背景颜色
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		//  绘制边框
		g.setColor(Color.red);
		g.drawRect(0, 0, width - 1, height - 1);
		//  生成随机字符
		//  准备数据
		String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		//  准备随机对象
		Random r = new Random();
		//  声明一个变量 保存验证码
		String code = "";
		//  书写4个随机字符
		for (int i = 0; i < 4; i++) {
			//  设置字体
			g.setFont(new Font("宋体", Font.BOLD, 28));
			//  设置随机颜色
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

			String str = data.charAt(r.nextInt(data.length())) + "";
			g.drawString(str, 10 + i * 28, 30);

			//  将新的字符 保存到验证码中
			code = code + str;
		}
		//  绘制干扰线
		for (int i = 0; i < 6; i++) {
			//  设置随机颜色
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

			g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
		}

		//  将验证码 打印到控制台
		System.out.println(code);

		//  将验证码放到session中
		request.getSession().setAttribute("code_session", code);

		//  将画布显示在浏览器中
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
```

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>index</title>

</head>
<body>
<h3>response知识学习</h3>

        <img src="/day07_response/CheckcodeServlet" alt="" id="img1">

        <hr>

<script>
            // 给img标签绑定点击事件
            document.getElementById('img1').onclick=function () {
                this.src='/day07_response/CheckcodeServlet?'+new Date().getTime(); // 使用时间戳为了欺骗浏览器
            }
        </script>
</body>
</html>
```

### 2 文件下载

**需求**

用户点击页面的链接，浏览器开始下载文件。 



#### 2.1 使用链接下载文件

##### ① 准备下载素材

文件： 禽兽.jpg	
文件： test.zip	
文件： girl.jpg	
文件： DownLoadUtils.java	
文件： demo.docx	
文件： car.jpg

##### ② 编写下载页面

```html
<h4>超链接直接下载</h4>
        <a href="/day07_response/download/test.zip">test.zip</a><br>
        <a href="/day07_response/download/demo.docx">demo.docx</a><br>
        <a href="/day07_response/download/car.jpg">car.jpg</a><br>
```

##### ③ 注意

> 超链接下载
>
> ​	如果浏览器不能识别这种文件类型则进行下载，如果能识别就是打开...
>
>    不能实现会员级别或积分的校验拦截，所有人都能下载....



#### 2.2 使用Servlet下载文件

- 二个响应头、二个字节流

```markdown
1. 被下载文件的字节输入流
		FileInputStream
		
2. response字节输出流
		ServletOutputStream
		
3. 告知客户端下载文件的MIME类型
		Content-Type:MIME类型
		
4. 告知浏览器以附件的方式保存
		Content-Disposition:attachment;filename=文件名
```



##### ① 简单下载

```java
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收请求参数名
        String filename = request.getParameter("filename");
        // 2.获取该文件真实路径，并封装到字节输入流
        ServletContext servletContext = getServletContext();
        String filePath = servletContext.getRealPath("/download/" + filename);
        FileInputStream in = new FileInputStream(filePath);

        // 3.准备response的字节输出流
        ServletOutputStream out = response.getOutputStream();

        // 4.告知浏览器下载文件的MIME类型
        String mimeType = servletContext.getMimeType(filename);
        response.setContentType(mimeType);

        // 解决文件名乱码问题
        filename = DownLoadUtils.getName(request.getHeader("user-agent"), filename);
        // 5.告知浏览器以附件形式保存文件
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);

        // 6.io的拷贝
       byte[] b = new byte[4096];
        int len;
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }       

        // 7.释放资源
        out.close();
        in.close();
    }

}
```

##### ② 解决中文乱码

> 使用工具类来判断浏览器的来源

```java
public class DownLoadUtils {



	public static String getName(String agent, String filename) throws UnsupportedEncodingException {
		if (agent.contains("Firefox")) {
			// 火狐浏览器
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
		} else {
			// 其它浏览器
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}
}
```

> 改造DownloadServlet

```java
public class DownLoadUtils {
   public static String getName(String agent, String filename) throws UnsupportedEncodingException {
      if (agent.contains("Firefox")) {
         // 火狐浏览器
         BASE64Encoder base64Encoder = new BASE64Encoder();
         filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
      } else {
         // 其它浏览器
         filename = URLEncoder.encode(filename, "utf-8");
      }
      return filename;
   }
}
```

##### ③ HuTool工具

> ​	这哥们是国内几个有志青年，把开发中积累的工具类，封装为一套工具包，满足我们绝大多数场景使用，简化开发过程，提高开发效率



> 导入工具包

hutool-all-5.2.3.jar

> 修改下载代码

```java
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收请求参数名
        String filename = request.getParameter("filename");
        // 2.获取该文件真实路径，并封装到字节输入流
        ServletContext servletContext = getServletContext();
        String filePath = servletContext.getRealPath("/download/" + filename);
        FileInputStream in = new FileInputStream(filePath);

        // 3.准备response的字节输出流
        ServletOutputStream out = response.getOutputStream();

        // 4.告知浏览器下载文件的MIME类型
        String mimeType = servletContext.getMimeType(filename);
        response.setContentType(mimeType);

        // 解决文件名乱码问题
        filename = DownLoadUtils.getName(request.getHeader("user-agent"), filename);
        // 5.告知浏览器以附件形式保存文件
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);

        // 6.io的拷贝
       /*byte[] b = new byte[4096];
        int len;
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }*/

        IoUtil.copy(in, out);

        // 7.释放资源
        out.close();
        in.close();
    }

}
```

## 总结

```markdown
一 Request概述
	开发人员可以通过request对象方法，来获取浏览器发送的所有信息.
	用户通过浏览器访问服务器时，Tomcat将HTTP请求中所有的信息都封装在Request对象中
二 Request获取Http请求信息
	行
		getMethod() 获取请求方式
		getContextPath() 获取请求项目的网络地址
		getRemoteAddr()  获取客户端访问ip
	头
		getHeader(String key)  根据请求头的名称获取对应的值
			Referer  请求来源
				防盗链
			User-Agent  浏览器版本信息
				浏览器兼容性
		getHeaderNames()  获取所有请求头的名称
	参数（体）
		api
			getParameter() 根据参数名获取单个值
			getParameterValues()  根据参数名获取多个值
			getParameterMap() 获取所有参数名和参数值数组
		中文乱码
			get：tomcat8及以上版本，解决了get方式乱码问题
			post：request.setCharacterEncoding("utf-8");
		BeanUtils
			将表单参数快速封装到JavaBean
				BeanUtils.populate(obj, parameterMap);
三 Request其他功能
	请求转发
		一种在服务器内部的资源跳转方式
		request.getRequestDispatcher("/内部资源").forward(request,response);
		特点
			转发是一次请求
			浏览器地址栏不发生变化
			只能跳转到服务器内部资源
			request和response只创建一次
	域对象（共享数据）
		api
			void setAttribute(String name, Object o)  存数据
			Object getAttribute(String name)  取数据
			 void removeAttribute(String name)  删数据
		生命周期
			何时创建
				用户发送请求时
			何时销毁
				服务器做出响应后
			作用范围
				一次请求多次转发中
	获取ServletContext
		api
			ServletContext getServletContext()
				
四 Request案例
	用户登录
五 Response概述
	开发人员可以使用response对象的方法，设置要返回给浏览器的响应信息
	response对象表示web服务器给浏览器返回的响应信息
六 Response设置Http响应消息
	Response设置响应消息
		设置响应行
			void setStatus(int sc)  设置响应状态码
		设置响应头
			void setHeader(String name, String value)  设置响应名称和对应的值
		设置响应体
			ServletOutputStream getOutputStream() 字节输出流
			PrintWriter getWriter() 字符输出流
				print()：将其他类型转为字符，再进行输出
				write()：只能输出字符
	响应重定向
		转发与重定向的区别
			转发
				地址栏： 没有改变
				浏览器： 发了一次请求
				服务器： 只有一对请求和响应对象
				发生的位置： 服务器
			重定向
				地址栏： 发生了改变
				浏览器： 发了两次请求
				服务器： 有两对请求和响应对象
				发生的位置： 浏览器
	响应定时刷新（课下看讲义即可....）
	响应中文
		response.setContentType("text/html;charset=utf-8");
七 Response案例
	点击切换验证码
		作用：识别机器，防止恶意提交
		本质：就是一张随机图片
	文件下载
		1. 被下载文件的字节输入流
			FileInputStream
		2. response字节输出流
			ServletOutputStream
		3. 告知客户端下载文件的MIME类型
			Content-Type:MIME类型
		4. 告知浏览器以附件的方式保存
			Content-Disposition:attachment;filename=文件名
		HuTool工具
			把开发中积累的工具类，封装为一套工具包，满足我们绝大多数场景使用，简化开发过程，提高开发效率
```

