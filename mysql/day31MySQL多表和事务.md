## 今日内容
1. 表关系

2. 多表查询【重点】

3. 多表案例

4. 事务安全【理解】

## 第一章 表关系

### 1 概述

​	现实生活中，（班级）实体与（学生）实体之间肯定是有关系的，那么我们在设计表的时候，就应该体现出（班级）表与（学生）表之间的这种关系！

​	我们把这种数据库称为<span style="color:red">关系型数据库（RDBMS）</span>，在数据库设计上，表关系分为三种：

```markdown
1. 一对多（多对一）
	应用场景：
		班级和学生、部门和员工
	解释：
		一个班级内有多名同学，多名同学都属于某一个班级

2. 多对多
	应用场景：
		学生和课程、学生和老师
	解释：
		一名同学可以选修多门课程，一门课程可以被多名同学选修
		

3. 一对一（一般情况下可以单表实现，有时候需要进行抽取）
	应用场景：
		公民和身份证号、商品和详情
	解释：
		一个公民只有一个对应的身份证号、一个身份证号只对应一个公民
```



### 2 实现

#### 2.1 一对多

> 举例：班级和学生

表描述
把一的一方称为：主表或一表
把多的一方称为：从表或多表
		建表原则：
在从表中，添加一个字段（主表_id)类型与主表的主键一致，这个字段我们称为外键，通过主外键关联，建立二张表的关系 



**sql实现**

```sql
-- 一对多设计
CREATE DATABASE web13_1;
USE web13_1;


-- 班级表（主表）
CREATE TABLE class(
  id INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(32)
);

insert  into `class`(`id`,`name`) values (1,'一班'),(2,'二班');

-- 学生表（从表）
CREATE TABLE student(
  id INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(32),
  class_id INT -- 外键字段
);
insert  into `student`(`id`,`NAME`,`class_id`) values (1,'胜多',1),(2,'舒代分',1),(3,'龚煞',2),(4,'中长佐',2);


-- 感知：根据班级信息查询学生
SELECT * FROM class c,student s WHERE c.id = s.class_id AND c.name = '天津4期';


-- 给学生表，添加外键约束
ALTER TABLE student ADD CONSTRAINT class_id_fk FOREIGN KEY(class_id) REFERENCES class(id);

-- 删除学生表的外键约束
ALTER TABLE student DROP FOREIGN KEY class_id_fk;

```





#### 2.2 多对多

> 举例：学生和课程

建表原则：
需要创建第三张表，又称为中间表，至少要有二个外键字段，分别指向各自主表的主键，通常还会把它们设为联合主键，建立二张表的多对多关系 



**sql实现**

```sql
-- 多对多设计
-- 创建第三张表,至少有二个字段,分别指向各自主表的主键,通常把这两个键设置为联合主键
-- 学生表存在
-- 创建课程表
CREATE TABLE course(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(32)
);

-- 添加字段
INSERT INTO course VALUE (1,'java');
INSERT INTO course VALUE (2,'c++');
INSERT INTO course VALUE (3,'挖掘机');
INSERT INTO course VALUE (4,'西点美食');

-- 创建中间表
CREATE TABLE sc(
	s_id INT,
	c_id INT,
	PRIMARY KEY(s_id,c_id), -- 联合主键
	CONSTRAINT s_id_fk FOREIGN KEY (s_id) REFERENCES student(id),
	CONSTRAINT c_id_fk FOREIGN KEY (c_id) REFERENCES course(id)
);

-- 插入数据
INSERT INTO sc VALUES(1,4);
INSERT INTO sc VALUES(1,1);
INSERT INTO sc VALUES(2,1);
INSERT INTO sc VALUES(2,2);
INSERT INTO sc VALUES(3,1);
INSERT INTO sc VALUES(4,3);
INSERT INTO sc VALUES(4,1);

-- 测试联合主键
INSERT INTO sc VALUES(1,4); -- Duplicate entry '1-4' for key 'PRIMARY'
-- 测试外键约束
INSERT INTO sc VALUES(1,6); -- Cannot add or update a child row: a foreign key constraint fails (`web13_1`.`sc`, CONSTRAINT `c_id_fk` FOREIGN KEY (`c_id`) REFERENCES `course` (`id`))

-- 感知:多表查询
SELECT * FROM student s INNER JOIN sc ON s.id = sc.s_id
INNER JOIN course c ON sc.c_id = c.id
WHERE s.name = '胜多';
```



