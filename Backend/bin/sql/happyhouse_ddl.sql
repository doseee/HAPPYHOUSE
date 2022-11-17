CREATE DATABASE  IF NOT EXISTS `happyhouse` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `happyhouse`;

CREATE TABLE IF NOT EXISTS users (
  user_id VARCHAR(16) NOT NULL,
  user_name VARCHAR(20) NOT NULL,
  user_password VARCHAR(60) NOT NULL,
  user_tel VARCHAR(20) NOT NULL,
  user_email VARCHAR(45) NOT NULL,
  user_address VARCHAR(45) NOT NULL,
  join_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (user_id))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `board` (
  `article_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `hit` int DEFAULT '0',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  KEY `board_to_users_user_id_fk` (`user_id`),
  CONSTRAINT `board_to_users_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `file_info` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `article_no` int DEFAULT NULL,
  `save_folder` varchar(45) DEFAULT NULL,
  `original_file` varchar(50) DEFAULT NULL,
  `save_file` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `file_info_to_board_article_no_fk` (`article_no`),
  CONSTRAINT `file_info_to_board_article_no_fk` FOREIGN KEY (`article_no`) REFERENCES `board` (`article_no`) on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

create view apt_info_deal 
as (
    select i.aptCode aptCode, i.dongCode, i.apartmentName apartmentName, d.sidoName sidoName, d.gugunName gugunName, i.roadName roadName, d.dongName dong, i.lng, i.lat
    from dongcode d, houseinfo i
    where d.dongCode = i.dongCode
);

--  apt
create view apt
as (
    select a.dongCode, a.sidoName sidoName, a.gugunName gugunName, a.roadName roadName, d.dealYear dealYear, d.dealMonth dealMonth, a.apartmentName apartmentName, d.floor floor, d.area area,  a.dong dong, d.dealAmount dealAmount, a.lng, a.lat
    from apt_info_deal a, housedeal d
    where a.aptCode = d.aptCode
);

CREATE TABLE aptlike (
  user_id VARCHAR(16) NOT NULL,
  like_no INT NOT NULL,
  sidoName VARCHAR(30) NOT NULL,
  gugunName VARCHAR(30) NOT NULL,
  dongName VARCHAR(30) NOT NULL,
  dongCode VARCHAR(30) NOT NULL,
  PRIMARY KEY (user_id, like_no),
  CONSTRAINT user_id
    FOREIGN KEY (user_id)
    REFERENCES users (user_id)
     ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

