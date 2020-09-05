package cn.com.mryhl.a_quick;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 初体验,添加一条记录
 */
public class JdbcQuick {
    public static void main(String[] args) throws Exception {
        // 1. 注册驱动
        DriverManager.registerDriver(new Driver());
        // 2. 建立连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web15", "root", "root");
        // 3. 编写sql
        String sql = "insert into user values(null,'harley','123')";
        // 4. 获取sql执行对象
        Statement statement = connection.createStatement();
        // 5. 发送sql并返回结果
        int i = statement.executeUpdate(sql);
        // 6. 处理结果
        if (i > 0) {
            System.out.println("添加成功....");
        } else {
            System.out.println("添加失败....");
        }
        // 7. 释放资源
        statement.close();
        connection.close();
    }

}
