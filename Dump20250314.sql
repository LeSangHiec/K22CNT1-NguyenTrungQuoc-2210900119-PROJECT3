CREATE DATABASE  IF NOT EXISTS `quanlydethi` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quanlydethi`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quanlydethi
-- ------------------------------------------------------
-- Server version	9.1.0

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
-- Table structure for table `ntq_cau_hoi`
--

DROP TABLE IF EXISTS `ntq_cau_hoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ntq_cau_hoi` (
  `ntq_id` bigint NOT NULL AUTO_INCREMENT,
  `ntq_nguoi_tao_id` bigint NOT NULL,
  `ntq_noi_dung` varchar(255) NOT NULL,
  `ntq_loai_cau_hoi` enum('TRAC_NGHIEM','DUNG_SAI','TU_LUAN') NOT NULL,
  `ntq_diem` double NOT NULL,
  `ntq_dap_an_a` tinytext,
  `ntq_dap_an_b` tinytext,
  `ntq_dap_an_c` tinytext,
  `ntq_dap_an_d` tinytext,
  `ntq_dap_an_dung` varchar(255) NOT NULL,
  PRIMARY KEY (`ntq_id`),
  KEY `idx_nguoi_tao_id` (`ntq_nguoi_tao_id`),
  CONSTRAINT `fk_cau_hoi_nguoi_tao` FOREIGN KEY (`ntq_nguoi_tao_id`) REFERENCES `ntq_nguoi_dung` (`ntq_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_cau_hoi`
--

LOCK TABLES `ntq_cau_hoi` WRITE;
/*!40000 ALTER TABLE `ntq_cau_hoi` DISABLE KEYS */;
INSERT INTO `ntq_cau_hoi` VALUES (2,1,'quoc','TRAC_NGHIEM',0.6,'q1','e23','51','323','A'),(5,8,'1+1=','TRAC_NGHIEM',4,'2','3','4','5','B');
/*!40000 ALTER TABLE `ntq_cau_hoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ntq_danh_muc`
--

DROP TABLE IF EXISTS `ntq_danh_muc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ntq_danh_muc` (
  `ntq_id` bigint NOT NULL AUTO_INCREMENT,
  `ntq_ten` varchar(255) NOT NULL,
  `ntq_mo_ta` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ntq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_danh_muc`
--

