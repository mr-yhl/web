package cn.com.mryhl.b_crud;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 练习删除,修改记录
 */
public class CrudDemo {
    /**
     * 修改内容
     * @throws Exception
     */
    @Test
    public void test01() throws Exception{
        // 1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2.建立连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web15", "root", "root");
        // 3.编写sql
        String sql = "update user set username = 'nike' where id = 4";
        // 4.获取sql执行对象
        Statement statement = connection.createStatement();
        // 5.发送sql并返回结果
        int i = statement.executeUpdate(sql);
        // 6.处理结果
        if (i>0) {
            System.out.println("修改成功...");
        }
        else {
            System.out.println("修改失败...");
        }
        // 7.释放资源
        statement.close();
        connection.close();
    }

    /**
     * 删除一条记录
     * @throws Exception
     */
    @Test
    public void test02() throws Exception{
        // 1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2.建立连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web15", "root", "root");

        // 3.编写sql
        String sql = "delete from user where id = 4 ";
        // 4.获取sql执行对象
        Statement statement = connection.createStatement();
        // 5.发送sql并返回结果
        int i = statement.executeUpdate(sql);
        // 6.处理结果
        if (i>0) {
            System.out.println("删除成功...");
        }
        else {
            System.out.println("删除失败...");
        }
        // 7.释放资源
        statement.close();
        connection.close();
    }

    /**
     * 查询记录
     * @throws Exception
     */
    @Test
    public void test03() throws Exception{
        // 1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2.建立连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web15", "root", "root");

        // 3.编写sql
        String sql = "select * from user";
        // 4.获取sql执行对象
        Statement statement = connection.createStatement();
        // 5.发送sql并返回结果
        ResultSet resultSet = statement.executeQuery(sql);
        // 6.处理结果
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            System.out.println(id+"-"+username+"-"+password);
        }
        // 7.释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }

}
