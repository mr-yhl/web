## 文件上传
### 1 浏览器将文件转为文本

```markdown
1. 表单提交方式为 method="post"
	<form method="post"></form>
	
2. 表单项需要提供 type="file"
	<input type="file" name="pic">
	
3. 表单的提交类型 enctype="multipart/form-data"
	<form enctype="multipart/form-data"> 
```

### 2 服务器将图片文本转为io流

> 通过：request.getInputStream()，此方法获取的整个请求体的数据，需要自己操作获取文件的io流，效率非常低

```markdown
1. 使用apache的工具类：commons-fileupload（10行代码），今天讲


2. servlet3.0 提供注解，在web综合去讲...


3. springMVC实现文件上传解析（底层：commons-fileupload） 1行代码 
```

### 代码实现
#### ①导入jar包
1	commons-fileupload-1.3.3.jar	
2	commons-io-2.6.jar	
3	hutool-all-5.2.3.jar	
#### ②编写代码
[主页面](./web/index.jsp)
[servlet](./src/cn/com/mryhl/servlet/UploadServlet.java)