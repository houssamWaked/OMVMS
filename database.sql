-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.4.0-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for omvms
CREATE DATABASE IF NOT EXISTS `omvms` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `omvms`;

-- Dumping structure for table omvms.category
CREATE TABLE IF NOT EXISTS `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table omvms.category: ~21 rows (approximately)
REPLACE INTO `category` (`category_id`, `category_name`) VALUES
	(1, 'New Category Name'),
	(2, 'Home Appliances'),
	(3, 'Furniture'),
	(4, 'Toys'),
	(5, 'Books'),
	(6, 'Groceries'),
	(7, 'Clothing'),
	(8, 'Beauty & Personal Care'),
	(9, 'Sports'),
	(10, 'Automotive'),
	(11, 'Garden & Outdoor'),
	(12, 'Health'),
	(13, 'Pets'),
	(14, 'Music'),
	(15, 'Movies'),
	(16, 'Software'),
	(17, 'Games'),
	(18, 'Stationery'),
	(19, 'Food'),
	(20, 'Beverages'),
	(21, 'New Category Name');

-- Dumping structure for table omvms.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table omvms.customer: ~20 rows (approximately)
REPLACE INTO `customer` (`customer_id`, `name`, `email`, `password`, `phone`, `address`) VALUES
	(1, 'John Updated', 'johnupdated@example.com', 'newpassword123', '9876543210', '789 Updated Street, City, Country'),
	(2, 'Jane Doe', 'janedoe@example.com', 'newpassword123', '9876543210', '456 Another Street'),
	(3, 'Bob Johnson', 'bob.johnson@example.com', 'password123', '3456789012', '789 Oak St, City C'),
	(4, 'Alice Davis', 'alice.davis@example.com', 'password123', '4567890123', '101 Pine St, City D'),
	(5, 'Charlie Brown', 'charlie.brown@example.com', 'password123', '5678901234', '202 Maple St, City E'),
	(6, 'David White', 'david.white@example.com', 'password123', '6789012345', '303 Birch St, City F'),
	(7, 'Eve Black', 'eve.black@example.com', 'password123', '7890123456', '404 Cedar St, City G'),
	(8, 'Frank Green', 'frank.green@example.com', 'password123', '8901234567', '505 Walnut St, City H'),
	(9, 'Grace Blue', 'grace.blue@example.com', 'password123', '9012345678', '606 Pine St, City I'),
	(10, 'Hank Red', 'hank.red@example.com', 'password123', '0123456789', '707 Maple St, City J'),
	(11, 'Ivy Purple', 'ivy.purple@example.com', 'password123', '1231231234', '808 Birch St, City K'),
	(12, 'Jack Gold', 'jack.gold@example.com', 'password123', '2342342345', '909 Cedar St, City L'),
	(13, 'Lily Pink', 'lily.pink@example.com', 'password123', '3453453456', '1010 Walnut St, City M'),
	(14, 'Mike Gray', 'mike.gray@example.com', 'password123', '4564564567', '1111 Pine St, City N'),
	(15, 'Nancy Silver', 'nancy.silver@example.com', 'password123', '5675675678', '1212 Maple St, City O'),
	(16, 'Oscar Copper', 'oscar.copper@example.com', 'password123', '6786786789', '1313 Birch St, City P'),
	(17, 'Paul Copper', 'paul.copper@example.com', 'password123', '7897897890', '1414 Cedar St, City Q'),
	(18, 'Quincy Zinc', 'quincy.zinc@example.com', 'password123', '8908908901', '1515 Walnut St, City R'),
	(19, 'Rachel Iron', 'rachel.iron@example.com', 'password123', '9019019012', '1616 Pine St, City S'),
	(20, 'Sam Steel', 'sam.steel@example.com', 'password123', '0120120123', '1717 Maple St, City T'),
	(21, 'John Doe', 'johndoe@example.com', 'password123', '1234567890', '123 Main Street'),
	(25, 'John Dose', 'johnddoe@example.com', 'passdword123', '1234567890', '123 Main Street, City, Country');

-- Dumping structure for table omvms.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` datetime(6) NOT NULL,
  `total_price` double NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `orders_ibfk_2` (`vendor_id`),
  KEY `orders_ibfk_1` (`customer_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`vendor_id`) REFERENCES `vendor` (`vendor_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table omvms.orders: ~13 rows (approximately)
REPLACE INTO `orders` (`order_id`, `order_date`, `total_price`, `customer_id`, `vendor_id`) VALUES
	(1, '2024-12-09 12:30:00.000000', 750, NULL, NULL),
	(2, '2024-12-02 00:00:00.000000', 480.5, 2, 2),
	(3, '2024-12-03 00:00:00.000000', 1200, 3, 3),
	(4, '2024-12-04 00:00:00.000000', 850, 4, 4),
	(5, '2024-12-05 00:00:00.000000', 220, 5, 5),
	(6, '2024-12-06 00:00:00.000000', 740, 1, 1),
	(7, '2024-12-07 00:00:00.000000', 950, 2, 2),
	(8, '2024-12-08 00:00:00.000000', 1350, 3, 3),
	(9, '2024-12-09 00:00:00.000000', 600, 4, 4),
	(10, '2024-12-10 00:00:00.000000', 880, 5, 5),
	(11, '2024-12-06 17:56:49.710095', 799.99, 1, 1),
	(12, '2024-12-06 00:00:00.000000', 500, NULL, NULL),
	(13, '2024-12-06 00:00:00.000000', 500, 1, 1),
	(14, '2024-12-09 12:30:00.000000', 100, 1, 1);

-- Dumping structure for table omvms.order_item
CREATE TABLE IF NOT EXISTS `order_item` (
  `order_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`),
  CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table omvms.order_item: ~0 rows (approximately)

-- Dumping structure for table omvms.order_items
CREATE TABLE IF NOT EXISTS `order_items` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
  CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table omvms.order_items: ~0 rows (approximately)

-- Dumping structure for table omvms.product
CREATE TABLE IF NOT EXISTS `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `category_id` (`category_id`),
  KEY `product_ibfk_1` (`vendor_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`vendor_id`) REFERENCES `vendor` (`vendor_id`) ON DELETE CASCADE,
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table omvms.product: ~21 rows (approximately)
REPLACE INTO `product` (`product_id`, `product_name`, `price`, `stock`, `description`, `vendor_id`, `category_id`) VALUES
	(1, 'Laptop', 575.9928, 100, 'A powerful laptop with 16GB RAM and 512GB SSD', 1, 1),
	(2, 'Smartphone', 539.991, 150, 'Latest model smartphone with high-resolution camera', 2, 1),
	(3, 'Smartwatch', 199.99, 200, 'A stylish smartwatch with fitness tracking features', 3, 10),
	(4, 'Headphones', 129.99, 50, 'Noise-cancelling wireless headphones', 4, 1),
	(5, 'Tablet', 399.99, 120, 'Lightweight tablet with 10-inch display', 5, 1),
	(6, 'Keyboard', 49.99, 300, 'Mechanical keyboard with RGB lighting', 6, 1),
	(7, 'Mouse', 29.99, 350, 'Wireless mouse with ergonomic design', 7, 1),
	(8, 'Monitor', 199.99, 80, '24-inch full HD monitor for gaming', 8, 17),
	(9, 'USB Drive', 19.99, 500, '64GB USB flash drive for data storage', 9, 16),
	(10, 'Printer', 89.99, 60, 'Wireless printer with duplex printing', 10, 20),
	(11, 'Router', 59.99, 100, 'High-speed Wi-Fi router with long-range coverage', 11, 10),
	(12, 'Smart Lightbulb', 15.99, 400, 'Energy-efficient smart lightbulb', 12, 3),
	(13, 'Camera', 499.99, 30, 'DSLR camera with 18-55mm lens', 13, 4),
	(14, 'Projector', 299.99, 40, 'Portable mini projector for movies and presentations', 14, 5),
	(15, 'Bluetooth Speaker', 49.99, 250, 'Portable Bluetooth speaker with clear sound', 15, 10),
	(16, 'VR Headset', 199.99, 70, 'Virtual reality headset for immersive experiences', 16, 20),
	(17, 'Power Bank', 29.99, 300, '10,000mAh power bank for charging devices on the go', 17, 5),
	(18, 'Wireless Charger', 39.99, 150, 'Fast wireless charging pad for smartphones', 18, 6),
	(19, 'Updated Product', 30, 150, 'Updated description', NULL, NULL),
	(20, 'Coffee Maker', 99.99, 50, 'Coffee maker with programmable features', 20, 9),
	(21, 'Product1', 25.5, 100, 'A new product', NULL, NULL);

