package cn.com.mryhl.test;

import cn.com.mryhl.domain.User;
import cn.com.mryhl.mapper.UserMapper;
import cn.com.mryhl.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class UserMapperTest extends BaseMapperTest {
    /**
     * 一对多测试
     */
    @Test
    public void test01() throws Exception {
        //  创建代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 根据用户ID查询
        User byIdWithOrderList = mapper.findByIdWithOrderList(41);
        System.out.println(byIdWithOrderList);

        // User{id=41, username='老王', birthday=Mon May 27 17:47:08 CST 2019, sex='男', address='北京', roleList=null, orderList=[Order{id=1, ordertime=Mon May 20 02:58:02 CST 2019, money=999.5, user=null}, Order{id=3, ordertime=Sat Jun 01 21:00:02 CST 2019, money=1666.0, user=null}]}
    }


}
