# mybatis案例练习

### 1 编程风格
+ 浏览测试：Chrome、Firefox
+ 包命名规范：公司域名倒写.模块
+ 大驼峰式命名
+ 方法命名规范：小驼峰式命名 （findAll、save、findById）
接口名上alt + enter 创建实现类


### 2.查询所有


### 3.分页查询(分页对象PageBean)

#### ① 查询数据库获取总条数
#### ② 计算总页数  Math.ceil(总记录数/每页条数)
#### ③ 查询结果集  select ... limit 开始索引,每页个数
#### ④ 高亮当前页码
