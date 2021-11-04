CREATE SCHEMA `contact_db` ;

CREATE TABLE `contact_db`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
	`firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `nid` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
 `dob` DATE NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);

INSERT INTO `contact_db`.`user` (username, password,firstname, lastname, nid, email,phone_number, dob) VALUES ('admin','1234','Ved','Rowjee', 'R080901278201F', 'vedrowjee@gmail.com','57483650','2001-9-08');

SELECT * FROM `contact_db`.`user`;