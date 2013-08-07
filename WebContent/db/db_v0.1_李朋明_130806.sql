# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.5.14
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2013-08-06 13:23:20
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
  `UUID` varchar(10) CHARACTER SET latin1 NOT NULL,
  `NAME` varchar(45) CHARACTER SET latin1 NOT NULL COMMENT '称谓',
  `IS_TRANSLATOR` int(11) NOT NULL DEFAULT '0' COMMENT '缺省为0,表示身份为作者\\n如果为1,表示是译者\\n如说是2,表示两个身份',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='作者（或译者）';

# Dumping data for table bimokudb.t_author: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_author` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_author` ENABLE KEYS */;


# Dumping structure for table bimokudb.t_book
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE IF NOT EXISTS `t_book` (
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
  `HASREAD` int(11) DEFAULT NULL COMMENT '读过人数',
  `DIRECTORY` varchar(1000) DEFAULT NULL COMMENT '目录',
  `PRICE` double NOT NULL COMMENT '价格',
  `COVER_PIC` varchar(45) DEFAULT NULL COMMENT '封面图片',
  `CLASSFY` varchar(45) DEFAULT NULL COMMENT '分类',
  `RELATIONSHIP` varchar(500) DEFAULT NULL COMMENT '{''dd'':''20'',''amazon'':''231'',}',
  `t_bookcol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ISBN` (`ISBN`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

# Dumping data for table bimokudb.t_book: ~2 rows (approximately)
/*!40000 ALTER TABLE `t_book` DISABLE KEYS */;
INSERT INTO `t_book` (`ID`, `ISBN`, `UUID`, `BOOKNAME`, `AUTHOR`, `TRANSLATOR`, `PRESS`, `VERSION`, `OUTLINE`, `WANTREAD`, `READING`, `HASREAD`, `DIRECTORY`, `PRICE`, `COVER_PIC`, `CLASSFY`, `RELATIONSHIP`, `t_bookcol`) VALUES
	(1, '42141B', NULL, 'fffffffffff', '31kj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 22, NULL, NULL, '{"DD":"1","DB":"","AM":"2"}', NULL),
	(2, '32141B', NULL, 'aaaaaaaaaaa#$@$@', '31kj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 22, NULL, NULL, '{"DD":"2","DB":"","AM":"1"}', NULL);
/*!40000 ALTER TABLE `t_book` ENABLE KEYS */;


# Dumping structure for table bimokudb.t_bookamazon
DROP TABLE IF EXISTS `t_bookamazon`;
CREATE TABLE IF NOT EXISTS `t_bookamazon` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ISBN` varchar(20) CHARACTER SET latin1 DEFAULT NULL COMMENT '图书编号',
  `UUID` varchar(10) CHARACTER SET latin1 DEFAULT NULL COMMENT 'UUID',
  `BOOKNAME` varchar(45) CHARACTER SET latin1 NOT NULL COMMENT '图书名',
  `AUTHOR` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '出版社',
  `VERSION` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '版本',
  `OUTLINE` varchar(2000) CHARACTER SET latin1 DEFAULT NULL COMMENT '图书概述',
  `WANTREAD` int(11) DEFAULT NULL COMMENT '想读人数',
  `READING` int(11) DEFAULT NULL COMMENT '在读人数',
  `READ` int(11) DEFAULT NULL COMMENT '读过人数',
  `DIRECTORY` varchar(1000) CHARACTER SET latin1 DEFAULT NULL COMMENT '目录',
  `PRICE` double NOT NULL COMMENT '价格',
  `COVER_PIC` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '封面图片',
  `CLASSFY` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='在亚马逊网站上面的书籍详细信息';

# Dumping data for table bimokudb.t_bookamazon: ~2 rows (approximately)
/*!40000 ALTER TABLE `t_bookamazon` DISABLE KEYS */;
INSERT INTO `t_bookamazon` (`ID`, `ISBN`, `UUID`, `BOOKNAME`, `AUTHOR`, `TRANSLATOR`, `PRESS`, `VERSION`, `OUTLINE`, `WANTREAD`, `READING`, `READ`, `DIRECTORY`, `PRICE`, `COVER_PIC`, `CLASSFY`) VALUES
	(1, '32141B', '3124u091', 'aaaaaaaaaaa#$@$@', '31kj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 22, NULL, NULL),
	(2, '42141B', '3124u091', 'vvvvvvvvaaaaaaaaaaa#$@$@', '31kj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 22, NULL, NULL);
/*!40000 ALTER TABLE `t_bookamazon` ENABLE KEYS */;


# Dumping structure for table bimokudb.t_bookdd
DROP TABLE IF EXISTS `t_bookdd`;
CREATE TABLE IF NOT EXISTS `t_bookdd` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ISBN` varchar(20) CHARACTER SET latin1 DEFAULT NULL COMMENT '图书编号',
  `UUID` varchar(10) CHARACTER SET latin1 DEFAULT NULL COMMENT 'UUID',
  `BOOKNAME` varchar(45) CHARACTER SET latin1 NOT NULL COMMENT '图书名',
  `AUTHOR` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '出版社',
  `VERSION` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '版本',
  `OUTLINE` varchar(2000) CHARACTER SET latin1 DEFAULT NULL COMMENT '图书概述',
  `WANTREAD` int(11) DEFAULT NULL COMMENT '想读人数',
  `READING` int(11) DEFAULT NULL COMMENT '在读人数',
  `READ` int(11) DEFAULT NULL COMMENT '读过人数',
  `DIRECTORY` varchar(1000) CHARACTER SET latin1 DEFAULT NULL COMMENT '目录',
  `PRICE` double NOT NULL COMMENT '价格',
  `COVER_PIC` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '封面图片',
  `CLASSFY` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='当当网书籍详细信息';

# Dumping data for table bimokudb.t_bookdd: ~2 rows (approximately)
/*!40000 ALTER TABLE `t_bookdd` DISABLE KEYS */;
INSERT INTO `t_bookdd` (`ID`, `ISBN`, `UUID`, `BOOKNAME`, `AUTHOR`, `TRANSLATOR`, `PRESS`, `VERSION`, `OUTLINE`, `WANTREAD`, `READING`, `READ`, `DIRECTORY`, `PRICE`, `COVER_PIC`, `CLASSFY`) VALUES
	(1, '42141B', '3124u091', 'fffffffffff', '31kj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 22, NULL, NULL),
	(2, '32141B', '3124u091', 'cavdvas2222@@@@@@@@', '31kj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 22, NULL, NULL);
/*!40000 ALTER TABLE `t_bookdd` ENABLE KEYS */;


# Dumping structure for table bimokudb.t_book_detail
DROP TABLE IF EXISTS `t_book_detail`;
CREATE TABLE IF NOT EXISTS `t_book_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(10) CHARACTER SET latin1 NOT NULL,
  `SITE` int(11) NOT NULL COMMENT '来自站点',
  `BOOKNAME` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '书名',
  `CLASSFY` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '分类',
  `AUTHOR` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '出版社',
  `VERSION` int(11) DEFAULT NULL COMMENT '版本',
  `ISBN` varchar(16) CHARACTER SET latin1 DEFAULT NULL,
  `LEVEL` int(11) DEFAULT NULL COMMENT '评级',
  `DIRECTORY` varchar(1025) CHARACTER SET latin1 DEFAULT NULL COMMENT '目录',
  `PRICE` double NOT NULL COMMENT '单价',
  `COVER_PIC` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '封面图片',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图书明细表';

# Dumping data for table bimokudb.t_book_detail: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_book_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_book_detail` ENABLE KEYS */;


# Dumping structure for table bimokudb.t_comment_detail
DROP TABLE IF EXISTS `t_comment_detail`;
CREATE TABLE IF NOT EXISTS `t_comment_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(10) CHARACTER SET latin1 NOT NULL,
  `BOOKNAME` varchar(45) CHARACTER SET latin1 NOT NULL COMMENT '书籍',
  `SITE` int(11) NOT NULL COMMENT '来自站点',
  `COM_USER` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '评论人称谓',
  `COM_CONTENT` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '评论内容',
  `COM_AVATAR` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '评论人头像',
  `CRTEATE_AT` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_AT` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图书评论明细表';

# Dumping data for table bimokudb.t_comment_detail: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_comment_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_comment_detail` ENABLE KEYS */;


# Dumping structure for table bimokudb.t_note_detail
DROP TABLE IF EXISTS `t_note_detail`;
CREATE TABLE IF NOT EXISTS `t_note_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(10) CHARACTER SET latin1 NOT NULL,
  `BOOKID` int(11) NOT NULL,
  `SITE` int(11) NOT NULL,
  `NOTE_CONTENT` varchar(1024) CHARACTER SET latin1 DEFAULT NULL COMMENT '笔记内容',
  `CREATE_AT` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_AT` timestamp NULL DEFAULT NULL COMMENT '跟新时间',
  `NOTE_USER` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '记录人（称谓）',
  `NOTE_AVATAR` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '头像连接',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图书笔记明细表';

# Dumping data for table bimokudb.t_note_detail: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_note_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_note_detail` ENABLE KEYS */;


# Dumping structure for table bimokudb.t_press
DROP TABLE IF EXISTS `t_press`;
CREATE TABLE IF NOT EXISTS `t_press` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(10) CHARACTER SET latin1 NOT NULL,
  `NAME` varchar(45) CHARACTER SET latin1 NOT NULL COMMENT '称谓',
  `ADDRESS` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '地址',
  `PHONE` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出版社';

# Dumping data for table bimokudb.t_press: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_press` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_press` ENABLE KEYS */;


# Dumping structure for table bimokudb.t_store
DROP TABLE IF EXISTS `t_store`;
CREATE TABLE IF NOT EXISTS `t_store` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(10) CHARACTER SET latin1 DEFAULT NULL,
  `NAME` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `SITE` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='书店（在线）';

# Dumping data for table bimokudb.t_store: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_store` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_store` ENABLE KEYS */;


# Dumping structure for table bimokudb.t_sumerize_detail
DROP TABLE IF EXISTS `t_sumerize_detail`;
CREATE TABLE IF NOT EXISTS `t_sumerize_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(10) CHARACTER SET latin1 NOT NULL,
  `BOOKNAME` varchar(45) CHARACTER SET latin1 NOT NULL,
  `SITE` int(11) NOT NULL COMMENT '1(当当)\\n2(亚马逊)\\n',
  `CONTENT_INTRO` varchar(1024) CHARACTER SET latin1 DEFAULT NULL COMMENT '内容介绍',
  `SERIES_INTRO` varchar(1024) CHARACTER SET latin1 DEFAULT NULL COMMENT '系列丛书介绍',
  `EDIT_INTRO` varchar(1024) CHARACTER SET latin1 DEFAULT NULL COMMENT '编辑介绍',
  `MEDIA_INTRO` varchar(1024) CHARACTER SET latin1 DEFAULT NULL COMMENT '媒体介绍',
  `AUTHOR_INTRO` varchar(1024) CHARACTER SET latin1 DEFAULT NULL COMMENT '作者介绍',
  `TRANSLATOR_INTRO` varchar(512) CHARACTER SET latin1 DEFAULT NULL COMMENT '译者介绍',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图书概述详细表';

# Dumping data for table bimokudb.t_sumerize_detail: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_sumerize_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sumerize_detail` ENABLE KEYS */;


# Dumping structure for table bimokudb.t_weibo_detail
DROP TABLE IF EXISTS `t_weibo_detail`;
CREATE TABLE IF NOT EXISTS `t_weibo_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(45) CHARACTER SET latin1 NOT NULL,
  `BOOKID` int(11) NOT NULL COMMENT '所属书籍',
  `SITE` int(11) NOT NULL COMMENT '来自站点',
  `CREATE_AT` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_AT` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `WEIBO_CONTENT` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `WEIBO_USER` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '微博用户称谓',
  `WEIBO_AVATAR` varchar(45) CHARACTER SET latin1 DEFAULT NULL COMMENT '微博用户头像',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图书微博';

# Dumping data for table bimokudb.t_weibo_detail: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_weibo_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_weibo_detail` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