#### 2.3 一对一

> ​	一般情况下，一对一其实可以设计为一张表来实现，个别字段过大，影响查询性能，我们才会拆分二张表，建立一一对应关系

> 举例：商品和详情

 **sql实现**

```sql
-- 一对一设计

-- 商品表（主表）
CREATE TABLE product(
 id INT PRIMARY KEY AUTO_INCREMENT,
 `name` VARCHAR(32)
);

insert  into `product`(`id`,`name`) values (1,'大富士苹果'),(2,'帝王香蕉'),(3,'香水菠萝');


-- 详情表（从表）
CREATE TABLE `desc`(
  id INT PRIMARY KEY AUTO_INCREMENT, -- 主键作为外键
 `name` VARCHAR(32),
 FOREIGN KEY (id) REFERENCES product(id) -- 外键约束
);
insert  into `product`(`id`,`name`) values (1,'大富士苹果'),(2,'帝王香蕉'),(3,'香水菠萝');

```



### 3 外键约束

> 功能：限定二张表有关系的数据，保证数据的正确性、有效性和完整性
>

```markdown
1. 添加外键约束（在从表中设置）
	1）创建表指定
		create table 表名(
			字段名 数据类型,
			....
			[constraint] [约束名] foreign key(外键字段) references 主表(主键字段)
		);
	2）修改表指定
		alter table 表名 add [constraint] [约束名] foreign key(外键) references 主表(主键)

2. 特点
	1）主表不能删除从表已引用的数据
	2）从表不能添加主表未拥有的数据
	3）先删除从表数据再删除主表数据
	4）先添加主表数据在添加从表数据
	5）外键字段可以为空但不能非法关联
	
3. 删除外键约束
		alter table 表名 drop foreign key 约束名;
		
4. 注意
	外键约束虽然功能强大，但是在互联网项中不会使用，我们可以通过java实现数据的校验
```





## 第二章 多表查询【重点】

在企业开发中, 我们一个业务需要的数据往往是来自多张表的, 所以这时候就需要多表联合查询。

所谓的多表联合查询就是使用一条SQL语句将多张表的数据一起查询展示出来。

**准备数据**

```sql
-- 多表查询
create database web13_2;
use web13_2;

-- 创建部门表（主表）
CREATE TABLE dept (
  id INT PRIMARY KEY AUTO_INCREMENT,
  NAME VARCHAR(20)
);

INSERT INTO dept (NAME) VALUES ('开发部'),('市场部'),('财务部'),('销售部');

-- 创建员工表（从表）
CREATE TABLE emp (
  id INT PRIMARY KEY AUTO_INCREMENT,
  NAME VARCHAR(10),
  gender CHAR(1),   -- 性别（sex）
  salary DOUBLE,   -- 工资
  join_date DATE,  -- 入职日期
  dept_id INT -- 外键字段
);

INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('孙悟空','男',7200,'2013-02-24',1);
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('猪八戒','男',3600,'2010-12-02',2);
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('唐僧','男',9000,'2008-08-08',2);
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('白骨精','女',5000,'2015-10-07',3);
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('蜘蛛精','女',4500,'2011-03-14',1);
INSERT INTO emp(NAME,gender,salary,join_date,dept_id) VALUES('沙僧','男',6666,'2017-03-04',null);
```

### 1 笛卡尔积

```markdown
* 功能
		将二张表的记录进行拼接，产生了笛卡尔积（又称为：交叉连接）
* 语法
		SELECT * FROM dept , emp;
```

```sql
-- 笛卡儿积
-- 两张表的连接是将所有信息进行拼接.查询到的记录是两张表记录的乘积
SELECT * FROM emp , dept;
```



### 2 内连接 

```markdown
* 功能
	拿A表的记录去匹配B表的记录，若匹配到就显示（查询二张表的交集）
* 语法
	1）隐式内连接
		select * from A表,B表 where 连接条件;
	2）显示内连接（推荐）
		select * from A表 [inner] join B表 on 连接条件;
```



