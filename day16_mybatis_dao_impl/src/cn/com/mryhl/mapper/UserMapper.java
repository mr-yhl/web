package cn.com.mryhl.mapper;

import cn.com.mryhl.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class UserMapper {
    // 提供根据id查询的方法
    public User findById(Integer id) throws Exception{
        // 加载核心文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 构建工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 创建会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("UserMapper.findById",id);
        // 释放资源
        sqlSession.close();
        return user;
    }
}
