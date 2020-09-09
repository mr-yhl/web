package cn.com.mryhl.domain;

import java.util.Date;

/**
 * 订单实体
 */
public class Order {
    private Integer id;
    private Date ordertime;
    private Double money;

    // 一个订单从属于一个用户

    private  User user;

    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Date getOrdertime() {
        return this.ordertime;
    }

    public void setOrdertime(final Date ordertime) {
        this.ordertime = ordertime;
    }

    public Double getMoney() {
        return this.money;
    }

    public void setMoney(final Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ordertime=" + ordertime +
                ", money=" + money +
                ", user=" + user +
                '}';
    }
}
