package cn.com.mryhl.mapper;

import cn.com.mryhl.domain.Order;

public interface OrderMapper {

    /**
     * 根据订单id查询,返回订单信息和用户信息
     */
    public Order findByIdWithUser(Integer id);
}
