## 今日内容

+ mysql函数【使用】	
+ 用户权限【了解】
+ mysql性能篇【索引和数据结构】 了解

## 第一章 MySQL函数

为了简化操作，**mysql提供**了大量的函数给程序员使用（比如你想输入当前时间，可以调用now()函数）

函数可以出现的位置：插入语句的values()中，更新语句中，删除语句中，查询语句及其子句中。

菜鸟教程：<https://www.runoob.com/mysql/mysql-functions.html>



### 1 字符串函数

**常用**

```markdown
1.  函数：CONCAT(s1,s2...sn)
	描述：字符串 s1,s2 等多个字符串合并为一个字符串
	实例：
mysql> select concat('杨海林','陛下');
+------------------------------+
| concat('杨海林','陛下')      |
+------------------------------+
| 杨海林陛下                   |
+------------------------------+
1 row in set (0.04 sec)

-------------------------------------------------

2.  函数：CHAR_LENGTH(str)
	描述：返回字符串 str 的字符数
	实例：
mysql> select char_length('杨海林陛下'); 
+--------------------------------+
| char_length('杨海林陛下')      |
+--------------------------------+
|                              5 |
+--------------------------------+
1 row in set (0.00 sec)
	
3.  函数：LENGTH(str)
	描述：返回字符串 s 的字节数
	字符集：UTF8（一个中文字符占3个字节）
	实例：
mysql> select char_length('杨海林陛下'); 
+--------------------------------+
| char_length('杨海林陛下')      |
+--------------------------------+
|                              5 |
+--------------------------------+
1 row in set (0.00 sec)
	
4.  函数：LCASE(s) | UCASE(s)
	描述：将字符串转换为大小写
	实例：
mysql> select char_length('杨海林陛下'); 
+--------------------------------+
| char_length('杨海林陛下')      |
+--------------------------------+
|                              5 |
+--------------------------------+
1 row in set (0.00 sec)
5.  函数：LOCATE(s1,str)
	描述：从字符串 str 中获取 s1 的开始位置
	注意：从1开始
	实例：
mysql> -- 第一次出现位置
mysql> select locate('y',"mryhl");
+---------------------+
| locate('y',"mryhl") |
+---------------------+
|                   3 |
+---------------------+
1 row in set (0.00 sec)

	
6.  函数：TRIM(str) | LTRIM(str) | RTRIM(str)
	描述：字符串去空格
	实例：
mysql> -- 第一次出现位置
mysql> select locate('y',"mryhl");
+---------------------+
| locate('y',"mryhl") |
+---------------------+
|                   3 |
+---------------------+
1 row in set (0.00 sec)
	
7.  函数：REPLACE(s,s1,s2)
	描述：将字符串 s2 替代字符串 s 中的字符串 s1
	实例：
mysql> -- 第一次出现位置
mysql> select locate('y',"mryhl");
+---------------------+
| locate('y',"mryhl") |
+---------------------+
|                   3 |
+---------------------+
1 row in set (0.00 sec)
	
8.  函数：SUBSTR(s, start, length)
	描述：从字符串 s 的 start 位置截取长度为 length 的子字符串
	注意：从1开始截取
	实例：
mysql> -- 第一次出现位置
mysql> select locate('y',"mryhl");
+---------------------+
| locate('y',"mryhl") |
+---------------------+
|                   3 |
+---------------------+
1 row in set (0.00 sec)
	
9. 函数：STRCMP(str1,str2)
	描述：比较字符串大小,左大于右时返回1，左等于右时返回0，，左小于于右时返回-1，
	实例：
mysql> -- 第一次出现位置
mysql> select locate('y',"mryhl");
+---------------------+
| locate('y',"mryhl") |
+---------------------+
|                   3 |
+---------------------+
1 row in set (0.00 sec)
```



**准备数据**

```sql
CREATE DATABASE web14_1;
USE web14_1;

CREATE TABLE `dept` (
    `id` INT(11) NOT NULL,
    `dname` VARCHAR(50) DEFAULT NULL,
    `loc` VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
INSERT  INTO `dept`(`id`,`dname`,`loc`) VALUES (10,'教研部','北京'),(20,'学工部','上海'),(30,'销售部','广州'),(40,'财务部','深圳');

CREATE TABLE `emp` (
    `id` INT(11) NOT NULL,
    `ename` VARCHAR(50) DEFAULT NULL,
    `job_id` INT(11) DEFAULT NULL,
    `mgr` INT(11) DEFAULT NULL,
    `joindate` DATE DEFAULT NULL,
    `salary` DECIMAL(7,2) DEFAULT NULL,
    `bonus` DECIMAL(7,2) DEFAULT NULL,
    `dept_id` INT(11) DEFAULT NULL,
    `nickname` VARCHAR(32) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

INSERT  INTO 
`emp`(`id`,`ename`,`job_id`,`mgr`,`joindate`,`salary`,`bonus`,`dept_id`,`nickname`)
VALUES 
(1001,'孙悟空',4,1004,'2010-12-17','8000.00',NULL,20,'sunwukong'),
(1002,'卢俊义',3,1006,'2011-02-20','16000.00','3000.00',30,'lujunyi'),
(1003,'林冲',3,1006,'2011-02-22','12500.00','5000.00',30,'linchong'),
(1004,'唐僧',2,1009,'2011-04-02','29750.00',NULL,20,'tangseng'),
(1005,'李逵',4,1006,'2011-09-28','12500.00','14000.00',30,'likui'),
(1006,'宋江',2,1009,'2011-05-01','28500.00',NULL,30,'songjiang'),
(1007,'刘备',2,1009,'2011-09-01','24500.00',NULL,10,'liubei'),
(1008,'猪八戒',4,1004,'2007-04-19','30000.00',NULL,20,'zhubajie'),
(1009,'罗贯中',1,NULL,'2001-02-17','50000.00',NULL,10,'luoguanzhong'),
(1010,'吴用',3,1006,'2008-09-08','15000.00','5000.00',30,'wuyong'),
(1011,'沙僧',4,1004,'2009-05-23','11000.00','3000.00',20,'shaseng'),
(1012,'李逵',4,1006,'2015-12-03','9500.00','5000.00',30,'likui'),
(1013,'小白龙',4,1004,'2013-12-03','30000.00','3000.00',20,'xiaobailong'),
(1014,'关羽',4,1007,'2002-02-23','13000.00',NULL,10,'guanyu');

CREATE TABLE `job` (
    `id` INT(11) NOT NULL,
    `jname` VARCHAR(20) DEFAULT NULL,
    `description` VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ;
INSERT  INTO `job`(`id`,`jname`,`description`) VALUES (1,'董事长','管理整个公司，接单'),(2,'经理','管理部门员工'),(3,'销售员','向客人推销产品'),(4,'文员','使用办公软件');

CREATE TABLE `salarygrade` (
    `grade` INT(11) NOT NULL,
    `losalary` INT(11) DEFAULT NULL,
    `hisalary` INT(11) DEFAULT NULL,
    PRIMARY KEY (`grade`)
) ;

INSERT  INTO `salarygrade`(`grade`,`losalary`,`hisalary`) VALUES (1,7000,12000),(2,12010,14000),(3,14010,20000),(4,20010,30000),(5,30010,99990);
```

