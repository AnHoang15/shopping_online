INSERT INTO `cart` (`id`, `quantity`, `product_id`, `user_id`) VALUES
(1, 2, 2, 12),
(3, 1, 8, 12);

INSERT INTO `category` (`id`, `image_name`, `is_active`, `name`) VALUES
(2, 'laptop.webp', b'1', 'Laptop'),
(4, 'avatar-gau-truc.jpg', b'0', 'Mobile Phone'),
(5, 'el.jfif', b'1', 'Electronics'),
(6, 'clothé.webp', b'1', 'Clothes'),
(8, 'watch.jpg', b'1', 'Watch'),
(9, 'grocẻy.webp', b'1', 'Grocery'),
(10, 'makeup.jpg', b'1', 'Beauty');

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

INSERT INTO `product_order` (`id`, `order_date`, `order_id`, `payment_type`, `price`, `quantity`, `status`, `order_address_id`, `product_id`, `user_id`) VALUES
(5, '2025-08-27', 'f024c53b-6f29-4eb7-92af-a2fe0a646a34', 'ONLINE', 1900000, 2, 'In Progress', 5, 2, 12),
(6, '2025-08-27', '74cc2a9c-7ce9-4f37-86b4-89555c405b37', 'ONLINE', NULL, 1, 'Cancelled', 6, 6, 12),
(7, '2025-08-27', 'a972309e-b869-4efa-98fd-e81f9c0d27c7', 'COD', 1900000, 3, 'In Progress', 7, 2, 12),
(8, '2025-08-27', '2d34459e-7fa1-4ac0-9cec-d66cfc4e193e', 'COD', NULL, 1, 'In Progress', 8, 6, 12),
(9, '2025-08-27', '1c552366-7883-4fc2-a0e3-2b2587bfae3b', 'COD', 5000000, 1, 'In Progress', 9, 8, 12),
(10, '2025-08-29', 'ec8c2170-6d2f-4c12-90cf-52f3c6756c50', 'COD', 1900000, 3, 'In Progress', 10, 2, 12),
(11, '2025-08-29', 'd853edde-6c33-406c-9586-4514090671c1', 'COD', 5000000, 1, 'In Progress', 11, 8, 12);

INSERT INTO `review` (`id`, `comment`, `created_at`, `rating`, `product_id`, `user_id`) VALUES
(1, 'Oke', '2025-08-30 14:23:50.000000', 5, 9, 12),
(3, '', '2025-08-31 13:26:08.000000', 4, 9, 12);

INSERT INTO `reviews` (`id`, `comment`, `created_at`, `rating`, `product_id`, `user_id`) VALUES
(1, 'oke', '2025-09-01 13:26:11.000000', 5, 9, 12),
(2, '', '2025-09-01 13:27:30.000000', 4, 9, 12);

INSERT INTO `user_dtls` (`id`, `address`, `city`, `email`, `mobile_number`, `name`, `password`, `pincode`, `profile_image`, `state`, `role`, `is_enable`, `account_non_locked`, `failed_attempt`, `lock_time`, `reset_token`) VALUES
(10, 'Hoa Lu', 'Ninh Binh', 'admin@admin.com', '123456678', 'admin', '$2a$10$kTItU18U5NNLg06MFuHD0Oq7WeP9OkqgH9EBbn49Q2c12DW2qPp5.', '12345', 'aodai.png', 'VietNam', 'ROLE_ADMIN', b'1', b'1', 0, NULL, NULL),
(12, 'Hoa Lu', 'Ninh Binh', 'anhoangah2005@gmail.com', '123456678', 'an', '$2a$10$4iW9Y3TS09FAYcb69beIR.w9G5olG1iLLW4VEkDe2VY8jgbG1lxMy', '12345', 'aodai.png', 'VietNam', 'ROLE_USER', b'1', b'1', 0, NULL, NULL);

INSERT INTO `users` (`id`, `username`, `email`, `password`, `created_at`) VALUES
(1, 'an', 'an@gmail.com', '123', '2025-08-29 01:54:50');
