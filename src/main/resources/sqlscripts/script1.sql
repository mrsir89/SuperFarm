
	#### Start Project Market ver 2.6  2019.08.26
	### 데이터 베이스 생성 및 유저 생성

    ##   2019.08.26
    ## orders 테이블 변화 NOTICEBOARD FAQBOARD 테이블 추가

-- 	DROP USER IF EXISTS `bitmaster`;

-- 	CREATE USER IF NOT EXISTS 'bitmaster'@'%' IDENTIFIED BY 'Test1234';

	DROP DATABASE IF EXISTS `market`;

	CREATE DATABASE IF NOT EXISTS `market`
	  DEFAULT CHARACTER SET utf8
	  DEFAULT COLLATE utf8_general_ci;

##	GRANT ALL PRIVILEGES ON market.* To 'bitmaster'@'%';


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


	###### CREATE TABLE SHEET customer

DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS farmer;



	CREATE TABLE IF NOT EXISTS users(
    user_num				BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id					VARCHAR(20) UNIQUE NOT NULL,
    user_password			VARCHAR(20) NOT NULL,
    user_name				VARCHAR(20) NOT NULL,
    user_type				VARCHAR(8) DEFAULT 'customer' CHECK(user_type IN('admin','customer')) ,
    user_regday				DATETIME,
    user_email	 			VARCHAR(50) NOT NULL UNIQUE,
    user_last_connect		DATETIME NOT NULL
    ) ENGINE = 'InnoDB' DEFAULT CHARACTER SET ='UTF8';

    ### AUTO_INCREMENT 시작값 설정 CUSTOMER는 10000 부터 시작
	ALTER TABLE users AUTO_INCREMENT = 10000;

	CREATE TABLE IF NOT EXISTS customer(
	user_num				BIGINT PRIMARY KEY,
    customer_birth 			DATE,
	customer_gender 		CHAR(2) CHECK(customer_gender IN('남','여')),
	customer_grade 			VARCHAR(10) NOT NULL DEFAULT '일반',
	customer_point 			INTEGER DEFAULT 0,
	customer_coupon 		JSON NULL,
    customer_phone 			VARCHAR(20) NOT NULL,
	customer_addr 			VARCHAR(50) NOT NULL,
	FOREIGN KEY(user_num) REFERENCES users (user_num)
	) ENGINE = 'InnoDB' DEFAULT CHARACTER SET ='UTF8';


	CREATE TABLE IF NOT EXISTS admins(
	user_num			BIGINT NOT NULL,
	admin_authority 	CHAR(15) NOT NULL,
	admin_phone			VARCHAR(20) NOT NULL,
	admin_business_num  VARCHAR(20) NOT NULL,
	FOREIGN KEY(user_num) REFERENCES users (user_num)
	)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

	CREATE TABLE IF NOT EXISTS admin_loged(
	admin_id			VARCHAR(10),
	admin_loged			DATETIME,

    FOREIGN KEY(admin_id) REFERENCES users(user_id)
	)ENGINE ='InnoDB' CHARACTER SET 'UTF8';


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

	CREATE TABLE IF NOT EXISTS `user_roles` (
	  `user_num`             	BIGINT NOT NULL,
	  `role_id`             	VARCHAR(255) NOT NULL,

	  UNIQUE (`user_num`, `role_id`),

	  FOREIGN KEY (`user_num`) REFERENCES `users` (`user_num`),
	  FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
	) ENGINE = `InnoDB` DEFAULT CHARACTER SET = `utf8`;

	INSERT INTO `privileges` VALUE
	  ('product_read', 'PRODUCT_READ', NULL)
	, ('product_buy', 'PRODUCT_BUY', NULL)
	, ('manager_all', 'MANAGER_ALL', NULL)
	;

	INSERT INTO `roles` VALUE
	  ('ADMIN', 'ROLE_ADMIN', NULL)
	, ('CUSTOMER', 'ROLE_CUSTOMER', NULL)
	, ('GUEST','ROLE_GUEST',NULL)
    ;

	INSERT INTO `role_privileges` VALUE
	  ('ADMIN', 'product_read')
	, ('ADMIN', 'product_buy')
	, ('ADMIN', 'manager_all')
	, ('CUSTOMER', 'product_read')
	, ('CUSTOMER', 'product_buy')
	, ('GUEST', 'product_read')
	;


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

