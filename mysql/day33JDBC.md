## 今日内容

+ jdbc基础
  + 通过java代码实现对数据的增删改查
+ 用户登录（BUG）
+ PreparedStatement（重点）
+ 连接池（容器）
  + Druid：德鲁伊

## 第一章 JDBC基础

### 1 概述

Java 数据库连接（Java DataBase Connectivity）

```markdown
* 作用
	通过Java语言操作数据库
	
* 本质
	是官方（sun公司）定义的一套操作所有关系型数据库的规则，即接口。各个数据库厂商去实现这套接口，提供数据库驱动jar包。我们可以使用这套接口（JDBC）编程，真正执行的代码是驱动jar包中的实现类。
```

> 面向接口编程

 

### 2 快速入门【重点】

**需求**

通过java代码向数据库插入一条记录



#### **环境搭建**

##### ① 准备数据库和表

```sql
CREATE DATABASE web15;
USE web15;
CREATE TABLE USER(
 id INT PRIMARY KEY AUTO_INCREMENT,
 username VARCHAR(50),
 `password` VARCHAR(50)
);

INSERT INTO USER (username,PASSWORD) VALUES ('admin','123'),('tom','123'),('jack','123');
```

##### ② 创建java模块

> 导入mysql的驱动（jar包）
>
> mysql版本8.0+

 mysql-connector-java-5.1.37-bin.jar



#### 步骤分析

```markdown
1. 注册驱动
2. 建立连接
3. 编写sql
4. 获取sql执行对象
5. 发送sql并返回结果
6. 处理结果
7. 释放资源
```

#### 代码实现

```java
import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 初体验,添加一条记录
 */
public class JdbcQuick {
    public static void main(String[] args) throws Exception {
        // 1. 注册驱动
        DriverManager.registerDriver(new Driver());
        // 2. 建立连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web15", "root", "root");
        // 3. 编写sql
        String sql = "insert into user values(null,'harley','123')";
        // 4. 获取sql执行对象
        Statement statement = connection.createStatement();
        // 5. 发送sql并返回结果
        int i = statement.executeUpdate(sql);
        // 6. 处理结果
        if (i > 0) {
            System.out.println("添加成功....");
        } else {
            System.out.println("添加失败....");
        }
        // 7. 释放资源
        statement.close();
        connection.close();
    }

}
```



### 3 API介绍

> 在java.sql 包下

**DriverManager：驱动管理对象【工具类】**

```markdown
1. 注册驱动
	静态方法：static void registerDriver(Driver driver) 【了解】
		我们观察mysql实现类内部代码，发现了静态代码也注册了一次驱动
		    static {
                try {
                    DriverManager.registerDriver(new Driver());
                } catch (SQLException var1) {
                    throw new RuntimeException("Can't register driver!");
                }
            }
   反射：Class.forName("com.mysql.jdbc.Driver"); 【掌握】
   		加载mysql的驱动类到jvm虚拟机，触发初始化方法
   SPI:服务注册机制【了解】

2. 建立连接
	static Connection getConnection(String url, String user, String password);
		参数
			url：固定格式
				语法：jdbc:mysql://主机地址:端口/数据库名
				实例：
					jdbc:mysql://localhost:3306/web15
					jdbc:mysql:///web15  【连接本机】
					
			user：用户名
			password：密码
```

**Connection：数据库连接对象**

```markdown
1. 获取sql执行对象
	Statement createStatement() 【了解】
	PreparedStatement prepareStatement(String sql)  【掌握】


2. 事务管理
	1）开启事务（关闭自动提交）
		void setAutoCommit(boolean autoCommit)  
			参数：
				true：开启自动提交
				flase：关闭自动提交
	2）提交事务
		void commit()  
	3）回滚事务
		void rollback()
```

**Statement：执行sql的对象**

```markdown
1. 执行所有类型的sql语句 【了解】
		boolean execute(String sql)  

2. 执行DML类型的sql语句
		int executeUpdate(String sql)  
			参数：insert、delete、update
			返回值：影响行数
			
3. 执行DQL类型的sql语句
		ResultSet executeQuery(String sql)  
			参数：select
			返回值：查询后的结果集（单条、多条）
```

