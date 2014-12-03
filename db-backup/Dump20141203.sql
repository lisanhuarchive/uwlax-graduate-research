CREATE DATABASE IF NOT EXISTS `attendance` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `attendance`;
-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: 127.0.0.1    Database: attendance
-- ------------------------------------------------------
-- Server version	5.6.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `admin_id` VARCHAR(50) NOT NULL,
  `col_pswd` VARCHAR(64) NOT NULL,
  `col_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`admin_id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'some one');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `cid`      INT(11)     NOT NULL AUTO_INCREMENT,
  `c_no`     VARCHAR(45) NOT NULL,
  `section`  VARCHAR(45) NOT NULL,
  `c_title`  VARCHAR(45) NOT NULL,
  `teacher`  VARCHAR(50) NOT NULL,
  `c_time`   VARCHAR(45)          DEFAULT NULL,
  `capacity` INT(11)              DEFAULT '0',
  `weekdays` INT(11)              DEFAULT '0',
  `term`     INT(11)     NOT NULL,
  `activate` TINYINT(1)  NOT NULL DEFAULT '0',
  PRIMARY KEY (`cid`),
  KEY `teacher` (`teacher`),
  KEY `term` (`term`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher`) REFERENCES `teacher` (`teacher_id`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`term`) REFERENCES `term` (`term_id`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =7
  DEFAULT CHARSET =latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (4, 'CS 441', '100', 'OS Concept', 'T123', '7:45-9:20', 20, 20, 6, 0),
  (5, 'CS 541', '200', 'OS Concept', 'T123', '7:45-9:20', 30, 20, 6, 1),
  (6, 'CS 741', '01', 'Software Engineering Principles ', '1', '11:00-12:25', 5, 20, 6, 1);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade` (
  `gid`     INT(11) NOT NULL AUTO_INCREMENT,
  `scid`    INT(11) NOT NULL,
  `item_id` INT(11) NOT NULL,
  `grade`   INT(11) NOT NULL,
  PRIMARY KEY (`gid`),
  KEY `scid` (`scid`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`scid`) REFERENCES `student_course` (`scid`),
  CONSTRAINT `grade_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `grade_item` (`item_id`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =2
  DEFAULT CHARSET =latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1, 2, 10, 20);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade_item`
--

DROP TABLE IF EXISTS `grade_item`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade_item` (
  `item_id`   INT(11)     NOT NULL AUTO_INCREMENT,
  `item_name` VARCHAR(45) NOT NULL,
  `total`     INT(11)     NOT NULL,
  `cid`       INT(11)     NOT NULL,
  `is_valid`  INT(11)     NOT NULL DEFAULT '1',
  PRIMARY KEY (`item_id`),
  KEY `cid_idx` (`cid`),
  CONSTRAINT `cid` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE =InnoDB
  AUTO_INCREMENT =11
  DEFAULT CHARSET =latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade_item`
--

LOCK TABLES `grade_item` WRITE;
/*!40000 ALTER TABLE `grade_item` DISABLE KEYS */;
INSERT INTO `grade_item`
VALUES (6, 'item 1', 10, 6, 1), (7, 'item 2', 20, 6, 0), (8, 'item 3', 30, 6, 0), (9, 'item 4', 40, 6, 0),
  (10, 'item 5', 50, 6, 1);
/*!40000 ALTER TABLE `grade_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `record` (
  `rid`     INT(11) NOT NULL AUTO_INCREMENT,
  `cid`     INT(11) NOT NULL,
  `is_open` INT(11) NOT NULL,
  PRIMARY KEY (`rid`),
  KEY `cid` (`cid`),
  CONSTRAINT `record_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =7
  DEFAULT CHARSET =latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` VALUES (1, 6, 0), (2, 6, 0), (3, 6, 0), (4, 6, 0), (5, 6, 0), (6, 6, 1);
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id`       VARCHAR(50) NOT NULL,
  `student_name`     VARCHAR(50) NOT NULL,
  `student_password` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`student_id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('333', '123', '556d7dc3a115356350f1f9910b1af1ab0e312d4b3e4fc788d2da63668f36d017'),
  ('957427426', 'Sanhu Li', '206e59c050468c29d63d9d2be30abd75c7e75094f5a0e0a5b7c01c454c6d2798'),
  ('999999999', 'test names', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_course` (
  `scid`       INT(11)     NOT NULL AUTO_INCREMENT,
  `student_id` VARCHAR(50) NOT NULL,
  `course_id`  INT(11)     NOT NULL,
  `is_valid`   INT(11)     NOT NULL DEFAULT '1',
  PRIMARY KEY (`scid`),
  UNIQUE KEY `student_id` (`student_id`, `course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `student_course_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `student_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`cid`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =5
  DEFAULT CHARSET =latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

LOCK TABLES `student_course` WRITE;
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES (1, '957427426', 5, 1), (2, '957427426', 6, 1), (3, '333', 6, 0), (4, '333', 5, 0);
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `student_course_view`
--

DROP TABLE IF EXISTS `student_course_view`;
/*!50001 DROP VIEW IF EXISTS `student_course_view`*/;
SET @saved_cs_client = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `student_course_view` AS
  SELECT
    1 AS `scid`,
    1 AS `student_id`,
    1 AS `student_name`,
    1 AS `student_password`,
    1 AS `cid`,
    1 AS `c_no`,
    1 AS `section`,
    1 AS `c_title`,
    1 AS `teacher`,
    1 AS `c_time`,
    1 AS `capacity`,
    1 AS `weekdays`,
    1 AS `term`,
    1 AS `activate`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `student_record`
--

DROP TABLE IF EXISTS `student_record`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_record` (
  `srid`        INT(11)     NOT NULL AUTO_INCREMENT,
  `scid`        INT(11)     NOT NULL,
  `rid`         INT(11)     NOT NULL,
  `record_time` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`srid`),
  UNIQUE KEY `rid_scid_UNIQUE` (`rid`, `scid`),
  KEY `scid` (`scid`),
  CONSTRAINT `student_record_ibfk_1` FOREIGN KEY (`scid`) REFERENCES `student_course` (`scid`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =5
  DEFAULT CHARSET =latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_record`
--

LOCK TABLES `student_record` WRITE;
/*!40000 ALTER TABLE `student_record` DISABLE KEYS */;
INSERT INTO `student_record` VALUES (1, 2, 4, '12/03/2014'), (3, 2, 6, '12/03/2014');
/*!40000 ALTER TABLE `student_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `teacher_id`       VARCHAR(50) NOT NULL,
  `teacher_name`     VARCHAR(50) NOT NULL,
  `teacher_password` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`teacher_id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('1', 'Mao Zheng', '3ed0256da8da5eabae7aa1680886a2aa394dd7c002eb2f3b02e9f0f9ec9daa2c'),
  ('T123', 'Professor A', '206e59c050468c29d63d9d2be30abd75c7e75094f5a0e0a5b7c01c454c6d2798'),
  ('T123456', '123456', '0866a99e11ebe15bd4c8a664df452dee8bb0f9f3da62fa4ba855a7203f7bd4ea');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `term`
--

DROP TABLE IF EXISTS `term`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `term` (
  `term_id`    INT(11)     NOT NULL AUTO_INCREMENT,
  `term_name`  VARCHAR(45) NOT NULL,
  `term_start` VARCHAR(45) NOT NULL,
  `term_end`   VARCHAR(45) NOT NULL,
  `activated`  TINYINT(1)  NOT NULL DEFAULT '1',
  PRIMARY KEY (`term_id`),
  UNIQUE KEY `term_name_UNIQUE` (`term_name`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =7
  DEFAULT CHARSET =latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `term`
--

LOCK TABLES `term` WRITE;
/*!40000 ALTER TABLE `term` DISABLE KEYS */;
INSERT INTO `term`
VALUES (1, '2013 Fall', '09/02/2013', '12/19/2013', 1), (5, '2014 Spring', '01/27/2014', '05/19/2014', 1),
  (6, '2014 fall', '09/01/2014', '12/19/2014', 1);
/*!40000 ALTER TABLE `term` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'attendance'
--

--
-- Final view structure for view `student_course_view`
--

/*!50001 DROP VIEW IF EXISTS `student_course_view`*/;
/*!50001 SET @saved_cs_client = @@character_set_client */;
/*!50001 SET @saved_cs_results = @@character_set_results */;
/*!50001 SET @saved_col_connection = @@collation_connection */;
/*!50001 SET character_set_client = utf8 */;
/*!50001 SET character_set_results = utf8 */;
/*!50001 SET collation_connection = utf8_general_ci */;
/*!50001 CREATE ALGORITHM = UNDEFINED */
/*!50013 DEFINER =`lsh`@`%`
  SQL SECURITY DEFINER */
/*!50001 VIEW `student_course_view` AS
  SELECT
    `sc`.`scid`            AS `scid`,
    `s`.`student_id`       AS `student_id`,
    `s`.`student_name`     AS `student_name`,
    `s`.`student_password` AS `student_password`,
    `c`.`cid`              AS `cid`,
    `c`.`c_no`             AS `c_no`,
    `c`.`section`          AS `section`,
    `c`.`c_title`          AS `c_title`,
    `c`.`teacher`          AS `teacher`,
    `c`.`c_time`           AS `c_time`,
    `c`.`capacity`         AS `capacity`,
    `c`.`weekdays`         AS `weekdays`,
    `c`.`term`             AS `term`,
    `c`.`activate`         AS `activate`
  FROM ((`student_course` `sc` JOIN `student` `s`) JOIN `course` `c`)
  WHERE ((`sc`.`student_id` = `s`.`student_id`) AND (`sc`.`course_id` = `c`.`cid`)) */;
/*!50001 SET character_set_client = @saved_cs_client */;
/*!50001 SET character_set_results = @saved_cs_results */;
/*!50001 SET collation_connection = @saved_col_connection */;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2014-12-03 12:36:48
