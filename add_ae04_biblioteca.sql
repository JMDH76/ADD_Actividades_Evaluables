-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-12-2021 a las 02:10:47
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `add_ae04_biblioteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `id` int(6) NOT NULL,
  `titulo` varchar(60) NOT NULL,
  `autor` varchar(50) NOT NULL,
  `anyo_nacimiento` varchar(4) NOT NULL,
  `anyo_publicacion` varchar(4) NOT NULL,
  `editorial` varchar(30) NOT NULL,
  `num_paginas` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Actividad AE04 ADD';

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`id`, `titulo`, `autor`, `anyo_nacimiento`, `anyo_publicacion`, `editorial`, `num_paginas`) VALUES
(1, 'El señor de los anillos', 'J.R.R. Tolkien', '1890', '1950', 'Minotauro', '1392'),
(2, 'El juego de Ender', 'Orson Scott Card', '1951', '1977', 'Ediciones B', '509'),
(3, 'Lazarillo de Tormes', 'Anónimo', 'N.C', '1554', 'Clásicos Populares', '150'),
(4, 'Las uvas de la ira', 'John Steinbeck', '1902', '1939', 'Alianza', '619'),
(5, 'Watchmen', 'Alan Moore', '1953', '1980', 'ECC', '416'),
(6, 'La hoguera de las vanidades', 'Tom Wolfe', '1930', '1980', 'Anagrama', '635'),
(7, 'La familia de Pascual Duarte', 'Camilo José Cela', '1916', '1942', 'Destino', '165'),
(8, 'El señor de las moscas', 'William Golding', '1911', '1972', 'Alianza', '236'),
(9, 'La ciudad de los prodigios', 'Eduardo Mendoza', '1943', '1986', 'Seix Barral', '541'),
(10, 'Ensayo sobre la ceguera', 'José Saramago', '1922', '1995', 'Santillana', '439'),
(11, 'Los surcos del azar', 'Paco Roca', '1969', '2013', 'Astiberri', '349'),
(12, 'Ghosts of Spain', 'Giles Tremlett', '1962', '2006', 'Faber & Faber', '467'),
(13, 'Sidi', 'Arturo Pérez Reverte', '1951', '2019', 'Penguin', '369'),
(14, 'Dune', 'Frank Herbert', '1920', '1965', 'Acervo', '741'),
(15, 'El regreso del Catón', 'Matilde Asensi', '1962', '2015', 'Planeta', '600'),
(16, 'Canción de sangre y oro', 'Jorge Molist', 'N.C', '2018', 'Planeta', '652'),
(17, 'Diccionario Maria Moliner', 'Maria Moliner', '1902', '1947', 'RAE', '1988'),
(18, 'Cañas y barro', 'Blasco Ibañez', '1854', '1902', 'Prometeo', '304');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
