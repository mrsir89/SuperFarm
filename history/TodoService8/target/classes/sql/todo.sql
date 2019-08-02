USE `todo`;

DROP TABLE IF EXISTS `todo`;
DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `role_privileges`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `privileges`;

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
