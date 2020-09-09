package com.itheima.service.impl;

import com.itheima.dao.RouteMapper;
import com.itheima.domain.PageBean;
import com.itheima.domain.Route;
import com.itheima.service.RouteService;
import com.itheima.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    @Override
    public List<Route> findAll() {
        // 获取RouteDao代理对象
        SqlSession sqlSession = MyBatisUtils.openSession();
        RouteMapper routeMapper = sqlSession.getMapper(RouteMapper.class);

        List<Route> list = routeMapper.findAll();

        MyBatisUtils.release(sqlSession);

        return list;
    }

    @Override
    public PageBean<Route> findByPage(Integer pageNum, Integer pageSize) {
        // 这里有7步
        // 获取代理对象
        SqlSession sqlSession = MyBatisUtils.openSession();
        RouteMapper routeMapper = sqlSession.getMapper(RouteMapper.class);

        // 获取实体对象
        PageBean<Route> pb = new PageBean<>();
        // 封装当前页和每页个数
        pb.setPageNum(pageNum);
        pb.setPageSize(pageSize);

        // 调用dao查询条数
        Integer count = routeMapper.findCount();
        pb.setTotalCount(count);

        // 计算页数
        Integer ceil = (int) Math.ceil(count * 1.0 / pageSize);
        pb.setTotalPage(ceil);

        Integer index = (pageNum -1) * pageSize;

        List<Route> list = routeMapper.findList(index, pageSize);
        pb.setList(list);


        // 需要释放资源
        MyBatisUtils.release(sqlSession);
        return pb;
    }
}