**练习**

```sql
-- 1.将所有员工的昵称改为大写
SELECT UCASE(nickname) FROM emp;

-- 2.显示所有员工的姓氏，截取
SELECT SUBSTR(ename,1,1) FROM emp;


-- 3.显示所有员工姓名字符长度
SELECT ename,CHAR_LENGTH(ename) FROM emp;


-- 4.显示所有员工姓名字节长度
SELECT ename,LENGTH(ename) FROM emp;

-- 5.将所有姓李的员工，姓氏替换为li
SELECT REPLACE(ename,'李','li') FROM emp;


-- 6.将所有员工的姓名和昵称拼接在一起 注意事项，mysql这个函数是一个可变参数，oracle只能传递二个参数
SELECT CONCAT(CONCAT(ename,nickname),salary) FROM emp;

```



### 2 日期函数

**常用**

```markdown
1.  函数：NOW() | CURDATE() | CURTIME()
	描述：获取系统当前日期时间、日期、时间
	实例：
mysql> select now();
+---------------------+
| now()               |
+---------------------+
| 2020-09-03 09:03:57 |
+---------------------+
1 row in set (0.07 sec)
	
2.  函数：YEAR(DATE) | MONTH(DATE) | DAY(DATE)
	描述：从日期中选择出年、月、日
	实例：
mysql> select year(now()); 
+-------------+
| year(now()) |
+-------------+
|        2020 |
+-------------+
1 row in set (0.00 sec)
	
3.  函数：LAST_DAY(DATE)
	描述：返回月份的最后一天
	实例：
mysql> select last_day(now());
+-----------------+
| last_day(now()) |
+-----------------+
| 2020-09-30      |
+-----------------+
1 row in set (0.06 sec)
	
4.  函数：ADDDATE(DATE,n) | SUBDATE(DATE,n)
	描述：计算起始日期 DATE 加（减） n 天的日期
	实例：
mysql> select adddate(now(),10);
+---------------------+
| adddate(now(),10)   |
+---------------------+
| 2020-09-13 09:07:43 |
+---------------------+
1 row in set (0.07 sec)
	
5.  函数：QUARTER(DATE)
	描述：返回日期 DATE 是第几季节，返回 1 到 4
	实例：
mysql> select quarter(now());
+----------------+
| quarter(now()) |
+----------------+
|              3 |
+----------------+
1 row in set (0.00 sec)
	
6.  函数：DATEDIFF(d1,d2)
	描述：计算日期 d1->d2 之间相隔的天数
	实例：
mysql> select datediff(now(),"2020-10-1");
+-----------------------------+
| datediff(now(),"2020-10-1") |
+-----------------------------+
|                         -28 |
+-----------------------------+
1 row in set (0.03 sec)
	
7.  函数：DATE_FORMAT(d,f)
	描述：按表达式 f的要求显示日期 d
	实例：
mysql> select date_format(now(),"%Y年%m月%d日");
+--------------------------------------+
| date_format(now(),"%Y年%m月%d日")    |
+--------------------------------------+
| 2020年09月03日                       |
+--------------------------------------+
1 row in set (0.00 sec)
```

**练习**

```sql
-- 1.统计每个员工入职的天数
SELECT ename,DATEDIFF(NOW(),joindate) FROM emp;

-- 2.统计每个员工的工龄
SELECT ename,DATEDIFF(NOW(),joindate)/365 FROM emp;

-- 3.查询2011年入职的员工
SELECT * FROM emp WHERE YEAR(joindate) = 2011;

-- 4.统计入职10年以上的员工信息
SELECT * FROM emp WHERE DATEDIFF(NOW(),joindate)/365  > 10;

```



### 3 数字函数

**常用**

