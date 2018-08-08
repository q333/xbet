SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `xbet` DEFAULT CHARACTER SET utf8 ;
USE `xbet` ;

CREATE TABLE IF NOT EXISTS `xbet`.`teams` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `xbet`.`matches` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `team1_id` INT(11) NOT NULL,
  `team2_id` INT(11) NOT NULL,
  `1` DOUBLE NOT NULL,
  `X` DOUBLE NOT NULL,
  `2` DOUBLE NOT NULL,
  `1X` DOUBLE NOT NULL,
  `12` DOUBLE NOT NULL,
  `2X` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `team1_idx` (`team1_id` ASC),
  INDEX `team2_idx` (`team2_id` ASC),
  CONSTRAINT `team1`
    FOREIGN KEY (`team1_id`)
    REFERENCES `xbet`.`teams` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `team2`
    FOREIGN KEY (`team2_id`)
    REFERENCES `xbet`.`teams` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `xbet`.`roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `xbet`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `balance` INT(11) NOT NULL DEFAULT '0',
  `role_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  INDEX `role_id_idx` (`role_id` ASC),
  CONSTRAINT `role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `xbet`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `xbet`.`bets` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `match_id` INT(11) NOT NULL,
  `bet_result` VARCHAR(4) NOT NULL,
  `bet` DOUBLE NOT NULL,
  `money` INT(11) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `match_id_idx` (`match_id` ASC),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `match_id`
    FOREIGN KEY (`match_id`)
    REFERENCES `xbet`.`matches` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `xbet`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `xbet`.`results` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `matches_id` INT(11) NOT NULL,
  `result` VARCHAR(45) NOT NULL,
  `winner_id` INT(11) NOT NULL,
  `loser_id` INT(11) NOT NULL,
  `winner_goals` INT(11) NOT NULL,
  `loser_goals` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `matches_id_idx` (`matches_id` ASC),
  CONSTRAINT `matches_id`
    FOREIGN KEY (`matches_id`)
    REFERENCES `xbet`.`matches` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `winner_id`
  FOREIGN KEY (`winner_id`)
  REFERENCES `xbet`.`teams` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `loser_id`
  FOREIGN KEY (`loser_id`)
  REFERENCES `xbet`.`teams` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
