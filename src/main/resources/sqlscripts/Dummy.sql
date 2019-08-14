########## DUMMY ############################
#########2019 08.13  ########################

########## COMPANY   #########################

INSERT INTO company VALUE ('00001','superFarm','하나님','001-2231-1232','서울시 거구장 임','010-7777-7777','02-7777-7777');

########### market admin  #####################
-- INSERT INTO marketadmin VALUE('admin01','Test1234','최고관리자','ROLE_MANAGER','010-1234-1234','2313-23123-21312',NOW(),NOW());


########## FARMER   ##########################
INSERT INTO farmer VALUE
('s001','김상우','서울시 강서구','010-0000-0001','0001-00001-00001','business'),
('s002','서강일','경기도 부평구','010-0000-0002','0001-00001-00002','individual'),
('s003','심인선','서울시 수유리','010-0000-0003','0001-00001-00003','corporation'),
('s004','하양희','서울시 마포구','010-0000-0004','0001-00001-00004','business'),
('s005','김영록','경기도 수원시','010-0000-0005','0001-00001-00005','individual'),
('s006','김수진','서울시 신도림','010-0000-0006','0001-00001-00006','business'),
('s007','안수윤','서울시 강서구','010-0000-0007','0001-00001-00007','corporation');

############ Customer   ########################
INSERT INTO Users  VALUE
('1','tester01','Test1234','테스터1','customer','1999-01-01','test01@test.com','2019-08-13:00:00:00'),
('2','tester02','Test1234','테스터2','customer','1999-02-01','test02@test.com','2019-08-13:00:00:00'),
('3','tester03','Test1234','테스터3','customer','1999-03-01','test03@test.com','2019-08-13:00:00:00'),
('4','admin01','Test1234','관리자1','customer','1999-03-01','admin01@test.com','2019-08-13:00:00:00');

INSERT INTO customer VALUE
(1,'2019-08-15','남','일반',0,null,'010-1111-2222','서울에 삼니다용'),
(2,'2018-08-15','여','일반',0,null,'010-1111-2222','서울에 삼니다용'),
(3,'2017-08-15','남','일반',0,null,'010-1111-2222','서울에 삼니다용');

INSERT INTO admins VALUE
(4,'관리자','010-7777-7777','111-11-13214');

########## 카테고리  ###############################

INSERT INTO lower_category(lower_title) VALUE
('채소'),('쌀'),('과일'),('야채'),
('생선'),('조개'),('갑각류'),('해조류'),
('특수부위'),('돼지'),('소'),('조류');

INSERT INTO upper_category(upper_title) VALUE
('농산물'),('수산물'),('축산물');

INSERT INTO upper_lower VALUE
((SELECT upper_code FROM upper_category WHERE upper_title='농산물'),(SELECT lower_code FROM lower_category WHERE lower_title='채소')),
((SELECT upper_code FROM upper_category WHERE upper_title='농산물'),(SELECT lower_code FROM lower_category WHERE lower_title='쌀')),
((SELECT upper_code FROM upper_category WHERE upper_title='농산물'),(SELECT lower_code FROM lower_category WHERE lower_title='과일')),
((SELECT upper_code FROM upper_category WHERE upper_title='농산물'),(SELECT lower_code FROM lower_category WHERE lower_title='야채')),

((SELECT upper_code FROM upper_category WHERE upper_title='수산물'),(SELECT lower_code FROM lower_category WHERE lower_title='해조류')),
((SELECT upper_code FROM upper_category WHERE upper_title='수산물'),(SELECT lower_code FROM lower_category WHERE lower_title='갑각류')),
((SELECT upper_code FROM upper_category WHERE upper_title='수산물'),(SELECT lower_code FROM lower_category WHERE lower_title='조개')),
((SELECT upper_code FROM upper_category WHERE upper_title='수산물'),(SELECT lower_code FROM lower_category WHERE lower_title='생선')),

