package cn.com.mryhl.service;

import cn.com.mryhl.domain.User;
import cn.com.mryhl.mapper.UserMapper;
import org.junit.Test;

public class UserService {
    @Test
    public void test01() throws Exception {
        // 调用dao
        UserMapper userMapper = new UserMapper();
        User user = userMapper.findById(42);
        System.out.println(user);

    }
}
