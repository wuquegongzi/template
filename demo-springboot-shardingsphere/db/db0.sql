/*
 Navicat Premium Data Transfer

 Source Server         : docker-mysql
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : db0

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 05/08/2021 18:30:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order0
-- ----------------------------
DROP TABLE IF EXISTS `t_order0`;
CREATE TABLE `t_order0` (
  `order_id` bigint NOT NULL COMMENT '订单流水ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户流水ID',
  `order_no` varchar(100) DEFAULT NULL COMMENT '订单编号',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order1
-- ----------------------------
DROP TABLE IF EXISTS `t_order1`;
CREATE TABLE `t_order1` (
  `order_id` bigint NOT NULL COMMENT '订单流水ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户流水ID',
  `order_no` varchar(100) DEFAULT NULL COMMENT '订单编号',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order2
-- ----------------------------
DROP TABLE IF EXISTS `t_order2`;
CREATE TABLE `t_order2` (
  `order_id` bigint NOT NULL COMMENT '订单流水ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户流水ID',
  `order_no` varchar(100) DEFAULT NULL COMMENT '订单编号',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order3
-- ----------------------------
DROP TABLE IF EXISTS `t_order3`;
CREATE TABLE `t_order3` (
  `order_id` bigint NOT NULL COMMENT '订单流水ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户流水ID',
  `order_no` varchar(100) DEFAULT NULL COMMENT '订单编号',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';

-- ----------------------------
-- Table structure for t_order_item0
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item0`;
CREATE TABLE `t_order_item0` (
  `order_item_id` bigint NOT NULL COMMENT '订单明细流水ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户流水ID',
  `order_id` bigint DEFAULT NULL COMMENT '订单流水ID',
  `seller_id` bigint DEFAULT NULL COMMENT '商家流水ID',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单明细';

-- ----------------------------
-- Table structure for t_order_item1
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item1`;
CREATE TABLE `t_order_item1` (
  `order_item_id` bigint NOT NULL COMMENT '订单明细流水ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户流水ID',
  `order_id` bigint DEFAULT NULL COMMENT '订单流水ID',
  `seller_id` bigint DEFAULT NULL COMMENT '商家流水ID',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单明细';

-- ----------------------------
-- Table structure for t_order_item2
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item2`;
CREATE TABLE `t_order_item2` (
  `order_item_id` bigint NOT NULL COMMENT '订单明细流水ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户流水ID',
  `order_id` bigint DEFAULT NULL COMMENT '订单流水ID',
  `seller_id` bigint DEFAULT NULL COMMENT '商家流水ID',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单明细';

-- ----------------------------
-- Table structure for t_order_item3
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item3`;
CREATE TABLE `t_order_item3` (
  `order_item_id` bigint NOT NULL COMMENT '订单明细流水ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户流水ID',
  `order_id` bigint DEFAULT NULL COMMENT '订单流水ID',
  `seller_id` bigint DEFAULT NULL COMMENT '商家流水ID',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单明细';

SET FOREIGN_KEY_CHECKS = 1;
