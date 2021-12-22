/*
 Navicat Premium Data Transfer

 Source Server         : zsk
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 127.0.0.1:3306
 Source Schema         : leavesystem

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 21/12/2021 17:49:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `adminid` int NOT NULL AUTO_INCREMENT,
  `adminname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`adminid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1001 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1001, 'admin');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `gradeid` int NOT NULL AUTO_INCREMENT,
  `gradename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`gradeid`) USING BTREE,
  INDEX `gradename`(`gradename`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 202019 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES (202003, '中一班');
INSERT INTO `grade` VALUES (202005, '中三班');
INSERT INTO `grade` VALUES (202004, '中二班');
INSERT INTO `grade` VALUES (202006, '大一班');
INSERT INTO `grade` VALUES (202007, '大二班');
INSERT INTO `grade` VALUES (202001, '小一班');
INSERT INTO `grade` VALUES (202002, '小二班');
INSERT INTO `grade` VALUES (202010, '幼稚班');
INSERT INTO `grade` VALUES (202008, '终极一班');

-- ----------------------------
-- Table structure for leavemsg
-- ----------------------------
DROP TABLE IF EXISTS `leavemsg`;
CREATE TABLE `leavemsg`  (
  `leaveid` int NOT NULL AUTO_INCREMENT,
  `starttime` datetime NULL DEFAULT NULL,
  `endtime` datetime NULL DEFAULT NULL,
  `studentname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gradename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teachername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parentname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `teacherdel` int NULL DEFAULT NULL,
  `studentdel` int NULL DEFAULT NULL,
  PRIMARY KEY (`leaveid`) USING BTREE,
  INDEX `studentname2`(`studentname`) USING BTREE,
  INDEX `teachername2`(`teachername`) USING BTREE,
  INDEX `parentname2`(`parentname`) USING BTREE,
  INDEX `gradename3`(`gradename`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leavemsg
-- ----------------------------
INSERT INTO `leavemsg` VALUES (1, '2020-09-11 19:20:19', '2020-09-12 19:20:24', '百小度', '中三班', '孙老师', '百大度', '同意', '游玩', 1, 1);
INSERT INTO `leavemsg` VALUES (3, '2020-03-14 00:00:00', '2020-03-16 00:00:00', '百小度', '中三班', '孙老师', '百大度', '拒绝', '看望外婆', 1, 1);
INSERT INTO `leavemsg` VALUES (6, '2016-06-06 00:00:00', '2016-06-10 00:00:00', '浩然', '中一班', '钱老师', '昊天', '拒绝', '参加比赛', 1, 1);
INSERT INTO `leavemsg` VALUES (7, '2020-04-20 00:00:00', '2020-04-21 13:03:00', '百小度', '中三班', '孙老师', '百大度', '拒绝', '观看比赛', 1, -1);
INSERT INTO `leavemsg` VALUES (8, '2020-01-15 17:13:57', '2020-01-17 00:00:00', '百小度', '中三班', '孙老师', '百小莲', '同意', '测试请假', NULL, NULL);
INSERT INTO `leavemsg` VALUES (9, '2020-09-01 14:00:00', '2020-09-02 08:00:00', '卢全煦', '中三班', '孙老师', '柯纤翊', '同意', '身体不适', NULL, NULL);
INSERT INTO `leavemsg` VALUES (10, '2020-09-13 00:00:00', '2020-09-15 10:00:00', '王成文', '中一班', '钱老师', '张昧谡', '同意', '病假', NULL, NULL);
INSERT INTO `leavemsg` VALUES (12, '2020-07-16 00:00:00', '2020-09-16 00:00:00', '王生安', '中三班', '正在等待', '翁编映', '等待批准', '测试请假', NULL, NULL);
INSERT INTO `leavemsg` VALUES (13, '2020-07-16 00:00:00', '2020-09-16 00:00:00', '王生安', '中三班', '正在等待', '翁编映', '等待批准', '测试请假', NULL, NULL);
INSERT INTO `leavemsg` VALUES (14, '2020-07-14 00:00:00', '2020-09-14 00:00:00', '王生安', '中三班', '正在等待', '翁编映', '等待批准', '测试请假3', NULL, NULL);
INSERT INTO `leavemsg` VALUES (16, '2020-05-01 00:00:00', '2020-05-05 00:00:00', '王生安', '中三班', '正在等待', '翁编映', '等待批准', '测试请假6', NULL, -1);
INSERT INTO `leavemsg` VALUES (18, '2020-10-01 00:00:00', '2020-09-07 00:00:00', '王生安', '中三班', '正在等待', '翁编映', '等待批准', '测试请假2', NULL, NULL);
INSERT INTO `leavemsg` VALUES (19, '2020-11-01 00:00:00', '2020-11-04 00:00:00', '王生安', '中三班', '正在等待', '翁编映', '等待批准', '请假测试9', NULL, NULL);
INSERT INTO `leavemsg` VALUES (21, '2020-07-19 00:00:00', '2020-07-21 00:00:00', '裕乾', '中一班', '钱老师', '彭笙葛', '同意', '测试请假', NULL, NULL);
INSERT INTO `leavemsg` VALUES (22, '2020-11-22 08:00:00', '2020-11-23 08:00:00', '龚佩义', '中二班', '李老师', '周昀薄', '同意', '身体不适', NULL, NULL);
INSERT INTO `leavemsg` VALUES (23, '2020-11-23 00:00:00', '2020-11-24 00:00:00', '百小度', '中三班', '孙老师', '百大度', '同意', '身体不适', NULL, NULL);

-- ----------------------------
-- Table structure for parent
-- ----------------------------
DROP TABLE IF EXISTS `parent`;
CREATE TABLE `parent`  (
  `parentid` int NOT NULL AUTO_INCREMENT,
  `parentname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `studentname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `studentid` int NULL DEFAULT NULL,
  PRIMARY KEY (`parentid`) USING BTREE,
  INDEX `studentname`(`studentname`) USING BTREE,
  INDEX `parentname`(`parentname`) USING BTREE,
  INDEX `studentid2`(`studentid`) USING BTREE,
  CONSTRAINT `studentid2` FOREIGN KEY (`studentid`) REFERENCES `student` (`studentid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `studentname` FOREIGN KEY (`studentname`) REFERENCES `student` (`studentname`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parent
-- ----------------------------
INSERT INTO `parent` VALUES (1, '百大度', '爸爸', '百小度', '男', '19459784545', 3016);
INSERT INTO `parent` VALUES (2, '昊天', '爸爸', '浩然', '男', '17896321234', 3001);
INSERT INTO `parent` VALUES (9, '百小莲', '妈妈', '百小度', '女', '15678941235', 3016);
INSERT INTO `parent` VALUES (10, '莫无双', '妈妈', '莫立恩', '女', '15633649513', 3044);
INSERT INTO `parent` VALUES (11, '汤始青', '妈妈', '张鸿鹤', '女', '15633649513', 3062);
INSERT INTO `parent` VALUES (12, '车亚忠', '妈妈', '欧贡界', '女', '15633649513', 3063);
INSERT INTO `parent` VALUES (13, '龙璨禄', '妈妈', '严席华', '女', '19459784545', 3061);
INSERT INTO `parent` VALUES (14, '柯纤翊', '妈妈', '卢全煦', '女', '15633649513', 3060);
INSERT INTO `parent` VALUES (15, '池慕营', '妈妈', '梁澄静', '女', '15633649513', 3059);
INSERT INTO `parent` VALUES (16, '吴登菘', '妈妈', '何刚名', '女', '15633649513', 3058);
INSERT INTO `parent` VALUES (17, '齐奋厦', '妈妈', '王赫颢', '女', '15633649513', 3057);
INSERT INTO `parent` VALUES (18, '夏莱冶', '妈妈', '卫惇琰', '女', '17789449874', 3056);
INSERT INTO `parent` VALUES (19, '元感棋', '妈妈', '陶臣杜', '女', '15633649513', 3055);
INSERT INTO `parent` VALUES (20, '陆示笙', '妈妈', '岑贝澄', '女', '15633649513', 3054);
INSERT INTO `parent` VALUES (21, '夏吏克', '爸爸', '贺　易', '男', '19459784545', 3053);
INSERT INTO `parent` VALUES (22, '简胜琰', '爸爸', '莫两敬', '男', '19459784545', 3052);
INSERT INTO `parent` VALUES (23, '苏经苞', '爸爸', '岳繁粟', '男', '19459784545', 3051);
INSERT INTO `parent` VALUES (24, '云介融', '爸爸', '卓互知', '男', '19459784545', 3050);
INSERT INTO `parent` VALUES (25, '何翼宪', '爸爸', '李彩早', '男', '19459784545', 3048);
INSERT INTO `parent` VALUES (26, '路壁桥', '爸爸', '邱靖祈', '男', '19459784545', 3047);
INSERT INTO `parent` VALUES (27, '殷蒙共', '爸爸', '郭磊留', '男', '12365498745', 3046);
INSERT INTO `parent` VALUES (28, '卞然倌', '爸爸', '戚渊苏', '男', '12365498745', 3045);
INSERT INTO `parent` VALUES (29, '吴来衷', '爸爸', '田稻善', '男', '12365498745', 3043);
INSERT INTO `parent` VALUES (30, '车瑛鸿', '爸爸', '萧百徽', '男', '12365498745', 3042);
INSERT INTO `parent` VALUES (31, '吴川宜', '爸爸', '汤丞昱', '男', '12365498745', 3041);
INSERT INTO `parent` VALUES (32, '张宁光', '爸爸', '陆丛枫', '男', '12365498745', 3040);
INSERT INTO `parent` VALUES (33, '刘慕骏', '爸爸', '朱付流', '男', '12365498745', 3039);
INSERT INTO `parent` VALUES (34, '张侠皆', '爸爸', '宁雨御', '男', '12365498745', 3038);
INSERT INTO `parent` VALUES (35, '欧彩徽', '爸爸', '蔡容富', '男', '12365498745', 3037);
INSERT INTO `parent` VALUES (37, '秦尝乐', '爸爸', '文壮翔', '男', '13678945698', 3035);
INSERT INTO `parent` VALUES (38, '金图康', '妈妈', '卢钦钧', '女', '13678945698', 3034);
INSERT INTO `parent` VALUES (39, '齐聪纪', '妈妈', '邱栾树', '女', '13678945698', 3033);
INSERT INTO `parent` VALUES (40, '龚杭维', '妈妈', '唐盎正', '女', '13678945698', 3028);
INSERT INTO `parent` VALUES (41, '葛赋铎', '妈妈', '王施峪', '女', '13678945698', 3027);
INSERT INTO `parent` VALUES (42, '池亭其', '妈妈', '邹包幼', '女', '13678945698', 3026);
INSERT INTO `parent` VALUES (43, '成河灿', '妈妈', '易江维', '女', '13678945698', 3025);
INSERT INTO `parent` VALUES (44, '周宪道', '妈妈', '周卓浩', '女', '13678945698', 3024);
INSERT INTO `parent` VALUES (45, '翁编映', '妈妈', '王生安', '女', '13678945698', 3023);
INSERT INTO `parent` VALUES (46, '戚扬伙', '爸爸', '王生安', '男', '15687496325', 3023);
INSERT INTO `parent` VALUES (47, '郑夜洲', '爸爸', '周卓浩', '男', '15687496325', 3024);
INSERT INTO `parent` VALUES (48, '蒲元明', '爸爸', '易江维', '男', '15687496325', 3025);
INSERT INTO `parent` VALUES (50, '梁夜翊', '爸爸', '俞灶迟', '男', '12345678410', NULL);
INSERT INTO `parent` VALUES (51, '成昀澄', '妈妈', '俞灶迟', '女', '13645852621', NULL);
INSERT INTO `parent` VALUES (53, '林静育', '爷爷', '俞灶迟', '男', '13648965424', NULL);
INSERT INTO `parent` VALUES (54, '张昧谡', '爸爸', '王成文', '男', '13648594784', NULL);
INSERT INTO `parent` VALUES (55, '彭笙葛', '爸爸', '裕乾', '男', '13648965410', NULL);
INSERT INTO `parent` VALUES (56, '周昀薄', '爸爸', '龚佩义', '男', '13645847894', NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `studentid` int NOT NULL AUTO_INCREMENT,
  `studentname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gradename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inschooltime` date NULL DEFAULT NULL,
  PRIMARY KEY (`studentid`) USING BTREE,
  INDEX `studentname`(`studentname`) USING BTREE,
  INDEX `gradename`(`gradename`) USING BTREE,
  CONSTRAINT `gradename` FOREIGN KEY (`gradename`) REFERENCES `grade` (`gradename`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3069 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (3001, '浩然', '中一班', '2014-12-30', '女', '2018-08-08');
INSERT INTO `student` VALUES (3002, '李田林', '小二班', '2014-09-21', '男', '2018-09-21');
INSERT INTO `student` VALUES (3003, '映玲', '中一班', '2016-12-30', '男', '2019-05-11');
INSERT INTO `student` VALUES (3004, '弘历', '中二班', '2017-09-15', '男', '2019-06-21');
INSERT INTO `student` VALUES (3005, '玉琼', '中三班', '2016-04-03', '男', '2019-07-11');
INSERT INTO `student` VALUES (3006, '陈捷', '大一班', '2017-09-15', '男', '2019-09-17');
INSERT INTO `student` VALUES (3007, '燕军', '大二班', '2017-09-15', '男', '2019-09-21');
INSERT INTO `student` VALUES (3008, '闫艺', '小一班', '2015-07-16', '男', '2020-06-16');
INSERT INTO `student` VALUES (3010, '裕乾', '中一班', '2017-09-15', '女', '2019-11-12');
INSERT INTO `student` VALUES (3011, '任容', '小二班', '2016-04-03', '女', '2019-10-16');
INSERT INTO `student` VALUES (3012, '文杰', '大一班', '2017-09-15', '女', '2018-12-21');
INSERT INTO `student` VALUES (3013, '金龙', '大二班', '2015-07-16', '女', '2018-11-11');
INSERT INTO `student` VALUES (3014, '林峰', '中三班', '2016-04-03', '女', '2020-01-05');
INSERT INTO `student` VALUES (3015, '梦琪', '终极一班', '2016-04-10', '男', '2019-11-11');
INSERT INTO `student` VALUES (3016, '百小度', '中三班', '2015-06-01', '男', '2020-01-05');
INSERT INTO `student` VALUES (3021, '晓松', '中一班', '2018-09-14', '男', '2020-09-14');
INSERT INTO `student` VALUES (3022, '杜湖怡', '幼稚班', '2018-09-03', '男', '2021-09-16');
INSERT INTO `student` VALUES (3023, '王生安', '中三班', '2015-06-01', '女', '2019-09-01');
INSERT INTO `student` VALUES (3024, '周卓浩', '中三班', '2015-06-01', '男', '2019-09-01');
INSERT INTO `student` VALUES (3025, '易江维', '中三班', '2015-06-01', '女', '2019-09-01');
INSERT INTO `student` VALUES (3026, '邹包幼', '中三班', '2015-06-01', '女', '2019-09-01');
INSERT INTO `student` VALUES (3027, '王施峪', '中三班', '2016-04-10', '男', '2019-09-01');
INSERT INTO `student` VALUES (3028, '唐盎正', '中三班', '2015-06-01', '女', '2019-09-01');
INSERT INTO `student` VALUES (3029, '邱栾树', '中三班', '2015-06-01', '女', '2019-09-01');
INSERT INTO `student` VALUES (3030, '党舒', '中三班', '2016-04-10', '男', '2019-09-01');
INSERT INTO `student` VALUES (3031, '周逸依', '中三班', '2015-06-01', '男', '2019-09-01');
INSERT INTO `student` VALUES (3032, '齐寒昇', '中三班', '2015-06-01', '女', '2019-09-01');
INSERT INTO `student` VALUES (3033, '邱栾树', '中三班', '2016-04-10', '男', '2019-09-01');
INSERT INTO `student` VALUES (3034, '卢钦钧', '中三班', '2015-06-01', '女', '2019-09-01');
INSERT INTO `student` VALUES (3035, '文壮翔', '中三班', '2016-04-10', '男', '2019-09-01');
INSERT INTO `student` VALUES (3037, '蔡容富', '中三班', '2015-06-01', '女', '2019-09-01');
INSERT INTO `student` VALUES (3038, '宁雨御', '中三班', '2015-06-01', '男', '2019-09-01');
INSERT INTO `student` VALUES (3039, '朱付流', '中三班', '2016-04-10', '女', '2019-09-01');
INSERT INTO `student` VALUES (3040, '陆丛枫', '中三班', '2015-06-01', '男', '2019-09-01');
INSERT INTO `student` VALUES (3041, '汤丞昱', '中三班', '2016-04-10', '女', '2019-09-01');
INSERT INTO `student` VALUES (3042, '萧百徽', '中三班', '2015-06-01', '女', '2019-09-01');
INSERT INTO `student` VALUES (3043, '田稻善', '中三班', '2015-06-01', '男', '2019-09-01');
INSERT INTO `student` VALUES (3044, '莫立恩', '中三班', '2016-04-10', '女', '2019-09-01');
INSERT INTO `student` VALUES (3045, '戚渊苏', '中三班', '2016-04-10', '男', '2019-09-01');
INSERT INTO `student` VALUES (3046, '郭磊留', '中三班', '2016-04-10', '男', '2019-09-01');
INSERT INTO `student` VALUES (3047, '邱靖祈', '中三班', '2015-06-01', '女', '2019-09-01');
INSERT INTO `student` VALUES (3048, '刘鲜发', '中三班', '2016-04-10', '女', '2019-09-01');
INSERT INTO `student` VALUES (3049, '李彩早', '中三班', '2016-04-10', '男', '2019-09-01');
INSERT INTO `student` VALUES (3050, '卓互知', '中三班', '2016-04-10', '女', '2019-09-01');
INSERT INTO `student` VALUES (3051, '岳繁粟', '中三班', '2016-04-10', '男', '2019-09-01');
INSERT INTO `student` VALUES (3052, '莫两敬', '中三班', '2015-06-01', '男', '2019-09-01');
INSERT INTO `student` VALUES (3053, '贺　易', '中三班', '2016-04-10', '女', '2019-09-01');
INSERT INTO `student` VALUES (3054, '岑贝澄', '中三班', '2015-06-01', '男', '2019-09-01');
INSERT INTO `student` VALUES (3055, '陶臣杜', '中三班', '2016-04-10', '女', '2019-09-01');
INSERT INTO `student` VALUES (3056, '卫惇琰', '中三班', '2016-04-10', '女', '2019-09-01');
INSERT INTO `student` VALUES (3057, '王赫颢', '中三班', '2015-06-01', '女', '2019-09-01');
INSERT INTO `student` VALUES (3058, '何刚名', '中三班', '2016-04-10', '男', '2019-09-01');
INSERT INTO `student` VALUES (3059, '梁澄静', '中三班', '2016-04-10', '女', '2019-09-01');
INSERT INTO `student` VALUES (3060, '卢全煦', '中三班', '2016-04-10', '女', '2019-09-01');
INSERT INTO `student` VALUES (3061, '严席华', '中三班', '2015-06-01', '男', '2019-09-01');
INSERT INTO `student` VALUES (3062, '张鸿鹤', '中三班', '2015-06-01', '女', '2019-09-01');
INSERT INTO `student` VALUES (3063, '欧贡界', '中三班', '2016-04-10', '男', '2019-09-01');
INSERT INTO `student` VALUES (3064, '俞灶迟', '中一班', '2015-09-15', '男', '2018-09-29');
INSERT INTO `student` VALUES (3065, '王成文', '中一班', '2015-09-17', '男', '2019-09-15');
INSERT INTO `student` VALUES (3066, '龚佩义', '中二班', '2012-09-22', '男', '2016-09-12');
INSERT INTO `student` VALUES (3067, '严君岚', '中二班', '2013-09-22', '男', '2019-09-08');
INSERT INTO `student` VALUES (3069, '志坤', '中一班', '2011-09-23', '男', '2014-09-07');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacherid` int NOT NULL AUTO_INCREMENT,
  `teachername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gradename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `degree` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inschooltime` date NULL DEFAULT NULL,
  PRIMARY KEY (`teacherid`) USING BTREE,
  INDEX `gradename1`(`gradename`) USING BTREE,
  INDEX `teachername`(`teachername`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2024 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (2001, '张老师', '小一班', '男', '1998-08-18', '本科', '2010-10-10');
INSERT INTO `teacher` VALUES (2002, '赵老师', '小二班', '男', '1996-06-12', '硕士', '2010-02-15');
INSERT INTO `teacher` VALUES (2003, '钱老师', '中一班', '男', '1989-10-30', '硕士', '2009-12-12');
INSERT INTO `teacher` VALUES (2004, '孙老师', '中三班', '女', '1995-01-24', '本科', '2011-01-29');
INSERT INTO `teacher` VALUES (2005, '李老师', '中二班', '女', '1992-07-17', '硕士', '2011-05-29');
INSERT INTO `teacher` VALUES (2006, '周老师', '大一班', '女', '1994-04-14', '本科', '2010-03-06');
INSERT INTO `teacher` VALUES (2007, '吴老师', '大二班', '女', '1991-09-01', '博士', '2007-05-07');
INSERT INTO `teacher` VALUES (2008, '王老师', '小一班', '男', '1996-11-06', '本科', '2009-12-12');
INSERT INTO `teacher` VALUES (2009, '蒋老师', '幼稚班', '女', '1998-10-10', '本科', '2009-12-12');
INSERT INTO `teacher` VALUES (2013, '冯老师', '中一班', '女', '1987-12-21', '本科', '2010-10-10');
INSERT INTO `teacher` VALUES (2014, '陈老师', '中一班', '女', '1996-10-18', '本科', '2010-10-10');
INSERT INTO `teacher` VALUES (2015, '褚卫老师', '无', '女', '1996-09-11', '本科', '2009-12-12');
INSERT INTO `teacher` VALUES (2016, '沈老师', '终极一班', '男', '1999-09-09', '博士', '2010-03-06');
INSERT INTO `teacher` VALUES (2017, '韩老师', '无', '男', '1996-10-10', '硕士', '2010-03-06');
INSERT INTO `teacher` VALUES (2022, '王施峪', '无', '男', '1991-05-05', '硕士', '2014-09-16');
INSERT INTO `teacher` VALUES (2023, '彭笙葛', '无', '男', '1990-10-11', '硕士', '2018-09-17');

-- ----------------------------
-- Table structure for userlogin
-- ----------------------------
DROP TABLE IF EXISTS `userlogin`;
CREATE TABLE `userlogin`  (
  `userid` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uploadfile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2080 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userlogin
-- ----------------------------
INSERT INTO `userlogin` VALUES (1001, 'admin', 'abc_123', '管理员', 'files/b86f94cb-f46e-4a65-8379-820d2ffdaa4c_IMG20151120001.jpg');
INSERT INTO `userlogin` VALUES (1002, '张老师', 'abc.123', '教师', NULL);
INSERT INTO `userlogin` VALUES (1003, '李老师', 'abc.123', '教师', 'files/7fe3aaf0-b529-4334-b903-430e06d91852_1.jpg');
INSERT INTO `userlogin` VALUES (1004, '百小度', '123', '学生', 'files/767b0190-9e31-42a8-8dfd-aaf513b7a668_P60814-164934.jpg');
INSERT INTO `userlogin` VALUES (2002, '孙老师', '123456', '教师', 'files/45dca1d1-faec-4077-9994-53c3a75a10b3_P71111-162011.jpg.JPG');
INSERT INTO `userlogin` VALUES (2003, '田琳', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2004, '钱老师', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2005, '赵老师', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2006, '周老师', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2007, '吴老师', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2008, '王老师', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2009, '蒋老师', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2010, '冯老师', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2011, '陈老师', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2012, '褚卫老师', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2013, '沈老师', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2014, '韩老师', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2015, '映玲', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2016, '弘历', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2017, '玉琼', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2018, '陈捷', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2019, '燕军', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2020, '闫艺', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2021, '裕乾', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2022, '任容', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2023, '文杰', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2024, '金龙', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2025, '林峰', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2026, '梦琪', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2027, '晓松', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2028, '杜湖怡', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2029, '王施峪', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2030, '王生安', '123456', '学生', 'files/0c69a73b-a9b7-4e29-bcb7-1822769eb098_IMG20160720110045.jpg');
INSERT INTO `userlogin` VALUES (2031, '周卓浩', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2032, '易江维', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2033, '邹包幼', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2034, '王施峪', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2035, '唐盎正', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2036, '邱栾树', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2037, '党舒', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2038, '周逸依', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2039, '齐寒昇', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2040, '邱栾树', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2041, '卢钦钧', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2042, '文壮翔', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2043, '蔡沐壮', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2044, '蔡容富', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2045, '宁雨御', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2046, '朱付流', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2047, '陆丛枫', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2048, '汤丞昱', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2049, '萧百徽', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2050, '田稻善', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2051, '莫立恩', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2052, '戚渊苏', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2053, '郭磊留', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2054, '邱靖祈', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2055, '刘鲜发', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2056, '李彩早', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2057, '卓互知', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2058, '岳繁粟', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2059, '莫两敬', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2060, '贺　易', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2061, '岑贝澄', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2062, '陶臣杜', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2063, '卫惇琰', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2064, '王赫颢', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2065, '何刚名', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2066, '梁澄静', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2067, '卢全煦', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2068, '严席华', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2069, '张鸿鹤', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2070, '欧贡界', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2071, '俞灶迟', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2072, '王成文', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2073, '王施峪', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2074, '王施峪', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2075, '彭笙葛', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2076, '龚佩义', 'abc123', '学生', 'files/b447d183-8212-46d6-9fee-6801f50e92e6_2.jpg');
INSERT INTO `userlogin` VALUES (2077, '严君岚', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2078, '侯真堂', '123456', '学生', NULL);
INSERT INTO `userlogin` VALUES (2079, '龙铮财', '123456', '教师', NULL);
INSERT INTO `userlogin` VALUES (2080, '志坤', '123456', '学生', NULL);

SET FOREIGN_KEY_CHECKS = 1;
