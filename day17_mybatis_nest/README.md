## 嵌套查询
```java
// 三个步骤
1.先根据订单id查询订单信息
    select * from orders where id = 1;
2.根据订单信息中的uid再去查询用户信息
    select * from user where id = 41;
3.再由mybatis进行组合嵌套
```

## Mybatis加载策略 settings设置
就是在需要用到数据时才进行加载，不需要用到数据时就不加载数据。延迟加载也称懒加载。
###　懒加载 
#### 全局懒加载
在Mybatis的核心配置文件中可以使用setting标签开启全局的加载策略
```xml
<settings>
    <!--懒加载-->
    <setting name="lazyLoadingEnabled" value="true"/>
</settings>
```

#### 局部延迟加载
在Mapper映射文件中在association和collection标签中都有一个fetchType属性，通过修改它的值，可以修改局部的加载策略
fetchType : lazy(延迟加载) | eager(立即加载)
```xml
 <association fetchType="eager"></association>
 <collection fetchType="eager"></collection>
```
注意事项: 局部的优先级高于全局的


#### 触发加载
> 大家在配置了延迟加载策略后，发现即使没有调用关联对象的任何方法，但是在你调用当前对象的equals、clone、hashCode、toString方法时也会触发关联对象的查询。
```xml
<!--settings设置-->
    <settings>
        <!--开启全局延迟加载功能了-->
        <setting name="lazyLoadingEnabled" value="true"/>
        <!--关闭四个默认触发关联查询的功能，只有调用getOrderList()时才会触发关联查询-->
        <setting name="lazyLoadTriggerMethods" value=""></setting>
    </settings>
```

### Mybatis缓存
缓存是用来提高查询效率的，所有的持久层框架基本上都有缓存机制 Mybatis也提供了缓存策略
#### 一级缓存(都在内存)
**SqlSession级别缓存**默认开启
#### 二级缓存(部分在硬盘)
针对整个namespace


```markdown
1. mybatis的缓存，都不需要我们手动存储和获取数据。mybatis自动维护的。

2. 使用mybatis，如果是中小型项目，使用自带缓存的机制是可以满足需求的。如果是大型（分布式）项目，mybatis的缓存灵活性不足，需要使用第三方的缓存技术（redis）解决问题。
```
###　导入实体　（实体  订单　角色）
[user](./src/cn/com/mryhl/domain/User.java) | [order](./src/cn/com/mryhl/domain/Order.java) | [role](./src/cn/com/mryhl/domain/Role.java)

### 映射与接口


订单: [映射](./src/cn/com/mryhl/mapper/OrderMapper.xml) | [接口](./src/cn/com/mryhl/mapper/OrderMapper.java)

角色: [映射](./src/cn/com/mryhl/mapper/RoleMapper.xml) | [接口](./src/cn/com/mryhl/mapper/RoleMapper.java)

用户: [映射](./src/cn/com/mryhl/mapper/UserMapper.xml) | [接口](./src/cn/com/mryhl/mapper/UserMapper.java)

### 测试基类

[BaseMapper](./src/cn/com/mryhl/test/BaseMapperTest.java)