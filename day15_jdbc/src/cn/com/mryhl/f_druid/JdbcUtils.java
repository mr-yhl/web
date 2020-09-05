package cn.com.mryhl.f_druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *  连接池工具类，保证一个项目中的连接池对象只创建一次....
 */
public class JdbcUtils {
    private static DataSource dataSource;
    // 初始化连接池对象
    static {
        try {
            // 通过ClassLoader 加载src目录下的druid.properties(固定语法)
            InputStream in = Demo02.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(in);
            // 获取druid连接对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 提供获取连接池的静态方法
    public static DataSource getDataSource(){
        return dataSource;
    }
    // 提供获取连接对象的静态方法
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
    // 提供释放资源的方法（conn是归还）
    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void release(Statement statement, Connection connection) {
        release(null, statement, connection);
    }
}
