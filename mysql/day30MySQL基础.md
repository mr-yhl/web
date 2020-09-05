### 今日内容
+ mysql基础
	+ sql
		+ 分类
		+ 基础语法
	+ 图形化工具
	+ 单表高级查询
	+ 数据库约束
	+ 备份与还原

+ mysql多表和事务

+ mysql函数和索引

+ jdbc和dataSource

+ mybatis基础

+ mybatis多表



## 第一章 数据库介绍

### 1 什么是数据库？

> 存储数据的仓库,本质上就是存储数据的文件系统，方便我们管理数据。




### 2 数据库管理系统层次

数据库管理系统（DataBase Management System，DBMS）：指一种操作和管理数据库的大型软件。

> MySQL软件-->仓库--->表-->记录（数据）



### 3 常见关系型数据库

```markdown
1. MYSQL：开源免费的数据库，小型的数据库.已经被Oracle收购了.MySQL6.x（商业、社区）版本也开始收费。

2. Oracle：收费的大型数据库，Oracle公司的产品。Oracle收购SUN公司，收购MYSQL。

3. DB2：IBM公司的数据库产品,收费的。常应用在银行系统中。

4. SQLServer：MicroSoft 公司收费的中型的数据库。C#、.net等语言常使用。

5. SyBase：已经淡出历史舞台。提供了一个非常专业数据建模的工具PowerDesigner。

6. SQLite: 嵌入式的小型数据库，应用在手机端。

7. OceanBase：阿里巴巴提供的数据库产品，国货之光...
```



## 第二章 MySQL使用

### 1 安装

> 参考昨天的笔记和视频进行安装

```markdown
* 安装成功后，通过DOS命令行验证
		mysql --version
C:\Users\11063\Desktop>mysql --version
mysql  Ver 14.14 Distrib 5.7.27, for Win64 (x86_64)  
```



### 2 配置

**启动和关闭（了解）**

> MySQL软件默认开启自启动

```markdown
1. windows服务窗口
	计算机右键管理，选择服务，搜索MySQL

2. DOS命令行（超级管理员）
		net start mysql
		net stop  mysql
```



**登录**

> dos命令行的方式

```shell
1. 本机登录
		mysql -u用户名 -p密码
		
2. 远程登录
		mysql -h主机地址 -u用户名 -p密码
```



**退出**

```shell
exit
```

```shell
C:\Users\11063\Desktop>mysql -uroot -p
Enter password: ****
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 47
Server version: 5.7.27 MySQL Community Server (GPL)

Copyright (c) 2000, 2019, Oracle and/or its affiliates. All rights reserved.  

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> exit
Bye
```





## 第三章 SQL

### 1 概述

> 结构化查询语言（Structured Query Language）
>
> 通过sql语句，可以实现对数据库的增删改查
>
> CRUD：create 创建、 retrieve(read) 检索、update 更新、delete 删除



### 2 SQL方言

SQL是一套标准，所有的数据库厂商都实现了此标准；但是各自厂商在此标准上增加了特有的语句，这部分内容我们称为方言。

> 方言举例：MySQL注释 ##  MySQL分页 limit



### 3 SQL书写规范

```markdown
1. sql语句可以单行或多行书写，最后以分号结束

2. sql语句在windows平台下不区分大小写，关键字大写

3. 注释
	-- 单行注释
	/* 多行注释 */
```



### 4 SQL分类

```markdown
1. DDL(Data Definition Language)数据定义语言 
		用来定义数据库对象：数据库，表，列等。关键字：create,drop,alter等
	
2. DML(Data Manipulation Language)数据操作语言 
		用来对数据库中表的数据进行增删改。关键字：insert,delete, update等
	
3. DQL(Data Query Language) 数据查询语言
		用来查询数据库中表的记录(数据)。关键字：select, where等
		
--------------------------------------------------------------------

4. DCL(Data Control Language)数据控制语言
		用来定义数据库的访问权限和安全级别，及创建用户。关键字：grant,revoke等

5. TCL(Transaction Control Language) 事务控制语言
		用于控制数据库的事务操作，关键字; commit,savepoint,rollback等
```



