package cn.com.mryhl.mapper;

import cn.com.mryhl.domain.Order;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {

    /**
     * 根据订单id查询,返回订单信息和用户信息
     */
    @Select("SELECT * FROM orders where id = #{id};")
    @Results({
            @Result(column="id",property="id",id = true),
            @Result(column="ordertime",property="ordertime"),
            @Result(column="money",property="money"),
            @Result(property = "user",column = "uid" ,one = @One(select="cn.com.mryhl.mapper.UserMapper.findById"))
    })
    public Order findByIdWithUser(Integer id);

    /**
     * 根据uid查询订单列表
     */
    @Select("SELECT * FROM orders where uid = #{uid};")
    public List<Order> findByUid(Integer uid);
}
