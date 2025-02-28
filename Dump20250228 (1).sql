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
  `id` bigint NOT NULL AUTO_INCREMENT,
  `de_thi_id` bigint NOT NULL,
  `nguoi_tao_id` bigint NOT NULL,
  `noi_dung` text NOT NULL,
  `loai_cau_hoi` enum('TRAC_NGHIEM','DUNG_SAI','TU_LUAN') NOT NULL,
  `diem` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `de_thi_id` (`de_thi_id`),
  KEY `nguoi_tao_id` (`nguoi_tao_id`),
  CONSTRAINT `ntq_cau_hoi_ibfk_1` FOREIGN KEY (`de_thi_id`) REFERENCES `ntq_de_thi` (`id`) ON DELETE CASCADE,
  CONSTRAINT `ntq_cau_hoi_ibfk_2` FOREIGN KEY (`nguoi_tao_id`) REFERENCES `ntq_nguoi_dung` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_cau_hoi`
--

LOCK TABLES `ntq_cau_hoi` WRITE;
/*!40000 ALTER TABLE `ntq_cau_hoi` DISABLE KEYS */;
INSERT INTO `ntq_cau_hoi` VALUES (1,1,2,'Câu hỏi 1 môn Toán','TRAC_NGHIEM',2.00),(2,2,3,'Câu hỏi 1 môn Lý','TRAC_NGHIEM',3.00),(3,3,4,'Câu hỏi 1 môn Hóa','TU_LUAN',4.00),(4,4,5,'Câu hỏi 1 môn Văn','DUNG_SAI',1.00),(5,5,1,'Câu hỏi 1 môn Anh Văn','TRAC_NGHIEM',2.00);
/*!40000 ALTER TABLE `ntq_cau_hoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ntq_danh_muc`
--

DROP TABLE IF EXISTS `ntq_danh_muc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ntq_danh_muc` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ten` varchar(100) NOT NULL,
  `mo_ta` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ten` (`ten`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_danh_muc`
--

