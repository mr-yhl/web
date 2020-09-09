package cn.com.mryhl.mapper;

import cn.com.mryhl.domain.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    /**
     * 根据用户id找用户
     *
     */
    @Select("SELECT * FROM `user` WHERE id = #{id};")
    public User findById(Integer id);


    /**
     * 一对多查询
     *
     */
    @Select("SELECT * FROM `user` where id = #{id};")
    @Results({
            @Result(column = "id",property = "id",id=true),
            @Result(column = "birthday",property ="birthday" ),
            @Result(column = "sex",property ="sex" ),
            @Result(column = "username",property ="username" ),
            @Result(column = "address",property ="address" ),

            @Result(property = "orderList",column = "id",many = @Many(select = "cn.com.mryhl.mapper.OrderMapper.findByUid"))
    })
    public User findByIdWithOrderList(Integer id);

}
