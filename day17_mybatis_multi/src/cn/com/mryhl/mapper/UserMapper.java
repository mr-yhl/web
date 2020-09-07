package cn.com.mryhl.mapper;

import cn.com.mryhl.domain.User;

public interface UserMapper {
    /**
     * 一对多根据用户id,查询用户和订单信息
     */
    public User findByIdWithOrderList(Integer id);
    /**
     * 多对多根据用户id，查询用户和角色信息
     */
    public User findByIdWithRoleList(Integer id);

}
