package cn.com.mryhl.f_druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

/**
 * 硬编码方式编写 druid连接池
 */
public class Demo01 {
    public static void main(String[] args) throws Exception {
        // 创建druid连接池对象
        DruidDataSource druidDataSource = new DruidDataSource();
        // 设置数据库基本四项
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/web15");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");

        // 设置容器参数
        // 初始化5个连接对象
        druidDataSource.setInitialSize(5);
        // 最大连接数10个
        druidDataSource.setMaxActive(10);
        // 空闲期保留6个连接对象
        druidDataSource.setMinIdle(6);
        // 第十一个人访问时,等待3秒
        druidDataSource.setMaxWait(3000);
        //  获取连接对象
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println("使用连接操作数据库"+connection);
        // 归还到连接池
        connection.close();
    }
}
