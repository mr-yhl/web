package cn.com.mryhl.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtils {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 1.加载核心文件
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            // 2.构建工厂对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            throw new RuntimeException("mybatis框架初始化失败...");
        }
    }

    // 获取sqlSession方法
    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    // 提交事务并释放资源
    public static void release(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
