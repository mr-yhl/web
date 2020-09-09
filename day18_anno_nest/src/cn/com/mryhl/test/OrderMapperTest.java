package cn.com.mryhl.test;

import cn.com.mryhl.domain.Order;
import cn.com.mryhl.mapper.OrderMapper;
import org.junit.Test;

public class OrderMapperTest extends BaseMapperTest {
    /**
     * 一对一测试
     */
    @Test
    public void test01() throws Exception {
        //获取代理对象
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 查询
        Order order = orderMapper.findByIdWithUser(1);

        System.out.println(order);

        // Order{id=1, ordertime=Mon May 20 02:58:02 CST 2019, money=999.5, user=User{id=41, username='老王', birthday=Mon May 27 17:47:08 CST 2019, sex='男', address='北京', roleList=null, orderList=null}}
    }
}
