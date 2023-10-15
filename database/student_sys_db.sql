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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `credits_num` int DEFAULT NULL,
  `note` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Lập trình Java',4,NULL),(2,'Kiểm thử phần mềm',3,NULL),(3,'Khai phá dữ liệu',3,NULL),(4,'Cơ sở dữ liệu phân tán',3,NULL),(5,'Luyện phát âm',3,NULL),(6,'Nghe - Nói 1',3,NULL),(7,'Triết học Mác - Lênin',3,NULL),(22,'Tiếng việt thực hành',12,'user');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_data`
--

DROP TABLE IF EXISTS `course_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_data` (
  `course_data_id` int NOT NULL AUTO_INCREMENT,
  `course_id` int DEFAULT NULL,
  `lecture_id` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `is_ended` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`course_data_id`),
  KEY `FK_major_date_idx` (`course_id`),
  KEY `FK_major_data_idx` (`lecture_id`),
  CONSTRAINT `FK_major_daa` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `FK_major_data` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`lecture_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_data`
--

LOCK TABLES `course_data` WRITE;
/*!40000 ALTER TABLE `course_data` DISABLE KEYS */;
INSERT INTO `course_data` VALUES (1,1,4,'2023-02-13','2023-04-23',0),(2,2,5,'2023-02-13','2023-04-23',0),(3,3,1,'2023-06-15','2023-08-23',0),(4,4,3,'2023-06-13','2023-08-29',0),(5,3,4,NULL,NULL,0),(7,4,1,NULL,NULL,0),(17,4,1,'2019-12-21','2020-02-01',0),(18,4,1,'2019-12-21','2020-02-01',0);
/*!40000 ALTER TABLE `course_data` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diploma_copy`
--

LOCK TABLES `diploma_copy` WRITE;
/*!40000 ALTER TABLE `diploma_copy` DISABLE KEYS */;
/*!40000 ALTER TABLE `diploma_copy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture`
--

DROP TABLE IF EXISTS `lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture` (
  `lecture_id` int NOT NULL AUTO_INCREMENT,
  `lecture_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lecture_phone` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`lecture_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture`
--

LOCK TABLES `lecture` WRITE;
/*!40000 ALTER TABLE `lecture` DISABLE KEYS */;
INSERT INTO `lecture` VALUES (1,'Nguyễn Văn Bảy','11'),(2,'Phạm Chí Công','12'),(3,'Nguyễn Tiến Đạt','13'),(4,'Dương Hữu Thành','14'),(5,'Minh Khuê','15');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
  `price` double DEFAULT NULL,
  `service_cate_id` int NOT NULL,
  PRIMARY KEY (`online_service_id`),
  KEY `FK_user_service_idx` (`user_id`),
  KEY `FK_type_idx` (`service_cate_id`),
  CONSTRAINT `FK_type` FOREIGN KEY (`service_cate_id`) REFERENCES `service_cate` (`service_cate_id`),
  CONSTRAINT `FK_user_service` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `online_service`
--

