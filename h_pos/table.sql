/*
SQLyog v10.2 
MySQL - 8.0.17 : Database - hzp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hzp` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `hzp`;

/*Table structure for table `t_good` */

DROP TABLE IF EXISTS `t_good`;

CREATE TABLE `t_good` (
  `flowId` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(30) NOT NULL COMMENT '商品条码',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `unit` varchar(10) DEFAULT NULL COMMENT '单位',
  `originalPrice` double(10,2) unsigned NOT NULL COMMENT '原价',
  `salePrice` double(10,2) unsigned NOT NULL COMMENT '销售价',
  `quantity` double(10,2) NOT NULL COMMENT '数量',
  `isFresh` char(1) NOT NULL DEFAULT '0' COMMENT '是否是生鲜蔬菜（0：不是，1：是）',
  PRIMARY KEY (`flowId`,`no`),
  UNIQUE KEY `no` (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `t_good` */

insert  into `t_good`(`flowId`,`no`,`name`,`unit`,`originalPrice`,`salePrice`,`quantity`,`isFresh`) values (8,'2356245123','可乐','件',2.30,2.30,3.00,'1'),(16,'12','hu1','克',0.00,0.00,10.00,'1'),(17,'123','hu2','克',0.00,0.00,10.00,'1'),(18,'1234','hu3','克',0.00,0.00,10.00,'1'),(19,'12345','hu4','克',0.00,0.00,10.00,'1'),(20,'123456','hu5','克',0.00,0.00,10.00,'1');

/*Table structure for table `t_users` */

DROP TABLE IF EXISTS `t_users`;

CREATE TABLE `t_users` (
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号（不可重复）',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `types` char(2) DEFAULT '0' COMMENT '账号类型（0：默认）',
  `create_time` varchar(50) NOT NULL COMMENT '注册时间',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像路径',
  `last_login_date` varchar(50) DEFAULT NULL COMMENT '上次登录时间',
  `token` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录令牌',
  `sex` char(1) DEFAULT '0' COMMENT '性别（0：男，1：女）',
  `qq` varchar(50) DEFAULT NULL,
  `weixin` varchar(50) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_users` */

insert  into `t_users`(`phone`,`password`,`types`,`create_time`,`nickname`,`img_url`,`last_login_date`,`token`,`sex`,`qq`,`weixin`,`mail`) values ('15979978803','E10ADC3949BA59ABBE56E057F20F883E','0','2019-11-28 13:42:17','小宝','','2019-11-28 13:42:17','MTU3NDkxOTczNzcyNi0yODUwNDYzNjUxNTk3OTk3ODgwMw==','1',NULL,NULL,''),('15979978880','35AB3D16DC06743D8ACF9FB74792DC84','0','2019-11-28 13:41:47','小宝','','2019-11-28 13:41:47','MTU3NDkyMjc0MzkyOS04MjI0Njc1OTExNTk3OTk3ODg4MA==','1',NULL,NULL,'');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
