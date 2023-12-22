-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: employees
-- ------------------------------------------------------
-- Server version	8.0.34

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
  `id` varchar(30) DEFAULT NULL,
  `store` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `department_name` varchar(10) NOT NULL,
  `phonenumber` varchar(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `account` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES ('101001','영통점','백정현','점주','01047729323','BKDycrgqzu@hankok.com','448823253'),('103001','강남점','백승민','점주','01024914362','YEIHRNnyOr@hankok.com','352263136'),('102001','광교점','남정윤','점주','01005953352','Jm6b8H6uQz@hankok.com','386315620'),('104001','청주점','김동현','점주','01028344195','HLaSnvSHLp@hankok.com','653136522'),('110001','수지점','김하민','점주','01060745075','Z9buWcwi4K@hankok.com','497913814'),('101101','영통점','김민준','기획부','01005308393','rUdDTAB9JB@hankok.com','354331075'),('101115','영통점','강서윤','기획부','01021688369','JkENtdlgnC@hankok.com','405642791'),('101117','영통점','김서연','기획부','01099223413','YK4kLV5P6r@hankok.com','638424036'),('101102','영통점','박지우','기획부','01026172074','WpUIuO3zEq@hankok.com','991074931'),('101201','영통점','최주원','총무부','01032298241','ZZSIQ99UPM@hankok.com','201284911'),('101211','영통점','박주원','총무부','01021991804','AQUFXK8qrZ@hankok.com','455240880'),('101251','영통점','김준우','총무부','01001527277','tct2SAvRA8@hankok.com','999516718'),('101232','영통점','한준우','총무부','01032298241','jn1iE5ZNKg@hankok.com','830472434'),('101305','영통점','고도현','인사부','01032298241','kim00@hankok.com','647459602'),('101301','영통점','김도현','인사부','01021923154','kim99@hankok.com','434670570'),('101307','영통점','정예은','인사부','01038275036','kim99@hankok.com','939491335'),('101321','영통점','이예은','인사부','01066364681','kim99@hankok.com','582477968'),('101421','영통점','임지유','영업부','01033470493','TIg2VvTvvI@hankok.com','746065944'),('101431','영통점','하예솔','영업부','01021544854','kim99@hankok.com','649365634'),('103101','강남점','남은수','기획부','01036736268',NULL,'316614062'),('103121','강남점','박수빈','기획부','01060422545','KdkEc5DbKL@hankok.com','899108735'),('103201','강남점','박서진','총무부','01082835636','jSeH3cUal2@hankok.com','379974902'),('103241','강남점','한서진','총무부','01060422545','IqAL7Z96MC@hankok.com','589959261'),('103321','강남점','한소율','인사부','01007216170','8QAhYjGAjv@hankok.com','74929222'),('103301','강남점','서지아','인사부','01058191978','NT3xpk5WQ8@hankok.com','418768177'),('103421','강남점','한지민','영업부','01080939476','i6jbRTmdL2@hankok.com','901681713'),('103431','강남점','이지민','영업부','01045352502','5l03XpfTDC@hankok.com','961632428'),('102101','광교점','김강민','기획부','01059304035','gCpTqWOYKY@hankok.com','568274905'),('102201','광교점','김광현','총무부','01018200232','wFhnfl3GiD@hankok.com','559731734'),('102401','광교점','박동원','영업부','01072675805','7mUq5p2A2T@hankok.com','360683841'),('102301','광교점','김민식','인사부','01041591728','EVjzLUPsw8@hankok.com','576571586'),('104101','청주점','오지환','기획부','01059744691','JX37qNFVNs@hankok.com','94280090'),('104201','청주점','한유섬','총무부','01081232278','dAKU3M903A@hankok.com','113999343'),('104301','청주점','고효준','인사부','01080939476','QW4T7y5gFw@hankok.com','200947899'),('104401','청주점','고우석','영업부','01087168613','9GqD9KUL5w@hankok.com','72082021'),('110101','수지점','황재균','기획부','01000378525','SlH5KQ9wGV@hankok.com','527587753'),('110201','수지점','안치홍','총무부','01049434441','D6cpyhvSp3@hankok.com','289511298');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `id` varchar(100) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES ('','',''),('kastro','백정현','3259'),('root','관리자','1234'),('yoon','윤남균','1234'),('yooon','윤가연','1234'),('yun','윤남경','12345');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-22 14:50:33
