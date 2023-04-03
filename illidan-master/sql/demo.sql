-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 20, 2020 at 05:31 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.1.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `demo`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_order`
--

CREATE TABLE `tbl_order` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `url_app` varchar(999) COLLATE utf8_unicode_ci NOT NULL,
  `key_word` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` double DEFAULT NULL,
  `created_date` date NOT NULL,
  `status` varchar(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_order`
--

INSERT INTO `tbl_order` (`id`, `email`, `url_app`, `key_word`, `price`, `created_date`, `status`) VALUES
(1, 'demo@gmail.com', 'app_url01', 'app_a, app_b', 9, '2020-02-13', 'true'),
(2, 'example@gmail.com', 'url_abc', 'rating anc', 9.9, '2020-02-13', 'true'),
(3, 'acb01@gmail.com', 'http://ancbj', 'key 01', 90, '2020-02-13', 'true');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_product`
--

CREATE TABLE `tbl_product` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `short_desc` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `support` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_date` date NOT NULL,
  `price` double DEFAULT NULL,
  `delivery` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `kind_of_service` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_product`
--

INSERT INTO `tbl_product` (`id`, `name`, `type_name`, `short_desc`, `support`, `created_date`, `price`, `delivery`, `kind_of_service`) VALUES
(7, '10 Reviews', 'BASIC', 'Up to 5 Custom Keywords', '24 / 7 Support', '2020-02-18', 20, '2 - 3', 1),
(8, '50 Reviews', 'PREMIUM', 'Up to 5 Custom Keywords', '24 / 7 Support', '2020-02-20', 70, '3 - 5', 1),
(9, '100 Reviews', 'ADVANCED', 'Up to 5 Custom Keywords', '24 / 7 Support', '2020-02-20', 115, '5 - 10', 1),
(10, '10 Five Star Ratings', 'BASIC', 'Up to 5 Custom Keywords', '24 / 7 Support', '2020-02-19', 20, '2 - 3', 2),
(11, '50 Five Star Ratings', 'PREMIUM', 'Up to 5 Custom Keywords', '24 / 7 Support', '2020-02-20', 65, '3 - 5', 2),
(12, '100 Five Star Ratings', 'ADVANCED', 'Up to 5 Custom Keywords', '24 / 7 Support', '2020-02-20', 100, '5 - 10', 2),
(14, '500 Reviews', 'SUPER', 'Up to 5 Custom Keywords', '24 / 7 Support', '2020-02-20', 500, '20 - 25', 1),
(15, '1000 Five Star Ratings', 'SPECIAL', 'Up to 5 Custom Keywords', '24 / 7 Support', '2020-02-20', 850, '20 - 25', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_order`
--
ALTER TABLE `tbl_order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_product`
--
ALTER TABLE `tbl_product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_order`
--
ALTER TABLE `tbl_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_product`
--
ALTER TABLE `tbl_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
