package cn.com.mryhl.mapper;

import cn.com.mryhl.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 根据id查询
     */

    public User findById(Integer id);

    /**
     * 多条件查询
     */
    public List<User> findByIdAndUsername1(Integer id,String username);

    public List<User> findByIdAndUsername2(@Param("id") Integer id, @Param("username") String username);

    public List<User> findByIdAndUsername3(User user);

    /**
     * 模糊chax
     */
    public List<User> findLikeUsername1(String username);

    public List<User> findLikeUsername2(String username);

    public List<User> findLikeUsername3(String username);

    public List<User> findLikeUsername4(String username);

    public void save(User user);

}
