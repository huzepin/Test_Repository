package com.cn.springboot.springboot.dao.impl;


import com.cn.springboot.springboot.bean.User;
import com.cn.springboot.springboot.dao.UserDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserDaoImpl implements UserDao {

    public SqlSession sqlSession;

    public UserDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public User queryUserById(String id) {
        return this.sqlSession.selectOne("UserDao.queryUserById", id);
    }

    public List<User> queryUserAll() {
        return this.sqlSession.selectList("UserDao.queryUserAll");
    }

    public int insertUser(User user) {
       int type = this.sqlSession.insert("UserDao.insertUser", user);
        if (type > 0)this.sqlSession.commit();
        return type;
    }

    public void updateUser(User user) {
        this.sqlSession.update("UserDao.updateUser", user);
    }

    public void deleteUser(String id) {
        this.sqlSession.delete("UserDao.deleteUser", id);
    }
}