((SELECT upper_code FROM upper_category WHERE upper_title='축산물'),(SELECT lower_code FROM lower_category WHERE lower_title='조류')),
((SELECT upper_code FROM upper_category WHERE upper_title='축산물'),(SELECT lower_code FROM lower_category WHERE lower_title='소')),
((SELECT upper_code FROM upper_category WHERE upper_title='축산물'),(SELECT lower_code FROM lower_category WHERE lower_title='돼지')),
((SELECT upper_code FROM upper_category WHERE upper_title='축산물'),(SELECT lower_code FROM lower_category WHERE lower_title='특수부위'));


########## PRODUCT ###############################
INSERT INTO product_type(product_type_name,product_unit,product_origin)
VALUE('이천쌀','Kg','경기도 이천'),('김포쌀','Kg','경기도 김포'),
('멸치','Kg','전라남도 여수'),('키조개','Kg','충청남도 태안'),
('등심스','Kg','강원도'),('제주도흑돼지','kg','강원도 고성군 어쩌구');

DESC product;
INSERT INTO product VALUE
(1,'이천쌀','1','2','1','20kg','햅쌀',1000000,'2019-06-01',909091,90909,0.1,0,0,'true','s001'),
(2,'이천쌀','1','2','1','10kg','햅쌀',2000000,'2019-06-01',909091,90909,0.1,0,0,'true','s001'),
(3,'이천쌀','1','2','1','15kg','햅쌀',800000,'2019-06-01',909091,90909,0.1,0,0,'true','s001'),
(4,'이천쌀','1','2','1','40kg','햅쌀',4000000,'2019-06-01',909091,90909,0.1,0,0,'true','s001'),

(5,'김포쌀','1','2','2','5kg','햅쌀',1000000,'2019-05-02',1909091,190909,0.1,4,2,'true','s003'),
(6,'김포쌀','1','2','2','5kg','묵은쌀',100000,'2019-05-02',1909091,190909,0.1,4,2,'true','s003'),
(7,'김포쌀','1','2','2','10kg','묵은쌀',12000000,'2019-05-02',1909091,190909,0.1,4,2,'true','s003'),
(8,'김포쌀','1','2','2','10kg','햅쌀',200000,'2019-05-02',1909091,190909,0.1,4,2,'true','s003'),
(9,'김포쌀','1','2','2','15kg','햅쌀',2500000,'2019-05-02',1909091,190909,0.1,4,2,'true','s003'),
(10,'김포쌀','1','2','2','20kg','묵은쌀',3000000,'2019-05-02',1909091,190909,0.1,4,2,'true','s003'),

(11,'멸치','2','1','3','1kg','null',23000,'2019-08-01',20000,909,0.1,200,2,'true','s001'),
(12,'멸치','2','1','3','2kg','null',33000,'2019-08-01',20000,909,0.1,200,2,'true','s001'),
(13,'멸치','2','1','3','3kg','null',43000,'2019-08-01',20000,909,0.1,200,2,'true','s001'),
(14,'멸치','2','1','3','4kg','null',53000,'2019-08-01',20000,909,0.1,200,2,'true','s001'),
(15,'멸치','2','1','3','5kg','null',63000,'2019-08-01',20000,909,0.1,200,2,'true','s001'),


(16,'키조개','2','2','4','2kg','2cm',22000,'2019-07-22',19000,2909,0.1,5,3,'true','s002'),
(17,'키조개','2','2','4','2kg','3cm',32000,'2019-07-22',19000,2909,0.1,5,3,'true','s002'),
(18,'키조개','2','2','4','2kg','4cm',42000,'2019-07-22',19000,2909,0.1,5,3,'true','s002'),
(19,'키조개','2','2','4','2kg','10cm',52000,'2019-07-22',19000,2909,0.1,5,3,'true','s002'),
(20,'키조개','2','2','4','2kg','24cm',62000,'2019-07-22',19000,2909,0.1,5,3,'true','s002'),
(21,'키조개','2','2','4','2kg','30cm',72000,'2019-07-22',19000,2909,0.1,5,3,'true','s002'),