```markdown
1.  函数：ABS(x)
	描述：返回 x 的绝对值　　
	实例：
mysql> select abs(-5);
+---------+
| abs(-5) |
+---------+
|       5 |
+---------+
1 row in set (0.05 sec)
	
2.  函数：CEIL(x) | FLOOR(x)
	描述：向上（下）取整
	实例：
mysql> select ceil(-234.345);
+----------------+
| ceil(-234.345) |
+----------------+
|           -234 |
+----------------+
1 row in set (0.07 sec)
	
3.  函数：MOD(x,y)
	描述：返回x mod y的结果，取余
	实例：
mysql> select mod(5,4);
+----------+
| mod(5,4) |
+----------+
|        1 |
+----------+
1 row in set (0.00 sec)
	
4.  函数：RAND()
	描述：返回 0 到 1 的随机数
	实例：
mysql> select mod(5,4);
+----------+
| mod(5,4) |
+----------+
|        1 |
+----------+
1 row in set (0.00 sec)
	
5.  函数：ROUND(x)
	描述：四舍五入
	实例：
mysql> select mod(5,4);
+----------+
| mod(5,4) |
+----------+
|        1 |
+----------+
1 row in set (0.00 sec)
	
6.  函数：TRUNCATE(x,y)
	描述：返回数值 x 保留到小数点后 y 位的值
	实例：
mysql> select mod(5,4);
+----------+
| mod(5,4) |
+----------+
|        1 |
+----------+
1 row in set (0.00 sec)
```

**练习**

```sql
-- 1.统计每个员工的工龄，超过半年的算一年
SELECT 
  ename,
  ROUND(DATEDIFF(NOW(), joindate) / 365) 
FROM
  emp ;

-- 2.统计每个部门的平均薪资,保留2位小数
SELECT 
  dept_id,
  TRUNCATE(AVG(salary), 2) 
FROM
  emp 
GROUP BY dept_id ;

-- 3.统计每个部门的平均薪资,小数向上取整
SELECT 
  dept_id,
  CEIL(AVG(salary)) 
FROM
  emp 
GROUP BY dept_id ;
-- 4.统计每个部门的平均薪资,小数向下取整
SELECT 
  dept_id,
  FLOOR(AVG(salary)) 
FROM
  emp 
GROUP BY dept_id ;
```



### 4 高级函数

#### 4.1  CASE表达式

> 相当于java中的switch语句

**语法** 

```sql
	SELECT 
		CASE [字段,值] 
			WHEN 判断条件1 
				THEN 希望的到的值1
			WHEN 判断条件2 
				THEN 希望的到的值2
			ELSE 前面条件都没有满足情况下得到的值 
		END
	FROM
		table_name;
```

**练习**

```sql
-- 查询每个员工的工资等级并排序
	-- 工资等级在1显示为 '努力赚钱'
	-- 工资等级在2显示为 '小康生活'
	-- 工资等级在3显示为 '可以娶媳妇'
	-- 工资等级在4显示为 '可以买车'
	-- 工资等级在5显示为 '可以买房'
	-- 工资等级不在以上列表中显示为  '土豪'
	
-- 1.确定表连接关系
SELECT * FROM emp e INNER JOIN salarygrade sg ON e.salary BETWEEN sg.losalary AND sg.hisalary;
-- 2.确定显示字段
SELECT e.ename,e.salary,sg.grade FROM emp e INNER JOIN salarygrade sg ON e.salary BETWEEN sg.losalary AND sg.hisalary;
-- 3.确定业务条件
SELECT 
  e.ename 姓名,
  e.salary 工资,
  CASE sa.grade
  WHEN 1 THEN '努力赚钱'
  WHEN 2 THEN '小康生活'
  WHEN 3 THEN '可以娶媳妇'
  WHEN 4 THEN '可以买车'
  WHEN 5 THEN '可以买房'
  ELSE '土豪'  
  END AS '生活状态'
FROM
  emp e 
  INNER JOIN salarygrade sa 
    ON e.salary BETWEEN sa.losalary 
    AND sa.hisalary ;
```



#### 4.2 IF表达式

> 相当于java中的三元运算符

**语法**

```sql
SELECT IF(1 > 0,'真','假') from 表名;
```

**练习**

```sql
-- 工资+奖金大于20000的员工 显示家有娇妻，否则显示单身狗
SELECT 
  ename 姓名,
  IF(
    (salary + IFNULL(bonus, 0)) > 20000,
    '家有绝美娇妻',
    '单身狗蛋'
  ) ;
```



## 第二章 MySQL综合练习

~~~sql
-- 1.计算员工的日薪(按22天)，保留二位小数
SELECT 
  ename,
  TRUNCATE((salary + IFNULL(bonus, 0)) / 22, 2) 日薪 
FROM
  emp ;
  

-- 2.计算出员工的年薪，并且以年薪排序 降序
SELECT 
  ename,
  ((salary + IFNULL(bonus, 0)) * 12) 年薪 
FROM
  emp 
ORDER BY 年薪 DESC;


-- 3.找出奖金少于5000或者没有获得奖金的员工的信息
SELECT 
  * 
FROM
  emp 
WHERE IFNULL(bonus, 0) < 5000 ;


-- 4.返回员工职务名称及其从事此职务的最低工资
-- 4.1 确定表和连接条件
SELECT * FROM emp e INNER JOIN job j ON e.job_id = j.id;
-- 4.2 分组
SELECT 
  j.jname 职务名称,
  MIN(e.salary) 最低工资
FROM
  emp e 
  INNER JOIN job j 
    ON e.job_id = j.id 
GROUP BY j.jname ;



-- 5.返回工龄超过10年，且2月份入职的员工信息
SELECT 
  * 
