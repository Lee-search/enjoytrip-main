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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `article_no` int NOT NULL,
  `user_id` varchar(16) DEFAULT NULL,
  `comment_text` varchar(1000) DEFAULT NULL,
  `comment_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  KEY `comments_to_board_article_fk` (`article_no`),
  KEY `comments_to_members_user_id_fk` (`user_id`),
  CONSTRAINT `comments_to_board_article_fk` FOREIGN KEY (`article_no`) REFERENCES `board` (`article_no`),
  CONSTRAINT `comments_to_members_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,251,'ssafy','댓글 테스트','2023-11-19 12:56:53'),(2,251,'ssafy','댓글테스트입니다1','2023-11-20 07:13:25'),(3,251,'ssafy','댓글테스트입니다2','2023-11-20 07:13:25'),(4,251,'ssafy','댓글테스트입니다3','2023-11-20 07:13:25'),(5,251,'ssafy','댓글테스트입니다4','2023-11-20 07:13:25'),(6,251,'ssafy','댓글테스트입니다5555555555555','2023-11-20 08:03:54'),(7,251,'ssafy','1','2023-11-20 08:04:45'),(8,251,'ssafy','2','2023-11-20 08:07:07'),(20,248,'ssafy','123123123','2023-11-20 08:31:44'),(21,248,'ssafy','123123','2023-11-20 08:32:17'),(22,248,'ssafy','123123','2023-11-20 08:34:07'),(23,248,'ssafy','123123','2023-11-20 08:38:33'),(27,251,'test','asdasdasd','2023-11-20 08:52:15'),(28,251,'test','asdasd','2023-11-20 08:52:17'),(30,251,'test','123','2023-11-21 04:35:12');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
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
