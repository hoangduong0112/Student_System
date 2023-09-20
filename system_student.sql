-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: student_sys_db
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `department_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diploma_copy`
--

DROP TABLE IF EXISTS `diploma_copy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diploma_copy` (
  `online_service_id` int NOT NULL,
  `copy` int DEFAULT NULL,
  `phone_contact` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `diploma_year` int DEFAULT NULL,
  `diploma_code` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`online_service_id`),
  CONSTRAINT `FK_online_service3` FOREIGN KEY (`online_service_id`) REFERENCES `online_service` (`online_service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diploma_copy`
--

LOCK TABLES `diploma_copy` WRITE;
/*!40000 ALTER TABLE `diploma_copy` DISABLE KEYS */;
/*!40000 ALTER TABLE `diploma_copy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `major_id` int NOT NULL,
  `department_id` int NOT NULL,
  `major_code` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `major_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`major_id`),
  KEY `KF_depart_major_idx` (`department_id`),
  CONSTRAINT `KF_depart_major` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `online_service`
--

DROP TABLE IF EXISTS `online_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `online_service` (
  `online_service_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `status` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_shipped` tinyint(1) DEFAULT NULL,
  `service_cate_id` int NOT NULL,
  PRIMARY KEY (`online_service_id`),
  KEY `FK_user_service_idx` (`user_id`),
  KEY `FK_type_idx` (`service_cate_id`),
  CONSTRAINT `FK_type` FOREIGN KEY (`service_cate_id`) REFERENCES `service_cate` (`service_cate_id`),
  CONSTRAINT `FK_user_service` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `online_service`
--

LOCK TABLES `online_service` WRITE;
/*!40000 ALTER TABLE `online_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `online_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reg_subject`
--

DROP TABLE IF EXISTS `reg_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reg_subject` (
  `reg_subject_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `semeter_subject_id` int NOT NULL,
  PRIMARY KEY (`reg_subject_id`),
  KEY `KF_user_reg_idx` (`user_id`),
  KEY `FK_subject_reg_idx` (`semeter_subject_id`),
  CONSTRAINT `FK_subject_reg` FOREIGN KEY (`semeter_subject_id`) REFERENCES `semeter_subject` (`semeter_subject_id`),
  CONSTRAINT `FK_user_reg` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reg_subject`
--

LOCK TABLES `reg_subject` WRITE;
/*!40000 ALTER TABLE `reg_subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `reg_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semeter`
--

DROP TABLE IF EXISTS `semeter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semeter` (
  `semeter_id` int NOT NULL AUTO_INCREMENT,
  `semeter_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `note` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`semeter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semeter`
--

LOCK TABLES `semeter` WRITE;
/*!40000 ALTER TABLE `semeter` DISABLE KEYS */;
/*!40000 ALTER TABLE `semeter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semeter_subject`
--

DROP TABLE IF EXISTS `semeter_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semeter_subject` (
  `semeter_subject_id` int NOT NULL AUTO_INCREMENT,
  `subject_id` int DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `student_quantity` int DEFAULT NULL,
  `user_id` int DEFAULT NULL COMMENT 'Giảng viên',
  `semeter_id` int DEFAULT NULL,
  PRIMARY KEY (`semeter_subject_id`),
  KEY `FK_subject_idx` (`subject_id`),
  KEY `FK_user_idx` (`user_id`),
  KEY `FK_Semeterr_idx` (`semeter_id`),
  CONSTRAINT `FK_Semeterr` FOREIGN KEY (`semeter_id`) REFERENCES `semeter` (`semeter_id`),
  CONSTRAINT `FK_subject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`),
  CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semeter_subject`
--

LOCK TABLES `semeter_subject` WRITE;
/*!40000 ALTER TABLE `semeter_subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `semeter_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_cate`
--

DROP TABLE IF EXISTS `service_cate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_cate` (
  `service_cate_id` int NOT NULL AUTO_INCREMENT,
  `service_cate_name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `is_avaible` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`service_cate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_cate`
--

LOCK TABLES `service_cate` WRITE;
/*!40000 ALTER TABLE `service_cate` DISABLE KEYS */;
/*!40000 ALTER TABLE `service_cate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stud_certification`
--

DROP TABLE IF EXISTS `stud_certification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stud_certification` (
  `online_service_id` int NOT NULL,
  `viet_copy` int DEFAULT NULL,
  `eng_copy` int DEFAULT NULL,
  `phone_contact` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`online_service_id`),
  CONSTRAINT `FK_online_service2` FOREIGN KEY (`online_service_id`) REFERENCES `online_service` (`online_service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stud_certification`
--

LOCK TABLES `stud_certification` WRITE;
/*!40000 ALTER TABLE `stud_certification` DISABLE KEYS */;
/*!40000 ALTER TABLE `stud_certification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study_room`
--

DROP TABLE IF EXISTS `study_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_room` (
  `study_room_id` int NOT NULL AUTO_INCREMENT,
  `study_room_name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_avaiable` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT '1',
  PRIMARY KEY (`study_room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_room`
--

LOCK TABLES `study_room` WRITE;
/*!40000 ALTER TABLE `study_room` DISABLE KEYS */;
/*!40000 ALTER TABLE `study_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `credits_num` int DEFAULT NULL,
  `note` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_open` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transcript`
--

DROP TABLE IF EXISTS `transcript`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transcript` (
  `online_service_id` int NOT NULL,
  `language` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `from_semeter` int NOT NULL,
  `to_semeter` int NOT NULL,
  `quantity` int NOT NULL,
  `contact_phone` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_sealed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`online_service_id`),
  CONSTRAINT `FK_online_service_transcript` FOREIGN KEY (`online_service_id`) REFERENCES `online_service` (`online_service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Bảng điểm';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transcript`
--

LOCK TABLES `transcript` WRITE;
/*!40000 ALTER TABLE `transcript` DISABLE KEYS */;
/*!40000 ALTER TABLE `transcript` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_role` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `avatar` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `user_id` int NOT NULL,
  `day_of_birth` datetime DEFAULT NULL,
  `gender` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `major_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FK_user_info` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-20 21:15:42
