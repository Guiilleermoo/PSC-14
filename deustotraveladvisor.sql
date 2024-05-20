/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Cliente`
--
use deustotraveladvisorDB;
DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cliente` (
  `dni` varchar(9) DEFAULT NULL,
  `nombre` varchar(9) DEFAULT NULL,
  `gmail` varchar(27) DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  `residencia` varchar(6) DEFAULT NULL,
  `password` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
INSERT INTO `Cliente` VALUES ('1','BORJA','BORJA',123456789,'BASDA',''),('2','jon','jon',123456789,'qqewq',''),('20233914R','Unai Aira','valdivielso.inigo@gmail.com',678491233,'Bilbao','guillermo123');
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Favorito`
--

DROP TABLE IF EXISTS `Favorito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Favorito` (
  `id` smallint(6) DEFAULT NULL,
  `cliente` varchar(9) DEFAULT NULL,
  `viaje` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Favorito`
--

LOCK TABLES `Favorito` WRITE;
/*!40000 ALTER TABLE `Favorito` DISABLE KEYS */;
INSERT INTO `Favorito` VALUES (1,'1',36),(2,'1',37),(3,'1',14),(6,'1',14),(7,'1',14),(8,'1',36),(103,'20233914R',36);
/*!40000 ALTER TABLE `Favorito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reserva`
--

DROP TABLE IF EXISTS `Reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reserva` (
  `cliente` varchar(9) DEFAULT NULL,
  `viaje` tinyint(4) DEFAULT NULL,
  `id` smallint(6) DEFAULT NULL,
  `num_plazas` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reserva`
--

LOCK TABLES `Reserva` WRITE;
/*!40000 ALTER TABLE `Reserva` DISABLE KEYS */;
INSERT INTO `Reserva` VALUES ('1',14,242,4),('1',14,421,1),('1',14,422,5),('20233914R',36,463,5);
/*!40000 ALTER TABLE `Reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trabajador`
--

DROP TABLE IF EXISTS `Trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Trabajador` (
  `DNI` varchar(9) DEFAULT NULL,
  `Nombre` varchar(5) DEFAULT NULL,
  `Gmail` varchar(26) DEFAULT NULL,
  `Telefono` int(11) DEFAULT NULL,
  `Sueldo` smallint(6) DEFAULT NULL,
  `password` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trabajador`
--

LOCK TABLES `Trabajador` WRITE;
/*!40000 ALTER TABLE `Trabajador` DISABLE KEYS */;
INSERT INTO `Trabajador` VALUES ('20233914S','Iñigo','txikivaldi@gmail.com	',608421488,2000,''),('20233914C','Unai','unai.aira@deustoadvisor.es',615478422,2500,'unai1234');
/*!40000 ALTER TABLE `Trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Viaje`
--

DROP TABLE IF EXISTS `Viaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Viaje` (
  `ID` tinyint(4) DEFAULT NULL,
  `origen` varchar(9) DEFAULT NULL,
  `destino` varchar(6) DEFAULT NULL,
  `fecha` varchar(10) DEFAULT NULL,
  `duracion` smallint(6) DEFAULT NULL,
  `precio` decimal(4,1) DEFAULT NULL,
  `oferta` tinyint(4) DEFAULT NULL,
  `empresa` varchar(6) DEFAULT NULL,
  `asientos_disponibles` smallint(6) DEFAULT NULL,
  `asientos_totales` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Viaje`
--

LOCK TABLES `Viaje` WRITE;
/*!40000 ALTER TABLE `Viaje` DISABLE KEYS */;
INSERT INTO `Viaje` VALUES (14,'Barcelona','Madrid','2024-05-06',5,52.0,4,'Deusto',53,95),(36,'Biilbao','Madrid','2024-04-18',1,134.0,14,'Deusto',95,100),(37,'Biilbao','Madrid','2024-04-25',100,168.0,8,'Deusto',110,120);
/*!40000 ALTER TABLE `Viaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sqlite_sequence`
--

DROP TABLE IF EXISTS `sqlite_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sqlite_sequence` (
  `name` varchar(7) DEFAULT NULL,
  `seq` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sqlite_sequence`
--

LOCK TABLES `sqlite_sequence` WRITE;
/*!40000 ALTER TABLE `sqlite_sequence` DISABLE KEYS */;
INSERT INTO `sqlite_sequence` VALUES ('Reserva',527),('Viaje',37);
/*!40000 ALTER TABLE `sqlite_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-30 16:42:42

/* DELETE 'deustoTravelAdvisor_user' database*/
DROP SCHEMA IF EXISTS deustotraveladvisor;
/* DELETE USER 'deustoTravelAdvisor_user' AT LOCAL SERVER*/
DROP USER IF EXISTS 'deustotraveladvisor_user'@'localhost';

/* CREATE ''deustoTravelAdvisordb' DATABASE */
CREATE SCHEMA IF NOT EXISTS deustotraveladvisor;
/* CREATE THE USER 'strava_user' AT LOCAL SERVER WITH PASSWORD 'strava_user' */
CREATE USER IF NOT EXISTS 'deustotraveladvisor_user'@'localhost' IDENTIFIED BY 'password';
/* GRANT FULL ACCESS TO THE DATABASE 'strava' FOR THE USER 'strava_user' AT LOCAL SERVER*/
GRANT ALL PRIVILEGES ON deustotraveladvisor.* TO 'deustotraveladvisor_user'@'localhost';
FLUSH PRIVILEGES;
