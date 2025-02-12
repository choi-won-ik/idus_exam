DROP DATABASE IF EXISTS test;
CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `test`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 10.10.10.11    Database: test
-- ------------------------------------------------------
-- Server version	5.5.5-10.6.18-MariaDB-0ubuntu0.22.04.1-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `email_verify`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
                          `idx` bigint(20) NOT NULL AUTO_INCREMENT,
                          `email` varchar(255) DEFAULT NULL,
                          `enabled` bit(1) NOT NULL,
                          `gender` enum('FEMALE','MALE','OTHER') DEFAULT NULL,
                          `name` varchar(255) DEFAULT NULL,
                          `nick_name` varchar(255) DEFAULT NULL,
                          `password` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'whwwhs7837@gmail.com',_binary '','MALE','최원익','최원익','$2a$10$DoefDUvdY7tiDKPwB15Jk.cjJEDh3eddQGrSDzU0dcQO4DDCx6q9a');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `email_verify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email_verify` (
  `idx` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) DEFAULT NULL,
  `member_idx` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `FKp8avtnemq9n8xbm9pbb6vq69u` (`member_idx`),
  CONSTRAINT `FKp8avtnemq9n8xbm9pbb6vq69u` FOREIGN KEY (`member_idx`) REFERENCES `member` (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_verify`
--

LOCK TABLES `email_verify` WRITE;
/*!40000 ALTER TABLE `email_verify` DISABLE KEYS */;
INSERT INTO `email_verify` VALUES (1,'298bcead-3bbb-46ab-b69d-79c2435d2249',1);
/*!40000 ALTER TABLE `email_verify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--


--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `idx` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_num` varchar(12) NOT NULL,
  `payment_date` datetime(6) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `member_idx` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `UKo6e714ot0hclyvhlcte6vc4mr` (`order_num`),
  KEY `FK55v8xjx00lm2iqk0f2yh46qsp` (`member_idx`),
  CONSTRAINT `FK55v8xjx00lm2iqk0f2yh46qsp` FOREIGN KEY (`member_idx`) REFERENCES `member` (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'534739234147','2025-02-11 07:50:59.562473','핸드폰',1),(2,'418119843403','2025-02-11 07:51:22.232264','노트북',1),(3,'644257804239','2025-02-11 07:51:26.400047','TV',1),(4,'051791110452','2025-02-11 07:51:31.949765','카메라',1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-12 11:15:09
