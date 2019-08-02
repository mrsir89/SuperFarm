# POS Scheme v0.1
# Written by steve.park@pieme.co.nz

DROP DATABASE IF EXISTS `pos`;

CREATE DATABASE IF NOT EXISTS `pos`
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

DROP USER IF EXISTS `master`;

CREATE USER IF NOT EXISTS 'master'@'%' IDENTIFIED BY 'Test1234';

GRANT ALL PRIVILEGES ON pos.* To 'master'@'%';

USE `pos`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `merchant_product_composites`;
DROP TABLE IF EXISTS `merchant_products`;

DROP TABLE IF EXISTS `merchants`;

DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `role_privileges`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `privileges`;

--
-- Table structure for table 'privileges'
--
CREATE TABLE IF NOT EXISTS `privileges` (
  `id`                  VARCHAR(255) NOT NULL,
  `name`                VARCHAR(255) NOT NULL,
  `description`         VARCHAR(255) DEFAULT NULL,

  PRIMARY KEY (`id`),

  UNIQUE(`name`)
) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table 'roles'
--
CREATE TABLE IF NOT EXISTS `roles` (
  `id`                  VARCHAR(255) NOT NULL,
  `name`                VARCHAR(255) NOT NULL,
  `description`         VARCHAR(255) DEFAULT NULL,

  PRIMARY KEY (`id`),

  UNIQUE(`name`)
) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table 'role_privileges'
--
CREATE TABLE IF NOT EXISTS `role_privileges` (
  `role_id`             VARCHAR(255) NOT NULL,
  `privilege_id`        VARCHAR(255) NOT NULL,

  UNIQUE (`role_id`, `privilege_id`),

  FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),

  FOREIGN KEY (`privilege_id`) REFERENCES `privileges` (`id`)
) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table 'users'
--
CREATE TABLE IF NOT EXISTS `users`
(
  `id`                  CHAR(36) NOT NULL,
  `username`            VARCHAR(255) NOT NULL,
  `password`            VARCHAR(255) NOT NULL,
  `title`               VARCHAR(100),
  `first_name`          VARCHAR(255),
  `last_name`           VARCHAR(255),
  `company_name`        VARCHAR(255),
  `email`               VARCHAR(255) NOT NULL,
  `country_code`        CHAR(2),
  `website`             VARCHAR(255),
  `note`                TEXT,
  `addresses`           JSON,
  `phones`              JSON,
  `is_active`           ENUM('Y', 'N') NOT NULL DEFAULT 'N',
  `activated`           DATETIME,
  `created`             DATETIME,
  `updated`             DATETIME,

  PRIMARY KEY (`id`),

  UNIQUE (`username`)
) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table 'user_roles'
--
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id`             CHAR(36) NOT NULL,
  `role_id`             VARCHAR(255) NOT NULL,

  UNIQUE (`user_id`, `role_id`),

  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),

  FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table 'merchants'
--
CREATE TABLE IF NOT EXISTS `merchants`
(
  `id`                  CHAR(36) NOT NULL,
  `user_id`             CHAR(36) NOT NULL,
  `name`                VARCHAR(255) NOT NULL,
  `merchant_code`       VARCHAR(255) NOT NULL,
  `status`              ENUM ('TRIALING', 'ACTIVE', 'SUSPENDED') NOT NULL,
  `created`             DATETIME,
  `updated`             DATETIME,

  PRIMARY KEY (`id`),

  UNIQUE (`merchant_code`),

  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table 'merchant_products'
--
CREATE TABLE IF NOT EXISTS `merchant_products`
(
  `id`                  CHAR(36) NOT NULL,
  `merchant_id`         CHAR(36) NOT NULL,
  `parent_id`           CHAR(36),
  `name`                VARCHAR(255) NOT NULL,
  `handle`              VARCHAR(255) NOT NULL,
  `supply_price`        DECIMAL(15, 5),
  `markup`              DECIMAL(15, 5),
  `price`               DECIMAL(15, 5) DEFAULT 0.0,
  `price_incl_tax`      DECIMAL(15, 5) DEFAULT 0.0,
  `tax`                 DECIMAL(15, 5) DEFAULT 0.0,
  `tax_rate`            DECIMAL(6, 5) NOT NULL,
  `description`         TEXT,
  `image_url`           VARCHAR(255),
  `image_urls`          JSON,
  `has_variants`        ENUM('Y', 'N') NOT NULL DEFAULT 'N',
  `variant_options`     JSON,
  `sku`                 VARCHAR(255),
  `stock_type`          ENUM('STANDARD', 'COMPOSITE') NOT NULL DEFAULT 'STANDARD',
  `is_active`           ENUM('Y', 'N') NOT NULL DEFAULT 'Y',
  `is_deleted`          ENUM('Y', 'N') NOT NULL DEFAULT 'N',
  `created`             DATETIME,
  `deleted`             DATETIME,
  `updated`             DATETIME,

  PRIMARY KEY (`id`),

  FOREIGN KEY (`merchant_id`) REFERENCES `merchants` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE,

  FOREIGN KEY (`parent_id`) REFERENCES `merchant_products` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table 'merchant_product_composites'
--
CREATE TABLE IF NOT EXISTS `merchant_product_composites`
(
  `id`                  BIGINT NOT NULL AUTO_INCREMENT,
  `merchant_id`         CHAR(36) NOT NULL,
  `parent_id`           CHAR(36) NOT NULL,
  `product_id`          CHAR(36) NOT NULL,
  `quantity`            REAL NOT NULL DEFAULT 1.0,
  `is_active`           ENUM('Y', 'N') NOT NULL DEFAULT 'Y',
  `is_deleted`          ENUM('Y', 'N') NOT NULL DEFAULT 'N',
  `created`             DATETIME,
  `deleted`             DATETIME,
  `updated`             DATETIME,

  PRIMARY KEY (`id`),

  FOREIGN KEY (`merchant_id`) REFERENCES `merchants` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE,

  FOREIGN KEY (`parent_id`) REFERENCES `merchant_products` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE,

  FOREIGN KEY (`product_id`) REFERENCES `merchant_products` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

INSERT INTO `privileges` VALUE
  ('read_product', 'READ_PRODUCT', NULL)
, ('write_product', 'WRITE_PRODUCT', NULL)
, ('read_user', 'READ_USER', NULL)
, ('write_user', 'WRITE_USER', NULL)
;

INSERT INTO `roles` VALUE
  ('role_admin', 'ROLE_ADMIN', NULL)
, ('role_customer', 'ROLE_CUSTOMER', NULL)
, ('role_merchant', 'ROLE_MERCHANT', NULL)
;

INSERT INTO `role_privileges` VALUE
  ('role_admin', 'read_product')
, ('role_admin', 'write_product')
, ('role_admin', 'read_user')
, ('role_admin', 'write_user')
, ('role_customer', 'read_product')
, ('role_customer', 'read_user')
, ('role_merchant', 'read_product')
, ('role_merchant', 'write_product')
, ('role_merchant', 'read_user')
;

SET @admin = uuid();

INSERT INTO `users` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `created`, `updated`)
  SELECT @admin, 'admin', 'Test1234', 'Steve', 'Park', 'admin@bitcamp.co.kr', NOW(), NOW() FROM DUAL;

INSERT INTO `user_roles`
  SELECT @admin, 'role_admin' FROM DUAL;

SET @user = uuid();

INSERT INTO `users` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `created`, `updated`)
  SELECT @user, 'steve', 'Test1234', 'Steve', 'Park', 'steve@bitcamp.co.kr', NOW(), NOW() FROM DUAL;

INSERT INTO `user_roles`
  SELECT @user, 'role_customer' FROM DUAL;
