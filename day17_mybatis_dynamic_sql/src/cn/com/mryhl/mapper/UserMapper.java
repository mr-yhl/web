package cn.com.mryhl.mapper;

import cn.com.mryhl.domain.QueryVo;
import cn.com.mryhl.domain.User;

import java.util.List;

public interface UserMapper {
    /**
     * 复习
     */
    public User findById(Integer id);


    /**
     * if和where
     */
    public List<User> findByIf(User user);


    /**
     * set标签
     */
    public  void updateIf(User user);

    /**
     * foreach标签:普通list集合
     */
    public List<User> findList(List<Integer> ids);

    /**
     * foreach标签:普通数组集合
     */
    public List<User> findArray(Integer[] ids);


    /**
     * foreach标签:实体中的List
     */
    public List<User> findQueryVo(QueryVo queryVo);



}
