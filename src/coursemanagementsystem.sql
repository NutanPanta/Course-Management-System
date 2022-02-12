-- MariaDB dump 10.18  Distrib 10.4.17-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: coursemanagementsystem
-- ------------------------------------------------------
-- Server version	10.4.17-MariaDB

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
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `courseId` int(11) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(150) NOT NULL,
  `courseStatus` varchar(20) NOT NULL,
  PRIMARY KEY (`courseId`),
  UNIQUE KEY `courseName` (`courseName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (3,'BIT','Open');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructormarkstable`
--

DROP TABLE IF EXISTS `instructormarkstable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instructormarkstable` (
  `marksId` int(11) NOT NULL AUTO_INCREMENT,
  `studentEmail` varchar(50) NOT NULL,
  `moduleName` varchar(150) NOT NULL,
  `instructorEmail` varchar(50) NOT NULL,
  `obtainedMarks` int(11) NOT NULL,
  `passMarks` int(11) NOT NULL,
  `fullMarks` int(11) NOT NULL,
  `grade` char(1) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`marksId`),
  UNIQUE KEY `unique_index` (`studentEmail`,`moduleName`,`instructorEmail`),
  CONSTRAINT `instructormarkstable_ibfk_1` FOREIGN KEY (`studentEmail`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructormarkstable`
--

LOCK TABLES `instructormarkstable` WRITE;
/*!40000 ALTER TABLE `instructormarkstable` DISABLE KEYS */;
INSERT INTO `instructormarkstable` VALUES (14,'test2@test.com','Academic Skills and Team Based Learning','nutanpanta@gmail.com',72,40,100,'A','Pass'),(15,'test2@test.com','Computational Mathematics','nutanpanta@gmail.com',55,40,100,'C','Pass'),(16,'shiva@gmail.com','Human Computer Interaction','nutanpanta@gmail.com',85,40,100,'A','Pass'),(17,'nutanrajpanta@gmail.com','Big Data','nutanpanta@gmail.com',95,40,100,'A','Pass'),(18,'nutanrajpanta@gmail.com','Web Technolies','nutanpanta@gmail.com',85,40,100,'A','Pass'),(19,'nutanrajpanta@gmail.com','Complex System','johnsondhakal@gmail.com',5,40,100,'F','Fail'),(20,'nutanrajpanta@gmail.com','Data Structure','johnsondhakal@gmail.com',5,40,100,'F','Fail'),(24,'nutanrajpanta@gmail.com','High Performance Computing','nirmalthing@gmail.com',79,40,100,'A','Pass'),(27,'nutanrajpanta@gmail.com','Project and Professionalism','shivagiri@gmail.com',75,40,100,'A','Pass'),(28,'nutanrajpanta@gmail.com','Mathematics','johnson@gmail.com',75,40,100,'A','Pass'),(30,'nutanrajpanta@gmail.com','Artificial Intelligence and Machine Learning','johnson@gmail.com',70,40,100,'B','Pass'),(31,'test2@test.com','Introductory Programming and Problem Solving','johnsondhakal@gmail.com',95,40,100,'A','Pass'),(32,'test2@test.com','Fundamentals of Computing','nirmalthing@gmail.com',76,40,100,'A','Pass'),(33,'test2@test.com','Embedded System Programming','shivagiri@gmail.com',65,40,100,'B','Pass'),(34,'test2@test.com','Internet Software Architecture','johnson@gmail.com',70,40,100,'B','Pass'),(35,'test2@test.com','Fundamentals Of C','nutanpanta@gmail.com',75,40,100,'A','Pass'),(37,'test2@test.com','Concurrency','johnsondhakal@gmail.com',75,40,100,'A','Pass'),(38,'aashish@gmail.com','Big Data','nutanpanta@gmail.com',99,40,100,'A','Pass');
/*!40000 ALTER TABLE `instructormarkstable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructorteachingmodules`
--

DROP TABLE IF EXISTS `instructorteachingmodules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instructorteachingmodules` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moduleName` varchar(150) NOT NULL,
  `instructorEmail` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `moduleName` (`moduleName`),
  KEY `instructorEmail` (`instructorEmail`),
  CONSTRAINT `instructorteachingmodules_ibfk_1` FOREIGN KEY (`moduleName`) REFERENCES `modules` (`moduleName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `instructorteachingmodules_ibfk_2` FOREIGN KEY (`instructorEmail`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructorteachingmodules`
--

LOCK TABLES `instructorteachingmodules` WRITE;
/*!40000 ALTER TABLE `instructorteachingmodules` DISABLE KEYS */;
INSERT INTO `instructorteachingmodules` VALUES (1,'Academic Skills and Team Based Learning','nutanpanta@gmail.com'),(4,'Introductory Programming and Problem Solving','johnsondhakal@gmail.com'),(5,'Fundamentals of Computing','nirmalthing@gmail.com'),(6,'Embedded System Programming','shivagiri@gmail.com'),(7,'Internet Software Architecture','johnson@gmail.com'),(8,'Computational Mathematics','nutanpanta@gmail.com'),(9,'Concepts and Technologies of AI','johnsondhakal@gmail.com'),(10,'Object-Oriented Design and Programming','nirmalthing@gmail.com'),(11,'Numerical Methods and Concurrency','shivagiri@gmail.com'),(12,'Distributed and Cloud System Programming','shivagiri@gmail.com'),(13,'Collaborative Development','johnson@gmail.com'),(14,'Human Computer Interaction','nutanpanta@gmail.com'),(15,'Complex System','johnsondhakal@gmail.com'),(16,'High Performance Computing','nirmalthing@gmail.com'),(17,'Project and Professionalism','shivagiri@gmail.com'),(18,'Artificial Intelligence and Machine Learning','johnson@gmail.com'),(19,'Big Data','nutanpanta@gmail.com'),(20,'Data Structure','johnsondhakal@gmail.com'),(21,'C Programming','nirmalthing@gmail.com'),(22,'Object Oriented Programming','shivagiri@gmail.com'),(23,'Mathematics','johnson@gmail.com'),(24,'Web Technolies','nutanpanta@gmail.com'),(25,'Computer Architecture','johnsondhakal@gmail.com'),(26,'Database Management','nirmalthing@gmail.com'),(32,'Fundamentals Of C','nutanpanta@gmail.com'),(35,'Concurrency','johnsondhakal@gmail.com');
/*!40000 ALTER TABLE `instructorteachingmodules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modules`
--

DROP TABLE IF EXISTS `modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modules` (
  `moduleId` int(11) NOT NULL AUTO_INCREMENT,
  `moduleName` varchar(150) NOT NULL,
  `courseName` varchar(150) NOT NULL,
  `level` varchar(20) NOT NULL,
  `moduleType` varchar(20) NOT NULL,
  `semester` varchar(20) NOT NULL,
  PRIMARY KEY (`moduleId`),
  UNIQUE KEY `moduleName` (`moduleName`),
  KEY `courseName` (`courseName`),
  CONSTRAINT `modules_ibfk_1` FOREIGN KEY (`courseName`) REFERENCES `courses` (`courseName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modules`
--

LOCK TABLES `modules` WRITE;
/*!40000 ALTER TABLE `modules` DISABLE KEYS */;
INSERT INTO `modules` VALUES (2,'Academic Skills and Team Based Learning','BIT','4','Compulsory','1'),(3,'Introductory Programming and Problem Solving','BIT','4','Compulsory','1'),(4,'Fundamentals of Computing','BIT','4','Compulsory','1'),(5,'Embedded System Programming','BIT','4','Compulsory','2'),(6,'Internet Software Architecture','BIT','4','Compulsory','2'),(7,'Computational Mathematics','BIT','4','Compulsory','2'),(8,'Concepts and Technologies of AI','BIT','5','Compulsory','1'),(9,'Object-Oriented Design and Programming','BIT','5','Compulsory','1'),(10,'Numerical Methods and Concurrency','BIT','5','Compulsory','1'),(11,'Distributed and Cloud System Programming','BIT','5','Compulsory','2'),(12,'Collaborative Development','BIT','5','Compulsory','2'),(13,'Human Computer Interaction','BIT','5','Compulsory','2'),(15,'Complex System','BIT','6','Compulsory','1'),(16,'High Performance Computing','BIT','6','Compulsory','1'),(17,'Project and Professionalism','BIT','6','Elective','1'),(18,'Artificial Intelligence and Machine Learning','BIT','6','Compulsory','2'),(19,'Big Data','BIT','6','Compulsory','2'),(20,'Data Structure','BIT','6','Elective','1'),(21,'C Programming','BIT','6','Elective','1'),(22,'Object Oriented Programming','BIT','6','Elective','1'),(23,'Mathematics','BIT','6','Elective','2'),(24,'Web Technolies','BIT','6','Elective','2'),(25,'Computer Architecture','BIT','6','Elective','2'),(26,'Database Management','BIT','6','Elective','2'),(52,'Fundamentals Of C','BIT','4','Compulsory','1'),(56,'Concurrency','BIT','4','Compulsory','2');
/*!40000 ALTER TABLE `modules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentelectivemodules`
--

DROP TABLE IF EXISTS `studentelectivemodules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentelectivemodules` (
  `studentElectiveModuleid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `moduleName` varchar(150) NOT NULL,
  PRIMARY KEY (`studentElectiveModuleid`),
  UNIQUE KEY `unique_index1` (`email`,`moduleName`),
  KEY `moduleName` (`moduleName`),
  CONSTRAINT `studentelectivemodules_ibfk_1` FOREIGN KEY (`email`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studentelectivemodules_ibfk_2` FOREIGN KEY (`moduleName`) REFERENCES `modules` (`moduleName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentelectivemodules`
--

LOCK TABLES `studentelectivemodules` WRITE;
/*!40000 ALTER TABLE `studentelectivemodules` DISABLE KEYS */;
INSERT INTO `studentelectivemodules` VALUES (30,'aashish@gmail.com','C Programming'),(31,'aashish@gmail.com','Computer Architecture'),(9,'aashish@gmail.com','Data Structure'),(32,'aashish@gmail.com','Database Management'),(4,'nutanrajpanta@gmail.com','Data Structure'),(10,'nutanrajpanta@gmail.com','Mathematics'),(1,'nutanrajpanta@gmail.com','Project and Professionalism'),(11,'nutanrajpanta@gmail.com','Web Technolies');
/*!40000 ALTER TABLE `studentelectivemodules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(25) NOT NULL,
  `lastName` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `userType` varchar(20) NOT NULL,
  `courseName` varchar(150) DEFAULT NULL,
  `level` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `email` (`email`),
  KEY `courseName` (`courseName`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`courseName`) REFERENCES `courses` (`courseName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'test','tested','test@test.com','test','Course Administrator',NULL,NULL),(2,'Nutan','Panta','nutanrajpanta@gmail.com','nutan','Student','BIT','6'),(3,'Nutan','Panta','nutanpanta@gmail.com','nutan','Instructor',NULL,NULL),(4,'test2','tested','test2@test.com','test2','Student','BIT','4'),(5,'Johnson','Dhakal','johnsondhakal@gmail.com','johnsondhakal','Instructor',NULL,NULL),(6,'Nirmal','Thing','nirmalthing@gmail.com','nirmalthing','Instructor',NULL,NULL),(7,'Shiva','Giri','shiva@gmail.com','shiva','Student','BIT','5'),(8,'Shiva','Giri','shivagiri@gmail.com','shiva','Instructor',NULL,NULL),(9,'Johnson','Dhakal','johnson@gmail.com','johnson','Instructor',NULL,NULL),(10,'Aashish','Giri','aashish@gmail.com','giri','Student','BIT','6'),(11,'a','b','a@a.com','ab','Student','BIT','4');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-16 19:33:38
