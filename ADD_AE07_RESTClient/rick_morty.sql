-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-02-2022 a las 18:27:46
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
-- Base de datos: `rick_morty`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personaje`
--

CREATE TABLE `personaje` (
  `id` int(4) NOT NULL,
  `nameId` varchar(100) NOT NULL,
  `specie` varchar(50) NOT NULL,
  `gender` varchar(25) NOT NULL,
  `origin` varchar(100) NOT NULL,
  `locat` varchar(100) NOT NULL,
  `stat` varchar(25) NOT NULL,
  `imagen` varchar(200) NOT NULL,
  `chapters` varchar(2000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `personaje`
--

INSERT INTO `personaje` (`id`, `nameId`, `specie`, `gender`, `origin`, `locat`, `stat`, `imagen`, `chapters`) VALUES
(1, 'Jesus Christ', 'Human', 'Male', 'Ricks’s Story', 'Ricks’s Story', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/608.jpeg', 'S04E06 - Never Ricking Morty\n'),
(2, 'Japheth\'s Middle Son', 'Humanoid', 'Male', 'Narnia Dimension', 'Narnia Dimension', 'Dead', 'https://rickandmortyapi.com/api/character/avatar/676.jpeg', 'S05E01 - Mort Dinner Rick Andre\n'),
(3, 'Cornvelious Daniel', 'Alien', 'Male', 'unknown', 'Galactic Federation Prison', 'Dead', 'https://rickandmortyapi.com/api/character/avatar/150.jpeg', 'S03E01 - The Rickshank Rickdemption\n'),
(4, 'Rick Sanchez', 'Human', 'Male', 'Earth (C-137)', 'Citadel of Ricks', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/1.jpeg', 'S01E01 - Pilot\nS01E02 - Lawnmower Dog\nS01E03 - Anatomy Park\nS01E04 - M. Night Shaym-Aliens!\nS01E05 - Meeseeks and Destroy\nS01E06 - Rick Potion #9\nS01E07 - Raising Gazorpazorp\nS01E08 - Rixty Minutes\nS01E09 - Something Ricked This Way Comes\nS01E10 - Close Rick-counters of the Rick Kind\nS01E11 - Ricksy Business\nS02E01 - A Rickle in Time\nS02E02 - Mortynight Run\nS02E03 - Auto Erotic Assimilation\nS02E04 - Total Rickall\nS02E05 - Get Schwifty\nS02E08 - Interdimensional Cable 2: Tempting Fate\nS02E09 - Look Who\'s Purging Now\nS02E10 - The Wedding Squanchers\nS03E01 - The Rickshank Rickdemption\nS03E02 - Rickmancing the Stone\nS03E03 - Pickle Rick\nS03E04 - Vindicators 3: The Return of Worldender\nS03E05 - The Whirly Dirly Conspiracy\nS03E06 - Rest and Ricklaxation\nS03E07 - The Ricklantis Mixup\nS04E03 - One Crew Over the Crewcoo\'s Morty\nS03E08 - Morty\'s Mind Blowers\nS04E04 - Claw and Hoarder: Special Ricktim\'s Morty\nS04E05 - Rattlestar Ricklactica\nS04E06 - Never Ricking Morty\nS04E07 - Promortyus\nS04E08 - The Vat of Acid Episode\nS04E09 - Childrick of Mort\nS04E10 - Star Mort: Rickturn of the Jerri\nS05E01 - Mort Dinner Rick Andre\nS05E02 - Mortyplicity\nS05E03 - A Rickconvenient Mort\nS03E09 - The ABC\'s of Beth\nS05E04 - Rickdependence Spray\nS05E05 - Amortycan Grickfitti\nS03E10 - The Rickchurian Mortydate\nS04E01 - Edge of Tomorty: Rick, Die, Rickpeat\nS05E06 - Rick & Morty\'s Thanksploitation Spectacular\nS05E07 - Gotron Jerrysis Rickvangelion\nS04E02 - The Old Man and the Seat\nS05E08 - Rickternal Friendshine of the Spotless Mort\nS05E09 - Forgetting Sarick Mortshall\nS05E10 - Rickmurai Jack\nS02E07 - Big Trouble in Little Sanchez\nS02E06 - The Ricks Must Be Crazy\n'),
(5, 'Morty Smith', 'Human', 'Male', 'unknown', 'Citadel of Ricks', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/2.jpeg', 'S01E01 - Pilot\nS01E02 - Lawnmower Dog\nS01E03 - Anatomy Park\nS01E04 - M. Night Shaym-Aliens!\nS01E05 - Meeseeks and Destroy\nS01E06 - Rick Potion #9\nS01E07 - Raising Gazorpazorp\nS01E08 - Rixty Minutes\nS01E09 - Something Ricked This Way Comes\nS01E10 - Close Rick-counters of the Rick Kind\nS01E11 - Ricksy Business\nS02E01 - A Rickle in Time\nS02E02 - Mortynight Run\nS02E03 - Auto Erotic Assimilation\nS02E04 - Total Rickall\nS02E05 - Get Schwifty\nS02E06 - The Ricks Must Be Crazy\nS02E07 - Big Trouble in Little Sanchez\nS02E08 - Interdimensional Cable 2: Tempting Fate\nS02E09 - Look Who\'s Purging Now\nS02E10 - The Wedding Squanchers\nS03E01 - The Rickshank Rickdemption\nS03E02 - Rickmancing the Stone\nS03E03 - Pickle Rick\nS03E08 - Morty\'s Mind Blowers\nS03E09 - The ABC\'s of Beth\nS03E10 - The Rickchurian Mortydate\nS04E01 - Edge of Tomorty: Rick, Die, Rickpeat\nS03E04 - Vindicators 3: The Return of Worldender\nS04E02 - The Old Man and the Seat\nS04E03 - One Crew Over the Crewcoo\'s Morty\nS04E04 - Claw and Hoarder: Special Ricktim\'s Morty\nS03E05 - The Whirly Dirly Conspiracy\nS04E05 - Rattlestar Ricklactica\nS03E06 - Rest and Ricklaxation\nS04E06 - Never Ricking Morty\nS03E07 - The Ricklantis Mixup\nS04E07 - Promortyus\nS04E08 - The Vat of Acid Episode\nS04E09 - Childrick of Mort\nS04E10 - Star Mort: Rickturn of the Jerri\nS05E01 - Mort Dinner Rick Andre\nS05E02 - Mortyplicity\nS05E03 - A Rickconvenient Mort\nS05E04 - Rickdependence Spray\nS05E05 - Amortycan Grickfitti\nS05E06 - Rick & Morty\'s Thanksploitation Spectacular\nS05E07 - Gotron Jerrysis Rickvangelion\nS05E08 - Rickternal Friendshine of the Spotless Mort\nS05E09 - Forgetting Sarick Mortshall\nS05E10 - Rickmurai Jack\n'),
(6, 'Jessica', 'Human', 'Female', 'Earth (Replacement Dimension)', 'Earth (Replacement Dimension)', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/180.jpeg', 'S01E11 - Ricksy Business\nS02E06 - The Ricks Must Be Crazy\nS02E02 - Mortynight Run\nS03E05 - The Whirly Dirly Conspiracy\nS02E07 - Big Trouble in Little Sanchez\nS03E06 - Rest and Ricklaxation\nS03E07 - The Ricklantis Mixup\nS03E08 - Morty\'s Mind Blowers\nS03E09 - The ABC\'s of Beth\nS04E01 - Edge of Tomorty: Rick, Die, Rickpeat\nS04E08 - The Vat of Acid Episode\nS05E01 - Mort Dinner Rick Andre\n'),
(7, 'Father Bob', 'Human', 'Male', 'Earth (Replacement Dimension)', 'Earth (Replacement Dimension)', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/124.jpeg', 'S02E05 - Get Schwifty\nS03E06 - Rest and Ricklaxation\n'),
(8, 'Quick Mystery Presenter', 'Human', 'Male', 'Interdimensional Cable', 'Interdimensional Cable', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/408.jpeg', 'S01E08 - Rixty Minutes\n'),
(9, 'Hairdresser Rick', 'Human', 'Male', 'unknown', 'Citadel of Ricks', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/477.jpeg', 'S03E07 - The Ricklantis Mixup\n'),
(10, 'Stacy', 'Human', 'Female', 'Earth (Replacement Dimension)', 'Earth (Replacement Dimension)', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/332.jpeg', 'S03E06 - Rest and Ricklaxation\n'),
(11, 'Summer Smith', 'Human', 'Female', 'Earth (Replacement Dimension)', 'Earth (Replacement Dimension)', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/3.jpeg', 'S01E06 - Rick Potion #9\nS01E07 - Raising Gazorpazorp\nS01E08 - Rixty Minutes\nS01E09 - Something Ricked This Way Comes\nS01E10 - Close Rick-counters of the Rick Kind\nS01E11 - Ricksy Business\nS02E01 - A Rickle in Time\nS02E03 - Auto Erotic Assimilation\nS02E04 - Total Rickall\nS02E05 - Get Schwifty\nS02E06 - The Ricks Must Be Crazy\nS02E07 - Big Trouble in Little Sanchez\nS02E08 - Interdimensional Cable 2: Tempting Fate\nS02E09 - Look Who\'s Purging Now\nS02E10 - The Wedding Squanchers\nS03E01 - The Rickshank Rickdemption\nS03E02 - Rickmancing the Stone\nS03E03 - Pickle Rick\nS03E06 - Rest and Ricklaxation\nS03E04 - Vindicators 3: The Return of Worldender\nS03E08 - Morty\'s Mind Blowers\nS03E05 - The Whirly Dirly Conspiracy\nS04E08 - The Vat of Acid Episode\nS04E09 - Childrick of Mort\nS04E10 - Star Mort: Rickturn of the Jerri\nS05E01 - Mort Dinner Rick Andre\nS05E02 - Mortyplicity\nS05E03 - A Rickconvenient Mort\nS05E04 - Rickdependence Spray\nS05E05 - Amortycan Grickfitti\nS05E06 - Rick & Morty\'s Thanksploitation Spectacular\nS05E07 - Gotron Jerrysis Rickvangelion\nS05E08 - Rickternal Friendshine of the Spotless Mort\nS05E10 - Rickmurai Jack\nS03E09 - The ABC\'s of Beth\nS03E10 - The Rickchurian Mortydate\nS04E01 - Edge of Tomorty: Rick, Die, Rickpeat\nS04E02 - The Old Man and the Seat\nS04E03 - One Crew Over the Crewcoo\'s Morty\nS04E04 - Claw and Hoarder: Special Ricktim\'s Morty\nS04E05 - Rattlestar Ricklactica\nS04E07 - Promortyus\n'),
(12, 'Riq IV', 'Human', 'Male', 'unknown', 'Citadel of Ricks', 'Dead', 'https://rickandmortyapi.com/api/character/avatar/295.jpeg', 'S01E10 - Close Rick-counters of the Rick Kind\nS01E11 - Ricksy Business\nS03E01 - The Rickshank Rickdemption\n'),
(13, 'Crystal Poacher', 'Alien', 'Male', 'unknown', 'Forbodulon Prime', 'Dead', 'https://rickandmortyapi.com/api/character/avatar/496.jpeg', 'S04E01 - Edge of Tomorty: Rick, Die, Rickpeat\n'),
(14, 'Super Turkey', 'Humanoid', 'Male', 'Earth (Replacement Dimension)', 'Earth (Replacement Dimension)', 'Dead', 'https://rickandmortyapi.com/api/character/avatar/800.jpeg', 'S05E06 - Rick & Morty\'s Thanksploitation Spectacular\n'),
(15, 'Fido', 'Animal', 'Male', 'Snuffles\' Dream', 'Snuffles\' Dream', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/397.jpeg', 'S01E02 - Lawnmower Dog\n'),
(16, 'Kirkland Brand Mr. Meeseeks', 'Humanoid', 'Male', 'Mr. Meeseeks Box', 'Earth (Replacement Dimension)', 'unknown', 'https://rickandmortyapi.com/api/character/avatar/524.jpeg', 'S04E01 - Edge of Tomorty: Rick, Die, Rickpeat\n'),
(17, 'Beth Sanchez', 'Human', 'Female', 'Earth (C-137)', 'Rick\'s Memories', 'Dead', 'https://rickandmortyapi.com/api/character/avatar/807.jpeg', 'S05E10 - Rickmurai Jack\n'),
(18, 'Big Fat rick', 'Human', 'Male', 'unknown', 'Earth (Replacement Dimension)', 'unknown', 'https://rickandmortyapi.com/api/character/avatar/769.jpeg', 'S05E07 - Gotron Jerrysis Rickvangelion\n'),
(19, 'Scary Teacher', 'Mythological Creature', 'Male', 'Mr. Goldenfold\'s dream', 'Mr. Goldenfold\'s dream', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/396.jpeg', 'S01E02 - Lawnmower Dog\n'),
(20, '26 Years Old Morty', 'Human', 'Male', 'Citadel of Ricks', 'Citadel of Ricks', 'Dead', 'https://rickandmortyapi.com/api/character/avatar/802.jpeg', 'S05E10 - Rickmurai Jack\n'),
(21, 'Angie Flint', 'Human', 'Female', 'unknown', 'Heist-Con', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/546.jpeg', 'S04E03 - One Crew Over the Crewcoo\'s Morty\n'),
(22, 'Coat Rack Head', 'Mythological Creature', 'Male', 'Hell', 'Hell', 'unknown', 'https://rickandmortyapi.com/api/character/avatar/749.jpeg', 'S05E05 - Amortycan Grickfitti\n'),
(23, 'Japheth\'s Eldest Son', 'Humanoid', 'Male', 'Narnia Dimension', 'Narnia Dimension', 'Dead', 'https://rickandmortyapi.com/api/character/avatar/677.jpeg', 'S05E01 - Mort Dinner Rick Andre\n'),
(24, 'Morty’s Disguise', 'Human', 'Female', 'Story Train', 'Story Train', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/637.jpeg', 'S04E06 - Never Ricking Morty\n'),
(25, 'Cirque du Soleil Zumanity Member', 'Human', 'Female', 'Earth (Replacement Dimension)', 'Earth (Replacement Dimension)', 'Dead', 'https://rickandmortyapi.com/api/character/avatar/740.jpeg', 'S05E04 - Rickdependence Spray\n'),
(26, 'Vance Maximus', 'Human', 'Male', 'unknown', 'Worldender\'s lair', 'Dead', 'https://rickandmortyapi.com/api/character/avatar/375.jpeg', 'S03E04 - Vindicators 3: The Return of Worldender\n'),
(27, 'Scarecrow Jerry', 'Robot', 'Male', 'Earth (Replacement Dimension)', 'Earth (Replacement Dimension)', 'Dead', 'https://rickandmortyapi.com/api/character/avatar/689.jpeg', 'S05E02 - Mortyplicity\n'),
(28, 'Nano Doctor', 'Alien', 'Male', 'unknown', 'St. Gloopy Noops Hospital', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/456.jpeg', 'S02E08 - Interdimensional Cable 2: Tempting Fate\n'),
(29, 'Chris', 'Humanoid', 'Male', 'Rick\'s Battery Microverse', 'Rick\'s Battery Microverse', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/65.jpeg', 'S02E06 - The Ricks Must Be Crazy\n'),
(30, 'Maximums Rickimus', 'Human', 'Male', 'unknown', 'Rick\'s Memories', 'Dead', 'https://rickandmortyapi.com/api/character/avatar/215.jpeg', 'S01E10 - Close Rick-counters of the Rick Kind\nS03E01 - The Rickshank Rickdemption\nS05E10 - Rickmurai Jack\n'),
(31, 'Collector', 'Alien', 'Male', 'The Menagerie', 'The Menagerie', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/67.jpeg', 'S03E08 - Morty\'s Mind Blowers\n'),
(32, 'Davin', 'Cronenberg', 'Male', 'Earth (C-137)', 'Earth (C-137)', 'Dead', 'https://rickandmortyapi.com/api/character/avatar/394.jpeg', 'S01E01 - Pilot\nS01E06 - Rick Potion #9\n'),
(33, 'Black Rick', 'Human', 'Male', 'unknown', 'Citadel of Ricks', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/48.jpeg', 'S03E01 - The Rickshank Rickdemption\nS03E07 - The Ricklantis Mixup\n'),
(34, 'King Jellybean', 'Mythological Creature', 'Male', 'Fantasy World', 'Fantasy World', 'Dead', 'https://rickandmortyapi.com/api/character/avatar/193.jpeg', 'S01E05 - Meeseeks and Destroy\n'),
(35, 'Tammy Guetermann', 'Human', 'Female', 'Story Train', 'Story Train', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/626.jpeg', 'S04E06 - Never Ricking Morty\n'),
(36, 'Daphne', 'Alien', 'Female', 'Morglutz', 'Ferkus 9', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/718.jpeg', 'S05E03 - A Rickconvenient Mort\n'),
(37, 'Beta-Seven', 'Alien', 'unknown', 'unknown', 'unknown', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/36.jpeg', 'S02E03 - Auto Erotic Assimilation\n'),
(38, 'Key Catcher', 'Alien', 'Female', 'unknown', 'Heist-Con', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/552.jpeg', 'S04E03 - One Crew Over the Crewcoo\'s Morty\n'),
(39, 'Birdperson', 'Alien', 'Male', 'Story Train', 'Story Train', 'Alive', 'https://rickandmortyapi.com/api/character/avatar/599.jpeg', 'S04E06 - Never Ricking Morty\n');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `personaje`
--
ALTER TABLE `personaje`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `personaje`
--
ALTER TABLE `personaje`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
