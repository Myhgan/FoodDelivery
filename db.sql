-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for osahaneat
CREATE DATABASE IF NOT EXISTS `osahaneat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `osahaneat`;

-- Dumping structure for table osahaneat.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_cate` varchar(50) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table osahaneat.category: ~0 rows (approximately)
INSERT INTO `category` (`id`, `name_cate`, `create_date`) VALUES
	(1, 'Buger', '2024-08-17 17:32:10'),
	(2, 'pizza', '2024-08-19 12:42:33');

-- Dumping structure for table osahaneat.food
CREATE TABLE IF NOT EXISTS `food` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `image` text,
  `time_ship` varchar(10) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `cate_id` int DEFAULT NULL,
  `is_freeship` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_food_cate_id` (`cate_id`),
  CONSTRAINT `fk_food_cate_id` FOREIGN KEY (`cate_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table osahaneat.food: ~1 rows (approximately)
INSERT INTO `food` (`id`, `title`, `image`, `time_ship`, `price`, `cate_id`, `is_freeship`) VALUES
	(7, 'sada fff', 'Hậu.jpg', '20 minues', 65000, 1, 1);

-- Dumping structure for table osahaneat.menurestaurant
CREATE TABLE IF NOT EXISTS `menurestaurant` (
  `res_id` int NOT NULL,
  `cate_id` int NOT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`res_id`,`cate_id`) USING BTREE,
  KEY `fk_menurestaurant_res_id` (`cate_id`) USING BTREE,
  CONSTRAINT `FK_menurestaurant_category` FOREIGN KEY (`cate_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_menurestaurant_restaurant` FOREIGN KEY (`res_id`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table osahaneat.menurestaurant: ~0 rows (approximately)
INSERT INTO `menurestaurant` (`res_id`, `cate_id`, `create_date`) VALUES
	(2, 1, '2024-08-19 12:53:51');

-- Dumping structure for table osahaneat.orderitem
CREATE TABLE IF NOT EXISTS `orderitem` (
  `order_id` int NOT NULL,
  `food_id` int NOT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`,`food_id`),
  KEY `fk_orderitem_food_id` (`food_id`),
  CONSTRAINT `fk_orderitem_food_id` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`),
  CONSTRAINT `fk_orderitem_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table osahaneat.orderitem: ~0 rows (approximately)

-- Dumping structure for table osahaneat.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `res_id` int DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orders_user_id` (`user_id`),
  KEY `fk_orders_res_id` (`res_id`),
  CONSTRAINT `fk_orders_res_id` FOREIGN KEY (`res_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `fk_orders_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table osahaneat.orders: ~1 rows (approximately)

-- Dumping structure for table osahaneat.promo
CREATE TABLE IF NOT EXISTS `promo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `res_id` int DEFAULT NULL,
  `percent` int DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_promo_res_id` (`res_id`),
  CONSTRAINT `fk_promo_res_id` FOREIGN KEY (`res_id`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table osahaneat.promo: ~0 rows (approximately)

-- Dumping structure for table osahaneat.ratingfood
CREATE TABLE IF NOT EXISTS `ratingfood` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `food_id` int DEFAULT NULL,
  `content` text,
  `rate_point` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ratingfood_user_id` (`user_id`),
  KEY `fk_ratingfood_food_id` (`food_id`),
  CONSTRAINT `fk_ratingfood_food_id` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`),
  CONSTRAINT `fk_ratingfood_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table osahaneat.ratingfood: ~0 rows (approximately)

-- Dumping structure for table osahaneat.ratingrestaurant
CREATE TABLE IF NOT EXISTS `ratingrestaurant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `res_id` int DEFAULT NULL,
  `content` text,
  `rate_point` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ratingrestaurant_user_id` (`user_id`),
  KEY `fk_ratingrestaurant_res_id` (`res_id`),
  CONSTRAINT `fk_ratingrestaurant_res_id` FOREIGN KEY (`res_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `fk_ratingrestaurant_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table osahaneat.ratingrestaurant: ~2 rows (approximately)
INSERT INTO `ratingrestaurant` (`id`, `user_id`, `res_id`, `content`, `rate_point`) VALUES
	(1, 2, 2, 'so very good', 5),
	(2, 1, 2, 'not bad\r\n', 4);

-- Dumping structure for table osahaneat.restaurant
CREATE TABLE IF NOT EXISTS `restaurant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  `description` text,
  `image` text,
  `is_freeship` tinyint(1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `open_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table osahaneat.restaurant: ~0 rows (approximately)
INSERT INTO `restaurant` (`id`, `title`, `subtitle`, `description`, `image`, `is_freeship`, `address`, `open_date`) VALUES
	(2, 'Buger King', 'American, Fast Food', 'asgdh danskjd nakd', 'Hậu.jpg', 1, 'nguyen duy trinh, long truong', '2024-08-01 17:00:00');

-- Dumping structure for table osahaneat.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table osahaneat.roles: ~2 rows (approximately)
INSERT INTO `roles` (`id`, `role_name`, `create_date`) VALUES
	(1, 'Role_admin', NULL),
	(2, 'Role_user', NULL);

-- Dumping structure for table osahaneat.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `fullname` varchar(50) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_users_role_id` (`role_id`),
  CONSTRAINT `fk_users_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table osahaneat.users: ~3 rows (approximately)
INSERT INTO `users` (`id`, `user_name`, `PASSWORD`, `fullname`, `create_date`, `role_id`) VALUES
	(1, 'chuot', '$2a$12$vIiAeKpdSDpX9VBuzPWLqOBjzBlG1ieVbwkgPVzpJ1N9MquWuBI8W', 'HauKhung', '2024-05-21 07:48:58', 1),
	(2, 'mon', '12345', 'HangThongMinh', '2024-05-21 07:49:26', 2),
	(6, 'an@gmail.coms', '123s', 'Nasn', NULL, 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
