-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-03-2025 a las 16:59:45
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `shopping_cart_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `shopping_cart`
--

CREATE TABLE `shopping_cart` (
  `id` bigint(20) NOT NULL,
  `full_price` double DEFAULT NULL,
  `status` enum('ACTIVE','CHECKED_OUT','COMPLETED','DELETED','INACTIVE','PENDING','CANCELED') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `shopping_cart`
--

INSERT INTO `shopping_cart` (`id`, `full_price`, `status`) VALUES
(2, 1320, 'DELETED'),
(3, 3400, 'COMPLETED'),
(4, 1900, 'CANCELED'),
(5, 2270, 'COMPLETED'),
(6, 3400, 'COMPLETED'),
(7, 100, 'DELETED'),
(8, 15100, 'COMPLETED');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `shopping_cart_detail`
--

CREATE TABLE `shopping_cart_detail` (
  `id` bigint(20) NOT NULL,
  `product_brand` varchar(20) NOT NULL,
  `id_product` bigint(20) NOT NULL,
  `product_name` varchar(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unit_price` double NOT NULL,
  `cart_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `shopping_cart_detail`
--

INSERT INTO `shopping_cart_detail` (`id`, `product_brand`, `id_product`, `product_name`, `quantity`, `unit_price`, `cart_id`) VALUES
(3, 'Bosch', 3, 'dishwasher', 4, 850, 3),
(4, 'Whirlpool', 5, 'oven', 3, 600, 4),
(5, 'Ninja', 6, 'blender', 1, 100, 4),
(6, 'philips', 1, 'microwave', 1, 220, 5),
(7, 'Samsung', 2, 'refrigerator', 1, 1200, 5),
(8, 'Bosch', 3, 'dishwasher', 1, 850, 5),
(9, 'Bosch', 3, 'dishwasher', 4, 850, 6),
(10, 'Cuisinart', 7, 'toaster', 2, 50, 7),
(11, 'Ninja', 6, 'blender', 1, 100, 8),
(12, 'Daikin', 4, 'air conditioner', 10, 1500, 8);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `shopping_cart`
--
ALTER TABLE `shopping_cart`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `shopping_cart_detail`
--
ALTER TABLE `shopping_cart_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK22wmajxqk34dgn36fe0fg03ya` (`cart_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `shopping_cart`
--
ALTER TABLE `shopping_cart`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `shopping_cart_detail`
--
ALTER TABLE `shopping_cart_detail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `shopping_cart_detail`
--
ALTER TABLE `shopping_cart_detail`
  ADD CONSTRAINT `FK22wmajxqk34dgn36fe0fg03ya` FOREIGN KEY (`cart_id`) REFERENCES `shopping_cart` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
