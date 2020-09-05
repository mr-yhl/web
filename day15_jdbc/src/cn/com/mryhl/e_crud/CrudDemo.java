package cn.com.mryhl.e_crud;

import cn.com.mryhl.c_util.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 使用PreparedSatement完成添加修改删除等操作
 */
public class CrudDemo {
    /**
     * 添加数据
     */
    @Test
    public void test01() throws Exception {
        // 建立连接
        Connection connection = JdbcUtils.getConnection();
        // 编写sql语句
        String sql = "insert into user values(null,?,?)";
        // 获取编译对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "东关塞荡");
        preparedStatement.setString(2, "412");
        // 执行sql并返回结果
        int i = preparedStatement.executeUpdate();
        // 打印处理结果
        System.out.println(i);
        // 释放资源
        JdbcUtils.release(preparedStatement, connection);

    }

    /**
     * 一次编译添加多条记录
     */
    @Test
    public void test02() throws Exception {
        // 建立连接
        Connection connection = JdbcUtils.getConnection();
        // 编写sql语句
        String sql = "insert into user values(null,?,?)";
        // 获取编译对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "东关塞荡");
        preparedStatement.setString(2, "412");
        // 执行sql并返回结果
        int i = preparedStatement.executeUpdate();
        // 打印处理结果
        System.out.println(i);
        preparedStatement.setString(1, "北山黛皋");
        preparedStatement.setString(2, "523");
        // 执行sql并返回结果
        i = preparedStatement.executeUpdate();
        // 打印处理结果
        System.out.println(i);
        // 释放资源
        JdbcUtils.release(preparedStatement, connection);
    }

    /**
     * 修改一条记录
     */
    @Test
    public void test03() throws Exception {
        // 建立连接
        Connection connection = JdbcUtils.getConnection();
        // 编写sql语句
        String sql = "update user set username = ? where id = ?";
        // 获取编译对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "东关塞荡");
        preparedStatement.setString(2, "2");
        // 执行sql并返回结果
        int i = preparedStatement.executeUpdate();
        // 打印处理结果
        System.out.println(i);
        // 释放资源
        JdbcUtils.release(preparedStatement, connection);
    }

    /**
     * 删除一条记录
     */
    @Test
    public void test04() throws Exception {
        // 建立连接
        Connection connection = JdbcUtils.getConnection();
        // 编写sql语句
        String sql = "delete from user  where id = ?";
        // 获取编译对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 2);
        // 执行sql并返回结果
        int i = preparedStatement.executeUpdate();
        // 打印处理结果
        System.out.println(i);
        // 释放资源
        JdbcUtils.release(preparedStatement, connection);


    }

    /**
     * 查询所有记录
     */
    @Test
    public void test05() throws Exception {
        // 建立连接
        Connection connection = JdbcUtils.getConnection();
        // 编写sql语句
        String sql = "select * from user";
        // 获取编译对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 执行sql并返回结果
        ResultSet resultSet = preparedStatement.executeQuery();
        // 打印处理结果
        while (resultSet.next()) {
            System.out.println(resultSet.getString("username") + "-" + resultSet.getString("password"));
        }
        // 释放资源
        JdbcUtils.release(preparedStatement, connection);


    }
}

