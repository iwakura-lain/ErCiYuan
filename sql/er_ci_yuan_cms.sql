/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.26-log : Database - er_ci_yuan_cms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`er_ci_yuan_cms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `er_ci_yuan_cms`;

/*Table structure for table `cms_ad` */

DROP TABLE IF EXISTS `cms_ad`;

CREATE TABLE `cms_ad` (
  `id` char(19) NOT NULL DEFAULT '' COMMENT 'ID',
  `title` varchar(20) DEFAULT '' COMMENT '标题',
  `type_id` char(19) NOT NULL COMMENT '类型ID',
  `image_url` varchar(500) NOT NULL DEFAULT '' COMMENT '图片地址',
  `color` varchar(10) DEFAULT NULL COMMENT '背景颜色',
  `link_url` varchar(500) DEFAULT '' COMMENT '链接地址',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='动漫推荐';

/*Data for the table `cms_ad` */

insert  into `cms_ad`(`id`,`title`,`type_id`,`image_url`,`color`,`link_url`,`sort`,`gmt_create`,`gmt_modified`) values ('1354380799433879553','异兽魔都','1','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/ad/2021/01/27/1e4eae27-e1cb-4eb6-a3e9-d257a9e27ffd.jpg',NULL,'http://localhost:3000/anime/1353342987448139778',10,'2021-01-27 18:48:40','2021-01-27 20:14:12'),('1354385330267860993','奇蛋物语','1','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/ad/2021/01/27/0f782282-1276-4139-9473-aae07fc86302.jpg',NULL,'http://localhost:3000/anime/1350309157980160002',8,'2021-01-27 19:06:41','2021-01-27 19:59:57'),('1354811418064261122','《空之境界》','1','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/ad/2021/01/28/45feacf1-fd16-4bdf-9069-617725921a47.jpg',NULL,'http://localhost:3000/anime/1349360033413976066',12,'2021-01-28 23:19:48','2021-01-28 23:19:48'),('1357879632432300033','FSN UBW','1','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/ad/2021/02/06/d4411a35-4905-4bea-9c60-6dcd806e143a.jpg',NULL,'http://localhost:3000/anime/1357879109499027457',150,'2021-02-06 10:31:47','2021-02-06 10:31:47');

/*Table structure for table `cms_ad_type` */

DROP TABLE IF EXISTS `cms_ad_type`;

CREATE TABLE `cms_ad_type` (
  `id` char(19) NOT NULL COMMENT 'ID',
  `title` varchar(20) NOT NULL COMMENT '标题',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='推荐位';

/*Data for the table `cms_ad_type` */

insert  into `cms_ad_type`(`id`,`title`,`gmt_create`,`gmt_modified`) values ('1','轮播图动漫','2021-01-27 13:51:38','2021-01-28 11:47:04'),('1354637125154091009','热门番剧','2021-01-28 11:47:13','2021-01-28 11:47:13'),('2','热门制作方','2021-01-27 13:51:19','2021-01-28 11:46:58');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