```sql
-- 内连接
-- 缺点:存在空值不能全部查询到
-- 用A表的记录去匹配B表的记录
-- 隐式内连接
SELECT * FROM emp , dept WHERE emp.id = dept.id;

-- 显式内连接
SELECT * FROM emp e INNER JOIN dept d ON e.id = d.id;

-- 小练习:  查询猪八戒的性别，工资和所在部门名称
-- 1.确定几张表
SELECT * FROM emp e INNER JOIN dept d;
-- 2.确定连接条件
SELECT * FROM emp e INNER JOIN dept d ON e.dept_id = d.id;
-- 3.查询指定字段
SELECT e.gender,e.salary,d.name FROM emp e INNER JOIN dept d ON e.dept_id = d.id;
-- 4.确定业务条件
SELECT e.gender,e.salary,d.name FROM emp e INNER JOIN dept d ON e.dept_id = d.id WHERE e.name = '猪八戒';
```



### 3 外连接

**左外连接【推荐】** 

```markdown
* 功能
		展示A表的全部，再去匹配B表，有就展示，没有就显示NULL
* 语法
		select * from A表 left [outer] join B表 on 连接条件;
```



```sql
-- 左外连接
-- 小练习: 查询所有员工信息及对应的部门名称
SELECT * FROM emp e LEFT OUTER JOIN dept d ON e.dept_id = d.id;
-- 小练习: 查询所有部门及对应的员工信息
SELECT * FROM dept d LEFT OUTER JOIN emp e ON e.dept_id = d.id;
```



**右外连接【了解】**

 

```markdown
* 功能
		展示B表的全部，再去匹配A表，有就展示，没有就显示NULL
* 语法
		select * from A表 right [outer] join B表 on 连接条件; 
```



```sql
-- 右外连接
-- 小练习: 查询所有部门及对应的员工信息
SELECT * FROM emp e RIGHT OUTER JOIN dept d ON e.dept_id = d.id;
```

### 4 子（嵌套）查询【难度】

```sql
-- 引入:查询工资最高的员工是谁？
-- 1.先查询最高工资
SELECT MAX(salary) FROM emp;
-- 2.在根据工资作为条件查询员工基本信息
SELECT * FROM emp WHERE salary = (SELECT MAX(salary) FROM emp);
```



```markdown
* 功能
	一条sql语句的执行结果，作为另一条sql语法的一部分
* 语法
	-- 查询结果为单列单值
		SELECT MAX(salary) FROM emp;

	-- 查询结果为单列多行
		SELECT salary FROM emp;

	-- 查询结果为多列多行
		SELECT * FROM emp;
		
* 总结
	子查询结果为单列（单值或多值），通常作为条件，在where后面使用
	子查询结果多列多行，通常作为虚拟（临时）表使用
		注意：如果一个需求能通过表连接解决，就尽量不要使用子查询
```



```sql
-- 子查询
-- 第一个查询语句是第二层查询的查询条件
-- 引入:查询工资最高的员工是谁
SELECT * FROM emp WHERE salary = (SELECT MAX(salary) FROM emp);
-- 小练习1: 查询工资小于平均工资的员工有哪些？(子查询结果为单列单值)
SELECT * FROM emp WHERE salary < (SELECT AVG(salary) FROM emp);
-- 小练习2: 查询工资大于5000的员工，来自于哪些部门的名字 (子查询结果为单列多行)
SELECT * FROM dept WHERE id IN (SELECT dept_id FROM emp WHERE salary > 5000);

-- 补充：查询出：开发部和财务部员工的信息
SELECT * FROM emp WHERE dept_id IN (SELECT id FROM dept WHERE NAME IN('开发部','采购部'));


-- 小练习3: 查询出2011年以后入职的员工信息，包括部门信息 (子查询结果为多列多行)
SELECT e.name,dept.name FROM (SELECT * FROM emp WHERE join_date >= '2011-1-1') e , dept WHERE e.dept_id = dept.id;
-- 内连接完成
SELECT * FROM emp e INNER JOIN	dept d ON e.dept_id = d.id WHERE e.join_date >= '2011-1-1';

-- 能用表连接就不用子查询
```



## 第三章 多表案例

​	我们在企业开发时，根据不同的业务需求往往需要通过2张及以上的表中去查询需要的数据。其实不管是几张表的查询，都是有规律可循的。  

**准备数据**