## 四 SQL基础操作

### 1 DDL【课下抄一遍】

> 进入公司后，一般一个项目只需要创建一个库若干张表，就可以了

#### 1.1 操作数据库

##### **C：创建**

**直接创建数据库【掌握】**

> 需求：创建一个名为web12的数据库 

```sql
-- 语法
create database 数据库名;
-- 实例
create database web12;
```

**创建数据库并指定字符集【了解】**

> 需求：创建一个名为web12_1的数据库,并指定字符集为gbk

```sql
-- 语法
create database 数据库名 charset 字符集;
-- 实例
create database web12_1 charset gbk;
```



##### **R：查询**

**查看所有数据库**

> 需求：查看所有数据数据库

```sql
-- 语法
show databases;
```

**查看建库语句**

> 需求：查看刚才建立的两个数据库的建库语句

```sql
-- 语法
show create database 数据库名;
-- 实例
show create database web12_1;
show create database web12;
```



##### **U：修改**

> 需求：修改web12_1的数据库字符集为utf8

```sql
-- 语法
alter database 数据库名 charset 字符集;
-- 实例
alter database web12_1 charset utf8;
```



##### **D：删除**

> 需求：删除名为web12_1的数据库

```sql
-- 语法
drop database 数据库名;
-- 实例
drop database web12_1;
```



**使用数据库**

> 需求：查看正在使用的数据库

```sql
-- 语法
select database();
```

> 需求：切换正在使用的数据库为web12

~~~sql
-- 语法
use 数据库名;
-- 实例
use web12;
~~~





#### 1.2 操作表

##### **C：创建【重点】**

> 需求： 在web12中创建一张student表，表中字段有  id  name  birthday 

```sql
-- 语法
create table 表名(
	字段名 数据类型,
    字段名 数据类型,
    ...
);
-- 实例
create table student(
	id int,
    name varchar(32),
    birthday date
);
```



**MySQL中常见的数据类型**

```markdown
* int      整型

* double   浮点型

* decimal  高精度浮点型
	decimal(m,n)
		m：总长度
		n：小数位长度
	举例
		decimal(5,2)
			最大值：999.99
			最小值：0.01

* varchar  可变长度字符串
	varchar(m) 
		m取值范围：1~21841 字符
	举例：
		varchar(10)
			最大值：10个字符（中英文及标点符号）
			最小值：1个字符

* char     固定长度字符串
	char(m)
		m取值范围：1~257
	举例：
		char(10)
			最大值：10个字符
			最小值：10个字符

* text	   文本

* date     日期，格式：yyyy-MM-dd

* datetime 日期时间，格式：yyyy-MM-dd HH:mm:ss
```



**克隆表**

> 为了简化表的创建，在创建新表的时候，可以使用已有表的结构
>
> 需求：利用student表的结构快速复制出一张teacher表

~~~sql
-- 语法
create table 新表 like 旧表;
-- 实例
create table teacher like student;
~~~



##### **R：查询**

**查询所有表**

> 需求：查看当前库中的所有数据表

~~~sql
-- 语法
show tables;
~~~

**查看建表语句**

> 需求：查看建表语句

~~~sql
-- 语法 
show create table 表名;
-- 实例 
show create table teacher;
~~~

**查看表结构**

> 需求：查看student和teacher表的表结构

~~~sql
-- 语法
desc 表名;
-- 实例
desc student;
desc teacher;
~~~



##### **U：修改【开发中绝对不用】**

**添加一列**

> 需求：修改teacher表中添加一列jieshao

~~~sql
-- 语法
alter table 表名 add 列名 数据类型;
-- 实例
alter table teacher add jieshao varchar(30);
~~~



**修改列类型**

> 需求：修改teacher表jieshao列的字符长度为99

