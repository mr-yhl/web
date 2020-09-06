## 常见的框架
Java世界中的框架非常的多，每一个框架都是为了解决某一部分或某些问题而存在的。
持久层框架：专注于解决数据持久化的框架。常用的有mybatis、hibernate、spring jdbc等等。
表现层框架：专注于解决与用户交互的框架。常见的有struts2、spring mvc等等。
全栈框架: 能在各层都给出解决方案的框架。比较著名的就是spring。

我们以企业中最常用的组合为准来学习Spring + Spring MVC + mybatis（SSM）

## mybatis介绍
### 历史
+ MyBatis本是apache的一个开源项目，名为iBatis。
+ 2010年这个项目由apache迁移到了google，并且改名为MyBatis。
+ 2013年迁移到Github。
### 简介
+ MyBatis是一款优秀的持久层框架，它不需要像JDBC那样去写复杂代码、手动设置参数、繁琐的处
理结果集
+ 它采用简单的XML配置 + 接口方法的形式实现对数据库的增删改查，使得让程序员只关注sql本身
## Mybatis快速入门
查询数据库user表的所有记录，封装到User对象中。
简单的增删改查功能
### 步骤
① 准备mybatis_db和user表
[sql](./mybatis_db.sql)
② 创建java模块，并导入相关jar包
③ 创建User实体
[实体](./src/cn/com/mryhl/domain/User.java)
④ 编写映射文件 UserMapper.xml
[映射文件](./src/cn/com/mryhl/mapper/UserMapper.xml)
⑤ 编写核心文件 SqlMapConfig.xml （mybatis-config.xml）
[核心文件](./src/SqlMapConfig.xml)
⑥ 编写测试
[测试类](src/cn/com/mryhl/service/UserMapperTest.java)

[引入了工具类](./src/cn/com/mryhl/util/MyBatisUtils.java)