## 过滤器
当用户访问服务器资源时,过滤器将请求拦截下来，完成统一的操作

**应用场景**
> 统一网站编码、登陆验证、敏感字符过滤（拦截）

[目标资源](./web/quick.jsp)  [quickFilter过滤器](src/cn/com/mryhl/a_quick/QuickFilter.java)
[xml配置文件](./web/WEB-INF/web.xml)
## 工作原来
### 生命周期
> 指的是一个对象从生（创建）到死（销毁）的一个过程
>
> // 创建时，执行init方法
>
> public void init(FilterConfig config);
>
> // 用户访问被拦截资源时，执行doFilter方法
>
> public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain);
>
> // 销毁时，执行destroy方法
>
> public void destroy();

```markdown
* 创建
    服务器启动时创建filter对象,并优于servlet资源的创建
*运行
    用户每次访问被拦截资源时
*销毁
    服务器关闭时销毁
```

[目标资源](./web/show.jsp)  [LifeFilter过滤器](./src/cn/com/mryhl/b_detail/LifeFilter.java) [xml配置文件](./web/WEB-INF/web.xml)

### 拦截路径
```markdown
1. 精准匹配
    配置一个指定（/show.jsp）的拦截资源
2. 目录匹配
    配置一个指定目录（/user/*）下的资源
        /user/UserInfo
        /user/UserOrder
        /user/xxxx
3. 后缀名匹配
    配置一个指定后缀名（*.html、*.jsp、*.jpg....）的资源
4. 匹配所有
    配置拦截所有（/*）的资源

```

[拦截路径](./src/cn/com/mryhl/b_detail/UrlPatternFilter.java)


### 拦截方式
```markdown
1. 拦截外部请求(默认)
    用户通过浏览器发送请求时，进行拦截
2. 拦截内部转发
    资源a转发到资源b时，拦截
3. 过滤器可以同时配置多种拦截方式
    forward、request

```

[拦截方式](./src/cn/com/mryhl/b_detail/ModelFilter.java) [AServlet](./src/cn/com/mryhl/c_servlet/AServlet.java)  [BServlet](./src/cn/com/mryhl/c_servlet/BServlet.java)

### 过滤器链
```markdown
1. 执行顺序
    1.用户发送请求
    2.FilterA拦截请求
    3.FilterB拦截请求
    4.show.jsp 执行了
    5.FilterB响应增强
    6.FilterA响应增强
    7.服务器响应结果
2. 拦截顺序
    xml
    <filter-mapping> 谁先声明，谁先拦截
    注解
    根据类名的大小进行先后的排序，值小的先执行...
    FilterA和FilterB进行对比，所以A先执行
```

[目标资源](./web/show.jsp) [FilterA](./src/cn/com/mryhl/d_chain/FilterA.java)   [FilterB](./src/cn/com/mryhl/d_chain/FilterB.java)


## Filter案例
用户访问某论坛网站，可以对文章比赛等内容进行留言
### 用户评论
[bbs](./web/bbs.jsp)   [评论的servlet](./src/cn/com/mryhl/e_case/WordServlet.java)  

### 统一编码
[统一编码](./src/cn/com/mryhl/e_case/EncodingFilter.java)   

### 非法字符拦截
[过滤关键字](./src/word.properties)
[评论的拦截器](./src/cn/com/mryhl/e_case/WordFilter.java)

### 非法字符过滤
[getParameter方法进行增强](./src/cn/com/mryhl/e_case/MyRequest.java)
[WordProFilter](./src/cn/com/mryhl/e_case/WordProFilter.java)