(22,'등심','3','1','5','0.6kg',null,33000,'2019-06-22',30000,1909,0.1,6,12,'true','s003'),
(23,'등심','3','1','5','1kg',null,43000,'2019-06-22',30000,1909,0.1,6,12,'true','s003'),
(24,'등심','3','1','5','2kg',null,53000,'2019-06-22',30000,1909,0.1,6,12,'true','s003'),
(25,'등심','3','1','5','3kg',null,63000,'2019-06-22',30000,1909,0.1,6,12,'true','s003'),
(26,'등심','3','1','5','4kg',null,73000,'2019-06-22',30000,1909,0.1,6,12,'true','s003'),
(27,'등심','3','1','5','5kg',null,83000,'2019-06-22',30000,1909,0.1,6,12,'true','s003'),

(28,'삽겹살','3','2','6','1kg','냉동',33000,'2019-07-12',20000,909,0.1,7,1,'true','s002'),
(29,'삽겹살','3','2','6','1kg','냉장',33000,'2019-07-12',20000,909,0.1,7,1,'true','s002'),
(30,'목살','3','2','6','1kg','냉동',33000,'2019-07-12',20000,909,0.1,7,1,'true','s002'),
(31,'목살','3','2','6','1kg','냉장',33000,'2019-07-12',20000,909,0.1,7,1,'true','s002');

######### BOARD #########################



INSERT INTO product_board VALUE
(1,1,1,1,'정말 좋은 이천쌀 입니다.','./resource/thumbnail01.jpg',3000,0,'쌀,이천쌀,햅쌀',null,'./resource/common01.jpg','./resource/detail01.jpg',
'./resource/bottom01.jpg','여기는 product_edit_content 공간 입니다용','2019-07-31 10:15:12','false'),
(2,2,1,1,'그냥 쌀이 아닌  김포쌀 입니다.','./resource/thumbnail02.jpg',3000,0,'쌀,김포쌀,햅쌀',null,'./resource/common02.jpg','./resource/detail02.jpg',
'./resource/bottom02.jpg','여기는 product_edit_content 공간 입니다용','2019-07-31 10:15:12','true'),
(3,3,2,1,'칼슘에 좋은 며르치 ','./resource/thumbnail03.jpg',3000,0,'멸치,생선,며르치',null,'./resource/common03.jpg','./resource/detail03.jpg',
'./resource/bottom03.jpg','여기는 product_edit_content 공간 입니다용','2019-07-31 10:15:12','false'),
(4,4,2,2,'182cm 키조개','./resource/thumbnail04.jpg',3000,0,'조개,키조개,어패류',null,'./resource/common04.jpg','./resource/detail04.jpg',
'./resource/bottom04.jpg','여기는 product_edit_content 공간 입니다용','2019-07-31 10:15:12','true'),
(5,5,3,1,'정말 좋은 특수부위 입니다.','./resource/thumbnail05.jpg',3000,0,'강원도,소,한우',null,'./resource/common05.jpg','./resource/detail05.jpg',
'/resource/bottom05.jpg','여기는 product_edit_content 공간 입니다용','2019-07-31 10:15:12','true'),
(6,6,3,2,'오 정말 제주 흑돼지 원산지는 강원도스','./resource/thumbnail06.jpg',3000,0,'돼지,제주흑돼지,오',null,'./resource/common06.jpg','./resource/detail06.jpg',
'./resource/bottom06.jpg','여기는 product_edit_content 공간 입니다용','2019-07-31 10:15:12','true');


