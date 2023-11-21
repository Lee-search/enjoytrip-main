-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ssafyvue
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `user_id` varchar(16) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(16) NOT NULL,
  `email_id` varchar(20) DEFAULT NULL,
  `email_domain` varchar(45) DEFAULT NULL,
  `join_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `token` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES ('admin','관리자','1234','admin','google.com','2023-10-08 16:10:08','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiaWF0IjoxNzAwNTQ5OTg4LCJleHAiOjE3MDMxNDE5ODgsInVzZXJJZCI6ImFkbWluIn0.W2wYI0TYZJfe85YvgX-ngRhP-Af1UpDQeoGpHzQ3erg'),('hissam','안효인','9876','hissam','ssafy.com','2023-10-26 14:52:05',NULL),('ssafy','김싸피','1234','ssafy','ssafy.com','2023-10-08 16:10:08','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiaWF0IjoxNzAwNDY5MDk3LCJleHAiOjE3MDMwNjEwOTcsInVzZXJJZCI6InNzYWZ5In0.0-mV2d8Uwg0zipmRPhVkFO6oP6UEaFWCFIvvSKzT2t4'),('ssafy.hong','홍싸피','1234','ssafy.hong','ssafy.com','2023-10-26 15:37:12',NULL),('ssafy.kim','김싸피','1234','ssafy.kim','ssafy.com','2023-10-26 15:33:08',NULL),('test','test12345','1234','test','ssafy.com','2023-11-20 01:05:05','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiaWF0IjoxNzAwNTQ3ODEwLCJleHAiOjE3MDMxMzk4MTAsInVzZXJJZCI6InRlc3QifQ.3MNn4r8fnL18qATcHlhV7XI_EUzKZ1XXPiX6QOz4DJk'),('test123','asdasd','1234','asdasd','ssafy.com','2023-11-20 08:27:25',NULL);
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-21 16:28:04
