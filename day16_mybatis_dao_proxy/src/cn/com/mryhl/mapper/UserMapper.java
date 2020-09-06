package cn.com.mryhl.mapper;

import cn.com.mryhl.domain.User;

public interface UserMapper {
    // 根据id查询
    public User findById(Integer id);

    public void save(User user);

    public void update(User user);

    public void delete(Integer id);
}
