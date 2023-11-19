-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: ssafyvue
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `article_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `hit` int DEFAULT '0',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  KEY `board_to_members_user_id_fk` (`user_id`),
  CONSTRAINT `board_to_members_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (2,'ssafy','스프링 DI 테스트중','ㅇㅇ',1,'2023-10-17 08:06:05'),(3,'ssafy','testing','testing',0,'2023-10-17 15:13:31'),(4,'ssafy','aop테스트중 around임','그렇다고',0,'2023-10-18 14:52:22'),(5,'ssafy','11','22',0,'2023-10-18 14:53:45'),(6,'ssafy','33','44',0,'2023-10-18 14:56:41'),(7,'ssafy','55','66',0,'2023-10-18 14:59:17'),(8,'ssafy','11','암호화한 내용 ::: 22',0,'2023-10-18 15:11:52'),(9,'ssafy','spring test 제목 수정해요!!!!@@@','spring test 내용도 수정해요!!!@@@',0,'2023-10-18 15:12:54'),(10,'ssafy','11','22',0,'2023-10-18 15:16:55'),(11,'ssafy','11','22',0,'2023-10-18 15:18:31'),(12,'ssafy','12','12',0,'2023-10-18 15:22:45'),(13,'ssafy','11','22',1,'2023-10-18 15:24:23'),(14,'ssafy','11','22',0,'2023-10-18 15:25:31'),(15,'ssafy','33','33',0,'2023-10-18 15:27:08'),(16,'ssafy','file upload test','test',1,'2023-10-23 07:30:08'),(17,'ssafy','파일 업로드 테스트중임다','ㅇㅇ',0,'2023-10-23 15:01:33'),(18,'ssafy','test2','test2',0,'2023-10-23 15:05:20'),(19,'ssafy','mybatis test!!!','dd',2,'2023-10-24 12:14:36'),(20,'ssafy','test ing ~~','test ing ~~',0,'2023-10-28 16:31:57'),(21,'ssafy','test 중입니다. 1','test 중입니다.',0,'2023-10-28 16:34:17'),(22,'ssafy','test 중입니다. 1 2','test 중입니다.',0,'2023-10-28 16:34:18'),(23,'ssafy','test 중입니다. 1 2 3','test 중입니다.',0,'2023-10-28 16:34:18'),(24,'ssafy','test 중입니다. 1 2 3 4','test 중입니다.',0,'2023-10-28 16:34:18'),(25,'ssafy','test 중입니다. 1 2 3 4 5','test 중입니다.',0,'2023-10-28 16:34:18'),(26,'ssafy','test 중입니다. 1 2 3 4 5 6','test 중입니다.',0,'2023-10-28 16:34:18'),(27,'ssafy','test 중입니다. 1 2 3 4 5 6 7','test 중입니다.',0,'2023-10-28 16:34:18'),(28,'ssafy','test 중입니다. 1 2 3 4 5 6 7 8','test 중입니다.',0,'2023-10-28 16:34:18'),(29,'ssafy','test 중입니다. 1 2 3 4 5 6 7 8 9','test 중입니다.',0,'2023-10-28 16:34:18'),(30,'ssafy','test 중입니다. 1 2 3 4 5 6 7 8 9 10','test 중입니다.',0,'2023-10-28 16:34:18'),(31,'admin','테스트 중입니다. 1','테스트 중입니다.',0,'2023-10-28 16:35:43'),(32,'admin','테스트 중입니다. 2','테스트 중입니다.',0,'2023-10-28 16:35:43'),(33,'admin','테스트 중입니다. 3','테스트 중입니다.',0,'2023-10-28 16:35:43'),(34,'admin','테스트 중입니다. 4','테스트 중입니다.',0,'2023-10-28 16:35:43'),(35,'admin','테스트 중입니다. 5','테스트 중입니다.',1,'2023-10-28 16:35:43'),(36,'admin','테스트 중입니다. 6','테스트 중입니다.',0,'2023-10-28 16:35:43'),(37,'admin','테스트 중입니다. 7','테스트 중입니다.',0,'2023-10-28 16:35:43'),(38,'admin','테스트 중입니다. 8','테스트 중입니다.',0,'2023-10-28 16:35:43'),(39,'admin','테스트 중입니다. 9','테스트 중입니다.',0,'2023-10-28 16:35:43'),(40,'admin','테스트 중입니다. 10','테스트 중입니다.',0,'2023-10-28 16:35:43'),(41,'admin','글이 많이 필요해요 1','글이 많이 필요해요',0,'2023-10-28 16:35:54'),(42,'admin','글이 많이 필요해요 2','글이 많이 필요해요',0,'2023-10-28 16:35:54'),(43,'admin','글이 많이 필요해요 3','글이 많이 필요해요',0,'2023-10-28 16:35:54'),(44,'admin','글이 많이 필요해요 4','글이 많이 필요해요',0,'2023-10-28 16:35:54'),(45,'admin','글이 많이 필요해요 5','글이 많이 필요해요',0,'2023-10-28 16:35:54'),(46,'admin','글이 많이 필요해요 6','글이 많이 필요해요',0,'2023-10-28 16:35:54'),(47,'admin','글이 많이 필요해요 7','글이 많이 필요해요',0,'2023-10-28 16:35:54'),(48,'admin','글이 많이 필요해요 8','글이 많이 필요해요',0,'2023-10-28 16:35:54'),(49,'admin','글이 많이 필요해요 9','글이 많이 필요해요',0,'2023-10-28 16:35:55'),(50,'admin','글이 많이 필요해요 10','글이 많이 필요해요',1,'2023-10-28 16:35:55'),(51,'ssafy','vuejs 연습중입니다. 1','vuejs 연습중입니다.',0,'2023-10-28 16:36:20'),(52,'ssafy','vuejs 연습중입니다. 2','vuejs 연습중입니다.',0,'2023-10-28 16:36:20'),(53,'ssafy','vuejs 연습중입니다. 3','vuejs 연습중입니다.',0,'2023-10-28 16:36:20'),(54,'ssafy','vuejs 연습중입니다. 4','vuejs 연습중입니다.',0,'2023-10-28 16:36:20'),(55,'ssafy','vuejs 연습중입니다. 5','vuejs 연습중입니다.',0,'2023-10-28 16:36:20'),(56,'ssafy','vuejs 연습중입니다. 6','vuejs 연습중입니다.',0,'2023-10-28 16:36:20'),(57,'ssafy','vuejs 연습중입니다. 7','vuejs 연습중입니다.',0,'2023-10-28 16:36:20'),(58,'ssafy','vuejs 연습중입니다. 8','vuejs 연습중입니다.',0,'2023-10-28 16:36:21'),(59,'ssafy','vuejs 연습중입니다. 9','vuejs 연습중입니다.',0,'2023-10-28 16:36:21'),(60,'ssafy','vuejs 연습중입니다. 10','vuejs 연습중입니다.',0,'2023-10-28 16:36:21'),(61,'ssafy','아주아주 잼나요 1','아주아주 잼나요',0,'2023-10-28 16:36:32'),(62,'ssafy','아주아주 잼나요 2','아주아주 잼나요',0,'2023-10-28 16:36:32'),(63,'ssafy','아주아주 잼나요 3','아주아주 잼나요',0,'2023-10-28 16:36:32'),(64,'ssafy','아주아주 잼나요 4','아주아주 잼나요',0,'2023-10-28 16:36:32'),(65,'ssafy','아주아주 잼나요 5','아주아주 잼나요',0,'2023-10-28 16:36:32'),(66,'ssafy','아주아주 잼나요 6','아주아주 잼나요',0,'2023-10-28 16:36:32'),(67,'ssafy','아주아주 잼나요 7','아주아주 잼나요',0,'2023-10-28 16:36:32'),(68,'ssafy','아주아주 잼나요 8','아주아주 잼나요',0,'2023-10-28 16:36:32'),(69,'ssafy','아주아주 잼나요 9','아주아주 잼나요',0,'2023-10-28 16:36:32'),(70,'ssafy','아주아주 잼나요 10','아주아주 잼나요',0,'2023-10-28 16:36:33'),(71,'ssafy','언능 끝내고 싶당 1','언능 끝내고 싶당',0,'2023-10-28 16:37:07'),(72,'ssafy','언능 끝내고 싶당 2','언능 끝내고 싶당',0,'2023-10-28 16:37:07'),(73,'ssafy','언능 끝내고 싶당 3','언능 끝내고 싶당',0,'2023-10-28 16:37:07'),(74,'ssafy','언능 끝내고 싶당 4','언능 끝내고 싶당',0,'2023-10-28 16:37:07'),(75,'ssafy','언능 끝내고 싶당 5','언능 끝내고 싶당',0,'2023-10-28 16:37:07'),(76,'ssafy','언능 끝내고 싶당 6','언능 끝내고 싶당',0,'2023-10-28 16:37:07'),(77,'ssafy','언능 끝내고 싶당 7','언능 끝내고 싶당',0,'2023-10-28 16:37:07'),(78,'ssafy','언능 끝내고 싶당 8','언능 끝내고 싶당',0,'2023-10-28 16:37:07'),(79,'ssafy','언능 끝내고 싶당 9','언능 끝내고 싶당',0,'2023-10-28 16:37:07'),(80,'ssafy','언능 끝내고 싶당 10','언능 끝내고 싶당',0,'2023-10-28 16:37:07'),(81,'ssafy','더써야 하나? 1','더써야 하나?',0,'2023-10-28 16:37:15'),(82,'ssafy','더써야 하나? 2','더써야 하나?',0,'2023-10-28 16:37:16'),(83,'ssafy','더써야 하나? 3','더써야 하나?',0,'2023-10-28 16:37:16'),(84,'ssafy','더써야 하나? 4','더써야 하나?',0,'2023-10-28 16:37:16'),(85,'ssafy','더써야 하나? 5','더써야 하나?',0,'2023-10-28 16:37:16'),(86,'ssafy','더써야 하나? 6','더써야 하나?',0,'2023-10-28 16:37:16'),(87,'ssafy','더써야 하나? 7','더써야 하나?',0,'2023-10-28 16:37:16'),(88,'ssafy','더써야 하나? 8','더써야 하나?',0,'2023-10-28 16:37:16'),(89,'ssafy','더써야 하나? 9','더써야 하나?',0,'2023-10-28 16:37:16'),(90,'ssafy','더써야 하나? 10','더써야 하나?',0,'2023-10-28 16:37:16'),(91,'ssafy','많이 써야 할듯 1','많이 써야 할듯',0,'2023-10-28 16:37:24'),(92,'ssafy','많이 써야 할듯 2','많이 써야 할듯',0,'2023-10-28 16:37:24'),(93,'ssafy','많이 써야 할듯 3','많이 써야 할듯',0,'2023-10-28 16:37:24'),(94,'ssafy','많이 써야 할듯 4','많이 써야 할듯',0,'2023-10-28 16:37:24'),(95,'ssafy','많이 써야 할듯 5','많이 써야 할듯',0,'2023-10-28 16:37:24'),(96,'ssafy','많이 써야 할듯 6','많이 써야 할듯',0,'2023-10-28 16:37:24'),(97,'ssafy','많이 써야 할듯 7','많이 써야 할듯',1,'2023-10-28 16:37:25'),(98,'ssafy','많이 써야 할듯 8','많이 써야 할듯',0,'2023-10-28 16:37:25'),(99,'ssafy','많이 써야 할듯 9','많이 써야 할듯',0,'2023-10-28 16:37:25'),(100,'ssafy','많이 써야 할듯 10','많이 써야 할듯',0,'2023-10-28 16:37:25'),(101,'ssafy','재미있는 글 없나? 1','재미있는 글 없나?',0,'2023-10-28 16:37:34'),(102,'ssafy','재미있는 글 없나? 2','재미있는 글 없나?',0,'2023-10-28 16:37:34'),(103,'ssafy','재미있는 글 없나? 3','재미있는 글 없나?',0,'2023-10-28 16:37:34'),(104,'ssafy','재미있는 글 없나? 4','재미있는 글 없나?',0,'2023-10-28 16:37:34'),(105,'ssafy','재미있는 글 없나? 5','재미있는 글 없나?',0,'2023-10-28 16:37:34'),(106,'ssafy','재미있는 글 없나? 6','재미있는 글 없나?',0,'2023-10-28 16:37:34'),(107,'ssafy','재미있는 글 없나? 7','재미있는 글 없나?',0,'2023-10-28 16:37:34'),(108,'ssafy','재미있는 글 없나? 8','재미있는 글 없나?',0,'2023-10-28 16:37:34'),(109,'ssafy','재미있는 글 없나? 9','재미있는 글 없나?',0,'2023-10-28 16:37:34'),(110,'ssafy','재미있는 글 없나? 10','재미있는 글 없나?',0,'2023-10-28 16:37:35'),(111,'ssafy','테스트 ing!! 1','테스트 ing!!',0,'2023-10-28 16:37:47'),(112,'ssafy','테스트 ing!! 2','테스트 ing!!',0,'2023-10-28 16:37:47'),(113,'ssafy','테스트 ing!! 3','테스트 ing!!',0,'2023-10-28 16:37:47'),(114,'ssafy','테스트 ing!! 4','테스트 ing!!',0,'2023-10-28 16:37:47'),(115,'ssafy','테스트 ing!! 5','테스트 ing!!',0,'2023-10-28 16:37:47'),(116,'ssafy','테스트 ing!! 6','테스트 ing!!',0,'2023-10-28 16:37:48'),(117,'ssafy','테스트 ing!! 7','테스트 ing!!',0,'2023-10-28 16:37:48'),(118,'ssafy','테스트 ing!! 8','테스트 ing!!',0,'2023-10-28 16:37:48'),(119,'ssafy','테스트 ing!! 9','테스트 ing!!',0,'2023-10-28 16:37:48'),(120,'ssafy','테스트 ing!! 10','테스트 ing!!',0,'2023-10-28 16:37:48'),(121,'ssafy','테스팅임다 1','테스팅임다',0,'2023-10-28 16:37:58'),(122,'ssafy','테스팅임다 2','테스팅임다',0,'2023-10-28 16:37:58'),(123,'ssafy','테스팅임다 3','테스팅임다',0,'2023-10-28 16:37:58'),(124,'ssafy','테스팅임다 4','테스팅임다',0,'2023-10-28 16:37:58'),(125,'ssafy','테스팅임다 5','테스팅임다',0,'2023-10-28 16:37:58'),(126,'ssafy','테스팅임다 6','테스팅임다',0,'2023-10-28 16:37:58'),(127,'ssafy','테스팅임다 7','테스팅임다',0,'2023-10-28 16:37:58'),(128,'ssafy','테스팅임다 8','테스팅임다',0,'2023-10-28 16:37:59'),(129,'ssafy','테스팅임다 9','테스팅임다',0,'2023-10-28 16:37:59'),(130,'ssafy','테스팅임다 10','테스팅임다',0,'2023-10-28 16:37:59'),(131,'ssafy','testing!!! 1','testing!!!',0,'2023-10-28 16:38:07'),(132,'ssafy','testing!!! 2','testing!!!',0,'2023-10-28 16:38:07'),(133,'ssafy','testing!!! 3','testing!!!',0,'2023-10-28 16:38:07'),(134,'ssafy','testing!!! 4','testing!!!',0,'2023-10-28 16:38:07'),(135,'ssafy','testing!!! 5','testing!!!',0,'2023-10-28 16:38:07'),(136,'ssafy','testing!!! 6','testing!!!',0,'2023-10-28 16:38:08'),(137,'ssafy','testing!!! 7','testing!!!',0,'2023-10-28 16:38:08'),(138,'ssafy','testing!!! 8','testing!!!',0,'2023-10-28 16:38:08'),(139,'ssafy','testing!!! 9','testing!!!',0,'2023-10-28 16:38:08'),(140,'ssafy','testing!!! 10','testing!!!',0,'2023-10-28 16:38:08'),(141,'ssafy','gogogo 1','gogogo',0,'2023-10-28 16:38:13'),(142,'ssafy','gogogo 2','gogogo',1,'2023-10-28 16:38:13'),(143,'ssafy','gogogo 3','gogogo',0,'2023-10-28 16:38:13'),(144,'ssafy','gogogo 4','gogogo',0,'2023-10-28 16:38:13'),(145,'ssafy','gogogo 5','gogogo',0,'2023-10-28 16:38:13'),(146,'ssafy','gogogo 6','gogogo',0,'2023-10-28 16:38:13'),(147,'ssafy','gogogo 7','gogogo',0,'2023-10-28 16:38:14'),(148,'ssafy','gogogo 8','gogogo',0,'2023-10-28 16:38:14'),(149,'ssafy','gogogo 9','gogogo',0,'2023-10-28 16:38:14'),(150,'ssafy','gogogo 10','gogogo',0,'2023-10-28 16:38:14'),(151,'ssafy','가즈아~~ 1','가즈아~~',0,'2023-10-28 16:38:22'),(152,'ssafy','가즈아~~ 2','가즈아~~',0,'2023-10-28 16:38:22'),(153,'ssafy','가즈아~~ 3','가즈아~~',0,'2023-10-28 16:38:22'),(154,'ssafy','가즈아~~ 4','가즈아~~',0,'2023-10-28 16:38:22'),(155,'ssafy','가즈아~~ 5','가즈아~~',0,'2023-10-28 16:38:22'),(156,'ssafy','가즈아~~ 6','가즈아~~',0,'2023-10-28 16:38:22'),(157,'ssafy','가즈아~~ 7','가즈아~~',0,'2023-10-28 16:38:22'),(158,'ssafy','가즈아~~ 8','가즈아~~',0,'2023-10-28 16:38:22'),(159,'ssafy','가즈아~~ 9','가즈아~~',0,'2023-10-28 16:38:22'),(160,'ssafy','가즈아~~ 10','가즈아~~',0,'2023-10-28 16:38:22'),(161,'hissam','안녕 하이쌤이야 1','안녕 하이쌤이야',0,'2023-10-28 16:38:59'),(162,'hissam','안녕 하이쌤이야 2','안녕 하이쌤이야',0,'2023-10-28 16:38:59'),(163,'hissam','안녕 하이쌤이야 3','안녕 하이쌤이야',0,'2023-10-28 16:38:59'),(164,'hissam','안녕 하이쌤이야 4','안녕 하이쌤이야',0,'2023-10-28 16:38:59'),(165,'hissam','안녕 하이쌤이야 5','안녕 하이쌤이야',0,'2023-10-28 16:39:00'),(166,'hissam','안녕 하이쌤이야 6','안녕 하이쌤이야',0,'2023-10-28 16:39:00'),(167,'hissam','안녕 하이쌤이야 7','안녕 하이쌤이야',0,'2023-10-28 16:39:00'),(168,'hissam','안녕 하이쌤이야 8','안녕 하이쌤이야',0,'2023-10-28 16:39:00'),(169,'hissam','안녕 하이쌤이야 9','안녕 하이쌤이야',0,'2023-10-28 16:39:00'),(170,'hissam','안녕 하이쌤이야 10','안녕 하이쌤이야',0,'2023-10-28 16:39:00'),(171,'hissam','언제까지 해야해 1','언제까지 해야해',0,'2023-10-28 16:39:10'),(172,'hissam','언제까지 해야해 2','언제까지 해야해',0,'2023-10-28 16:39:11'),(173,'hissam','언제까지 해야해 3','언제까지 해야해',0,'2023-10-28 16:39:11'),(174,'hissam','언제까지 해야해 4','언제까지 해야해',0,'2023-10-28 16:39:11'),(175,'hissam','언제까지 해야해 5','언제까지 해야해',0,'2023-10-28 16:39:11'),(176,'hissam','언제까지 해야해 6','언제까지 해야해',0,'2023-10-28 16:39:11'),(177,'hissam','언제까지 해야해 7','언제까지 해야해',0,'2023-10-28 16:39:11'),(178,'hissam','언제까지 해야해 8','언제까지 해야해',0,'2023-10-28 16:39:11'),(179,'hissam','언제까지 해야해 9','언제까지 해야해',0,'2023-10-28 16:39:11'),(180,'hissam','언제까지 해야해 10','언제까지 해야해',0,'2023-10-28 16:39:11'),(181,'hissam','후앙 1','후앙',0,'2023-10-28 16:39:17'),(182,'hissam','후앙 2','후앙',0,'2023-10-28 16:39:17'),(183,'hissam','후앙 3','후앙',0,'2023-10-28 16:39:17'),(184,'hissam','후앙 4','후앙',0,'2023-10-28 16:39:17'),(185,'hissam','후앙 5','후앙',0,'2023-10-28 16:39:17'),(186,'hissam','후앙 6','후앙',0,'2023-10-28 16:39:17'),(187,'hissam','후앙 7','후앙',0,'2023-10-28 16:39:17'),(188,'hissam','후앙 8','후앙',0,'2023-10-28 16:39:17'),(189,'hissam','후앙 9','후앙',0,'2023-10-28 16:39:17'),(190,'hissam','후앙 10','후앙',0,'2023-10-28 16:39:17'),(191,'hissam','이제 그만?? 1','이제 그만??',0,'2023-10-28 16:39:35'),(192,'hissam','이제 그만?? 2','이제 그만??',0,'2023-10-28 16:39:35'),(193,'hissam','이제 그만?? 3','이제 그만??',0,'2023-10-28 16:39:35'),(194,'hissam','이제 그만?? 4','이제 그만??',0,'2023-10-28 16:39:35'),(195,'hissam','이제 그만?? 5','이제 그만??',0,'2023-10-28 16:39:35'),(196,'hissam','이제 그만?? 6','이제 그만??',0,'2023-10-28 16:39:35'),(197,'hissam','이제 그만?? 7','이제 그만??',0,'2023-10-28 16:39:35'),(198,'hissam','이제 그만?? 8','이제 그만??',0,'2023-10-28 16:39:35'),(199,'hissam','이제 그만?? 9','이제 그만??',0,'2023-10-28 16:39:35'),(200,'hissam','이제 그만?? 10','이제 그만??',0,'2023-10-28 16:39:36'),(201,'ssafy.hong','나도 쓴다 1','나도 쓴다',0,'2023-10-28 16:39:53'),(202,'ssafy.hong','나도 쓴다 2','나도 쓴다',0,'2023-10-28 16:39:53'),(203,'ssafy.hong','나도 쓴다 3','나도 쓴다',0,'2023-10-28 16:39:53'),(204,'ssafy.hong','나도 쓴다 4','나도 쓴다',0,'2023-10-28 16:39:53'),(205,'ssafy.hong','나도 쓴다 5','나도 쓴다',0,'2023-10-28 16:39:53'),(206,'ssafy.hong','나도 쓴다 6','나도 쓴다',0,'2023-10-28 16:39:53'),(207,'ssafy.hong','나도 쓴다 7','나도 쓴다',0,'2023-10-28 16:39:53'),(208,'ssafy.hong','나도 쓴다 8','나도 쓴다',0,'2023-10-28 16:39:53'),(209,'ssafy.hong','나도 쓴다 9','나도 쓴다',0,'2023-10-28 16:39:53'),(210,'ssafy.hong','나도 쓴다 10','나도 쓴다',0,'2023-10-28 16:39:53'),(211,'ssafy.hong','이만 하면 됐나? 1','이만 하면 됐나?',0,'2023-10-28 16:40:07'),(212,'ssafy.hong','이만 하면 됐나? 2','이만 하면 됐나?',0,'2023-10-28 16:40:07'),(213,'ssafy.hong','이만 하면 됐나? 3','이만 하면 됐나?',0,'2023-10-28 16:40:07'),(214,'ssafy.hong','이만 하면 됐나? 4','이만 하면 됐나?',0,'2023-10-28 16:40:07'),(215,'ssafy.hong','이만 하면 됐나? 5','이만 하면 됐나?',0,'2023-10-28 16:40:07'),(216,'ssafy.hong','이만 하면 됐나? 6','이만 하면 됐나?',0,'2023-10-28 16:40:07'),(217,'ssafy.hong','이만 하면 됐나? 7','이만 하면 됐나?',0,'2023-10-28 16:40:08'),(218,'ssafy.hong','이만 하면 됐나? 8','이만 하면 됐나?',0,'2023-10-28 16:40:08'),(219,'ssafy.hong','이만 하면 됐나? 9','이만 하면 됐나?',0,'2023-10-28 16:40:08'),(220,'ssafy.hong','이만 하면 됐나? 10','이만 하면 됐나?',0,'2023-10-28 16:40:08'),(221,'ssafy.hong','테스트하고있슴다 1','테스트하고있슴다',0,'2023-10-28 16:40:20'),(222,'ssafy.hong','테스트하고있슴다 2','테스트하고있슴다',0,'2023-10-28 16:40:20'),(223,'ssafy.hong','테스트하고있슴다 3','테스트하고있슴다',0,'2023-10-28 16:40:20'),(224,'ssafy.hong','테스트하고있슴다 4','테스트하고있슴다',0,'2023-10-28 16:40:20'),(225,'ssafy.hong','테스트하고있슴다 5','테스트하고있슴다',0,'2023-10-28 16:40:20'),(226,'ssafy.hong','테스트하고있슴다 6','테스트하고있슴다',0,'2023-10-28 16:40:20'),(227,'ssafy.hong','테스트하고있슴다 7','테스트하고있슴다',0,'2023-10-28 16:40:20'),(228,'ssafy.hong','테스트하고있슴다 8','테스트하고있슴다',0,'2023-10-28 16:40:20'),(229,'ssafy.hong','테스트하고있슴다 9','테스트하고있슴다',0,'2023-10-28 16:40:20'),(230,'ssafy.hong','테스트하고있슴다 10','테스트하고있슴다',0,'2023-10-28 16:40:20'),(231,'ssafy.hong','마지막!!! 1','마지막!!!',0,'2023-10-28 16:40:29'),(232,'ssafy.hong','마지막!!! 2','마지막!!!',0,'2023-10-28 16:40:29'),(233,'ssafy.hong','마지막!!! 3','마지막!!!',0,'2023-10-28 16:40:29'),(234,'ssafy.hong','마지막!!! 4','마지막!!!',0,'2023-10-28 16:40:29'),(235,'ssafy.hong','마지막!!! 5','마지막!!!',1,'2023-10-28 16:40:29'),(236,'ssafy.hong','마지막!!! 6','마지막!!!',0,'2023-10-28 16:40:29'),(237,'ssafy.hong','마지막!!! 70000','마지막!!!0000',1,'2023-10-28 16:40:29'),(246,'ssafy','제목 수정해요','내용도 수정해요 \n\nㅇㅋㅇㅋ',3,'2023-11-02 07:57:59'),(247,'ssafy','vue js 글작성하자','vue js 글작성하자\nvue js 글작성하자\nvue js 글작성하자vue js 글작성하자vue js 글작성하자vue js 글작성하자ㅍvue js 글작성하자vue js 글작성하자vue js 글작성하자\n\n\n\nvue js 글작성하자vue js 글작성하자vue js 글작성하자\n',1,'2023-11-02 07:59:31'),(248,'admin','관리자 방문 뷰 고고','관리자 방문 뷰 고고\n\n\n관리자 방문 뷰 고고\n\n\n관리자 방문 뷰 고고',4,'2023-11-02 08:04:28'),(251,'ssafy','999테스트 마지막 글12233','테스트 마지막 글1133333',26,'2023-11-04 14:50:12'),(252,'ssafy','1234','1234',1,'2023-11-19 14:21:26');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-19 23:26:57