```sql
-- 多表案例
create database web13_3;
use web13_3;

-- 部门表
CREATE TABLE dept (
  id INT PRIMARY KEY auto_increment, -- 部门id
  dname VARCHAR(50), -- 部门名称
  loc VARCHAR(50) -- 部门位置
);
-- 添加4个部门
INSERT INTO dept(id,dname,loc) VALUES 
(10,'教研部','北京'),
(20,'学工部','上海'),
(30,'销售部','广州'),
(40,'财务部','深圳');

-- 职务表
CREATE TABLE job (
  id INT PRIMARY KEY,
  jname VARCHAR(20), -- 职务名称
  description VARCHAR(50) -- 职务描述
);
-- 添加4个职务
INSERT INTO job (id, jname, description) VALUES
(1, '董事长', '管理整个公司，接单'),
(2, '经理', '管理部门员工'),
(3, '销售员', '向客人推销产品'),
(4, '文员', '使用办公软件');

-- 员工表
CREATE TABLE emp (
  id INT PRIMARY KEY, -- 员工id
  ename VARCHAR(50), -- 员工姓名
  job_id INT, -- 职务id  外键
  mgr INT , -- 上级领导
  joindate DATE, -- 入职日期
  salary DECIMAL(7,2), -- 工资 99999.99
  bonus DECIMAL(7,2), -- 奖金 99999.99
  dept_id INT, -- 所在部门编号  外键
  CONSTRAINT emp_jobid_ref_job_id_fk FOREIGN KEY (job_id) REFERENCES job (id),
  CONSTRAINT emp_deptid_ref_dept_id_fk FOREIGN KEY (dept_id) REFERENCES dept (id)
);
-- 添加员工
INSERT INTO emp(id,ename,job_id,mgr,joindate,salary,bonus,dept_id) VALUES 
(1001,'孙悟空',4,1004,'2000-12-17','8000.00',NULL,20),
(1002,'卢俊义',3,1006,'2001-02-20','16000.00','3000.00',30),
(1003,'林冲',3,1006,'2001-02-22','12500.00','5000.00',30),
(1004,'唐僧',2,1009,'2001-04-02','29750.00',NULL,20),
(1005,'李逵',4,1006,'2001-09-28','12500.00','14000.00',30),
(1006,'宋江',2,1009,'2001-05-01','28500.00',NULL,30),
(1007,'刘备',2,1009,'2001-09-01','24500.00',NULL,10),
(1008,'猪八戒',4,1004,'2007-04-19','30000.00',NULL,20),
(1009,'罗贯中',1,NULL,'2001-11-17','50000.00',NULL,10),
(1010,'吴用',3,1006,'2001-09-08','15000.00','0.00',30),
(1011,'沙僧',4,1004,'2007-05-23','11000.00',NULL,20),
(1012,'李逵',4,1006,'2001-12-03','9500.00',NULL,30),
(1013,'小白龙',4,1004,'2001-12-03','30000.00',NULL,20),
(1014,'关羽',4,1007,'2002-01-23','13000.00',NULL,10);

-- 工资等级表
CREATE TABLE salarygrade (
  grade INT PRIMARY KEY,  -- 等级
  losalary INT, -- 最低工资
  hisalary INT -- 最高工资
);
-- 添加5个工资等级
INSERT INTO salarygrade(grade,losalary,hisalary) VALUES 
(1,7000,12000),
(2,12010,14000),
(3,14010,20000),
(4,20010,30000),
(5,30010,99990);
```



```sql
-- 1 查询所有员工信息。显示员工编号，员工姓名，工资，职务名称，职务描述
-- 1. 确定几张表
SELECT * FROM emp e INNER JOIN job j;
-- 2. 确定连接条件
SELECT * FROM emp e INNER JOIN job j ON e.job_id = j.id;
-- 3. 确定显示字段
SELECT e.id,e.ename,e.salary,j.jname,j.description FROM emp e INNER JOIN job j ON e.job_id = j.id;
```



