-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.17 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for psoas
CREATE DATABASE IF NOT EXISTS `psoas-test` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `psoas-test`;


-- Dumping structure for table psoas.announcements
CREATE TABLE IF NOT EXISTS `announcements` (
  `id` int(50) unsigned NOT NULL AUTO_INCREMENT,
  `title` char(64) NOT NULL,
  `message` text NOT NULL,
  `announcer` int(50) unsigned NOT NULL,
  `publish` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `announcer` (`announcer`),
  CONSTRAINT `announcements_ibfk_1` FOREIGN KEY (`announcer`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table psoas.categories
CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(50) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(32) NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table psoas.contests
CREATE TABLE IF NOT EXISTS `contests` (
  `id` int(50) unsigned NOT NULL AUTO_INCREMENT,
  `title` char(64) NOT NULL,
  `description` text NOT NULL,
  `category` int(50) unsigned NOT NULL,
  `creator` int(50) unsigned NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `creator` (`creator`),
  CONSTRAINT `contests_ibfk_1` FOREIGN KEY (`creator`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table psoas.ratings
CREATE TABLE IF NOT EXISTS `ratings` (
  `id` int(50) unsigned NOT NULL AUTO_INCREMENT,
  `rating` int(1) DEFAULT NULL,
  `message` text,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table psoas.settings
CREATE TABLE IF NOT EXISTS `settings` (
  `id` int(50) unsigned NOT NULL AUTO_INCREMENT,
  `setting` text NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table psoas.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(50) unsigned NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `email` char(30) NOT NULL,
  `pass_hash` char(64) NOT NULL,
  `coins` int(10) DEFAULT '5',
  `is_admin` int(1) NOT NULL DEFAULT '0',
  `is_active` tinyint(1) DEFAULT '0',
  `last_login` timestamp NULL DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table psoas.user_settings
CREATE TABLE IF NOT EXISTS `user_settings` (
  `id` int(50) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(50) unsigned NOT NULL,
  `setting` int(50) unsigned NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `setting` (`setting`),
  CONSTRAINT `user_settings_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `user_settings_ibfk_2` FOREIGN KEY (`setting`) REFERENCES `settings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table psoas.votes
CREATE TABLE IF NOT EXISTS `votes` (
  `id` int(50) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(50) unsigned NOT NULL,
  `contest` int(50) unsigned NOT NULL,
  `rating` int(1) unsigned NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `votes_ibfk_2` (`contest`),
  CONSTRAINT `votes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `votes_ibfk_2` FOREIGN KEY (`contest`) REFERENCES `contests` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
