-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Carlos','Tocho','test@test.com'),(2,'nombre','apellido','email'),(3,'Oscar','Lara','roskou@gmail.com'),(4,'Sergio','Lopez','serlopez@gmail.com'),(5,'Hector','Costilla','hecostilla@gmail.com'),(6,'Braulio','Combe','brcombe@gmail.com'),(7,'Eva','Lozano','evlozano@gmail.com'),(55,'lolo','lolo','lolo@lolo.com'),(56,'jose','jose','sofia@sofia.com'),(57,'lala','lala','hoooolaaaaa@hoooolaaaaa.com'),(58,'Sofia','Reina','soyreina@gmail.com'),(59,'miguel','pablos','mig@pab.com'),(63,'Carlos','Carlota','catl@gmail.com');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habitacion`
--

DROP TABLE IF EXISTS `habitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habitacion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo` varchar(260) DEFAULT NULL,
  `descripcion` varchar(260) DEFAULT NULL,
  `precio` float NOT NULL,
  `tipo_id` bigint DEFAULT NULL,
  `numpersonas` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt7s5am7hg4x7lpt25wpn8m3ju` (`tipo_id`),
  CONSTRAINT `FKt7s5am7hg4x7lpt25wpn8m3ju` FOREIGN KEY (`tipo_id`) REFERENCES `tipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habitacion`
--

LOCK TABLES `habitacion` WRITE;
/*!40000 ALTER TABLE `habitacion` DISABLE KEYS */;
INSERT INTO `habitacion` VALUES (1,'Gaudí','Lorem ipsum dolor sit amet consectetur adipisicing elit. Harum consequatur maxime, assumenda debitis iure aspernatur cum tenetur nisi aperiam exercitationem. At quasi, impedit quisquam laborum minima id debitis velit asperiores earum dolorum quia reprehenderit',300,1,2),(2,'Pedralbes','Lorem ipsum dolor sit amet consectetur adipisicing elit. Harum consequatur maxime, assumenda debitis iure aspernatur cum tenetur nisi aperiam exercitationem. At quasi, impedit quisquam laborum minima id debitis velit asperiores earum dolorum quia reprehenderit',700,3,4),(3,'La Rambla','Lorem ipsum dolor sit amet consectetur adipisicing elit. Harum consequatur maxime, assumenda debitis iure aspernatur cum tenetur nisi aperiam exercitationem. At quasi, impedit quisquam laborum minima id debitis velit asperiores earum dolorum quia reprehenderit',500,1,2),(4,'Batlló','Lorem ipsum dolor sit amet consectetur adipisicing elit. Harum consequatur maxime, assumenda debitis iure aspernatur cum tenetur nisi aperiam exercitationem. At quasi, impedit quisquam laborum minima id debitis velit asperiores earum dolorum quia reprehenderit',150,4,2);
/*!40000 ALTER TABLE `habitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `enabled` tinyint DEFAULT NULL,
  `id_cliente` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1cfu8vpuvyfr3pq6cyvng4j0k` (`id_cliente`),
  CONSTRAINT `FK1cfu8vpuvyfr3pq6cyvng4j0k` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'adrian','1234','ROLE_USER',1,1),(2,'taribo','taribo','ROLE_USER',1,2),(9,'lolo','lolo','ROLE_USER',1,55),(10,'jose','jose','ROLE_USER',1,56),(11,'lala','lala','ROLE_USER',1,57),(12,'sofiasofia','sofiasofia','ROLE_USER',1,58),(13,'miguel','miguel','ROLE_USER',1,59),(17,'caracarcarac','password','ROLE_USER',1,63);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fechaIn` date DEFAULT NULL,
  `fechaOut` date DEFAULT NULL,
  `precioTotal` float NOT NULL,
  `cliente_id` bigint DEFAULT NULL,
  `id_habitacion` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7cg2jiyn5cf6f6elccvb6963k` (`cliente_id`),
  KEY `FKe2otj7riiuv7q4k4t58gg9kj1` (`id_habitacion`),
  CONSTRAINT `FK7cg2jiyn5cf6f6elccvb6963k` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKe2otj7riiuv7q4k4t58gg9kj1` FOREIGN KEY (`id_habitacion`) REFERENCES `habitacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (1,'2020-01-01','2020-01-05',200,1,1),(2,'2020-01-01','2020-01-05',300,2,2),(3,'2020-01-01','2020-01-05',500,1,1),(4,'2020-03-01','2020-03-05',300,2,2),(11,'2020-10-02','2020-11-03',3200,1,1),(12,'2020-09-30','2020-10-27',2700,1,1),(13,'2020-10-27','2020-10-29',200,1,2),(14,'2020-10-26','2020-10-30',400,1,1),(15,'2020-10-02','2020-10-10',800,1,1),(16,'2020-10-05','2020-10-15',1000,1,1),(17,'2020-09-28','2020-10-28',9000,1,3),(18,'2020-10-12','2020-10-21',2700,1,4),(65,'2020-11-11','2020-11-12',300,1,1),(66,'2020-11-02','2020-11-15',6175,1,3),(67,'2020-11-03','2020-11-08',1500,1,1),(68,'2020-11-12','2020-11-14',600,1,1),(84,'2020-11-09','2020-11-10',276,1,1),(85,'2020-11-16','2020-11-17',276,1,1),(86,'2020-11-17','2020-11-19',552,1,1),(87,'2020-11-19','2020-11-21',552,1,1),(88,'2020-11-14','2020-11-15',644,1,2),(89,'2020-11-21','2020-11-21',500,1,3),(90,'2020-11-16','2020-11-18',276,1,4),(91,'2020-11-12','2020-11-13',138,1,4),(92,'2020-11-18','2020-11-20',920,1,3),(93,'2020-11-17','2020-12-03',1968,1,4),(94,'2020-11-13','2020-11-21',5152,1,2),(95,'2020-11-21','2020-11-29',2208,1,1),(96,'2020-11-09','2020-11-09',700,1,2);
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temporadas`
--

DROP TABLE IF EXISTS `temporadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temporadas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `fecha_ini` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temporadas`
--

LOCK TABLES `temporadas` WRITE;
/*!40000 ALTER TABLE `temporadas` DISABLE KEYS */;
INSERT INTO `temporadas` VALUES (1,'alta','2020-07-15','2020-08-31',-0.1),(2,'alta','2020-12-15','2021-01-08',-0.1),(3,'baja','2020-03-01','2020-04-30',0.08),(4,'baja','2020-11-01','2020-12-01',0.08),(5,'alta','2021-07-15','2021-08-31',-0.1),(6,'alta','2021-12-15','2021-01-08',-0.1),(7,'baja','2021-03-01','2021-04-30',0.08),(8,'baja','2021-11-01','2021-12-01',0.08);
/*!40000 ALTER TABLE `temporadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo`
--

DROP TABLE IF EXISTS `tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo`
--

LOCK TABLES `tipo` WRITE;
/*!40000 ALTER TABLE `tipo` DISABLE KEYS */;
INSERT INTO `tipo` VALUES (1,'Doble','Doble'),(2,'Simple','Simple'),(3,'Enorme','Enorme'),(4,'Pequeña','Pequeña');
/*!40000 ALTER TABLE `tipo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-11 10:59:22
