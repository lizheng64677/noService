/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : db_no

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-04-18 17:59:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_exp_decorate`
-- ----------------------------
DROP TABLE IF EXISTS `t_exp_decorate`;
CREATE TABLE `t_exp_decorate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(254) DEFAULT NULL COMMENT '活动名称',
  `title` varchar(512) DEFAULT NULL COMMENT '活动标题',
  `description` text COMMENT '活动描述',
  `begin_time` datetime DEFAULT NULL COMMENT '活动开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '活动结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '活动创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '活动修改时间',
  `upate_user` int(11) DEFAULT NULL COMMENT '活动修改人',
  `create_user` int(11) DEFAULT NULL COMMENT '活动创建人',
  `share_title` varchar(512) DEFAULT NULL COMMENT '页面分享标题',
  `share_img` varchar(1024) DEFAULT NULL COMMENT '页面分享图片',
  `begin_money` decimal(10,2) DEFAULT '0.00' COMMENT '随机金额开始数目',
  `end_money` decimal(10,2) DEFAULT '0.00' COMMENT '随机金额结束数目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_exp_decorate
-- ----------------------------
INSERT INTO `t_exp_decorate` VALUES ('1', null, null, null, null, null, null, null, null, null, null, null, '1.23', null);
