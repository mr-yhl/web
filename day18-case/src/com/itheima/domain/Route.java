package com.itheima.domain;

import java.io.Serializable;

public class Route implements Serializable {

    private int rid;//线路id
    private String rname;//线路名称
    private double price;//线路价格
    private String rimage;//线路图
    private String routeIntroduce;// 线路描述

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRimage() {
        return rimage;
    }

    public void setRimage(String rimage) {
        this.rimage = rimage;
    }

    public String getRouteIntroduce() {
        return routeIntroduce;
    }

    public void setRouteIntroduce(String routeIntroduce) {
        this.routeIntroduce = routeIntroduce;
    }

    @Override
    public String toString() {
        return "Route{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", price=" + price +
                ", rimage='" + rimage + '\'' +
                ", routeIntroduce='" + routeIntroduce + '\'' +
                '}';
    }
}
