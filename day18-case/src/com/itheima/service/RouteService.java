package com.itheima.service;

import com.itheima.domain.PageBean;
import com.itheima.domain.Route;

import java.util.List;

public interface RouteService {
    public List<Route> findAll();

    public PageBean<Route> findByPage(Integer pageNum,Integer pageSize);
}
