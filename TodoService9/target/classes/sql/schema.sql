# Todo Scheme v1.0
# Written by seongwuk.park@gmail.com

DROP DATABASE IF EXISTS `todo`;

CREATE DATABASE IF NOT EXISTS `todo`
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

CREATE USER IF NOT EXISTS 'master'@'%' IDENTIFIED BY 'Test1234';

GRANT ALL PRIVILEGES ON todo.* To 'master'@'%';

USE `todo`;

DROP TABLE IF EXISTS `todo`;
DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `role_privileges`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `privileges`;

DROP TABLE IF EXISTS `oauth_approvals`;
DROP TABLE IF EXISTS `oauth_code`;
DROP TABLE IF EXISTS `oauth_refresh_token`;
DROP TABLE IF EXISTS `oauth_access_token`;
DROP TABLE IF EXISTS `oauth_client_token`;
DROP TABLE IF EXISTS `oauth_client_details`;

--
-- Table structure for table `oauth_client_details`
--
CREATE TABLE IF NOT EXISTS `oauth_client_details` (
  `client_id`               VARCHAR(255) PRIMARY KEY,
  `resource_ids`            VARCHAR(255),
  `client_secret`           VARCHAR(255),
  `scope`                   VARCHAR(255),
  `authorized_grant_types`  VARCHAR(255),
  `web_server_redirect_uri` VARCHAR(255),
  `authorities`             VARCHAR(255),
  `access_token_validity`   INTEGER,
  `refresh_token_validity`  INTEGER,
  `additional_information`  VARCHAR(4096),
  `autoapprove`             VARCHAR(255)
) ENGINE = `MyISAM` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table `oauth_client_token`
--
CREATE TABLE IF NOT EXISTS `oauth_client_token` (
  `token_id`                VARCHAR(255),
  `token`                   BLOB,
  `authentication_id`       VARCHAR(255) PRIMARY KEY,
  `user_name`               VARCHAR(255),
  `client_id`               VARCHAR(255)
) ENGINE = `MyISAM` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table `oauth_access_token`
--
CREATE TABLE IF NOT EXISTS `oauth_access_token` (
  `token_id`                VARCHAR(255),
  `token`                   BLOB,
  `authentication_id`       VARCHAR(255) PRIMARY KEY,
  `user_name`               VARCHAR(255),
  `client_id`               VARCHAR(255),
  `authentication`          BLOB,
  `refresh_token`           VARCHAR(255)
) ENGINE = `MyISAM` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table `oauth_refresh_token`
--
CREATE TABLE IF NOT EXISTS `oauth_refresh_token` (
  `token_id`                VARCHAR(255),
  `token`                   BLOB,
  `authentication`          BLOB
) ENGINE = `MyISAM` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table `oauth_code`
--
CREATE TABLE IF NOT EXISTS `oauth_code` (
  `code`                    VARCHAR(255),
  `authentication`          BLOB
) ENGINE = `MyISAM` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table `oauth_approvals`
--
CREATE TABLE IF NOT EXISTS `oauth_approvals` (
  `userid`                  VARCHAR(255),
  `clientid`                VARCHAR(255),
  `scope`                   VARCHAR(255),
  `status`                  VARCHAR(10),
  `expiresat`               TIMESTAMP DEFAULT NULL,
  `lastmodifiedat`          TIMESTAMP DEFAULT NULL
) ENGINE = `MyISAM` DEFAULT CHARACTER SET = `utf8`;

SET @client_id = '762f6bbb-a257-11e9-9b39-0242ac120002';
SET @client_secret = 'c16b2a8b36678a7440caeda356534ef2fa75699098bb7d58d499541024e53a51';
SET @resource_ids = 'todo-server-rest-api';
SET @authorized_grant_types = 'password,authorization_code,refresh_token,client_credentials';
SET @scope = 'read,write';
SET @authorities = 'ROLE_GUEST';
SET @access_token_validity = 3600;
SET @refresh_token_validity = 86400;

INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `authorities`, `access_token_validity`, `refresh_token_validity`, `autoapprove`)
  VALUES (@client_id, @resource_ids, @client_secret, @scope, @authorized_grant_types, @authorities, @access_token_validity, @refresh_token_validity, true);

--
-- Table structure for table `privileges`
--
CREATE TABLE IF NOT EXISTS `privileges` (
  `id`                  VARCHAR(255) NOT NULL,
  `name`                VARCHAR(255) NOT NULL,
  `description`         VARCHAR(255) DEFAULT NULL,

  PRIMARY KEY (`id`),

  UNIQUE(`name`)
) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table `roles`
--
CREATE TABLE IF NOT EXISTS `roles` (
  `id`                  VARCHAR(255) NOT NULL,
  `name`                VARCHAR(255) NOT NULL,
  `description`         VARCHAR(255) DEFAULT NULL,

  PRIMARY KEY (`id`),

  UNIQUE(`name`)
) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table `role_privileges`
--
CREATE TABLE IF NOT EXISTS `role_privileges` (
  `role_id`             VARCHAR(255) NOT NULL,
  `privilege_id`        VARCHAR(255) NOT NULL,

  UNIQUE (`role_id`, `privilege_id`),

  FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),

  FOREIGN KEY (`privilege_id`) REFERENCES `privileges` (`id`)
) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table `users`
--
CREATE TABLE IF NOT EXISTS `users` (
  `id`                  CHAR(36),
  `username`            VARCHAR(255) NOT NULL,
  `password`            VARCHAR(255) NOT NULL,
  `first_name`          VARCHAR(255) NOT NULL,
  `last_name`           VARCHAR(255) NOT NULL,
  `email`               VARCHAR(255) NOT NULL,
  `created`             DATETIME,
  `updated`             DATETIME,

  PRIMARY KEY (`id`),

  UNIQUE(`username`)
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
-- Table structure for table `todo`
--
CREATE TABLE IF NOT EXISTS `todo` (
  `id`                  CHAR(36),
  `user_id`             CHAR(36) NOT NULL,
  `content`             MEDIUMTEXT NOT NULL,
  `completed`           ENUM("Y", "N") NOT NULL DEFAULT "N",
  `created`             DATETIME,
  `updated`             DATETIME,

  PRIMARY KEY (`id`),

  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

INSERT INTO `privileges` VALUE
  ('read_todo', 'READ_TODO', NULL)
, ('write_todo', 'WRITE_TODO', NULL)
, ('read_user', 'READ_USER', NULL)
, ('write_user', 'WRITE_USER', NULL)
;

INSERT INTO `roles` VALUE
  ('role_admin', 'ROLE_ADMIN', NULL)
, ('role_user', 'ROLE_USER', NULL)
, ('role_guest', 'ROLE_GUEST', NULL)
;

INSERT INTO `role_privileges` VALUE
  ('role_admin', 'read_todo')
, ('role_admin', 'write_todo')
, ('role_admin', 'read_user')
, ('role_admin', 'write_user')
, ('role_user', 'read_todo')
, ('role_user', 'write_todo')
, ('role_user', 'read_user')
, ('role_guest', 'read_todo')
;

SET @admin = uuid();

INSERT INTO `users`
  SELECT @admin, 'admin', 'Test1234', 'Steve', 'Park', 'admin@bitcamp.co.kr', NOW(), NOW() FROM DUAL;

INSERT INTO `user_roles`
  SELECT @admin, 'role_admin' FROM DUAL;

SET @user = uuid();

INSERT INTO `users`
  SELECT @user, 'steve', 'Test1234', 'Steve', 'Park', 'steve@bitcamp.co.kr', NOW(), NOW() FROM DUAL;

INSERT INTO `user_roles`
  SELECT @user, 'role_user' FROM DUAL;
