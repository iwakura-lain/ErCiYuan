/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.26-log : Database - er_ci_yuan
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`er_ci_yuan` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `er_ci_yuan`;

/*Table structure for table `otaku_admin` */

DROP TABLE IF EXISTS `otaku_admin`;

CREATE TABLE `otaku_admin` (
  `id` char(19) NOT NULL COMMENT 'admin ID',
  `name` varchar(20) NOT NULL COMMENT '管理员名称',
  `intro` varchar(500) NOT NULL DEFAULT '' COMMENT '管理员简介',
  `career` varchar(500) DEFAULT NULL COMMENT '管理员签名',
  `level` int(10) unsigned NOT NULL COMMENT '头衔 1顶级老法师 2首席大法师',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `join_date` date DEFAULT NULL COMMENT '入驻时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员';

/*Data for the table `otaku_admin` */

insert  into `otaku_admin`(`id`,`name`,`intro`,`career`,`level`,`avatar`,`sort`,`join_date`,`is_deleted`,`gmt_create`,`gmt_modified`) values ('1','刘德华','毕业于师范大学数学系，热爱教育事业，执教数学思维6年有余','具备深厚的数学思维功底、丰富的小学教育经验，授课风格生动活泼，擅长用形象生动的比喻帮助理解、简单易懂的语言讲解难题，深受学生喜欢',2,'https://online-teach-file-helen.oss-cn-beijing.aliyuncs.com/avatar/2019/11/25/03.jpg',10,'2019-10-29',1,'2018-03-30 17:15:57','2019-04-28 05:02:18'),('10','唐嫣','北京师范大学法学院副教授','北京师范大学法学院副教授、清华大学法学博士。自2004年至今已有9年的司法考试培训经验。长期从事司法考试辅导，深知命题规律，了解解题技巧。内容把握准确，授课重点明确，层次分明，调理清晰，将法条法理与案例有机融合，强调综合，深入浅出。',1,'http://guli-file.oss-cn-beijing.aliyuncs.com/avatar/2019/02/27/073eb5d2-f5f4-488a-82ed-aec8a5289a5d.png',20,'2019-10-29',1,'2018-04-03 14:32:19','2019-02-22 02:01:26'),('1335972252209831937','马保国123','耗子尾汁2333333','www233333',1,'test',1,'2020-01-02',1,'2020-12-07 23:39:41','2021-01-03 00:30:18'),('1345356730879512577','2223','11111','2333',1,NULL,2,'2020-12-30',1,'2021-01-02 21:10:15','2021-01-02 21:10:15'),('1345407173106143233','12222333','123','123',1,NULL,222,'2021-01-31',1,'2021-01-03 00:30:41','2021-01-03 00:30:41'),('1345408919022931969','test1','12','21',1,NULL,1,'2021-01-16',1,'2021-01-03 00:37:37','2021-01-03 00:37:37'),('1346737742775529473','testImage','123','223',1,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/06/c7219a1b-f6b2-4edf-880b-d8e681bc95e3.jpg',3,'2021-01-01',1,'2021-01-06 16:37:54','2021-01-06 16:37:54'),('1346999681468530690','test2','121121','2222',1,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/09/490b7f9e-668a-4618-86a3-028130afcfcd.jpg',0,'2021-01-07',1,'2021-01-07 09:58:45','2021-01-09 16:51:41'),('1346999764675133441','test3','2222','2222',1,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/07/7b0325f2-db32-4a2e-ab44-143b7f459c73.jpg',0,'2021-01-14',1,'2021-01-07 09:59:05','2021-01-07 09:59:05'),('1347000559344746497','test4','33233','2222',1,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/09/4364cb53-cfcc-4d2b-beb8-b57f38ba6740.jpg',0,'2021-01-08',1,'2021-01-07 10:02:14','2021-01-09 16:52:26'),('1347000656237363201','test5','32323','22232',1,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/09/9cc8e2c4-9e19-4578-aa81-32c76c9f7bd1.jpg',0,'2021-01-13',1,'2021-01-07 10:02:37','2021-01-09 16:52:34'),('1347000726470983681','test6','2323232','3233',1,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/09/655a5803-9b00-426a-a233-2a5d411d6c9a.jpg',0,'2021-01-01',1,'2021-01-07 10:02:54','2021-01-09 16:51:26'),('1347534477160488962','伊蕾娜2','323','232',1,NULL,1,'2021-01-08',1,'2021-01-08 21:23:50','2021-01-08 21:23:50'),('1347542530912034818','235781','の3人','232',1,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/08/b280f150-4930-4e9a-933d-b97bd6b69f02.jpg',1,'2021-01-01',1,'2021-01-08 21:55:50','2021-01-08 21:55:50'),('1349672031402020866','test10','3232','23232',1,NULL,2,'2021-01-01',1,'2021-01-14 18:57:43','2021-01-14 18:57:43'),('1349672082257956866','test11','222','222',1,NULL,0,'2021-01-08',1,'2021-01-14 18:57:55','2021-01-14 18:57:55'),('1349672121495670785','test12','2222','232',1,NULL,0,'2021-01-05',1,'2021-01-14 18:58:04','2021-01-14 18:58:04'),('1349672184619945986','test13','4545','222',1,NULL,0,'2021-01-14',1,'2021-01-14 18:58:19','2021-01-14 18:58:19'),('1353332608991019009','MAPPA','2011年6月，丸山正雄在疯房子宣布退休并于东京都杉并区建立MAPPA。当时丸山正雄宣布退休时正值70岁，任命为代表董事。\n关于名字的由来：「Maruyama Animation Produce Project Association」（译为：丸山动画制作工程协会）。\n2016年4月，MAPPA社长由大冢学接任，丸山本人卸任改任会长，并成立Studio M2，原因是「自己的公司不能盈利」。\n2018年4月2日，开设宫城县仙台市的分工作室。\n另开了个名为「GRIZZLY」的工作室马甲用来专做BL番剧。\n','《进击的巨人 The Final Season》、《异兽魔都》、《狂赌之渊》、《咒术回战》',2,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/24/3eb29a1f-bb37-4fa0-b2d0-35362ec1d248.png',0,'2011-06-07',0,'2021-01-24 21:23:32','2021-01-24 21:23:32'),('1354686783284936705','J.C. STAFF','創立於1986年1月。該社以將輕小說改編成動畫而著名，有一家名為JamCreation的子公司。\n其社名是ジャパン・クリエイティブ・スタッフ（Japan Creative Staff）的簡稱。暱稱JC社或節操社。 中文暫時翻譯為“日本創意員工”。\n代表取締役宮田知行，取締役社長阿部倫久（均出身於龍之子），取締役（總務擔當）渡邊雅一，製作本部長松倉友二。目前員工人數197人，總部地點位於日本東京都武藏野市境二丁目14番1號，郵政編碼 〒180-0022。','《灼眼的夏娜》、《魔法禁书目录》、《某科学的超电磁炮》、《在地下城寻求邂逅是否搞错了什么》、《零之使魔》',2,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/28/af8009f4-1291-4c2b-a317-a34c1a7b8370.png',7,'2021-01-01',0,'2021-01-28 15:04:33','2021-01-28 15:04:33'),('1357876560188473345','ufotable','ufotable的原名起源于根据一张长得像飞碟的桌子，那时社长苦于飞碟社的起名时突然在一次展览上看到一张这样的桌子，于是给动画制作公司起了这个名字。后面飞碟社富了以后从某位法国收藏家那里买了这一张桌子','《 Fate/Zero 》 《 FSN/UBW 》 《空之境界》 《噬神者》',2,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/02/06/8d957279-d108-4869-8622-d491bc54431a.jpg',2,'2021-02-06',0,'2021-02-06 10:19:35','2021-02-06 10:19:44'),('2','伊莉雅','我的 berserker 是最强的','你不是说过，做哥哥的要保护好妹妹吗……是啊。因为我是姐姐。那么，就一定要保护好弟弟 ',2,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/08/4aef9e3c-6143-497b-af50-94c9822552e5.jpg',1,'2019-10-28',1,'2018-03-30 18:28:26','2021-01-02 23:00:35'),('3','钟汉良','钟汉良钟汉良钟汉良钟汉良','中教一级职称。讲课极具亲和力。',1,'http://guli-file.oss-cn-beijing.aliyuncs.com/avatar/2019/02/26/250bab5f-bbd6-49ab-80c3-7413ce806e83.jpg',2,'2019-10-29',1,'2018-03-31 09:20:46','2019-02-22 02:01:27'),('4','周杰伦','长期从事考研政治课讲授和考研命题趋势与应试对策研究。考研辅导新锐派的代表。','政治学博士、管理学博士后，北京师范大学马克思主义学院副教授。多年来总结出了一套行之有效的应试技巧与答题方法，针对性和实用性极强，能帮助考生在轻松中应考，在激励的竞争中取得高分，脱颖而出。',1,'https://online-teach-file-helen.oss-cn-beijing.aliyuncs.com/avatar/2019/11/25/fee1e99f-8852-4da0-a256-9732e55bb608.jpg',1,'2019-10-27',1,'2018-04-03 14:13:51','2019-10-29 19:52:46'),('5','伊蕾娜','没错，就是我','旅游旅游旅游',2,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/avatar/2021/01/06/bcc2fb1d-79be-4388-8a42-e7cd6abed7f8.jpg',0,'2021-01-01',1,'2018-04-03 14:15:36','2021-01-06 21:29:53'),('6','姚晨','华东师范大学数学系硕士生导师，中国数学奥林匹克高级教练','曾参与北京市及全国多项数学活动的命题和组织工作，多次带领北京队参加高中、初中、小学的各项数学竞赛，均取得优异成绩。教学活而新，能够调动学生的学习兴趣并擅长对学生进行思维点拨，对学生学习习惯的养成和非智力因素培养有独到之处，是一位深受学生喜爱的老师。',1,'',1,'2019-10-29',1,'2018-04-01 14:19:28','2019-02-22 02:01:29'),('7','胡歌','考研政治辅导实战派专家，全国考研政治命题研究组核心成员。','法学博士，北京师范大学马克思主义学院副教授，专攻毛泽东思想概论、邓小平理论，长期从事考研辅导。出版著作两部，发表学术论文30余篇，主持国家社会科学基金项目和教育部重大课题子课题各一项，参与中央实施马克思主义理论研究和建设工程项目。',2,'',8,'2019-09-04',1,'2018-04-03 14:21:03','2019-02-22 02:01:30'),('8','谢娜','资深课程设计专家，专注10年AACTP美国培训协会认证导师','十年课程研发和培训咨询经验，曾任国企人力资源经理、大型外企培训经理，负责企业大学和培训体系搭建；曾任专业培训机构高级顾问、研发部总监，为包括广东移动、东莞移动、深圳移动、南方电网、工商银行、农业银行、民生银行、邮储银行、TCL集团、清华大学继续教育学院、中天路桥、广西扬翔股份等超过200家企业提供过培训与咨询服务，并担任近50个大型项目的总负责人。',1,'',10,'2019-10-29',1,'2018-04-03 14:23:33','2019-11-23 08:38:09');

/*Table structure for table `otaku_anime` */

DROP TABLE IF EXISTS `otaku_anime`;

CREATE TABLE `otaku_anime` (
  `id` char(19) NOT NULL COMMENT '动漫ID',
  `admin_id` char(19) NOT NULL COMMENT '动漫管理员ID',
  `subject_id` char(19) NOT NULL COMMENT '动漫分类ID',
  `subject_parent_id` char(19) NOT NULL COMMENT '动漫分类父级ID',
  `title` varchar(50) NOT NULL COMMENT '动漫名称',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '动漫销售价格，设置为0则可免费观看',
  `anime_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '总集数',
  `cover` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '动漫封面图片路径',
  `buy_count` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '销售数量',
  `view_count` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '浏览数量',
  `version` bigint(20) unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `status` varchar(10) NOT NULL DEFAULT 'Draft' COMMENT '动漫状态 Draft未发布  Normal已发布',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_subject_id` (`subject_id`),
  KEY `idx_teacher_id` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='动漫';

/*Data for the table `otaku_anime` */

insert  into `otaku_anime`(`id`,`admin_id`,`subject_id`,`subject_parent_id`,`title`,`price`,`anime_num`,`cover`,`buy_count`,`view_count`,`version`,`status`,`gmt_create`,`gmt_modified`) values ('1349352735203352577','1354686783284936705','1348638268584882178','1348638268077371393','总之就是非常 gay','0.01',0,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/16/159313c9-333d-48ac-a888-c3a0771a1212.jpg',0,84,84,'Normal','2021-01-13 21:48:56','2021-02-06 22:31:29'),('1349360033413976066','1357876560188473345','1348638268584882178','1348638268077371393','空之境界','0.01',11,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/13/70718194-a957-45ce-9963-734b27f39a10.jpg',0,221,222,'Normal','2021-01-13 22:17:57','2021-02-09 00:49:29'),('1350308978929516545','1353332608991019009','1348638268584882177','1348638268077371393','进击的巨人','0.01',24,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/24/75e9eb6b-5449-401c-8835-ca03d8667b57.png',0,183,183,'Normal','2021-01-16 13:08:43','2021-02-08 16:11:53'),('1350309157980160002','1353332608991019009','1348638268584882177','1348638268077371393','奇蛋物语','0.01',5,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/02/05/69de118e-ec5c-4073-8d93-e321a0ea4611.png',0,56,47,'Normal','2021-01-16 13:09:25','2021-02-05 23:55:32'),('1353342987448139778','1353332608991019009','1348638269776064513','1348638269436325889','异兽魔都','0.01',24,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/24/baf38d0f-fc28-4476-b303-0432ff1b46c0.jpg',0,56,57,'Normal','2021-01-24 22:04:47','2021-02-05 21:25:51'),('1354684655321579522','1353332608991019009','1348638269662818306','1348638269436325889','狂赌之渊','0.01',48,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/28/1ed8dd41-e05e-4f24-a40d-bc680b401c80.png',0,77,78,'Normal','2021-01-28 14:56:05','2021-02-06 22:35:22'),('1357879109499027457','1357876560188473345','1357878559491555330','1348638269436325889','Fate/Stay Night [UBW]','0.01',24,'https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/02/06/205c8d71-bb8a-4b6a-8d60-2386827eddd6.png',1,22,24,'Normal','2021-02-06 10:29:43','2021-02-08 16:11:41');

/*Table structure for table `otaku_anime_collect` */

DROP TABLE IF EXISTS `otaku_anime_collect`;

CREATE TABLE `otaku_anime_collect` (
  `id` char(19) NOT NULL COMMENT '收藏ID',
  `anime_id` char(19) NOT NULL COMMENT '管理员ID',
  `member_id` char(19) NOT NULL DEFAULT '' COMMENT '动漫ID',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='动漫收藏';

/*Data for the table `otaku_anime_collect` */

insert  into `otaku_anime_collect`(`id`,`anime_id`,`member_id`,`gmt_create`,`gmt_modified`) values ('1357174377247723521','1349360033413976066','1356490735013920770','2021-02-04 11:49:21','2021-02-04 11:49:21'),('1357187966411001857','1350309157980160002','1356490735013920770','2021-02-04 12:43:21','2021-02-04 12:43:21');

/*Table structure for table `otaku_anime_description` */

DROP TABLE IF EXISTS `otaku_anime_description`;

CREATE TABLE `otaku_anime_description` (
  `id` char(19) NOT NULL COMMENT '动漫ID',
  `description` text COMMENT '动漫简介',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='动漫简介';

/*Data for the table `otaku_anime_description` */

insert  into `otaku_anime_description`(`id`,`description`,`gmt_create`,`gmt_modified`) values ('1349024875263528962','string','2021-01-13 00:06:09','2021-01-13 00:06:09'),('1349033987229544449','string','2021-01-13 00:42:21','2021-01-13 00:42:21'),('1349244434360758274','<p>dddddd</p>','2021-01-13 14:38:36','2021-01-13 14:38:36'),('1349248742271676418','<p>323</p>','2021-01-13 14:55:43','2021-01-13 14:55:43'),('1349248975458201602','<p>233223</p>','2021-01-13 14:56:38','2021-01-13 14:56:38'),('1349305538617208834','<p>22</p>','2021-01-13 18:41:24','2021-01-13 18:41:24'),('1349318750234406913','<p>233333</p>','2021-01-13 19:33:54','2021-01-13 19:33:54'),('1349345908784820226','<p>22</p>','2021-01-13 21:21:49','2021-01-13 21:21:49'),('1349348335021928449','<p>222</p>','2021-01-13 21:31:27','2021-01-13 21:31:27'),('1349348532137439233','<p>22</p>','2021-01-13 21:32:14','2021-01-13 21:32:14'),('1349352735203352577','<p>222</p>','2021-01-13 21:48:56','2021-01-31 14:24:23'),('1349360033413976066','<p>214，赛高！！</p>','2021-01-13 22:17:57','2021-02-06 10:20:02'),('1350308978929516545','<p>莱纳，你坐啊</p>\n<ul>\n<li>1</li>\n<li>2</li>\n<li>3</li>\n<li>4</li>\n</ul>','2021-01-16 13:08:43','2021-01-30 15:20:08'),('1350309157980160002','<p>123443</p>','2021-01-16 13:09:25','2021-02-05 23:55:30'),('1353342987448139778','<p>蜥蜴人大战蘑菇</p>','2021-01-24 22:04:47','2021-02-02 20:46:00'),('1354684655321579522','<p>女主颜艺无敌</p>','2021-01-28 14:56:05','2021-01-30 15:19:45'),('1357879109499027457','<p>远坂凛&nbsp;yyds</p>','2021-02-06 10:29:43','2021-02-06 10:29:43');

/*Table structure for table `otaku_chapter` */

DROP TABLE IF EXISTS `otaku_chapter`;

CREATE TABLE `otaku_chapter` (
  `id` char(19) NOT NULL COMMENT '集数ID',
  `anime_id` char(19) NOT NULL COMMENT '动漫ID',
  `title` varchar(50) NOT NULL COMMENT '集数标题',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '显示排序',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_anime_id` (`anime_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='动漫';

/*Data for the table `otaku_chapter` */

insert  into `otaku_chapter`(`id`,`anime_id`,`title`,`sort`,`gmt_create`,`gmt_modified`) values ('1350667108263776258','1350308978929516545','第1季',0,'2021-01-17 12:51:47','2021-01-17 23:27:03'),('1356885372207648770','1354684655321579522','第一季',0,'2021-02-03 16:40:57','2021-02-03 16:40:57'),('1357879147579113473','1357879109499027457','第一季',0,'2021-02-06 10:29:52','2021-02-06 10:29:52');

/*Table structure for table `otaku_comment` */

DROP TABLE IF EXISTS `otaku_comment`;

CREATE TABLE `otaku_comment` (
  `id` char(19) NOT NULL COMMENT '管理员ID',
  `anime_id` varchar(19) NOT NULL DEFAULT '' COMMENT '动漫id',
  `admin_id` char(19) NOT NULL DEFAULT '' COMMENT '管理员id',
  `member_id` varchar(19) NOT NULL DEFAULT '' COMMENT '用户id',
  `nickname` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`anime_id`),
  KEY `idx_teacher_id` (`admin_id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论';

/*Data for the table `otaku_comment` */

/*Table structure for table `otaku_subject` */

DROP TABLE IF EXISTS `otaku_subject`;

CREATE TABLE `otaku_subject` (
  `id` char(19) NOT NULL COMMENT '动漫类别ID',
  `title` varchar(255) NOT NULL COMMENT '类别名称',
  `parent_id` char(19) NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='动漫类别';

/*Data for the table `otaku_subject` */

insert  into `otaku_subject`(`id`,`title`,`parent_id`,`sort`,`gmt_create`,`gmt_modified`) values ('1348638268077371393','连载番剧','0',0,'2021-01-11 22:29:54','2021-01-24 00:20:13'),('1348638268584882177','热血','1348638268077371393',0,'2021-01-11 22:29:54','2021-01-12 14:36:07'),('1348638268584882178','后宫','1348638268077371393',0,'2021-01-11 22:29:54','2021-01-24 00:23:53'),('1348638269436325889','完结番剧','0',0,'2021-01-11 22:29:55','2021-01-24 00:23:18'),('1348638269662818306','恋爱','1348638269436325889',0,'2021-01-11 22:29:55','2021-01-11 22:29:55'),('1348638269776064513','神魔','1348638269436325889',0,'2021-01-11 22:29:55','2021-01-11 22:29:55'),('1348638269843173378','机战','1348638269436325889',0,'2021-01-11 22:29:55','2021-01-11 22:29:55'),('1348638269960613889','后宫','1348638269436325889',0,'2021-01-11 22:29:55','2021-01-11 22:29:55'),('1349017374149275649','机战','1348638268077371393',0,'2021-01-12 23:36:20','2021-01-12 23:36:20'),('1349370372515885058','热血/机战/神魔/后宫','1348638268077371393',0,'2021-01-13 22:59:02','2021-01-13 22:59:02'),('1353003904012177410','MAD','0',0,'2021-01-23 23:37:23','2021-01-24 00:20:18'),('1353008188070080514','冷门番','0',0,'2021-01-23 23:54:24','2021-01-24 00:24:37'),('1353011020101890050','Bad Apple','1353003904012177410',0,'2021-01-24 00:05:40','2021-01-24 00:24:13'),('1353015806243155969','智斗','1353008188070080514',0,'2021-01-24 00:24:41','2021-01-24 00:24:41'),('1357878559491555330','热血','1348638269436325889',0,'2021-02-06 10:27:31','2021-02-06 10:27:31');

/*Table structure for table `otaku_video` */

DROP TABLE IF EXISTS `otaku_video`;

CREATE TABLE `otaku_video` (
  `id` char(19) NOT NULL COMMENT '视频ID',
  `anime_id` char(19) NOT NULL COMMENT '动漫ID',
  `chapter_id` char(19) NOT NULL COMMENT '集数ID',
  `title` varchar(50) NOT NULL COMMENT '节点名称',
  `video_source_id` varchar(100) DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) DEFAULT NULL COMMENT '原始文件名称',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `play_count` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '播放次数',
  `is_free` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否可以试听：0收费 1免费',
  `duration` float NOT NULL DEFAULT '0' COMMENT '视频时长（秒）',
  `status` varchar(20) NOT NULL DEFAULT 'Empty' COMMENT 'Empty未上传 Transcoding转码中  Normal正常',
  `size` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '视频源文件大小（字节）',
  `version` bigint(20) unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`anime_id`),
  KEY `idx_chapter_id` (`chapter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='动漫视频';

/*Data for the table `otaku_video` */

insert  into `otaku_video`(`id`,`anime_id`,`chapter_id`,`title`,`video_source_id`,`video_original_name`,`sort`,`play_count`,`is_free`,`duration`,`status`,`size`,`version`,`gmt_create`,`gmt_modified`) values ('1352662163484606466','1350308978929516545','1350667108263776258','11','a6fe4d5378db4b4fa5b3dc89f9b4c8f1','无职转生～到了异世界就拿出真本事～：第2话_番剧_bilibili_哔哩哔哩.mp4_20210118_202224.mp4',0,0,0,0,'Empty',0,14,'2021-01-23 00:59:26','2021-01-28 20:03:48'),('1356882065783279618','1350308978929516545','1350667108263776258','尼特竟是我自己','6d18dad9406540279a064fbb23c3093b','无职转生～到了异世界就拿出真本事～：第2话_番剧_bilibili_哔哩哔哩.mp4_20210118_202224.mp4',0,0,1,0,'Empty',0,1,'2021-02-03 16:27:49','2021-02-03 16:27:49'),('1356885431821291521','1354684655321579522','1356885372207648770','1',NULL,NULL,0,0,0,0,'Empty',0,1,'2021-02-03 16:41:11','2021-02-03 16:41:11'),('1356894790253039618','1354684655321579522','1356885372207648770','2','29776c2352ce4b01bae3b5caef9e061f','6.mp4',0,0,0,0,'Empty',0,1,'2021-02-03 17:18:23','2021-02-03 17:18:23'),('1357879267003531266','1357879109499027457','1357879147579113473','第一集','509c923e5c194211a8e8c001596f64db','无职转生～到了异世界就拿出真本事～：第2话_番剧_bilibili_哔哩哔哩.mp4_20210118_202224.mp4',0,0,0,0,'Empty',0,1,'2021-02-06 10:30:20','2021-02-06 10:30:20');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
