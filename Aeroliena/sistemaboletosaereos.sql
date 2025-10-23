-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 23-10-2025 a las 22:13:36
-- Versión del servidor: 9.1.0
-- Versión de PHP: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemaboletosaereos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aerolinea`
--

DROP TABLE IF EXISTS `aerolinea`;
CREATE TABLE IF NOT EXISTS `aerolinea` (
  `idaerolinea` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `pais` varchar(50) NOT NULL,
  PRIMARY KEY (`idaerolinea`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `avion`
--

DROP TABLE IF EXISTS `avion`;
CREATE TABLE IF NOT EXISTS `avion` (
  `idavion` int NOT NULL AUTO_INCREMENT,
  `modelo` varchar(50) NOT NULL,
  `capacidad` int NOT NULL,
  `idaerolinea` int NOT NULL,
  PRIMARY KEY (`idavion`),
  KEY `FKa8lvsv20mu2bwmfx5gl69kr7m` (`idaerolinea`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

DROP TABLE IF EXISTS `pago`;
CREATE TABLE IF NOT EXISTS `pago` (
  `idpago` int NOT NULL AUTO_INCREMENT,
  `idreservacion` int NOT NULL,
  `metodopago` varchar(50) NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `fechapago` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idpago`),
  KEY `FKohaq560elwi41qegaqlpu0gxy` (`idreservacion`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajero`
--

DROP TABLE IF EXISTS `pasajero`;
CREATE TABLE IF NOT EXISTS `pasajero` (
  `idpasajero` int NOT NULL AUTO_INCREMENT,
  `nombrecompleto` varchar(100) NOT NULL,
  `fechanacimiento` date NOT NULL,
  `pasaporte` varchar(20) NOT NULL,
  `preferenciaasiento` varchar(255) DEFAULT NULL,
  `idusuario` int DEFAULT NULL,
  PRIMARY KEY (`idpasajero`),
  UNIQUE KEY `pasaporte` (`pasaporte`),
  UNIQUE KEY `idusuario` (`idusuario`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reclamo`
--

DROP TABLE IF EXISTS `reclamo`;
CREATE TABLE IF NOT EXISTS `reclamo` (
  `idreclamo` int NOT NULL AUTO_INCREMENT,
  `idpasajero` int NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  `estado` varchar(20) DEFAULT 'pendiente',
  PRIMARY KEY (`idreclamo`),
  KEY `FKpsckxb60mrhjenriqgab5qutc` (`idpasajero`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservacion`
--

DROP TABLE IF EXISTS `reservacion`;
CREATE TABLE IF NOT EXISTS `reservacion` (
  `idreservacion` int NOT NULL AUTO_INCREMENT,
  `idvuelo` int NOT NULL,
  `idpasajero` int NOT NULL,
  `fechareserva` datetime DEFAULT CURRENT_TIMESTAMP,
  `estado` varchar(255) NOT NULL,
  PRIMARY KEY (`idreservacion`),
  KEY `FK6hslk5idl1co1rpga6xigyl4k` (`idpasajero`),
  KEY `FKjoojn4lx6kcjb7x9n8khp0bt1` (`idvuelo`)
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ruta`
--

DROP TABLE IF EXISTS `ruta`;
CREATE TABLE IF NOT EXISTS `ruta` (
  `idruta` int NOT NULL AUTO_INCREMENT,
  `ciudadorigen` varchar(100) NOT NULL,
  `ciudaddestino` varchar(100) NOT NULL,
  `duracion` int NOT NULL,
  PRIMARY KEY (`idruta`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tripulacion`
--

DROP TABLE IF EXISTS `tripulacion`;
CREATE TABLE IF NOT EXISTS `tripulacion` (
  `idtripulante` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `rol` varchar(50) NOT NULL,
  `idaerolinea` int NOT NULL,
  PRIMARY KEY (`idtripulante`),
  KEY `FK1v61v8pqgsiyjmxwgdx4c8gsa` (`idaerolinea`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `nombrecompleto` varchar(100) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `contrasena` varchar(200) NOT NULL,
  `rol` varchar(50) NOT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `correo` (`correo`)
) ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idusuario`, `nombrecompleto`, `correo`, `contrasena`, `rol`) VALUES
(1, 'Rodrigo Josue Joel Manuel', 'admin@correo.com', '$2a$10$tCj.HAFPPjaNBNGW8.RxXOobdwwbvSYVjZTt8Rnh/b01uDhz.Cvzm', 'administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vuelo`
--

DROP TABLE IF EXISTS `vuelo`;
CREATE TABLE IF NOT EXISTS `vuelo` (
  `idvuelo` int NOT NULL AUTO_INCREMENT,
  `idruta` int NOT NULL,
  `idavion` int NOT NULL,
  `fechasalida` date NOT NULL,
  `horasalida` time NOT NULL,
  `tarifa` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idvuelo`),
  KEY `FK5xvdu3w5rwr0fxvqjorj36sbp` (`idavion`),
  KEY `FKl3vcbbcu9c9uluqr5y987n2ay` (`idruta`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