INSERT INTO review_board VALUE
(1,5,'tester01','./resource/review01.jpg','제목있음','이 등심 누가 만들었등심 ',5,'2019-07-22:10:12:11','false',null),
(2,5,'tester02','./resource/review02.jpg','새로운 레시피는','태워 먹었더니 더 맛있습니다. ',2,'2019-07-22:13:12:11','false',null),
(3,5,'tester03','./resource/review03.jpg','장사꾼이시군만요','생각 보다 싸서 놀랐어요 ',5,'2019-07-22:14:12:11','false',null),
(4,5,'tester01','./resource/review04.jpg','여기가 제목이라고 해서','ㅋㅋㅋㅋㅋㅋㅋㅋㅋ',4,'2019-07-23:11:13:11','false',null),
(5,5,'tester02','./resource/review05.jpg','ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ','10자 이상 쓰는 중 ok?',2,'2019-07-23:15:12:11','false',null),
(6,5,'tester01','./resource/review06.jpg','질문이 있습니다','맛있는데 사딸라 정도만은 안파시나요 ',3,'2019-07-24:14:12:11','false',null),
(7,5,'tester02','./resource/review07.jpg','아 그래서요??','이천쌀이 정말 맛있습니다용 ',5,'2019-07-24:17:27:11','false',null),
(8,1,'tester02','./resource/review08.jpg','아 asdasd','이천쌀이 정말 맛있습니다용 ',5,'2019-07-24:17:27:11','false',null),
(9,2,'tester01','./resource/review09.jpg','좋은 보네','이천쌀이 정말 맛있습니다용 ',5,'2019-07-24:17:27:11','false',null),
(10,3,'tester01','./resource/review10.jpg','야스야스야스','이천쌀이 정말 맛있습니다용 ',5,'2019-07-24:17:27:11','false',null),
(11,2,'tester01','./resource/review11.jpg','별점은','이천쌀이 정말 맛있습니다용 ',5,'2019-07-24:17:27:11','false',null),
(12,1,'tester02','./resource/review12.jpg','그렇다고 이렇게 늦으면','이천쌀이 정말 맛있습니다용 ',5,'2019-07-24:17:27:11','false',null),
(13,4,'tester03','./resource/review13.jpg','살만한가봐요 그래서','이천쌀이 정말 맛있습니다용 ',5,'2019-07-24:17:27:11','false',null),
(14,4,'tester01','./resource/review14.jpg','3000만큼','이천쌀이 정말 맛있습니다용 ',5,'2019-07-24:17:27:11','false',null);



INSERT INTO question_board VALUE
(1,5,'tester01','테스트 제목 입니다.','1234','이거 제품에 대해 너무 비싼거 아니에요?','2019-07-31 10:22:12','false','false',null),
(2,5,'tester02','wpahrdlqslek.','1234','당일 배송 가능 한가요?','2019-07-31 12:23:12','false','false',null),
(3,5,'tester03','zzzzzzzzzzzzzz','1234','어????????????????????? ','2019-07-31 15:23:12','false','false',null),
(4,3,'tester02','여기는asd목','1234','테스트 중입니다용 답변 부탁 드려요','2019-07-31 16:22:12','false','false',null),
(5,3,'tester03','여기도 제목 제목아자자','1234','우리 잘되나 모르겠네?','2019-07-31 17:22:12','false','false',null),
(6,3,'tester01','여기도 제목목목목','1234','리얼리 리얼리 리얼리 맛있나','2019-07-31 18:12:12','false','false',null),
(7,2,'tester02','여기도 제목 이지요','1234','ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ','2019-07-31 19:28:12','false','false',null),
(8,2,'tester03','제목 있습니다.','1234','국산 맞나요 확신 할 수 있습니까?','2019-07-31 20:31:12','false','false',null),
(9,1,'tester01','아아 제목 테스트 중입니다.','1234','배송비 빼주실수 있어요? 직거래 가능 합니다.','2019-07-31 22:23:12','false','false',null),
(10,5,'tester03','zzzzzzzzzzzzz1','1234','어????????????????????? ','2019-07-31 15:23:12','false','false',null),
(11,5,'tester01','zzzzzzzzzzzzz2','1234','어????????????????????? ','2019-07-31 15:23:12','false','false',null),
(12,5,'tester01','zzzzzzzzzzzzz3','1234','어????????????????????? ','2019-07-31 15:23:12','false','false',null),
(13,5,'tester02','zzzzzzzzzzzzz4','1234','어????????????????????? ','2019-07-31 15:23:12','false','false',null),
(14,5,'tester01','zzzzzzzzzzzzz5','1234','어????????????????????? ','2019-07-31 15:23:12','false','false',null),
(15,5,'tester02','zzzzzzzzzzzzz6','1234','어????????????????????? ','2019-07-31 15:23:12','false','false',null),
(16,5,'tester01','zzzzzzzzzzzzz7','1234','어????????????????????? ','2019-07-31 15:23:12','false','false',null),
(17,5,'tester01','zzzzzzzzzzzzz8','1234','어????????????????????? ','2019-07-31 15:23:12','false','false',null),
(18,5,'tester01','zzzzzzzzzzzzz9','1234','어????????????????????? ','2019-07-31 15:23:12','false','false',null),
(19,5,'tester02','zzzzzzzzzzzz10','1234','어????????????????????? ','2019-07-31 15:23:12','false','false',null),
(20,5,'tester03','zzzzzzzzzzzz11','1234','어????????????????????? ','2019-07-31 15:23:12','false','false',null),
(21,5,'tester02','zzzzzzzzzzzz12','1234','어????????????????????? ','2019-07-31 15:23:12','false','false',null),
(22,5,'tester01','zzzzzzzzzzzz13','1234','어????????????????????? ','2019-07-31 15:23:12','false','false',null);


