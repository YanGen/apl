/*
Navicat MySQL Data Transfer

Source Server         : WampMysql
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : apl

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-04-23 18:27:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for school_building
-- ----------------------------
DROP TABLE IF EXISTS `school_building`;
CREATE TABLE `school_building` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `target` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of school_building
-- ----------------------------
INSERT INTO `school_building` VALUES ('1', 'xingzhenglou', '2', 'laoshi', 'xingzhenglou ');

-- ----------------------------
-- Table structure for school_user
-- ----------------------------
DROP TABLE IF EXISTS `school_user`;
CREATE TABLE `school_user` (
  `id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of school_user
-- ----------------------------
