## jsp基础
[脚本和注释](./web/a_jsp/demo01.jsp)

[page指令](./web/a_jsp/demo02.jsp)
[500错误](./web/a_jsp/500.jsp)

[include指令](./web/a_jsp/demo03.jsp)
[header头部文件](./web/a_jsp/header.jsp)

[taglib指令](./web/a_jsp/demo04.jsp)

## jsp指令小结
### 1.page指令
>+ contentType：指定响应体类型和MIME类型
>+ language：仅支持java语言
>+ import：导入jar包
### 2.include指令
>+ 静态包含页面资源
### 3.taglib指令
>+ 导入apache提供的jstl标签库

[内置对象](./web/a_jsp/demo05.jsp)

[JSP动作标签](./web/a_jsp/demo06.jsp)
[footer](./web/a_jsp/footer.jsp)

## 动态包含和静态包含的区别
### 静态包含(在开发中jsp页面用,性能好)
> 合并转换成一个文件,进行编译,只执行一次
> 注意:使用静态包含,多个页面变量不能重名
### 动态包含
> 转换成多个Java文件,分别编译,将结果合并输出为一个页面

[请求转发a](./web/b_forword/a.jsp)
[请求转发b](./web/b_forword/b.jsp)
## mvc模式
### jsp发展史
>+ 早期只有servlet，只能使用response输出html标签，非常麻烦。
>+ 后来有了JSP，简化了servlet开发；如果过度使用JSP，在JSP页面中写了大量的java代码和html标
   签，造成难于维护，难于分工协作的场景。
>+ 再后来为了弥补过度使用jsp的问题，我们使用servlet+jsp这套组合拳，利于分工协作。

### MVC介绍（模型视图控制器 model view controller）
```markdown
* M：model（模型）
    JavaBean（普通java类）
        1.处理业务逻辑
        2.封装实体
* V：view（视图）
    Jsp
        展示数据（动态资源）
* C：controller（控制器）
    Servlet
        1.接收请求
        2.调用模型
        3.转发视图
* 优缺点
    优点
        降低耦合性，方便维护和拓展，利于分工协作
    缺点
        使得项目架构变得复杂，对开发人员要求高
```
## EL
### 作用:用来替换jsp脚本的功能,简化java代码

[EL介绍和语法](./web/c_el/demo01.jsp)

[EL取值练习](./web/c_el/demo02.jsp)

[el表达式运算符](./web/c_el/demo03.jsp)

[补充知识](./web/c_el/demo04.jsp)

## JSTL(jsp Standard Lag Library)
替代和简化jsp中Java的代码
[c:if标签](./web/d_jstl/demo01.jsp)

[c:forEach标签](./web/d_jstl/demo02.jsp)

> 注意:在jsp中使用jstl的标签,必须引入jar包

## 三层架构
对mvc的升级.

> 表示层：又称为 web层，与浏览器（用户）进行数据交互的。
> 业务逻辑层：又称为service层，用于处理业务功能的。 
> 数据访问层：又称为dao层，与数据库进行数据交互的

### 目录结构
> * com.itheima 公司域名倒写  
> * com.itheima.dao 持久层
> * com.itheima.service 业务层
> * ccom.itheima.web 表示层
> * com.itheima.domain 实体（JavaBean）
> * com.itheima.util 工具