**ResultSet：结果集对象,封装查询结果**

```markdown
1. 指针下移（通常与while、if结合使用）
		boolean next()
			返回值：
				true 有数据
				false 没数据

2. 获取数据
		T getXxx(int 列编号)
		T getXxx(String 列名)
		-----------------------------
		Object getObject(String 列名)
		String getString(String 列名)
		
		注意：每一行数据对应java中的一个对象
```

### 4 CRUD操作

**jdbc固定七步**

```java
// 1.注册驱动
// 2.建立连接
// 3.编写sql
// 4.获取sql执行对象
// 5.发送sql并返回结果
// 6.处理结果
// 7.释放资源
```

**user表 添加一条记录**

```java
public class JdbcQuick {

    public static void main(String[] args) throws Exception {
        // 1. 注册驱动
        // DriverManager.registerDriver(new Driver());
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 建立连接
        // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web15", "root", "root");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///web15", "root", "root");
        // 3. 编写sql（分号可以省略）
        String sql = "insert into user values(null,'辛文龙','999');";
        // 4. 获取sql执行对象
        Statement statement = connection.createStatement();
        // 5. 发送sql并返回结果
        int i = statement.executeUpdate(sql);
        // 6. 处理结果
        if (i > 0) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }

        // 7. 释放资源
        statement.close();
        connection.close();
    }
}
```

**user表 修改一条记录**

```java
	/**
     * 修改内容
     * @throws Exception
     */
    @Test
    public void test01() throws Exception{
        // 1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2.建立连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web15", "root", "root");
        // 3.编写sql
        String sql = "update user set username = 'nike' where id = 4";
        // 4.获取sql执行对象
        Statement statement = connection.createStatement();
        // 5.发送sql并返回结果
        int i = statement.executeUpdate(sql);
        // 6.处理结果
        if (i>0) {
            System.out.println("修改成功...");
        }
        else {
            System.out.println("修改失败...");
        }
        // 7.释放资源
        statement.close();
        connection.close();
    }
```

**user表 删除一条记录**

```java
	/**
     * 删除一条记录
     * @throws Exception
     */
    @Test
    public void test02() throws Exception{
        // 1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2.建立连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web15", "root", "root");

        // 3.编写sql
        String sql = "delete from user where id = 4 ";
        // 4.获取sql执行对象
        Statement statement = connection.createStatement();
        // 5.发送sql并返回结果
        int i = statement.executeUpdate(sql);
        // 6.处理结果
        if (i>0) {
            System.out.println("删除成功...");
        }
        else {
            System.out.println("删除失败...");
        }
        // 7.释放资源
        statement.close();
        connection.close();
    }
```

**user表 查询所有记录**

```java
    /**
     * 查询记录
     * @throws Exception
     */
    @Test
    public void test03() throws Exception{
        // 1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2.建立连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web15", "root", "root");

        // 3.编写sql
        String sql = "select * from user";
        // 4.获取sql执行对象
        Statement statement = connection.createStatement();
        // 5.发送sql并返回结果
        ResultSet resultSet = statement.executeQuery(sql);
        // 6.处理结果
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            System.out.println(id+"-"+username+"-"+password);
        }
        // 7.释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
```



### 5 工具类

​	通过上面案例需求我们会发现每次去执行SQL语句都需要注册驱动，获取连接，得到Statement，以及释放资源。发现很多重复的劳动，我们可以将重复的代码定义到一个工具类中。 

**目的**：简化书写，一劳永逸



**步骤分析**

```java
public class JdbcUtils{
    
   // 1.注册驱动 
   static{
        Class.forName("com.mysql.jdbc.Driver");
   }
 
   // 2.建立连接
   public static Connection getConnection(){
       return  DriverManager.getConnection("jdbc:mysql://localhost:3306/web15", "root", "root");
   }
    
   // 3.释放资源
    public static void release(......){
        
    }
}
```





**代码实现**

```java
import java.sql.*;

/**
 * 创建工具类
 */

public class JdbcUtils {
    // 1.注册驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 2.建立连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/web15","root", "root");
    }
    // 3.释放资源
    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void release(Statement statement, Connection connection) {
        release(null, statement, connection);
    }
}
```

