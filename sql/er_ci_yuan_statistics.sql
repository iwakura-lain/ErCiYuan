/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.26-log : Database - er_ci_yuan_statistics
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`er_ci_yuan_statistics` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `er_ci_yuan_statistics`;

/*Table structure for table `statistics_daily` */

DROP TABLE IF EXISTS `statistics_daily`;

CREATE TABLE `statistics_daily` (
  `id` char(19) NOT NULL COMMENT '主键',
  `date_calculated` varchar(20) NOT NULL COMMENT '统计日期',
  `register_num` int(11) NOT NULL DEFAULT '0' COMMENT '注册人数',
  `login_num` int(11) NOT NULL DEFAULT '0' COMMENT '登录人数',
  `video_view_num` int(11) NOT NULL DEFAULT '0' COMMENT '每日播放视频数',
  `anime_num` int(11) NOT NULL DEFAULT '0' COMMENT '每日新增视频数',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `statistics_day` (`date_calculated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站统计日数据';

/*Data for the table `statistics_daily` */

insert  into `statistics_daily`(`id`,`date_calculated`,`register_num`,`login_num`,`video_view_num`,`anime_num`,`gmt_create`,`gmt_modified`) values ('1358431103758426114','2021-01-30',2,0,0,199,'2021-02-07 23:03:08','2021-02-07 23:03:08'),('1358822906567438337','2021-02-08',0,0,0,160,'2021-02-09 01:00:01','2021-02-09 01:00:01');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
