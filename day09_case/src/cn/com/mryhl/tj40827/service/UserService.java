package cn.com.mryhl.tj40827.service;

import cn.com.mryhl.tj40827.dao.UserDao;
import cn.com.mryhl.tj40827.domain.User;

import java.util.List;

public class UserService  {

    public List<User> findAll() {
        // 调用dao查询
        UserDao userDao = new UserDao();
        return userDao.findAll();
    }
}