```sql
-- 2 查询所有员工信息。显示员工编号，员工姓名，工资，职务名称，职务描述，部门名称，部门位置
-- 1.确定几张表
SELECT * FROM emp e
	INNER JOIN job j
	INNER JOIN dept d;
-- 2.确定连接条件
SELECT * FROM emp e
	INNER JOIN job j ON e.job_id = j.id
	INNER JOIN dept d ON e.dept_id = d.id;
-- 3.确定显示字段
SELECT 
  e.id,
  e.ename,
  e.salary,
  j.jname,
  j.description,
  d.dname,
  d.loc 
FROM
  emp e 
  INNER JOIN job j 
    ON e.job_id = j.id 
  INNER JOIN dept d 
    ON e.dept_id = d.id ;
```



```sql
-- 3 查询所有员工信息。显示员工姓名，工资，职务名称，职务描述，部门名称，部门位置，工资等级
-- 1.确定几张表
SELECT * FROM emp e
	INNER JOIN job j
	INNER JOIN dept d
	INNER JOIN salarygrade sg;
-- 2.确定连接条件
SELECT * FROM emp e
	INNER JOIN job j ON e.job_id = j.id
	INNER JOIN dept d ON e.dept_id = d.id
	INNER JOIN salarygrade sg ON e.salary BETWEEN sg.losalary AND sg.hisalary;
-- 3.确定显示字段
SELECT  
  e.id,
  e.ename,
  e.salary,
  j.jname,
  j.description,
  d.dname,
  d.loc,
  sg.grade
FROM emp e
	INNER JOIN job j ON e.job_id = j.id
	INNER JOIN dept d ON e.dept_id = d.id
	INNER JOIN salarygrade sg ON e.salary BETWEEN sg.losalary AND sg.hisalary;
```



```sql
-- 4 查询经理的信息。显示员工姓名，工资，职务名称，职务描述，部门名称，部门位置，工资等级
SELECT  
  e.id,
  e.ename,
  e.salary,
  j.jname,
  j.description,
  d.dname,
  d.loc,
  sg.grade
FROM emp e
	INNER JOIN job j ON e.job_id = j.id
	INNER JOIN dept d ON e.dept_id = d.id
	INNER JOIN salarygrade sg ON e.salary BETWEEN sg.losalary AND sg.hisalary
	WHERE j.jname = '经理';
```



```markdown
1. 多表查询会出现笛卡尔积

2. 消除笛卡尔的规律
	连接（过滤）条件 = 表个数 -1
	
3. 编写sql步骤
	a）确定几张表
	b）确定连接条件  on
	c）确定显示字段
	d）确定业务条件  where 
```

```sql
-- 5 查询出部门编号、部门名称、部门位置、部门人数（至少三遍）
-- 1.求出每个部门人数
SELECT dept_id,COUNT(*) AS total FROM emp GROUP BY dept_id;
-- 2.把部门人数作为虚拟表关联部门表
SELECT d.id,d.dname,d.loc,e.total FROM (SELECT dept_id,COUNT(*) AS total FROM emp GROUP BY dept_id) AS e
	INNER JOIN dept d ON e.dept_id = d.id;
```

```sql
-- 如果一张表的字段存在上下级关系，这张表就可以成为自关联表（一对多的思想）

-- 查询所有员工的姓名及上级领导的姓名
SELECT e.id,e.ename,m.id,m.ename FROM emp e LEFT OUTER JOIN emp m ON e.mgr = m.id;
```





## 第四章 事务安全 TCL

### 1 概述

#### 1.1 转账案例

**准备数据**

```sql
create database web13_4;
use web13_4;
-- 创建账户表
CREATE TABLE account (
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(32),
	money DOUBLE 
);
INSERT INTO account (NAME, money) VALUES ('许幻山', 1000), ('林有有', 1000);
```

**模拟转账**

```sql
-- 许幻山扣钱（招商）
UPDATE account SET money = money - 100 WHERE id = 1;


-- 网络或机器故障...

-- 林有有加钱（建设）
UPDATE account SET money = money + 100 WHERE id = 2;
```



#### 1.2 事务解释

如果一个包含多个步骤的业务操作，被事务管理，那么这些操作要么同时成功，要么同时失败,回滚。

### 2 事务操作

#### 2.1 手动提交事务【mysql】

```markdown
1. 开启事务
	begin | start transaction

2. 提交事务
	commit

3. 回滚事务
	rollback
```

 

**模拟转账**