~~~sql
-- 语法
alter table 表名 modify 列名 新数据类型;
-- 实例
alter table teacher modify jieshao varchar(99);
~~~



**修改列名**

> 需求：修改teacher表jieshao列名为intro

~~~sql
-- 语法
alter table 表名 change 旧列名 新列名 新数据类型;
-- 实例
alter table teacher change jieshao intro varchar(256);
~~~



**删除指定列**

> 需求：删除teacher表的intro列

~~~sql
-- 语法
alter table 表名 drop 列名;
-- 实例
alter table teacher drop intro;
~~~



**修改表名**

> 需求：修改teacher表的名称为tch

~~~sql
-- 语法
rename table 旧表名 to  新表名;
-- 实例
rename table teacher to  tch;
~~~





##### **D：删除**

> 需求：删除tch表

~~~sql
-- 语法
drop table 表名;
-- 实例
drop table tch;
~~~

### 2 图形化工具

> 安装

> 登录（远程连接）

> 代替DDL操作库

> 代替DDL操作表



### 3 DML【重点】

##### 添加记录

> 需求：在student表中添加如下数据 

| id   | name   | birthday   |
| ---- | ------ | ---------- |
| 1    | 工藤   | 1990-01-01 |
| 5    | 小兰   | 1990-01-01 |
| 6    | 小五郎 |            |

   

**方式一：完整语法**

```sql
-- 语法
insert into 表名(列名1,列名2....) values(值1,值2...);
-- 实例
insert into student(id,name,birthday)values('1','工藤','1990-01-01');
INSERT INTO student(id,`name`)VALUES(6,'小五郎');
-- 注意
	1.字符串类型可以使用 单引号或者双引号，推荐使用单引号
	2.字符串可以插入一切类型，数据库底层进行了因式转换
	3.类的类型必须跟值类型，还有个数，需要一一对应
```



**方式一：简化语法**

```sql
-- 语法
insert into 表名 values(值1,值2...);
-- 实例
insert into student values(5,'小兰','1993-2-2');
insert into student values(7,'灰原哀',null);
-- 注意
	1.插入值的顺序必须跟字段顺一致，补充查看字段顺序：desc 表名
	2.对于不需要插入真实数据的列（字段），使用null填补位置
	
-- 扩展：一条命令插入多条记录
insert into student values(8,'阿里博士',null),(9,'木木景观',null);
```





##### 修改记录

> 需求：修改工藤的name为柯南,birthday为2000-01-01

```sql
-- 语法
update 表名 set 列1=新值1,列2=新值2 where 条件;
-- 实例
update student set name='柯南',birthday='2000-01-01' where id = 1;
```



##### 3）删除记录

> 需求：删除student中name=木木景观的记录

```sql
-- 语法
delete from 表名 where 条件;
-- 实例
DELETE FROM student WHERE id = 9;
```





##### 4）蠕虫复制【了解】

> 作用：将一张表的记录，快速复制到另外一张表
>
> 要求：二张表结构一致

```sql
-- 准备条件 克隆学生表
create table stu like student;

-- 蠕虫复制
insert into stu select * from student;
```



### 4 DQL【重点】

**准备数据** 

```sql
CREATE TABLE `student1` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `chinese` double DEFAULT NULL,
  `english` double DEFAULT NULL,
  `math` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 插入记录
insert into student1(id,name,chinese,english,math) values(1,'tom',89,78,90);
insert into student1(id,name,chinese,english,math) values(2,'jack',67,98,56);
insert into student1(id,name,chinese,english,math) values(3,'jerry',87,78,77);
insert into student1(id,name,chinese,english,math) values(4,'lucy',88,NULL,90);
insert into student1(id,name,chinese,english,math) values(5,'james',82,84,77);
insert into student1(id,name,chinese,english,math) values(6,'jack',55,85,45);
insert into student1(id,name,chinese,english,math) values(7,'tom',89,65,30);
```



**基本查询格式**

