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
  `nguoi_tao_id` bigint NOT NULL,
  `noi_dung` varchar(255) NOT NULL,
  `loai_cau_hoi` enum('TRAC_NGHIEM','DUNG_SAI','TU_LUAN') NOT NULL,
  `diem` double NOT NULL,
  `dap_an_a` text,
  `dap_an_b` text,
  `dap_an_c` text,
  `dap_an_d` text,
  `dap_an_dung` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_nguoi_tao_id` (`nguoi_tao_id`),
  CONSTRAINT `fk_cau_hoi_nguoi_tao` FOREIGN KEY (`nguoi_tao_id`) REFERENCES `ntq_nguoi_dung` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_cau_hoi`
--

LOCK TABLES `ntq_cau_hoi` WRITE;
/*!40000 ALTER TABLE `ntq_cau_hoi` DISABLE KEYS */;
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
  `ten` varchar(255) NOT NULL,
  `mo_ta` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_ten` (`ten`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_danh_muc`
--

LOCK TABLES `ntq_danh_muc` WRITE;
/*!40000 ALTER TABLE `ntq_danh_muc` DISABLE KEYS */;
/*!40000 ALTER TABLE `ntq_danh_muc` ENABLE KEYS */;
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
  KEY `idx_danh_muc_id` (`danh_muc_id`),
  KEY `idx_nguoi_tao_id` (`nguoi_tao_id`),
  CONSTRAINT `fk_de_thi_danh_muc` FOREIGN KEY (`danh_muc_id`) REFERENCES `ntq_danh_muc` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_de_thi_nguoi_tao` FOREIGN KEY (`nguoi_tao_id`) REFERENCES `ntq_nguoi_dung` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_de_thi`
--

LOCK TABLES `ntq_de_thi` WRITE;
/*!40000 ALTER TABLE `ntq_de_thi` DISABLE KEYS */;
/*!40000 ALTER TABLE `ntq_de_thi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ntq_de_thi_cau_hoi`
--

DROP TABLE IF EXISTS `ntq_de_thi_cau_hoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ntq_de_thi_cau_hoi` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `de_thi_id` bigint NOT NULL,
  `cau_hoi_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_de_thi_id` (`de_thi_id`),
  KEY `idx_cau_hoi_id` (`cau_hoi_id`),
  CONSTRAINT `fk_de_thi_cau_hoi_cau_hoi` FOREIGN KEY (`cau_hoi_id`) REFERENCES `ntq_cau_hoi` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_de_thi_cau_hoi_de_thi` FOREIGN KEY (`de_thi_id`) REFERENCES `ntq_de_thi` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_de_thi_cau_hoi`
--

LOCK TABLES `ntq_de_thi_cau_hoi` WRITE;
/*!40000 ALTER TABLE `ntq_de_thi_cau_hoi` DISABLE KEYS */;
/*!40000 ALTER TABLE `ntq_de_thi_cau_hoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ntq_nguoi_dung`
--

DROP TABLE IF EXISTS `ntq_nguoi_dung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ntq_nguoi_dung` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ten_dang_nhap` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mat_khau` varchar(255) NOT NULL,
  `vai_tro` enum('HOC_SINH','GIAO_VIEN','QUAN_TRI','ROLE_HOC_SINH','ROLE_GIAO_VIEN','ROLE_QUAN_TRI') NOT NULL,
  `ngay_tao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `trang_thai` enum('HOAT_DONG','KHOA') DEFAULT 'HOAT_DONG',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_ten_dang_nhap` (`ten_dang_nhap`),
  UNIQUE KEY `uk_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ntq_nguoi_dung`
--

LOCK TABLES `ntq_nguoi_dung` WRITE;
/*!40000 ALTER TABLE `ntq_nguoi_dung` DISABLE KEYS */;
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
  KEY `idx_nguoi_dung_id` (`nguoi_dung_id`),
  KEY `idx_de_thi_id` (`de_thi_id`),
  CONSTRAINT `fk_phan_hoi_de_thi` FOREIGN KEY (`de_thi_id`) REFERENCES `ntq_de_thi` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_phan_hoi_nguoi_dung` FOREIGN KEY (`nguoi_dung_id`) REFERENCES `ntq_nguoi_dung` (`id`) ON DELETE CASCADE,
  CONSTRAINT `chk_danh_gia` CHECK ((`danh_gia` between 1 and 5))
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

-- Dump completed on 2025-03-07 20:07:40