FROM
  emp 
WHERE DATEDIFF(NOW(),joindate)/365 > 10 
  AND MONTH(joindate) = 2 ;


-- 6.返回与 林冲 同一年入职的员工
-- 6.1 求出林冲入职时间
SELECT YEAR(joindate) FROM emp WHERE ename = '林冲';
-- 6.2 在查询同年入职员工 
SELECT 
  * 
FROM
  emp 
WHERE YEAR(joindate) = 
  (SELECT 
    YEAR(joindate) 
  FROM
    emp 
  WHERE ename = '林冲' 

); 





-- 7.返回每个员工的名称及其上级领导的名称
SELECT 
  e.ename,
  m.ename 
FROM
  emp e 
  LEFT OUTER JOIN emp m 
    ON e.mgr = m.id ;



-- 8.返回工资为二等级（工资等级表）的职员名字（员工表）、部门名称（部门表）
-- 8.1 确定表和连接条件
SELECT * FROM emp e 
	INNER JOIN salarygrade sg ON e.salary BETWEEN sg.losalary AND sg.hisalary
	INNER JOIN dept d ON e.dept_id = d.id;
-- 8.2 确定显示字段
SELECT 
	e.ename,
	sg.grade,
	d.dname
FROM emp e 
	INNER JOIN salarygrade sg ON e.salary BETWEEN sg.losalary AND sg.hisalary
	INNER JOIN dept d ON e.dept_id = d.id;
-- 8.3 确定业务条件
SELECT 
  e.ename,
  sa.grade,
  d.dname 
FROM
  emp e 
  INNER JOIN salarygrade sa 
    ON e.salary BETWEEN sa.losalary 
    AND sa.hisalary 
  INNER JOIN dept d 
    ON e.dept_id = d.id 
    WHERE sa.grade = 2;


-- 9.涨工资：董事长2000 经理1500 其他800
-- 9.1 确定几张表和连接条件
SELECT * FROM emp e INNER JOIN job j ON e.job_id = j.id;
-- 9.2 确定显示字段
SELECT 
  e.ename,
  j.jname,
  e.salary 涨钱,
  CASE j.jname
  WHEN '董事长' THEN  e.salary + 2000
  WHEN '经理' THEN  e.salary + 1500
  ELSE  e.salary + 800
  END 涨后
FROM
  emp e 
  INNER JOIN job j 
    ON e.job_id = j.id ;
~~~



## 第三章 用户权限 DCL【了解】

> 到了公司这个职责一般有DBA（DataBase  Adminisitrator）完成

```markdown
1. 创建用户
	语法：
		create user '用户名'@'主机名' identified by '密码';
	注意：
		主机名
			指定ip地址
			%：任意地址都可以访问
mysql> create user 'yhl'@'%' identified by '123456';
Query OK, 0 rows affected (2.41 sec)	

C:\Users\11063>mysql -uyhl -p
Enter password: ******
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 9
Server version: 5.7.27 MySQL Community Server (GPL)

Copyright (c) 2000, 2019, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
2. 授权用户
	语法：
		grant 权限1,权限2... on 数据库名.表名 to '用户名'@'主机名';
	注意：
		权限：
			select、insert、update、delete、alter、create....
			all
		数据库名.*
mysql> grant select on web14_1.emp to 'yhl'@'%';     
Query OK, 0 rows affected (0.00 sec)

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| web14_1            |
+--------------------+
2 rows in set (0.00 sec)
3. 查看权限
	语法：
		show grants for '用户名'@'主机名'; 

mysql> show grants for 'yhl'@'%';
+----------------------------------------------+
| Grants for yhl@%                             |
+----------------------------------------------+
| GRANT USAGE ON *.* TO 'yhl'@'%'              |
| GRANT SELECT ON `web14_1`.`emp` TO 'yhl'@'%' |
+----------------------------------------------+
2 rows in set (0.00 sec)
4. 撤销授权
	语法：
		revoke 权限1,权限2... on 数据库名.表名 from '用户名'@'主机名'; 
	注意：
		权限：
			select、insert、update、delete、alter、create....
			all
		数据库名.*
mysql> revoke select on web14_1.emp from 'yhl'@'%';  
Query OK, 0 rows affected (0.00 sec)

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
+--------------------+
1 row in set (0.00 sec)
5. 删除用户
	语法：
		drop user '用户名'@'主机名';
mysql> drop user 'yhl'@'%';
Query OK, 0 rows affected (0.00 sec)

6. 密码管理
	1.超级管理员
		set password for '用户名'@'主机名'=password('新密码');
	2.普通用户
		set password=password('新密码');
		
```



```sql
-- 我现在的角色就是DBA

-- 创建一个用户
CREATE USER 'yhl'@'%'  IDENTIFIED BY '123';

-- 授权
GRANT SELECT ON web14_1.emp TO 'yhl'@'%';

-- 查看权限
SHOW GRANTS FOR 'yhl'@'%';

-- 撤销权限
REVOKE ALL ON web14_1.emp FROM 'yhl'@'%';

-- 帮tom用户修改密码
SET PASSWORD FOR 'yhl'@'%' = PASSWORD('333');

-- 删除用户
DROP USER 'yhl'@'%';
```


## 第四章 MySQL性能

### 1 分析-数据库查询效率低下

​	进入企业后通常只关注业务和功能，反而忽略了性能，随着系统运行时间增加，数据库数据也在增长，那么执行数据库查询的时候性能会越来越慢。

> 硬件优化
>
> 软件优化：单体应用架构（索引）、分库分片（项目二中讲）、读写分离