## 상위 카테고리
CREATE TABLE IF NOT EXISTS lower_category(
	lower_code			INTEGER PRIMARY KEY AUTO_INCREMENT,
    lower_title			VARCHAR(20)

)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

## 하위 카테고리
CREATE TABLE IF NOT EXISTS upper_category(
	upper_code			INTEGER PRIMARY KEY AUTO_INCREMENT,
    upper_title			VARCHAR(20)

)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

## 상위 하위 카테고리 서로 참조 테이블
CREATE TABLE IF NOT EXISTS upper_lower(
	upper_code INTEGER NOT NULL,
    lower_code INTEGER NOT NULL UNIQUE,

    FOREIGN KEY(lower_code) REFERENCES lower_category(lower_code),
    FOREIGN KEY(upper_code) REFERENCES upper_category(upper_code)

)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

## 제품 정보와 같은 제품을 묶어 주는 group 처럼 묶어 주는 테이블
CREATE TABLE IF NOT EXISTS product_type(
	product_type_code 			BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	product_type_name 			VARCHAR(20) NOT NULL,
	product_unit 				VARCHAR(20),
	product_origin	 			VARCHAR(20),
    product_meterial			VARCHAR(20)
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

## 실제 생산자가 다를 경우 farmer를 등록 하는 테이블 최초에는 판매자만 등록 하면 된다
	CREATE TABLE IF NOT EXISTS farmer(
	farmer_id				VARCHAR(10) PRIMARY KEY,
	farmer_name				VARCHAR(20) NOT NULL,
	farmer_addr				VARCHAR(10) NOT NULL,
	farmer_phone			VARCHAR(20) NOT NULL,
	farmer_business_number	VARCHAR(20) NOT NULL,
	farmer_business_check	VARCHAR(12) CHECK(farmer_business_check IN('business','individual','corporation'))
	)ENGINE ='InnoDB' CHARACTER SET 'UTF8';



##카트 테이블
	CREATE TABLE IF NOT EXISTS cart(
    cart_num				BIGINT PRIMARY KEY AUTO_INCREMENT,
	user_num 				BIGINT NOT NULL,
    product_board_title		VARCHAR(40) NOT NULL,
	product_board_num		BIGINT NOT NULL,
    product_code		    BIGINT NOT NULL,
    cart_product_name		VARCHAR(20) NOT NULL,
    cart_product_option1	VARCHAR(20),
	cart_product_option2	VARCHAR(20),
    cart_product_price		DECIMAL(18,5),
    cart_product_count		INTEGER DEFAULT 1,
    cart_product_img		VARCHAR(255),
    FOREIGN KEY(user_num) REFERENCES users(user_num)

	)ENGINE ='InnoDB' DEFAULT CHARACTER SET ='UTF8';

## 쿠폰 테이블
CREATE TABLE IF NOT EXISTS coupon(
	coupon_id			VARCHAR(20) PRIMARY KEY,
    coupon_name			VARCHAR(20),
    coupon_createdate	DATETIME,
    coupon_expiredate	DATETIME,
    coupon_content		VARCHAR(100),
    coupon_target		VARCHAR(20),
    coupon_discount		INTEGER
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';


## 게시판 테이블들
###########################################################
##제품 등록 게시판 테이블
CREATE TABLE IF NOT EXISTS product_board(
product_board_num 				BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
upper_code						INTEGER NOT NULL,
lower_code 						INTEGER NOT NULL,
product_board_title 			VARCHAR(40) NOT NULL,
product_board_thumbnail 		VARCHAR(255),
product_board_deliveryprice 	INTEGER DEFAULT 0,
product_board_best 				INTEGER DEFAULT 0,
product_board_tag 				VARCHAR(100),
product_board_link 				VARCHAR(100),
product_board_common 			VARCHAR(255),
product_board_detail 			VARCHAR(255),
product_board_bottom 			VARCHAR(255),
product_edit_content 			VARCHAR(2000),
product_add_date				DATETIME,
product_board_deleted 			CHAR(5) DEFAULT 'false' CHECK(product_board_deleted IN('false','true'))

)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

--  ## productBoard와 product에 대한 연결 테이블
--  CREATE TABLE IF NOT EXISTS productBoard_product(
--  product_board_num BIGINT NOT NULL,
--  product_code	   BIGINT NOT NULL,
--
--  UNIQUE (`product_board_num`, `product_code`),
--
--  FOREIGN KEY(product_board_num) REFERENCES product_board(product_board_num),
--  FOREIGN KEY(product_code) REFERENCES product(product_code)
--
--  )ENGINE ='InnoDB' CHARACTER SET 'UTF8';
--
 ## 실제 제품 테이블
CREATE TABLE IF NOT EXISTS product(
	product_code			BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_name			VARCHAR(20) NOT NULL,
    upper_code				INTEGER NOT NULL,
    lower_code				INTEGER NOT NULL,
    product_board_num		BIGINT NOT NULL,
    product_type_code		BIGINT NOT NULL,
    product_option1			VARCHAR(20) DEFAULT 'empty',
    product_option2			VARCHAR(20) DEFAULT 'empty',
    product_price			DECIMAL(18,5) NOT NULL,
    product_made_date		DATE,
    product_notax_price		DECIMAL(18,5),
    product_taxprice		DECIMAL(17,5),
    product_tax				DECIMAL(6,3) DEFAULT 0.1,
    product_stock			INTEGER DEFAULT 0,
    product_total_sales		INTEGER DEFAULT 0,
    product_status			CHAR(5) DEFAULT 'true' CHECK(product_status IN('true','false')),
    farmer_id				VARCHAR(10),

    FOREIGN KEY(farmer_id) REFERENCES farmer(farmer_id),
    FOREIGN KEY(product_type_code) REFERENCES product_type(product_type_code),
    FOREIGN KEY(product_board_num) REFERENCES product_board(product_board_num)
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';
ALTER TABLE product AUTO_INCREMENT = 100; -- 초기값 얼마?????????

## 제품의 재고 테이블
	CREATE TABLE IF NOT EXISTS product_inventory(
    product_code 			BIGINT UNIQUE NOT NULL,
    product_stock			INTEGER DEFAULT 0,
    product_total_sales		INTEGER DEFAULT 0,
    product_refund_count	INTEGER DEFAULT 0,
    product_type_code		BIGINT,


    FOREIGN KEY(product_code) REFERENCES product(product_code),
    FOREIGN KEY(product_type_code) REFERENCES product_type(product_type_code)
)ENGINE = 'InnoDB' CHARACTER SET 'UTF8';


CREATE TABLE IF NOT EXISTS stock_log(
product_code			BIGINT PRIMARY KEY,
product_add_stock		BIGINT NOT NULL,
product_add_date		DATETIME,
product_type_code		BIGINT,


FOREIGN KEY(product_code) REFERENCES product(product_code),
FOREIGN KEY(product_type_code) REFERENCES product_type(product_type_code)
)ENGINE='InnoDB' CHARACTER SET 'UTF8';


## 리뷰 게시판 테이블
CREATE TABLE IF NOT EXISTS review_board (
review_board_num 				BIGINT PRIMARY KEY AUTO_INCREMENT,
product_board_num 				BIGINT NOT NULL,
customer_id 					VARCHAR(20) NOT NULL,
review_board_img				VARCHAR(100),
review_board_title				VARCHAR(20),
review_board_content 			VARCHAR(1000),
review_board_rating 			INTEGER DEFAULT 3 CHECK(review_board_rating AND(review_board_rating <=5 )),
review_board_regdate 			DATETIME NOT NULL,
review_board_deleted 			CHAR(5) NOT NULL DEFAULT 'false' CHECK(review_board_deleted IN('false','true')),
review_board_delete_date		DATETIME

)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

## Q&A 게시판 테이블
CREATE TABLE IF NOT EXISTS question_board(
question_board_num				BIGINT PRIMARY KEY AUTO_INCREMENT,
product_board_num 				BIGINT,
user_id							VARCHAR(20),
question_board_title			VARCHAR(50),
question_board_password			char(4),
question_board_content 			VARCHAR(1000),
question_board_regdate 			DATETIME,
question_board_status 			CHAR(5) NOT NULL DEFAULT 'false' CHECK(question_board_status IN('false','true')),
question_board_deleted 			CHAR(5) NOT NULL DEFAULT 'false' CHECK(question_board_deleted IN('false','true')),
question_board_delete_date		DATETIME NULL

)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

## Q&A 게시판 답글 테이블
CREATE TABLE IF NOT EXISTS question_answer(
answer_num						BIGINT PRIMARY KEY AUTO_INCREMENT,
answer_date						DATETIME,
answer_content					VARCHAR(1000),
question_board_num				BIGINT,
answer_writer					VARCHAR(20) NOT NULL,
answer_deleted					CHAR(5) DEFAULT 'false' CHECK(answer_deleted IN('false','true')),
answer_board_delete_date        DATETIME,
FOREIGN KEY(question_board_num) REFERENCES question_board(question_board_num)
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';


##    주문 관련 테이블
## 실제 주문 테이블
CREATE TABLE IF NOT EXISTS orders(
order_num 					BIGINT PRIMARY KEY AUTO_INCREMENT,
user_num 					BIGINT NOT NULL,
order_record_bill			VARCHAR(200),
order_payment_date			DATETIME,  -- 결제 날짜
order_sell_date				DATETIME,  -- 구매 날짜
payment_method				VARCHAR(20) NOT NULL,
order_total_price			DECIMAL(18,5) NOT NULL,
order_memo					VARCHAR(1000), -- 주문에 대한 메모,
order_payment_status		VARCHAR(10) DEFAULT '결제완료' CHECK(order_payment_Status IN('결제완료','결제취소')),

FOREIGN KEY (user_num) REFERENCES users(user_num)
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

## 주문에 대한 제품 테이블
CREATE TABLE IF NOT EXISTS order_items(
order_item_num			BIGINT PRIMARY KEY AUTO_INCREMENT,
product_code			BIGINT NOT NULL,
order_num				BIGINT NOT NULL,
order_item_group		BIGINT ,
order_item_depth		INT DEFAULT 0,
order_item_price		DECIMAL(18,5),
order_count				INTEGER DEFAULT 1,
order_item_send_status	VARCHAR(10) CHECK(order_item_send_status IN('발송완료','입고완료','입고대기','입고지연')),
order_item_status		VARCHAR(10) CHECK(order_item_status IN('배송완료','거래완료','반품접수','반품완료','교환접수','교환완료','환불접수','환불완료')),
order_shipping_memo		VARCHAR(50),

FOREIGN KEY(order_num) REFERENCES orders(order_num),
FOREIGN KEY(product_code) REFERENCES product(product_code)
)ENGINE = 'InnoDB' CHARACTER SET 'UTF8';

## 주문에 대한 배송 테이블
CREATE TABLE IF NOT EXISTS shipping(
shipping_num				BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
order_num					BIGINT NOT NULL,
tracking_number				VARCHAR(30),
shipping_courier			VARCHAR(20),
delivery_address			VARCHAR(100) NOT NULL,
shipping_method				VARCHAR(10) NOT NULL,
shipping_price				DECIMAL(10,3) NOT NULL,
shipping_status				VARCHAR(10),
shipping_arrivel_date		DATE,
shipping_reciever			VARCHAR(20) NOT NULL,
shipping_reciever_phone		VARCHAR(20) NOT NULL,
shipping_reciever_phone2	VARCHAR(20) NOT NULL,
shipping_memo				VARCHAR(100),

FOREIGN KEY(order_num) REFERENCES orders(order_num)
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';


## 배송에 대한 제품 테이블
CREATE TABLE IF NOT EXISTS shipping_item(
shipping_num			 BIGINT NOT NULL,
product_code			 BIGINT NOT NULL,
product_name			 VARCHAR(20) NOT NULL,
product_option1		     VARCHAR(20),
product_option2          VARCHAR(20),
product_price			 DECIMAL(18,5),
order_sell_date			 DATETIME,

FOREIGN KEY(shipping_num) REFERENCES shipping(shipping_num),
FOREIGN KEY(product_code) REFERENCES product(product_code)
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

## 주문에 대한 서로 참조 테이블 1:N:N (orders : orderItem : shipping)
CREATE TABLE IF NOT EXISTS orders_shipping(
order_num					BIGINT NOT NULL,
shipping_num				BIGINT NOT NULL UNIQUE,
order_item_num				BIGINT NOT NULL UNIQUE,

FOREIGN KEY(order_num) REFERENCES orders(order_num),
FOREIGN KEY(shipping_num) REFERENCES shipping(shipping_num),
FOREIGN KEY(order_item_num) REFERENCES order_items(order_item_num)
)ENGINE ='InnoDB' CHARACTER SET 'UTF8';

CREATE TABLE IF NOT EXISTS review_file(
	file_id 			LONG,
    file_origin_name 	VARCHAR(255),
    file_name			VARCHAR(255),
    file_size 			LONG,
    file_reg_dated		DATETIME,
    file_upload_ip		VARCHAR(230),
    file_content_type	VARCHAR(255),
    user_id				VARCHAR(20) NOT NULL,
    review_board_num	BIGINT NOT NULL,

    FOREIGN KEY(user_id) REFERENCES users(user_id),
    FOREIGN KEY(review_board_num) REFERENCES review_board(review_board_num)


)ENGINE = 'InnoDB' CHARACTER SET 'UTF8';


-- 날짜만 시간은 관리 용도로,
CREATE TABLE IF NOT EXISTS notice_board(
notice_num            	BIGINT PRIMARY KEY AUTO_INCREMENT,
notice_title          	VARCHAR(100) ,
notice_writer_num     	BIGINT ,
notice_writer_name    	VARCHAR(20) ,
notice_write_date      	DATETIME ,
notice_edit_date		DATETIME,
notice_content         	VARCHAR(2000) ,
notice_img            	VARCHAR(255),
notice_deleted          CHAR(5) CHECK(notice_deleted IN('true','false')),
notice_views         	BIGINT

)ENGINE = 'InnoDB' CHARACTER SET 'UTF8';

-- 날짜만 시간은 관리 용도로,
CREATE TABLE IF NOT EXISTS faq_board(
faq_board_num           BIGINT PRIMARY KEY AUTO_INCREMENT,
faq_writer_num          BIGINT ,
faq_question			VARCHAR(100),
faq_answer				VARCHAR(2000),
faq_write_date      	DATETIME ,
faq_edit_date			DATETIME,
faq_img            		VARCHAR(255),
faq_deleted          	CHAR(5) CHECK(faq_deleted IN('true','false'))

)ENGINE = 'InnoDB' CHARACTER SET 'UTF8';

CREATE TABLE IF NOT EXISTS month_selling(
month_code				BIGINT PRIMARY KEY AUTO_INCREMENT,
month_date				DATE,
month_sales				INTEGER,
month_refund			INTEGER
) ENGINE = 'InnoDB' CHARACTER SET 'UTF8';

