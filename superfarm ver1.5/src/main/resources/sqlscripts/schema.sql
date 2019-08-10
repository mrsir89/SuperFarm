
	#### Start Project Market ver 0.1  2019.07.14
	### 데이터 베이스 생성 및 유저 생성
	DROP DATABASE IF EXISTS `market`;

	CREATE DATABASE IF NOT EXISTS `market`
	  DEFAULT CHARACTER SET utf8
	  DEFAULT COLLATE utf8_general_ci;

	DROP USER IF EXISTS `bitmaster`;

	CREATE USER IF NOT EXISTS 'bitmaster'@'%' IDENTIFIED BY 'Test1234';

	GRANT ALL PRIVILEGES ON market.* To 'bitmaster'@'%';

	USE `market`;

	DROP TABLE IF EXISTS company;

	CREATE TABLE company(
	`company_code` 		CHAR(5) PRIMARY KEY,
    `company_name` 		VARCHAR(20) NOT NULL,
    `company_owner`		VARCHAR(20) NOT NULL,
    `company_number`	VARCHAR(20) NOT NULL,
    `company_address`	VARCHAR(50) NOT NULL,
    `company_phone`		VARCHAR(20) NOT NULL,
    `company_fax`		VARCHAR(20)
    )ENGINE = 'InnoDB' DEFAULT CHARACTER SET = 'UTF8';



	########################################################

	DROP TABLE IF EXISTS oauth_client_details;
	DROP TABLE IF EXISTS oauth_client_token;
	DROP TABLE IF EXISTS oauth_refresh_token;
	DROP TABLE IF EXISTS oauth_code;
	DROP TABLE IF EXISTS ClientDetails;
	DROP TABLE IF EXISTS oauth_approvals;
	DROP TABLE IF EXISTS oauth_access_token;




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



	#### DROP TABLE  #####################
	DROP TABLE IF EXISTS `customer_roles`;
	DROP TABLE IF EXISTS `role_privileges`;
    DROP TABLE IF EXISTS `privileges`;
	DROP TABLE IF EXISTS `roles`;

    DROP TABLE IF EXISTS `grade`;
	DROP TABLE IF EXISTS `customer_contact`;
	DROP TABLE IF EXISTS `customer_loged`;
	DROP TABLE IF EXISTS `customer`;
	DROP TABLE IF EXISTS `cart`;
	DROP TABLE IF EXISTS `cart_product`;






	###### 2019.07.15
	###### CREATE TABLE SHEET customer

	CREATE TABLE IF NOT EXISTS customer(
	customer_num 			BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	customer_id 			VARCHAR(20) UNIQUE NOT NULL,
	customer_pwd 			VARCHAR(20) NOT NULL,
	customer_name 			VARCHAR(20) NOT NULL,
	customer_birth 			DATE,
	customer_gender 		CHAR(2) CHECK(customer_gender IN('남','여')),
	customer_regday 		DATETIME NOT NULL,
	customer_grade 			VARCHAR(10) NOT NULL DEFAULT '일반',
	customer_point 			INTEGER DEFAULT 0,
	customer_coupon 		JSON NULL
	) ENGINE = 'InnoDB' DEFAULT CHARACTER SET ='UTF8';
	### AUTO_INCREMENT 시작값 설정 CUSTOMER는 10000 부터 시작
	ALTER TABLE customer AUTO_INCREMENT = 10000;

    CREATE TABLE IF NOT EXISTS customer_contact(
	customer_num 				BIGINT NOT NULL PRIMARY KEY,
	customer_phone 				VARCHAR(20) NOT NULL,
	customer_addr 				VARCHAR(50) NOT NULL,
	customer_email	 			VARCHAR(50) NOT NULL UNIQUE,
	FOREIGN KEY(customer_num) REFERENCES customer (customer_num)
	) ENGINE = 'InnoDB' DEFAULT CHARACTER SET ='UTF8';

	CREATE TABLE IF NOT EXISTS customer_loged(
	customer_num 				BIGINT NOT NULL PRIMARY KEY,
	customer_lastlog 			DATETIME NOT NULL,
	FOREIGN KEY(customer_num) 	REFERENCES customer(customer_num)
	)ENGINE ='InnoDB' DEFAULT CHARACTER SET='UTF8';

	CREATE TABLE IF NOT EXISTS cart(
	cart_id 					BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	customer_id 				VARCHAR(20) NOT NULL,
	customer_num 				BIGINT NOT NULL,
	cart_product 				JSON NULL,
	cart_totalprice 			INTEGER DEFAULT 0
	)ENGINE = 'InnoDB' DEFAULT CHARACTER SET = 'UTF8';
	ALTER TABLE cart AUTO_INCREMENT = 100;

	CREATE TABLE IF NOT EXISTS cart_product(
	cart_id 				BIGINT NOT NULL,
	product_code 			BIGINT NOT NULL,
	product_name 			VARCHAR(20) NOT NULL,
	cart_count 				INT DEFAULT 1 ,
	product_option1 		VARCHAR(20),
	product_option2 		VARCHAR(20),
	product_option3 		INTEGER DEFAULT 0 NOT NULL
	)ENGINE ='InnoDB' DEFAULT CHARACTER SET ='UTF8';

	CREATE TABLE IF NOT EXISTS grade(
	customer_grade 			VARCHAR(10) NOT NULL,
	customer_discount 		INTEGER NOT NULL
	)ENGINE ='InnoDB' DEFAULT CHARACTER SET = 'UTF8';


	############### 07.23 임시 권한 수정
	## customer    customer    			- PRODUCT_READ
	##                                  - PRODUCT_BUY
	######################################################
	## admin            manger_all      - manager_all


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

	  FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_num`),
	  FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
	) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

	INSERT INTO `privileges` VALUE
	  ('product_read', 'PRODUCT_READ', NULL)
	, ('product_buy', 'PRODUCT_BUY', NULL)
	, ('manager_all', 'MANAGER_ALL', NULL)
	;

	INSERT INTO `roles` VALUE
	  ('manager_master', 'ROLE_MANAGER', NULL)
	, ('customer', 'ROLE_CUSTOMER', NULL)
	, ('guest','ROLE_GUEST',NULL)
    ;

	INSERT INTO `role_privileges` VALUE
	  ('manager_master', 'product_read')
	, ('manager_master', 'product_buy')
	, ('manager_master', 'manager_all')
	, ('customer', 'product_read')
	, ('customer', 'product_buy')
	, ('guest', 'product_read')
	;

############################################
#########     ADMIN 07.22     ##############

DROP TABLE IF EXISTS marketadmin;
DROP TABLE IF EXISTS admin_loged;
DROP TABLE IF EXISTS farmer;

CREATE TABLE IF NOT EXISTS marketadmin(
admin_id 			VARCHAR(10) PRIMARY KEY,
admin_pwd 			VARCHAR(20) NOT NULL,
admin_name			VARCHAR(10) NOT NULL,
admin_authority 	CHAR(15) NOT NULL,
admin_phone			VARCHAR(20) NOT NULL,
admin_business_num  VARCHAR(20) NOT NULL,
admin_regday		DATETIME NOT NULL,
admin_loged			DATE NOT NULL
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

CREATE TABLE IF NOT EXISTS admin_loged(
admin_id			VARCHAR(10),
admin_loged			DATETIME
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';


CREATE TABLE IF NOT EXISTS farmer(
farmer_id				VARCHAR(10) PRIMARY KEY,
farmer_name				VARCHAR(20) NOT NULL,
farmer_addr				VARCHAR(10) NOT NULL,
farmer_phone			VARCHAR(20) NOT NULL,
farmer_business_number	VARCHAR(20) NOT NULL,
farmer_business_check	VARCHAR(12) CHECK(farmer_business_check IN('business','individual','corporation'))
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';



#####################################################
######### PRODUCT ###################################
#####################################################


###    TABLES ABOUT PRODUCT    ###

DROP TABLE IF EXISTS question_answer;
DROP TABLE IF EXISTS question_board;
DROP TABLE IF EXISTS product_board;
DROP TABLE IF EXISTS review_board;

DROP TABLE IF EXISTS coupon;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_type;

DROP TABLE IF EXISTS upper_lower;
DROP TABLE IF EXISTS lower_category;
DROP TABLE IF EXISTS upper_category;


CREATE TABLE IF NOT EXISTS lower_category(
	lower_code			INTEGER PRIMARY KEY AUTO_INCREMENT,
    lower_title			VARCHAR(20)

)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

CREATE TABLE IF NOT EXISTS upper_category(
	upper_code			INTEGER PRIMARY KEY AUTO_INCREMENT,
    upper_title			VARCHAR(20)

)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

CREATE TABLE IF NOT EXISTS upper_lower(
	upper_code INTEGER NOT NULL,
    lower_code INTEGER NOT NULL UNIQUE,
    FOREIGN KEY(lower_code) REFERENCES lower_category(lower_code),
    FOREIGN KEY(upper_code) REFERENCES upper_category(upper_code)

)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

CREATE TABLE IF NOT EXISTS product_type(
	product_type_code 			BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	product_type_name 			VARCHAR(20) NOT NULL,
	product_unit 				VARCHAR(20),
	product_origin	 			VARCHAR(20)
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

CREATE TABLE IF NOT EXISTS product(
	product_code			BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_name			VARCHAR(20) NOT NULL,
    upper_code				INTEGER NOT NULL,
    lower_code				INTEGER NOT NULL,
    product_type_code		BIGINT NOT NULL,
    product_option1			VARCHAR(20) DEFAULT 'empty',
    product_option2			VARCHAR(20) DEFAULT 'empty',
    product_price			INTEGER NOT NULL,
    product_made_date		DATE,
    product_notax_price		INTEGER,
    product_taxprice		INTEGER,
    product_tax				Float DEFAULT 0.1,
    product_stock			INTEGER DEFAULT 0,
    product_total_sales		INTEGER DEFAULT 0,
    product_status			CHAR(5) DEFAULT 'true' CHECK(product_status IN('true','false')),
    FOREIGN KEY(product_type_code) REFERENCES product_type(product_type_code)

)ENGINE ='InnoDB' CHARACTER SET 'UTF8';
ALTER TABLE product AUTO_INCREMENT = 100; -- 초기값 얼마?????????

CREATE TABLE IF NOT EXISTS coupon(
	coupon_id			VARCHAR(20) PRIMARY KEY,
    coupon_name			VARCHAR(20),
    coupon_createdate	DATETIME,
    coupon_expiredate	DATETIME,
    coupon_content		VARCHAR(100),
    coupon_target		VARCHAR(20),
    coupon_discount		INTEGER
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';






###   TABLE ABOUT ORDER    ###
DROP TABLE IF EXISTS orderlist;
DROP TABLE IF EXISTS orders;
-- DROP TABLE 거래내역;  미정


CREATE TABLE IF NOT EXISTS orders(   -- order 예약어로 사용불가
	orders_code				VARCHAR(20) PRIMARY KEY,
    product_code			VARCHAR(20),
    product_name			VARCHAR(20),
    orders_sell_date		DATETIME,
    orders_release_state	VARCHAR(10),
    orders_delivery_num		INTEGER,
    orders_price			INTEGER,
    orders_way				VARCHAR(20),
    orders_count			INTEGER,
    customer_num			BIGINT
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';


CREATE TABLE IF NOT EXISTS orderlist(
	orders_code				VARCHAR(20),
	customer_num			BIGINT,
	orders_record_bill		VARCHAR(20),
    orders_sell_date		DATETIME,
    orders_record_check		VARCHAR(10)
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

##########     07.30    ##################################
########## CREATE BOARD ##################################



###########################################################

CREATE TABLE IF NOT EXISTS product_board(
product_board_num 				BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
product_code					BIGINT NOT NULL,
upper_code						INTEGER NOT NULL,
lower_code 						INTEGER NOT NULL,
product_board_title 			VARCHAR(40) NOT NULL,
product_board_thumbnail 		VARCHAR(255),
product_board_deliveryprice 	INTEGER DEFAULT 0,
product_baord_best 				INTEGER DEFAULT 0,
product_board_tag 				VARCHAR(100),
product_board_link 				VARCHAR(100),
product_board_common 			VARCHAR(255),
product_board_detail 			VARCHAR(255),
product_board_bottom 			VARCHAR(255),
product_edit_content 			VARCHAR(2000),
product_add_date				DATETIME,
product_board_status 			CHAR(5) DEFAULT 'true' CHECK(product_board_status IN('false','true')),

FOREIGN KEY(product_code) REFERENCES product(product_code)
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';



CREATE TABLE IF NOT EXISTS review_board (
review_board_num 				BIGINT PRIMARY KEY AUTO_INCREMENT,
product_board_num 				BIGINT NOT NULL,
customer_id 					VARCHAR(20) NOT NULL,
review_board_img				VARCHAR(100),
review_board_title				VARCHAR(20),
review_board_content 			VARCHAR(1000),
review_board_rating 			INTEGER CHECK(review_board_rating AND(review_board_rating <=5 )),
review_board_regdate 			DATETIME NOT NULL,
review_board_status 			CHAR(5) NOT NULL DEFAULT 'true' CHECK(review_board_status IN('false','true'))

)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

CREATE TABLE IF NOT EXISTS question_board(
question_board_num				BIGINT PRIMARY KEY AUTO_INCREMENT,
product_board_num 				BIGINT,
customer_id						VARCHAR(20),
question_board_password			char(10),
question_board_content 			VARCHAR(1000),
question_board_regdate 			DATETIME,
question_board_status 			CHAR(5) NOT NULL DEFAULT 'true' CHECK(question_board_status IN('false','true'))

)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

CREATE TABLE IF NOT EXISTS question_answer(
answer_num						BIGINT PRIMARY KEY AUTO_INCREMENT,
answer_date						DATETIME,
answer_content					VARCHAR(1000),
question_board_num				BIGINT,
answer_writer					VARCHAR(20) NOT NULL,
FOREIGN KEY(question_board_num) REFERENCES question_board(question_board_num)
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';


	############### 연 습 장 ##############################users

	SELECT * FROM marketadmin;
	SELECT *FROM customer;
    SELECT * FROM customer_contact;
    SELECT * FROM customer_loged;
	SELECT * FROM oauth_access_token;
	SELECT * FROM oauth_client_token;
	SELECT * FROM oauth_client_details;
	SELECT * FROM oauth_refresh_token;
	SELECT * FROM  oauth_code;
	SELECT * FROM  oauth_access_token;
	commit;