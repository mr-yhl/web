package cn.com.mryhl.d_tx;


import cn.com.mryhl.c_util.JdbcUtils;
import org.junit.Test;

import java.sql.*;

/**
 * 嵌入事务的代码
 */
public class TXDemo {
    @Test
    public void test01() {
        Connection connection = null;
        Statement statement = null;
        try {
            // 获取连接
            connection = JdbcUtils.getConnection();
            // 开启事务
            connection.setAutoCommit(false);

            // 处理业务逻辑
            statement = connection.createStatement();
            String sql = "update account set balance = balance - 100 where id = 1";
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                System.out.println("舞王扣了钱...");
            }
            // 手动给出错误
            int a = 1/0;
            sql = "update account set balance = balance + 100 where id = 2";
            i = statement.executeUpdate(sql);
            if (i > 0) {
                System.out.println("碧螺加了钱...");
            }
            // 提交事务
            connection.commit();
        } catch (Exception e) {
            // 回滚事务
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // 释放资源
            JdbcUtils.release(statement, connection);
        }
    }

}
