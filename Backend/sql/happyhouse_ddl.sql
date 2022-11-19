-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema happyhouse
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema happyhouse
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `happyhouse` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `happyhouse` ;

-- -----------------------------------------------------
-- Table `happyhouse`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`users` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(60) NOT NULL,
  `user_tel` VARCHAR(20) NOT NULL,
  `user_email` VARCHAR(45) NOT NULL,
  `user_address` VARCHAR(45) NOT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `happyhouse`.`aptlike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`aptlike` (
  `like_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NOT NULL,
  `sidoName` VARCHAR(30) NOT NULL,
  `gugunName` VARCHAR(30) NOT NULL,
  `dongName` VARCHAR(30) NOT NULL,
  `dongCode` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`like_no`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `happyhouse`.`users` (`user_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `happyhouse`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`board` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT '0',
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  INDEX `board_to_users_user_id_fk` (`user_id` ASC) VISIBLE,
  CONSTRAINT `board_to_users_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `happyhouse`.`users` (`user_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `happyhouse`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`comment` (
  `comment_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NOT NULL,
  `article_no` INT NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `register_time` VARCHAR(45) NULL DEFAULT 'CURRENT_TIMESTAMP',
  `user_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`comment_no`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `article_no_idx` (`article_no` ASC) VISIBLE,
  CONSTRAINT `article_no_fk`
    FOREIGN KEY (`article_no`)
    REFERENCES `happyhouse`.`board` (`article_no`)
    ON DELETE CASCADE,
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `happyhouse`.`users` (`user_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `happyhouse`.`dongcode`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`dongcode` (
  `dongCode` VARCHAR(10) NOT NULL,
  `sidoName` VARCHAR(30) NULL DEFAULT NULL,
  `gugunName` VARCHAR(30) NULL DEFAULT NULL,
  `dongName` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`dongCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `happyhouse`.`houseinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`houseinfo` (
  `aptCode` BIGINT NOT NULL,
  `buildYear` INT NULL DEFAULT NULL,
  `roadName` VARCHAR(40) NULL DEFAULT NULL,
  `roadNameBonbun` VARCHAR(5) NULL DEFAULT NULL,
  `roadNameBubun` VARCHAR(5) NULL DEFAULT NULL,
  `roadNameSeq` VARCHAR(2) NULL DEFAULT NULL,
  `roadNameBasementCode` VARCHAR(1) NULL DEFAULT NULL,
  `roadNameCode` VARCHAR(7) NULL DEFAULT NULL,
  `dong` VARCHAR(40) NULL DEFAULT NULL,
  `bonbun` VARCHAR(4) NULL DEFAULT NULL,
  `bubun` VARCHAR(4) NULL DEFAULT NULL,
  `sigunguCode` VARCHAR(5) NULL DEFAULT NULL,
  `eubmyundongCode` VARCHAR(5) NULL DEFAULT NULL,
  `dongCode` VARCHAR(10) NULL DEFAULT NULL,
  `landCode` VARCHAR(1) NULL DEFAULT NULL,
  `apartmentName` VARCHAR(40) NULL DEFAULT NULL,
  `jibun` VARCHAR(10) NULL DEFAULT NULL,
  `lng` VARCHAR(30) NULL DEFAULT NULL,
  `lat` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`aptCode`),
  UNIQUE INDEX `UNIQUE` (`buildYear` ASC, `apartmentName` ASC, `jibun` ASC, `sigunguCode` ASC, `eubmyundongCode` ASC) INVISIBLE,
  INDEX `houseinfo_dongCode_dongcode_dongCode_fk_idx` (`dongCode` ASC) INVISIBLE,
  CONSTRAINT `houseinfo_dongCode_dongcode_dongCode_fk`
    FOREIGN KEY (`dongCode`)
    REFERENCES `happyhouse`.`dongcode` (`dongCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `happyhouse`.`housedeal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`housedeal` (
  `no` BIGINT NOT NULL,
  `dealAmount` VARCHAR(20) NULL DEFAULT NULL,
  `dealYear` INT NULL DEFAULT NULL,
  `dealMonth` INT NULL DEFAULT NULL,
  `dealDay` INT NULL DEFAULT NULL,
  `area` VARCHAR(20) NULL DEFAULT NULL,
  `floor` VARCHAR(4) NULL DEFAULT NULL,
  `cancelDealType` VARCHAR(1) NULL DEFAULT NULL,
  `aptCode` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
  INDEX `housedeal_aptCode_houseinfo_aptCode_fk_idx` (`aptCode` ASC) VISIBLE,
  CONSTRAINT `housedeal_aptCode_houseinfo_aptCode_fk`
    FOREIGN KEY (`aptCode`)
    REFERENCES `happyhouse`.`houseinfo` (`aptCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `happyhouse`.`notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`notice` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT NULL,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `happyhouse` ;

-- -----------------------------------------------------
-- Placeholder table for view `happyhouse`.`apt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`apt` (`dongCode` INT, `sidoName` INT, `gugunName` INT, `roadName` INT, `dealYear` INT, `dealMonth` INT, `apartmentName` INT, `floor` INT, `area` INT, `dong` INT, `dealAmount` INT, `lng` INT, `lat` INT);

-- -----------------------------------------------------
-- Placeholder table for view `happyhouse`.`apt_info_deal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`apt_info_deal` (`aptCode` INT, `dongCode` INT, `apartmentName` INT, `sidoName` INT, `gugunName` INT, `roadName` INT, `dong` INT, `lng` INT, `lat` INT);

-- -----------------------------------------------------
-- View `happyhouse`.`apt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `happyhouse`.`apt`;
USE `happyhouse`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`ssafy`@`%` SQL SECURITY DEFINER VIEW `happyhouse`.`apt` AS select `happyhouse`.`a`.`dongCode` AS `dongCode`,`happyhouse`.`a`.`sidoName` AS `sidoName`,`happyhouse`.`a`.`gugunName` AS `gugunName`,`happyhouse`.`a`.`roadName` AS `roadName`,`d`.`dealYear` AS `dealYear`,`d`.`dealMonth` AS `dealMonth`,`happyhouse`.`a`.`apartmentName` AS `apartmentName`,`d`.`floor` AS `floor`,`d`.`area` AS `area`,`happyhouse`.`a`.`dong` AS `dong`,`d`.`dealAmount` AS `dealAmount`,`happyhouse`.`a`.`lng` AS `lng`,`happyhouse`.`a`.`lat` AS `lat` from (`happyhouse`.`apt_info_deal` `a` join `happyhouse`.`housedeal` `d`) where (`happyhouse`.`a`.`aptCode` = `d`.`aptCode`);

-- -----------------------------------------------------
-- View `happyhouse`.`apt_info_deal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `happyhouse`.`apt_info_deal`;
USE `happyhouse`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`ssafy`@`%` SQL SECURITY DEFINER VIEW `happyhouse`.`apt_info_deal` AS select `i`.`aptCode` AS `aptCode`,`i`.`dongCode` AS `dongCode`,`i`.`apartmentName` AS `apartmentName`,`d`.`sidoName` AS `sidoName`,`d`.`gugunName` AS `gugunName`,`i`.`roadName` AS `roadName`,`d`.`dongName` AS `dong`,`i`.`lng` AS `lng`,`i`.`lat` AS `lat` from (`happyhouse`.`dongcode` `d` join `happyhouse`.`houseinfo` `i`) where (`d`.`dongCode` = `i`.`dongCode`);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
