/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2017-11-27 16:24:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '库存数量',
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀开始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('1000', '1000元秒杀iphone6', '100', '2017-11-27 16:05:22', '2017-11-28 16:05:27', '2017-11-27 16:05:34');
INSERT INTO `seckill` VALUES ('1001', '500元秒杀ipad', '200', '2017-11-27 16:05:50', '2017-11-28 16:05:53', '2017-11-27 16:05:56');
INSERT INTO `seckill` VALUES ('1002', '300元秒杀红米', '300', '2017-11-27 16:06:08', '2017-11-28 16:06:11', '2017-11-27 16:06:17');
INSERT INTO `seckill` VALUES ('1003', '200元秒杀红米note', '400', '2017-11-27 16:06:38', '2017-11-28 16:06:42', '2017-11-27 16:06:47');

-- ----------------------------
-- Table structure for success_killed
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品id',
  `user_phone` bigint(20) NOT NULL COMMENT '用户手机号',
  `state` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态标识 -1:无效 0成功 1:已付款 2:已发货"',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`,`user_phone`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of success_killed
-- ----------------------------
