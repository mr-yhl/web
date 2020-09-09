package com.itheima.dao;

import com.itheima.domain.Route;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
public interface RouteMapper {

    @Select("select * from tab_route")
    List<Route> findAll();

    /**
     * 获取总条数
     * @return
     */
    @Select("select count(*) from tab_route")
    Integer findCount();


    /**
     * 获取当前页面结果集
     */
    @Select("select * from tab_route limit #{index},#{pageSize}")
    List<Route> findList(@Param("index") Integer index, @Param("pageSize") Integer pageSize);
}
