# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.5.14
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2013-08-22 15:29:25
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
  `ISBN` varchar(15) NOT NULL COMMENT '图书编号',
  `BOOKNAME` varchar(45) NOT NULL COMMENT '图书名',
  `UUID` varchar(10) DEFAULT NULL COMMENT 'UUID',
  `AUTHOR` varchar(45) DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) DEFAULT NULL COMMENT '出版社',
  `VERSION` varchar(20) DEFAULT NULL COMMENT '版本',
  `WANTREAD` int(11) DEFAULT NULL COMMENT '想读人数',
  `READING` int(11) DEFAULT NULL COMMENT '在读人数',
  `HASREAD` int(11) DEFAULT NULL COMMENT '读过人数',
  `COVER_PIC` varchar(75) DEFAULT NULL COMMENT '封面图片',
  `CATELOG` varchar(50) DEFAULT NULL COMMENT '分类',
  `AUTHORINTRO` varchar(215) DEFAULT NULL COMMENT '作者简介',
  `DIRECTORY` varchar(1000) DEFAULT NULL COMMENT '目录',
  `OUTLINE` varchar(2000) DEFAULT NULL COMMENT '图书概述',
  `PRICE` double DEFAULT NULL COMMENT '价格',
  `PUB_PRICE` double DEFAULT NULL COMMENT '定价',
  `RELATIONSHIP` varchar(80) DEFAULT NULL COMMENT '{''dd'':''20'',''amazon'':''231'',}',
  `ALL_PRICE` varchar(80) DEFAULT NULL COMMENT '所有价格｛‘dd’:''22'',''amazon'':''23''｝',
  `ISLOCK` int(10) DEFAULT '0' COMMENT '是否被锁住。如果这则数据已经被豆瓣网操作了，那么这则数据被锁住【0表示为枷锁，！0表示以枷锁】',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISBN` (`ISBN`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='图书集成库';

# Data exporting was unselected.


# Dumping structure for table bimokudb.t_bookamazon
DROP TABLE IF EXISTS `t_bookamazon`;
CREATE TABLE IF NOT EXISTS `t_bookamazon` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ISBN` varchar(15) NOT NULL COMMENT '图书编号',
  `BOOKNAME` varchar(45) NOT NULL COMMENT '图书名',
  `UUID` varchar(10) DEFAULT NULL COMMENT 'UUID',
  `AUTHOR` varchar(45) DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) DEFAULT NULL COMMENT '出版社',
  `VERSION` varchar(20) DEFAULT NULL COMMENT '版本',
  `COVER_PIC` varchar(75) DEFAULT NULL COMMENT '封面图片',
  `CATELOG` varchar(50) DEFAULT NULL COMMENT '分类',
  `OUTLINE` varchar(2000) DEFAULT NULL COMMENT '图书概述',
  `DIRECTORY` varchar(1000) DEFAULT NULL COMMENT '目录',
  `AUTHORINTRO` varchar(215) DEFAULT NULL COMMENT '作者简介',
  `WANTREAD` int(11) DEFAULT NULL COMMENT '想读人数',
  `READING` int(11) DEFAULT NULL COMMENT '在读人数',
  `READ` int(11) DEFAULT NULL COMMENT '读过人数',
  `PRICE` double DEFAULT NULL COMMENT '价格',
  `PUB_PRICE` double DEFAULT NULL COMMENT '定价',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISBN` (`ISBN`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='在亚马逊网站上面的书籍详细信息';

# Data exporting was unselected.


# Dumping structure for table bimokudb.t_bookdb
DROP TABLE IF EXISTS `t_bookdb`;
CREATE TABLE IF NOT EXISTS `t_bookdb` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ISBN` varchar(15) NOT NULL COMMENT '图书编号',
  `BOOKNAME` varchar(45) NOT NULL COMMENT '图书名',
  `UUID` varchar(10) DEFAULT NULL COMMENT 'UUID',
  `AUTHOR` varchar(45) DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) DEFAULT NULL COMMENT '出版社',
  `VERSION` varchar(20) DEFAULT NULL COMMENT '版本',
  `COVER_PIC` varchar(75) DEFAULT NULL COMMENT '封面图片',
  `CATELOG` varchar(50) DEFAULT NULL COMMENT '分类',
  `OUTLINE` varchar(2000) DEFAULT NULL COMMENT '图书概述',
  `DIRECTORY` varchar(1000) DEFAULT NULL COMMENT '目录',
  `AUTHORINTRO` varchar(215) DEFAULT NULL COMMENT '作者简介',
  `WANTREAD` int(11) DEFAULT NULL COMMENT '想读人数',
  `READING` int(11) DEFAULT NULL COMMENT '在读人数',
  `READ` int(11) DEFAULT NULL COMMENT '读过人数',
  `PRICE` double DEFAULT NULL COMMENT '价格',
  `PUB_PRICE` double DEFAULT NULL COMMENT '定价',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISBN` (`ISBN`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='在豆瓣网站上面的书籍详细信息';

# Data exporting was unselected.


# Dumping structure for table bimokudb.t_bookdd
DROP TABLE IF EXISTS `t_bookdd`;
CREATE TABLE IF NOT EXISTS `t_bookdd` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ISBN` varchar(15) NOT NULL COMMENT '图书编号',
  `BOOKNAME` varchar(45) NOT NULL COMMENT '图书名',
  `UUID` varchar(10) DEFAULT NULL COMMENT 'UUID',
  `AUTHOR` varchar(45) DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) DEFAULT NULL COMMENT '出版社',
  `VERSION` varchar(20) DEFAULT NULL COMMENT '版本',
  `COVER_PIC` varchar(75) DEFAULT NULL COMMENT '封面图片',
  `CATELOG` varchar(50) DEFAULT NULL COMMENT '分类',
  `OUTLINE` varchar(2000) DEFAULT NULL COMMENT '图书概述',
  `DIRECTORY` varchar(1000) DEFAULT NULL COMMENT '目录',
  `AUTHORINTRO` varchar(215) DEFAULT NULL COMMENT '作者简介',
  `WANTREAD` int(11) DEFAULT NULL COMMENT '想读人数',
  `READING` int(11) DEFAULT NULL COMMENT '在读人数',
  `READ` int(11) DEFAULT NULL COMMENT '读过人数',
  `PRICE` double DEFAULT NULL COMMENT '价格',
  `PUB_PRICE` double DEFAULT NULL COMMENT '定价',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISBN` (`ISBN`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='在当当网站上面的书籍详细信息';

# Data exporting was unselected.


# Dumping structure for table bimokudb.t_bookjd
DROP TABLE IF EXISTS `t_bookjd`;
CREATE TABLE IF NOT EXISTS `t_bookjd` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ISBN` varchar(15) NOT NULL COMMENT '图书编号',
  `BOOKNAME` varchar(45) NOT NULL COMMENT '图书名',
  `UUID` varchar(10) DEFAULT NULL COMMENT 'UUID',
  `AUTHOR` varchar(45) DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) DEFAULT NULL COMMENT '出版社',
  `VERSION` varchar(20) DEFAULT NULL COMMENT '版本',
  `COVER_PIC` varchar(75) DEFAULT NULL COMMENT '封面图片',
  `CATELOG` varchar(50) DEFAULT NULL COMMENT '分类',
  `OUTLINE` varchar(2000) DEFAULT NULL COMMENT '图书概述',
  `DIRECTORY` varchar(1000) DEFAULT NULL COMMENT '目录',
  `AUTHORINTRO` varchar(215) DEFAULT NULL COMMENT '作者简介',
  `WANTREAD` int(11) DEFAULT NULL COMMENT '想读人数',
  `READING` int(11) DEFAULT NULL COMMENT '在读人数',
  `READ` int(11) DEFAULT NULL COMMENT '读过人数',
  `PRICE` double DEFAULT NULL COMMENT '价格',
  `PUB_PRICE` double DEFAULT NULL COMMENT '定价',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISBN` (`ISBN`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='在京东网站上面的书籍详细信息';

# Data exporting was unselected.


# Dumping structure for table bimokudb.t_bookpub
DROP TABLE IF EXISTS `t_bookpub`;
CREATE TABLE IF NOT EXISTS `t_bookpub` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ISBN` varchar(15) NOT NULL COMMENT '图书编号',
  `BOOKNAME` varchar(45) NOT NULL COMMENT '图书名',
  `UUID` varchar(10) DEFAULT NULL COMMENT 'UUID',
  `AUTHOR` varchar(45) DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) DEFAULT NULL COMMENT '出版社',
  `VERSION` varchar(20) DEFAULT NULL COMMENT '版本',
  `COVER_PIC` varchar(75) DEFAULT NULL COMMENT '封面图片',
  `CATELOG` varchar(50) DEFAULT NULL COMMENT '分类',
  `OUTLINE` varchar(2000) DEFAULT NULL COMMENT '图书概述',
  `DIRECTORY` varchar(1000) DEFAULT NULL COMMENT '目录',
  `AUTHORINTRO` varchar(215) DEFAULT NULL COMMENT '作者简介',
  `WANTREAD` int(11) DEFAULT NULL COMMENT '想读人数',
  `READING` int(11) DEFAULT NULL COMMENT '在读人数',
  `READ` int(11) DEFAULT NULL COMMENT '读过人数',
  `PRICE` double DEFAULT NULL COMMENT '价格',
  `PUB_PRICE` double DEFAULT NULL COMMENT '定价',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISBN` (`ISBN`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='在中国互动网站上面的书籍详细信息';

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
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
