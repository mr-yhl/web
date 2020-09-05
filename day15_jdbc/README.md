## jdbc 快速入门 七步连接数据库
1. 注册驱动
2. 建立连接
3. 编写sql
4. 获取sql执行对象
5. 发送sql并返回结果
6. 处理结果
7. 释放资源
[添加数据](./src/cn/com/mryhl/a_quick/JdbcQuick.java)
### api介绍
#### DriverManager：驱动管理对象
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
jdbc:mysql:///web15 【连接本机】
user：用户名
password：密码
```
#### Connection：数据库连接对象
```markdown
1. 获取sql执行对象
Statement createStatement() 【了解】
PreparedStatement prepareStatement(String sql) 【掌握】
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
#### Statement：执行sql的对象
```markdown
1. 执行所有类型的sql语句 【了解】
boolean execute(String sql)
-----------------------------------
2. 执行DML类型的sql语句
int executeUpdate(String sql)
参数：insert、delete、update
返回值：影响行数
3. 执行DQL类型的sql语句
ResultSet executeQuery(String sql)
参数：select
返回值：查询后的结果集（单条、多条）
```
#### ResultSet：结果集对象,封装查询结果
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
## jbdc练习,实现查询,修改,删除等功能
[查询,删除,修改练习](./src/cn/com/mryhl/b_crud/CrudDemo.java)

## jdbcUtils 一个工具类 简化了连接步骤等
[JdbcUtils](./src/cn/com/mryhl/c_util/JdbcUtils.java)

## 事务练习
[事务练习](./src/cn/com/mryhl/d_tx/TXDemo.java)

## 使用PreparedStatement完成增删改查
[CroudDemo](./src/cn/com/mryhl/e_crud/CrudDemo.java)

#### 优点
1. 防止sql注入，提高程序的安全性
2. 减少编译次数，提高程序的效率

## 连接池
[Druid连接池](./src/cn/com/mryhl/f_druid/Demo01.java)
[配置文件法](./src/cn/com/mryhl/f_druid/Demo02.java)

[引入连接池的工具类](./src/cn/com/mryhl/f_druid/JdbcUtils.java)