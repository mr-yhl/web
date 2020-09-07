## 多表查询
* 在数据库中表建立关系：通过主外键关联
* 在java中实体家里关系：通过属性关联
* 在mybatis框架中把（多对一）也可以理解（一对一）
* 在订单的角度去看，一个订单只从属于一个用户

###　导入实体　（实体  订单　角色）
[user](./src/cn/com/mryhl/domain/User.java) | [order](./src/cn/com/mryhl/domain/Order.java) | [role](./src/cn/com/mryhl/domain/Role.java)

### 映射与接口


订单: [映射](./src/cn/com/mryhl/mapper/OrderMapper.xml) | [接口](./src/cn/com/mryhl/mapper/OrderMapper.java)

角色: [映射](./src/cn/com/mryhl/mapper/RoleMapper.xml) | [接口](./src/cn/com/mryhl/mapper/RoleMapper.java)

用户: [映射](./src/cn/com/mryhl/mapper/UserMapper.xml) | [接口](./src/cn/com/mryhl/mapper/UserMapper.java)

### 测试基类

[BaseMapper](./src/cn/com/mryhl/test/BaseMapperTest.java)

####　一对一配置：使用<resultMap>+<association>做配置
    association:
        property：关联的实体属性名
        javaType：关联的实体类型
#### 一对多配置：使用<resultMap>+<collection>做配置
    collection：
        property：关联的集合属性名
        ofType：关联的集合泛型类型
#### 多对多配置：使用<resultMap>+<collection>做配置
    collection：
        property：关联的集合属性名
        ofType：关联的集合泛型类型
#### 多对多的配置跟一对多很相似，难度在于SQL语句的编写。

