## 动态sql
> 根据传入的参数不同, 需要执行的SQL的结构就会不同，这就是动态SQL。
### if和where标签 对应select
+ if用于但分支条件判断,相当于if关键字
+ where代码块中的条件都不成立的时候, where不在拼接
+ 当where代码块中的条件至少有一个成立的时候，它会帮你去掉第一个and|or


### set标签,对应update
+ 帮你去掉最后一个逗号


### foreach标签
+ 用于变量集合
+ 普通list集合
+ 普通array数组
+ 实体中list | array

### sql片段
映射文件中抽取sql片段,通过＜ｉｎｃｌｕｄｅ＞引入，**提高复用性**

[实体文件](./src/cn/com/mryhl/domain/User.java)

[映射文件](./src/cn/com/mryhl/mapper/UserMapper.xml)

[接口文件](./src/cn/com/mryhl/mapper/UserMapper.java)

[测试文件](./src/cn/com/mryhl/test/UserMapperTest.java)
