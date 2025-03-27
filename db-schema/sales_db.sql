-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-03-2025 a las 16:59:37
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
-- Base de datos: `sales_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sale`
--

CREATE TABLE `sale` (
  `id` bigint(20) NOT NULL,
  `cart_id` bigint(20) NOT NULL,
  `client_identification` varchar(255) DEFAULT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `client_surname` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `full_price` double DEFAULT NULL,
  `payment_method` enum('BANK_TRANSFER','CASH','CREDIT_CARD','DEBIT_CARD','PAYPAL') DEFAULT NULL,
  `status` enum('ACTIVE','CANCELED','CHECKED_OUT','COMPLETED','DELETED','INACTIVE','PENDING') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sale`
--

INSERT INTO `sale` (`id`, `cart_id`, `client_identification`, `client_name`, `client_surname`, `date`, `full_price`, `payment_method`, `status`) VALUES
(1, 3, '35919455', 'Sebastian', 'Serrano', '2025-03-27 13:56:13.000000', 3400, 'CASH', 'COMPLETED'),
(2, 4, '35919455', 'Sebastian', 'Serrano', '2025-03-27 14:33:21.000000', 1900, 'CASH', 'CANCELED'),
(3, 5, '35999955', 'Miles', 'Davis', '2025-03-27 15:26:17.000000', 2270, 'DEBIT_CARD', 'COMPLETED'),
(4, 6, '27999955', 'Frank', 'Zappa', '2025-03-27 15:27:30.000000', 3400, 'CREDIT_CARD', 'COMPLETED'),
(5, 7, '99249519', 'Tom', 'Yorke', '2025-03-27 15:28:40.000000', 100, 'CREDIT_CARD', 'DELETED'),
(6, 8, '12345678', 'Paul', 'Mccartney', '2025-03-27 15:30:10.000000', 15100, 'CASH', 'COMPLETED');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `sale`
--
ALTER TABLE `sale`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
