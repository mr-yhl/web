package cn.com.mryhl.service;

import cn.com.mryhl.dao.UserDao;
import cn.com.mryhl.domain.User;

import java.util.List;

public class UserServic {
    UserDao userDao = new UserDao();
    public List<User> findAll() {
        // 调用dao查询

        return  userDao.findAll();
    }

    public void add(User user) {
        userDao.add(user);
    }


    public void delete(String id) {
        userDao.delete(id);
    }

    public User findById(String id) {
        return userDao.findById(id);
    }

    public void update(User newUser) {
        userDao.update(newUser);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