```sql
-- 查询全部的字段
select * from 表名;

-- 查询指定的字段
select 列名1,列名2... from 表名;

-- 去重
select distnct 列名 from 表名;

-- null值处理
select ifnull(列名,默认值) from 表名;

-- 别名
select 列名 [as] 列别名,列名 [as] 列别名 from 表名 [as] 表别名;
```



**练习**

```sql
-- 查询表中所有学生的信息
-- 查询表中所有学生的姓名和对应的语文成绩
-- 查询表中学生姓名（去重）
-- 在查询的所有学生数学分数上加10分特长分
-- 统计每个学生的总分
-- 使用别名表示学生总分


-- 查询表中所有学生的信息
SELECT * FROM student1;
-- 查询表中所有学生的姓名和对应的语文成绩
SELECT `name`,chinese FROM student1;

-- 查询表中学生姓名（去重）
-- distinct 去重关键字，多字段去重，要求内容完全一致
SELECT DISTINCT `name` FROM student1;
SELECT DISTINCT `name`,chinese FROM student1;

-- 在查询的所有学生数学分数上加10分特长分
SELECT `name`,math FROM student1;
-- 注意只是查询时，增加了10分，表中原有记录不会受到影响
SELECT `name`,math +10 FROM student1;

-- 统计每个学生的总分
SELECT `name`,chinese + english + math FROM student1;
/*
 null与其他数据进行数学运算都为null
 解决：ifnull(列名,默认值)，作用：如果该列的值为null，就使用你指定的默认值
*/
SELECT IFNULL(english,0) FROM student1;
-- 修复
SELECT `name`,chinese + IFNULL(english,0) + math FROM student1;

-- 使用别名表示学生总分
-- 语法：select 列名 [as] 列别名 from 表名 [as] 表别名
SELECT `name` AS 姓名,(chinese + IFNULL(english,0) + math) AS 总分 FROM student1;


```





## 第五章 DQL单表高级查询【大作业】

**准备数据** 

```sql
DROP TABLE IF EXISTS `student2`;
CREATE TABLE `student2` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `math` int(11) DEFAULT NULL,
  `english` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 插入记录
INSERT INTO student2(id,NAME,age,sex,address,math,english) VALUES 
(1,'马云',55,'男','杭州',66,78),
(2,'马化腾',45,'女','深圳',98,87),
(3,'马景涛',55,'男','香港',56,77),
(4,'柳岩',20,'女','湖南',76,65),
(5,'柳青',20,'男','湖南',86,NULL),
(6,'刘德华',57,'男','香港',99,99),
(7,'马德',22,'女','香港',99,99),
(8,'德玛西亚',18,'男','南京',56,65);
```

### 1 条件查询

**条件查询格式**

```sql
select * from 表名 where 条件;
```

#### **1）关系运算符**

```sql
语法：
	> < >= <= = !=(<>) 
实例：

-- 查询age等于20岁的学生
SELECT * FROM student2 WHERE age = 20;
-- 查询age不等于20岁的学生
SELECT * FROM student2 WHERE age != 20;
-- 查询math分数大于80分的学生
SELECT * FROM student2 WHERE math > 80;
-- 查询english分数小于或等于80分的学生
SELECT * FROM student2 WHERE english <= 80;
```

#### **2）逻辑运算符**

```sql
语法：
	&&(and) ||(or) !(not)
	in关键字
		指定字段，多个条件查询
	between关键字
		指定字段，范围查询
实例：

-- 查询age大于35且性别为男的学生(两个条件同时满足)
SELECT * FROM student2 WHERE age >35 AND sex = '男';
-- 查询age大于35或性别为男的学生(两个条件其中一个满足)
SELECT * FROM student2 WHERE age >35 OR sex = '男';
-- 查询id是1或3或5的学生
SELECT * FROM student2 WHERE id =1 OR id =3 OR id =5;
-- in关键字
-- 字段 in(条件1，条件2，条件3);
-- 再次查询id是1或3或5的学生
SELECT * FROM student2 WHERE id IN(1,3,5,7);
-- 查询id不是1或3或5的学生
SELECT * FROM student2 WHERE id NOT IN(1,3,5,7);

-- 查询english成绩大于等于77，且小于等于87的学生
SELECT * FROM student2 WHERE english>=77 AND english<=87;
-- between关键字
-- 字段 between较小的值 and 较大的值;

-- 再次查询english成绩大于等于77，且小于等于87的学生
SELECT * FROM student2 WHERE english BETWEEN 77 AND 87;
```