### 2 分析-效率低的SQL

> 补充：企业开发我们会使用第三方技术来监控sql慢查询日志....

​	MySQL有记录sql执行时长的能力，我们只需要设置一个阈值，一旦某条SQL执行时间大于了这个阈值，MySQL就会将其记录到日志文件中，称为慢日志

```sql
-- 1 查看慢查询日志开启情况（mysql默认是关闭）
	show variables like '%slow_query_log%';

-- 2 开启慢查询日志
	set global slow_query_log = on;

-- 3 查看慢查询时间配置(MySQL默认慢查询的记录时间为10秒)
	show variables like '%long_query_time%';

-- 4 修改慢查询的记录时间（临时修改）
	set long_query_time=3;
```

```markdown
mysql> show variables like '%slow_query_log%';
+---------------------+---------------------------------------------+
| Variable_name       | Value                                       |
+---------------------+---------------------------------------------+
| slow_query_log      | OFF                                         |
| slow_query_log_file | D:\mysql-5.7.27-winx64\data\Mr-yhl-slow.log |
+---------------------+---------------------------------------------+
2 rows in set, 1 warning (0.00 sec)

mysql> set global slow_query_log = on;
Query OK, 0 rows affected (0.05 sec)

mysql> show variables like '%long_query_time%';
+-----------------+-----------+
| Variable_name   | Value     |
+-----------------+-----------+
| long_query_time | 10.000000 |
+-----------------+-----------+
1 row in set, 1 warning (0.00 sec)

mysql> set long_query_time=3;
Query OK, 0 rows affected (0.00 sec)
```

### 3 监控-效率低的SQL

**插入千万数据**

```sql
create database web14_2;

use web14_2;

-- 1. 准备表
CREATE TABLE `user`(
	id INT,
	username VARCHAR(32),
	`password` VARCHAR(32),
	sex VARCHAR(6),
	email VARCHAR(50)
);

-- 2. 创建存储过程，实现批量插入记录
DELIMITER $$ -- 声明存储过程的结束符号为$$
CREATE PROCEDURE auto_insert()
BEGIN
    DECLARE i INT DEFAULT 1;
	START TRANSACTION; -- 开启事务
    WHILE(i<=10000000)DO
        INSERT INTO `user` VALUES(i,CONCAT('jack',i),MD5(i),'male',CONCAT('jack',i,'@itcast.cn'));
        SET i=i+1;
    END WHILE;
	COMMIT; -- 提交
END$$ -- 声明结束
DELIMITER ; -- 重新声明分号为结束符号

-- 3. 查看存储过程
SHOW CREATE PROCEDURE auto_insert;

-- 4. 调用存储过程
CALL auto_insert();
```

> 执行sql，耗时

```markdown
共 1 行受到影响

执行耗时   : 0.004 sec
传送时间   : 1.044 sec
总耗时      : 1.049 sec
--------------------------------------------------

共 0 行受到影响

执行耗时   : 0 sec
传送时间   : 0.001 sec
总耗时      : 0.001 sec
--------------------------------------------------

共 0 行受到影响

执行耗时   : 0.005 sec
传送时间   : 0 sec
总耗时      : 0.006 sec
--------------------------------------------------

共 0 行受到影响

执行耗时   : 0 sec
传送时间   : 1.023 sec
总耗时      : 1.024 sec
--------------------------------------------------

返回了 1 行

执行耗时   : 0 sec
传送时间   : 0 sec
总耗时      : 0.001 sec
--------------------------------------------------

共 0 行受到影响

执行耗时   : 5 min 19 sec
传送时间   : 0 sec
总耗时      : 5 min 19 sec
```



> 测试千万记录的查询时间

```sql
-- 根据id查询
mysql> select * from user where id = 9999;
+------+----------+----------------------------------+------+--------------------+
| id   | username | password                         | sex  | email              |
+------+----------+----------------------------------+------+--------------------+
| 9999 | jack9999 | fa246d0262c3925617b0c72bb20eeb1d | male | jack9999@itcast.cn |
+------+----------+----------------------------------+------+--------------------+
1 row in set (10.17 sec)

mysql> select * from user where id = 99999; 
+-------+-----------+----------------------------------+------+---------------------+
| id    | username  | password                         | sex  | email               |
+-------+-----------+----------------------------------+------+---------------------+
| 99999 | jack99999 | d3eb9a9233e52948740d7eb8c3062d14 | male | jack99999@itcast.cn |
+-------+-----------+----------------------------------+------+---------------------+
1 row in set (9.52 sec)
```

> 慢查询日志文件分析

```markdown
# Time: 2020-09-03T09:56:57.779855Z (时间戳,格林尼治时间)
# User@Host: root[root] @ localhost [::1]  Id:     6
# Query_time(查询耗时): 10.040115  Lock_time(加锁时间): 0.000105 Rows_sent(查询到的记录): 1  Rows_examined(数据量): 10000000
SET timestamp=1599127017;
select * from user where id = 99999;(查询语句)
```

## 第五章 MySQL索引

### 1 什么是索引

在现实生活中，我们都去过图书馆查阅图书。

现在我们将所有图书杂乱无章的摆放在一起，那么找一本书就像大海捞针一样效率非常低。

如果我们按分类整理排序后，根据类别去找对应的图书那么效率就很高了。其实这个整理排序的过程就是<span style="color:red">索引</span>。

MySQL索引的建立对于MySQL的高效运行是很重要的，索引可以大大提高MySQL的检索速度。

如果合理的设计且使用索引的MySQL是兰博基尼的话，那么没有设计和使用索引的MySQL就是人力三轮车。



