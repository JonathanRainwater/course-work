-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: 
-- ------------------------------------------------------
-- Server version	5.7.22

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
-- Current Database: `c2375a25proj`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `c2375a25proj` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `c2375a25proj`;

--
-- Table structure for table `CAMERA`
--

DROP TABLE IF EXISTS `CAMERA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CAMERA` (
  `CAMERA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `URL` tinytext NOT NULL,
  `TITLE` varchar(100) DEFAULT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`CAMERA_ID`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `CAMERA_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`USER_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CAMERA`
--

LOCK TABLES `CAMERA` WRITE;
/*!40000 ALTER TABLE `CAMERA` DISABLE KEYS */;
INSERT INTO `CAMERA` VALUES (3,'http://128.151.166.50/mjpg/video.mjpg','Somewhere in the USA',3),(13,'http://68.157.197.211/mjpg/video.mjpg','Inside of store',1),(15,'http://89.169.2.76:81/mjpg/video.mjpg','Somewhere in Russia',1),(57,'http://70.182.67.95:88/mjpg/video.mjpg','Oklahoma City',3),(59,'http://134.114.250.90/mjpg/video.mjpg','A Hallway in Flagstaff AZ',3),(63,'http://166.130.128.91:81/mjpg/video.mjpg','Counter in Oklahoma',1),(64,'http://220.73.58.90/mjpg/video.mjpg','Korean Intersection B',1);
/*!40000 ALTER TABLE `CAMERA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LOGIN`
--

DROP TABLE IF EXISTS `LOGIN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LOGIN` (
  `LOGIN_TIME` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `F_NAME` varchar(50) DEFAULT NULL,
  `M_INITIAL` char(1) DEFAULT NULL,
  `L_NAME` varchar(50) DEFAULT NULL,
  `IP` varchar(39) NOT NULL,
  `VALID` tinyint(1) NOT NULL,
  PRIMARY KEY (`LOGIN_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOGIN`
--

LOCK TABLES `LOGIN` WRITE;
/*!40000 ALTER TABLE `LOGIN` DISABLE KEYS */;
INSERT INTO `LOGIN` VALUES ('2018-12-01 23:46:34.955452','','','','1.2.3.4',0),('2018-12-01 23:46:37.579103','','','','1.2.3.4',0),('2018-12-01 23:46:38.513005','','','','1.2.3.4',0),('2018-12-01 23:46:39.348107','','','','1.2.3.4',0),('2018-12-01 23:46:40.142297','','','','1.2.3.4',0),('2018-12-01 23:46:40.694580','','','','1.2.3.4',0),('2018-12-01 23:46:41.098892','','','','1.2.3.4',0),('2018-12-01 23:46:41.532490','','','','1.2.3.4',0),('2018-12-01 23:46:41.972080','','','','1.2.3.4',0),('2018-12-01 23:46:42.440070','','','','1.2.3.4',0),('2018-12-01 23:46:42.914520','','','','1.2.3.4',0),('2018-12-01 23:46:43.429690','','','','1.2.3.4',0),('2018-12-02 00:17:54.711561','a','a','a','1.2.3.4',0),('2018-12-02 00:38:03.365593','DD','D','ddd','1.2.3.4',0),('2018-12-02 00:38:23.591012','','','','1.2.3.4',0),('2018-12-02 00:38:24.332206','','','','1.2.3.4',0),('2018-12-02 00:38:25.088292','','','','1.2.3.4',0),('2018-12-02 00:38:25.850289','','','','1.2.3.4',0),('2018-12-02 01:05:45.382713','','','','1.2.3.4',0),('2018-12-02 01:07:02.048097','','','','1.2.3.4',0),('2018-12-02 01:15:45.063681','','','','1.2.3.4',0),('2018-12-02 01:17:36.094897','','','','1.2.3.4',0),('2018-12-02 01:18:58.904415','','','','1.2.3.4',0),('2018-12-02 01:19:24.121608','','','','1.2.3.4',0),('2018-12-02 01:19:56.434069','d','s','aa','1.2.3.4',0),('2018-12-02 01:30:32.769747','','','','1.2.3.4',0),('2018-12-02 01:31:11.101320','','','','1.2.3.4',0),('2018-12-02 01:32:26.024903','','','','1.2.3.4',0),('2018-12-02 01:33:00.115822','','','','1.2.3.4',0);
/*!40000 ALTER TABLE `LOGIN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REPORT`
--

DROP TABLE IF EXISTS `REPORT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REPORT` (
  `REPORT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `UPLOAD_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `BODY` text NOT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`REPORT_ID`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `REPORT_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`USER_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REPORT`
--

LOCK TABLES `REPORT` WRITE;
/*!40000 ALTER TABLE `REPORT` DISABLE KEYS */;
INSERT INTO `REPORT` VALUES (2,'2017-12-24 23:00:00','Tonight I spotted a large, overweight tresspasser climbing around on my neighbor\'s roof near his chimney. I yelled at him that I was going to get my shotgun and that he\'d better be gone when I got back. When I returned, I heard some odd jingling noises but didn\'t see him again. He was dressed all in red. I think he was a gang member.',2),(3,'2018-10-31 19:00:00','I had a knock on the door this evening. When I opened it, there was a highly agitated man with his shirt pulled over his head. He was trembling and screaming at me in an incoherent manner. When I asked him who he was and what he wanted, he shouted that he was \"the great Cornholio,\" and that he needed a teepee, or something. Fortunately, I managed to slam the door shut and lock it before he made it inside. Dear God, what\'s the world coming to?',3),(38,'2018-12-02 19:14:08','A UFO abducted my dog off of the back porch just now! They said they needed a brave warrior to go and fight the evil Cat Lord and save their planet from being used as his intergalactic scratching post! Why didn\'t they take someone else\'s dog? Now what will I do with my wife\'s cooking that I normally pretend to eat but feed to the dog instead when she isn\'t looking? Now I\'m going to have to actually eat that stuff!',1);
/*!40000 ALTER TABLE `REPORT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `L_NAME` varchar(50) NOT NULL,
  `F_NAME` varchar(50) DEFAULT NULL,
  `M_INITIAL` char(1) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `IS_ADMIN` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES (1,'Anderson','Tom','J','$2y$10$lqyyLLMJkKxFS6eRztjSZ.RvrTgZOuYmtfU4NFMkcrQs3xuCURII2',NULL),(2,'Smith','Edgar','A','$2y$10$YsyxCMFnGf10R6BxHy0so.yg8ehL2nKkdxz5hCdWGCWjvQkNKvTeu',NULL),(3,'Moseby','Ralph','B','$2y$10$h6/7fvPcQxYYfhw5hqQNROC3SDiLLnMyHZrd619VnXHdp99BYij6q',NULL),(4,'Rainwater','Jonathan','D','$2y$10$9dFj7ZHl620XlkSNPgypYOvhydE3vPNbLIngA4S.QWLZwLympHh76',1);
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;


-- Dump completed on 2018-12-16 10:46:55