### 6 事务操作

```markdown
* 事务
	如果一个包含多个步骤的业务操作，被事务管理，那么这些操作要么同时成功，要么同时失败。
	
* MySQL操作
	1.开启事务
		begin
	2.提交事务
		commit
	3.回滚事务
		rollback
		
* java操作（使用Connection对象）
	1.开启事务（关闭自动提交）
		void setAutoCommit(false);
	2.提交事务
		void commit();
	3.回滚事务
		void rollback();
```

> 转账案例

##### ① 准备表和数据

```sql
-- 创建账户表
CREATE TABLE account (
	id INT PRIMARY KEY AUTO_INCREMENT,
	user VARCHAR(10),
	balance DOUBLE
);
-- 添加数据
INSERT INTO account (user, balance) VALUES ('亚洲舞王', 1000), ('乔碧萝', 1000);
```



##### ② 异常捕获架构

```java
public class TXDemo{
    
    
    @Test
    public void testTx(){
        try{
            
        }catch(Exception e){
            
        }finally{
            
        }
    }
}
```



##### ③ 嵌入事务代码

```java
public class TXDemo {

    @Test
    public void testTx() {
        Connection connection = null;
        Statement statement = null;

        try {
            // 1.获取连接
            connection = JdbcUtils.getConnection();
            // 2.开启事务
            connection.setAutoCommit(false);

            // 3.处理业务逻辑（包含多条sql语句）

            // 4.提交事务
            connection.commit();
        } catch (Exception e) {
            // 5.回滚事务
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            // 6.释放资源
            JdbcUtils.release(statement, connection);

        }
    }
}
```



##### ④ 代码实现

```java
import cn.com.mryhl.c_util.JdbcUtils;
import org.junit.Test;

import java.sql.*;

/**
 * 嵌入事务的代码
 */
public class TXDemo {
    @Test
    public void test01() {
        Connection connection = null;
        Statement statement = null;
        try {
            // 获取连接
            connection = JdbcUtils.getConnection();
            // 开启事务
            connection.setAutoCommit(false);

            // 处理业务逻辑
            statement = connection.createStatement();
            String sql = "update account set balance = balance - 100 where id = 1";
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                System.out.println("舞王扣了钱...");
            }
            // 手动给出错误
            int a = 1/0;
            sql = "update account set balance = balance + 100 where id = 2";
            i = statement.executeUpdate(sql);
            if (i > 0) {
                System.out.println("碧螺加了钱...");
            }
            // 提交事务
            connection.commit();
        } catch (Exception e) {
            // 回滚事务
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // 释放资源
            JdbcUtils.release(statement, connection);
        }
    }

}
```

## 第二章 案例：用户登录

**需求**

登录页面，用户输入账号和密码，实现登录网站功能



### 1 需求分析

前台页面输入用户名和密码,通过servlet调用数据库查询数据对比内容返回结果。 

### 2 代码实现

##### ① 创建web模块

> 导入相关jar
>
> commons-beanutils-1.8.3.jar
>
> commons-logging-1.1.1.jar
>
> javax.servlet.jsp.jstl.jar
>
> jstl-impl.jar
>
> mysql-connector-java-5.1.47.jar

##### ② 导入资源

导入页面资源,主页面等内容。



##### ③ login.jsp

```jsp
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>管理员登录</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
  </head>
  <body>
  	<div class="container" style="width: 400px;">
  		<h3 style="text-align: center;">管理员登录</h3>
        <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
	      <div class="form-group">
	        <label for="user">用户名：</label>
	        <input type="text" name="username" class="form-control" id="user" placeholder="请输入用户名"/>
	      </div>

	      <div class="form-group">
	        <label for="password">密码：</label>
	        <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
	      </div>

	     
	      <hr/>
	      <div class="form-group" style="text-align: center;">
	        <input class="btn btn btn-primary" type="submit" value="登录">
	       </div>
	  	</form>

		<!-- 出错显示的信息框 -->
	  	<div class="alert alert-warning alert-dismissible" role="alert">
		  ${error}
		</div>
  	</div>
  </body>
</html>
```