LOCK TABLES `ntq_danh_muc` WRITE;
/*!40000 ALTER TABLE `ntq_danh_muc` DISABLE KEYS */;
INSERT INTO `ntq_danh_muc` VALUES (1,'Toán','Môn học toán'),(2,'Lý','Môn học lý'),(3,'Hóa','Môn học hóa'),(4,'Văn','Môn học văn'),(5,'Anh Văn','Môn học tiếng Anh');
/*!40000 ALTER TABLE `ntq_danh_muc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ntq_dap_an`
--

DROP TABLE IF EXISTS `ntq_dap_an`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ntq_dap_an` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cau_hoi_id` bigint NOT NULL,
  `noi_dung` text NOT NULL,
  `dung_sai` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cau_hoi_id` (`cau_hoi_id`),
  CONSTRAINT `ntq_dap_an_ibfk_1` FOREIGN KEY (`cau_hoi_id`) REFERENCES `ntq_cau_hoi` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_dap_an`
--

LOCK TABLES `ntq_dap_an` WRITE;
/*!40000 ALTER TABLE `ntq_dap_an` DISABLE KEYS */;
INSERT INTO `ntq_dap_an` VALUES (1,1,'Đáp án A',1),(2,2,'Đáp án B',0),(3,3,'Đáp án C',1),(4,4,'Đáp án D',0),(5,5,'Đáp án E',1);
/*!40000 ALTER TABLE `ntq_dap_an` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ntq_de_thi`
--

DROP TABLE IF EXISTS `ntq_de_thi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ntq_de_thi` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tieu_de` varchar(255) NOT NULL,
  `mo_ta` text,
  `danh_muc_id` bigint DEFAULT NULL,
  `nguoi_tao_id` bigint NOT NULL,
  `ngay_tao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `danh_muc_id` (`danh_muc_id`),
  KEY `nguoi_tao_id` (`nguoi_tao_id`),
  CONSTRAINT `ntq_de_thi_ibfk_1` FOREIGN KEY (`danh_muc_id`) REFERENCES `ntq_danh_muc` (`id`) ON DELETE SET NULL,
  CONSTRAINT `ntq_de_thi_ibfk_2` FOREIGN KEY (`nguoi_tao_id`) REFERENCES `ntq_nguoi_dung` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_de_thi`
--

LOCK TABLES `ntq_de_thi` WRITE;
/*!40000 ALTER TABLE `ntq_de_thi` DISABLE KEYS */;
INSERT INTO `ntq_de_thi` VALUES (1,'Đề thi Toán lớp 10','Đề thi thử môn Toán',1,2,'2025-02-27 07:55:07'),(2,'Đề thi Lý lớp 10','Đề thi thử môn Lý',2,3,'2025-02-27 07:55:07'),(3,'Đề thi Hóa lớp 10','Đề thi thử môn Hóa',3,4,'2025-02-27 07:55:07'),(4,'Đề thi Văn lớp 10','Đề thi thử môn Văn',4,5,'2025-02-27 07:55:07'),(5,'Đề thi Anh Văn lớp 10','Đề thi thử môn tiếng Anh',5,1,'2025-02-27 07:55:07');
/*!40000 ALTER TABLE `ntq_de_thi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ntq_nguoi_dung`
--

DROP TABLE IF EXISTS `ntq_nguoi_dung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ntq_nguoi_dung` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ten_dang_nhap` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mat_khau` varchar(255) NOT NULL,
  `vai_tro` enum('HOC_SINH','GIAO_VIEN','QUAN_TRI') NOT NULL,
  `ngay_tao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ten_dang_nhap` (`ten_dang_nhap`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_nguoi_dung`
--

LOCK TABLES `ntq_nguoi_dung` WRITE;
/*!40000 ALTER TABLE `ntq_nguoi_dung` DISABLE KEYS */;
INSERT INTO `ntq_nguoi_dung` VALUES (1,'user1','user1@example.com','password123','HOC_SINH','2025-02-27 07:55:07'),(2,'user2','user2@example.com','password123','GIAO_VIEN','2025-02-27 07:55:07'),(3,'user3','user3@example.com','password123','QUAN_TRI','2025-02-27 07:55:07'),(4,'user4','user4@example.com','password123','HOC_SINH','2025-02-27 07:55:07'),(5,'user5','user5@example.com','password123','GIAO_VIEN','2025-02-27 07:55:07');
/*!40000 ALTER TABLE `ntq_nguoi_dung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ntq_phan_hoi`
--

DROP TABLE IF EXISTS `ntq_phan_hoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ntq_phan_hoi` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nguoi_dung_id` bigint NOT NULL,
  `de_thi_id` bigint NOT NULL,
  `binh_luan` text,
  `danh_gia` int DEFAULT NULL,
  `ngay_tao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `nguoi_dung_id` (`nguoi_dung_id`),
  KEY `de_thi_id` (`de_thi_id`),
  CONSTRAINT `ntq_phan_hoi_ibfk_1` FOREIGN KEY (`nguoi_dung_id`) REFERENCES `ntq_nguoi_dung` (`id`) ON DELETE CASCADE,
  CONSTRAINT `ntq_phan_hoi_ibfk_2` FOREIGN KEY (`de_thi_id`) REFERENCES `ntq_de_thi` (`id`) ON DELETE CASCADE,
  CONSTRAINT `ntq_phan_hoi_chk_1` CHECK ((`danh_gia` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_phan_hoi`
--

LOCK TABLES `ntq_phan_hoi` WRITE;
/*!40000 ALTER TABLE `ntq_phan_hoi` DISABLE KEYS */;
INSERT INTO `ntq_phan_hoi` VALUES (1,1,1,'Đề thi rất hay',5,'2025-02-27 07:55:07'),(2,2,2,'Đề thi khá khó',4,'2025-02-27 07:55:07'),(3,3,3,'Đề thi hợp lý',3,'2025-02-27 07:55:07'),(4,4,4,'Đề thi dễ dàng',5,'2025-02-27 07:55:07'),(5,5,5,'Đề thi cần cải thiện',2,'2025-02-27 07:55:07');
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

-- Dump completed on 2025-02-28 21:19:51
