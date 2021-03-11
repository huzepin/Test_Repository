package cn.com.hzp.selfsale.selfsale.server;

import cn.com.hzp.selfsale.selfsale.bean.User;

public interface IUserService {
    /**
     */
    int create(User user);

    /**
     * 根据用户名删除用户
     *
     */
    void deleteByName(User user);

    /**
     * 获取用户总数
     * @return
     */
    Integer getUsersCount();

    /**
     * 删除所有用户
     */
    void deleteAllUsers();
}
