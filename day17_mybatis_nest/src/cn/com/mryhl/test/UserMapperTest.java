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


    /**
     * 测试一级缓存
     */
    @Test
    public void test03() throws Exception {
        SqlSession sqlSession = MyBatisUtils.openSession();
        // 进行第一次查询
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.findById(41);
        System.out.println(user1);

        // 如果加上清除缓存,将出现两次查询语句
        sqlSession.clearCache();

        // 第二次查询
        UserMapper mapper2 = sqlSession.getMapper(UserMapper.class);
        User user2 = mapper2.findById(41);
        System.out.println(user2);

        // 关闭sqlSession
        MyBatisUtils.release(sqlSession);

        /*
        [2020-09-07 20:09:42,277] DEBUG source.pooled.PooledDataSource  - Created connection 736778932.
        [2020-09-07 20:09:42,277] DEBUG ansaction.jdbc.JdbcTransaction  - Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@2bea5ab4]
        [2020-09-07 20:09:42,285] DEBUG yhl.mapper.UserMapper.findById  - ==>  Preparing: select * from user where id = ?
        [2020-09-07 20:09:42,344] DEBUG yhl.mapper.UserMapper.findById  - ==> Parameters: 41(Integer)
        [2020-09-07 20:09:42,368] DEBUG yhl.mapper.UserMapper.findById  - <==      Total: 1
        User{id=41, username='老王', birthday=Mon May 27 17:47:08 CST 2019, sex='男', address='北京', roleList=null, orderList=null}
        User{id=41, username='老王', birthday=Mon May 27 17:47:08 CST 2019, sex='男', address='北京', roleList=null, orderList=null}
        只执行了一次查询语句
         */
        /*
        加入清理缓存后的结果
        [2020-09-07 20:11:27,470] DEBUG source.pooled.PooledDataSource  - Created connection 736778932.
        [2020-09-07 20:11:27,471] DEBUG ansaction.jdbc.JdbcTransaction  - Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@2bea5ab4]
        [2020-09-07 20:11:27,480] DEBUG yhl.mapper.UserMapper.findById  - ==>  Preparing: select * from user where id = ?
        [2020-09-07 20:11:27,544] DEBUG yhl.mapper.UserMapper.findById  - ==> Parameters: 41(Integer)
        [2020-09-07 20:11:27,570] DEBUG yhl.mapper.UserMapper.findById  - <==      Total: 1
        User{id=41, username='老王', birthday=Mon May 27 17:47:08 CST 2019, sex='男', address='北京', roleList=null, orderList=null}
        [2020-09-07 20:11:27,571] DEBUG yhl.mapper.UserMapper.findById  - ==>  Preparing: select * from user where id = ?
        [2020-09-07 20:11:27,572] DEBUG yhl.mapper.UserMapper.findById  - ==> Parameters: 41(Integer)
        [2020-09-07 20:11:27,573] DEBUG yhl.mapper.UserMapper.findById  - <==      Total: 1
        User{id=41, username='老王', birthday=Mon May 27 17:47:08 CST 2019, sex='男', address='北京', roleList=null, orderList=null}
        [2020-09-07 20:11:27,574] DEBUG ansaction.jdbc.JdbcTransaction  - Resetting autocommit to true on JDBC Connection [com.mysql.jdbc.JDBC4Connection@2bea5ab4]
        [2020-09-07 20:11:27,574] DEBUG ansaction.jdbc.JdbcTransaction  - Closing JDBC Connection [com.mysql.jdbc.JDBC4Connection@2bea5ab4]
        [2020-09-07 20:11:27,574] DEBUG source.pooled.PooledDataSource  - Returned connection 736778932 to pool.

         */
    }


    /**
     * 测试二级缓存
     */
    @Test
    public void test04() throws Exception {
        // 模拟用户一
        SqlSession sqlSession1 = MyBatisUtils.openSession();
        // 进行第一次查询
        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        User user1 = mapper1.findById(41);
        System.out.println(user1);

        // 关闭sqlSession
        MyBatisUtils.release(sqlSession1);


        // 模拟用户二
        SqlSession sqlSession2 = MyBatisUtils.openSession();
        // 进行第一次查询
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.findById(41);
        System.out.println(user2);

        // 关闭sqlSession
        MyBatisUtils.release(sqlSession2);
        // user需要序列化
        /*
        [2020-09-07 20:23:24,775] DEBUG source.pooled.PooledDataSource  - Created connection 1848415041.
        [2020-09-07 20:23:24,775] DEBUG ansaction.jdbc.JdbcTransaction  - Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@6e2c9341]
        [2020-09-07 20:23:24,783] DEBUG yhl.mapper.UserMapper.findById  - ==>  Preparing: select * from user where id = ?
        [2020-09-07 20:23:24,846] DEBUG yhl.mapper.UserMapper.findById  - ==> Parameters: 41(Integer)
        [2020-09-07 20:23:24,876] DEBUG yhl.mapper.UserMapper.findById  - <==      Total: 1
        User{id=41, username='老王', birthday=Mon May 27 17:47:08 CST 2019, sex='男', address='北京', roleList=null, orderList=null}
        [2020-09-07 20:23:24,889] DEBUG ansaction.jdbc.JdbcTransaction  - Resetting autocommit to true on JDBC Connection [com.mysql.jdbc.JDBC4Connection@6e2c9341]
        [2020-09-07 20:23:24,889] DEBUG ansaction.jdbc.JdbcTransaction  - Closing JDBC Connection [com.mysql.jdbc.JDBC4Connection@6e2c9341]
        [2020-09-07 20:23:24,889] DEBUG source.pooled.PooledDataSource  - Returned connection 1848415041 to pool.
        [2020-09-07 20:23:24,967] DEBUG cn.com.mryhl.mapper.UserMapper  - Cache Hit Ratio [cn.com.mryhl.mapper.UserMapper]: 0.5
        User{id=41, username='老王', birthday=Mon May 27 17:47:08 CST 2019, sex='男', address='北京', roleList=null, orderList=null}

         */

    }
}