LOCK TABLES `online_service` WRITE;
/*!40000 ALTER TABLE `online_service` DISABLE KEYS */;
INSERT INTO `online_service` VALUES (27,3,'2023-10-10','ACCEPT',0,60000,1),(28,3,'2023-10-10','PENDING',0,50000,5);
/*!40000 ALTER TABLE `online_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `service_online_id` int DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `payment_status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `vnpay_txnref` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `service_online_id_UNIQUE` (`service_online_id`),
  KEY `FK_payment_idx` (`service_online_id`),
  CONSTRAINT `FK_payment` FOREIGN KEY (`service_online_id`) REFERENCES `online_service` (`online_service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (10,27,'2023-10-10 00:21:08','PAID',60000,'71046451'),(12,28,'2023-10-10 02:16:43','CANCEL',50000,'72256319');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_info`
--

DROP TABLE IF EXISTS `schedule_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule_info` (
  `schedule_info_id` int NOT NULL AUTO_INCREMENT,
  `weekdays` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `start_at` int DEFAULT NULL,
  `end_at` int DEFAULT NULL,
  `study_room` int DEFAULT NULL,
  `course_data_id` int DEFAULT NULL,
  PRIMARY KEY (`schedule_info_id`),
  KEY `FK_study_room_id_idx` (`study_room`),
  KEY `FK_subject_data_idx` (`course_data_id`),
  CONSTRAINT `FK_study_room_id` FOREIGN KEY (`study_room`) REFERENCES `study_room` (`study_room_id`),
  CONSTRAINT `FK_subject_data` FOREIGN KEY (`course_data_id`) REFERENCES `course_data` (`course_data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_info`
--

LOCK TABLES `schedule_info` WRITE;
/*!40000 ALTER TABLE `schedule_info` DISABLE KEYS */;
INSERT INTO `schedule_info` VALUES (9,'Mon',1,4,1,1),(10,'Tues',1,4,2,2),(11,'Wed',1,4,3,2),(12,'Thurs',5,8,5,3),(13,'Mon',1,3,3,NULL),(14,'Mon',4,5,3,NULL),(20,'Sat',1,4,3,NULL),(23,'Sat',5,9,3,17);
/*!40000 ALTER TABLE `schedule_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester` (
  `semester_id` int NOT NULL AUTO_INCREMENT,
  `semester_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `note` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_finish` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`semester_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (2,'HK1-2021','Học Kỳ 1 - 2021',1),(3,'HK2-2021','Học Kỳ 2 - 2021',1),(4,'HK3-2021','Học Kỳ 3 - 2023',1),(5,'HK1-2022','Học Kỳ 1 - 2022',1),(6,'HK2-2022','Học Kỳ 2 - 2022',1),(7,'HK3-2022','Học Kỳ 3 - 2022',1),(8,'HK1-2023','Học Kỳ 1 - 2023',1),(9,'HK2-2023','Học Kỳ 2 - 2023',1),(10,'HK3-2023','Học Kỳ 3 - 2023',1),(11,'HK1-2024','Học Kỳ 1 - 2024',0),(12,'HK2-2024','Học kỳ 2 - 2024',0);
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester_details`
--

DROP TABLE IF EXISTS `semester_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester_details` (
  `semester_details_id` int NOT NULL AUTO_INCREMENT,
  `semester_user_id` int DEFAULT NULL,
  `course_data_id` int DEFAULT NULL,
  `score` double(10,2) DEFAULT NULL,
  `is_passed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`semester_details_id`),
  KEY `FK_semeter_details1_idx` (`semester_user_id`),
  KEY `FK_semeter_details2_idx` (`course_data_id`),
  CONSTRAINT `FK_semester_details1` FOREIGN KEY (`course_data_id`) REFERENCES `course_data` (`course_data_id`),
  CONSTRAINT `FK_semester_details2` FOREIGN KEY (`semester_user_id`) REFERENCES `semester_user` (`semester_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester_details`
--

LOCK TABLES `semester_details` WRITE;
/*!40000 ALTER TABLE `semester_details` DISABLE KEYS */;
INSERT INTO `semester_details` VALUES (1,29,1,5.00,1),(2,29,2,5.00,1),(3,30,3,5.00,1),(4,30,4,5.00,1),(5,26,1,5.00,1),(6,26,2,5.00,1),(7,27,3,5.00,1),(8,27,4,5.00,1);
/*!40000 ALTER TABLE `semester_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester_user`
--

DROP TABLE IF EXISTS `semester_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester_user` (
  `semester_user_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `semester_id` int DEFAULT NULL,
  `status` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`semester_user_id`),
  KEY `FK_user_seme_idx` (`semester_id`),
  KEY `FK_user_seme1_idx` (`user_id`),
  CONSTRAINT `FK_user_seme1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_user_seme2` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`semester_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester_user`
--

LOCK TABLES `semester_user` WRITE;
/*!40000 ALTER TABLE `semester_user` DISABLE KEYS */;
INSERT INTO `semester_user` VALUES (1,1,2,NULL),(2,1,3,NULL),(3,1,4,NULL),(4,1,5,NULL),(5,1,6,NULL),(6,2,2,NULL),(7,2,3,NULL),(8,2,4,NULL),(9,2,5,NULL),(10,2,6,NULL),(26,2,9,NULL),(27,2,10,NULL),(28,2,11,NULL),(29,1,9,NULL),(30,1,10,NULL),(32,1,11,NULL);
/*!40000 ALTER TABLE `semester_user` ENABLE KEYS */;
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
  `is_available` tinyint(1) DEFAULT NULL,
  `description` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `num_of_date` int DEFAULT NULL,
  PRIMARY KEY (`service_cate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_cate`
--

LOCK TABLES `service_cate` WRITE;
/*!40000 ALTER TABLE `service_cate` DISABLE KEYS */;
INSERT INTO `service_cate` VALUES (1,'Cấp bảng điểm',20000,1,'Đăng ký cấp bảng điểm',5),(2,'Cấp CNSV',50000,1,'Đăng ký chứng nhận sinh viên',3),(3,'Cấp bản sao BTN',100000,1,'Đăng ký cấp bản sao Bằng tốt nghiệp',5),(4,'Cấp chứng nhận tốt nghiệp tạm thời',100000,0,'Đăng ký cấp chứng nhận tốt nghiệp tạm thời',3),(5,'Mở khóa mã số sinh viên',50000,1,'Mở khóa mã số sinh viên',3);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
  `study_room_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_available` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`study_room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_room`
--

LOCK TABLES `study_room` WRITE;
/*!40000 ALTER TABLE `study_room` DISABLE KEYS */;
INSERT INTO `study_room` VALUES (1,'NK.001',1),(2,'NK.002',1),(3,'NK.003',1),(4,'NK.004',1),(5,'NK.005',1),(6,'NK.006',1),(9,'NK.113',0),(10,'NK.999',1),(11,'NK.111',1);
/*!40000 ALTER TABLE `study_room` ENABLE KEYS */;
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
  `from_semester` int DEFAULT NULL,
  `to_semester` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `contact_phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_sealed` tinyint(1) DEFAULT NULL,
  `online_service_id` int DEFAULT NULL,
  PRIMARY KEY (`transcript_id`),
  UNIQUE KEY `online_service_id_UNIQUE` (`online_service_id`),
  KEY `FK_fromSemeter_idx` (`from_semester`),
  KEY `FK_toSemeter_idx` (`to_semester`),
  CONSTRAINT `FK_fromSemeter` FOREIGN KEY (`from_semester`) REFERENCES `semester` (`semester_id`),
  CONSTRAINT `FK_online_service3` FOREIGN KEY (`online_service_id`) REFERENCES `online_service` (`online_service_id`),
  CONSTRAINT `FK_toSemeter` FOREIGN KEY (`to_semester`) REFERENCES `semester` (`semester_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Bảng điểm';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transcript`
--

LOCK TABLES `transcript` WRITE;
/*!40000 ALTER TABLE `transcript` DISABLE KEYS */;
INSERT INTO `transcript` VALUES (6,'Vietnamese',3,4,3,'123',1,27);
/*!40000 ALTER TABLE `transcript` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unlock_student`
--

DROP TABLE IF EXISTS `unlock_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unlock_student` (
  `unlock_student_id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Lý do mở khóa',
  `image` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `online_service_id` int DEFAULT NULL,
  PRIMARY KEY (`unlock_student_id`),
  UNIQUE KEY `online_service_id_UNIQUE` (`online_service_id`),
  KEY `FK_online_service_4_idx` (`online_service_id`),
  CONSTRAINT `FK_online_service_4` FOREIGN KEY (`online_service_id`) REFERENCES `online_service` (`online_service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='mở khóa mã số sv';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unlock_student`
--

LOCK TABLES `unlock_student` WRITE;
/*!40000 ALTER TABLE `unlock_student` DISABLE KEYS */;
INSERT INTO `unlock_student` VALUES (3,'Đóng tiền học trễ hạn','test',28);
/*!40000 ALTER TABLE `unlock_student` ENABLE KEYS */;
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
  `day_of_birth` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `major_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_major_idx` (`major_id`),
  CONSTRAINT `FK_major` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ADMIN','ADMIN@gmail.com','$2a$10$qKveXpAB3RVXDqGIcGY4vOMw8ojqWUO2WBC1v9WHXYCQiETyoFWdK','ADMIN','','2001-12-01','Nam','090',NULL),(2,'MODERATOR','MODERATOR@gmail.com','$2a$10$qKveXpAB3RVXDqGIcGY4vOMw8ojqWUO2WBC1v9WHXYCQiETyoFWdK','MODERATOR','','2001-12-21','Nam','090',NULL),(3,'Hoang Duong','nhoxduong356@gmail.com','$2a$10$qKveXpAB3RVXDqGIcGY4vOMw8ojqWUO2WBC1v9WHXYCQiETyoFWdK','USER',NULL,'2001-12-21','Nam','090',NULL);
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

-- Dump completed on 2023-10-10  5:53:20