### 2 MySQL索引分类

```markdown
* 主键（约束）索引
	主键字段 要求：唯一且非空，增加查询速度

* 唯一（约束）索引
	唯一字段 要求：唯一，增加查询速度
	
------------------------------

* 普通索引
	仅提高查询速度

* 组合索引
	相当于联合主键，将多个字段组成一个索引，提高查询速度

* hash索引（了解）
	提高查询速度
	
* 倒排索引（Lucene讲解）
```



### 3 MySQL索引语法

#### 3.1 创建索引

##### ① 直接创建【了解】

```sql
-- 创建普通索引
create index 索引名 on 表名(字段);

-- 创建唯一索引
create unique index 索引名 on 表名(字段);

-- 创建普通组合索引
create index 索引名 on 表名(字段1,字段2);

-- 创建唯一组合索引
create unique index 索引名 on 表名(字段1,字段2);
```

> 练习题
>

~~~sql
CREATE TABLE student1(
  id INT,
  username VARCHAR(30),
  telephone VARCHAR(30),
  sex VARCHAR(5)
);
-- 为username创建普通索引
CREATE INDEX username_idx ON student1(username);


-- 为telephone创建唯一索引
CREATE UNIQUE INDEX tel_un_idx 
ON student1 (telephone) ;

-- 为username  telephone  sex 设置唯一联合索引
CREATE UNIQUE INDEX uts_un_idx
ON student1(username,telephone,sex);

~~~



##### ② 修改表时指定【了解】

```sql
-- 添加主键索引
alter table 表名 add primary key(字段);  -- 默认索引名：primary

-- 添加唯一索引
alter table 表名 add unique(字段);  -- 默认索引名：字段名

-- 添加普通索引
alter table 表名 add index(字段); -- 默认索引名：字段名
```

> 练习题
>

~~~sql
CREATE TABLE student2(
  id INT,
  username VARCHAR(30),
  telephone VARCHAR(30),
  sex CHAR(2)
);
-- 为id设置主键索引
ALTER TABLE student2 ADD PRIMARY KEY (id);

-- 为telephone设置唯一索引
ALTER TABLE student2 ADD UNIQUE (telephone);

-- 为username 设置普通索引
ALTER TABLE student2 ADD INDEX(username);
~~~



##### ③ 创建表时指定【掌握】

```sql
-- 创建表并指定合理的索引字段
CREATE TABLE teacher(
 id INT PRIMARY KEY AUTO_INCREMENT, -- 主键索引
 username VARCHAR(32), 
 telephone VARCHAR(11) UNIQUE, -- 唯一索引
 sex VARCHAR(5),
 INDEX(username) -- 普通索引
);
```



#### 3.2 删除索引

```sql
-- 直接删除
drop index 索引名 on 表名;

-- 修改表时删除 【掌握】
alter table 表名 drop index 索引名;
```

```sql
-- 删除 手机号索引
ALTER TABLE student2 DROP INDEX telephone;
```



### 4 索引效果演示

> 没有索引的查询

```sql
-- 根据id 
select * from user where id = 1234567;
-- 根据用户名
select * from user where username = 'jack1234567';
-- 根据邮箱模糊查询
select * from user where email like 'jack123456%';

mysql> select * from user where id = 1234567;
+---------+-------------+----------------------------------+------+-----------------------+
| id      | username    | password                         | sex  | email                 |
+---------+-------------+----------------------------------+------+-----------------------+
| 1234567 | jack1234567 | fcea920f7412b5da7be0cf42b8c93759 | male | jack1234567@itcast.cn |
+---------+-------------+----------------------------------+------+-----------------------+
1 row in set (10.41 sec)

mysql> select * from user where username = 'jack1234567';
+---------+-------------+----------------------------------+------+-----------------------+
| id      | username    | password                         | sex  | email                 |
+---------+-------------+----------------------------------+------+-----------------------+
| 1234567 | jack1234567 | fcea920f7412b5da7be0cf42b8c93759 | male | jack1234567@itcast.cn |
+---------+-------------+----------------------------------+------+-----------------------+
1 row in set (10.09 sec)

mysql> select * from user where email like 'jack123456%';
+---------+-------------+----------------------------------+------+-----------------------+
| id      | username    | password                         | sex  | email                 |
+---------+-------------+----------------------------------+------+-----------------------+
|  123456 | jack123456  | e10adc3949ba59abbe56e057f20f883e | male | jack123456@itcast.cn  |
| 1234560 | jack1234560 | d8e423a9d5eb97da9e2d58cd57b92808 | male | jack1234560@itcast.cn |
| 1234561 | jack1234561 | aaa42296669b958c3cee6c0475c8093e | male | jack1234561@itcast.cn |
| 1234562 | jack1234562 | 6978915ee7dbc5aff1bc34b59e8c0fc5 | male | jack1234562@itcast.cn |
| 1234563 | jack1234563 | 11ea1cf4f9ed1fd9e94f451963c90365 | male | jack1234563@itcast.cn |
| 1234564 | jack1234564 | 9cf6f9edb58e7f3dadc1f65fdbe58b7a | male | jack1234564@itcast.cn |
| 1234565 | jack1234565 | a071495b74b65a34559c76227e0633a4 | male | jack1234565@itcast.cn |
| 1234566 | jack1234566 | 8d70d8ab2768f232ebe874175065ead3 | male | jack1234566@itcast.cn |
| 1234567 | jack1234567 | fcea920f7412b5da7be0cf42b8c93759 | male | jack1234567@itcast.cn |
| 1234568 | jack1234568 | fe743d8d97aa7dfc6c93ccdc2e749513 | male | jack1234568@itcast.cn |
| 1234569 | jack1234569 | e36a2f90240e9e84483504fd4a704452 | male | jack1234569@itcast.cn |
+---------+-------------+----------------------------------+------+-----------------------+
11 rows in set (10.41 sec)
```

