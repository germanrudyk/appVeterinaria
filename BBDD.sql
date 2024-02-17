CREATE DATABASE  IF NOT EXISTS `veterinaria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `veterinaria`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: veterinaria
-- ------------------------------------------------------
-- Server version	8.0.28

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
  `id` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `cta_cte_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs36sbowjngmwg0kflbm7jyaro` (`cta_cte_id`),
  CONSTRAINT `FKs36sbowjngmwg0kflbm7jyaro` FOREIGN KEY (`cta_cte_id`) REFERENCES `ctacte` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('7cd7eea9-fed7-4713-be52-04ba8077f2e3','Carlos Estevez','2625407410','5c126280-395a-4fe0-a54c-e39253b006d6'),('a3f0eab6-b89d-4aef-8bd5-bb06565fbb05','Jorge Toledo','2625407410','43122535-87c6-49f8-b0dd-347c53160b2e');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctacte`
--

DROP TABLE IF EXISTS `ctacte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctacte` (
  `id` varchar(255) NOT NULL,
  `monto` int DEFAULT NULL,
  `cliente_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7liaarnyusmycgffm30i116ld` (`cliente_id`),
  CONSTRAINT `FK7liaarnyusmycgffm30i116ld` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctacte`
--

LOCK TABLES `ctacte` WRITE;
/*!40000 ALTER TABLE `ctacte` DISABLE KEYS */;
INSERT INTO `ctacte` VALUES ('43122535-87c6-49f8-b0dd-347c53160b2e',NULL,'a3f0eab6-b89d-4aef-8bd5-bb06565fbb05'),('5c126280-395a-4fe0-a54c-e39253b006d6',NULL,'7cd7eea9-fed7-4713-be52-04ba8077f2e3');
/*!40000 ALTER TABLE `ctacte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ctacte_productos`
--

DROP TABLE IF EXISTS `ctacte_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ctacte_productos` (
  `cta_cte_id` varchar(255) NOT NULL,
  `productos_id` varchar(255) NOT NULL,
  UNIQUE KEY `UK_chtlo7434p972auyp7619iw8c` (`productos_id`),
  KEY `FK5h6enoyijhsfp49dppdddvkfw` (`cta_cte_id`),
  CONSTRAINT `FK5h6enoyijhsfp49dppdddvkfw` FOREIGN KEY (`cta_cte_id`) REFERENCES `ctacte` (`id`),
  CONSTRAINT `FKdbqurpne7l3mvp6ueyb0q6l29` FOREIGN KEY (`productos_id`) REFERENCES `productocta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ctacte_productos`
--

LOCK TABLES `ctacte_productos` WRITE;
/*!40000 ALTER TABLE `ctacte_productos` DISABLE KEYS */;
INSERT INTO `ctacte_productos` VALUES ('5c126280-395a-4fe0-a54c-e39253b006d6','7d9be226-57a7-43ca-a591-8b791c9241d2'),('5c126280-395a-4fe0-a54c-e39253b006d6','a3fea484-0787-4269-aa26-45abf5dea25b');
/*!40000 ALTER TABLE `ctacte_productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` int DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES ('04c0f425-2aad-4b17-8b4a-24e9814dce7e','Desparasitario',8500,2),('45f16d6b-e480-4963-ba6d-3d1bc1b56361','Pipeta',6000,1),('8e4a9fe8-faa4-40dc-b73e-af358e79cb95','Alimento para gatos',9700,0),('ae7d7518-e1c7-452e-ba4f-711be61c630c','Alimento para perros',3900,4);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productocta`
--

DROP TABLE IF EXISTS `productocta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productocta` (
  `id` varchar(255) NOT NULL,
  `cancelado` bit(1) DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `montoacancelar` int DEFAULT NULL,
  `precio_total` int DEFAULT NULL,
  `precio_unitario` int DEFAULT NULL,
  `cliente_id` varchar(255) DEFAULT NULL,
  `producto_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhbjaj3wv1irsys763d882snj8` (`cliente_id`),
  KEY `FK2gn4d99qcvx0uja457tclp96p` (`producto_id`),
  CONSTRAINT `FK2gn4d99qcvx0uja457tclp96p` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKhbjaj3wv1irsys763d882snj8` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productocta`
--

LOCK TABLES `productocta` WRITE;
/*!40000 ALTER TABLE `productocta` DISABLE KEYS */;
INSERT INTO `productocta` VALUES ('01106b71-0998-421a-ae63-04d468a868e7',_binary '',4,60500,327600,81900,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','45f16d6b-e480-4963-ba6d-3d1bc1b56361'),('0f6b1a6d-15a1-4c6e-950a-2fb91851589e',_binary '',1,3900,3900,3900,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','ae7d7518-e1c7-452e-ba4f-711be61c630c'),('11db3f22-90fb-41f3-9b99-dc794d7b2363',_binary '',1,44600,51100,51100,NULL,'45f16d6b-e480-4963-ba6d-3d1bc1b56361'),('26696afa-d02f-478f-9199-baf1ead1b9d5',_binary '',4,0,135000,40500,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','04c0f425-2aad-4b17-8b4a-24e9814dce7e'),('2a3d775b-db5a-4765-b80a-92e400317835',_binary '',1,2000,2000,2000,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','04c0f425-2aad-4b17-8b4a-24e9814dce7e'),('3fabdd30-5301-4b3c-bf5f-af3c12c21f81',_binary '\0',1,71400,81900,81900,NULL,'45f16d6b-e480-4963-ba6d-3d1bc1b56361'),('40f87639-134c-4554-a2ab-6b76cafa0a6f',_binary '\0',2,142700,163800,81900,NULL,'45f16d6b-e480-4963-ba6d-3d1bc1b56361'),('41e8f00e-7728-4717-87b0-47e61b7a3ee4',_binary '',4,16000,16000,8000,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','04c0f425-2aad-4b17-8b4a-24e9814dce7e'),('47f408b0-14ae-4a22-9a64-75924d973e29',_binary '',2,89200,102200,51100,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','45f16d6b-e480-4963-ba6d-3d1bc1b56361'),('54f8b7d0-bddf-4339-adb9-9639a07a1e7f',_binary '',3,10000,28500,9500,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','8e4a9fe8-faa4-40dc-b73e-af358e79cb95'),('57401f01-159a-4fad-9d5f-c546e1345372',_binary '',2,7200,19000,9500,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','8e4a9fe8-faa4-40dc-b73e-af358e79cb95'),('6837c505-b6e8-49d5-8068-4a98ce58adee',_binary '\0',2,142700,163800,81900,NULL,'45f16d6b-e480-4963-ba6d-3d1bc1b56361'),('70e8ef28-72f7-4e32-abdd-0862ede9b68f',_binary '',1,0,9500,9500,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','8e4a9fe8-faa4-40dc-b73e-af358e79cb95'),('78b6b3f6-2317-448a-9d53-a1c478c97b91',_binary '',2,0,7800,3900,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','ae7d7518-e1c7-452e-ba4f-711be61c630c'),('7a713e52-c14d-4929-8c4a-f7a468b4e1e6',_binary '',3,200000,245700,81900,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','45f16d6b-e480-4963-ba6d-3d1bc1b56361'),('7d9be226-57a7-43ca-a591-8b791c9241d2',_binary '\0',2,163800,163800,81900,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','45f16d6b-e480-4963-ba6d-3d1bc1b56361'),('906ab5d8-8d08-4001-9129-4c927e8c1cac',_binary '',2,7800,7800,3900,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','ae7d7518-e1c7-452e-ba4f-711be61c630c'),('9214c1e8-1ce7-414b-b318-48a41c6bb163',_binary '',2,1000000,1079400,539700,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','04c0f425-2aad-4b17-8b4a-24e9814dce7e'),('945518d9-8e47-4cda-a990-97b062b7990c',_binary '\0',3,11700,11700,3900,NULL,'ae7d7518-e1c7-452e-ba4f-711be61c630c'),('9d3b3ff6-5b98-4543-a997-30a1bc9e8b5f',_binary '',1,81900,81900,81900,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','45f16d6b-e480-4963-ba6d-3d1bc1b56361'),('a3fea484-0787-4269-aa26-45abf5dea25b',_binary '\0',2,19400,19400,9700,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','8e4a9fe8-faa4-40dc-b73e-af358e79cb95'),('a6582739-fe83-4bef-9165-e2f69982ecd5',_binary '',2,0,7800,3900,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','ae7d7518-e1c7-452e-ba4f-711be61c630c'),('a8375809-4846-4d03-9424-9680bf2eb943',_binary '',4,178400,204400,51100,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','45f16d6b-e480-4963-ba6d-3d1bc1b56361'),('b233a75a-3bb6-43ba-a678-d4f8a3a70c9e',_binary '\0',2,142700,163800,81900,NULL,'45f16d6b-e480-4963-ba6d-3d1bc1b56361'),('b284b2d2-cf57-4ac5-a7c1-9783182e6af3',_binary '',6,0,72000,12000,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','04c0f425-2aad-4b17-8b4a-24e9814dce7e'),('b9ff3e08-1aa0-427e-95c8-6685952c3aed',_binary '',3,133800,153300,51100,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','45f16d6b-e480-4963-ba6d-3d1bc1b56361'),('c0245b91-20bd-4d52-aa98-694d7a176c53',_binary '',7,619300,3777900,539700,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','04c0f425-2aad-4b17-8b4a-24e9814dce7e'),('c06be64f-179c-4e17-93b8-eea6b40114ea',_binary '',1,539700,539700,539700,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','04c0f425-2aad-4b17-8b4a-24e9814dce7e'),('c083a033-de07-4a6a-9c2f-f0037f716a0a',_binary '',2,100000,163800,81900,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','45f16d6b-e480-4963-ba6d-3d1bc1b56361'),('c7f1b0e9-980d-4a3c-a90d-9a1c667a57f0',_binary '',2,7800,7800,3900,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','ae7d7518-e1c7-452e-ba4f-711be61c630c'),('c8d82678-e316-4514-a594-49abe54bc738',_binary '',6,220000,267600,44600,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','04c0f425-2aad-4b17-8b4a-24e9814dce7e'),('c90097d4-6071-4fb1-8351-e72b3fa6c89a',_binary '',2,10000,19000,9500,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','8e4a9fe8-faa4-40dc-b73e-af358e79cb95'),('ce1f0be2-2ab7-4b9d-b68a-4263c8de01a3',_binary '',4,56000,72000,18000,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','04c0f425-2aad-4b17-8b4a-24e9814dce7e'),('f7eaab27-b346-413e-96a3-8cdff3ad8203',_binary '',1,3900,3900,3900,'7cd7eea9-fed7-4713-be52-04ba8077f2e3','ae7d7518-e1c7-452e-ba4f-711be61c630c');
/*!40000 ALTER TABLE `productocta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('b7a3d8ce-688a-46ac-bcfc-a94b244c91e8','Fabian','$2a$10$5g.dvuKJvvB1FViyjqdk2eLoiEEnpf6H6dCBcaayYp2pN.CbU9C9y','EMPLEADO','fabian'),('f8fdbd84-8368-4340-84ea-6136ea66fe7e','Leo','$2a$10$2QHtAU5JNN.7oahpg21EEukIhLQA9TE4SMdQ7khNwYhxVXTsZy3Sq','LEO','leo');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-17 11:01:57
