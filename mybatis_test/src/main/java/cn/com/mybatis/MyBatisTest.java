package cn.com.mybatis;

import cn.com.mybatis.dao.UserDaoImpl;
import cn.com.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class MyBatisTest {

    public static void main(String[] args) {
        // 指定全局配置文件
       // InputStream inputStream = new FileInputStream("SqlMapConfig.xml");

        String resource = "mybatis-config";
        // 读取配置文件
        InputStream inputStream = null;
        try {
            inputStream =  Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 操作CRUD，第一个参数：指定statement，规则：命名空间+“.”+statementId
            // 第二个参数：指定传入sql的参数：这里是用户id
            User user = sqlSession.selectOne("MyMapper.selectUser", 1);


            UserDaoImpl dao = new UserDaoImpl(sqlSession);
            User adduser = new User();
            adduser.setUserid("3");
            adduser.setUsername("胡先生");
            adduser.setAge("23");
            adduser.setSex("男");
            int intdex =  dao.insertUser(adduser);

            System.out.println("新增用户="+intdex);


            List<User> data =dao.queryUserAll();
            System.out.println(data.toString());

        } finally {
            sqlSession.close();
        }

    }

}
