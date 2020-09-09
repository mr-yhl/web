## Mybatis注解
* @Insert：实现新增，代替了<insert></insert>
 
* @Update：实现更新，代替了<update></update>

* @Delete：实现删除，代替了<delete></delete>

* @Select：实现查询，代替了<select></select>

* @Result：实现结果集封装，代替了<result></result>

* @Results：可以与@Result 一起使用，封装多个结果集，代替了<resultMap></resultMap>

* @One：实现一对一结果集封装，代替了<association></association>
 
* @Many：实现一对多结果集封装，代替了<collection></collection>

### 单表注解2步  
#### ①接口  

#### ②注解
[文件](./src/cn/com/mryhl/mapper/UserMapper.java)
    增删改查、手动映射



### 总结
1. 注解开发和xml配置相比，从开发效率来说，注解编写更简单，效率更高。
2. 从可维护性来说，注解如果要修改，必须修改源码，会导致维护成本增加。xml维护性更强。
3. 对于单表的CRUD可以使用注解，对于复制查询和多表关联建议使用XML，可以混合开发