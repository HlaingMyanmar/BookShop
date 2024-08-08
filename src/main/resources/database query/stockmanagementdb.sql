-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 08, 2024 at 06:38 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookshop_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `aid` varchar(15) NOT NULL,
  `aname` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`aid`, `aname`) VALUES
('#au1', 'ချမ်းမင်းအောင် (AIT Computer)'),
('#au2', 'ကိုဝေဖြိုးအောင် (AIT Computer)'),
('#au3', 'ပီမိုးနင်း');

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `bcode` varchar(15) NOT NULL,
  `name` varchar(300) NOT NULL,
  `qty` int(20) DEFAULT NULL,
  `price` int(20) NOT NULL,
  `cid` varchar(15) NOT NULL,
  `aid` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`bcode`, `name`, `qty`, `price`, `cid`, `aid`) VALUES
('#S-20240808-1', 'JAVA SE Programming Part -1 ', 3, 23000, '#ca5', '#au2'),
('#S-20240808-2', 'JAVA SE Programming Part -2 ', 4, 22650, '#ca5', '#au2'),
('#S-20240808-3', 'JAVA SE Programming Part -3 ', 4, 8900, '#ca5', '#au2');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `cid` varchar(15) NOT NULL,
  `cname` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`cid`, `cname`) VALUES
('#ca1', 'Brain Food'),
('#ca2', 'တက်ကျမ်း'),
('#ca3', 'Grade -11'),
('#ca4', 'Language'),
('#ca5', 'Computer Training DVD');

-- --------------------------------------------------------

--
-- Table structure for table `cuorder`
--

