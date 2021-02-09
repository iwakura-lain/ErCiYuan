/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.26-log : Database - er_ci_yuan_ucenter
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`er_ci_yuan_ucenter` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `er_ci_yuan_ucenter`;

/*Table structure for table `ucenter_member` */

DROP TABLE IF EXISTS `ucenter_member`;

CREATE TABLE `ucenter_member` (
  `github_id` varchar(128) DEFAULT NULL COMMENT 'github id',
  `id` char(19) NOT NULL COMMENT '会员id',
  `openid` varchar(128) DEFAULT NULL COMMENT '微信openid',
  `mobile` varchar(11) DEFAULT '' COMMENT '手机号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(2) unsigned DEFAULT NULL COMMENT '性别 1 男，2 女',
  `age` tinyint(3) unsigned DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `sign` varchar(100) DEFAULT NULL COMMENT '用户签名',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  `gitee_id` varchar(128) DEFAULT NULL COMMENT 'gitee id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员表';

/*Data for the table `ucenter_member` */

insert  into `ucenter_member`(`github_id`,`id`,`openid`,`mobile`,`password`,`nickname`,`sex`,`age`,`avatar`,`sign`,`is_disabled`,`is_deleted`,`gmt_create`,`gmt_modified`,`gitee_id`) values (NULL,'1355173155426861057',NULL,'15985112705','e10adc3949ba59abbe56e057f20f883e','antigenMHC',NULL,NULL,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/09/b317673c-e06c-44cc-8f8f-8e0e9ae9cd17.jpg',NULL,0,0,'2021-01-29 23:17:13','2021-01-29 23:17:13',NULL),(NULL,'1355377258480336898',NULL,'18085165674','13f6cc6fb1af39c9d637373d816e5cbb','MADLAX',NULL,NULL,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/09/b317673c-e06c-44cc-8f8f-8e0e9ae9cd17.jpg',NULL,0,0,'2021-01-30 12:48:15','2021-01-30 12:48:15',NULL),(NULL,'1355379152367976449',NULL,'15985102705','e10adc3949ba59abbe56e057f20f883e','1234',NULL,NULL,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/09/b317673c-e06c-44cc-8f8f-8e0e9ae9cd17.jpg',NULL,0,0,'2021-01-30 12:55:46','2021-01-30 12:55:46',NULL),(NULL,'1356490735013920770',NULL,'13595052748','e10adc3949ba59abbe56e057f20f883e','new',NULL,NULL,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/ucenter/2021/02/06/07b7725f-b16c-4b9a-a23d-02b5def66f71.jpg',NULL,0,0,'2021-02-02 14:32:48','2021-02-06 01:57:22','5342263'),('46243248','1356570387585789954',NULL,'15985112706','e10adc3949ba59abbe56e057f20f883e','nickname',NULL,NULL,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/28/thumb-1920-688669.png',NULL,0,0,'2021-02-02 19:49:19','2021-02-02 19:51:02',NULL),(NULL,'1358245410100744193',NULL,'15985112707','e10adc3949ba59abbe56e057f20f883e','15985112706',NULL,NULL,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/28/thumb-1920-688669.png',NULL,0,0,'2021-02-07 10:45:15','2021-02-07 10:45:15',NULL),(NULL,'1358251389366308865',NULL,'15985112701','e10adc3949ba59abbe56e057f20f883e','179',NULL,NULL,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/28/thumb-1920-688669.png',NULL,0,0,'2021-02-07 11:09:01','2021-02-07 11:09:01',NULL),(NULL,'1358251536242446337',NULL,'15985112702','e10adc3949ba59abbe56e057f20f883e','1595',NULL,NULL,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/28/thumb-1920-688669.png',NULL,0,0,'2021-02-07 11:09:36','2021-02-07 11:09:36',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
