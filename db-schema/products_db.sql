-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-03-2025 a las 16:59:56
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
-- Base de datos: `products_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `brand` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `price` double NOT NULL,
  `status` enum('ACTIVE','DELETED','INACTIVE','PENDING') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `products`
--

INSERT INTO `products` (`id`, `brand`, `name`, `price`, `status`) VALUES
(1, 'philips', 'microwave', 220, 'ACTIVE'),
(2, 'Samsung', 'refrigerator', 1200, 'ACTIVE'),
(3, 'Bosch', 'dishwasher', 850, 'ACTIVE'),
(4, 'Daikin', 'air conditioner', 1500, 'ACTIVE'),
(5, 'Whirlpool', 'oven', 600, 'ACTIVE'),
(6, 'Ninja', 'blender', 100, 'ACTIVE'),
(7, 'Cuisinart', 'toaster', 50, 'ACTIVE'),
(8, 'Dyson', 'vacuum cleaner', 400, 'ACTIVE'),
(9, 'Hamilton Beach', 'electric kettle', 35, 'ACTIVE'),
(10, 'Keurig', 'coffee maker', 120, 'DELETED');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
