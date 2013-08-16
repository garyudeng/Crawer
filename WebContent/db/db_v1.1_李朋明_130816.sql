# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.5.14
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2013-08-16 15:37:22
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for bimokudb
CREATE DATABASE IF NOT EXISTS `bimokudb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bimokudb`;


# Dumping structure for table bimokudb.t_author
DROP TABLE IF EXISTS `t_author`;
CREATE TABLE IF NOT EXISTS `t_author` (
  `ID` int(11) NOT NULL,
  `UUID` varchar(10) NOT NULL,
  `NAME` varchar(45) NOT NULL COMMENT '称谓',
  `IS_TRANSLATOR` int(11) NOT NULL DEFAULT '0' COMMENT '缺省为0,表示身份为作者\\n如果为1,表示是译者\\n如说是2,表示两个身份',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='作者（或译者）';

# Data exporting was unselected.


# Dumping structure for table bimokudb.t_book
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE IF NOT EXISTS `t_book` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ISBN` varchar(20) DEFAULT NULL COMMENT '图书编号',
  `UUID` varchar(10) DEFAULT NULL COMMENT 'UUID',
  `BOOKNAME` varchar(512) NOT NULL COMMENT '图书名',
  `AUTHOR` varchar(45) DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) DEFAULT NULL COMMENT '出版社',
  `VERSION` varchar(45) DEFAULT NULL COMMENT '版本',
  `OUTLINE` varchar(2000) DEFAULT NULL COMMENT '图书概述',
  `WANTREAD` int(11) DEFAULT NULL COMMENT '想读人数',
  `READING` int(11) DEFAULT NULL COMMENT '在读人数',
  `HASREAD` int(11) DEFAULT NULL COMMENT '读过人数',
  `DIRECTORY` varchar(1000) DEFAULT NULL COMMENT '目录',
  `CATELOG` varchar(215) DEFAULT NULL COMMENT '分类',
  `AUTHORINTRO` varchar(215) DEFAULT NULL COMMENT '作者简介',
  `PRICE` double NOT NULL COMMENT '价格',
  `COVER_PIC` varchar(45) DEFAULT NULL COMMENT '封面图片',
  `CLASSFY` varchar(45) DEFAULT NULL COMMENT '分类',
  `RELATIONSHIP` varchar(500) DEFAULT NULL COMMENT '{''dd'':''20'',''amazon'':''231'',}',
  `t_bookcol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISBN` (`ISBN`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='图书集成库';

# Data exporting was unselected.


# Dumping structure for table bimokudb.t_bookamazon
DROP TABLE IF EXISTS `t_bookamazon`;
CREATE TABLE IF NOT EXISTS `t_bookamazon` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ISBN` varchar(20) DEFAULT NULL COMMENT '图书编号',
  `UUID` varchar(10) DEFAULT NULL COMMENT 'UUID',
  `BOOKNAME` varchar(45) NOT NULL COMMENT '图书名',
  `AUTHOR` varchar(45) DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) DEFAULT NULL COMMENT '出版社',
  `VERSION` varchar(45) DEFAULT NULL COMMENT '版本',
  `OUTLINE` varchar(2000) DEFAULT NULL COMMENT '图书概述',
  `WANTREAD` int(11) DEFAULT NULL COMMENT '想读人数',
  `READING` int(11) DEFAULT NULL COMMENT '在读人数',
  `READ` int(11) DEFAULT NULL COMMENT '读过人数',
  `DIRECTORY` varchar(1000) DEFAULT NULL COMMENT '目录',
  `PRICE` double NOT NULL COMMENT '价格',
  `COVER_PIC` varchar(45) DEFAULT NULL COMMENT '封面图片',
  `CATELOG` varchar(215) DEFAULT NULL COMMENT '分类',
  `AUTHORINTRO` varchar(215) DEFAULT NULL COMMENT '作者简介',
  `CLASSFY` varchar(45) DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISBN` (`ISBN`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='在亚马逊网站上面的书籍详细信息';

# Data exporting was unselected.


# Dumping structure for table bimokudb.t_bookdb
DROP TABLE IF EXISTS `t_bookdb`;
CREATE TABLE IF NOT EXISTS `t_bookdb` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ISBN` varchar(20) DEFAULT NULL COMMENT '图书编号',
  `UUID` varchar(10) DEFAULT NULL COMMENT 'UUID',
  `BOOKNAME` varchar(45) NOT NULL COMMENT '图书名',
  `AUTHOR` varchar(45) DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) DEFAULT NULL COMMENT '出版社',
  `VERSION` varchar(45) DEFAULT NULL COMMENT '版本',
  `OUTLINE` varchar(2000) DEFAULT NULL COMMENT '图书概述',
  `WANTREAD` int(11) DEFAULT NULL COMMENT '想读人数',
  `READING` int(11) DEFAULT NULL COMMENT '在读人数',
  `READ` int(11) DEFAULT NULL COMMENT '读过人数',
  `DIRECTORY` varchar(1000) DEFAULT NULL COMMENT '目录',
  `PRICE` double NOT NULL COMMENT '价格',
  `COVER_PIC` varchar(45) DEFAULT NULL COMMENT '封面图片',
  `CATELOG` varchar(215) DEFAULT NULL COMMENT '分类',
  `AUTHORINTRO` varchar(215) DEFAULT NULL COMMENT '作者简介',
  `CLASSFY` varchar(45) DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISBN` (`ISBN`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='豆瓣网站上面的书籍详细信息';

# Data exporting was unselected.


# Dumping structure for table bimokudb.t_bookdd
DROP TABLE IF EXISTS `t_bookdd`;
CREATE TABLE IF NOT EXISTS `t_bookdd` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ISBN` varchar(20) DEFAULT NULL COMMENT '图书编号',
  `UUID` varchar(10) DEFAULT NULL COMMENT 'UUID',
  `BOOKNAME` varchar(45) NOT NULL COMMENT '图书名',
  `AUTHOR` varchar(45) DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) DEFAULT NULL COMMENT '出版社',
  `VERSION` varchar(45) DEFAULT NULL COMMENT '版本',
  `OUTLINE` varchar(2000) DEFAULT NULL COMMENT '图书概述',
  `WANTREAD` int(11) DEFAULT NULL COMMENT '想读人数',
  `READING` int(11) DEFAULT NULL COMMENT '在读人数',
  `READ` int(11) DEFAULT NULL COMMENT '读过人数',
  `DIRECTORY` varchar(1000) DEFAULT NULL COMMENT '目录',
  `PRICE` double NOT NULL COMMENT '价格',
  `COVER_PIC` varchar(45) DEFAULT NULL COMMENT '封面图片',
  `CATELOG` varchar(215) DEFAULT NULL COMMENT '分类',
  `AUTHORINTRO` varchar(215) DEFAULT NULL COMMENT '作者简介',
  `CLASSFY` varchar(45) DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISBN` (`ISBN`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='dangdang网站上面的书籍详细信息';

# Data exporting was unselected.


# Dumping structure for table bimokudb.t_bookjd
DROP TABLE IF EXISTS `t_bookjd`;
CREATE TABLE IF NOT EXISTS `t_bookjd` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ISBN` varchar(20) DEFAULT NULL COMMENT '图书编号',
  `UUID` varchar(10) DEFAULT NULL COMMENT 'UUID',
  `BOOKNAME` varchar(45) NOT NULL COMMENT '图书名',
  `AUTHOR` varchar(45) DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) DEFAULT NULL COMMENT '出版社',
  `VERSION` varchar(45) DEFAULT NULL COMMENT '版本',
  `OUTLINE` varchar(2000) DEFAULT NULL COMMENT '图书概述',
  `WANTREAD` int(11) DEFAULT NULL COMMENT '想读人数',
  `READING` int(11) DEFAULT NULL COMMENT '在读人数',
  `READ` int(11) DEFAULT NULL COMMENT '读过人数',
  `DIRECTORY` varchar(1000) DEFAULT NULL COMMENT '目录',
  `PRICE` double NOT NULL COMMENT '价格',
  `COVER_PIC` varchar(45) DEFAULT NULL COMMENT '封面图片',
  `CATELOG` varchar(215) DEFAULT NULL COMMENT '分类',
  `AUTHORINTRO` varchar(215) DEFAULT NULL COMMENT '作者简介',
  `CLASSFY` varchar(45) DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISBN` (`ISBN`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='京东网站上面的书籍详细信息';

# Data exporting was unselected.


# Dumping structure for table bimokudb.t_press
DROP TABLE IF EXISTS `t_press`;
CREATE TABLE IF NOT EXISTS `t_press` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(10) NOT NULL,
  `NAME` varchar(45) NOT NULL COMMENT '称谓',
  `ADDRESS` varchar(45) DEFAULT NULL COMMENT '地址',
  `PHONE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='出版社';

# Data exporting was unselected.


# Dumping structure for table bimokudb.t_store
DROP TABLE IF EXISTS `t_store`;
CREATE TABLE IF NOT EXISTS `t_store` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(10) DEFAULT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `SITE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='书店（在线）';

# Data exporting was unselected.
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
