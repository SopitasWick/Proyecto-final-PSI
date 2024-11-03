-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mgrentaspsi
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mgrentaspsi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mgrentaspsi` DEFAULT CHARACTER SET utf8 ;
USE `mgrentaspsi` ;

-- -----------------------------------------------------
-- Table `mgrentaspsi`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mgrentaspsi`.`Cliente` (
  `idCliente` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(80) NOT NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `idCliente_UNIQUE` (`idCliente` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mgrentaspsi`.`Carrito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mgrentaspsi`.`Carrito` (
  `idCarrito` INT NOT NULL,
  `costo` FLOAT NOT NULL,
  `descripcion` VARCHAR(250) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `disponibilidad` VARCHAR(40) NOT NULL,
  `foto` LONGBLOB NOT NULL,
  PRIMARY KEY (`idCarrito`),
  UNIQUE INDEX `idCarrito_UNIQUE` (`idCarrito` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mgrentaspsi`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mgrentaspsi`.`Administrador` (
  `idAdministrador` INT NOT NULL,
  `nombre_usuario` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAdministrador`),
  UNIQUE INDEX `idAdministrador_UNIQUE` (`idAdministrador` ASC) VISIBLE,
  UNIQUE INDEX `nombre_usuario_UNIQUE` (`nombre_usuario` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mgrentaspsi`.`Transaccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mgrentaspsi`.`Transaccion` (
  `idTransaccion` INT NOT NULL,
  `nombre_transaccion` ENUM('rentar', 'comprar', 'reparar', 'crear registro', 'eliminar registro', 'actualizar registro') NOT NULL,
  `idCarrito` INT NOT NULL,
  `idAdministrador` INT NOT NULL,
  `idCliente` INT NOT NULL,
  PRIMARY KEY (`idTransaccion`),
  UNIQUE INDEX `idTransaccion_UNIQUE` (`idTransaccion` ASC) VISIBLE,
  UNIQUE INDEX `idCarrito_UNIQUE` (`idCarrito` ASC) VISIBLE,
  UNIQUE INDEX `idAdministrador_UNIQUE` (`idAdministrador` ASC) VISIBLE,
  UNIQUE INDEX `idCliente_UNIQUE` (`idCliente` ASC) VISIBLE,
  CONSTRAINT `idCarrito`
    FOREIGN KEY (`idCarrito`)
    REFERENCES `mgrentaspsi`.`Carrito` (`idCarrito`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idAdministrador`
    FOREIGN KEY (`idAdministrador`)
    REFERENCES `mgrentaspsi`.`Administrador` (`idAdministrador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idCliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `mgrentaspsi`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mgrentaspsi`.`Renta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mgrentaspsi`.`Renta` (
  `idTransaccionCarrito` INT NOT NULL,
  `fechaInicial` DATE NOT NULL,
  `fechaDevolucion` DATE NOT NULL,
  UNIQUE INDEX `idTransaccionCarrito_UNIQUE` (`idTransaccionCarrito` ASC),
  CONSTRAINT `idTransaccionRenta`
    FOREIGN KEY (`idTransaccionCarrito`)
    REFERENCES `mgrentaspsi`.`Transaccion` (`idTransaccion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mgrentaspsi`.`Compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mgrentaspsi`.`Compra` (
  `idTransaccionCarrito` INT NOT NULL,
  `fecha` DATE NOT NULL,
  UNIQUE INDEX `idTransaccionCarrito_UNIQUE` (`idTransaccionCarrito` ASC),
  CONSTRAINT `idTransaccionCompra`
    FOREIGN KEY (`idTransaccionCarrito`)
    REFERENCES `mgrentaspsi`.`Transaccion` (`idTransaccion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mgrentaspsi`.`Reparacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mgrentaspsi`.`Reparacion` (
  `idTransaccionCarrito` INT NOT NULL,
  `fechaEntrega` DATE NOT NULL,
  `resumenReparacion` VARCHAR(250) NOT NULL,
  UNIQUE INDEX `Reparacion_UNIQUE` (`idTransaccionCarrito` ASC),
  CONSTRAINT `idTransaccionReparacion`
    FOREIGN KEY (`idTransaccionCarrito`)
    REFERENCES `mgrentaspsi`.`Transaccion` (`idTransaccion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
