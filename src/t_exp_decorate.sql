/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : db_no

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-04-18 23:17:08
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
  `status` int(1) DEFAULT '0' COMMENT '活动状态 0 未开始，1开始 2结束',
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
  `exp_uv` int(11) DEFAULT NULL COMMENT '参与人数',
  `exp_pv` int(11) DEFAULT NULL COMMENT '访问次数',
  `count_price` decimal(10,2) DEFAULT NULL COMMENT '累积金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_exp_decorate
-- ----------------------------
INSERT INTO `t_exp_decorate` VALUES ('9', '111', '11', '0', '22222222222222', '2018-04-10 21:31:00', '2018-05-04 21:31:00', '2018-04-18 21:31:34', '2018-04-18 21:31:34', null, null, '234', '/noService/resources/outImages/decorate/20180418/20180418213132_957.png', '1.00', '2.00', '1', '1002', '12834.32');
INSERT INTO `t_exp_decorate` VALUES ('10', '535', '345', '1', '333', '2018-04-10 21:56:00', '2018-05-04 21:56:00', '2018-04-18 21:57:11', '2018-04-18 21:57:11', null, null, '33', '/noService/resources/outImages/decorate/20180418/20180418215709_680.png', '1.00', '2.00', '23', '234234', '53223.63');
