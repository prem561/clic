-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 06, 2016 at 12:55 PM
-- Server version: 5.6.21
-- PHP Version: 5.5.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `clic`
--

-- --------------------------------------------------------

--
-- Table structure for table `address_type`
--

CREATE TABLE IF NOT EXISTS `address_type` (
`address_type_id` int(11) NOT NULL,
  `Address_Type` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address_type`
--

INSERT INTO `address_type` (`address_type_id`, `Address_Type`) VALUES
(1, 'Billing Address'),
(2, 'Res Address'),
(3, 'Item Address');

-- --------------------------------------------------------

--
-- Table structure for table `brands_m`
--

CREATE TABLE IF NOT EXISTS `brands_m` (
`brand_id` int(20) NOT NULL,
  `brand_type_id` int(11) NOT NULL,
  `brand_name` varchar(30) DEFAULT NULL,
  `brand_logo` varchar(30) DEFAULT NULL,
  `brand_description` varchar(4000) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `brands_m`
--

INSERT INTO `brands_m` (`brand_id`, `brand_type_id`, `brand_name`, `brand_logo`, `brand_description`, `status`) VALUES
(1, 1, 'LG', '/images/lg.png', 'Life''s Good', 0),
(2, 1, 'saumsung', 'logo', 'desc', 0);

-- --------------------------------------------------------

--
-- Table structure for table `brand_admin`
--

CREATE TABLE IF NOT EXISTS `brand_admin` (
`id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `brand_admin`
--

INSERT INTO `brand_admin` (`id`, `name`, `password`, `brand_id`, `created_date`, `status`) VALUES
(1, 'lgadmin', '12345', 1, '2016-07-17 06:14:40', 1);

-- --------------------------------------------------------

--
-- Table structure for table `brand_type_m`
--

CREATE TABLE IF NOT EXISTS `brand_type_m` (
`typeid` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `brand_type_m`
--

INSERT INTO `brand_type_m` (`typeid`, `name`, `status`) VALUES
(1, 'Electroics', 1),
(2, 'Auto Mobiles', 1);

-- --------------------------------------------------------

--
-- Table structure for table `brand_users_t`
--

CREATE TABLE IF NOT EXISTS `brand_users_t` (
`id` int(11) NOT NULL,
  `usertype` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `retail_id` int(11) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `brand_users_t`
--

INSERT INTO `brand_users_t` (`id`, `usertype`, `name`, `password`, `retail_id`, `brand_id`, `created_date`, `status`) VALUES
(8, 1, 'admin', 'admin', 1, 1, '2016-07-31 06:21:37', 1),
(9, 1, 'prem', '1234', 1, 1, '2016-07-31 06:23:46', 1),
(10, 1, 'admin', 'admin', 1, 1, '2016-07-31 06:24:03', 1),
(11, 1, 'prem', 'kumar', 1, 1, '2016-07-31 06:24:53', 1),
(12, 1, 'admin', 'admin', 1, 1, '2016-07-31 06:26:09', 1),
(13, 1, 'admin', 'admin', 1, 1, '2016-07-31 06:27:27', 1),
(14, 1, 'admin', 'admin', 1, 1, '2016-07-31 06:28:03', 1),
(15, 1, 'admin', 'admin', 1, 1, '2016-07-31 06:33:10', 1);

-- --------------------------------------------------------

--
-- Table structure for table `callback`
--

CREATE TABLE IF NOT EXISTS `callback` (
`callback_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `customer_item_id` int(11) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(40) NOT NULL DEFAULT 'new'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `callback`
--

INSERT INTO `callback` (`callback_id`, `customer_id`, `customer_item_id`, `created_time`, `status`) VALUES
(1, 1, 0, '2016-07-16 17:06:11', 'new');

-- --------------------------------------------------------

--
-- Table structure for table `category_m`
--

CREATE TABLE IF NOT EXISTS `category_m` (
`category_id` int(20) NOT NULL,
  `category_name` varchar(30) DEFAULT NULL,
  `brand_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `description` varchar(4000) NOT NULL,
  `image_url` varchar(50) DEFAULT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category_m`
--

INSERT INTO `category_m` (`category_id`, `category_name`, `brand_id`, `product_id`, `description`, `image_url`, `status`) VALUES
(1, 'refrigerator', 1, 1, '', '', '1'),
(2, 'Ac', 1, 1, 'ac', '', '1');

-- --------------------------------------------------------

--
-- Table structure for table `content_business_type_m`
--

CREATE TABLE IF NOT EXISTS `content_business_type_m` (
`type_id` int(11) NOT NULL,
  `type` varchar(1000) NOT NULL,
  `description` varchar(1000) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `content_business_type_m`
--

INSERT INTO `content_business_type_m` (`type_id`, `type`, `description`) VALUES
(1, 'faq', 'faq');

-- --------------------------------------------------------

--
-- Table structure for table `content_data_m`
--

CREATE TABLE IF NOT EXISTS `content_data_m` (
`content_dataid` int(11) NOT NULL,
  `text` varchar(1000) NOT NULL,
  `item_id` int(11) NOT NULL,
  `content_businesstype_id` int(11) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `path` varchar(4000) NOT NULL,
  `meta_key_words` varchar(4000) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `content_data_m`
--

INSERT INTO `content_data_m` (`content_dataid`, `text`, `item_id`, `content_businesstype_id`, `content_type_id`, `path`, `meta_key_words`, `status`) VALUES
(1, 'Lg 32 inches smart tv 32lb5820', 1, 1, 1, 'local', 'Lg 32 inches smart tv 32lb5820', 1);

-- --------------------------------------------------------

--
-- Table structure for table `content_m`
--

CREATE TABLE IF NOT EXISTS `content_m` (
  `product_id` int(20) NOT NULL,
  `video_URL` varchar(100) DEFAULT NULL,
  `manual_book_URL` varchar(100) DEFAULT NULL,
  `faq_URL` varchar(100) DEFAULT NULL,
  `sub_category_id` int(20) DEFAULT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `content_type_m`
--

CREATE TABLE IF NOT EXISTS `content_type_m` (
`type_id` int(11) NOT NULL,
  `type` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `content_type_m`
--

INSERT INTO `content_type_m` (`type_id`, `type`) VALUES
(1, 'image'),
(2, 'video');

-- --------------------------------------------------------

--
-- Table structure for table `customer_address_map_t`
--

CREATE TABLE IF NOT EXISTS `customer_address_map_t` (
`id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `address_id` int(11) NOT NULL,
  `address_type_id` int(11) NOT NULL,
  `customer_item_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_address_map_t`
--

INSERT INTO `customer_address_map_t` (`id`, `customer_id`, `address_id`, `address_type_id`, `customer_item_id`) VALUES
(1, 1, 3, 3, 38),
(2, 1, 4, 3, 39);

-- --------------------------------------------------------

--
-- Table structure for table `customer_address_t`
--

CREATE TABLE IF NOT EXISTS `customer_address_t` (
`customer_address_id` int(20) NOT NULL,
  `line1` varchar(100) DEFAULT NULL,
  `line2` varchar(200) DEFAULT NULL,
  `address_city` varchar(20) DEFAULT NULL,
  `address_district` varchar(20) DEFAULT NULL,
  `address_state` varchar(20) DEFAULT NULL,
  `address_country` varchar(20) DEFAULT NULL,
  `address_pincode` int(10) DEFAULT NULL,
  `lat` varchar(100) DEFAULT NULL,
  `lang` varchar(120) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_address_t`
--

INSERT INTO `customer_address_t` (`customer_address_id`, `line1`, `line2`, `address_city`, `address_district`, `address_state`, `address_country`, `address_pincode`, `lat`, `lang`, `status`) VALUES
(1, 'line1', 'line2', 'city', 'd', 's', 'india', NULL, NULL, '45', 1),
(2, 'line1', 'line2', 'city', 'd', 's', 'india', NULL, NULL, '45', 1),
(3, 'line1', 'line2', 'city', 'd', 's', 'india', NULL, NULL, '45', 1),
(4, 'line1', 'line2', 'city', 'd', 's', 'india', NULL, NULL, '45', 1);

-- --------------------------------------------------------

--
-- Table structure for table `customer_device_details_t`
--

CREATE TABLE IF NOT EXISTS `customer_device_details_t` (
`id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `device_model` varchar(100) NOT NULL,
  `device_id` int(11) NOT NULL,
  `version` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer_feedback`
--

CREATE TABLE IF NOT EXISTS `customer_feedback` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `service_request_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL DEFAULT '0',
  `comment` varchar(100) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer_item_data_t`
--

CREATE TABLE IF NOT EXISTS `customer_item_data_t` (
`item_id` int(20) NOT NULL,
  `brand_id` int(20) DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  `category_id` int(20) DEFAULT NULL,
  `subcategory_id` int(20) DEFAULT NULL,
  `master_item_id` int(11) NOT NULL,
  `year_of_purchase` text,
  `Model_Number` varchar(50) NOT NULL,
  `serial_number` int(11) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `warrenty` varchar(30) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `createdtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_item_data_t`
--

INSERT INTO `customer_item_data_t` (`item_id`, `brand_id`, `product_id`, `category_id`, `subcategory_id`, `master_item_id`, `year_of_purchase`, `Model_Number`, `serial_number`, `customer_id`, `warrenty`, `status`, `createdtime`) VALUES
(38, 1, 1, 1, NULL, 1, '2015', 'LG 42 LB 5821', NULL, 1, '6', 1, '2016-06-25 17:15:05'),
(39, 1, 1, 1, NULL, 2, '2015', 'LG 42 LB 5821', NULL, 1, '6', 1, '2016-06-25 19:08:24');

-- --------------------------------------------------------

--
-- Table structure for table `customer_item_documents_t`
--

CREATE TABLE IF NOT EXISTS `customer_item_documents_t` (
`id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `doc_type_id` int(11) NOT NULL,
  `file_path` varchar(1000) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_item_documents_t`
--

INSERT INTO `customer_item_documents_t` (`id`, `item_id`, `doc_type_id`, `file_path`, `status`) VALUES
(1, 12, 1, '112101466840451665', 1),
(2, 13, 1, '113101466840779081.png', 1),
(3, 22, 1, '122101466854119615.png', 1),
(4, 29, 1, '129101466854387972.png', 1),
(5, 31, 1, '131101466854506171.png', 1),
(6, 32, 1, '132101466854679279.png', 1),
(7, 38, 1, '138101466855105460.png', 1),
(8, 39, 1, '139101466861905346.png', 1);

-- --------------------------------------------------------

--
-- Table structure for table `customer_relation_t`
--

CREATE TABLE IF NOT EXISTS `customer_relation_t` (
`customer_relation_id` int(11) NOT NULL,
  `master_customer_id` int(11) NOT NULL,
  `relation_customer_id` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer_shared_profiles_t`
--

CREATE TABLE IF NOT EXISTS `customer_shared_profiles_t` (
`id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `sub_customer_id` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_shared_profiles_t`
--

INSERT INTO `customer_shared_profiles_t` (`id`, `customer_id`, `sub_customer_id`, `status`, `created_date`) VALUES
(1, 1, 2, 0, '2016-08-06 09:20:04'),
(2, 1, 13, 1, '2016-08-06 10:50:15'),
(3, 1, 14, 1, '2016-08-06 10:51:19');

-- --------------------------------------------------------

--
-- Table structure for table `customer_t`
--

CREATE TABLE IF NOT EXISTS `customer_t` (
`customer_id` int(20) NOT NULL,
  `customer_name` varchar(20) DEFAULT NULL,
  `customer_lastname` varchar(120) DEFAULT NULL,
  `customer_gender` set('male','female') DEFAULT NULL,
  `customer_mob` varchar(20) DEFAULT NULL,
  `customer_email` varchar(30) DEFAULT NULL,
  `customer_password` varchar(20) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_t`
--

INSERT INTO `customer_t` (`customer_id`, `customer_name`, `customer_lastname`, `customer_gender`, `customer_mob`, `customer_email`, `customer_password`, `status`, `created_time`) VALUES
(1, 'prem', '', 'male', '9035', NULL, NULL, 1, '0000-00-00 00:00:00'),
(2, 'kumar', '', NULL, '9035232140', 'prem@gmail.com', '123456', 1, '0000-00-00 00:00:00'),
(3, 'kumar', '', NULL, '9035232140', 'prem@gmail.com', '123456', 1, '0000-00-00 00:00:00'),
(4, 'kumar', '', NULL, '9035232140', 'prem@gmail.com', '123456', 1, '0000-00-00 00:00:00'),
(5, 'kumar', '', NULL, '9035232141', 'prem@gmail.com', '123456', 1, '0000-00-00 00:00:00'),
(6, 'kumar', '', NULL, '9035232141', 'prem@gmail.com', '123456', 1, '0000-00-00 00:00:00'),
(7, NULL, '', NULL, NULL, NULL, '123456', 1, '2016-06-16 16:14:41'),
(8, NULL, '', NULL, NULL, NULL, '1234567', 1, '2016-06-16 16:15:26'),
(9, 'kumar', NULL, NULL, '9035232141', 'prem@gmail.com', '123456', 1, '2016-06-19 16:03:16'),
(10, 'kumar', NULL, NULL, '9035232141', 'prem@gmail.com', '123456', 1, '2016-06-19 16:09:17'),
(11, 'kumar', NULL, NULL, '9035232141', 'prem@gmail.com', '123456', 1, '2016-06-19 16:10:22'),
(12, 'prems', NULL, NULL, NULL, NULL, NULL, 1, '2016-08-06 16:16:04'),
(13, 'prems', NULL, NULL, '8884678834', NULL, '8884678834', 1, '2016-08-06 16:18:41'),
(14, 'premsp', NULL, NULL, '8884678831', NULL, '8884678831', 1, '2016-08-06 16:21:19');

-- --------------------------------------------------------

--
-- Table structure for table `docs_type_m`
--

CREATE TABLE IF NOT EXISTS `docs_type_m` (
`type_id` int(11) NOT NULL,
  `docttype` varchar(40) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `docs_type_m`
--

INSERT INTO `docs_type_m` (`type_id`, `docttype`, `description`, `status`) VALUES
(1, 'warrenty_copy', '', ''),
(2, 'invoice_copy', '', ''),
(3, 'user_mannual', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `master_item_m`
--

CREATE TABLE IF NOT EXISTS `master_item_m` (
`item_id` int(20) NOT NULL,
  `brand_id` int(20) DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  `category_id` int(20) DEFAULT NULL,
  `subcategory_id` int(20) DEFAULT NULL,
  `year_of_release` datetime DEFAULT NULL,
  `Model_Number` varchar(50) NOT NULL,
  `Specification` varchar(1000) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `image_url` varchar(100) NOT NULL,
  `Default_Warranty_in_Months` int(5) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_item_m`
--

INSERT INTO `master_item_m` (`item_id`, `brand_id`, `product_id`, `category_id`, `subcategory_id`, `year_of_release`, `Model_Number`, `Specification`, `description`, `image_url`, `Default_Warranty_in_Months`, `status`) VALUES
(1, 1, 1, 1, 1, '2016-06-01 00:00:00', 'LG 32 LB 5820', '', '', '', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `otp_t`
--

CREATE TABLE IF NOT EXISTS `otp_t` (
`otp_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `otp` varchar(10) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `otp_t`
--

INSERT INTO `otp_t` (`otp_id`, `customer_id`, `otp`, `time`, `status`) VALUES
(2, 2147483647, '3343', '2016-06-05 16:10:18', 0);

-- --------------------------------------------------------

--
-- Table structure for table `products_m`
--

CREATE TABLE IF NOT EXISTS `products_m` (
`product_id` int(20) NOT NULL,
  `product_name` varchar(30) DEFAULT NULL,
  `brand_id` int(20) DEFAULT NULL,
  `description` varchar(4000) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products_m`
--

INSERT INTO `products_m` (`product_id`, `product_name`, `brand_id`, `description`, `status`) VALUES
(1, 'Home Apllicances', 1, 'Home Apllicances ', '');

-- --------------------------------------------------------

--
-- Table structure for table `repair_estimation_m`
--

CREATE TABLE IF NOT EXISTS `repair_estimation_m` (
`id` int(11) NOT NULL,
  `repair_id` int(11) NOT NULL,
  `warrenty_status` tinyint(1) NOT NULL,
  `labour_charge` int(11) NOT NULL,
  `extra_parts` int(11) NOT NULL,
  `Estimated_time` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `repair_types_m`
--

CREATE TABLE IF NOT EXISTS `repair_types_m` (
`type_id` int(11) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `sub_category_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `repair_code` varchar(10) NOT NULL,
  `description` varchar(40) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `repair_types_m`
--

INSERT INTO `repair_types_m` (`type_id`, `brand_id`, `product_id`, `category_id`, `sub_category_id`, `item_id`, `repair_code`, `description`, `status`) VALUES
(1, 1, 1, 1, 1, 1, 'LG00', 'video problem', 1),
(2, 1, 1, 1, 1, 1, 'LG01', 'Audio Problem', 1);

-- --------------------------------------------------------

--
-- Table structure for table `request_types_m`
--

CREATE TABLE IF NOT EXISTS `request_types_m` (
`typeid` int(11) NOT NULL,
  `request_type` varchar(400) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `request_types_m`
--

INSERT INTO `request_types_m` (`typeid`, `request_type`, `status`) VALUES
(1, 'Demo', 1),
(2, 'Installation', 1),
(3, 'Repair', 1),
(4, 'Warranty Visit', 1);

-- --------------------------------------------------------

--
-- Table structure for table `retailer_m`
--

CREATE TABLE IF NOT EXISTS `retailer_m` (
`id` int(11) NOT NULL,
  `store` varchar(110) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `retailer_m`
--

INSERT INTO `retailer_m` (`id`, `store`, `brand_id`, `status`) VALUES
(1, 'Chroma', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `service_request_t`
--

CREATE TABLE IF NOT EXISTS `service_request_t` (
`request_id` int(11) NOT NULL,
  `type_of_request` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `customer_item_id` int(11) NOT NULL,
  `repair_id` int(11) DEFAULT NULL,
  `description` varchar(2000) NOT NULL,
  `scheduled_time` date NOT NULL,
  `lat` varchar(100) NOT NULL,
  `lang` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `image_path` varchar(100) NOT NULL,
  `token_number` varchar(100) NOT NULL,
  `received_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `ticket_status` varchar(50) NOT NULL DEFAULT 'pending'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service_request_t`
--

INSERT INTO `service_request_t` (`request_id`, `type_of_request`, `customer_id`, `customer_item_id`, `repair_id`, `description`, `scheduled_time`, `lat`, `lang`, `address`, `image_path`, `token_number`, `received_time`, `status`, `ticket_status`) VALUES
(1, 2, 1, 38, 0, 'video problem', '2016-06-12', '23.6786', '45.898089', '12334', 'image path', '12334', '2016-07-29 00:00:00', 1, 'Completed'),
(2, 1, 1, 38, 0, 'audio problem', '2016-06-12', '23.6786', '45.898089', '12334', 'image path', '12334', '2016-07-21 00:00:00', 1, 'In Process'),
(3, 2, 1, 38, NULL, 'desc', '2016-06-12', '23.6786', '45.898089', 'address', 'no file', '11466929859359', '2016-06-26 14:00:59', 1, 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `service_request_update_t`
--

CREATE TABLE IF NOT EXISTS `service_request_update_t` (
`id` int(11) NOT NULL,
  `service_request_id` int(11) NOT NULL,
  `Comment` varchar(2000) NOT NULL,
  `Updated_On` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Updated_By` varchar(100) DEFAULT NULL,
  `token_number` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service_request_update_t`
--

INSERT INTO `service_request_update_t` (`id`, `service_request_id`, `Comment`, `Updated_On`, `Updated_By`, `token_number`) VALUES
(1, 1, 'thanks', '2016-08-06 13:48:47', 'admin', NULL),
(2, 1, 'Thanks for your support , your query resolved', '2016-08-06 14:04:47', 'admin', NULL),
(3, 2, 'we are processing your request', '2016-08-06 14:07:48', 'admin', NULL),
(4, 2, 'We are processing your request , thanks', '2016-08-06 14:08:36', 'admin', NULL),
(5, 2, 'We are processing your request , thanks :)', '2016-08-06 14:09:36', 'admin', NULL),
(6, 1, 'thanks', '2016-08-06 14:15:45', 'admin', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `subcategory_m`
--

CREATE TABLE IF NOT EXISTS `subcategory_m` (
`subcategory_id` int(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(500) NOT NULL,
  `category_id` int(20) DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subcategory_m`
--

INSERT INTO `subcategory_m` (`subcategory_id`, `name`, `description`, `category_id`, `product_id`, `brand_id`, `status`) VALUES
(1, 'single door refrigirator', '', 1, 1, 1, '');

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE IF NOT EXISTS `ticket` (
`ticket_id` int(20) NOT NULL,
  `customer_id` int(20) DEFAULT NULL,
  `customer_item_id` int(20) DEFAULT NULL,
  `raised_by` varchar(20) DEFAULT NULL,
  `query` varchar(100) DEFAULT NULL,
  `status` set('processing','resolved') DEFAULT NULL,
  `received_time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ticket_status`
--

CREATE TABLE IF NOT EXISTS `ticket_status` (
  `ticket_id` int(20) DEFAULT NULL,
  `chat_file` blob,
  `feedback` text,
  `updated_time` time DEFAULT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_type_m`
--

CREATE TABLE IF NOT EXISTS `user_type_m` (
`id` int(11) NOT NULL,
  `typeofuser` varchar(100) NOT NULL,
  `brand_id` smallint(6) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_type_m`
--

INSERT INTO `user_type_m` (`id`, `typeofuser`, `brand_id`, `status`) VALUES
(1, 'brand_promotor', 0, 1),
(2, 'chat_admin', 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address_type`
--
ALTER TABLE `address_type`
 ADD PRIMARY KEY (`address_type_id`);

--
-- Indexes for table `brands_m`
--
ALTER TABLE `brands_m`
 ADD PRIMARY KEY (`brand_id`);

--
-- Indexes for table `brand_admin`
--
ALTER TABLE `brand_admin`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `brand_type_m`
--
ALTER TABLE `brand_type_m`
 ADD PRIMARY KEY (`typeid`);

--
-- Indexes for table `brand_users_t`
--
ALTER TABLE `brand_users_t`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `callback`
--
ALTER TABLE `callback`
 ADD PRIMARY KEY (`callback_id`);

--
-- Indexes for table `category_m`
--
ALTER TABLE `category_m`
 ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `content_business_type_m`
--
ALTER TABLE `content_business_type_m`
 ADD PRIMARY KEY (`type_id`);

--
-- Indexes for table `content_data_m`
--
ALTER TABLE `content_data_m`
 ADD PRIMARY KEY (`content_dataid`);

--
-- Indexes for table `content_m`
--
ALTER TABLE `content_m`
 ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `content_type_m`
--
ALTER TABLE `content_type_m`
 ADD PRIMARY KEY (`type_id`);

--
-- Indexes for table `customer_address_map_t`
--
ALTER TABLE `customer_address_map_t`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_address_t`
--
ALTER TABLE `customer_address_t`
 ADD PRIMARY KEY (`customer_address_id`);

--
-- Indexes for table `customer_device_details_t`
--
ALTER TABLE `customer_device_details_t`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_item_data_t`
--
ALTER TABLE `customer_item_data_t`
 ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `customer_item_documents_t`
--
ALTER TABLE `customer_item_documents_t`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_relation_t`
--
ALTER TABLE `customer_relation_t`
 ADD PRIMARY KEY (`customer_relation_id`);

--
-- Indexes for table `customer_shared_profiles_t`
--
ALTER TABLE `customer_shared_profiles_t`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_t`
--
ALTER TABLE `customer_t`
 ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `docs_type_m`
--
ALTER TABLE `docs_type_m`
 ADD PRIMARY KEY (`type_id`);

--
-- Indexes for table `master_item_m`
--
ALTER TABLE `master_item_m`
 ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `otp_t`
--
ALTER TABLE `otp_t`
 ADD PRIMARY KEY (`otp_id`);

--
-- Indexes for table `products_m`
--
ALTER TABLE `products_m`
 ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `repair_estimation_m`
--
ALTER TABLE `repair_estimation_m`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `repair_types_m`
--
ALTER TABLE `repair_types_m`
 ADD PRIMARY KEY (`type_id`);

--
-- Indexes for table `request_types_m`
--
ALTER TABLE `request_types_m`
 ADD PRIMARY KEY (`typeid`);

--
-- Indexes for table `retailer_m`
--
ALTER TABLE `retailer_m`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `service_request_t`
--
ALTER TABLE `service_request_t`
 ADD PRIMARY KEY (`request_id`);

--
-- Indexes for table `service_request_update_t`
--
ALTER TABLE `service_request_update_t`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subcategory_m`
--
ALTER TABLE `subcategory_m`
 ADD PRIMARY KEY (`subcategory_id`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
 ADD PRIMARY KEY (`ticket_id`);

--
-- Indexes for table `user_type_m`
--
ALTER TABLE `user_type_m`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address_type`
--
ALTER TABLE `address_type`
MODIFY `address_type_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `brands_m`
--
ALTER TABLE `brands_m`
MODIFY `brand_id` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `brand_admin`
--
ALTER TABLE `brand_admin`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `brand_type_m`
--
ALTER TABLE `brand_type_m`
MODIFY `typeid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `brand_users_t`
--
ALTER TABLE `brand_users_t`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `callback`
--
ALTER TABLE `callback`
MODIFY `callback_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `category_m`
--
ALTER TABLE `category_m`
MODIFY `category_id` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `content_business_type_m`
--
ALTER TABLE `content_business_type_m`
MODIFY `type_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `content_data_m`
--
ALTER TABLE `content_data_m`
MODIFY `content_dataid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `content_type_m`
--
ALTER TABLE `content_type_m`
MODIFY `type_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `customer_address_map_t`
--
ALTER TABLE `customer_address_map_t`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `customer_address_t`
--
ALTER TABLE `customer_address_t`
MODIFY `customer_address_id` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `customer_device_details_t`
--
ALTER TABLE `customer_device_details_t`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `customer_item_data_t`
--
ALTER TABLE `customer_item_data_t`
MODIFY `item_id` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=40;
--
-- AUTO_INCREMENT for table `customer_item_documents_t`
--
ALTER TABLE `customer_item_documents_t`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `customer_relation_t`
--
ALTER TABLE `customer_relation_t`
MODIFY `customer_relation_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `customer_shared_profiles_t`
--
ALTER TABLE `customer_shared_profiles_t`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `customer_t`
--
ALTER TABLE `customer_t`
MODIFY `customer_id` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `docs_type_m`
--
ALTER TABLE `docs_type_m`
MODIFY `type_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `master_item_m`
--
ALTER TABLE `master_item_m`
MODIFY `item_id` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `otp_t`
--
ALTER TABLE `otp_t`
MODIFY `otp_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `products_m`
--
ALTER TABLE `products_m`
MODIFY `product_id` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `repair_estimation_m`
--
ALTER TABLE `repair_estimation_m`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `repair_types_m`
--
ALTER TABLE `repair_types_m`
MODIFY `type_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `request_types_m`
--
ALTER TABLE `request_types_m`
MODIFY `typeid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `retailer_m`
--
ALTER TABLE `retailer_m`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `service_request_t`
--
ALTER TABLE `service_request_t`
MODIFY `request_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `service_request_update_t`
--
ALTER TABLE `service_request_update_t`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `subcategory_m`
--
ALTER TABLE `subcategory_m`
MODIFY `subcategory_id` int(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
MODIFY `ticket_id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_type_m`
--
ALTER TABLE `user_type_m`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