#### **3）NULL值判断**

```sql
语法：
	为空：is null
	非空：is not null
实例：

-- 查询英语成绩为null的学生
SELECT * FROM student2 WHERE english IS NULL;
-- 查询英语成绩不为null的学生
SELECT * FROM student2 WHERE english IS NOT NULL;
```

#### **4）模糊查询**

```sql
语法：
	字段 like '字符串通配符'
		_ 表示单个任意字符
		% 表示多个任意字符
实例：

-- 查询姓马的学生
SELECT * FROM student2 WHERE `name` LIKE '马%';
-- 查询姓名中包含'德'字的学生
SELECT * FROM student2 WHERE `name` LIKE '%德%';
-- 查询姓马，且姓名有三个字的学生
SELECT * FROM student2 WHERE `name` LIKE  '马__';
```

### 2 排序

```sql
语法： 
	select * from 表名 order by 字段 [asc | desc] ,字段 [asc | desc]
		asc 升序（默认值）
		desc 降序
		注意：多字段排序，后的排序结果，是在前面排序基础之上实现
实例：

-- 查询所有数据,使用年龄降序排序
SELECT * FROM student2 ORDER BY age DESC;

-- 查询所有数据,在年龄降序排序的基础上，如果年龄相同再以数学成绩降序排序
SELECT * FROM student2 ORDER BY age DESC,math DESC;

-- 排序有null值的列
SELECT * FROM student2 ORDER BY english DESC;
```

### 3 聚合（分组）函数

> 作用：对一列数据进行计算，返回一个结果，忽略null值

```sql
语法： 
	count(列) 统计，建议使用 count(*)
	sum(列)   求和
	max(列)   最大值
	min(列)   最小值
	avg(列)   平均值

实例：
-- 查询学生总数
SELECT COUNT(*) FROM student2;

-- 查询数学成绩总分
SELECT SUM(math) FROM student2;

-- 查询数学成绩平均分
SELECT AVG(math) FROM student2;

-- 查询数学成绩最高分
SELECT MAX(math) FROM student2;

-- 查询数学成绩最低分
SELECT MIN(math) FROM student2;
```

### 4 分组【难度】

> 作用：对一列数据进行分组，相同的数据分为一组，通常与聚合函数一起使用，完成统计工作

 

```sql
语法：
select 分组字段 from 表名 group by 分组字段 having 分组后的条件过滤;
实例：

-- 查询所有学生, 按性别分组, 统计每组的人数
SELECT sex,COUNT(*) FROM student2 GROUP BY sex;

-- 查询年龄大于25岁的人,按性别分组,统计每组的人数
-- 1.查询年龄大于25岁的人
SELECT * FROM student2 WHERE age >25;
-- 2.按性别分组
SELECT sex FROM student2 WHERE age >25 GROUP BY sex;
-- 3.统计每组的人数
SELECT sex,COUNT(*) FROM student2 WHERE age >25 GROUP BY sex;


-- 查询年龄大于25岁的人,按性别分组,统计每组的人数，显示人数大于2的信息
SELECT sex,COUNT(*) FROM student2 WHERE age >25 AND  COUNT(*) >2 GROUP BY sex; -- Invalid use of group function
SELECT sex,COUNT(*) FROM student2 WHERE age >25 GROUP BY sex HAVING COUNT(*) >2;
```

> 面试：where 和 having 区别
>
> 1.where是分组前进行条件过滤，不支持聚合函数
>
> 2.having是分组后进行条件过滤，支持聚合函数





### 5 分页（方言）

