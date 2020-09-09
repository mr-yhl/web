### 多表(绝对不用)
之前我们在映射文件中通过配置`<resultMap>、<association>、<collection>`来实现复杂关系映射。

使用注解开发后，我们可以使用`@Results、@Result，@One、@Many`注解组合完成复杂关系的配置。
> 注解多表查询,底层用的是mybatis的嵌套


### 延迟加载
* fetchType = FetchType.LAZY	表示懒加载
* fetchType = FetchType.EAGER 表示立即加载
* fetchType = FetchType.DEFAULT 表示使用全局配置
### 缓存

> 在Mapper接口中使用注解配置二级缓存即可

```java
@CacheNamespace
public interface UserMapper {...}
```