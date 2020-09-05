package cn.com.mryhl.f_druid;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 配置文件方式,创建druid连接池对象
 */
public class Demo02 {
    public static void main(String[] args) throws Exception {
        // 通过ClassLoader 加载src目录下的 druid.properties  （固定语法）
        InputStream in = Demo02.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        properties.load(in);
        // 获取druid连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        for (int i = 1; i <= 11; i++) {
            Connection connection = dataSource.getConnection();
            System.out.println("第"+i+"次获取连接"+connection);
            if (i == 10){
                // 归还
                connection.close();
            }
        }

    }
    /*
    第1次获取连接com.mysql.jdbc.JDBC4Connection@5c0369c4
    第2次获取连接com.mysql.jdbc.JDBC4Connection@2be94b0f
    第3次获取连接com.mysql.jdbc.JDBC4Connection@d70c109
    第4次获取连接com.mysql.jdbc.JDBC4Connection@17ed40e0
    第5次获取连接com.mysql.jdbc.JDBC4Connection@50675690
    第6次获取连接com.mysql.jdbc.JDBC4Connection@31b7dea0
    第7次获取连接com.mysql.jdbc.JDBC4Connection@3ac42916
    第8次获取连接com.mysql.jdbc.JDBC4Connection@47d384ee
    第9次获取连接com.mysql.jdbc.JDBC4Connection@2d6a9952
    第10次获取连接com.mysql.jdbc.JDBC4Connection@22a71081
    第11次获取连接com.mysql.jdbc.JDBC4Connection@22a71081
    */
}
