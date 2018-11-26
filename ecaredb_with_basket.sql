-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ecaredb
-- ------------------------------------------------------
-- Server version	5.6.23-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `basket`
--

DROP TABLE IF EXISTS `basket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basket` (
  `id_basket` int(11) NOT NULL,
  `id_tariff` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_basket`),
  KEY `fk_basket_tariff1_idx` (`id_tariff`),
  CONSTRAINT `fk_basket_tariff1` FOREIGN KEY (`id_tariff`) REFERENCES `tariff` (`id_tariff`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket`
--

LOCK TABLES `basket` WRITE;
/*!40000 ALTER TABLE `basket` DISABLE KEYS */;
INSERT INTO `basket` VALUES (3,NULL),(4,NULL),(1,3),(2,6);
/*!40000 ALTER TABLE `basket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basket_option_add`
--

DROP TABLE IF EXISTS `basket_option_add`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basket_option_add` (
  `id_basket_add` int(11) NOT NULL,
  `id_option_add` int(11) NOT NULL,
  PRIMARY KEY (`id_basket_add`,`id_option_add`),
  KEY `fk_basket_has_option_cellular_option_cellular1_idx` (`id_option_add`),
  KEY `fk_basket_has_option_cellular_basket1_idx` (`id_basket_add`),
  CONSTRAINT `fk_basket_has_option_cellular_basket1` FOREIGN KEY (`id_basket_add`) REFERENCES `basket` (`id_basket`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_basket_has_option_cellular_option_cellular1` FOREIGN KEY (`id_option_add`) REFERENCES `option_cellular` (`id_option`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket_option_add`
--

LOCK TABLES `basket_option_add` WRITE;
/*!40000 ALTER TABLE `basket_option_add` DISABLE KEYS */;
INSERT INTO `basket_option_add` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `basket_option_add` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basket_option_remove`
--

DROP TABLE IF EXISTS `basket_option_remove`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basket_option_remove` (
  `id_basket_remove` int(11) NOT NULL,
  `id_option_remove` int(11) NOT NULL,
  PRIMARY KEY (`id_basket_remove`,`id_option_remove`),
  KEY `fk_basket_has_option_cellular_option_cellular2_idx` (`id_option_remove`),
  KEY `fk_basket_has_option_cellular_basket2_idx` (`id_basket_remove`),
  CONSTRAINT `fk_basket_has_option_cellular_basket2` FOREIGN KEY (`id_basket_remove`) REFERENCES `basket` (`id_basket`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_basket_has_option_cellular_option_cellular2` FOREIGN KEY (`id_option_remove`) REFERENCES `option_cellular` (`id_option`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket_option_remove`
--

LOCK TABLES `basket_option_remove` WRITE;
/*!40000 ALTER TABLE `basket_option_remove` DISABLE KEYS */;
INSERT INTO `basket_option_remove` VALUES (1,4),(1,5);
/*!40000 ALTER TABLE `basket_option_remove` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract` (
  `id_contract` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(45) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `id_person` int(11) NOT NULL,
  `id_tariff` int(11) NOT NULL DEFAULT '0',
  `is_blocked_person` int(11) DEFAULT '0',
  `is_blocked_manager` int(11) DEFAULT '0',
  `id_basket` int(11) NOT NULL,
  PRIMARY KEY (`id_contract`,`id_tariff`,`id_person`),
  UNIQUE KEY `number_UNIQUE` (`number`),
  KEY `fk_contract_person_idx` (`id_person`),
  KEY `fk_contract_tariff1_idx` (`id_tariff`),
  KEY `fk_contract_basket1_idx` (`id_basket`),
  CONSTRAINT `fk_contract_basket1` FOREIGN KEY (`id_basket`) REFERENCES `basket` (`id_basket`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contract_person` FOREIGN KEY (`id_person`) REFERENCES `person` (`id_person`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_contract_tariff1` FOREIGN KEY (`id_tariff`) REFERENCES `tariff` (`id_tariff`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,'+7(956)765-34-21',NULL,1,3,0,0,1),(2,'+7(934)564-34-00',1345,1,6,0,0,2),(3,'666',NULL,3,2,0,0,3),(4,'+7(956)859-96-04',NULL,3,4,0,0,4);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract_option`
--

DROP TABLE IF EXISTS `contract_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract_option` (
  `id_contract` int(11) NOT NULL,
  `id_option` int(11) NOT NULL,
  PRIMARY KEY (`id_contract`,`id_option`),
  KEY `fk_contract_has_option_option1_idx` (`id_option`),
  KEY `fk_contract_has_option_contract1_idx` (`id_contract`),
  CONSTRAINT `fk_contract_has_option_contract1` FOREIGN KEY (`id_contract`) REFERENCES `contract` (`id_contract`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_contract_has_option_option1` FOREIGN KEY (`id_option`) REFERENCES `option_cellular` (`id_option`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract_option`
--

LOCK TABLES `contract_option` WRITE;
/*!40000 ALTER TABLE `contract_option` DISABLE KEYS */;
INSERT INTO `contract_option` VALUES (1,2),(1,5),(3,6);
/*!40000 ALTER TABLE `contract_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `option_cellular`
--

DROP TABLE IF EXISTS `option_cellular`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `option_cellular` (
  `id_option` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `cost_connection` int(11) DEFAULT NULL,
  `cost` int(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id_option`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `option_cellular`
--

LOCK TABLES `option_cellular` WRITE;
/*!40000 ALTER TABLE `option_cellular` DISABLE KEYS */;
INSERT INTO `option_cellular` VALUES (1,'Live Balance',20,300,'Terminated principles sentiments of no pianoforte if projection impossible. Horses pulled nature favour number yet highly his has old.Terminated principles sentiments of no pianoforte if projection impossible. Horses pulled nature favour number yet highly his has old.'),(2,'Messages',100,250,'Kindness to he horrible reserved ye. Effect twenty indeed beyond for not had county. The use him without greatly can private. Increasing it unpleasant no of contrasted no continuing. Nothing colonel my no removed in weather. It dissimilar in up devonshire inhabiting'),(3,'СМС',NULL,NULL,NULL),(4,'ММС',NULL,NULL,NULL),(5,'dkgmlh',NULL,NULL,NULL),(6,'qqqqqq',340,230,'sdfagrgeg\r\nfdgdgfddddddddddddddddddddddddddhdshshtsrh\r\ndfhgdshsdfhshs\r\nhsfdhdfh');
/*!40000 ALTER TABLE `option_cellular` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `option_exclude`
--

DROP TABLE IF EXISTS `option_exclude`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `option_exclude` (
  `id_option1` int(11) NOT NULL,
  `id_option2` int(11) NOT NULL,
  PRIMARY KEY (`id_option1`,`id_option2`),
  KEY `fk_option_has_option_option4_idx` (`id_option2`),
  KEY `fk_option_has_option_option3_idx` (`id_option1`),
  CONSTRAINT `fk_option_has_option_option3` FOREIGN KEY (`id_option1`) REFERENCES `option_cellular` (`id_option`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_option_has_option_option4` FOREIGN KEY (`id_option2`) REFERENCES `option_cellular` (`id_option`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `option_exclude`
--

LOCK TABLES `option_exclude` WRITE;
/*!40000 ALTER TABLE `option_exclude` DISABLE KEYS */;
INSERT INTO `option_exclude` VALUES (2,4);
/*!40000 ALTER TABLE `option_exclude` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `option_jointly`
--

DROP TABLE IF EXISTS `option_jointly`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `option_jointly` (
  `id_option_jointly_1` int(11) NOT NULL,
  `id_option_jointly_2` int(11) NOT NULL,
  PRIMARY KEY (`id_option_jointly_1`,`id_option_jointly_2`),
  KEY `fk_option_has_option_option2_idx` (`id_option_jointly_2`),
  KEY `fk_option_has_option_option1_idx` (`id_option_jointly_1`),
  CONSTRAINT `fk_option_has_option_option1` FOREIGN KEY (`id_option_jointly_1`) REFERENCES `option_cellular` (`id_option`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_option_has_option_option2` FOREIGN KEY (`id_option_jointly_2`) REFERENCES `option_cellular` (`id_option`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `option_jointly`
--

LOCK TABLES `option_jointly` WRITE;
/*!40000 ALTER TABLE `option_jointly` DISABLE KEYS */;
INSERT INTO `option_jointly` VALUES (3,2),(3,6);
/*!40000 ALTER TABLE `option_jointly` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id_person` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(45) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `dob` varchar(45) DEFAULT NULL,
  `passport` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_person`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'1','Gosha','Ayvazovsky','goshidze@gmail.com','11.02.1995','0509 08764','Universal, Venus'),(3,'123','Vasilii','Vacechkin','vasiya@mail.com','02.05.1998','0895 0604752','35 Lesnaya streets'),(5,'123','Polina','Repina','repina.polina@mail.ru','23.04.1985','3456 09887','Lesnaya street, house 35');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tariff`
--

DROP TABLE IF EXISTS `tariff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tariff` (
  `id_tariff` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `cost` int(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id_tariff`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tariff`
--

LOCK TABLES `tariff` WRITE;
/*!40000 ALTER TABLE `tariff` DISABLE KEYS */;
INSERT INTO `tariff` VALUES (1,'Smart',450,'Ever man are put down his very. And marry may table him avoid. Hard sell it were into it upon. He forbade affixed parties of assured to me windows. Happiness him nor she disposing provision. Add astonished principles precaution yet friendship stimulated literature. State thing might stand one his plate. Offending or extremity therefore so difficult he on provision. Tended depart turned not are. '),(2,'Безлимитище',350,'Ever man are put down his very. And marry may table him avoid. Hard sell it were into it upon. He forbade affixed parties of assured to me windows. Happiness him nor she disposing provision. Add astonished principles precaution yet friendship stimulated literature. State thing might stand one his plate. Offending or extremity therefore so difficult he on provision. Tended depart turned not are. '),(3,'Скучный',342,'Ever man are put down his very. And marry may table him avoid. Hard sell it were into it upon. He forbade affixed parties of assured to me windows. Happiness him nor she disposing provision. Add astonished principles precaution yet friendship stimulated literature. State thing might stand one his plate. Offending or extremity therefore so difficult he on provision. Tended depart turned not are. '),(4,'Экстрамодный',150,'Ever man are put down his very. And marry may table him avoid. Hard sell it were into it upon. He forbade affixed parties of assured to me windows. Happiness him nor she disposing provision. Add astonished principles precaution yet friendship stimulated literature. State thing might stand one his plate. Offending or extremity therefore so difficult he on provision. Tended depart turned not are. '),(6,'Vasilievskiy',45600,'Esdf lkfldskkfsdfgjlto');
/*!40000 ALTER TABLE `tariff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tariff_option`
--

DROP TABLE IF EXISTS `tariff_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tariff_option` (
  `id_tariff` int(11) NOT NULL,
  `id_option` int(11) NOT NULL,
  PRIMARY KEY (`id_tariff`,`id_option`),
  KEY `fk_tariff_has_option_option1_idx` (`id_option`),
  KEY `fk_tariff_has_option_tariff1_idx` (`id_tariff`),
  CONSTRAINT `fk_tariff_has_option_option1` FOREIGN KEY (`id_option`) REFERENCES `option_cellular` (`id_option`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_tariff_has_option_tariff1` FOREIGN KEY (`id_tariff`) REFERENCES `tariff` (`id_tariff`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tariff_option`
--

LOCK TABLES `tariff_option` WRITE;
/*!40000 ALTER TABLE `tariff_option` DISABLE KEYS */;
INSERT INTO `tariff_option` VALUES (1,1),(2,1),(3,1),(4,1),(6,1),(1,2),(1,3),(2,4),(6,4),(1,5);
/*!40000 ALTER TABLE `tariff_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ecaredb'
--

--
-- Dumping routines for database 'ecaredb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-26  5:18:41