##### ④ 导入JbdcUtils

 ⑤ LoginServlet

```java
import cn.com.mryhl.c_util.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // 连接数据库
            Connection connection = JdbcUtils.getConnection();
            // 编写sql
            String sql = "select * from user where username = '" + username + "' and password = '" + password + "'";
            // 获取sql执行对象
            Statement statement = connection.createStatement();
            // 发送sql并接收返回结果
            ResultSet resultSet = statement.executeQuery(sql);
            // 进行判断
            if (resultSet.next()) {
                // 从数据库返回结果中获取结果
                String loginUsername = resultSet.getString("username");
                // 存入session中
                request.getSession().setAttribute("loginUsername", loginUsername);
                response.sendRedirect(request.getContextPath() + "/list.jsp");

            } else {
                // 登陆失败
                request.setAttribute("error", "用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }


        } catch (SQLException e) {
           throw new RuntimeException("数据库连接失败");
        }


    }
}
```



##### ⑥ list.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>登陆人:${loginUsername}</h3>
    <h3 style="text-align: center">用户信息列表</h3>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <tr>
            <td>1</td>
            <td>张三</td>
            <td>男</td>
            <td>20</td>
            <td>广东</td>
            <td>44444222</td>
            <td>zs@qq.com</td>
            <td><a class="btn btn-default btn-sm" href="update.jsp">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="">删除</a></td>
        </tr>
        <tr>
            <td>2</td>
            <td>张三</td>
            <td>男</td>
            <td>20</td>
            <td>广东</td>
            <td>44444222</td>
            <td>zs@qq.com</td>
            <td><a class="btn btn-default btn-sm" href="update.jsp">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="">删除</a></td>
        </tr>
        <tr>
            <td>3</td>
            <td>张三</td>
            <td>男</td>
            <td>20</td>
            <td>广东</td>
            <td>44444222</td>
            <td>zs@qq.com</td>
            <td><a class="btn btn-default btn-sm" href="update.jsp">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="">删除</a></td>
        </tr>
        <tr>
            <td>4</td>
            <td>张三</td>
            <td>男</td>
            <td>20</td>
            <td>广东</td>
            <td>44444222</td>
            <td>zs@qq.com</td>
            <td><a class="btn btn-default btn-sm" href="update.jsp">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="">删除</a></td>
        </tr>
        <tr>
            <td>5</td>
            <td>张三</td>
            <td>男</td>
            <td>20</td>
            <td>广东</td>
            <td>44444222</td>
            <td>zs@qq.com</td>
            <td><a class="btn btn-default btn-sm" href="update.jsp">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="">删除</a></td>
        </tr>
        <tr>
            <td colspan="9" align="center">
                <a class="btn btn-primary" href="add.jsp">添加用户</a>
            </td>
        </tr>
    </table>
</div>
</body>
</html>

```

## 第三章 PreparedStatement

### 1 概述

**SQL注入问题**

我们让用户输入的信息和SQL语句进行字符串拼接。用户输入的内容作为了SQL语句语法的一部分，改变了原有SQL真正的意义，以上问题称为SQL注入。 

```sql
-- 此sql原有的含义，是根据用户名和密码 查询
SELECT * FROM USER WHERE username = 'jack' AND PASSWORD = '123'；


-- 现在我们将用户输入的参数和sql字符语句进行拼接，改了sql原有的含义，只是根据用户名去查询（sql注入）
SELECT * FROM USER WHERE username = 'jack'## ' and password = ''；
```



**解决sql注入问题**

我们就不能让用户输入的信息和SQL语句进行字符串拼接。需要使用PreparedSatement对象解决SQL注入。 

```sql
-- 将实际参数采用？占位符代替
SELECT * FROM USER WHERE username = ? AND PASSWORD = ?
```





**PreparedSatement使用步骤**

```java
// 1.sql语句使用？占位符代替
String sql = "SELECT * FROM USER WHERE username = ? AND PASSWORD = ?";

// 2.conn获取sql预编译执行对象,先把java中sql字符串发送给数据库进行预编译
PreparedSatement pstmt = conn.prepareStatement(sql);

