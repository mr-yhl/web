# 接口代理开发方式
采用 Mybatis 的基于接口代理方式实现 持久层 的开发，这种方式是我们后面进入企业的主流。

基于接口代理方式的开发只需要程序员编写 Mapper 接口，Mybatis 框架会为我们动态生成实现类的对象。


[接口](./src/cn/com/mryhl/mapper/UserMapper.java)

[映射文件](./src/cn/com/mryhl/mapper/UserMapper.xml)

[测试类](./src/cn/com/mryhl/service/UserService.java)