**添加数据**

```sql
INSERT INTO student2(id,NAME,age,sex,address,math,english) VALUES 
(9,'唐僧',25,'男','长安',87,78),
(10,'孙悟空',18,'男','花果山',100,66),
(11,'猪八戒',22,'男','高老庄',58,78),
(12,'沙僧',50,'男','流沙河',77,88),
(13,'白骨精',22,'女','白虎岭',66,66),
(14,'蜘蛛精',23,'女','盘丝洞',88,88);
```

```sql
语法：
	select * from 表名 limit 起始索引,显示个数
		起始索引：从0开始
		显示个数：一页展示多少条
索引公式：
	开始索引=（当前页-1）× 每页个数

实例：

-- 查询学生表中数据，显示前4条
SELECT * FROM student2 LIMIT 0,4;
-- 补充：查询前XX条，起始索引可以省略
SELECT * FROM student2 LIMIT 5;

-- 查询学生表中数据，从第三条开始显示，显示4条
SELECT * FROM student2 LIMIT 2,4;

-- 模拟百度分页，共有14条记录，一页展示5条
-- 第一页
SELECT * FROM student2 LIMIT 0,5;
-- 第二页
SELECT * FROM student2 LIMIT 5,5;
-- 第三页
SELECT * FROM student2 LIMIT 10,5;



```

### 6 高级查询的顺序

```sql
select * from 表名 where 条件 group by 分组 having 条件 order by 排序 limit 分页
```

## 第六章 数据库约束

### 1 概述

**作用**

对表中的数据进行限定，保证数据的正确性、有效性和完整性。	

**分类**

1. ☆主键约束：表示当前记录的（唯一+非空）标识。类似于人类的身份证号
2. 唯一约束：表示当前记录的唯一性
3. 非空约束：表示当前记录不能为空
4. 默认值：我们可以为当前字段指定默认值，如果未指定，默认值就为null
5. 外键约束：限定二张表数据的正确性、有效性和完整性（明天讲）



### 2 实现

#### 2.1 主键约束【重点】

 作用：限定某一列的值非空且唯一， 主键就是表中记录的唯一标识。<span style="color:red">通常使用id作为主键</span>

**一张表只能有一个主键，但是一个主键可以包含多个字段**

> 方式一：在建表的时候,在字段名后面通过primary key 声明

```sql
-- 语法：
create table 表名(
	id int primary key,
    ....
    ....
);

-- 实例：
CREATE TABLE pk1(
    id INT PRIMARY KEY,
    `name` VARCHAR(32)
);

-- 插入 测试
INSERT INTO pk1(id,`name`) VALUES(1,'张三');
-- Column 'id' cannot be null  不能为空（非空）
INSERT INTO pk1(id,`name`) VALUES(NULL,'张三');
 -- Duplicate entry '1' for key 'PRIMARY'  不能重复（唯一）
INSERT INTO pk1(id,`name`) VALUES(1,'李四');
```

> 方式二：在建表的时候,在约束区域通过primary key 声明

```sql
-- 语法：
create table 表名(
	id int,
    字段名 数据类型,
    primay key(id)
      
);
-- 实例：
CREATE TABLE pk2(
    id INT,
    `name` VARCHAR(32),
    PRIMARY KEY(id)
);

-- 联合主键
CREATE TABLE pk3(
 id INT ,
 `name` VARCHAR(32),
 PRIMARY KEY(id,`name`)
);

-- 插入测试
INSERT INTO pk3(id,`name`) VALUES(1,'lucy');
INSERT INTO pk3(id,`name`) VALUES(1,'jerry');
INSERT INTO pk3(id,`name`) VALUES(1,'haha');
-- Duplicate entry '1-lucy' for key 'PRIMARY' （多个字段看做一个整体 唯一且非空）
INSERT INTO pk3(id,`name`) VALUES(1,'lucy');
```



> 方式三（了解）：通过修改表结构,添加主键约束 

