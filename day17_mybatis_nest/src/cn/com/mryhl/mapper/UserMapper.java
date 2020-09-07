package cn.com.mryhl.mapper;

import cn.com.mryhl.domain.User;

public interface UserMapper {

    /**
     * 根据用户id找用户
     *
     */
    public User findById(Integer id);


    /**
     * 一对多查询
     *
     */
    public User findByIdWithOrderList(Integer id);

    /**
     * 多对多
     */
    public User findByIdWithRoleList(Integer id);
}