INSERT INTO question_answer VALUE
(1,'2019-07-31 12:23:14','냉 무',1,'관리자','true',null),
(2,'2019-07-31 13:23:14','거 답글을 제대로 다시오',1,'tester01','false',null),
(3,'2019-07-31 14:20:12','이거참 맛있소',2,'관리자','false',null),
(4,'2019-07-31 15:34:12','안녕하시오',2,'tester02','false',null),
(5,'2019-07-31 16:36:15','그만하자 오호호호',3,'tester03','false',null),
(6,'2019-07-31 17:22:17','배고프다!!!',3,'관리자','false',null),
(7,'2019-07-31 18:41:24','안녕하오',4,'관리자','false',null),
(8,'2019-07-31 19:27:44','냉 무',5,'관리자','false',null),
(9,'2019-07-31 20:22:34','ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',6,'관리자','false',null),
(10,'2019-07-31 21:12:24','냉 무',7,'관리자','false',null),
(11,'2019-07-31 22:19:44','냉 무',8,'관리자','false',null),
(12,'2019-07-31 23:32:14','냉 무',6,'관리자','false',null),
(13,'2019-07-31 23:32:14','냉 무',7,'관리자','false',null),
(14,'2019-07-31 23:32:14','냉 무',8,'관리자','false',null),
(15,'2019-07-31 23:32:14','냉 무',9,'관리자','false',null),
(16,'2019-07-31 23:32:14','냉 무',10,'관리자','false',null),
(17,'2019-07-31 23:32:14','냉 무',11,'관리자','false',null),
(18,'2019-07-31 23:32:14','냉 무',12,'관리자','false',null),
(19,'2019-07-31 23:32:14','냉 무',13,'관리자','false',null),
(20,'2019-07-31 23:32:14','냉 무',14,'관리자','false',null),
(21,'2019-07-31 23:32:14','냉 무',15,'관리자','false',null),
(22,'2019-07-31 23:32:14','냉 무',16,'관리자','false',null),
(23,'2019-07-31 23:32:14','냉 무1',16,'관리자','false',null),
(24,'2019-07-31 23:32:14','냉 무2',16,'관리자','false',null),
(25,'2019-07-31 23:32:14','냉 무3',16,'관리자','false',null),
(26,'2019-07-31 23:32:14','냉 무4',16,'관리자','false',null),
(27,'2019-07-31 23:32:14','냉 무5',16,'관리자','false',null);


