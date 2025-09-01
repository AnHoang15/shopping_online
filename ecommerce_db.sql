-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 01, 2025 at 04:39 PM
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
-- Database: `ecommerce_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `quantity`, `product_id`, `user_id`) VALUES
(1, 2, 2, 12),
(3, 1, 8, 12);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `image_name`, `is_active`, `name`) VALUES
(2, 'laptop.webp', b'1', 'Laptop'),
(4, 'avatar-gau-truc.jpg', b'0', 'Mobile Phone'),
(5, 'el.jfif', b'1', 'Electronics'),
(6, 'clothé.webp', b'1', 'Clothes'),
(8, 'watch.jpg', b'1', 'Watch'),
(9, 'grocẻy.webp', b'1', 'Grocery'),
(10, 'makeup.jpg', b'1', 'Beauty');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `status` varchar(50) DEFAULT 'PENDING',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_address`
--

CREATE TABLE `order_address` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_address`
--

INSERT INTO `order_address` (`id`, `address`, `city`, `email`, `firstname`, `last_name`, `mobile_no`, `pincode`, `state`, `first_name`) VALUES
(1, 'Hoa Lu', 'Ninh Binh', 'anhoangah2005@gmail.com', 'An', 'Hoang', '122245', '123', 'VietNam', NULL),
(2, 'Hoa Lu', 'Ninh Binh', 'anhoangah2005@gmail.com', 'An', 'Hoang', '122245', '123', 'VietNam', NULL),
(3, 'Hoa Lu', 'Ninh Binh', 'anhoangah2005@gmail.com', 'An', 'Hoang', '122245', '123', 'VietNam', NULL),
(4, 'Hoa Lu', 'Ninh Binh', 'anhoangah2005@gmail.com', 'An', 'Hoang', '122245', '123', 'VietNam', NULL),
(5, 'Hoa Lu', 'Ninh Binh', 'anhoangah2005@gmail.com', 'An', 'Hoang', '54234', '123', 'VietNam', NULL),
(6, 'Hoa Lu', 'Ninh Binh', 'anhoangah2005@gmail.com', 'An', 'Hoang', '54234', '123', 'VietNam', NULL),
(7, 'Hoa Lu', 'Ninh Binh', 'anhoangah2005@gmail.com', 'An', 'Hoang', '54234', '123', 'VietNam', NULL),
(8, 'Hoa Lu', 'Ninh Binh', 'anhoangah2005@gmail.com', 'An', 'Hoang', '54234', '123', 'VietNam', NULL),
(9, 'Hoa Lu', 'Ninh Binh', 'anhoangah2005@gmail.com', 'An', 'Hoang', '54234', '123', 'VietNam', NULL),
(10, 'Hoa Lu', 'Ninh Binh', 'anhoangah2005@gmail.com', NULL, 'Hoang', '54234', '123', 'VietNam', 'An'),
(11, 'Hoa Lu', 'Ninh Binh', 'anhoangah2005@gmail.com', NULL, 'Hoang', '54234', '123', 'VietNam', 'An');

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `desciption` varchar(500) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `title` varchar(500) DEFAULT NULL,
  `discount` int(11) NOT NULL,
  `discount_price` double NOT NULL DEFAULT 0,
  `is_active` bit(1) NOT NULL,
  `description` varchar(5000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `category`, `desciption`, `image`, `price`, `stock`, `title`, `discount`, `discount_price`, `is_active`, `description`) VALUES
(2, 'phone11', NULL, 'mobile.jpg', 2000000, 90, 'Iphone 17', 5, 1900000, b'0', NULL),
(3, 'Grocery', NULL, 'kinhdo.jpg', 120000, 12, 'Bánh trung thu ', 4, 115200, b'1', 'Kinh Đô'),
(4, 'Laptop', NULL, 'laptop.jpg', 70, 90, 'laptop Hp', 0, 0, b'0', NULL),
(5, 'Laptop', NULL, 'dell.jpg', 7000000, 90, 'laptop Dell', 40, 4200000, b'1', ''),
(6, 'Beauty', NULL, 'dior.jpg', 23000, 12, 'Son Dior', 0, 23000, b'1', ''),
(7, 'Beauty', NULL, 'beautìul.jpg', 200000, 34, 'Maybelline', 0, 200000, b'0', ''),
(8, 'Mobile Phone', NULL, 'ip.webp', 5000000, 12, 'Iphone 17', 0, 5000000, b'1', ''),
(9, 'Beauty', NULL, 'romand.webp', 400000, 12, 'Son Romand', 0, 400000, b'1', 'Made in Korea'),
(10, 'Watch', NULL, 'apple.png', 15000000, 56, 'Apple Wactch', 0, 15000000, b'1', 'Store'),
(11, 'Clothes', NULL, 'aodai.png', 450000, 45, 'Áo Dài ', 30, 315000, b'1', 'Việt Nam'),
(12, 'Electronics', NULL, 'magi.png', 450000000, 45, 'Máy giặt', 0, 450000000, b'1', '');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock` int(11) DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product_order`
--

CREATE TABLE `product_order` (
  `id` int(11) NOT NULL,
  `order_date` date DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `order_address_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_order`
--

INSERT INTO `product_order` (`id`, `order_date`, `order_id`, `payment_type`, `price`, `quantity`, `status`, `order_address_id`, `product_id`, `user_id`) VALUES
(5, '2025-08-27', 'f024c53b-6f29-4eb7-92af-a2fe0a646a34', 'ONLINE', 1900000, 2, 'In Progress', 5, 2, 12),
(6, '2025-08-27', '74cc2a9c-7ce9-4f37-86b4-89555c405b37', 'ONLINE', NULL, 1, 'Cancelled', 6, 6, 12),
(7, '2025-08-27', 'a972309e-b869-4efa-98fd-e81f9c0d27c7', 'COD', 1900000, 3, 'In Progress', 7, 2, 12),
(8, '2025-08-27', '2d34459e-7fa1-4ac0-9cec-d66cfc4e193e', 'COD', NULL, 1, 'In Progress', 8, 6, 12),
(9, '2025-08-27', '1c552366-7883-4fc2-a0e3-2b2587bfae3b', 'COD', 5000000, 1, 'In Progress', 9, 8, 12),
(10, '2025-08-29', 'ec8c2170-6d2f-4c12-90cf-52f3c6756c50', 'COD', 1900000, 3, 'In Progress', 10, 2, 12),
(11, '2025-08-29', 'd853edde-6c33-406c-9586-4514090671c1', 'COD', 5000000, 1, 'In Progress', 11, 8, 12);

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `rating` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`id`, `comment`, `created_at`, `rating`, `product_id`, `user_id`) VALUES
(1, 'Oke', '2025-08-30 14:23:50.000000', 5, 9, 12),
(3, '', '2025-08-31 13:26:08.000000', 4, 9, 12);

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` bigint(20) NOT NULL,
  `comment` text DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `rating` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`id`, `comment`, `created_at`, `rating`, `product_id`, `user_id`) VALUES
