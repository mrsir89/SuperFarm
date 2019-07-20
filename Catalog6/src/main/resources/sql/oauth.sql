USE `pos`;

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

INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `authorities`, `access_token_validity`, `refresh_token_validity`, `autoapprove`)
  VALUES ('762f6bbb-a257-11e9-9b39-0242ac120002', 'catalog-server-rest-api', 'c16b2a8b36678a7440caeda356534ef2fa75699098bb7d58d499541024e53a51', 'read,write', 'password,authorization_code,refresh_token,client_credentials', 'ROLE_GUEST', 86400, 2592000, true);