// 3.给?赋予实际参数
pstmt.setString(1,"admin");
pstmt.setObject(2,"123");

// 4.执行sql并返回结果，不需要再传递sql语句了
ResultSet rs = pstmt.executeQuery();
```







### 2 操作

**重写用户登录案例**

```java
import cn.com.mryhl.c_util.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/LoginServletPro")
public class LoginServletPro extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // 连接数据库
            Connection connection = JdbcUtils.getConnection();
            // 编写sql
            String sql = "select * from user where username = ? and password = ?";
            // 获取sql执行对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            // 发送sql并接收返回结果
            ResultSet resultSet = preparedStatement.executeQuery();
            // 进行判断
            if (resultSet.next()) {
                // 从数据库返回结果中获取结果
                String loginUsername = resultSet.getString("username");
                // 存入session中
                request.getSession().setAttribute("loginUsername", loginUsername);
                response.sendRedirect(request.getContextPath() + "/list.jsp");

            } else {
                // 登陆失败
                request.setAttribute("error", "用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }


        } catch (SQLException e) {
            throw new RuntimeException("数据库连接失败");
        }
    }
}
```



**步骤分析**

```java
// 1.建立连接
// 2.编写sql，?占位符代替实际参数
// 3.获取sql预编译执行对象
// 4.给?设置实际参数
// 5.执行sql并返回结果
// 6.处理结果
// 7.释放资源
```



**重写添加一条记录**

```java
/**
     *
     * 添加数据
     */
    @Test
    public void test01() throws Exception {
        // 建立连接
        Connection connection = JdbcUtils.getConnection();
        // 编写sql语句
        String sql = "insert into user values(null,?,?)";
        // 获取编译对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"东关塞荡");
        preparedStatement.setString(2,"412");
        // 执行sql并返回结果
        int i = preparedStatement.executeUpdate();
        // 打印处理结果
        System.out.println(i);
        // 释放资源
        JdbcUtils.release(preparedStatement,connection);

    }

    /**
     * 一次编译添加多条记录
     *
     */
    @Test
    public void test02() throws Exception {
        // 建立连接
        Connection connection = JdbcUtils.getConnection();
        // 编写sql语句
        String sql = "insert into user values(null,?,?)";
        // 获取编译对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"东关塞荡");
        preparedStatement.setString(2,"412");
        // 执行sql并返回结果
        int i = preparedStatement.executeUpdate();
        // 打印处理结果
        System.out.println(i);
        preparedStatement.setString(1,"北山黛皋");
        preparedStatement.setString(2,"523");
        // 执行sql并返回结果
        i = preparedStatement.executeUpdate();
        // 打印处理结果
        System.out.println(i);
        // 释放资源
        JdbcUtils.release(preparedStatement,connection);
    }
```



**重写更新一条记录**

```java
    /**
     * 修改一条记录
     *
     */
    @Test
    public void test03() throws Exception {
        // 建立连接
        Connection connection = JdbcUtils.getConnection();
        // 编写sql语句
        String sql = "update user set username = ? where id = ?";
        // 获取编译对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"东关塞荡");
        preparedStatement.setString(2,"2");
        // 执行sql并返回结果
        int i = preparedStatement.executeUpdate();
        // 打印处理结果
        System.out.println(i);
        // 释放资源
        JdbcUtils.release(preparedStatement,connection);
    }

}
```

**重写删除一条记录**

```java
/**
     * 删除一条记录
     */
    @Test
    public void test04() throws Exception {
        // 建立连接
        Connection connection = JdbcUtils.getConnection();
        // 编写sql语句
        String sql = "delete from user  where id = ?";
        // 获取编译对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 2);
        // 执行sql并返回结果
        int i = preparedStatement.executeUpdate();
        // 打印处理结果
        System.out.println(i);
        // 释放资源
        JdbcUtils.release(preparedStatement, connection);


    }
