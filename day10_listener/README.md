## 监听器
```markdown
在我们的java程序中，有时也需要监视某些事情，一旦被监视的对象发生相应的变化，我们应该采取相应的
操作。
监听web三大域对象：HttpServletRequest、HttpSession、ServletContext
场景
历史访问次数、统计在线人数、系统启动时初始化配置信息
```

### 快速入门
#### ① xml版本
[监听快速入门](./src/cn/com/mryhl/a_listener/MyListener.java) |  [xml配置文件](./web/WEB-INF/web.xml)

#### ② 注解版本
[监听快速入门](./src/cn/com/mryhl/a_listener/MyListener.java)

### 统计在线人数
有用户使用网站,在线人数就+1;用户退出网站,在线人数就-1

[初始化人数](./src/cn/com/mryhl/b_case/InintCountPersonListener.java) |
[建立会话,统计人数](./src/cn/com/mryhl/b_case/ChangeCountPersonListener.java) |
[退出登陆](./src/cn/com/mryhl/b_case/LogoutServlet.java) |
[主页面](./web/index.jsp)