package cn.com.mryhl.dao;

import cn.com.mryhl.domain.User;
import cn.com.mryhl.util.DataUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.util.List;

public class UserDao {
    public List<User> findAll() {
        return DataUtils.readAll();
    }

    public void add(User user) {
        // 查找
        List<User> list = DataUtils.readAll();
        // 追加
        list.add(user);
        // 会写
        DataUtils.writeAll(list);

    }

    public void delete(String id) {
        // 查找
        List<User> list = DataUtils.readAll();
        // 遍历
        for (User user : list) {
            if (id.equals(user.getId())){
                list.remove(user);
                break;
            }
        }

        // 会写
        DataUtils.writeAll(list);
    }





    public User findById(String id) {
        // 查找
        List<User> list = DataUtils.readAll();
        // 遍历
        for (User user : list) {
            if (id.equals(user.getId())){
                return user;
            }
        }

        return null;
    }

    public void update(User newUser) {
        // 查找
        List<User> list = DataUtils.readAll();
        // 遍历
        // 2.遍历并替换
        for (User user : list) {
            if (user.getId().equals(newUser.getId())) {

                try {
                // 自动替换
                    BeanUtils.copyProperties(user, newUser);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // 会写
        DataUtils.writeAll(list);
    }

    public User findByUsername(String username) {
        // 1.先查
        List<User> list = DataUtils.readAll();
        // 2.遍历并匹配
        for (User user : list) {
            if (user.getName().equals(username)) {

                return user;
            }
        }

        return null;
    }
}