> 添加索引再次测试

```sql
-- id 使用主键索引
ALTER TABLE `user` ADD PRIMARY KEY(id);
-- username 普通索引
ALTER TABLE `user` ADD INDEX(username);
-- email 使用唯一索引
ALTER TABLE `user` ADD  UNIQUE(email);

mysql> ALTER TABLE `user` ADD PRIMARY KEY(id);
Query OK, 0 rows affected (3 min 18.75 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> ALTER TABLE `user` ADD INDEX(username);
Query OK, 0 rows affected (1 min 39.03 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> ALTER TABLE `user` ADD  UNIQUE(email);
Query OK, 0 rows affected (1 min 58.31 sec)
Records: 0  Duplicates: 0  Warnings: 0
```

**运行结果**

```markdown
mysql> select * from user where id = 1234567;
+---------+-------------+----------------------------------+------+-----------------------+
| id      | username    | password                         | sex  | email                 |
+---------+-------------+----------------------------------+------+-----------------------+
| 1234567 | jack1234567 | fcea920f7412b5da7be0cf42b8c93759 | male | jack1234567@itcast.cn |
+---------+-------------+----------------------------------+------+-----------------------+
1 row in set (0.08 sec)

mysql> select * from user where username = 'jack1234567';
+---------+-------------+----------------------------------+------+-----------------------+
| id      | username    | password                         | sex  | email                 |
+---------+-------------+----------------------------------+------+-----------------------+
| 1234567 | jack1234567 | fcea920f7412b5da7be0cf42b8c93759 | male | jack1234567@itcast.cn |
+---------+-------------+----------------------------------+------+-----------------------+
1 row in set (0.10 sec)

mysql> select * from user where email like 'jack123456%';
+---------+-------------+----------------------------------+------+-----------------------+
| id      | username    | password                         | sex  | email                 |
+---------+-------------+----------------------------------+------+-----------------------+
| 1234560 | jack1234560 | d8e423a9d5eb97da9e2d58cd57b92808 | male | jack1234560@itcast.cn |
| 1234561 | jack1234561 | aaa42296669b958c3cee6c0475c8093e | male | jack1234561@itcast.cn |
| 1234562 | jack1234562 | 6978915ee7dbc5aff1bc34b59e8c0fc5 | male | jack1234562@itcast.cn |
| 1234563 | jack1234563 | 11ea1cf4f9ed1fd9e94f451963c90365 | male | jack1234563@itcast.cn |
| 1234564 | jack1234564 | 9cf6f9edb58e7f3dadc1f65fdbe58b7a | male | jack1234564@itcast.cn |
| 1234565 | jack1234565 | a071495b74b65a34559c76227e0633a4 | male | jack1234565@itcast.cn |
| 1234566 | jack1234566 | 8d70d8ab2768f232ebe874175065ead3 | male | jack1234566@itcast.cn |
| 1234567 | jack1234567 | fcea920f7412b5da7be0cf42b8c93759 | male | jack1234567@itcast.cn |
| 1234568 | jack1234568 | fe743d8d97aa7dfc6c93ccdc2e749513 | male | jack1234568@itcast.cn |
| 1234569 | jack1234569 | e36a2f90240e9e84483504fd4a704452 | male | jack1234569@itcast.cn |
|  123456 | jack123456  | e10adc3949ba59abbe56e057f20f883e | male | jack123456@itcast.cn  |
+---------+-------------+----------------------------------+------+-----------------------+
11 rows in set (0.07 sec)
```





### 5 索引的优缺点

**优点**

* 类似大学图书馆建书目索引，提高数据检索的效率，降低数据库的IO成本。
* 通过索引列对数据进行排序，降低数据排序的成本，降低CPU的消耗。



**缺点**

- 实际上索引也是表中的一部分，所以索引列也是要占用空间
- 虽然索引大大提高了查询速度，同时却会降低更新表的速度，如对表进行INSERT、UPDATE和DELETE时，需要重写对索引进行排序整理



### 6 索引创建原则

```markdown
1. 经常搜索的字段，where

2. 经常排序的字段，order by

3. 经常分组的字段，group by （内容可识别率不能低于70%）

4. 多表连接的字段，主键 和 外键
```





### 7 常见索引失效情况

```sql
-- 1.使用like模糊匹配，%通配符在最左侧使用时
select * from user where username like '%jack123456';
+--------+------------+----------------------------------+------+----------------------+
| id     | username   | password                         | sex  | email                |
+--------+------------+----------------------------------+------+----------------------+
| 123456 | jack123456 | e10adc3949ba59abbe56e057f20f883e | male | jack123456@itcast.cn |
+--------+------------+----------------------------------+------+----------------------+
1 row in set (30.40 sec)

```

```sql
-- 2.尽量避免使用or，如果条件有一个没有索引，那么会进行全表扫描
mysql> select * from user where username = 'jack123' or password = 'd8e423a9d5eb97da9e2d58cd57b92808';
+---------+-------------+----------------------------------+------+-----------------------+
| id      | username    | password                         | sex  | email                 |
+---------+-------------+----------------------------------+------+-----------------------+
|     123 | jack123     | 202cb962ac59075b964b07152d234b70 | male | jack123@itcast.cn     |
| 1234560 | jack1234560 | d8e423a9d5eb97da9e2d58cd57b92808 | male | jack1234560@itcast.cn |
+---------+-------------+----------------------------------+------+-----------------------+
2 rows in set (30.23 sec)
```

