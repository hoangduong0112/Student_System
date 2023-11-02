-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: studentsystem
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
  `department_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Công nghệ thông tin',NULL),(2,'Ngôn ngữ anh',NULL),(3,'Kinh tế - Quản lý công',NULL);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diploma_copy`
--

DROP TABLE IF EXISTS `diploma_copy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diploma_copy` (
  `diploma_copy_id` int NOT NULL AUTO_INCREMENT,
  `copy` int DEFAULT NULL,
  `phone_contact` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `diploma_year` int DEFAULT NULL,
  `diploma_code` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `online_service_id` int DEFAULT NULL,
  PRIMARY KEY (`diploma_copy_id`),
  UNIQUE KEY `online_service_id_UNIQUE` (`online_service_id`),
  KEY `FK_ServiceOnline1_idx` (`online_service_id`),
  CONSTRAINT `FK_ServiceOnline1` FOREIGN KEY (`online_service_id`) REFERENCES `online_service` (`online_service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diploma_copy`
--

LOCK TABLES `diploma_copy` WRITE;
/*!40000 ALTER TABLE `diploma_copy` DISABLE KEYS */;
INSERT INTO `diploma_copy` VALUES (1,3,'0903182308','195712049@gmail.com',2023,'abc',1),(2,3,'0903182308','195712049@gmail.com',2023,'abc',2),(3,3,'0903182308','195712049@gmail.com',2023,'abc',3);
/*!40000 ALTER TABLE `diploma_copy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_class`
--

DROP TABLE IF EXISTS `group_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_class` (
  `group_class_id` int NOT NULL AUTO_INCREMENT,
  `group_class_name` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`group_class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='lớp id, lớp name, (main lecture) chu nhiem lop';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_class`
--

LOCK TABLES `group_class` WRITE;
/*!40000 ALTER TABLE `group_class` DISABLE KEYS */;
INSERT INTO `group_class` VALUES (1,'IT2001-01','Thực hành ca 2 lớp IT2001'),(2,'IT2001-02','Thực hành ca 2 lớp IT2001'),(3,'IT2002-01','Thực hành ca 1 lớp IT2002'),(4,'IT2002-02','Thực hành ca 2 lớp IT2002'),(5,'AV1903',''),(6,'AV1902',NULL),(7,'AV1901',NULL);
/*!40000 ALTER TABLE `group_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture`
--

DROP TABLE IF EXISTS `lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture` (
  `lecture_id` int NOT NULL,
  `lecture_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`lecture_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture`
--

LOCK TABLES `lecture` WRITE;
/*!40000 ALTER TABLE `lecture` DISABLE KEYS */;
/*!40000 ALTER TABLE `lecture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `major_id` int NOT NULL AUTO_INCREMENT,
  `department_id` int NOT NULL,
  `major_code` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `major_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`major_id`),
  KEY `KF_depart_major_idx` (`department_id`),
  CONSTRAINT `KF_depart_major` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (1,1,'645455','Công nghệ thông tin'),(2,1,'645456','Khoa Học máy tính');
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
  `created_date` date DEFAULT NULL,
  `status` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_shipped` tinyint(1) DEFAULT NULL,
  `service_cate_id` int NOT NULL,
  PRIMARY KEY (`online_service_id`),
  KEY `FK_user_service_idx` (`user_id`),
  KEY `FK_type_idx` (`service_cate_id`),
  CONSTRAINT `FK_type` FOREIGN KEY (`service_cate_id`) REFERENCES `service_cate` (`service_cate_id`),
  CONSTRAINT `FK_user_service` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `online_service`
--

LOCK TABLES `online_service` WRITE;
/*!40000 ALTER TABLE `online_service` DISABLE KEYS */;
INSERT INTO `online_service` VALUES (1,2,'2023-09-24','None',0,3),(2,2,'2023-09-24','None',0,3),(3,2,'2023-09-24','None',0,3),(6,2,'2023-09-24','None',0,1),(7,2,'2023-09-24','None',0,1);
/*!40000 ALTER TABLE `online_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `schedule_id` int NOT NULL AUTO_INCREMENT,
  `time_of_day` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'sáng, chiều, tối',
  `start_period` int DEFAULT NULL,
  `num_of_period` int DEFAULT NULL,
  `day_week` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semeter`
--

DROP TABLE IF EXISTS `semeter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semeter` (
  `semeter_id` int NOT NULL AUTO_INCREMENT,
  `semeter_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `note` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`semeter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semeter`
--

LOCK TABLES `semeter` WRITE;
/*!40000 ALTER TABLE `semeter` DISABLE KEYS */;
INSERT INTO `semeter` VALUES (2,'HK1-2021','Học Kỳ 1 - 2021'),(3,'HK2-2021','Học Kỳ 2 - 2021'),(4,'HK3-2021','Học Kỳ 3 - 2023'),(5,'HK1-2022','Học Kỳ 1 - 2022'),(6,'HK2-2022','Học Kỳ 2 - 2022'),(7,'HK3-2022','Học Kỳ 3 - 2022'),(8,'HK1-2023','Học Kỳ 1 - 2023'),(9,'HK2-2023','Học Kỳ 2 - 2023'),(10,'HK3-2023','Học Kỳ 3 - 2023'),(11,'HK1-2024','Học Kỳ 1 - 2024');
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
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `student_quantity` int DEFAULT NULL,
  `semeter_id` int DEFAULT NULL,
  `study_room_id` int DEFAULT NULL,
  PRIMARY KEY (`semeter_subject_id`)
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
  `service_cate_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `is_avaible` tinyint(1) DEFAULT NULL,
  `description` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`service_cate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_cate`
--

LOCK TABLES `service_cate` WRITE;
/*!40000 ALTER TABLE `service_cate` DISABLE KEYS */;
INSERT INTO `service_cate` VALUES (1,'Cấp bảng điểm',20000,1,'Đăng ký cấp bảng điểm'),(2,'Cấp CNSV',50000,1,'Đăng ký chứng nhận sinh viên'),(3,'Cấp bản sao BTN',10000,1,'Đăng ký cấp bản sao Bằng tốt nghiệp'),(4,'Cấp chứng nhận tốt nghiệp tạm thời',100000,0,'Đăng ký cấp chứng nhận tốt nghiệp tạm thời');
/*!40000 ALTER TABLE `service_cate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stud_certification`
--

DROP TABLE IF EXISTS `stud_certification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stud_certification` (
  `stud_certification_id` int NOT NULL AUTO_INCREMENT,
  `viet_copy` int DEFAULT NULL,
  `eng_copy` int DEFAULT NULL,
  `phone_contact` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `online_service` int DEFAULT NULL,
  PRIMARY KEY (`stud_certification_id`),
  UNIQUE KEY `online_service_UNIQUE` (`online_service`),
  CONSTRAINT `FK_online_service2` FOREIGN KEY (`online_service`) REFERENCES `online_service` (`online_service_id`)
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
-- Table structure for table `stud_semeter`
--

DROP TABLE IF EXISTS `stud_semeter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stud_semeter` (
  `stud_semeter` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `semeter_id` int DEFAULT NULL,
  PRIMARY KEY (`stud_semeter`),
  KEY `FK_user_semeter1_idx` (`user_id`),
  KEY `FK_user_semeter2_idx` (`semeter_id`),
  CONSTRAINT `FK_user_semeter1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_user_semeter2` FOREIGN KEY (`semeter_id`) REFERENCES `semeter` (`semeter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stud_semeter`
--

LOCK TABLES `stud_semeter` WRITE;
/*!40000 ALTER TABLE `stud_semeter` DISABLE KEYS */;
/*!40000 ALTER TABLE `stud_semeter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study_room`
--

DROP TABLE IF EXISTS `study_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_room` (
  `study_room_id` int NOT NULL AUTO_INCREMENT,
  `study_room_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_avaiable` tinyint(1) DEFAULT '1',
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
  `subject_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `credits_num` int DEFAULT NULL,
  `note` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
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
  `transcript_id` int NOT NULL AUTO_INCREMENT,
  `language` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `from_semeter` int DEFAULT NULL,
  `to_semeter` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `contact_phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_sealed` tinyint(1) DEFAULT NULL,
  `online_service_id` int DEFAULT NULL,
  PRIMARY KEY (`transcript_id`),
  UNIQUE KEY `online_service_id_UNIQUE` (`online_service_id`),
  KEY `FK_fromSemeter_idx` (`from_semeter`),
  KEY `FK_toSemeter_idx` (`to_semeter`),
  CONSTRAINT `FK_fromSemeter` FOREIGN KEY (`from_semeter`) REFERENCES `semeter` (`semeter_id`),
  CONSTRAINT `FK_online_service3` FOREIGN KEY (`online_service_id`) REFERENCES `online_service` (`online_service_id`),
  CONSTRAINT `FK_toSemeter` FOREIGN KEY (`to_semeter`) REFERENCES `semeter` (`semeter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Bảng điểm';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transcript`
--

LOCK TABLES `transcript` WRITE;
/*!40000 ALTER TABLE `transcript` DISABLE KEYS */;
INSERT INTO `transcript` VALUES (2,'Vietnamese',3,4,3,'090',1,6),(3,'Vietnamese',3,5,3,'090',1,7);
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
  `full_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_role` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `day_of_birth` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `major_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_major_idx` (`major_id`),
  CONSTRAINT `FK_major` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Hoang Duong','1957012049-HoangDuong','$2a$10$WqLqrOo/cBVCCyQETVV5Cu/CD0ojo9caYqFchuhvuTPJGffpuQy/m','USER','12323','2001-12-01','Nam','090',1),(2,'tes','abc@gmail','$2a$10$qKveXpAB3RVXDqGIcGY4vOMw8ojqWUO2WBC1v9WHXYCQiETyoFWdK','USER','123','2001-12-21','Nam','090',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-02 23:24:03
