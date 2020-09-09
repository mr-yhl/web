package cn.com.mryhl.mapper;

import cn.com.mryhl.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    /**
     * 根据用户id找用户
     *
     */
    @Select(" select * from user where id = #{id}")
    public User findById(Integer id);


    @Select("select id as uid ,username as uname,birthday as bir , sex as gender ,address as addr from user")
    @Results({
            @Result(column = "uid",property = "id" ,id = true),
            @Result(column = "uname", property = "username"), // 相当于<result></result>
            @Result(column = "bir", property = "birthday"),
            @Result(column = "gender", property = "sex"),
            @Result(column = "addr", property = "address")
    })
    public List<User> findAll();

    @Insert("insert into user values(null,#{username},#{birthday},#{sex},#{address})")
    public void save(User user);

    @Update("update user set username = #{username},sex = #{sex},address = #{address},birthday= #{birthday} where id = #{id}")
    public void update(User user);


    @Delete("delete from user where id = #{id}")
    public void delete(Integer id);

}
