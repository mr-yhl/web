package cn.com.mryhl.service;

import cn.com.mryhl.domain.User;
import cn.com.mryhl.util.MyBatisUtils;
import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserMapperTest {
    /**
     * 查询所有数据
     *
     */
    @Test
    public void test01() throws Exception {
        // 加载核心文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 构建工厂对象'
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 创建会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 调用API执行sql语句
        // 参数:命名空间.id
        List<User> list = sqlSession.selectList("UserMapper.findAll");
        for (User user : list) {
            System.out.println(user);
        }
        // 释放资源
        sqlSession.close();

    }
    /**
     * 添加记录
     */
    @Test
    public void test02() throws Exception {
        // 加载核心文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 构建工厂对象'
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 创建会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 调用API执行sql语句
        // 创建实体类对象
        User user = new User();
        user.setUsername("jack");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("滨海区");
        int insert = sqlSession.insert("UserMapper.save", user);
        System.out.println(insert);
        // 释放资源
        sqlSession.commit();
        sqlSession.close();
    }
    /**
     * 修改记录
     */
    @Test
    public void test03() throws Exception {
        // 加载核心文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 构建工厂对象'
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 创建会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 调用API执行sql语句
        // 创建实体类对象
        User user = new User();
        user.setId(49);
        user.setUsername("Tom");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("滨海区");
        int update = sqlSession.update("UserMapper.update", user);
        System.out.println(update);
        // 释放资源
        sqlSession.commit();
        sqlSession.close();
    }
    /**
     * 删除记录
     */
    @Test
    public void test04() throws Exception {
        // 加载核心文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 构建工厂对象'
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 创建会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 调用API执行sql语句
        // 创建实体类对象

        int delete = sqlSession.delete("UserMapper.delete", 49);
        System.out.println(delete);
        // 释放资源
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test05() throws Exception {
        // 获取sqlSession会话
        SqlSession sqlSession = MyBatisUtils.openSession();
        // 调用API
        User user = sqlSession.selectOne("UserMapper.findById", 41);
        System.out.println(user);
        MyBatisUtils.release(sqlSession);
    }
}