-- Dumping structure for table omvms.review
CREATE TABLE IF NOT EXISTS `review` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `rating` int(11) DEFAULT NULL CHECK (`rating` between 1 and 5),
  `comment` varchar(500) DEFAULT NULL,
  `review_date` datetime(6) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `customer_id` (`customer_id`),
  KEY `review_ibfk_1` (`product_id`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE,
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table omvms.review: ~19 rows (approximately)
REPLACE INTO `review` (`review_id`, `rating`, `comment`, `review_date`, `product_id`, `customer_id`) VALUES
	(1, 5, 'Great product, works perfectly!', '2024-11-01 00:00:00.000000', 1, 1),
	(2, 4, 'Good smartphone, but battery could be better', '2024-11-02 00:00:00.000000', 2, 2),
	(3, 5, 'Love this smartwatch, very comfortable and functional', '2024-11-03 00:00:00.000000', 3, 3),
	(4, 3, 'Headphones are decent, but not great for bass', '2024-11-04 00:00:00.000000', 4, 4),
	(5, 5, 'Excellent tablet for work and play', '2024-11-05 00:00:00.000000', 5, 5),
	(6, 5, 'Updated review: Excellent product!', '2024-12-09 00:37:02.924000', NULL, NULL),
	(7, 5, 'The mouse is extremely comfortable', '2024-11-07 00:00:00.000000', 7, 7),
	(8, 4, 'Monitor is good, but colors could be more vibrant', '2024-11-08 00:00:00.000000', 8, 8),
	(9, 5, 'Perfect for quick data transfer', '2024-11-09 00:00:00.000000', 9, 9),
	(10, 5, 'Prints are sharp and fast', '2024-11-10 00:00:00.000000', 10, 10),
	(11, 4, 'Router works well, but setup was confusing', '2024-11-11 00:00:00.000000', 11, 11),
	(12, 5, 'Smart bulb is easy to install and energy-efficient', '2024-11-12 00:00:00.000000', 12, 12),
	(13, 5, 'Amazing camera, great quality for photos', '2024-11-13 00:00:00.000000', 13, 13),
	(14, 4, 'Projector is portable but lacks high resolution', '2024-11-14 00:00:00.000000', 14, 14),
	(15, 5, 'Superb sound quality for the price', '2024-11-15 00:00:00.000000', 15, 15),
	(16, 5, 'VR headset is fantastic for gaming', '2024-11-16 00:00:00.000000', 16, 16),
	(17, 5, 'A must-have for charging devices on the go', '2024-11-17 00:00:00.000000', 17, 17),
	(18, 5, 'Wireless charger works fast', '2024-11-18 00:00:00.000000', 18, 18),
	(20, 5, 'Great coffee maker, makes excellent coffee', '2024-11-20 00:00:00.000000', 20, 20),
	(21, 5, 'Updated review: Excellent product!', '2024-12-09 00:37:02.924000', NULL, NULL);

-- Dumping structure for table omvms.vendor
CREATE TABLE IF NOT EXISTS `vendor` (
  `vendor_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `registration_date` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`vendor_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table omvms.vendor: ~21 rows (approximately)
REPLACE INTO `vendor` (`vendor_id`, `name`, `email`, `password`, `registration_date`, `address`) VALUES
	(1, 'Vendor One', 'vendor1@example.com', 'password123', '2024-01-01', '123 Vendor St, City A'),
	(2, 'Vendor A Updated', 'vendora_updated@example.com', 'newpassword123', NULL, 'Updated Vendor Address'),
	(3, 'Vendor Three', 'vendor3@example.com', 'password123', '2024-03-01', '789 Vendor Rd, City C'),
	(4, 'Vendor Four', 'vendor4@example.com', 'password123', '2024-04-01', '101 Vendor Blvd, City D'),
	(5, 'Vendor Five', 'vendor5@example.com', 'password123', '2024-05-01', '202 Vendor Ln, City E'),
	(6, 'Vendor Six', 'vendor6@example.com', 'password123', '2024-06-01', '303 Vendor Dr, City F'),
	(7, 'Vendor Seven', 'vendor7@example.com', 'password123', '2024-07-01', '404 Vendor Pl, City G'),
	(8, 'Vendor Eight', 'vendor8@example.com', 'password123', '2024-08-01', '505 Vendor Cr, City H'),
	(9, 'Vendor Nine', 'vendor9@example.com', 'password123', '2024-09-01', '606 Vendor Ave, City I'),
	(10, 'Vendor Ten', 'vendor10@example.com', 'password123', '2024-10-01', '707 Vendor Rd, City J'),
	(11, 'Vendor Eleven', 'vendor11@example.com', 'password123', '2024-11-01', '808 Vendor St, City K'),
	(12, 'Vendor Twelve', 'vendor12@example.com', 'password123', '2024-12-01', '909 Vendor Blvd, City L'),
	(13, 'Vendor Thirteen', 'vendor13@example.com', 'password123', '2024-01-15', '1010 Vendor Ln, City M'),
	(14, 'Vendor Fourteen', 'vendor14@example.com', 'password123', '2024-02-15', '1111 Vendor Ave, City N'),
	(15, 'Vendor Fifteen', 'vendor15@example.com', 'password123', '2024-03-15', '1212 Vendor Rd, City O'),
	(16, 'Vendor Sixteen', 'vendor16@example.com', 'password123', '2024-04-15', '1313 Vendor Dr, City P'),
	(17, 'Vendor Seventeen', 'vendor17@example.com', 'password123', '2024-05-15', '1414 Vendor Pl, City Q'),
	(18, 'Vendor Eighteen', 'vendor18@example.com', 'password123', '2024-06-15', '1515 Vendor Cr, City R'),
	(19, 'Vendor Nineteen', 'vendor19@example.com', 'password123', '2024-07-15', '1616 Vendor Ave, City S'),
	(20, 'Vendor Twenty', 'vendor20@example.com', 'password123', '2024-08-15', '1717 Vendor Blvd, City T'),
	(21, 'Vendor A', 'vendora@example.com', 'vendorpassword123', NULL, 'Vendor Address');

-- Dumping structure for table omvms.vendor_payment
CREATE TABLE IF NOT EXISTS `vendor_payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `payment_date` datetime(6) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `vendor_payment_ibfk_1` (`vendor_id`),
  CONSTRAINT `vendor_payment_ibfk_1` FOREIGN KEY (`vendor_id`) REFERENCES `vendor` (`vendor_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table omvms.vendor_payment: ~30 rows (approximately)
REPLACE INTO `vendor_payment` (`payment_id`, `amount`, `payment_date`, `vendor_id`) VALUES
	(1, 1000, '2024-11-01 00:00:00.000000', 1),
	(2, 1200, '2024-11-02 00:00:00.000000', 2),
	(3, 1500, '2024-11-03 00:00:00.000000', 3),
	(4, 500, '2024-11-04 00:00:00.000000', 4),
	(5, 800, '2024-11-05 00:00:00.000000', 5),
	(6, 700, '2024-11-06 00:00:00.000000', 6),
	(7, 1000, '2024-11-07 00:00:00.000000', 7),
	(8, 1300, '2024-11-08 00:00:00.000000', 8),
	(9, 900, '2024-11-09 00:00:00.000000', 9),
	(10, 600, '2024-11-10 00:00:00.000000', 10),
	(11, 1400, '2024-11-11 00:00:00.000000', 11),
	(12, 1100, '2024-11-12 00:00:00.000000', 12),
	(13, 1500, '2024-11-13 00:00:00.000000', 13),
	(14, 1300, '2024-11-14 00:00:00.000000', 14),
	(15, 1000, '2024-11-15 00:00:00.000000', 15),
	(16, 800, '2024-11-16 00:00:00.000000', 16),
	(17, 950, '2024-11-17 00:00:00.000000', 17),
	(18, 1200, '2024-11-18 00:00:00.000000', 18),
	(19, 1100, '2024-11-19 00:00:00.000000', 19),
	(20, 1300, '2024-11-20 00:00:00.000000', 20),
	(21, 1500, '2024-12-01 00:00:00.000000', 1),
	(22, 2400.5, '2024-12-02 00:00:00.000000', 2),
	(23, 3100.75, '2024-12-03 00:00:00.000000', 3),
	(24, 500, '2024-12-04 00:00:00.000000', 4),
	(25, 1300, '2024-12-05 00:00:00.000000', 5),
	(26, 2200, '2024-12-06 00:00:00.000000', 1),
	(27, 1800, '2024-12-07 00:00:00.000000', 2),
	(28, 2700, '2024-12-08 00:00:00.000000', 3),
	(29, 800, '2024-12-09 00:00:00.000000', 4),
	(30, 1900, '2024-12-10 00:00:00.000000', 5);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
