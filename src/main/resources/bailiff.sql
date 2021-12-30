CREATE DATABASE  IF NOT EXISTS `bailiff` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bailiff`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: bailiff
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `bailiff`
--

DROP TABLE IF EXISTS `bailiff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bailiff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fio_UNIQUE` (`fio`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bailiff`
--

LOCK TABLES `bailiff` WRITE;
/*!40000 ALTER TABLE `bailiff` DISABLE KEYS */;
INSERT INTO `bailiff` VALUES (2,'Заноза В.П.'),(1,'Лашук А.И.'),(3,'Пробчук М.Г.');
/*!40000 ALTER TABLE `bailiff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fio_UNIQUE` (`fio`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (2,'Бакунович Е.И.'),(9,'Бобрик А.В.'),(8,'Бобров Б.Б.'),(6,'Борисенко В.Д.'),(5,'Борисёнок В.Н.'),(3,'Макаренко Т.И.'),(4,'Мильто Н.С.'),(1,'Пупкин Р.О.'),(7,'Рысевец Д.А.');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation`
--

DROP TABLE IF EXISTS `operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation`
--

LOCK TABLES `operation` WRITE;
/*!40000 ALTER TABLE `operation` DISABLE KEYS */;
INSERT INTO `operation` VALUES (1,'Алименты'),(2,'Обращение в суд'),(3,'Работа с иностранными гражданами'),(4,'Составление документации');
/*!40000 ALTER TABLE `operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `client` varchar(100) NOT NULL,
  `operation` varchar(100) NOT NULL,
  `bailiff` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `cl_idx` (`client`),
  KEY `oper_idx` (`operation`),
  KEY `bail_idx` (`bailiff`),
  CONSTRAINT `bail` FOREIGN KEY (`bailiff`) REFERENCES `bailiff` (`fio`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cl` FOREIGN KEY (`client`) REFERENCES `client` (`fio`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oper` FOREIGN KEY (`operation`) REFERENCES `operation` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'Бакунович Е.И.','Алименты','Заноза В.П.'),(2,'Борисёнок В.Н.','Работа с иностранными гражданами','Пробчук М.Г.'),(3,'Пупкин Р.О.','Алименты','Заноза В.П.'),(4,'Бобрик А.В.','Обращение в суд','Лашук А.И.'),(5,'Борисенко В.Д.','Алименты','Пробчук М.Г.'),(6,'Рысевец Д.А.','Составление документации','Заноза В.П.'),(7,'Мильто Н.С.','Обращение в суд','Лашук А.И.'),(8,'Бобров Б.Б.','Составление документации','Лашук А.И.'),(9,'Макаренко Т.И.','Обращение в суд','Пробчук М.Г.'),(10,'Бакунович Е.И.','Алименты','Лашук А.И.');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-30 19:57:10