LOCK TABLES `ntq_danh_muc` WRITE;
/*!40000 ALTER TABLE `ntq_danh_muc` DISABLE KEYS */;
INSERT INTO `ntq_danh_muc` VALUES (1,'Toán','Danh mục câu hỏi về Toán học'),(2,'Lý','Danh mục câu hỏi về Vật lý'),(3,'Hóa','Danh mục câu hỏi về Hóa học');
/*!40000 ALTER TABLE `ntq_danh_muc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ntq_de_thi`
--

DROP TABLE IF EXISTS `ntq_de_thi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ntq_de_thi` (
  `ntq_id` bigint NOT NULL AUTO_INCREMENT,
  `ntq_tieu_de` varchar(255) NOT NULL,
  `ntq_mo_ta` varchar(255) DEFAULT NULL,
  `ntq_danh_muc_id` bigint DEFAULT NULL,
  `ntq_nguoi_tao_id` bigint NOT NULL,
  `ntq_ngay_tao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ntq_id`),
  KEY `idx_danh_muc_id` (`ntq_danh_muc_id`),
  KEY `idx_nguoi_tao_id` (`ntq_nguoi_tao_id`),
  CONSTRAINT `fk_de_thi_danh_muc` FOREIGN KEY (`ntq_danh_muc_id`) REFERENCES `ntq_danh_muc` (`ntq_id`) ON DELETE SET NULL,
  CONSTRAINT `fk_de_thi_nguoi_tao` FOREIGN KEY (`ntq_nguoi_tao_id`) REFERENCES `ntq_nguoi_dung` (`ntq_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_de_thi`
--

LOCK TABLES `ntq_de_thi` WRITE;
/*!40000 ALTER TABLE `ntq_de_thi` DISABLE KEYS */;
INSERT INTO `ntq_de_thi` VALUES (1,'đề thi','quocquocquoc\r\n',NULL,1,'2025-03-09 07:06:31'),(3,'q','qư',NULL,8,'1970-01-01 01:00:00'),(4,'quocquoc','qưeqeqweqw',NULL,8,'1970-01-01 01:00:00');
/*!40000 ALTER TABLE `ntq_de_thi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ntq_de_thi_cau_hoi`
--

DROP TABLE IF EXISTS `ntq_de_thi_cau_hoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ntq_de_thi_cau_hoi` (
  `ntq_id` bigint NOT NULL AUTO_INCREMENT,
  `ntq_de_thi_id` bigint NOT NULL,
  `ntq_cau_hoi_id` bigint NOT NULL,
  PRIMARY KEY (`ntq_id`),
  KEY `idx_de_thi_id` (`ntq_de_thi_id`),
  KEY `idx_cau_hoi_id` (`ntq_cau_hoi_id`),
  CONSTRAINT `fk_de_thi_cau_hoi_cau_hoi` FOREIGN KEY (`ntq_cau_hoi_id`) REFERENCES `ntq_cau_hoi` (`ntq_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_de_thi_cau_hoi_de_thi` FOREIGN KEY (`ntq_de_thi_id`) REFERENCES `ntq_de_thi` (`ntq_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_de_thi_cau_hoi`
--

LOCK TABLES `ntq_de_thi_cau_hoi` WRITE;
/*!40000 ALTER TABLE `ntq_de_thi_cau_hoi` DISABLE KEYS */;
INSERT INTO `ntq_de_thi_cau_hoi` VALUES (7,1,2),(8,1,5),(11,3,2),(12,3,5),(13,4,2),(14,4,5);
/*!40000 ALTER TABLE `ntq_de_thi_cau_hoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ntq_menu`
--

DROP TABLE IF EXISTS `ntq_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ntq_menu` (
  `ntq_id` bigint NOT NULL AUTO_INCREMENT,
  `ntq_ten` varchar(255) NOT NULL,
  `ntq_duong_dan` varchar(255) NOT NULL,
  `ntq_icon` varchar(100) DEFAULT NULL,
  `ntq_thu_tu` int DEFAULT '0',
  `ntq_parent_id` bigint DEFAULT NULL,
  `ntq_trang_thai` enum('HOAT_DONG','KHOA') DEFAULT 'HOAT_DONG',
  PRIMARY KEY (`ntq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_menu`
--

LOCK TABLES `ntq_menu` WRITE;
/*!40000 ALTER TABLE `ntq_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `ntq_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ntq_nguoi_dung`
--

DROP TABLE IF EXISTS `ntq_nguoi_dung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ntq_nguoi_dung` (
  `ntq_id` bigint NOT NULL AUTO_INCREMENT,
  `ntq_ten_dang_nhap` varchar(255) NOT NULL,
  `ntq_email` varchar(255) NOT NULL,
  `ntq_mat_khau` varchar(255) NOT NULL,
  `ntq_vai_tro` varchar(255) NOT NULL,
  `ntq_ngay_tao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ntq_trang_thai` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ntq_id`),
  UNIQUE KEY `uk_ten_dang_nhap` (`ntq_ten_dang_nhap`),
  UNIQUE KEY `uk_email` (`ntq_email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_nguoi_dung`
--

LOCK TABLES `ntq_nguoi_dung` WRITE;
/*!40000 ALTER TABLE `ntq_nguoi_dung` DISABLE KEYS */;
INSERT INTO `ntq_nguoi_dung` VALUES (1,'admin','admin@example.com','admin123','QUAN_TRI','2025-03-09 06:41:36','HOAT_DONG'),(2,'teacher','teacher@example.com','teacher123','GIAO_VIEN','2025-03-09 06:41:36','HOAT_DONG'),(3,'student','student@example.com','student123','HOC_SINH','2025-03-09 06:41:36','HOAT_DONG'),(7,'quoc','admin1@gmail.com','admin','QUAN_TRI','2025-03-09 13:29:34','HOAT_DONG'),(8,'quocdangcap69','trungquock102@gmail.com','quocquoc','HOC_SINH',NULL,'HOAT_DONG');
/*!40000 ALTER TABLE `ntq_nguoi_dung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ntq_phan_hoi`
--

DROP TABLE IF EXISTS `ntq_phan_hoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ntq_phan_hoi` (
  `ntq_id` bigint NOT NULL AUTO_INCREMENT,
  `ntq_nguoi_dung_id` bigint NOT NULL,
  `ntq_de_thi_id` bigint NOT NULL,
  `ntq_binh_luan` text,
  `ntq_danh_gia` int DEFAULT NULL,
  `ntq_ngay_tao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ntq_id`),
  KEY `idx_nguoi_dung_id` (`ntq_nguoi_dung_id`),
  KEY `idx_de_thi_id` (`ntq_de_thi_id`),
  CONSTRAINT `fk_phan_hoi_de_thi` FOREIGN KEY (`ntq_de_thi_id`) REFERENCES `ntq_de_thi` (`ntq_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_phan_hoi_nguoi_dung` FOREIGN KEY (`ntq_nguoi_dung_id`) REFERENCES `ntq_nguoi_dung` (`ntq_id`) ON DELETE CASCADE,
  CONSTRAINT `chk_danh_gia` CHECK ((`ntq_danh_gia` between 1 and 5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_phan_hoi`
--

LOCK TABLES `ntq_phan_hoi` WRITE;
/*!40000 ALTER TABLE `ntq_phan_hoi` DISABLE KEYS */;
/*!40000 ALTER TABLE `ntq_phan_hoi` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-14 22:41:36