```sql
-- 语法：
alter table 表名 add primary key(id);
-- 实例：
alter table student2 add primary key(id);
```

> 主键自增：要求主键类型支持自增，一张表只能指定一个自增器

```sql
-- 语法：
create table 表名(
	id int primary key auto_increment,
    ....
    ....
);
-- 实例：
CREATE TABLE pk4(
 id INT PRIMARY KEY AUTO_INCREMENT,
 `name` VARCHAR(32)
);

-- 后期插入数据时
INSERT INTO pk4 (`name`) VALUES('haha');
INSERT INTO pk4 VALUES(NULL,'hehe');
```

> truncate：摧毁表，重构表

```sql
-- 语法：
truncate table 表名;
-- 实例：
truncate table pk4;
```

> 面试题
>
> `truncate table 表名` 和 `delete from 表名` 区别是什么?
>
> DML分类:delete from，相当于橡皮擦，一条条记录抹除，保留痕迹
>
> DDL分类：truncate table，相当于摧毁表，重构表，不保留痕迹，数据量大，性能更好



#### 2.2 唯一约束

> 作用：限定某一列的值不能重复
>

```sql
-- 语法：
create table 表名(
	id INT PRIMARY KEY AUTO_INCREMENT,
    字段名 数据类型 unique
);
-- 实例：
CREATE TABLE un1(
 id INT PRIMARY KEY AUTO_INCREMENT,
 `name` VARCHAR(32) UNIQUE
);

-- 插入测试
INSERT INTO un1(`name`) VALUES('haha');
-- Duplicate entry 'haha' for key 'name' 不能重复（唯一）
INSERT INTO un1(`name`) VALUES('haha');
-- 唯一约束，可以出现多个null，因为这哥们六亲不认
INSERT INTO un1(`name`) VALUES(NULL);
```

#### 2.3 非空约束

> 作用：限定某一列的值不能为null
>

```sql
-- 语法：
create table 表名(
	id INT PRIMARY KEY AUTO_INCREMENT,
    字段名 数据类型 not null
);
-- 实例：
-- 一般情况下 唯一和非空是一起使用...
CREATE TABLE nn1(
  id INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(32) UNIQUE NOT NULL -- 唯一+非空
);
-- 插入测试
INSERT INTO nn1(`name`) VALUES('haha');
-- Duplicate entry 'haha' for key 'name' 不能重复（唯一）
INSERT INTO nn1(`name`) VALUES('haha');
-- Column 'name' cannot be null   非空
INSERT INTO nn1(`name`) VALUES(NULL);
```

> 虽然我们给 name字段设置了 二个约束（unique + not null），功能类似，但它并不是主键约束

#### 2.4 默认值

作用：限定某一列的默认值，再没有指定的情况下所有列的默认值为null

```sql
-- 语法：
create table 表名(
  id int primary key auto_increment,
  字段名 数据类型 default 默认值
);
-- 实例：
CREATE TABLE df1(
 `id` INT PRIMARY KEY AUTO_INCREMENT,
 `name` VARCHAR(32) DEFAULT NULL,
 `sex` VARCHAR(5) DEFAULT '男'
);

-- 录入男生数据
INSERT INTO df1(id,`name`,sex) VALUES(1,'haha','男');
INSERT INTO df1(`name`)VALUES('无敌');

-- 录入女生数据
INSERT INTO df1 VALUES(NULL,'haha','女');

-- 补充：使用简化方式，录入男生信息，使用男生性别的默认值
INSERT INTO df1 VALUES(NULL,'王其',NULL); -- 错误写法 null把默认值进行了覆盖
INSERT INTO df1 VALUES(NULL,'王其','男'); 
```

## 第七章 数据库备份与还原

### 1 DOS命令行【了解】

> 导出（备份）

```sql
语法：
		mysqldump -u用户名 -p密码 导出的数据库名 > 导出文件路径(*.sql)
实例：
		mysqldump -uroot -proot web12 > d:\bak.sql
		mysqldump -uroot -p web12 > d:\bak.sql
		
```