(1, 'oke', '2025-09-01 13:26:11.000000', 5, 9, 12),
(2, '', '2025-09-01 13:27:30.000000', 4, 9, 12);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_dtls`
--

CREATE TABLE `user_dtls` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `is_enable` bit(1) NOT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `failed_attempt` int(11) DEFAULT NULL,
  `lock_time` datetime(6) DEFAULT NULL,
  `reset_token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_dtls`
--

INSERT INTO `user_dtls` (`id`, `address`, `city`, `email`, `mobile_number`, `name`, `password`, `pincode`, `profile_image`, `state`, `role`, `is_enable`, `account_non_locked`, `failed_attempt`, `lock_time`, `reset_token`) VALUES
(10, 'Hoa Lu', 'Ninh Binh', 'admin@admin.com', '123456678', 'admin', '$2a$10$Mg7DNNpdnFUNeSIzEBPxOOcM5izl..WWjpakXZX5Z1CWkS0bV0Vzu', '123', 'laptop.jpg', 'VietNam', 'ROLE_ADMIN', b'1', b'1', 1, NULL, NULL),
(11, 'Hoa Lu', 'Ninh Binh', 'test@test.com', '0974301263', 'abcd', '$2a$10$.RNYoDRRcUNWqsrI.DSdm.djykAZcaUqGTdPdSGS82tNadbhYwvOa', '123', 'default.jpg', 'VietNam', 'ROLE_USER', b'1', b'0', 3, '2025-08-29 10:06:23.000000', '7c50f020-006d-4d12-a2c7-56f067a1120b'),
(12, 'Hoa Lu', 'Ninh Binh', 'anhoangah2005@gmail.com', '0974301263', 'AH', '$2a$10$HgBR7CW6nWctI5OCXZu3z.mdNMQpCmWEjaj36PZnrt8WmXVWfO5Oy', '123', 'avt.jpg', 'VietNam', 'ROLE_USER', b'1', b'1', 1, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3d704slv66tw6x5hmbm6p2x3u` (`product_id`),
  ADD KEY `FK9x4wn098i53ikun1ynxet2ynj` (`user_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `order_address`
--
ALTER TABLE `order_address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_order`
--
ALTER TABLE `product_order`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKqcdbxaeuc7c5gahwh0dutg04r` (`order_address_id`),
  ADD KEY `FKh73acsd9s5wp6l0e55td6jr1m` (`product_id`),
  ADD KEY `FK4f2ycr32kigtux5ag3tv0xu5m` (`user_id`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKiyof1sindb9qiqr9o8npj8klt` (`product_id`),
  ADD KEY `FKkrh9nym8w35dn6pi8lm0gpvki` (`user_id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9yqmlf28ges8c30nj4v4hva7t` (`product_id`),
  ADD KEY `FKj9xka4gk6lk2ew5vt1xdqc6di` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `user_dtls`
--
ALTER TABLE `user_dtls`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_address`
--
ALTER TABLE `order_address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_order`
--
ALTER TABLE `product_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_dtls`
--
ALTER TABLE `user_dtls`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FK3d704slv66tw6x5hmbm6p2x3u` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FK9x4wn098i53ikun1ynxet2ynj` FOREIGN KEY (`user_id`) REFERENCES `user_dtls` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `product_order`
--
ALTER TABLE `product_order`
  ADD CONSTRAINT `FK4f2ycr32kigtux5ag3tv0xu5m` FOREIGN KEY (`user_id`) REFERENCES `user_dtls` (`id`),
  ADD CONSTRAINT `FK8frxalwc79tpxo7hgqp3hsjck` FOREIGN KEY (`order_address_id`) REFERENCES `order_address` (`id`),
  ADD CONSTRAINT `FKh73acsd9s5wp6l0e55td6jr1m` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `FKiyof1sindb9qiqr9o8npj8klt` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKkrh9nym8w35dn6pi8lm0gpvki` FOREIGN KEY (`user_id`) REFERENCES `user_dtls` (`id`);

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `FK9yqmlf28ges8c30nj4v4hva7t` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKj9xka4gk6lk2ew5vt1xdqc6di` FOREIGN KEY (`user_id`) REFERENCES `user_dtls` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