```sql
-- 1.开启事务
begin;

-- 2.许幻山扣钱
UPDATE account SET money = money - 100 WHERE id = 1;

-- 机器故障

-- 3.林有有加钱
UPDATE account SET money = money + 100 WHERE id = 2;

-- 4.提交事务
commit;
```

#### 2.2 自动提交事务【JDBC】

> MySQL默认情况下一条DML语句，就是一个独立的事务

```markdown
1. 查看MySQL是否开启自动提交事务
	show variables like 'autocommit';
	
2. 临时关闭自动提交
	set autocommit = off;
```

 ```mark
mysql> show variables like 'autocommit';
+---------------+-------+
| Variable_name | Value |
+---------------+-------+
| autocommit    | ON    |
+---------------+-------+
1 row in set, 1 warning (0.00 sec)

mysql> set autocommit = off;
Query OK, 0 rows affected (0.00 sec)

mysql> show variables like 'autocommit';
+---------------+-------+
| Variable_name | Value |
+---------------+-------+
| autocommit    | OFF   |
+---------------+-------+
1 row in set, 1 warning (0.00 sec)
 ```



**模拟转账**

```sql
-- 1.许幻山扣钱
UPDATE account SET money = money - 100 WHERE id = 1;

-- 只有commit时，才会同步到数据库磁盘文件
```





### 3 事务工作原理【了解】