> 缺点：没有建库语句...

> 导入（还原）

```sql
语法：
		mysql -u用户名 -p密码 < 导入文件路径(*.sql)
实例：
		mysql -uroot -p < d:\bak.sql
```

### 2 工具导入

## 总结

```markdown
数据库介绍
	概述
		本质就是存储数据库的仓库，就是文件系统，方便管理数据
		DBMS（数据库管理系统）
			MySQL软件
				多个仓库
					多张表
						多条记录（数据）
		实体和表关系
			一个类对应一张表
			一个变量对应一个字段（列）
			一个对象对应一条记录
		常见关系型数据库
			MySQL
			Oracle
			DB2
数据库安装和使用
	登录
		直接登录本机
			mysql -u用户名 -p
		远程指定ip
			mysql -h主机名 -u用户名 -p密码
	退出
		exit
sql介绍
	概述
		结构化查询语言，通过sql语句可以实现对数据库的基础操作【CRUD】
			create 创建
			retrieve 检索
			update 更新
			delete 删除
	方言
		sql是一套标准，所有的数据库厂商都实现了这套标准，但各自厂商在这套标准上增加自己的特有语句，这部分就称为方言
			例如：mysql注释 #
	sql分类
		DDL
			操作数据库和表
				create
				alter
				drop
		DML
			操作记录的增删改
				insert
				update
				delete
		DQL
			操作记录的查询
				select
		DCL
			操作用户的权限
		TCL
			操作数据的事务安全
sql基础操作
	DDL
		操作数据库
			create database 数据库名;
			show databases;
			drop database 数据库名;
			use 数据库名;
		操作表
			创建表
			常用数据类型
				int
				double
				varchar(1~24400)
					utf8字符个数
				date
			查看表
				show tables;
				desc 表名;
			修改表
				alter table 表名
					add
					modify
					change
					drop
			修改表名
				rename table 旧表名 to 新表名;
			删除表
				drop table 表名;
	DML
		添加记录
			insert into 表名(列名1,列名2...) values(值1,值2...);
				字符串可以使用单双引，推荐单引号
				字符串可以插入任意类型，底层进行了隐式转换
		修改记录
			update 表名 set 列名1=值1,列名2=值2  [where 条件]
		删除记录
			delete from 表名 [where 条件]
	DQL
		简单查询
			select ... from 表名
		去重关键字
			select distinct 列名 from 表名
		ifnull()高级函数
			ifnull(列名,默认值) 如果该列有值直接返回，如果为null那么返回默认值
		别名
			select 列名 [as] 列别名  from 表名 [as] 表别名
dql单表高级查询
	条件
		select ... from 表名 where 条件
			关系运算符
			逻辑运算符
				and
				or
				not
			in关键字
				select ... from 表名 where 列名 in(值1,值2..);
			between关键字
				select ... from 表名 where 列名 between 较小的值 and  较大的的值
			is null关键字
				null 六亲不认
					is null  为空
					is not null  不为空
			like关键字
				_ 单个任意字符
				% 多个任意字符
	排序
		select ... from 表名 order by 排序列 [asc | desc]
			asc 升序 默认值
			desc 降序
	聚合函数
		count
			count(*) ，统计包含null数据
		max
		min
		sum
		avg
	分组
		select 分组 表名 group by 分组 having 分组后条件
			where在分组前条件过滤，不能使用聚合函数
			having在分组后条件过滤，可以使用聚合函数
	分页
		select ... from 表名 limit 开始索引,每页显示个数
		索引公式：
			索引= （当前页-1）× 每页显示个数
数据库约束
	对数据进一步限定，保证数据的正确性，有效性和完整性
	分类
		1）主键
			primary key
				给每一条记录增加唯一标识，非空且唯一
		2）唯一
			unique
		3）非空
			not null
		4）默认值
			default
		5）外键
			foreign key
	创建表时候设置主键约束
数据库备份与还原
	命令行
	图形化工具
```



