package cn.com.mryhl.service;


import cn.com.mryhl.domain.User;
import cn.com.mryhl.mapper.UserMapper;
import cn.com.mryhl.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

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

    /*
    多条件查询测试集
     */
    /**
     * 测试方式一
     */
    @Test
    public void test02() throws Exception {
        List<User> list = userMapper.findByIdAndUsername1(41, "老王");
        System.out.println(list);
    }
    /**
     * 测试方式二
     */
    @Test
    public void test03() throws Exception {
        List<User> list = userMapper.findByIdAndUsername2(41, "老王");
        System.out.println(list);
    }
    /**
     * 测试方式三
     */
    @Test
    public void test04() throws Exception {
        User user = new User();
        user.setId(41);
        user.setUsername("老王");
        List<User> list = userMapper.findByIdAndUsername3(user);
        System.out.println(list);
    }
    /*
    模糊查询
     */

    @Test
    public void test05() throws Exception {
        List<User> list = userMapper.findLikeUsername1("%王%");
        for (User user : list) {
            System.out.println(user);
        }

    }

    @Test
    public void test06() throws Exception {
        List<User> list = userMapper.findLikeUsername2("王");
        for (User user : list) {
            System.out.println(user);
        }

    }
    @Test
    public void test07() throws Exception {
        List<User> list = userMapper.findLikeUsername3("王");
        for (User user : list) {
            System.out.println(user);
        }

    }


    @Test
    public void test08() throws Exception {
        User user = new User();
        user.setUsername("lucy");
        user.setSex("女");
        user.setBirthday(new Date());
        user.setAddress("北京");
        userMapper.save(user);

        System.out.println(user.getId());

    }

}
