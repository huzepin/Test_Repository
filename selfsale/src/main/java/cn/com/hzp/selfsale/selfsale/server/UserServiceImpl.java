package cn.com.hzp.selfsale.selfsale.server;

import cn.com.hzp.selfsale.selfsale.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
CREATE TABLE `db_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '操作员名称',
  `phone` bigint(11) NOT NULL COMMENT '手机号',
  `password` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号密码',
  `available` char(2) NOT NULL COMMENT '是否可用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8


CREATE TABLE `db_devices_actives` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(30) NOT NULL COMMENT '激活码，唯一',
  `storeName` varchar(100) DEFAULT NULL COMMENT '店铺名称',
  `storePos` varchar(20) DEFAULT NULL COMMENT 'POS机号',
  `createDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建激活码的日期',
  `userId` varchar(20) NOT NULL COMMENT '操作人id',
  `available` char(2) NOT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8


CREATE TABLE `db_code_life` (
  `code` varchar(20) NOT NULL COMMENT '激活码',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `user_id` bigint(20) NOT NULL COMMENT '操作人id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8

*/
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;//Spring的JdbcTemplate是自动配置的，可直接使用
    public int create(User user) {
        int flag = jdbcTemplate.update("insert into db_user(name, phone,password,available) values(?, ?,?,?)", user.getName(),user.getPhone(),user.getPassword(),"1" );
        return flag;
    }

    public void deleteByName(User user) {
        jdbcTemplate.update("delete from db_user where name = ?", user.getName());
    }

    public Integer getUsersCount() {
        return jdbcTemplate.queryForObject("select count(1) from db_user", Integer.class);
    }

    public void deleteAllUsers() {
      //  jdbcTemplate.update("delete from db_user");
    }
}
