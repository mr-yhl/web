package com.itheima.service;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;

import java.util.List;

public class UserService {

    public List<User> findAll() {
        // 调用dao查询
        UserDao userDao = new UserDao();
        // List<User> list =  userDao.findAll();
        return userDao.findAll();
    }
}