```



**重写查询所有记录**

```java
    /**
     * 查询所有记录
     */
    @Test
    public void test05() throws Exception {
        // 建立连接
        Connection connection = JdbcUtils.getConnection();
        // 编写sql语句
        String sql = "select * from user";
        // 获取编译对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 执行sql并返回结果
        ResultSet resultSet = preparedStatement.executeQuery();
        // 打印处理结果
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username") + "-" + resultSet.getString("password"));
        }
        // 释放资源
        JdbcUtils.release(preparedStatement, connection);


    }
```

### 3 优点

1. 防止sql注入，提高程序的安全性
2. 减少编译次数，提高程序的效率





## 第四章 连接池【理解】

### 1 概述

连接池其实就是一个容器(集合)，存放数据库连接的容器。

当系统初始化好后，容器被创建，容器中会申请一些连接对象，当用户来访问数据库时，从容器中获取连接对象，用户访问完之后，会将连接对象归还给容器。

**优点**

- 节约资源，减轻服务器压力
- 提高连接复用性，用户访问高效

**实现**

Java为数据库连接池提供了公共的接口: DataSource ，各个连接池厂商去实现这套接口，提供jar包。

 

```markdown
* DataSource （javax.sql）
	功能
		* 获取连接：Connection getConnection()
		* 归还连接：connection.close()
			连接池底层对connection.close()进行了增强，使用动态代理，不再是释放资源而是归还连接..
```

**常用的连接池技术**

* C3P0：数据库连接池技术，使用它的开源项目有Hibernate、Spring等。
* <span style="color:red">Druid</span>：(德鲁伊)阿里巴巴提供的数据库连接池技术，是目前最好的数据库连接池。
* HikariCP：小日本开发的连接池技术,称之为效率最高的一款连接池,springboot默认的连接池

**其他的连接池**(了解)

* boneCP：数据库连接池技术，体积小速度快。
* DBCP：Apache提供的数据库连接池技术。

### 2 Druid连接池 【使用了解】

#### 导入环境

mysql-connector-java-5.1.37-bin.jar

druid-1.0.9.jar

#### 2.1 硬编码

| 配置        | 缺省值 | 说明                                                         |
| ----------- | ------ | ------------------------------------------------------------ |
| initialSize | 0      | 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时 |
| maxActive   | 8      | 最大连接池数量                                               |
| maxIdle     | 8      | 已经不再使用，配置了也没效果                                 |
| minIdle     |        | 最小连接池数量                                               |
| maxWait     |        | 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。 |





```java
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

/**
 * 硬编码方式编写 druid连接池
 */
public class Demo01 {
    public static void main(String[] args) throws Exception {
        // 创建druid连接池对象
        DruidDataSource druidDataSource = new DruidDataSource();
        // 设置数据库基本四项
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/web15");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");

        // 设置容器参数
        // 初始化5个连接对象
        druidDataSource.setInitialSize(5);
        // 最大连接数10个
        druidDataSource.setMaxActive(10);
        // 空闲期保留6个连接对象
        druidDataSource.setMinIdle(6);
        // 第十一个人访问时,等待3秒
        druidDataSource.setMaxWait(3000);
        //  获取连接对象
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println("使用连接操作数据库"+connection);
        // 归还到连接池
        connection.close();
    }
}
```



> 上面的代码将可能会修改的参数，写死在java代码中，不方便后期项目的迭代和维护...

#### 2.2 配置文件

##### ① 抽取配置文件

> 配置文件要求在src根目录下

```properties
# 数据库基本四项
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://127.0.0.1:3306/web05
username=root
password=root

# 初始化个数
initialSize=5
# 最大连接个数
maxActive=10
# 等待时间，毫秒
maxWait=3000
```

 ② 代码引入配置文件

```java
/**
 * 配置文件方式,创建druid连接池对象
 */
