-- MySQL dump 10.13  Distrib 8.0.36, for Win64
--
-- Host: localhost    Database: agenda_telefonica
-- ------------------------------------------------------
-- Server version       8.0.36
--
-- Gerado para entrega do trabalho de Agenda Telefonica - PUC.
-- Para restaurar:  mysql -u root -p < dump_agenda_telefonica.sql

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `agenda_telefonica`
--

DROP DATABASE IF EXISTS `agenda_telefonica`;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `agenda_telefonica`
    /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */
    /*!80016 DEFAULT ENCRYPTION='N' */;

USE `agenda_telefonica`;

--
-- Table structure for table `contato`
--

DROP TABLE IF EXISTS `contato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contato` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `email` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contato`
--

LOCK TABLES `contato` WRITE;
/*!40000 ALTER TABLE `contato` DISABLE KEYS */;
INSERT INTO `contato` (`id`, `nome`, `telefone`, `email`) VALUES
 (1, 'Ana Beatriz Souza',   '(31) 9 9876-1122', 'ana.souza@example.com'),
 (2, 'Bruno Oliveira',      '(11) 9 8765-4321', 'bruno.oliveira@example.com'),
 (3, 'Camilly Rodrigues',   '(31) 9 9123-4567', 'camilly.rodrigues@example.com'),
 (4, 'Daniel Pereira',      '(21) 9 9654-7788', 'daniel.pereira@example.com'),
 (5, 'Eduarda Lima',        '(31) 9 8877-6655', 'eduarda.lima@example.com'),
 (6, 'Felipe Martins',      '(11) 9 7766-5544', 'felipe.martins@example.com'),
 (7, 'Gabriela Almeida',    '(31) 9 9001-2233', 'gabriela.almeida@example.com'),
 (8, 'Henrique Costa',      '(85) 9 8899-7766', 'henrique.costa@example.com'),
 (9, 'Isabela Fernandes',   '(31) 9 9345-6789', 'isabela.fernandes@example.com'),
 (10, 'Joao Victor Lima',   '(11) 9 9234-5678', 'joao.victor@example.com'),
 (11, 'Larissa Mendes',     '(21) 9 9456-7890', 'larissa.mendes@example.com'),
 (12, 'Marcos Silva',       '(31) 9 9567-8901', 'marcos.silva@example.com'),
 (13, 'Natalia Ribeiro',    '(47) 9 9678-9012', 'natalia.ribeiro@example.com'),
 (14, 'Otavio Carvalho',    '(11) 9 9789-0123', 'otavio.carvalho@example.com'),
 (15, 'Paula Nunes',        '(31) 9 9890-1234', 'paula.nunes@example.com'),
 (16, 'Rafael Santos',      '(62) 9 9901-2345', 'rafael.santos@example.com'),
 (17, 'Sofia Andrade',      '(85) 9 9012-3456', 'sofia.andrade@example.com'),
 (18, 'Thiago Barbosa',     '(31) 9 9123-4560', 'thiago.barbosa@example.com');
/*!40000 ALTER TABLE `contato` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
