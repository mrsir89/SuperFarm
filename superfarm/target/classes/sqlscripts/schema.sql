
	#### Final Project Market

    #  2019. 07. 21######################
	### 데이터 베이스 생성 및 유저 생성


	DROP DATABASE IF EXISTS `market`;

	CREATE DATABASE IF NOT EXISTS `market`
	  DEFAULT CHARACTER SET utf8
	  DEFAULT COLLATE utf8_general_ci;

	DROP USER IF EXISTS `bitmaster`;

	CREATE USER IF NOT EXISTS 'bitmaster'@'%' IDENTIFIED BY 'Test1234';

	GRANT ALL PRIVILEGES ON market.* To 'bitmaster'@'%';

	USE `market`;


	#### DROP TABLE  #####################

	DROP TABLE IF EXISTS `customer`;
	DROP TABLE IF EXISTS `cart`;
	DROP TABLE IF EXISTS `grade`;
	DROP TABLE IF EXISTS `customer_contact`;
	DROP TABLE IF EXISTS `customer_loged`;
	DROP TABLE IF EXISTS `cart_product`;

	###### 2019.07.15
	###### CREATE TABLE SHEET customer

	CREATE TABLE IF NOT EXISTS customer(
	c_num BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	c_id VARCHAR(20) UNIQUE NOT NULL,
	c_pwd VARCHAR(20) NOT NULL,
	c_name VARCHAR(20) NOT NULL,
	c_birth INTEGER,
	c_gender CHAR(2) CHECK(c_gender IN('남','여')),
	c_regeday DATE NOT NULL,
	c_grade VARCHAR(10) NOT NULL DEFAULT '일반',
	c_point INTEGER DEFAULT 0,
	c_coupon JSON NULL
	) ENGINE = 'InnoDB' DEFAULT CHARACTER SET ='UTF8';
	### AUTO_INCREMENT 시작값 설정 CUSTOMER는 10000 부터 시작
	ALTER TABLE customer AUTO_INCREMENT = 10000;

    CREATE TABLE IF NOT EXISTS customer_contact(
	c_num BIGINT NOT NULL PRIMARY KEY,
	c_phone INTEGER NOT NULL,
	c_addr VARCHAR(50) NOT NULL,
	c_email VARCHAR(50) NOT NULL UNIQUE,
	FOREIGN KEY(c_num) REFERENCES customer (c_num)
	) ENGINE = 'InnoDB' DEFAULT CHARACTER SET ='UTF8';

	CREATE TABLE IF NOT EXISTS customer_loged(
	c_num BIGINT NOT NULL PRIMARY KEY,
	c_lastlog DATE NOT NULL,
	FOREIGN KEY(c_num) REFERENCES customer(c_num)
	)ENGINE ='InnoDB' DEFAULT CHARACTER SET='UTF8';

	CREATE TABLE IF NOT EXISTS cart(
	cart_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	c_id VARCHAR(20) NOT NULL,
	c_num BIGINT NOT NULL,
	cart_product JSON NULL,
	cart_totalprice INTEGER DEFAULT 0
	)ENGINE = 'InnoDB' DEFAULT CHARACTER SET = 'UTF8';
	ALTER TABLE cart AUTO_INCREMENT = 100;

	CREATE TABLE IF NOT EXISTS cart_product(
	cart_id BIGINT NOT NULL,
	product_code BIGINT NOT NULL,
	product_name VARCHAR(20) NOT NULL,
	cart_count INT DEFAULT 1 ,
	product_option1 VARCHAR(20),
	product_option2 VARCHAR(20),
	product_option3 INTEGER DEFAULT 0 NOT NULL
	)ENGINE ='InnoDB' DEFAULT CHARACTER SET ='UTF8';




	CREATE TABLE IF NOT EXISTS grade(
	c_grade VARCHAR(10) NOT NULL,
	c_discount INTEGER NOT NULL
	)ENGINE ='InnoDB' DEFAULT CHARACTER SET = 'UTF8';


	########## Test 용 고객 저장

-- 	INSERT INTO customer(c_id,c_pwd,c_name,c_birth,c_gender,c_regeday,c_grade,c_point,c_coupon)
-- 	VALUES ( 'dropthebit','Test1234','프로젝트',19850615,'남',NOW(),'일반',0,null);

-- 	INSERT INTO customer_contact(c_num,c_phone,c_addr,c_email)
-- 	VALUES(10000,01077775465,'서울시 강서구','ksksksk@gmail.com');
	########################################################


#########################################################################


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
)  DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table `oauth_client_token`
--
CREATE TABLE IF NOT EXISTS `oauth_client_token` (
  `token_id`                VARCHAR(255),
  `token`                   BLOB,
  `authentication_id`       VARCHAR(255) PRIMARY KEY,
  `user_name`               VARCHAR(255),
  `client_id`               VARCHAR(255)
)  DEFAULT CHARACTER SET = `utf8`;

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
)  DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table `oauth_refresh_token`
--
CREATE TABLE IF NOT EXISTS `oauth_refresh_token` (
  `token_id`                VARCHAR(255),
  `token`                   BLOB,
  `authentication`          BLOB
)  DEFAULT CHARACTER SET = `utf8`;

