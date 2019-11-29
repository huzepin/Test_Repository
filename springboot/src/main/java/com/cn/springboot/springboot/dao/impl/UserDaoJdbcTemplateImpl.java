package com.cn.springboot.springboot.dao.impl;

import com.cn.springboot.springboot.bean.User;
import com.cn.springboot.springboot.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//作用于持久层.用来表明该类是用来执行与数据库相关的操作（即dao对象），并支持自动处理数据库操作产生的异常
@Repository
public class UserDaoJdbcTemplateImpl implements UserDao {
    //自动导入依赖的bean
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public User queryUserById(String id) {
        return null;
    }

    @Override
    public List<User> queryUserAll() {
        return null;
    }

    @Override
    public int insertUser(User user) {
        String sql = "INSERT INTO t_user (userid, username, sex, age) VALUES (:userid, :username, :sex, :age)";
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("userid", user.getUserid());
        param.put("username", user.getUsername());
        param.put("sex", user.getSex());
        param.put("age", user.getAge());
        return jdbcTemplate.update(sql, param);

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(String id) {

    }


    public String selectUser() {
        String sql = "select * from t_user "; //u where u.age in (:age )
        Map<String,Object> parameters = new HashMap<String,Object>();
       // parameters.put("age", "18");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, parameters);
        return list.toString();
    }



    public String selectUser(String id) {
        String sql = "select * from t_user u where u.userid in (:id )";
        Map<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("id", id);
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, parameters);
        return list.toString();
    }

    //String sql = "select count(*) from user_table u where u.user_id in (:userids )";
}
