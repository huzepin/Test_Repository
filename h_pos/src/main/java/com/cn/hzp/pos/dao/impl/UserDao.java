package com.cn.hzp.pos.dao.impl;


import com.cn.hzp.pos.bean.User;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface UserDao {
    /**
     * 根据phone查询用户信息
     *
     * @param phone
     * @return
     */
    public User queryUserByPhone(String phone);

    /*
     根据phone 和 password 登录
     */
    public User phoneAndPw(String phone,String password);

    /*
     根据token 登录
     */
    public User token(String token);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<User> queryUserAll();

    /**
     * 新增用户
     *
     * @param user
     */
    public int insertUser(User user);

    /**
     * 更新用户信息
     *
     * @param user
     */
    public void updateUser(User user);

    /**
     * 根据id删除用户信息
     *
     * @param id
     */
    public void deleteUser(String id);
}
