-- Create Database
CREATE SCHEMA `libraries` DEFAULT CHARACTER SET utf8 COLLATE utf8_turkish_ci ;

-- DELETE DATABASE
DROP DATABASE `libraries`;

-- Set
use libraries;

-- Table
CREATE TABLE `libraries`.`register` (
                                        `id` INT NOT NULL AUTO_INCREMENT,
                                        `nick_name` VARCHAR(255) NULL DEFAULT 'takma adınıız yazmadınız.',
                                        `email_address` VARCHAR(255) NULL DEFAULT 'email adres yazmadınız',
                                        `password` VARCHAR(45) NULL DEFAULT 'şifrenizi girmediniz',
                                        `remaining_number` VARCHAR(2) NULL DEFAULT '4',
                                        `is_passive` VARCHAR(2) NULL DEFAULT '0',
                                        PRIMARY KEY (`id`))
    ENGINE = InnoDB;

-- tabloyayeni kolon eklemek
ALTER TABLE `libraries`.`register`
    ADD COLUMN `system_created_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP AFTER `password`;

-- DELETE TABLE
DROP TABLE `libraries`.`register`;

-- TRUNCATE Table
TRUNCATE `libraries`.`register`;

-- INSERT
INSERT INTO `libraries`.`register` (`nick_name`, `email_address`, `password`) VALUES ('computer', 'hamitmizrak@gmail.com', '123456');

-- FIND
SELECT * FROM libraries.register where id=1;

-- UPDATE
UPDATE `libraries`.`register` SET `nick_name` = 'computer1' WHERE (`id` = '1');

-- DELETE
DELETE FROM `libraries`.`register` WHERE (`id` = '1');

-- SELECT
SELECT * FROM libraries.register;
select count(*) from `libraries`.`register`;

-- LIKE --
