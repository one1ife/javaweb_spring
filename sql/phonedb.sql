/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : phonedb

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 13/06/2022 19:38:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for myphone
-- ----------------------------
DROP TABLE IF EXISTS `myphone`;
CREATE TABLE `myphone`  (
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` int NULL DEFAULT NULL,
  `count` int NULL DEFAULT NULL,
  `soc` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of myphone
-- ----------------------------
INSERT INTO `myphone` VALUES ('iPhone13', 5999, 13, 'A15');
INSERT INTO `myphone` VALUES ('iPhone13 Pro', 7999, 50, 'A15');
INSERT INTO `myphone` VALUES ('三星S22', 4999, 99, '骁龙8 Gen1');
INSERT INTO `myphone` VALUES ('Redmi K50', 1999, 14, '天玑8100');
INSERT INTO `myphone` VALUES ('iPhone12', 4999, 768, 'A14');
INSERT INTO `myphone` VALUES ('iPhone11', 4499, 18, 'A13');
INSERT INTO `myphone` VALUES ('荣耀70', 2699, 231, '骁龙778G');
INSERT INTO `myphone` VALUES ('荣耀70 Pro+', 4200, 30, '天玑9000');
INSERT INTO `myphone` VALUES ('荣耀70 Pro', 3699, 99, '天玑8000');
INSERT INTO `myphone` VALUES ('魅族18', 2699, 19, '骁龙888');
INSERT INTO `myphone` VALUES ('一加7', 2999, 35, '骁龙855');
INSERT INTO `myphone` VALUES ('一加7 Pro', 4499, 13, '骁龙855');

SET FOREIGN_KEY_CHECKS = 1;