public class Demo02 {
    public static void main(String[] args) throws Exception {
        // 通过ClassLoader 加载src目录下的 druid.properties  （固定语法）
        InputStream in = Demo02.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        properties.load(in);
        // 获取druid连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        for (int i = 1; i <= 11; i++) {
            Connection connection = dataSource.getConnection();
            System.out.println("第"+i+"次获取连接"+connection);
            if (i == 10){
                // 归还
                connection.close();
            }
        }

    }
    /*
    第1次获取连接com.mysql.jdbc.JDBC4Connection@5c0369c4
    第2次获取连接com.mysql.jdbc.JDBC4Connection@2be94b0f
    第3次获取连接com.mysql.jdbc.JDBC4Connection@d70c109
    第4次获取连接com.mysql.jdbc.JDBC4Connection@17ed40e0
    第5次获取连接com.mysql.jdbc.JDBC4Connection@50675690
    第6次获取连接com.mysql.jdbc.JDBC4Connection@31b7dea0
    第7次获取连接com.mysql.jdbc.JDBC4Connection@3ac42916
    第8次获取连接com.mysql.jdbc.JDBC4Connection@47d384ee
    第9次获取连接com.mysql.jdbc.JDBC4Connection@2d6a9952
    第10次获取连接com.mysql.jdbc.JDBC4Connection@22a71081
    第11次获取连接com.mysql.jdbc.JDBC4Connection@22a71081
    */
}
```







### 3 工具类

​	我们现在发现每一个执行conn之前，都需要实现创建dataSource对象，这个对象是非常笨重的，一个项目只需要配置一次连接池即可



**技术分析**

```java
public class JdbcUtils{
    
    // 1.初始化连接池对象
    static{
        
    }
    
    // 2.提供获取连接池的静态方法
    
    // 3.提供获取连接对象的静态方法
    
    // 4.提供释放资源的方法（conn是归还）
}
```





**代码实现**

```java
import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *  连接池工具类，保证一个项目中的连接池对象只创建一次....
 */
public class JdbcUtils {
    private static DataSource dataSource;
    // 初始化连接池对象
    static {
        try {
            // 通过ClassLoader 加载src目录下的druid.properties(固定语法)
            InputStream in = Demo02.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(in);
            // 获取druid连接对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 提供获取连接池的静态方法
    public static DataSource getDataSource(){
        return dataSource;
    }
    // 提供获取连接对象的静态方法
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
    // 提供释放资源的方法（conn是归还）
    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void release(Statement statement, Connection connection) {
        release(null, statement, connection);
    }
}
```

## 总结

```mark
JDBC基础
	概述
		通过java语言操作数据库
	本质
		面向接口编程思想
		sun公司通过操作关系型数据库的一套规范（接口），所有的数据库厂商都需要实现这套接口，对于开发者只需要学习这套接口的API就可以操作所有类型的关系型数据库，真正的执行者是实现类（jar包驱动）
	快速入门
		1.注册驱动
		2.建立连接
		3.编写sql
		4.获取sql执行对象
		5.执行sql并返回结果
		6.处理结果
		7.释放资源
	API详解
		DriverManager
			1.注册驱动
				Class.forName()
			2.建立连接
				url
				username
				password
		Connection
			1.获取sql执行对象
				Statement
				PreparedStatement
			2.事务安全
		Statement
			1.仅执行DML类型sql语句
				int executeUpdate(String sql)
			2.仅执行DQL类型sql语句
				ResultSet executeQuery(String sql)
		ResultSet
			1.指针下移
				boolean next()
			2.获取数据
				T getXxx(String 列名)
	crud练习
	事务安全
		模拟转账

PreparedStatement
	介绍
		预编译sql执行对象
	优点
		1）防止sql注入，提高安全性
		2）减少编译次数，提高效率
	步骤
		1）注册驱动
		2）建立连接
		3）编写sql ?占位符代替参数
		4）获取sql预编译执行对象 先将sql发送到数据库
		5）设置实际参数
		6）执行sql并返回结果
		7）处理结果
		8）释放资源
	重写用户登录案例，解决sql注入bug
	crud练习
连接池
	介绍：
		在系统初始化时，创建一个连接池生成一些连接对象，用户访问数据库时，从连接池获取连接，使用完毕归还到连接池
	优点
		1）减轻服务器压力
		2）提高连接复用性
	dataSource
		是sun公司提供连接池规范（接口），连接池厂商根据接口编写实现类
			c3p0
			hikariCP
			Druid
	作业
		druid快速入门
			1）导入jar包
			2）定义配置文件
			3）编写代码
		druid连接池工具类
			一个项目只初始化一次连接池对象
```

