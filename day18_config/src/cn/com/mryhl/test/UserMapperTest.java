package cn.com.mryhl.test;

import cn.com.mryhl.domain.User;
import cn.com.mryhl.mapper.UserMapper;
import org.junit.Test;

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
}
