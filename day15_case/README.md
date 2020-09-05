## 普通的数据库连接
通过[前台页面](./web/login.jsp)输入用户名和密码,调用[loginservlet](./src/cn/com/mryhl/web/servlet/LoginServlet.java),通过jdbc连接数据库获取查询结果进行判
断,并返回[结果](./web/list.jsp)。

##　sql注入问题
我们让用户输入的信息和SQL语句进行字符串拼接。用户输入的内容作为了SQL语句语法的一部分，改变了原有SQL真正的意义，以上问题称为SQL注入。 
## 解决sql注入问题

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
[解决了sql注入的servlet](./src/cn/com/mryhl/web/servlet/LoginServletPro.java)

