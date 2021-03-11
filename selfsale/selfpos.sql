/*
SQLyog v10.2 
MySQL - 8.0.17 : Database - selfsale
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`selfsale` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `selfsale`;

/*Table structure for table `db_code_life` */

DROP TABLE IF EXISTS `db_code_life`;

CREATE TABLE `db_code_life` (
  `code` varchar(20) NOT NULL COMMENT '激活码',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `user_id` bigint(20) NOT NULL COMMENT '操作人id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `db_code_life` */

/*Table structure for table `db_devices_actives` */

DROP TABLE IF EXISTS `db_devices_actives`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `db_devices_actives` */

/*Table structure for table `db_user` */

DROP TABLE IF EXISTS `db_user`;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `db_user` */

insert  into `db_user`(`id`,`name`,`phone`,`password`,`available`,`create_time`) values (1,'huzeping',15979978803,'/W4xI/bSmixPZf4CwKOnXg==','1','2021-02-26 14:39:38');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
