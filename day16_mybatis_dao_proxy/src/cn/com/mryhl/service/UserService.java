package cn.com.mryhl.service;


import cn.com.mryhl.domain.User;
import cn.com.mryhl.mapper.UserMapper;
import cn.com.mryhl.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class UserService {
    private SqlSession sqlSession;
    private UserMapper userMapper;
    /**
     * 提取共同的内容
     */
    @Before
    public void before(){
        // 获取sqlSession
        sqlSession = MyBatisUtils.openSession();
        // 创建UserMapper代理对象
        userMapper = sqlSession.getMapper(UserMapper.class);
    }
    @After
    public void after(){
        // 释放资源
        MyBatisUtils.release(sqlSession);
    }
    /**
     * 查询一条记录
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {

        User user = userMapper.findById(41);
        System.out.println(user);


    }

    /**
     * 测试新增
     */
    @Test
    public void test02() throws Exception {
        // 3.执行sql
        User user = new User();
        user.setUsername("haha");
        user.setSex("女");
        user.setBirthday(new Date());
        user.setAddress("北京");
        userMapper.save(user);

    }
    /**
     * 修改测试
     */
    @Test
    public void test03() throws Exception {
        // 执行sql
        User user = new User();
        user.setId(48);
        user.setUsername("haha");
        user.setSex("女");
        user.setBirthday(new Date());
        user.setAddress("北京");
        userMapper.update(user);
    }

    @Test
    public void test04() throws Exception {
        // 执行sql语句
        userMapper.delete(43);
    }


}