```sql
-- 3.在索引列上进行计算
mysql> select * from user where id + 1 = 10;
+----+----------+----------------------------------+------+-----------------+
| id | username | password                         | sex  | email           |
+----+----------+----------------------------------+------+-----------------+
|  9 | jack9    | 45c48cce2e2d7fbdea1afc51c7c6ad26 | male | jack9@itcast.cn |
+----+----------+----------------------------------+------+-----------------+
1 row in set (28.77 sec)
```

```sql
-- 4.使用 !=、 <>、 not in、is not null时
select * from user where id is not null;
...
10000000 rows in set (34.33 sec)
```

```sql
-- 5.组合索引，最左优先原则
alter table user add unique(id,password);

select * from user where id = 1 and password = 'c4ca4238a0b923820dcc509a6f75849b'; -- 有效
select * from user where id = 1 ; -- 有效
select * from user where  password = 'c4ca4238a0b923820dcc509a6f75849b'; -- 失效
```

### 8 索引的数据结构

#### 8.1 概述

我们知道**索引**是帮助MySQL高效获取**排好序**的**数据结构**。

为什么使用索引后查询效率提高很多呢？接下来我们来了解下。 

​	在没有索引的情况下我们执行一条sql语句，那么是表进行全局遍历，磁盘寻址（注意逻辑上相邻的记录在磁盘上也并不是一定物理相邻的）。

```sql
select  * from user where col1=6;
```

​		为了加快的查找效率，可以维护一个右边所示的二叉查找树，每个节点分别包含索引键值和一个指向对应数据记录物理地址的指针，这样就可以运用二叉查找快速获取到相应数据。

```sql
select  * from user where col2=89;
```

#### 8.2 索引的数据结构

1. 二叉树：对于当前父节点，左分支节点元素小，右分支节点元素大
2. 红黑树：红黑树是一种平衡二叉树，目的是寻求左右分支长度的平衡
3. BTree：多叉平衡查找树，每个节点都可以存放多个数据，可以有效减少层数
4. B+Tree：针对于BTree的优化，数据只存储在叶子节点上，非叶子节点只存储索引和指针
5. Hash



**数据结构学习网站**

```http
https://www.cs.usfca.edu/~galles/visualization/Algorithms.html
```

#### 8.3  MySQL中的B+Tree

```sql
-- 查看mysql索引节点大小
show global status like 'innodb_page_size';
```

### 9 数据库的存储引擎

MySQL存储引擎的不同，那么索引文件保存的方式也有所不同，常见的有二种存储引擎**MyISAM**和**InnoDB**。

#### 9.1 MyISAM

MySQL5.5之前默认使用的存储引擎，不支持事务和外键约束

```sql
CREATE TABLE haha(
 id INT PRIMARY KEY AUTO_INCREMENT,
 `name` VARCHAR(32)
)ENGINE=MYISAM;
```

#### 9.2 InnoDB

MySQL5.5之后默认使用的存储引擎，支持事务

```sql
CREATE TABLE hehe(
 id INT PRIMARY KEY AUTO_INCREMENT,
 `name` VARCHAR(32)
)ENGINE=INNODB;
```

### 10 面试题

**为什么InnoDB表必须有主键，并且推荐使用整型的自增主键？**

```markdown
1. innodb保存数据时必须依赖B+Tree 如果不指定主键索引，自动生成一个，浪费磁盘空间，也没有提高查询效率
2. 主键使用整型且自增，整型比较速度快，插入数据时，减少树的裂变，提高插入效率....
```



## 总结

```markdown
MySQL函数
	字符串
		concat()
			拼接
		char_length()
			字符长度
		trim()
			去掉前后空格
		replace()
			替换
		substr()
			截取
				注意：位置从1开始
	日期
		now()、curdate()、curtime()
		year()、month()、day()
		adddate()、subdate()
		datediff()
			日期间之间的计算
		date_formate
	数学
		ceil()、floor()
			向上向下取整
		rand()
			随机数
		round()
			四舍五入
		truncate()
			保留指定小数位
	高级函数
		case表达式
			相当于java中swtich
		if表达式
			相当于java中三元运算符
MySQL性能
	数据库类型
		查询密集型
			索引优化
		增删改密集型
	慢查询日志
		show variables like '%query%';
		
	插入千万级别记录
MySQL索引
	索引=排好序的数据结构
	创建索引
	索引的创建原则
		1）经常where查询字段
		2）经常 group by | order by 字段
		3）多表关联字段（内连接、外连接）
	索引失效问题
		1）like使用左模糊查询
		2）or拼接
		3）索引列计算
		4）not in 、 != 、  is not null
	索引的数据结构
		二叉树
			左边子节点比父节点小，右边的子节点比父节点大
		红黑树
			平衡二叉树
				左旋和右旋
		BTree
			多路平衡搜索树
				有效控制树的高度
					每一个节点可以设置多个元素
						索引
						指针
						数据
		B+Tree
			MySQL使用数据结构
				非叶子节点只存储索引和指针，叶子节点存储索引和数据（从左到右，从小到大依次排序，底层是一个双向链表）
		Hash散列
	数据库存储引擎
		存储引擎
			MyIsam
				非聚集索引
					特点：不支持事务和外键约束
			Innodb
				聚集索引
					特点：支持事务和外键约束
```

