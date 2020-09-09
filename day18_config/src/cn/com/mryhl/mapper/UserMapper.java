package cn.com.mryhl.mapper;

import cn.com.mryhl.domain.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    /**
     * 根据用户id找用户
     *
     */
    @Select(" select * from user where id = #{id}")
    public User findById(Integer id);



}
