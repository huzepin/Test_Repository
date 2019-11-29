package cn.com.mybatis.dao;


import cn.com.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;


public class UserDaoTest {

    public UserDao userDao;
    public SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {
        String resource = "mybatis-config";
        // 读取配置文件
        InputStream is = Resources.getResourceAsStream(resource);
        // 构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 获取sqlSession
        sqlSession = sqlSessionFactory.openSession();
        this.userDao = new UserDaoImpl(sqlSession);

    }

    @Test
    public void queryUserById() {
        System.out.println(this.userDao.queryUserById("1"));
    }

    @Test
    public void queryUserAll() {
        List<User> userList = this.userDao.queryUserAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void insertUser() {
        User adduser = new User();
            adduser.setUserid("3");
            adduser.setUsername("胡先生");
            adduser.setAge("23");
            adduser.setSex("男");
        this.userDao.insertUser(adduser);
        this.sqlSession.commit();

    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUserid("3");
        user.setUsername("胡先生");
        user.setAge("29");
        user.setSex("男");
        this.userDao.updateUser(user);
        this.sqlSession.commit();

    }

    @Test
    public void deleteUser() {
        this.userDao.deleteUser("4");
        this.sqlSession.commit();
    }
}
