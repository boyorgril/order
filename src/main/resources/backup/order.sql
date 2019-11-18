/*
Navicat MySQL Data Transfer

Source Server         : PersonalDB
Source Server Version : 50645
Source Host           : localhost:3306
Source Database       : order

Target Server Type    : MYSQL
Target Server Version : 50645
File Encoding         : 65001

Date: 2019-11-18 17:05:16
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

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
-- Table structure for loginrecord
-- ----------------------------
DROP TABLE IF EXISTS `loginrecord`;
CREATE TABLE `loginrecord` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `ipAddress` varchar(255) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `loginTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for operationrecord
-- ----------------------------
DROP TABLE IF EXISTS `operationrecord`;
CREATE TABLE `operationrecord` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `userId` bigint(10) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `createAt` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `updateWhen` timestamp NULL DEFAULT NULL,
  `payTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
  `createAt` date DEFAULT NULL,
  `updateWhen` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
