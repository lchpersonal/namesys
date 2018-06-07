-- --------------------------------------------------------
-- 主机:                           rm-wz9o75099urn62399yo.mysql.rds.aliyuncs.com
-- 服务器版本:                        5.7.18-log - Source distribution
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  8.2.0.4675
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 namesys 的数据库结构
CREATE DATABASE IF NOT EXISTS `namesys` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `namesys`;


-- 导出  表 namesys.interactiveres 结构
CREATE TABLE IF NOT EXISTS `interactiveres` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `info` varchar(800) NOT NULL COMMENT '互动信息',
  `stars` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='互动资源';

-- 数据导出被取消选择。


-- 导出  表 namesys.interactiveres_new 结构
CREATE TABLE IF NOT EXISTS `interactiveres_new` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `net` char(2) NOT NULL COMMENT '所属网',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `sex` char(2) NOT NULL COMMENT '性别',
  `age` tinyint(4) NOT NULL COMMENT '年龄',
  `nativeplace` varchar(15) NOT NULL COMMENT '籍贯',
  `parent` varchar(10) NOT NULL COMMENT '推荐人',
  `tradition` varchar(100) NOT NULL COMMENT '传统行业',
  `payfor` varchar(100) NOT NULL COMMENT '行业付出',
  `tel` char(11) NOT NULL COMMENT '电话',
  `wechart` varchar(20) NOT NULL COMMENT '微信',
  `other` varchar(100) DEFAULT NULL COMMENT '其他',
  PRIMARY KEY (`id`),
  UNIQUE KEY `net_name` (`net`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='互动资源';

-- 数据导出被取消选择。


-- 导出  表 namesys.klg 结构
CREATE TABLE IF NOT EXISTS `klg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `addUser` varchar(20) NOT NULL,
  `addTime` datetime NOT NULL,
  `updateUser` varchar(20) NOT NULL,
  `updateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行业知识';

-- 数据导出被取消选择。


-- 导出  表 namesys.namedetailinfo 结构
CREATE TABLE IF NOT EXISTS `namedetailinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `name` varchar(10) NOT NULL,
  `sex` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '-1:未设置,0:男，1:女',
  `age` tinyint(4) NOT NULL DEFAULT '0',
  `nativePlace` varchar(20) DEFAULT NULL COMMENT '籍贯',
  `workplace` varchar(20) DEFAULT NULL COMMENT '工作地',
  `health` varchar(150) DEFAULT NULL COMMENT '健康状况',
  `education` varchar(100) DEFAULT NULL COMMENT '文化程度',
  `characteristics` varchar(100) DEFAULT NULL COMMENT '体貌特征',
  `relationship` varchar(100) DEFAULT NULL COMMENT '与我的关系',
  `coexistenceMode` varchar(500) DEFAULT NULL COMMENT '相处模式',
  `evaluateMe` varchar(100) DEFAULT NULL COMMENT '如何评价我',
  `workExperience` varchar(1000) DEFAULT NULL COMMENT '工作经历',
  `lifeExperience` varchar(1000) DEFAULT NULL COMMENT '重要的人生阅历',
  `income` varchar(100) DEFAULT NULL COMMENT '收入情况',
  `entrepreneurship` varchar(100) DEFAULT NULL COMMENT '是否支持经商和创业',
  `vacations` varchar(100) DEFAULT NULL,
  `maritalStatus` varchar(100) DEFAULT NULL COMMENT '婚姻状况',
  `memberOfFamily` varchar(100) DEFAULT NULL COMMENT '家庭成员',
  `familyStatus` varchar(100) DEFAULT NULL COMMENT '家庭地位',
  `familyIncomeAndSupport` varchar(200) DEFAULT NULL COMMENT '家庭主要收入和支持',
  `character` varchar(200) DEFAULT NULL COMMENT '个人性格',
  `policyConcern` varchar(100) DEFAULT NULL COMMENT '政策关注度',
  `loveReading` varchar(100) DEFAULT NULL COMMENT '是否爱看书',
  `hobby` varchar(100) DEFAULT NULL COMMENT '兴趣爱好',
  `specialty` varchar(100) DEFAULT NULL COMMENT '特长',
  `topicOfLike` varchar(100) DEFAULT NULL COMMENT '喜欢聊的话题',
  `dream` varchar(100) DEFAULT NULL COMMENT '梦想',
  `concept` varchar(100) DEFAULT NULL COMMENT '思想观念',
  `reasonOfInvite` varchar(200) DEFAULT NULL COMMENT '约他的理由',
  `others` varchar(500) DEFAULT NULL COMMENT '其他',
  `echelon` tinyint(4) NOT NULL DEFAULT '0' COMMENT '梯队',
  `pavingTimes` int(11) NOT NULL DEFAULT '0' COMMENT '铺垫次数',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1：已邀约，2邀约成功，3邀约失败，4带成，5带失败',
  `addTime` datetime NOT NULL COMMENT '添加时间',
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_name` (`username`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 namesys.pavingrecord 结构
CREATE TABLE IF NOT EXISTS `pavingrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nameInfoId` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `record` varchar(2000) DEFAULT NULL,
  `pavingTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `namelistId` (`nameInfoId`,`pavingTime`),
  KEY `username` (`username`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='铺垫记录';

-- 数据导出被取消选择。


-- 导出  表 namesys.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(20) NOT NULL,
  `parentUsername` varchar(20) DEFAULT NULL COMMENT '推荐人名字',
  `password` varchar(100) NOT NULL,
  `name` varchar(10) NOT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '-1:管理员，0:主任，1:经理,2:网管',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
