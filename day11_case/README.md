## 用户管理小练习
实现了增删改查的功能。
三层架构实现了本案例的功能。

## JSON与Java转换

**常见的解析器**

| 工具名称    | 介绍                                                 |
| ----------- | ---------------------------------------------------- |
| Jsonlib     | Java 类库，需要导入的jar包较多                       |
| Gson        | google提供的一个简单的json转换工具                   |
| Fastjson    | alibaba技术团队提供的一个高性能的json转换工具        |
| **Jackson** | 开源免费的json转换工具，springmvc转换默认使用jackson |



#### 准备工作


> 核心转换对象

```java
ObjectMapper om = new ObjectMapper();
```





#### ① 将java对象转为json字符串【掌握】

[javatojson](./src/cn/com/mryhl/json/JavaToJson.java)




#### ② 将json字符串转为java对象

[jsontojava](./src/cn/com/mryhl/json/JsonToJava.java)

### jq提供的ajax

```markdown
* 语法
		$.ajax({
			type:请求方式 get | post,
			url:请求地址,
			data：请求携带的参数,
			success:请求成功后，处理返回的结果，
			--------------------（了解）
			dataType：预期返回的数据类型（text、json）,
			error：请求失败后，处理的结果,
			async:是否异步
				true：异步提交
				false：同步提交
		})
		
* 简化语法
	$.get(url,data,success)
			url:请求地址,
			data：请求携带的参数,
			success:请求成功后，处理返回的结果
	$.post(url,data,success)
			url:请求地址,
			data：请求携带的参数,
			success:请求成功后，处理返回的结果
```