![](https://p.pstatp.com/origin/1000f000221e184218a5e) 



### 4 保存（回滚）点

​	当事务开启后，一部分sql执行成功，添加一个保存点，后续操作报错了，回滚到保存点，保证之前的操作可以成功提交

```markdown
1. 设置保存点
		savepoint 保存点名称;
	
2. 回滚到指定保存点
		rollback to 保存点名称;
```



> 演示保存点功能

```sql
-- 1.开启事务
begin;
-- 2.许幻山扣钱 如花
UPDATE account SET money = money - 100 WHERE id = 1;
-- 3.许幻山扣钱 凤姐
UPDATE account SET money = money - 100 WHERE id = 1;

-- 保存点
savepoint ol;

-- 4.许幻山扣钱 芙蓉姐姐
UPDATE account SET money = money - 100 WHERE id = 1;

-- 机器故障..

-- 回滚到保存点
rollback to ol;

-- 提交事务
commit;


-- 5. 许幻山扣钱 乔碧萝

-- 6. 许幻山扣钱 石榴姐
```





### 5 事务特性【ACID】【面试题】

```markdown
1. 原子性：atomicity
		如果一个包含多个步骤的业务操作，被事务管理，那么这些操作要么同时成功，要么同时失败。
		
2. 一致性：consistency
		事务执行前后，保证数据完整性一致

3. 隔离性：isolation【并发】
		多个事务之间要求隔离，互不干扰

4. 持久性：durability
		事务一旦成功提交，持久化到磁盘，即使数据库出现问题也不会对数据不会产生影响
```



### 6 事务隔离性【并发】

> 建议项目二学习完毕，再回头看这个事务介绍，非常容易理解



​	多个事务之间隔离的，相互独立的。但是如果多个事务操作同一批数据，则会引发一些问题，设置不同的隔离级别就可以解决这些问题。

> 事务A、事务B  同时操作（并发）

```markdown
1. 脏读
	事务A读取到事务B中操作的数据了，如果事务B进行了回滚，这部分内容就称为脏读
	一个事务读取到另一个事务，未提交的数据就称为脏读...
	
2. 不可重复读
	事务A读取了部分数据，此时事务B也操作了这部分数据进行了修改，然后事务A再次查询这部分数据，发生不一致，这就称为不可重复读
	一个事务二次读取的内容不一致，被事务B的update影响，就称为不可重复读

3. 幻读（虚读）
	事务A和事务B同时查询数据（id：10）不存在，此时事务A添加使用id10，事务B添加使用id10，事务A成功了，事务B失败了，这种现象就称为幻读
```



#### 6.1 MySQL数据库隔离级别

| 级别 | 名字     | 隔离级别         | 脏读 | 不可重复读 | 幻读 | 数据库默认隔离级别 |
| ---- | -------- | ---------------- | ---- | ---------- | ---- | ------------------ |
| 1    | 读未提交 | read uncommitted | 是   | 是         | 是   |                    |
| 2    | 读已提交 | read committed   | 否   | 是         | 是   | Oracle和SQL Server |
| 3    | 可重复读 | repeatable read  | 否   | 否         | 是   | MySQL              |
| 4    | 串行化   | serializable     | 否   | 否         | 否   |                    |

> 考虑性能： 1 > 2 > 3 > 4
>
> 考虑安全：4 > 3 > 2 > 1
>
> 综合考虑：2 、3

#### 6.2 演示隔离级别产生的问题【课下不需要看视频】

```markdown
1. 查看当前数据库隔离级别
		show variables like '%isolation%';
2. 临时修改隔离级别
		set session transaction isolation level 级别字符串;
```

> 模拟搞出二个事务：事务A、事务B

> 演示脏读

```markdown
1. 设置事务的隔离级别为 read uncommitted
		set session transaction isolation level read uncommitted;
```

> 解决脏读（产生了不可重复读的问题）

```markdown
1. 设置事务的隔离级别为 read committed
		set session transaction isolation level read committed;
```

> 解决不可重复读

```markdown
1. 设置事务的隔离级别为 repeatable read
		set session transaction isolation level repeatable read;
```

>  解决幻读

```markdown
1. 设置事务的隔离级别为 serializable
		set session transaction isolation level serializable;
```

## 总结

```markdown
表关系
	一对多
		应用场景
			班级和学生
			部门和员工
		实例
			一个班级下面有多名学生，多名学生属于同一个班级
		建表原则
			在从表中添加一个字段（列），字段名（主表名_id）类型与主表的主键一致，这个字段称为外键，通过外键指向主表的主键，建立关联关系
	多对多
		应用场景
			老师和学生
			学生和课程
		实例
			一个学生可以选修多门课程，一门课程可以被多个学生选修
		建表原则
			多对多其实由二个一对多组成
			需要借助于第三张表（中间表），需要有二个外键字段分别指向各自的主键，通常还会作为联合主键（由二个一对多组成）
	一对一
		应用场景
			公民和身份证号
			商品和详情
		实例
			一个公民只能有一个身份证号，一个身份证号只能属于一个公民
		建表原则
			主键是外键
多表查询
	笛卡尔积
		多表中每一条记录的进行组合，又称为交叉连接
	内连接
		查询二张表的交集部分
		语法
			隐式内连接
				select ... from 左表,右表 where 连接条件;
			显示内连接
				select ... from 左表 inner join 右表 on 连接条件;
	外连接
		左外连接
			查询左表全部，再去匹配右表，有就显示，没有显示null
			语法
				select ... from 左表 left outer join 右表 on 连接条件;
		右外连接
	子查询
		子查询结果为单值
		子查询结果为单列多行
		子查询结果为多列多行
		规律
			子查询结果只要为单列，肯定在where后作为条件使用
			子查询结果只要为多列，很定在from后作为虚拟表使用
多表案例
	规律
		1。确定几张表
		2。确定连接条件
			on关键字
		3。确定显示字段
		4。确定业务条件
			where关键字
事务安全 TCL
	什么是事务：
		是指的是多个步骤的一组业务操作，要么都成功，要么都失败
	操作
		手动提交
			begin;开始事务
			commit;提交
			rollback;回滚
		自动提交
			默认
	事务原理
		临时空间
	保存点
		设置保存点
			savepoint 保存点名
		回滚保存点
			rollback to 保存点名
	面试题
		事务特性
			A
				原子性
					如果一个包含多个步骤的业务操作，被事务管理，那么这些操作要么同时成功，要么同时失败。
			C
				一致性
					事务执行前后，保证数据完整性一致
			I
				隔离性
					多个事务之间要求隔离，互不干扰
			D
				持久性
					事务一旦成功提交，持久化到磁盘，即使数据库出现问题也不会对数据不会产生影响
		隔离性会出现问题
			脏读
				一个事务读取到另一个事务，未提交的数据就称为脏读
			不可重复读
				一个事务二次读取的内容不一致，被事务B的update影响，就称为不可重复读
			幻读（虚读）
				事务A和事务B同时查询数据（id：10）不存在，此时事务A添加使用id10，事务B添加使用id10，事务A成功了，事务B失败了，这种现象就称为幻读
		数据库隔离级别
			读未提交
			读已提交
				oracle 和 sqlServer 默认
			可重复读
				MySQL 默认
			串行化
```

