package cn.com.mryhl.test;


import cn.com.mryhl.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;

public class BaseMapperTest {
    protected SqlSession sqlSession;

    @Before
    public void before(){
        // 获取sqlSession
        sqlSession = MyBatisUtils.openSession();


    }
    @After
    public void after(){
        // 释放资源
        MyBatisUtils.release(sqlSession);
    }

}
