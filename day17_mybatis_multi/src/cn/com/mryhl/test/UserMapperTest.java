package cn.com.mryhl.test;

import cn.com.mryhl.domain.User;
import cn.com.mryhl.mapper.UserMapper;
import org.junit.Test;

public class UserMapperTest extends BaseMapperTest {
    /**
     * 一对多测试
     */
    @Test
    public void test01() throws Exception {
        //  创建代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 根据用户ID查询
        User byIdWithOrderList = userMapper.findByIdWithOrderList(41);
        System.out.println(byIdWithOrderList);

        // 运行结果
        // User{id=41, username='老王', birthday=Mon May 27 17:47:08 CST 2019, sex='男', address='北京', orderList=[Order{id=1, ordertime=Mon May 20 02:58:02 CST 2019, money=999.5, user=null}, Order{id=3, ordertime=Sat Jun 01 21:00:02 CST 2019, money=1666.0, user=null}]}

    }

    /**
     * 多对多测试
     */
    @Test
    public void test02() throws Exception {
        // 创建代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 根据用户id查询
        User byIdWithRoleList = userMapper.findByIdWithRoleList(41);
        System.out.println(byIdWithRoleList);
        // User{id=41, username='老王', birthday=Mon May 27 17:47:08 CST 2019, sex='男', address='北京', roleList=[Role{id=1, role_name='院长', role_desc='管理整个学院'}, Role{id=2, role_name='总裁', role_desc='管理整个公司'}], orderList=null}
    }
}
