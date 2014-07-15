-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Jul 12, 2014 at 06:41 PM
-- Server version: 5.5.34
-- PHP Version: 5.5.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `Log`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblLog`
--

CREATE TABLE `tblLog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` mediumtext NOT NULL,
  `date` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;
