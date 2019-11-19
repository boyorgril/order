/*
Navicat MySQL Data Transfer

Source Server         : PersonalDB
Source Server Version : 50645
Source Host           : localhost:3306
Source Database       : order

Target Server Type    : MYSQL
Target Server Version : 50645
File Encoding         : 65001

Date: 2019-11-19 17:00:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `userId` bigint(10) DEFAULT NULL COMMENT '用户id',
  `location` varchar(255) DEFAULT NULL COMMENT '地址',
  `phoneNumber` varchar(20) DEFAULT NULL COMMENT '电话',
  `name` varchar(255) DEFAULT NULL COMMENT '收货人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '26', '飞输入法', '4654165', '飞输入法吃');
INSERT INTO `address` VALUES ('2', '26', '是CAD深V在是在大V 是水电费不舍得扔 ', '24542542', '出生地车');
INSERT INTO `address` VALUES ('3', '26', 'das是字符大V 不是的', '68486245', '年');
INSERT INTO `address` VALUES ('4', '26', '大转法发斯蒂芬', '15898523578', '扯犊子');
INSERT INTO `address` VALUES ('5', '29', '大转法发斯蒂芬', '15898523578', '扯犊子');

-- ----------------------------
-- Table structure for collectfood
-- ----------------------------
DROP TABLE IF EXISTS `collectfood`;
CREATE TABLE `collectfood` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sfid` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createAt` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collectfood
-- ----------------------------
INSERT INTO `collectfood` VALUES ('1', '1', '26', '2019-11-17', 'UNCOLLECT');
INSERT INTO `collectfood` VALUES ('2', '2', '26', '2019-11-17', 'UNCOLLECT');
INSERT INTO `collectfood` VALUES ('3', '3', '26', '2019-11-17', 'COLLECT');
INSERT INTO `collectfood` VALUES ('4', '1', '29', '2019-11-19', 'COLLECT');

-- ----------------------------
-- Table structure for collectshop
-- ----------------------------
DROP TABLE IF EXISTS `collectshop`;
CREATE TABLE `collectshop` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `shopId` bigint(20) DEFAULT NULL,
  `createAt` timestamp NULL DEFAULT NULL,
  `status` varchar(255) DEFAULT '' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of collectshop
-- ----------------------------
INSERT INTO `collectshop` VALUES ('1', '26', '1', '2019-11-17 08:33:31', 'COLLECT');
INSERT INTO `collectshop` VALUES ('2', '26', '2', '2019-11-17 08:33:34', 'COLLECT');
INSERT INTO `collectshop` VALUES ('3', '29', '1', '2019-11-19 01:29:09', 'COLLECT');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(10) DEFAULT NULL COMMENT '用户id',
  `order_id` bigint(10) DEFAULT NULL COMMENT '订单id',
  `content` varchar(255) DEFAULT NULL COMMENT '评论',
  `create_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '26', '52', '很不错很好吃', '2019-11-18 12:40:15');
INSERT INTO `comment` VALUES ('2', '26', '50', '还不错一般般了', '2019-11-18 12:40:45');

-- ----------------------------
-- Table structure for loginrecord
-- ----------------------------
DROP TABLE IF EXISTS `loginrecord`;
CREATE TABLE `loginrecord` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `ipAddress` varchar(255) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `loginTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loginrecord
-- ----------------------------
INSERT INTO `loginrecord` VALUES ('9', '127.0.0.1', '26', '2019-11-14 13:35:11');
INSERT INTO `loginrecord` VALUES ('10', '192.168.19.44', '26', '2019-11-14 13:38:10');
INSERT INTO `loginrecord` VALUES ('11', '127.0.0.1127.0.0.1', '26', '2019-11-16 01:28:10');
INSERT INTO `loginrecord` VALUES ('12', '127.0.0.1127.0.0.1', '26', '2019-11-16 01:29:25');
INSERT INTO `loginrecord` VALUES ('13', '127.0.0.1127.0.0.1', '26', '2019-11-16 01:31:36');
INSERT INTO `loginrecord` VALUES ('14', '127.0.0.1127.0.0.1', '26', '2019-11-16 01:37:57');
INSERT INTO `loginrecord` VALUES ('15', '127.0.0.1127.0.0.1', '26', '2019-11-16 01:38:58');
INSERT INTO `loginrecord` VALUES ('16', '127.0.0.1127.0.0.1', '26', '2019-11-16 02:17:16');
INSERT INTO `loginrecord` VALUES ('17', '127.0.0.1127.0.0.1', '26', '2019-11-16 02:18:45');
INSERT INTO `loginrecord` VALUES ('18', '127.0.0.1127.0.0.1', '26', '2019-11-16 02:31:31');
INSERT INTO `loginrecord` VALUES ('19', '127.0.0.1127.0.0.1', '26', '2019-11-16 02:32:53');
INSERT INTO `loginrecord` VALUES ('20', '127.0.0.1127.0.0.1', '26', '2019-11-16 02:35:13');
INSERT INTO `loginrecord` VALUES ('21', '127.0.0.1127.0.0.1', '26', '2019-11-16 02:36:30');
INSERT INTO `loginrecord` VALUES ('22', '127.0.0.1127.0.0.1', '26', '2019-11-16 02:39:29');
INSERT INTO `loginrecord` VALUES ('23', '127.0.0.1127.0.0.1', '26', '2019-11-16 06:03:23');
INSERT INTO `loginrecord` VALUES ('24', '127.0.0.1127.0.0.1', '26', '2019-11-16 06:07:02');
INSERT INTO `loginrecord` VALUES ('25', '127.0.0.1127.0.0.1', '26', '2019-11-16 06:17:26');
INSERT INTO `loginrecord` VALUES ('26', '127.0.0.1127.0.0.1', '26', '2019-11-16 06:48:01');
INSERT INTO `loginrecord` VALUES ('27', '127.0.0.1127.0.0.1', '26', '2019-11-16 06:51:51');
INSERT INTO `loginrecord` VALUES ('28', '127.0.0.1127.0.0.1', '26', '2019-11-16 06:53:36');
INSERT INTO `loginrecord` VALUES ('29', '127.0.0.1127.0.0.1', '26', '2019-11-16 06:56:25');
INSERT INTO `loginrecord` VALUES ('30', '127.0.0.1127.0.0.1', '26', '2019-11-16 07:49:45');
INSERT INTO `loginrecord` VALUES ('31', '127.0.0.1127.0.0.1', '26', '2019-11-17 00:56:02');
INSERT INTO `loginrecord` VALUES ('32', '127.0.0.1127.0.0.1', '26', '2019-11-17 01:00:49');
INSERT INTO `loginrecord` VALUES ('33', '127.0.0.1127.0.0.1', '26', '2019-11-17 01:19:43');
INSERT INTO `loginrecord` VALUES ('34', '127.0.0.1127.0.0.1', '26', '2019-11-17 01:28:01');
INSERT INTO `loginrecord` VALUES ('35', '127.0.0.1127.0.0.1', '26', '2019-11-17 01:29:38');
INSERT INTO `loginrecord` VALUES ('36', '127.0.0.1127.0.0.1', '26', '2019-11-17 01:33:01');
INSERT INTO `loginrecord` VALUES ('37', '127.0.0.1127.0.0.1', '26', '2019-11-17 01:34:40');
INSERT INTO `loginrecord` VALUES ('38', '127.0.0.1127.0.0.1', '26', '2019-11-17 01:36:04');
INSERT INTO `loginrecord` VALUES ('39', '127.0.0.1127.0.0.1', '26', '2019-11-17 01:38:06');
INSERT INTO `loginrecord` VALUES ('40', '127.0.0.1127.0.0.1', '26', '2019-11-17 01:41:32');
INSERT INTO `loginrecord` VALUES ('41', '127.0.0.1127.0.0.1', '26', '2019-11-17 01:43:12');
INSERT INTO `loginrecord` VALUES ('42', '127.0.0.1127.0.0.1', '26', '2019-11-17 01:47:41');
INSERT INTO `loginrecord` VALUES ('43', '127.0.0.1127.0.0.1', '26', '2019-11-17 02:00:19');
INSERT INTO `loginrecord` VALUES ('44', '127.0.0.1127.0.0.1', '26', '2019-11-17 02:08:14');
INSERT INTO `loginrecord` VALUES ('45', '127.0.0.1127.0.0.1', '26', '2019-11-17 02:12:16');
INSERT INTO `loginrecord` VALUES ('46', '127.0.0.1127.0.0.1', '26', '2019-11-17 02:21:07');
INSERT INTO `loginrecord` VALUES ('47', '127.0.0.1127.0.0.1', '26', '2019-11-17 02:25:46');
INSERT INTO `loginrecord` VALUES ('48', '127.0.0.1127.0.0.1', '26', '2019-11-17 02:28:52');
INSERT INTO `loginrecord` VALUES ('49', '127.0.0.1127.0.0.1', '26', '2019-11-17 02:34:19');
INSERT INTO `loginrecord` VALUES ('50', '127.0.0.1127.0.0.1', '26', '2019-11-17 02:42:49');
INSERT INTO `loginrecord` VALUES ('51', '127.0.0.1127.0.0.1', '26', '2019-11-17 02:46:45');
INSERT INTO `loginrecord` VALUES ('52', '127.0.0.1127.0.0.1', '26', '2019-11-17 02:49:51');
INSERT INTO `loginrecord` VALUES ('53', '127.0.0.1127.0.0.1', '26', '2019-11-17 03:47:55');
INSERT INTO `loginrecord` VALUES ('54', '127.0.0.1127.0.0.1', '26', '2019-11-17 04:35:09');
INSERT INTO `loginrecord` VALUES ('55', '127.0.0.1127.0.0.1', '26', '2019-11-17 04:36:25');
INSERT INTO `loginrecord` VALUES ('56', '127.0.0.1127.0.0.1', '26', '2019-11-17 04:37:25');
INSERT INTO `loginrecord` VALUES ('57', '127.0.0.1127.0.0.1', '26', '2019-11-17 04:42:17');
INSERT INTO `loginrecord` VALUES ('58', '127.0.0.1127.0.0.1', '26', '2019-11-17 05:29:39');
INSERT INTO `loginrecord` VALUES ('59', '127.0.0.1127.0.0.1', '26', '2019-11-17 08:10:34');
INSERT INTO `loginrecord` VALUES ('60', '127.0.0.1127.0.0.1', '26', '2019-11-17 08:29:19');
INSERT INTO `loginrecord` VALUES ('61', '127.0.0.1127.0.0.1', '26', '2019-11-17 08:32:52');
INSERT INTO `loginrecord` VALUES ('62', '127.0.0.1127.0.0.1', '26', '2019-11-17 11:17:17');
INSERT INTO `loginrecord` VALUES ('63', '127.0.0.1127.0.0.1', '26', '2019-11-17 11:19:22');
INSERT INTO `loginrecord` VALUES ('64', '127.0.0.1127.0.0.1', '26', '2019-11-17 11:40:35');
INSERT INTO `loginrecord` VALUES ('65', '127.0.0.1127.0.0.1', '26', '2019-11-17 11:42:16');
INSERT INTO `loginrecord` VALUES ('66', '127.0.0.1127.0.0.1', '26', '2019-11-17 11:47:53');
INSERT INTO `loginrecord` VALUES ('67', '127.0.0.1127.0.0.1', '26', '2019-11-17 11:50:19');
INSERT INTO `loginrecord` VALUES ('68', '127.0.0.1127.0.0.1', '26', '2019-11-17 11:52:44');
INSERT INTO `loginrecord` VALUES ('69', '127.0.0.1127.0.0.1', '26', '2019-11-17 12:03:58');
INSERT INTO `loginrecord` VALUES ('70', '127.0.0.1127.0.0.1', '26', '2019-11-17 12:20:03');
INSERT INTO `loginrecord` VALUES ('71', '127.0.0.1127.0.0.1', '26', '2019-11-17 12:33:01');
INSERT INTO `loginrecord` VALUES ('72', '127.0.0.1127.0.0.1', '26', '2019-11-17 13:09:40');
INSERT INTO `loginrecord` VALUES ('73', '127.0.0.1127.0.0.1', '26', '2019-11-17 13:16:10');
INSERT INTO `loginrecord` VALUES ('74', '127.0.0.1127.0.0.1', '26', '2019-11-17 13:17:19');
INSERT INTO `loginrecord` VALUES ('75', '127.0.0.1127.0.0.1', '26', '2019-11-17 13:26:21');
INSERT INTO `loginrecord` VALUES ('76', '127.0.0.1127.0.0.1', '26', '2019-11-17 13:29:24');
INSERT INTO `loginrecord` VALUES ('77', '127.0.0.1127.0.0.1', '26', '2019-11-17 13:31:45');
INSERT INTO `loginrecord` VALUES ('78', '127.0.0.1127.0.0.1', '26', '2019-11-18 01:20:46');
INSERT INTO `loginrecord` VALUES ('79', '127.0.0.1127.0.0.1', '26', '2019-11-18 01:25:40');
INSERT INTO `loginrecord` VALUES ('80', '127.0.0.1127.0.0.1', '26', '2019-11-18 02:22:55');
INSERT INTO `loginrecord` VALUES ('81', '127.0.0.1127.0.0.1', '26', '2019-11-18 02:24:45');
INSERT INTO `loginrecord` VALUES ('82', '127.0.0.1127.0.0.1', '26', '2019-11-18 02:26:53');
INSERT INTO `loginrecord` VALUES ('83', '127.0.0.1127.0.0.1', '26', '2019-11-18 02:29:39');
INSERT INTO `loginrecord` VALUES ('84', '127.0.0.1127.0.0.1', '26', '2019-11-18 02:36:10');
INSERT INTO `loginrecord` VALUES ('85', '127.0.0.1127.0.0.1', '26', '2019-11-18 04:48:02');
INSERT INTO `loginrecord` VALUES ('86', '127.0.0.1127.0.0.1', '26', '2019-11-18 05:10:18');
INSERT INTO `loginrecord` VALUES ('87', '127.0.0.1', '26', '2019-11-18 05:40:33');
INSERT INTO `loginrecord` VALUES ('88', '127.0.0.1', '26', '2019-11-18 06:17:36');
INSERT INTO `loginrecord` VALUES ('89', '127.0.0.1', '26', '2019-11-18 06:51:20');
INSERT INTO `loginrecord` VALUES ('90', '127.0.0.1', '26', '2019-11-18 06:53:10');
INSERT INTO `loginrecord` VALUES ('91', '127.0.0.1', '26', '2019-11-18 06:56:35');
INSERT INTO `loginrecord` VALUES ('92', '127.0.0.1', '26', '2019-11-18 07:28:53');
INSERT INTO `loginrecord` VALUES ('93', '127.0.0.1', '26', '2019-11-18 08:14:59');
INSERT INTO `loginrecord` VALUES ('94', '127.0.0.1', '26', '2019-11-18 08:27:06');
INSERT INTO `loginrecord` VALUES ('95', '127.0.0.1', '26', '2019-11-18 08:28:34');
INSERT INTO `loginrecord` VALUES ('96', '127.0.0.1', '26', '2019-11-18 08:31:54');
INSERT INTO `loginrecord` VALUES ('97', '127.0.0.1', '26', '2019-11-18 08:45:48');
INSERT INTO `loginrecord` VALUES ('98', '127.0.0.1', '26', '2019-11-18 08:55:27');
INSERT INTO `loginrecord` VALUES ('99', '127.0.0.1', '26', '2019-11-18 10:08:41');
INSERT INTO `loginrecord` VALUES ('100', '127.0.0.1', '27', '2019-11-18 10:12:23');
INSERT INTO `loginrecord` VALUES ('101', '127.0.0.1', '2', '2019-11-18 10:13:00');
INSERT INTO `loginrecord` VALUES ('102', '127.0.0.1', '2', '2019-11-18 10:13:30');
INSERT INTO `loginrecord` VALUES ('103', '127.0.0.1', '26', '2019-11-18 10:14:38');
INSERT INTO `loginrecord` VALUES ('104', '127.0.0.1', '2', '2019-11-18 10:17:05');
INSERT INTO `loginrecord` VALUES ('105', '127.0.0.1', '26', '2019-11-19 00:51:37');
INSERT INTO `loginrecord` VALUES ('106', '127.0.0.1', '26', '2019-11-19 00:54:47');
INSERT INTO `loginrecord` VALUES ('107', '127.0.0.1', '26', '2019-11-19 01:00:32');
INSERT INTO `loginrecord` VALUES ('108', '127.0.0.1', '29', '2019-11-19 01:21:21');
INSERT INTO `loginrecord` VALUES ('109', '127.0.0.1', '29', '2019-11-19 01:24:44');
INSERT INTO `loginrecord` VALUES ('110', '127.0.0.1', '29', '2019-11-19 01:27:31');
INSERT INTO `loginrecord` VALUES ('111', '127.0.0.1', '30', '2019-11-19 01:30:36');
INSERT INTO `loginrecord` VALUES ('112', '127.0.0.1', '26', '2019-11-19 07:43:27');
INSERT INTO `loginrecord` VALUES ('113', '127.0.0.1', '26', '2019-11-19 07:45:41');
INSERT INTO `loginrecord` VALUES ('114', '127.0.0.1', '26', '2019-11-19 07:51:47');
INSERT INTO `loginrecord` VALUES ('115', '127.0.0.1', '26', '2019-11-19 08:02:59');
INSERT INTO `loginrecord` VALUES ('116', '127.0.0.1', '26', '2019-11-19 08:42:51');
INSERT INTO `loginrecord` VALUES ('117', '127.0.0.1', '26', '2019-11-19 08:46:14');
INSERT INTO `loginrecord` VALUES ('118', '127.0.0.1', '26', '2019-11-19 08:55:40');
INSERT INTO `loginrecord` VALUES ('119', '127.0.0.1', '26', '2019-11-19 08:58:14');

-- ----------------------------
-- Table structure for operation_record
-- ----------------------------
DROP TABLE IF EXISTS `operation_record`;
CREATE TABLE `operation_record` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `ip_address` varchar(10) DEFAULT NULL,
  `describes` varchar(255) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operation_record
-- ----------------------------
INSERT INTO `operation_record` VALUES ('1', null, '调用后连接点方法为：findAccount,参数为：[com.groupwork.order.datasource.dto.User@5c34833a]', null);
INSERT INTO `operation_record` VALUES ('2', '127.0.0.1', '调用后连接点方法为：addRecord,参数为：[127.0.0.1, 26]', null);
INSERT INTO `operation_record` VALUES ('3', '127.0.0.1', '调用后连接点方法为：allShop,参数为：[26]', null);
INSERT INTO `operation_record` VALUES ('4', '127.0.0.1', '调用后连接点方法为：shopFoodByShopId,参数为：[1, 26]', null);
INSERT INTO `operation_record` VALUES ('5', '127.0.0.1', '调用后连接点方法为：findShopById,参数为：[1]', null);
INSERT INTO `operation_record` VALUES ('6', '127.0.0.1', '调用后连接点方法为：shopFoodByShopId,参数为：[27, 26]', null);
INSERT INTO `operation_record` VALUES ('7', '127.0.0.1', '调用后连接点方法为：findShopById,参数为：[27]', null);
INSERT INTO `operation_record` VALUES ('8', '127.0.0.1', '连接点方法为：findShopById,参数为：[27],异常为：java.lang.NullPointerException', null);
INSERT INTO `operation_record` VALUES ('9', null, '调用后的方法为：findAccount,参数为：[com.groupwork.order.datasource.dto.User@45047368]', '2019-11-19 08:46:14');
INSERT INTO `operation_record` VALUES ('10', '127.0.0.1', '调用后的方法为：addRecord,参数为：[127.0.0.1, 26]', '2019-11-19 08:46:14');
INSERT INTO `operation_record` VALUES ('11', '127.0.0.1', '调用后的方法为：allShop,参数为：[26]', '2019-11-19 08:46:14');
INSERT INTO `operation_record` VALUES ('12', '127.0.0.1', '调用后的方法为：shopFoodByShopId,参数为：[1, 26]', '2019-11-19 08:46:16');
INSERT INTO `operation_record` VALUES ('13', '127.0.0.1', '调用后的方法为：findShopById,参数为：[1]', '2019-11-19 08:46:16');
INSERT INTO `operation_record` VALUES ('14', '127.0.0.1', '调用后的方法为：shopFoodByShopId,参数为：[2, 26]', '2019-11-19 08:46:19');
INSERT INTO `operation_record` VALUES ('15', '127.0.0.1', '调用后的方法为：findShopById,参数为：[2]', '2019-11-19 08:46:19');
INSERT INTO `operation_record` VALUES ('16', '127.0.0.1', '调用后的方法为：shopFoodByShopId,参数为：[27, 26]', '2019-11-19 08:46:30');
INSERT INTO `operation_record` VALUES ('17', '127.0.0.1', '调用后的方法为：findShopById,参数为：[27]', '2019-11-19 08:46:30');
INSERT INTO `operation_record` VALUES ('18', '127.0.0.1', '调用的方法为：findShopById,参数为：[27],异常为：java.lang.NullPointerException', '2019-11-19 08:46:30');
INSERT INTO `operation_record` VALUES ('19', '127.0.0.1', '调用后的方法为：collectShop,参数为：[26, 2]', '2019-11-19 08:46:51');
INSERT INTO `operation_record` VALUES ('20', '127.0.0.1', '调用后的方法为：searchFood,参数为：[26]', '2019-11-19 08:46:52');
INSERT INTO `operation_record` VALUES ('21', '127.0.0.1', '调用后的方法为：searchShop,参数为：[26]', '2019-11-19 08:46:52');
INSERT INTO `operation_record` VALUES ('22', '127.0.0.1', '调用后的方法为：findUserInfo,参数为：[26]', '2019-11-19 08:46:52');
INSERT INTO `operation_record` VALUES ('23', '127.0.0.1', '调用后的方法为：findDetailById,参数为：[26]', '2019-11-19 08:46:54');
INSERT INTO `operation_record` VALUES ('24', '127.0.0.1', '调用后的方法为：updateUserName,参数为：[26, 赤胆忠当初]', '2019-11-19 08:47:01');
INSERT INTO `operation_record` VALUES ('25', null, '调用后的方法为：findAccount,参数为：[com.groupwork.order.datasource.dto.User@6f9cea87]', '2019-11-19 08:55:40');
INSERT INTO `operation_record` VALUES ('26', '127.0.0.1', '调用后的方法为：addRecord,参数为：[127.0.0.1, 26]', '2019-11-19 08:55:40');
INSERT INTO `operation_record` VALUES ('27', '127.0.0.1', '调用后的方法为：allShop,参数为：[26]', '2019-11-19 08:55:40');
INSERT INTO `operation_record` VALUES ('28', '127.0.0.1', '调用后的方法为：shopFoodByShopId,参数为：[27, 26]', '2019-11-19 08:55:41');
INSERT INTO `operation_record` VALUES ('29', '127.0.0.1', '调用后的方法为：findShopById,参数为：[27]', '2019-11-19 08:55:41');
INSERT INTO `operation_record` VALUES ('30', '127.0.0.1', '调用的方法为：findShopById,参数为：[27],异常为：java.lang.NullPointerException', '2019-11-19 08:55:41');
INSERT INTO `operation_record` VALUES ('31', '127.0.0.1', '调用后的方法为：shopFoodByShopId,参数为：[2, 26]', '2019-11-19 08:55:51');
INSERT INTO `operation_record` VALUES ('32', '127.0.0.1', '调用后的方法为：findShopById,参数为：[2]', '2019-11-19 08:55:51');
INSERT INTO `operation_record` VALUES ('33', '127.0.0.1', '调用后的方法为：foodComment,参数为：[4]', '2019-11-19 08:55:52');
INSERT INTO `operation_record` VALUES ('34', null, '调用后的方法为：findAccount,参数为：[com.groupwork.order.datasource.dto.User@3edb3ffd]', '2019-11-19 08:58:14');
INSERT INTO `operation_record` VALUES ('35', '127.0.0.1', '调用后的方法为：addRecord,参数为：[127.0.0.1, 26]', '2019-11-19 08:58:14');
INSERT INTO `operation_record` VALUES ('36', '127.0.0.1', '调用后的方法为：allShop,参数为：[26]', '2019-11-19 08:58:14');
INSERT INTO `operation_record` VALUES ('37', '127.0.0.1', '调用后的方法为：shopFoodByShopId,参数为：[27, 26]', '2019-11-19 08:58:16');
INSERT INTO `operation_record` VALUES ('38', '127.0.0.1', '调用后的方法为：findShopById,参数为：[27]', '2019-11-19 08:58:16');
INSERT INTO `operation_record` VALUES ('39', '127.0.0.1', '调用的方法为：findShopById,参数为：[27],异常为：java.lang.NullPointerException', '2019-11-19 08:58:16');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL COMMENT '订单状态',
  `buyId` bigint(10) DEFAULT NULL COMMENT '买家id',
  `sellId` bigint(10) DEFAULT NULL COMMENT '卖家id',
  `addressId` bigint(10) DEFAULT NULL COMMENT '收货地址id',
  `totalMoney` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `createAt` timestamp NULL DEFAULT NULL,
  `updateWhen` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `payTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('29', 'NOCOMPLETE', '26', '1', '1', '110.00', '2019-11-17 05:29:48', '2019-11-17 05:29:48', null);
INSERT INTO `order` VALUES ('30', 'NOCOMPLETE', '26', '1', '1', '110.00', '2019-11-17 05:33:30', '2019-11-17 05:33:30', null);
INSERT INTO `order` VALUES ('31', 'NOCOMPLETE', '26', '1', '1', '110.00', '2019-11-17 05:36:03', '2019-11-17 05:36:03', null);
INSERT INTO `order` VALUES ('50', 'NOCOMPLETE', '26', '1', '2', '20.00', '2019-11-18 02:29:48', '2019-11-18 02:29:48', null);
INSERT INTO `order` VALUES ('52', 'NOCOMPLETE', '26', '1', '1', '60.00', '2019-11-18 02:36:41', '2019-11-18 02:36:41', null);
INSERT INTO `order` VALUES ('53', 'NOCOMPLETE', '26', '1', '2', '0.00', '2019-11-18 05:04:00', '2019-11-18 05:04:00', null);
INSERT INTO `order` VALUES ('54', 'NOCOMPLETE', '26', '1', '1', '30.00', '2019-11-18 05:08:09', '2019-11-18 05:08:09', null);
INSERT INTO `order` VALUES ('55', 'NOCOMPLETE', '26', '1', '2', '70.00', '2019-11-18 05:10:26', '2019-11-18 05:10:26', '2019-11-18 05:10:30');
INSERT INTO `order` VALUES ('56', 'NOCOMPLETE', '26', '1', '2', '70.00', '2019-11-18 08:57:17', '2019-11-18 08:57:17', '2019-11-18 08:57:30');
INSERT INTO `order` VALUES ('57', 'NOCOMPLETE', '26', '2', '2', '40.00', '2019-11-18 10:16:46', '2019-11-18 10:16:46', '2019-11-18 10:16:51');

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `oid` bigint(20) DEFAULT NULL COMMENT '订单id',
  `sfid` bigint(20) DEFAULT NULL COMMENT '菜品id',
  `number` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------
INSERT INTO `orderdetail` VALUES ('64', '29', '1', '3');
INSERT INTO `orderdetail` VALUES ('65', '29', '2', '4');
INSERT INTO `orderdetail` VALUES ('66', '29', '3', '4');
INSERT INTO `orderdetail` VALUES ('67', '30', '1', '3');
INSERT INTO `orderdetail` VALUES ('68', '30', '2', '4');
INSERT INTO `orderdetail` VALUES ('69', '30', '3', '4');
INSERT INTO `orderdetail` VALUES ('70', '31', '1', '3');
INSERT INTO `orderdetail` VALUES ('71', '31', '2', '4');
INSERT INTO `orderdetail` VALUES ('72', '31', '3', '4');
INSERT INTO `orderdetail` VALUES ('73', '32', '1', '2');
INSERT INTO `orderdetail` VALUES ('74', '32', '2', '2');
INSERT INTO `orderdetail` VALUES ('75', '32', '3', '1');
INSERT INTO `orderdetail` VALUES ('76', '33', '1', '2');
INSERT INTO `orderdetail` VALUES ('77', '33', '2', '2');
INSERT INTO `orderdetail` VALUES ('78', '33', '3', '1');
INSERT INTO `orderdetail` VALUES ('79', '34', '1', '2');
INSERT INTO `orderdetail` VALUES ('80', '34', '2', '2');
INSERT INTO `orderdetail` VALUES ('81', '34', '3', '1');
INSERT INTO `orderdetail` VALUES ('82', '35', '1', '2');
INSERT INTO `orderdetail` VALUES ('83', '35', '2', '2');
INSERT INTO `orderdetail` VALUES ('84', '35', '3', '1');
INSERT INTO `orderdetail` VALUES ('85', '36', '1', '2');
INSERT INTO `orderdetail` VALUES ('86', '36', '2', '2');
INSERT INTO `orderdetail` VALUES ('87', '36', '3', '1');
INSERT INTO `orderdetail` VALUES ('88', '37', '1', '2');
INSERT INTO `orderdetail` VALUES ('89', '37', '2', '2');
INSERT INTO `orderdetail` VALUES ('90', '37', '3', '1');
INSERT INTO `orderdetail` VALUES ('91', '38', '1', '2');
INSERT INTO `orderdetail` VALUES ('92', '38', '2', '2');
INSERT INTO `orderdetail` VALUES ('93', '38', '3', '1');
INSERT INTO `orderdetail` VALUES ('94', '39', '1', '2');
INSERT INTO `orderdetail` VALUES ('95', '39', '2', '2');
INSERT INTO `orderdetail` VALUES ('96', '39', '3', '1');
INSERT INTO `orderdetail` VALUES ('97', '40', '1', '2');
INSERT INTO `orderdetail` VALUES ('98', '40', '2', '2');
INSERT INTO `orderdetail` VALUES ('99', '40', '3', '1');
INSERT INTO `orderdetail` VALUES ('100', '41', '1', '2');
INSERT INTO `orderdetail` VALUES ('101', '41', '2', '2');
INSERT INTO `orderdetail` VALUES ('102', '41', '3', '1');
INSERT INTO `orderdetail` VALUES ('103', '42', '1', '2');
INSERT INTO `orderdetail` VALUES ('104', '42', '2', '2');
INSERT INTO `orderdetail` VALUES ('105', '42', '3', '1');
INSERT INTO `orderdetail` VALUES ('106', '43', '1', '2');
INSERT INTO `orderdetail` VALUES ('107', '43', '2', '2');
INSERT INTO `orderdetail` VALUES ('108', '43', '3', '1');
INSERT INTO `orderdetail` VALUES ('109', '44', '1', '2');
INSERT INTO `orderdetail` VALUES ('110', '44', '2', '2');
INSERT INTO `orderdetail` VALUES ('111', '44', '3', '1');
INSERT INTO `orderdetail` VALUES ('112', '45', '1', '2');
INSERT INTO `orderdetail` VALUES ('113', '45', '2', '2');
INSERT INTO `orderdetail` VALUES ('114', '45', '3', '1');
INSERT INTO `orderdetail` VALUES ('115', '46', '1', '2');
INSERT INTO `orderdetail` VALUES ('116', '46', '2', '2');
INSERT INTO `orderdetail` VALUES ('117', '46', '3', '1');
INSERT INTO `orderdetail` VALUES ('118', '47', '1', '2');
INSERT INTO `orderdetail` VALUES ('119', '47', '2', '1');
INSERT INTO `orderdetail` VALUES ('120', '47', '3', '2');
INSERT INTO `orderdetail` VALUES ('121', '48', '1', '2');
INSERT INTO `orderdetail` VALUES ('122', '48', '2', '1');
INSERT INTO `orderdetail` VALUES ('123', '48', '3', '2');
INSERT INTO `orderdetail` VALUES ('124', '49', '1', '1');
INSERT INTO `orderdetail` VALUES ('125', '49', '2', '1');
INSERT INTO `orderdetail` VALUES ('126', '49', '3', '1');
INSERT INTO `orderdetail` VALUES ('127', '50', '1', '1');
INSERT INTO `orderdetail` VALUES ('128', '50', '2', '0');
INSERT INTO `orderdetail` VALUES ('129', '50', '3', '1');
INSERT INTO `orderdetail` VALUES ('130', '51', '1', '0');
INSERT INTO `orderdetail` VALUES ('131', '51', '2', '0');
INSERT INTO `orderdetail` VALUES ('132', '51', '3', '0');
INSERT INTO `orderdetail` VALUES ('133', '52', '1', '2');
INSERT INTO `orderdetail` VALUES ('134', '52', '2', '2');
INSERT INTO `orderdetail` VALUES ('135', '52', '3', '2');
INSERT INTO `orderdetail` VALUES ('136', '54', '1', '1');
INSERT INTO `orderdetail` VALUES ('137', '54', '2', '1');
INSERT INTO `orderdetail` VALUES ('138', '54', '3', '1');
INSERT INTO `orderdetail` VALUES ('139', '55', '1', '2');
INSERT INTO `orderdetail` VALUES ('140', '55', '2', '2');
INSERT INTO `orderdetail` VALUES ('141', '55', '3', '3');
INSERT INTO `orderdetail` VALUES ('142', '56', '1', '3');
INSERT INTO `orderdetail` VALUES ('143', '56', '2', '2');
INSERT INTO `orderdetail` VALUES ('144', '56', '3', '2');
INSERT INTO `orderdetail` VALUES ('145', '57', '4', '2');
INSERT INTO `orderdetail` VALUES ('146', '57', '5', '2');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(10) DEFAULT NULL COMMENT '商家拥有者',
  `shop_img_url` varchar(255) DEFAULT NULL COMMENT '图标地址',
  `name` varchar(255) DEFAULT NULL COMMENT '商铺名称',
  `introduce` varchar(255) DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1', '1', '/img/test.jpg', '测试商家', '这是一个测试商家，随便写了点什么东西，门插框架发哈萨克按实际卡死积分大卡司');
INSERT INTO `shop` VALUES ('2', '27', '/img/test.jpg', '商家', 'csdfcsvzsv');
INSERT INTO `shop` VALUES ('3', '2', '/img/666.jpg', 'dfsaf', 'fasfasdfd');

-- ----------------------------
-- Table structure for shopfood
-- ----------------------------
DROP TABLE IF EXISTS `shopfood`;
CREATE TABLE `shopfood` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `shopId` bigint(10) DEFAULT NULL COMMENT '商家id',
  `name` varchar(255) DEFAULT NULL COMMENT '菜名',
  `imgUrl` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `saleNum` int(10) DEFAULT NULL COMMENT '销量',
  `introduce` varchar(255) DEFAULT NULL COMMENT '介绍',
  `createAt` date DEFAULT NULL COMMENT '创建时间',
  `updateWhen` date DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopfood
-- ----------------------------
INSERT INTO `shopfood` VALUES ('1', '1', 'aaa', '/img/test.jpg', '10.00', '10', '这是菜品a', '2019-11-16', null);
INSERT INTO `shopfood` VALUES ('2', '1', 'aaa', '/img/a2.jpg', '10.00', '10', '这是菜品acsdc', '2019-11-16', '2019-11-16');
INSERT INTO `shopfood` VALUES ('3', '1', 'aaa', '/img/a3.jpg', '10.00', '10', '这是菜品asadczsd', '2019-11-16', '2019-11-13');
INSERT INTO `shopfood` VALUES ('4', '2', 'fadsfas', '/img/666.jpg', '10.00', null, 'dfzvfdzszv', null, null);
INSERT INTO `shopfood` VALUES ('5', '2', 'csDDC', '/img/666.jpg', '10.00', null, 'dsvzvszdvzsd', null, null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `passWord` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `imgUrl` varchar(255) DEFAULT NULL,
  `money` double(10,2) DEFAULT NULL,
  `createAt` timestamp NULL DEFAULT NULL,
  `updateWhen` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123', null, '6a9ba76019b19b14271d426fc80b6bd1', null, null, null, null, null);
INSERT INTO `user` VALUES ('2', 'bbb', null, '08f8e0260c64418510cefb2b06eee5cd', 'SELLER', '/1574067334366.jpg', null, null, null);
INSERT INTO `user` VALUES ('4', '34312432', null, '202cb962ac59075b964b07152d234b70', null, null, null, null, null);
INSERT INTO `user` VALUES ('11', '1234', null, '81dc9bdb52d04dc20036dbd8313ed055', null, null, null, null, null);
INSERT INTO `user` VALUES ('12', '1234567', null, 'fcea920f7412b5da7be0cf42b8c93759', null, null, null, null, null);
INSERT INTO `user` VALUES ('14', '12345678', null, '202cb962ac59075b964b07152d234b70', null, null, null, null, null);
INSERT INTO `user` VALUES ('15', '12345678', null, '202cb962ac59075b964b07152d234b70', null, null, null, null, null);
INSERT INTO `user` VALUES ('16', 'oldsix', null, '202cb962ac59075b964b07152d234b70', null, null, null, null, null);
INSERT INTO `user` VALUES ('17', 'oldseven', null, '202cb962ac59075b964b07152d234b70', null, null, null, null, null);
INSERT INTO `user` VALUES ('18', 'oldseven', null, '202cb962ac59075b964b07152d234b70', null, null, null, null, null);
INSERT INTO `user` VALUES ('19', 'thjyt', null, '202cb962ac59075b964b07152d234b70', null, null, null, null, null);
INSERT INTO `user` VALUES ('20', '1234567856', null, '1fec1abb927614d4131d3e49ef063ec8', null, null, null, null, null);
INSERT INTO `user` VALUES ('21', '321', null, 'caf1a3dfb505ffed0d024130f58c5cfa', null, null, null, null, null);
INSERT INTO `user` VALUES ('23', '1231111', null, '698d51a19d8a121ce581499d7b701668', null, null, null, null, null);
INSERT INTO `user` VALUES ('24', '1231111', null, '698d51a19d8a121ce581499d7b701668', null, null, null, null, null);
INSERT INTO `user` VALUES ('25', '12323312', null, '202cb962ac59075b964b07152d234b70', null, null, null, null, null);
INSERT INTO `user` VALUES ('26', 'aaa', '赤胆忠当初', '47bce5c74f589f4867dbd57e9ca9f808', 'BUYER', '/1574067334366.jpg', '500.00', null, '2019-11-19 16:47:01');
INSERT INTO `user` VALUES ('29', 'ccc', '扯犊子', '9df62e693988eb4e1e1444ece0578579', 'BUYER', '/1574126909504.jpg', '500.00', '2019-11-19 01:21:04', '2019-11-19 01:28:29');
INSERT INTO `user` VALUES ('30', 'eee', 'eee', 'd2f2297d6e829cd3493aa7de4416a18f', 'SELLER', '/img/initial.png', null, '2019-11-19 01:30:14', null);
