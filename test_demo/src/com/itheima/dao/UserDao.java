package com.itheima.dao;

import com.itheima.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static List<User> list = new ArrayList<>();

    static {
        list.add(new User("1", "虞姬", "女", 28, "香港", "7766521", "7766521@qq.com"));
        list.add(new User("2", "甄姬", "女", 12, "天津", "7766541", "7766541@qq.com"));
        list.add(new User("3", "安其拉", "女", 33, "大连", "7726521", "7726521@qq.com"));
        list.add(new User("4", "蔡文姬", "女", 19, "釜山", "7736521", "7736521@qq.com"));
    }

    public List<User> findAll() {
        return list;
    }
}