--
-- Table structure for table `oauth_code`
--
CREATE TABLE IF NOT EXISTS `oauth_code` (
  `code`                    VARCHAR(255),
  `authentication`          BLOB
)  DEFAULT CHARACTER SET = `utf8`;

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
)  DEFAULT CHARACTER SET = `utf8`;


	INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`,
	 `client_secret`, `scope`,
	 `authorized_grant_types`, `authorities`,
	 `access_token_validity`, `refresh_token_validity`, `autoapprove`)
	  VALUES ('762f6bbb-a257-11e9-9b39-0242ac120002', 'market-server-rest-api',
	  'c16b2a8b36678a7440caeda356534ef2fa75699098bb7d58d499541024e53a51', 'read,write',
	  'password,authorization_code,refresh_token,client_credentials', 'guest',
	  86400, 2592000, true);

      	INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`,
	 `client_secret`, `scope`,
	 `authorized_grant_types`, `authorities`,
	 `access_token_validity`, `refresh_token_validity`, `autoapprove`)
	  VALUES ('test01', 'market-server-rest-api',
	  'test01', 'read,write',
	  'password,authorization_code,refresh_token,client_credentials', 'guest',
	  86400, 2592000, true);

commit;

	###########################################################
	############ 권한 관리를 위한 테이블 현재Test중  07.18 ############
	###########################################################

	############# DROP TABLE  ################################

-- 	DROP TABLE IF EXISTS `customer_roles`;
-- 	DROP TABLE IF EXISTS `roles`;

-- 	##############################################################

-- 	## 유저의 c_num에 role의 id를 저장 하는 곳이고 하나의 유저는 여러개의 role_id를 받아서
-- 	##  여러개의 권한을 받을 수있다.

-- 	CREATE TABLE IF NOT EXISTS `roles` (
-- 	  `role_id`             VARCHAR(255) NOT NULL CHECK(role_id IN('customer_general','admin','guest')),
-- 	  `role_privilege`      VARCHAR(255) NOT NULL,
-- 	  `description`         VARCHAR(255) DEFAULT NULL

-- 	) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

-- 	CREATE TABLE IF NOT EXISTS `customer_roles` (
-- 	  `c_num`        		BIGINT NOT NULL ,
-- 	  `role_id`             VARCHAR(255) NOT NULL,

-- 	  FOREIGN KEY (`c_num`) REFERENCES `customer` (`c_num`)
-- 	) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

-- 	INSERT INTO roles VALUE
-- 	('guest','PRODUCT_READ',null),
-- 	('customer_general','PRODUCT_READ',null),
-- 	('customer_general','PRODUCT_BUY',null);

-- 	INSERT INTO customer_roles VALUE
-- 	(10002,'customer_general');



	### 임시 권한
	## customer    customer_general    - ROLE_READ
	##                                 - ROLE_WRITE
	##			   customer_vvip       - ROLE_VIPWRITE
	##                                 - ROLE_VIPREAD
	######################################################
	## admin            admin_owner    - ROLE_ADMIN_READ
	##                  admin_owner    - ROLE_ADMIN_WRITE
	## 					admin_stok     - ROLE_ADMIN_READ




	########## 권한 테스트 임시 테이블        #######################

	DROP TABLE IF EXISTS `privileges`;
	DROP TABLE IF EXISTS `roles`;
	DROP TABLE IF EXISTS `role_privileges`;
	DROP TABLE IF EXISTS `roles`;
	DROP TABLE IF EXISTS `customer_roles`;


	CREATE TABLE IF NOT EXISTS `privileges` (
	  `id`                  VARCHAR(255) NOT NULL,
	  `name`                VARCHAR(255) NOT NULL,
	  `description`         VARCHAR(255) DEFAULT NULL,

	  PRIMARY KEY (`id`),

	  UNIQUE(`name`)
	) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

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

	CREATE TABLE IF NOT EXISTS `customer_roles` (
	  `customer_id`             BIGINT NOT NULL,
	  `role_id`             	VARCHAR(255) NOT NULL,

	  UNIQUE (`customer_id`, `role_id`),

	  FOREIGN KEY (`customer_id`) REFERENCES `customer` (`c_num`),
	  FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
	) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

	INSERT INTO `privileges` VALUE
	  ('read_product', 'READ_PRODUCT', NULL)
	, ('write_product', 'WRITE_PRODUCT', NULL)
	, ('read_user', 'READ_USER', NULL)
	, ('write_user', 'WRITE_USER', NULL)
    , ('buy_product','BUY_PRODUCT',NULL)
	;

	INSERT INTO `roles` VALUE
	  ('role_admin', 'ROLE_ADMIN', NULL)
	, ('role_customer', 'ROLE_CUSTOMER', NULL)
	;

	INSERT INTO `role_privileges` VALUE
	  ('role_admin', 'read_product')
	, ('role_admin', 'write_product')
	, ('role_admin', 'read_user')
	, ('role_admin', 'write_user')
	, ('role_customer', 'read_product')
	, ('role_customer', 'buy_product')
	;
	INSERT INTO customer_roles VALUE
	(1,'role_customer');

######################################################
###                     ADMIN  생성           #########
######################################################

DROP TABLE IF  EXISTS `marketadmin`;

CREATE TABLE IF NOT EXISTS `marketadmin`(
admin_id 			VARCHAR(10) PRIMARY KEY,
admin_pwd 			VARCHAR(20) NOT NULL,
admin_name			VARCHAR(10) NOT NULL,
admin_authority 	VARCHAR(10) NOT NULL,
admin_phone			INTEGER NOT NULL,
admin_business_num	INTEGER NULL,
admn_regday			DATE
)ENGINE = 'InnoDB' DEFAULT CHARACTER SET'UTF8';

	#####################################################

	############### 연 습 장 ###################################


	SELECT *FROM customer;
    SELECT * FROM customer_roles;
	SELECT * FROM oauth_access_token;
	SELECT * FROM oauth_client_token;
	SELECT * FROM oauth_client_details;
	SELECT * FROM oauth_refresh_token;
	SELECT * FROM  oauth_code;
	SELECT * FROM  oauth_approvals;
	SELECT * FROM  oauth_access_token;

	commit;