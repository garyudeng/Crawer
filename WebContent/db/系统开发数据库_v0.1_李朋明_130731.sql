CREATE DATABASE  IF NOT EXISTS `bimokudb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bimokudb`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: bimokudb
-- ------------------------------------------------------
-- Server version	5.5.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_author`
--

DROP TABLE IF EXISTS `t_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_author` (
  `ID` int(11) NOT NULL,
  `UUID` int(11) NOT NULL,
  `NAME` varchar(45) NOT NULL COMMENT '称谓',
  `IS_TRANSLATOR` int(11) NOT NULL DEFAULT '0' COMMENT '缺省为0,表示身份为作者\\n如果为1,表示是译者\\n如说是2,表示两个身份',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='作者（或译者）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_author`
--

LOCK TABLES `t_author` WRITE;
/*!40000 ALTER TABLE `t_author` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_book`
--

DROP TABLE IF EXISTS `t_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_book` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `UUID` varchar(45) DEFAULT NULL COMMENT 'UUID',
  `BOOKNAME` varchar(45) DEFAULT NULL COMMENT '图书名',
  `AUTHOR` varchar(45) DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) DEFAULT NULL COMMENT '出版社',
  `VERSION` varchar(45) DEFAULT NULL COMMENT '版本',
  `ISBN` varchar(20) DEFAULT NULL COMMENT '图书编号',
  `OUTLINE` varchar(2000) DEFAULT NULL COMMENT '图书概述',
  `WANTREAD` int(11) DEFAULT NULL COMMENT '想读人数',
  `READING` int(11) DEFAULT NULL COMMENT '在读人数',
  `READ` int(11) DEFAULT NULL COMMENT '读过人数',
  `DIRECTORY` varchar(1000) DEFAULT NULL COMMENT '目录',
  `PRICE` float NOT NULL COMMENT '价格',
  `COVER_PIC` varchar(45) DEFAULT NULL COMMENT '封面图片',
  `CLASSFY` varchar(45) DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_book`
--

LOCK TABLES `t_book` WRITE;
/*!40000 ALTER TABLE `t_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_book_detail`
--

DROP TABLE IF EXISTS `t_book_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_book_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` int(11) NOT NULL,
  `SITE` int(11) NOT NULL COMMENT '来自站点',
  `BOOKNAME` varchar(45) DEFAULT NULL COMMENT '书名',
  `CLASSFY` varchar(45) DEFAULT NULL COMMENT '分类',
  `AUTHOR` varchar(45) DEFAULT NULL COMMENT '作者',
  `TRANSLATOR` varchar(45) DEFAULT NULL COMMENT '译者',
  `PRESS` varchar(45) DEFAULT NULL COMMENT '出版社',
  `VERSION` int(11) DEFAULT NULL COMMENT '版本',
  `ISBN` varchar(16) DEFAULT NULL,
  `LEVEL` int(11) DEFAULT NULL COMMENT '评级',
  `DIRECTORY` varchar(1025) DEFAULT NULL COMMENT '目录',
  `PRICE` float NOT NULL COMMENT '单价',
  `COVER_PIC` varchar(45) DEFAULT NULL COMMENT '封面图片',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='图书明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_book_detail`
--

LOCK TABLES `t_book_detail` WRITE;
/*!40000 ALTER TABLE `t_book_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_book_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_comment_detail`
--

DROP TABLE IF EXISTS `t_comment_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` int(11) NOT NULL,
  `BOOKID` varchar(45) NOT NULL COMMENT '书籍id',
  `SIT` varchar(45) NOT NULL COMMENT '来自站点',
  `COM_USER` varchar(45) DEFAULT NULL COMMENT '评论人称谓',
  `COM_CONTENT` varchar(45) DEFAULT NULL COMMENT '评论内容',
  `COM_AVATAR` varchar(45) DEFAULT NULL COMMENT '评论人头像',
  `CRTEATE_AT` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_AT` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='图书评论明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment_detail`
--

LOCK TABLES `t_comment_detail` WRITE;
/*!40000 ALTER TABLE `t_comment_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_comment_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_note_detail`
--

DROP TABLE IF EXISTS `t_note_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_note_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(10) NOT NULL,
  `BOOKID` int(11) NOT NULL,
  `SITE` int(11) NOT NULL,
  `NOTE_CONTENT` varchar(1024) DEFAULT NULL COMMENT '笔记内容',
  `CREATE_AT` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_AT` timestamp NULL DEFAULT NULL COMMENT '跟新时间',
  `NOTE_USER` varchar(45) DEFAULT NULL COMMENT '记录人（称谓）',
  `NOTE_AVATAR` varchar(45) DEFAULT NULL COMMENT '头像连接',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='图书笔记明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_note_detail`
--

LOCK TABLES `t_note_detail` WRITE;
/*!40000 ALTER TABLE `t_note_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_note_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_press`
--

DROP TABLE IF EXISTS `t_press`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_press` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` int(11) NOT NULL,
  `NAME` varchar(45) NOT NULL COMMENT '称谓',
  `ADDRESS` varchar(45) DEFAULT NULL COMMENT '地址',
  `PHONE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='出版社';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_press`
--

LOCK TABLES `t_press` WRITE;
/*!40000 ALTER TABLE `t_press` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_press` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_store`
--

DROP TABLE IF EXISTS `t_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_store` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` int(11) DEFAULT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `SITE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='书店（在线）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_store`
--

LOCK TABLES `t_store` WRITE;
/*!40000 ALTER TABLE `t_store` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sumerize_detail`
--

DROP TABLE IF EXISTS `t_sumerize_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sumerize_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(10) NOT NULL,
  `BOOKID` int(11) NOT NULL,
  `SITE` int(11) NOT NULL COMMENT '1(当当)\\n2(亚马逊)\\n',
  `CONTENT_INTRO` varchar(1024) DEFAULT NULL COMMENT '内容介绍',
  `SERIES_INTRO` varchar(1024) DEFAULT NULL COMMENT '系列丛书介绍',
  `EDIT_INTRO` varchar(1024) DEFAULT NULL COMMENT '编辑介绍',
  `MEDIA_INTRO` varchar(1024) DEFAULT NULL COMMENT '媒体介绍',
  `AUTHOR_INTRO` varchar(1024) DEFAULT NULL COMMENT '作者介绍',
  `TRANSLATOR_INTRO` varchar(512) DEFAULT NULL COMMENT '译者介绍',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='图书概述详细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sumerize_detail`
--

LOCK TABLES `t_sumerize_detail` WRITE;
/*!40000 ALTER TABLE `t_sumerize_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sumerize_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_weibo_detail`
--

DROP TABLE IF EXISTS `t_weibo_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_weibo_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(45) NOT NULL,
  `BOOKID` int(11) NOT NULL COMMENT '所属书籍',
  `SITE` int(11) NOT NULL COMMENT '来自站点',
  `CREATE_AT` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_AT` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `WEIBO_CONTENT` varchar(45) DEFAULT NULL,
  `WEIBO_USER` varchar(45) DEFAULT NULL COMMENT '微博用户称谓',
  `WEIBO_AVATAR` varchar(45) DEFAULT NULL COMMENT '微博用户头像',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='图书微博';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_weibo_detail`
--

LOCK TABLES `t_weibo_detail` WRITE;
/*!40000 ALTER TABLE `t_weibo_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_weibo_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-31 14:10:24
