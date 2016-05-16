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
CREATE DATABASE IF NOT EXISTS `psoas` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `psoas`;


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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table psoas.announcements: ~2 rows (approximately)
/*!40000 ALTER TABLE `announcements` DISABLE KEYS */;
INSERT INTO `announcements` (`id`, `title`, `message`, `announcer`, `publish`, `created`, `updated`) VALUES
	(2, 'test title', 'test message', 1, '2016-04-23 15:00:51', '2016-04-23 15:00:53', '2016-04-23 15:00:55'),
	(3, 'test title', 'test message', 1, '2016-03-21 01:18:31', '2016-04-23 15:00:54', '2016-04-23 15:00:57');
/*!40000 ALTER TABLE `announcements` ENABLE KEYS */;


-- Dumping structure for table psoas.categories
CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(50) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(32) NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table psoas.categories: ~0 rows (approximately)
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` (`id`, `name`, `created`, `updated`) VALUES
	(1, 'test category', NULL, NULL);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Dumping data for table psoas.contests: ~2 rows (approximately)
/*!40000 ALTER TABLE `contests` DISABLE KEYS */;
INSERT INTO `contests` (`id`, `title`, `description`, `category`, `creator`, `status`, `created`, `updated`) VALUES
	(3, 'test contest title', 'test contest description', 1, 1, 1, '2016-03-20 17:33:28', '2016-04-24 10:16:51'),
	(4, 'test contest title', 'test contest description', 1, 1, 1, '2016-03-20 17:33:28', '2016-04-24 10:16:53');
/*!40000 ALTER TABLE `contests` ENABLE KEYS */;


-- Dumping structure for table psoas.ratings
CREATE TABLE IF NOT EXISTS `ratings` (
  `id` int(50) unsigned NOT NULL AUTO_INCREMENT,
  `rating` int(1) DEFAULT NULL,
  `message` text,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table psoas.ratings: ~0 rows (approximately)
/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;


-- Dumping structure for table psoas.settings
CREATE TABLE IF NOT EXISTS `settings` (
  `id` int(50) unsigned NOT NULL AUTO_INCREMENT,
  `setting` text NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table psoas.settings: ~2 rows (approximately)
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` (`id`, `setting`, `created`, `updated`) VALUES
	(1, 'Announcements', '2016-03-17 09:00:14', '2016-03-17 09:00:14'),
	(2, 'Volume', '2016-03-17 09:00:22', '2016-03-17 09:00:22');
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table psoas.users: ~1 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `name`, `email`, `pass_hash`, `coins`, `is_admin`, `is_active`, `last_login`, `created`, `updated`) VALUES
	(1, 'furqan', 'furqan1@furqan.com', 'df4358b3b8209d8123ac6384d02ef8d0', 5, 0, 1, NULL, '2016-03-16 22:26:02', '2016-03-16 22:26:02'),
	(2, 'furqan-ahmed', 'ahmedfurqan88+2@gmail.com', 'df4358b3b8209d8123ac6384d02ef8d0', 0, 0, 1, NULL, NULL, NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table psoas.user_settings: ~4 rows (approximately)
/*!40000 ALTER TABLE `user_settings` DISABLE KEYS */;
INSERT INTO `user_settings` (`id`, `user_id`, `setting`, `status`, `created`, `updated`) VALUES
	(1, 1, 1, 1, '2016-03-17 09:02:30', '2016-03-17 09:02:30'),
	(2, 1, 2, 1, '2016-03-17 09:02:38', '2016-03-17 09:02:38'),
	(3, 2, 1, 1, '2016-03-17 09:02:48', '2016-03-17 09:02:48'),
	(4, 2, 2, 1, '2016-03-17 09:02:55', '2016-03-17 09:02:55');
/*!40000 ALTER TABLE `user_settings` ENABLE KEYS */;


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
  CONSTRAINT `votes_ibfk_2` FOREIGN KEY (`contest`) REFERENCES `contests` (`id`) ON DELETE CASCADE,
  CONSTRAINT `votes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table psoas.votes: ~0 rows (approximately)
/*!40000 ALTER TABLE `votes` DISABLE KEYS */;
/*!40000 ALTER TABLE `votes` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
