/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.26-log : Database - er_ci_yuan_trade
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`er_ci_yuan_trade` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `er_ci_yuan_trade`;

/*Table structure for table `trade_order` */

DROP TABLE IF EXISTS `trade_order`;

CREATE TABLE `trade_order` (
  `id` char(19) NOT NULL DEFAULT '',
  `order_no` varchar(20) NOT NULL DEFAULT '' COMMENT '订单号',
  `anime_id` varchar(19) NOT NULL DEFAULT '' COMMENT '动漫id',
  `anime_title` varchar(100) DEFAULT NULL COMMENT '动漫名称',
  `anime_cover` varchar(255) DEFAULT NULL COMMENT '动漫封面',
  `admin_name` varchar(20) DEFAULT NULL COMMENT '制作方名称',
  `member_id` varchar(19) NOT NULL DEFAULT '' COMMENT '会员id',
  `nickname` varchar(50) DEFAULT NULL COMMENT '会员昵称',
  `mobile` varchar(11) DEFAULT NULL COMMENT '会员手机',
  `total_fee` decimal(20,2) DEFAULT NULL COMMENT '订单金额（分）',
  `pay_type` tinyint(3) DEFAULT NULL COMMENT '支付类型（1：微信 2：支付宝）',
  `status` tinyint(3) DEFAULT NULL COMMENT '订单状态（0：未支付 1：已支付）',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_order_no` (`order_no`),
  KEY `idx_course_id` (`anime_id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单';

/*Data for the table `trade_order` */

insert  into `trade_order`(`id`,`order_no`,`anime_id`,`anime_title`,`anime_cover`,`admin_name`,`member_id`,`nickname`,`mobile`,`total_fee`,`pay_type`,`status`,`is_deleted`,`gmt_create`,`gmt_modified`) values ('1357568903850307586','20210205135703834','1349360033413976066','空之境界','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/13/70718194-a957-45ce-9963-734b27f39a10.jpg','J.C. STAFF','1356490735013920770','new','13595052748','1.00',1,1,0,'2021-02-05 13:57:04','2021-02-05 13:57:25'),('1357569297934528514','20210205135837310','1350308978929516545','进击的巨人','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/24/75e9eb6b-5449-401c-8835-ca03d8667b57.png','MAPPA','1356490735013920770','new','13595052748','1.00',1,1,0,'2021-02-05 13:58:38','2021-02-05 13:58:50'),('1357602911464271874','20210205161211234','1349352735203352577','总之就是非常 gay','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/16/159313c9-333d-48ac-a888-c3a0771a1212.jpg','J.C. STAFF','1356490735013920770','new','13595052748','1.00',1,0,1,'2021-02-05 16:12:12','2021-02-05 16:12:12'),('1357613323786203138','20210205165334047','1354684655321579522','狂赌之渊','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/28/1ed8dd41-e05e-4f24-a40d-bc680b401c80.png','MAPPA','1356490735013920770','new','13595052748','1.00',1,1,0,'2021-02-05 16:53:34','2021-02-05 17:05:02'),('1357617359935549442','20210205170936651','1353342987448139778','异兽魔都','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/24/baf38d0f-fc28-4476-b303-0432ff1b46c0.jpg','MAPPA','1356490735013920770','new','13595052748','1.00',1,1,0,'2021-02-05 17:09:37','2021-02-05 17:09:54'),('1357618123974160386','20210205171238959','1350309157980160002','奇蛋物语','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/16/43d84f9f-b458-4ec7-81b5-d73fcfd96d0d.jpg','MAPPA','1356490735013920770','new','13595052748','1.00',1,1,0,'2021-02-05 17:12:39','2021-02-05 17:18:01'),('1357680416187543553','20210205212010088','1349352735203352577','总之就是非常 gay','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/16/159313c9-333d-48ac-a888-c3a0771a1212.jpg','J.C. STAFF','1356490735013920770','new','13595052748','1.00',1,0,1,'2021-02-05 21:20:10','2021-02-05 21:20:10'),('1357680679858270210','20210205212113143','1349352735203352577','总之就是非常 gay','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/16/159313c9-333d-48ac-a888-c3a0771a1212.jpg','J.C. STAFF','1356490735013920770','new','13595052748','1.00',1,0,1,'2021-02-05 21:21:13','2021-02-05 21:21:13'),('1357681862828175362','20210205212555925','1349352735203352577','总之就是非常 gay','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/16/159313c9-333d-48ac-a888-c3a0771a1212.jpg','J.C. STAFF','1356490735013920770','new','13595052748','1.00',1,0,1,'2021-02-05 21:25:55','2021-02-05 21:25:55'),('1357683193991213058','20210205213112505','1349352735203352577','总之就是非常 gay','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/16/159313c9-333d-48ac-a888-c3a0771a1212.jpg','J.C. STAFF','1356490735013920770','new','13595052748','1.00',1,0,1,'2021-02-05 21:31:13','2021-02-05 21:31:13'),('1357685480771833858','20210205214017351','1349352735203352577','总之就是非常 gay','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/16/159313c9-333d-48ac-a888-c3a0771a1212.jpg','J.C. STAFF','1356490735013920770','new','13595052748','1.00',1,1,0,'2021-02-05 21:40:18','2021-02-05 21:42:13'),('1358107584868720642','20210207013735017','1357879109499027457','Fate/Stay Night [UBW]','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/02/06/205c8d71-bb8a-4b6a-8d60-2386827eddd6.png','ufotable','1356490735013920770','new','13595052748','1.00',1,1,0,'2021-02-07 01:37:35','2021-02-07 01:55:29'),('1358403043113938946','20210207211138568','1350308978929516545','进击的巨人','https://antigenmhc-erciyuan.oss-cn-hangzhou.aliyuncs.com/cover/2021/01/24/75e9eb6b-5449-401c-8835-ca03d8667b57.png','MAPPA','1358251536242446337','1595','15985112702','1.00',1,0,0,'2021-02-07 21:11:38','2021-02-07 21:11:38');

/*Table structure for table `trade_pay_log` */

DROP TABLE IF EXISTS `trade_pay_log`;

CREATE TABLE `trade_pay_log` (
  `id` char(19) NOT NULL DEFAULT '',
  `order_no` varchar(20) NOT NULL DEFAULT '' COMMENT '订单号',
  `pay_time` datetime DEFAULT NULL COMMENT '支付完成时间',
  `total_fee` bigint(20) DEFAULT NULL COMMENT '支付金额（分）',
  `transaction_id` varchar(30) DEFAULT NULL COMMENT '交易流水号',
  `trade_state` char(20) DEFAULT NULL COMMENT '交易状态',
  `pay_type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付类型（1：微信 2：支付宝）',
  `attr` text COMMENT '其他属性',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付日志表';

/*Data for the table `trade_pay_log` */

insert  into `trade_pay_log`(`id`,`order_no`,`pay_time`,`total_fee`,`transaction_id`,`trade_state`,`pay_type`,`attr`,`is_deleted`,`gmt_create`,`gmt_modified`) values ('1357568992916353025','20210205135703834','2021-02-05 13:57:25',1,'4200000926202102052445151515','SUCCESS',1,'{\"transaction_id\":\"4200000926202102052445151515\",\"nonce_str\":\"xHeOOsUA6EhtWk7Qx5wlGsI5FXZ4a6ku\",\"bank_type\":\"OTHERS\",\"openid\":\"oHwsHuC_ppr3p0dhz3QE_sIfEECQ\",\"sign\":\"CCE87D518013CD05957281D71BA1AC39\",\"fee_type\":\"CNY\",\"mch_id\":\"1558950191\",\"cash_fee\":\"1\",\"out_trade_no\":\"20210205135703834\",\"appid\":\"wx74862e0dfcf69954\",\"total_fee\":\"1\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"time_end\":\"20210205135723\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}',0,'2021-02-05 13:57:25','2021-02-05 13:57:25'),('1357569349537050626','20210205135837310','2021-02-05 13:58:50',1,'4200000944202102054810347083','SUCCESS',1,'{\"transaction_id\":\"4200000944202102054810347083\",\"nonce_str\":\"iHMBB6ZICgboiHM0cnyvX9rqnx8SdiOM\",\"bank_type\":\"OTHERS\",\"openid\":\"oHwsHuC_ppr3p0dhz3QE_sIfEECQ\",\"sign\":\"31A140FDBD853CFC1127C4EBB2F13806\",\"fee_type\":\"CNY\",\"mch_id\":\"1558950191\",\"cash_fee\":\"1\",\"out_trade_no\":\"20210205135837310\",\"appid\":\"wx74862e0dfcf69954\",\"total_fee\":\"1\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"time_end\":\"20210205135848\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}',0,'2021-02-05 13:58:50','2021-02-05 13:58:50'),('1357616206720086018','20210205165334047','2021-02-05 17:05:02',1,'4200000909202102054768479266','SUCCESS',1,'{\"transaction_id\":\"4200000909202102054768479266\",\"nonce_str\":\"PqD865PtYWx9VVBH9tgdpTguQXWnf0ij\",\"bank_type\":\"OTHERS\",\"openid\":\"oHwsHuC_ppr3p0dhz3QE_sIfEECQ\",\"sign\":\"5400A4FA9EB5FB4C85501FC43EF72067\",\"fee_type\":\"CNY\",\"mch_id\":\"1558950191\",\"cash_fee\":\"1\",\"out_trade_no\":\"20210205165334047\",\"appid\":\"wx74862e0dfcf69954\",\"total_fee\":\"1\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"time_end\":\"20210205170459\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}',0,'2021-02-05 17:05:02','2021-02-05 17:05:02'),('1357617434900344833','20210205170936651','2021-02-05 17:09:54',1,'4200000945202102054209671934','SUCCESS',1,'{\"transaction_id\":\"4200000945202102054209671934\",\"nonce_str\":\"kqHs1QWK4Ki5Zj6Rui7hKyaU4NqktfmA\",\"bank_type\":\"OTHERS\",\"openid\":\"oHwsHuC_ppr3p0dhz3QE_sIfEECQ\",\"sign\":\"610DBD0BC81302527B015C114097DC5C\",\"fee_type\":\"CNY\",\"mch_id\":\"1558950191\",\"cash_fee\":\"1\",\"out_trade_no\":\"20210205170936651\",\"appid\":\"wx74862e0dfcf69954\",\"total_fee\":\"1\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"time_end\":\"20210205170953\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}',0,'2021-02-05 17:09:54','2021-02-05 17:09:54'),('1357619475144028162','20210205171238959','2021-02-05 17:18:01',1,'4200000915202102059461368455','SUCCESS',1,'{\"transaction_id\":\"4200000915202102059461368455\",\"nonce_str\":\"WAOKhuOAtQQIenYDZ1xfQxcdQpwWleiC\",\"bank_type\":\"OTHERS\",\"openid\":\"oHwsHuC_ppr3p0dhz3QE_sIfEECQ\",\"sign\":\"A3262349E1E447B7AEA7F20283DAE021\",\"fee_type\":\"CNY\",\"mch_id\":\"1558950191\",\"cash_fee\":\"1\",\"out_trade_no\":\"20210205171238959\",\"appid\":\"wx74862e0dfcf69954\",\"total_fee\":\"1\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"time_end\":\"20210205171759\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}',0,'2021-02-05 17:18:01','2021-02-05 17:18:01'),('1357685963532029954','20210205214017351','2021-02-05 21:42:13',1,'4200000941202102053395504479','SUCCESS',1,'{\"transaction_id\":\"4200000941202102053395504479\",\"nonce_str\":\"4ffGXNKwsGWiM9hgTKTr3IWFjMFaSB0u\",\"bank_type\":\"OTHERS\",\"openid\":\"oHwsHuC_ppr3p0dhz3QE_sIfEECQ\",\"sign\":\"3CBFFD3774C642D2E5024C095D091C11\",\"fee_type\":\"CNY\",\"mch_id\":\"1558950191\",\"cash_fee\":\"1\",\"out_trade_no\":\"20210205214017351\",\"appid\":\"wx74862e0dfcf69954\",\"total_fee\":\"1\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"time_end\":\"20210205214211\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}',0,'2021-02-05 21:42:13','2021-02-05 21:42:13'),('1358112089219903489','20210207013735017','2021-02-07 01:55:29',1,'4200000916202102071895369644','SUCCESS',1,'{\"transaction_id\":\"4200000916202102071895369644\",\"nonce_str\":\"7gzYikVHZ8NwfRpdq09o54buA5gxItn5\",\"bank_type\":\"OTHERS\",\"openid\":\"oHwsHuC_ppr3p0dhz3QE_sIfEECQ\",\"sign\":\"A0B6931A7EAB7F3DCADABF1883D0B3A0\",\"fee_type\":\"CNY\",\"mch_id\":\"1558950191\",\"cash_fee\":\"1\",\"out_trade_no\":\"20210207013735017\",\"appid\":\"wx74862e0dfcf69954\",\"total_fee\":\"1\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"time_end\":\"20210207015528\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}',0,'2021-02-07 01:55:29','2021-02-07 01:55:29');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
