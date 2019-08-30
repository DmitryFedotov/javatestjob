-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: company
-- ------------------------------------------------------
-- Server version	5.7.27-log

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
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(20) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `middle_name` varchar(20) NOT NULL,
  `adress` varchar(100) NOT NULL,
  `birthday` date NOT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `position_id` int(11) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `schedule_id_idx` (`schedule_id`),
  KEY `position_id_idx` (`position_id`),
  CONSTRAINT `position_id` FOREIGN KEY (`position_id`) REFERENCES `positions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `schedule_id` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (18,'Петров','Антон','Андреевич','Ярославль, улица Чернопрудная 10 корпус 4, 181','1989-04-27','Руководитель',1,1),(20,'Волков','Петр','Андреевич','Ярославль, улица Пушкина, 13','1985-09-25','Региональный менеджер компаии',2,1),(21,'Антонов','Афанасий','Эдуардович','Ярославль, Московский проспект 127, квартира 14','1993-05-22','Стажёр на период от 1 до 2 месяцев на должность программист Java',4,4),(22,'Гаврилов','Матвей','Павлович','Ярославль, улица Нефтянников 22, 64','1999-08-13','Сотрудник специализируется на UI/UIX дизайне.',3,1),(24,'Ермаков','Денис','Владимирович','Ярославль, проезд Ушакова, 11 ,53			','2001-09-24','практикант',4,4),(25,'Алябьев','Максим','Андреевич','Ярославль, улица Гагарина, 61, 152','1979-03-17','Стабильный сотрудник',3,3),(27,'Абрамов','Дмитрий','Игоревич','Ярославль, улица Малая Пролетарская 38, 120','1995-06-27','Комментария пока нет',3,2),(28,'Смирнова','Анастасия','Игоревна','Ярославль, улица Труфанова, 28А, 50','1998-01-02','Амбициозный сотрудник',3,2),(29,'Горинов','Вячеслав','Олегович','Ярославль, улица Громова 22 , 64','1999-05-30','Главный менеджер',2,3);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-30 15:17:01
