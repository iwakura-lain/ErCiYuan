/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.26-log : Database - er_ci_yuan_rbac
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`er_ci_yuan_rbac` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `er_ci_yuan_rbac`;

/*Table structure for table `acl_permission` */

DROP TABLE IF EXISTS `acl_permission`;

CREATE TABLE `acl_permission` (
  `id` char(19) NOT NULL DEFAULT '' COMMENT '编号',
  `pid` char(19) NOT NULL DEFAULT '' COMMENT '所属上级',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '类型(1:菜单,2:按钮)',
  `permission_value` varchar(50) DEFAULT NULL COMMENT '权限值',
  `path` varchar(100) DEFAULT NULL COMMENT '访问路径',
  `component` varchar(100) DEFAULT NULL COMMENT '组件路径',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态(0:禁止,1:正常)',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限';

/*Data for the table `acl_permission` */

insert  into `acl_permission`(`id`,`pid`,`name`,`type`,`permission_value`,`path`,`component`,`icon`,`status`,`is_deleted`,`gmt_create`,`gmt_modified`) values ('1','0','全部数据',0,NULL,NULL,NULL,NULL,NULL,0,'2019-11-15 17:13:06','2019-11-15 17:13:06'),('1195268474480156673','1','权限管理',1,NULL,'/acl','Layout','quanxian',NULL,0,'2019-11-15 17:13:06','2019-11-18 13:54:25'),('1195268616021139457','1195268474480156673','用户管理',1,NULL,'user/list','/acl/user/list','',NULL,0,'2019-11-15 17:13:40','2019-11-18 13:53:12'),('1195268788138598401','1195268474480156673','角色管理',1,NULL,'role/list','/acl/role/list',NULL,NULL,0,'2019-11-15 17:14:21','2019-11-15 17:14:21'),('1195268893830864898','1195268474480156673','菜单管理',1,NULL,'menu/list','/acl/menu/list',NULL,NULL,0,'2019-11-15 17:14:46','2019-11-15 17:14:46'),('1195269143060602882','1195268616021139457','查看',2,'user.list','','',NULL,NULL,0,'2019-11-15 17:15:45','2019-11-17 21:57:16'),('1195269295926206466','1195268616021139457','添加',2,'user.add','user/add','/acl/user/form',NULL,NULL,0,'2019-11-15 17:16:22','2019-11-15 17:16:22'),('1195269473479483394','1195268616021139457','修改',2,'user.update','user/update/:id','/acl/user/form',NULL,NULL,0,'2019-11-15 17:17:04','2019-11-15 17:17:04'),('1195269547269873666','1195268616021139457','删除',2,'user.remove','','',NULL,NULL,0,'2019-11-15 17:17:22','2019-11-15 17:17:22'),('1195269821262782465','1195268788138598401','修改',2,'role.update','role/update/:id','/acl/role/form',NULL,NULL,0,'2019-11-15 17:18:27','2019-11-15 17:19:53'),('1195269903542444034','1195268788138598401','查看',2,'role.list','','',NULL,NULL,0,'2019-11-15 17:18:47','2019-11-15 17:18:47'),('1195270037005197313','1195268788138598401','添加',2,'role.add','role/add','/acl/role/form',NULL,NULL,0,'2019-11-15 17:19:19','2019-11-18 11:05:42'),('1195270442602782721','1195268788138598401','删除',2,'role.remove','','',NULL,NULL,0,'2019-11-15 17:20:55','2019-11-15 17:20:55'),('1195270621548568578','1195268788138598401','角色权限',2,'role.acl','role/distribution/:id','/acl/role/roleForm',NULL,NULL,0,'2019-11-15 17:21:38','2019-11-15 17:21:38'),('1195270744097742849','1195268893830864898','查看',2,'permission.list','','',NULL,NULL,0,'2019-11-15 17:22:07','2019-11-15 17:22:07'),('1195270810560684034','1195268893830864898','添加',2,'permission.add','','',NULL,NULL,0,'2019-11-15 17:22:23','2019-11-15 17:22:23'),('1195270862100291586','1195268893830864898','修改',2,'permission.update','','',NULL,NULL,0,'2019-11-15 17:22:35','2019-11-15 17:22:35'),('1195270887933009922','1195268893830864898','删除',2,'permission.remove','','',NULL,NULL,0,'2019-11-15 17:22:41','2019-11-15 17:22:41'),('1195349439240048642','1','魔法统筹协会',1,NULL,'/admin','Layout','adminHome',NULL,0,'2019-11-15 22:34:49','2021-02-08 16:49:27'),('1195349699995734017','1195349439240048642','在职魔女',1,NULL,'list','/admin/adminList','mohoxi',NULL,0,'2019-11-15 22:35:52','2021-02-08 16:48:56'),('1195349810561781761','1195349439240048642','注册魔女',1,NULL,'create','/admin/adminForm','addAdmin',NULL,0,'2019-11-15 22:36:18','2021-02-08 16:48:46'),('1195349876252971010','1195349810561781761','添加',2,'admin.add','','',NULL,NULL,0,'2019-11-15 22:36:34','2019-11-15 22:36:34'),('1195349979797753857','1195349699995734017','查看',2,'admin.list','','','mohoxi',NULL,0,'2019-11-15 22:36:58','2019-11-15 22:36:58'),('1195350117270261762','1195349699995734017','修改',2,'admin.update','edit/:id','/admin/adminForm','addAdmin',NULL,0,'2019-11-15 22:37:31','2019-11-15 22:37:31'),('1195350188359520258','1195349699995734017','删除',2,'admin.remove','','',NULL,NULL,0,'2019-11-15 22:37:48','2019-11-15 22:37:48'),('1195350299365969922','1','动漫分类',1,NULL,'/anime-type','Layout','typeList',NULL,0,'2019-11-15 22:38:15','2019-11-15 22:38:15'),('1195350397751758850','1195350299365969922','动漫分类列表',1,NULL,'list','/animetype/animeTypelist',NULL,NULL,0,'2019-11-15 22:38:38','2021-02-08 17:07:09'),('1195350500512206850','1195350299365969922','导入动漫分类',1,NULL,'import','/animetype/importAnimeType',NULL,NULL,0,'2019-11-15 22:39:03','2019-11-15 22:39:03'),('1195350687590748161','1195350500512206850','导入',2,'subject.import','','',NULL,NULL,0,'2019-11-15 22:39:47','2019-11-15 22:39:47'),('1195350831744782337','1','动漫管理',1,NULL,'/anime','Layout','anime',NULL,0,'2019-11-15 22:40:21','2019-11-15 22:40:21'),('1195350919074385921','1195350831744782337','动漫列表',1,NULL,'list','/anime/animeList',NULL,NULL,0,'2019-11-15 22:40:42','2019-11-15 22:40:42'),('1195351020463296513','1195350831744782337','发布动漫',1,NULL,'info','/anime/animeForm',NULL,NULL,0,'2019-11-15 22:41:06','2019-11-15 22:41:06'),('1195351326706208770','1195350919074385921','编辑动漫',2,'anime.update','info/:id','/anime/animeForm',NULL,NULL,0,'2019-11-15 22:42:19','2019-11-15 22:42:19'),('1195351566221938690','1195350919074385921','编辑动漫大纲',2,'chapter.update','chapter/:id','/anime/animeForm',NULL,NULL,0,'2019-11-15 22:43:17','2019-11-15 22:43:17'),('1195351862889254913','1','统计分析',1,NULL,'/statistics','Layout','statistics',NULL,0,'2019-11-15 22:44:27','2019-11-15 22:44:27'),('1195351968841568257','1195351862889254913','生成统计',1,NULL,'create','/statistics/create',NULL,NULL,0,'2019-11-15 22:44:53','2019-11-15 22:44:53'),('1195352054917074946','1195351862889254913','统计图表',1,NULL,'chart','/statistics/chart',NULL,NULL,0,'2019-11-15 22:45:13','2019-11-15 22:45:13'),('1195352127734386690','1195352054917074946','查看',2,'daily.list','','',NULL,NULL,0,'2019-11-15 22:45:30','2019-11-15 22:45:30'),('1195352215768633346','1195351968841568257','生成',2,'daily.add','','',NULL,NULL,0,'2019-11-15 22:45:51','2019-11-15 22:45:51'),('1195352547621965825','1','CMS管理',1,NULL,'/ad','Layout','content',NULL,0,'2019-11-15 22:47:11','2019-11-18 10:51:46'),('1195352909401657346','1195353513549205505','添加',2,'ad.add','create','/ad/form',NULL,NULL,0,'2019-11-15 22:48:37','2021-02-08 15:45:35'),('1195353051395624961','1195353513549205505','修改',2,'ad.update','edit/:id','/ad/form',NULL,NULL,0,'2019-11-15 22:49:11','2021-02-08 15:45:30'),('1195353513549205505','1195352547621965825','推荐管理',1,NULL,'list','/ad/list',NULL,NULL,0,'2019-11-15 22:51:01','2021-02-08 15:26:56'),('1195353672110673921','1195353513549205505','删除',2,'ad.remove','','',NULL,NULL,0,'2019-11-15 22:51:39','2021-02-08 15:45:25'),('1195354315093282817','1195354153482555393','查看',2,'order.list','','',NULL,NULL,0,'2019-11-15 22:54:12','2019-11-15 22:54:12'),('1196301740985311234','1195268616021139457','分配角色',2,'user.assgin','user/role/:id','/acl/user/roleForm',NULL,NULL,0,'2019-11-18 13:38:56','2019-11-18 13:38:56'),('1358677885054648321','1195352547621965825','推荐位',1,NULL,'type-list','/adType/list',NULL,NULL,0,'2021-02-08 15:23:45','2021-02-08 15:27:19'),('1358683267936321538','1195353513549205505','查看',2,'ad.list','list','/ad/list',NULL,NULL,0,'2021-02-08 15:45:09','2021-02-08 15:45:18'),('1358683728592535553','1195350919074385921','查看动漫',2,'anime.list','list','/anime/animeList',NULL,NULL,0,'2021-02-08 15:46:59','2021-02-08 15:47:13'),('1358685976110333954','1195350397751758850','查看',2,'subject.list','','',NULL,NULL,0,'2021-02-08 15:55:55','2021-02-08 17:05:07'),('1358706516996947970','1195350397751758850','添加',2,'subject.add','','',NULL,NULL,0,'2021-02-08 17:17:32','2021-02-08 17:17:32'),('1358706567458619393','1195350397751758850','修改',2,'subject.update','','',NULL,NULL,0,'2021-02-08 17:17:44','2021-02-08 17:17:44'),('1358706608084647938','1195350397751758850','删除',2,'subject.remove','','',NULL,NULL,0,'2021-02-08 17:17:54','2021-02-08 17:17:54'),('1358763461795696642','1358677885054648321','查看',2,'adtype.list','','',NULL,NULL,0,'2021-02-08 21:03:49','2021-02-08 21:05:02'),('1358763587486404609','1358677885054648321','删除',2,'adtype.remove','','',NULL,NULL,0,'2021-02-08 21:04:19','2021-02-08 21:04:19'),('1358763632705196033','1358677885054648321','添加',2,'adtype.add','','',NULL,NULL,0,'2021-02-08 21:04:29','2021-02-08 21:04:54'),('1358763667962515457','1358677885054648321','更新',2,'adtype.update','','',NULL,NULL,0,'2021-02-08 21:04:38','2021-02-08 21:04:48'),('1358765597669511170','1195350919074385921','删除',2,'anime.remove','','',NULL,NULL,0,'2021-02-08 21:12:18','2021-02-08 21:12:18');

/*Table structure for table `acl_role` */

DROP TABLE IF EXISTS `acl_role`;

CREATE TABLE `acl_role` (
  `id` char(19) NOT NULL DEFAULT '' COMMENT '角色id',
  `role_name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(20) DEFAULT NULL COMMENT '角色编码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `acl_role` */

insert  into `acl_role`(`id`,`role_name`,`role_code`,`remark`,`is_deleted`,`gmt_create`,`gmt_modified`) values ('1','普通管理员',NULL,NULL,0,'2019-11-11 13:09:32','2021-02-08 15:29:23'),('1193757683205607426','课程管理员',NULL,NULL,0,'2019-11-11 13:09:45','2019-11-18 10:25:44'),('1196300996034977794','test',NULL,NULL,0,'2019-11-18 13:35:58','2019-11-18 13:35:58');

/*Table structure for table `acl_role_permission` */

DROP TABLE IF EXISTS `acl_role_permission`;

CREATE TABLE `acl_role_permission` (
  `id` char(19) NOT NULL DEFAULT '',
  `role_id` char(19) NOT NULL DEFAULT '',
  `permission_id` char(19) NOT NULL DEFAULT '',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_permission_id` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限';

/*Data for the table `acl_role_permission` */

insert  into `acl_role_permission`(`id`,`role_id`,`permission_id`,`is_deleted`,`gmt_create`,`gmt_modified`) values ('1358971507201331202','1','1195269143060602882',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525505','1','1195269903542444034',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525506','1','1195270744097742849',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525507','1','1195349979797753857',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525508','1','1358685976110333954',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525509','1','1358683728592535553',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525510','1','1195352054917074946',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525511','1','1195352127734386690',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525512','1','1358683267936321538',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525513','1','1358763461795696642',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525514','1','1',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525515','1','1195268474480156673',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525516','1','1195268616021139457',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525517','1','1195268788138598401',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525518','1','1195268893830864898',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525519','1','1195349439240048642',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507205525520','1','1195349699995734017',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507213914113','1','1195350299365969922',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507213914114','1','1195350397751758850',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507213914115','1','1195350831744782337',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507213914116','1','1195350919074385921',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507213914117','1','1195351862889254913',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507213914118','1','1195352547621965825',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507213914119','1','1195353513549205505',0,'2021-02-09 10:50:30','2021-02-09 10:50:30'),('1358971507213914120','1','1358677885054648321',0,'2021-02-09 10:50:30','2021-02-09 10:50:30');

/*Table structure for table `acl_user` */

DROP TABLE IF EXISTS `acl_user`;

CREATE TABLE `acl_user` (
  `id` char(19) NOT NULL COMMENT '会员id',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '微信openid',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `salt` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `token` varchar(100) DEFAULT NULL COMMENT '用户签名',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `acl_user` */

insert  into `acl_user`(`id`,`username`,`password`,`nick_name`,`salt`,`token`,`is_deleted`,`gmt_create`,`gmt_modified`) values ('1','admin','96e79218965eb72c92a549dd5a330112','admin','',NULL,0,'2019-11-01 10:39:47','2019-11-01 10:39:47'),('1358680248616255490','normal','e10adc3949ba59abbe56e057f20f883e','normal',NULL,NULL,0,'2021-02-08 15:33:09','2021-02-08 15:33:09'),('2','test','96e79218965eb72c92a549dd5a330112','test',NULL,NULL,0,'2019-11-01 16:36:07','2019-11-01 16:40:08');

/*Table structure for table `acl_user_role` */

DROP TABLE IF EXISTS `acl_user_role`;

CREATE TABLE `acl_user_role` (
  `id` char(19) NOT NULL DEFAULT '' COMMENT '主键id',
  `role_id` char(19) NOT NULL DEFAULT '0' COMMENT '角色id',
  `user_id` char(19) NOT NULL DEFAULT '0' COMMENT '用户id',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `acl_user_role` */

insert  into `acl_user_role`(`id`,`role_id`,`user_id`,`is_deleted`,`gmt_create`,`gmt_modified`) values ('1358680275778568194','1','1358680248616255490',0,'2021-02-08 15:33:15','2021-02-08 15:33:15');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