CREATE TABLE `cuorder` (
  `orid` varchar(15) NOT NULL,
  `ordate` date NOT NULL,
  `cuname` varchar(20) DEFAULT NULL,
  `cuphone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cuorder`
--

INSERT INTO `cuorder` (`orid`, `ordate`, `cuname`, `cuphone`) VALUES
('#Or1', '2024-06-20', '', ''),
('#Or2', '2024-06-20', '', ''),
('#Or3', '2024-06-20', 'Hsu Wai Hin', '09252425319'),
('#Or4', '2024-06-22', 'HlaingHtun', '09943342858'),
('#Or5', '2024-06-22', '', ''),
('#Or6', '2024-07-19', 'Thu Thu Zin', '098797979');

-- --------------------------------------------------------

--
-- Table structure for table `netprofit`
--

CREATE TABLE `netprofit` (
  `puid` varchar(15) NOT NULL,
  `pudate` date NOT NULL,
  `bcode` varchar(15) DEFAULT NULL,
  `currency` varchar(10) DEFAULT NULL,
  `currency_amount` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `transportation` int(11) DEFAULT NULL,
  `expense` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `precentage` double DEFAULT NULL,
  `netprofit` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `netprofit`
--

INSERT INTO `netprofit` (`puid`, `pudate`, `bcode`, `currency`, `currency_amount`, `amount`, `transportation`, `expense`, `qty`, `precentage`, `netprofit`) VALUES
('#P-20240808-2', '2024-08-08', '#S-20240808-3', 'Dollor ($)', 1.5, 4500, 2000, 0, 3, 20, 8900);

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

CREATE TABLE `purchase` (
  `puid` varchar(15) NOT NULL,
  `pudate` date NOT NULL,
  `bcode` varchar(15) NOT NULL,
  `bcategory` varchar(150) DEFAULT NULL,
  `bauthor` varchar(15) DEFAULT NULL,
  `sid` varchar(15) NOT NULL,
  `qty` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `remark` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `purchase`
--

INSERT INTO `purchase` (`puid`, `pudate`, `bcode`, `bcategory`, `bauthor`, `sid`, `qty`, `price`, `remark`) VALUES
('#P-20240808-1', '2024-08-08', '#S-20240808-1', '#ca5', '#au2', '#su2', 3, 23000, 'test'),
('#P-20240808-1', '2024-08-08', '#S-20240808-2', '#ca5', '#au2', '#su2', 4, 22650, 'test'),
('#P-20240808-2', '2024-08-08', '#S-20240808-3', '#ca5', '#au2', '#su2', 4, 8900, 'tss');

-- --------------------------------------------------------

--
-- Table structure for table `purchasereturn`
--

CREATE TABLE `purchasereturn` (
  `rdate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `puid` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `purchasereturndetails`
--

CREATE TABLE `purchasereturndetails` (
  `rdate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `bcode` varchar(15) NOT NULL,
  `qty` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `returnReason` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sale`
--

CREATE TABLE `sale` (
  `orid` varchar(15) NOT NULL,
  `bcode` varchar(15) NOT NULL,
  `cid` varchar(15) NOT NULL,
  `aid` varchar(15) NOT NULL,
  `qty` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sale_return`
--

CREATE TABLE `sale_return` (
  `return_id` varchar(15) NOT NULL,
  `orid` varchar(15) NOT NULL,
  `bcode` varchar(15) NOT NULL,
  `return_date` date NOT NULL,
  `qty_returned` int(11) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `suid` varchar(15) NOT NULL,
  `suname` varchar(45) DEFAULT NULL,
  `suphone` varchar(100) DEFAULT NULL,
  `suaddress` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`suid`, `suname`, `suphone`, `suaddress`) VALUES
('#su1', 'ပန်းရွေပြည်စာအုပ်တိုက်', '09296285', 'အမှတ်-၇၅၊၄၃လမ်း ၊ ရန်ကုန်မြို့'),
('#su2', 'AIT Company', '09', '090');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`aid`);

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`bcode`),
  ADD KEY `cid` (`cid`),
  ADD KEY `aid` (`aid`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`cid`);

--
-- Indexes for table `cuorder`
--
ALTER TABLE `cuorder`
  ADD PRIMARY KEY (`orid`);

--
-- Indexes for table `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`puid`,`bcode`,`sid`),
  ADD KEY `bookcode` (`bcode`),
  ADD KEY `supplierid` (`sid`);

--
-- Indexes for table `purchasereturn`
--
ALTER TABLE `purchasereturn`
  ADD PRIMARY KEY (`rdate`),
  ADD KEY `puid` (`puid`);

--
-- Indexes for table `purchasereturndetails`
--
ALTER TABLE `purchasereturndetails`
  ADD PRIMARY KEY (`rdate`,`bcode`),
  ADD KEY `bcode` (`bcode`);

--
-- Indexes for table `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`orid`,`bcode`,`cid`,`aid`),
  ADD KEY `bcode` (`bcode`),
  ADD KEY `cid` (`cid`),
  ADD KEY `aid` (`aid`);

--
-- Indexes for table `sale_return`
--
ALTER TABLE `sale_return`
  ADD PRIMARY KEY (`return_id`),
  ADD KEY `orid` (`orid`),
  ADD KEY `bcode` (`bcode`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`suid`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`),
  ADD CONSTRAINT `book_ibfk_2` FOREIGN KEY (`aid`) REFERENCES `author` (`aid`);

--
-- Constraints for table `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`bcode`) REFERENCES `book` (`bcode`),
  ADD CONSTRAINT `purchase_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `supplier` (`suid`);

--
-- Constraints for table `purchasereturn`
--
ALTER TABLE `purchasereturn`
  ADD CONSTRAINT `purchasereturn_ibfk_1` FOREIGN KEY (`puid`) REFERENCES `purchase` (`puid`);

--
-- Constraints for table `purchasereturndetails`
--
ALTER TABLE `purchasereturndetails`
  ADD CONSTRAINT `purchasereturndetails_ibfk_1` FOREIGN KEY (`bcode`) REFERENCES `book` (`bcode`),
  ADD CONSTRAINT `purchasereturndetails_ibfk_2` FOREIGN KEY (`rdate`) REFERENCES `purchasereturn` (`rdate`);

--
-- Constraints for table `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `sale_ibfk_1` FOREIGN KEY (`bcode`) REFERENCES `book` (`bcode`),
  ADD CONSTRAINT `sale_ibfk_2` FOREIGN KEY (`orid`) REFERENCES `cuorder` (`orid`),
  ADD CONSTRAINT `sale_ibfk_3` FOREIGN KEY (`cid`) REFERENCES `book` (`cid`),
  ADD CONSTRAINT `sale_ibfk_4` FOREIGN KEY (`aid`) REFERENCES `book` (`aid`);

--
-- Constraints for table `sale_return`
--
ALTER TABLE `sale_return`
  ADD CONSTRAINT `sale_return_ibfk_1` FOREIGN KEY (`orid`) REFERENCES `cuorder` (`orid`),
  ADD CONSTRAINT `sale_return_ibfk_2` FOREIGN KEY (`bcode`) REFERENCES `sale` (`bcode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
