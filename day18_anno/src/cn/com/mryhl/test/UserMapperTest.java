package cn.com.mryhl.test;

import cn.com.mryhl.domain.User;
import cn.com.mryhl.mapper.UserMapper;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class UserMapperTest extends BaseMapperTest {
    /**
     * 用户查询
     */
    @Test
    public void test01() throws Exception {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findById(41);
        System.out.println(user);
    }

    /**
     * 查询所有
     */
    @Test
    public void test02() throws Exception {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
        //System.out.println(all);
    }

    /**
     * 插入测试
     */
    @Test
    public void test03() throws Exception {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("印度小啊三");
        user.setAddress("横河");
        user.setBirthday(new Date());
        user.setSex("不详");

        userMapper.save(user);
    }

    /**
     * 修改测试
     */
    @Test
    public void test04() throws Exception {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(58);
        user.setUsername("东方萨关");
        user.setAddress("横河");
        user.setBirthday(new Date());
        user.setSex("男");

        userMapper.update(user);
    }

    /**
     * 删除
     */
    @Test
    public void test05() throws Exception {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.delete(58);
    }

}
