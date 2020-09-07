package cn.com.mryhl.test;

import cn.com.mryhl.domain.QueryVo;
import cn.com.mryhl.domain.User;
import cn.com.mryhl.mapper.UserMapper;
import cn.com.mryhl.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试代码
 */
public class UserMapperTest {
    private SqlSession sqlSession;
    private UserMapper userMapper;
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
     *
     * 复习
     */
    @Test
    public void test01() throws Exception {
        User user = userMapper.findById(41);
        System.out.println(user);
    }


    /**
     * if和where测试
     */
    @Test
    public void test02() throws Exception {
        // 封装条件
        User user = new User();
        user.setId(41);
        user.setUsername("老王");

        // 执行查询
        List<User> userList = userMapper.findByIf(user);
        System.out.println(userList);

    }

    /**
     * set标签
     */
    @Test
    public void test03() throws Exception {
        // 封装修改内容
        User user = new User();
        user.setId(45);
        user.setUsername("东方盖花");
        // 执行更新
        userMapper.updateIf(user);


    }


    /**
     * foreach标签
     */
    @Test
    public void test04() throws Exception {
        // 普通list
        /*ArrayList<Integer> ids = new ArrayList<>();
        ids.add(41);
        ids.add(45);
        ids.add(46);

        List<User> list = userMapper.findList(ids);
        System.out.println(list);*/

        // 普通array
        /*Integer[] ids = {41,45,46};
        List<User> userList = userMapper.findArray(ids);
        System.out.println(userList);*/


        // 实体list
        QueryVo queryVo = new QueryVo();

        List<Integer> ids = new ArrayList<>();
        ids.add(41);
        ids.add(45);
        ids.add(46);
        queryVo.setIds(ids);
        List<User> list = userMapper.findQueryVo(queryVo);
        System.out.println(list);
    }
}
