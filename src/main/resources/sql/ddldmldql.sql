-- ddl


-- DROP TABLE ABANDONEDANIMAL;

CREATE TABLE ABANDONEDANIMAL (
	ABNUMBER NUMBER NOT NULL,
	SNUMBER NUMBER DEFAULT 0 NOT NULL,
	NAME VARCHAR2(30) DEFAULT '미정' NOT NULL,
	SPECIES VARCHAR2(10) DEFAULT '기타' NOT NULL,
	KIND VARCHAR2(50) NULL,
	GENDER CHAR(1) DEFAULT '-' NOT NULL,
	AGE NUMBER NULL,
	WEIGHT FLOAT NULL,
	IMGPATH VARCHAR2(300) DEFAULT 'no_image.png' NULL,
	LOCATION VARCHAR2(50) NULL,
	ADMISSIONDATE DATE NOT NULL,
	UNIQUENESS VARCHAR2(100) NULL,
	DESCRIPTION VARCHAR2(100) NULL,
	VIDEO BLOB NULL,
	ANIMALSTATE VARCHAR2(10) DEFAULT '보호' NOT NULL,
	CONSTRAINT AB_GENDER_CHECK CHECK (gender IN ('F', 'M', '-')),
	CONSTRAINT AB_SPECIES_CHECK CHECK (species IN ('개', '고양이', '기타')),
	CONSTRAINT AB_STATE_CHECK CHECK (animalstate IN ('보호','입양','임보')),
	CONSTRAINT PK_ABANDONEDANIMAL PRIMARY KEY (ABNUMBER),
	CONSTRAINT AB_CK_ABNUMBER_NOTNULL CHECK ("ABNUMBER" IS NOT NULL),
	CONSTRAINT AB_CK_SNUMBER_NOTNULL CHECK ("SNUMBER" IS NOT NULL),
	CONSTRAINT AB_CK_NAME_NOTNULL CHECK ("NAME" IS NOT NULL),
	CONSTRAINT AB_CK_SPECIES_NOTNULL CHECK ("SPECIES" IS NOT NULL),
	CONSTRAINT AB_CK_GENDER_NOTNULL CHECK ("GENDER" IS NOT NULL),
	CONSTRAINT AB_CK_ADMISSIONDATE_NOTNULL CHECK ("ADMISSIONDATE" IS NOT NULL),
	CONSTRAINT AB_CK_ANIMALSTATE_NOTNULL CHECK ("ANIMALSTATE" IS NOT NULL)
);

COMMENT ON TABLE AbandonedAnimal IS '유기동물';
COMMENT ON COLUMN AbandonedAnimal.abNumber IS '유기동물 번호';
COMMENT ON COLUMN AbandonedAnimal.sNumber IS '보호소 번호';
COMMENT ON COLUMN AbandonedAnimal.name IS '이름';
COMMENT ON COLUMN AbandonedAnimal.species IS '종';
COMMENT ON COLUMN AbandonedAnimal.kind IS '품종';
COMMENT ON COLUMN AbandonedAnimal.gender IS '성별';
COMMENT ON COLUMN AbandonedAnimal.age IS '나이';
COMMENT ON COLUMN AbandonedAnimal.weight IS '체중';
COMMENT ON COLUMN AbandonedAnimal.imgPath IS '사진';
COMMENT ON COLUMN AbandonedAnimal.location IS '지역';
COMMENT ON COLUMN AbandonedAnimal.admissionDate IS '입소 날짜';
COMMENT ON COLUMN AbandonedAnimal.uniqueness IS '특이사항';
COMMENT ON COLUMN AbandonedAnimal.description IS '소개글';
COMMENT ON COLUMN AbandonedAnimal.video IS '소개영상';
COMMENT ON COLUMN AbandonedAnimal.animalState IS '유기동물 상태';

-- DROP TABLE ADOPT;

CREATE TABLE ADOPT (
	ADNUMBER NUMBER NOT NULL,
	MNUMBER NUMBER NOT NULL,
	ABNUMBER NUMBER NOT NULL,
	RESIDENCE VARCHAR2(30) NOT NULL,
	MARITALSTATUS VARCHAR2(30) NOT NULL,
	JOB VARCHAR2(50) NOT NULL,
	STATUS VARCHAR2(10) DEFAULT '처리중' NOT NULL,
	CONSTRAINT PK_ADOPT PRIMARY KEY (ADNUMBER)
);

COMMENT ON TABLE Adopt IS '입양';
COMMENT ON COLUMN Adopt.adNumber IS '입양 번호';
COMMENT ON COLUMN Adopt.mNumber IS '회원 번호';
COMMENT ON COLUMN Adopt.abNumber IS '유기동물 번호';
COMMENT ON COLUMN Adopt.residence IS '거주지';
COMMENT ON COLUMN Adopt.maritalStatus IS '결혼 여부';
COMMENT ON COLUMN Adopt.job IS '직업';
COMMENT ON COLUMN Adopt.status IS '처리 상태';

-- DROP TABLE BOARD;

CREATE TABLE BOARD (
	BNUMBER NUMBER NOT NULL,
	MNUMBER NUMBER NOT NULL,
	KINDOFBOARD VARCHAR2(12) DEFAULT 'free' NOT NULL,
	TITLE VARCHAR2(100) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	IMGPATH VARCHAR2(300) NULL,
	VIDEO BLOB NULL,
	WRTIME DATE DEFAULT SYSDATE+0.375 NOT NULL,
	CHECKPUBLIC CHAR(1) DEFAULT 'Y' NOT NULL,
	VIEWCOUNT NUMBER DEFAULT 0 NOT NULL,
	REPLYCOUNT NUMBER DEFAULT 0 NOT NULL,
	CONSTRAINT PK_BOARD PRIMARY KEY (BNUMBER)
);

COMMENT ON TABLE Board IS '게시판';
COMMENT ON COLUMN Board.bNumber IS '글 번호';
COMMENT ON COLUMN Board.mNumber IS '회원 번호';
COMMENT ON COLUMN Board.kindOfBoard IS '게시판 종류';
COMMENT ON COLUMN Board.title IS '제목';
COMMENT ON COLUMN Board.content IS '내용';
COMMENT ON COLUMN Board.imgPath IS '사진';
COMMENT ON COLUMN Board.video IS '영상';
COMMENT ON COLUMN Board.wrTime IS '작성 시간';
COMMENT ON COLUMN Board.checkPublic IS '공개 여부';
COMMENT ON COLUMN Board.viewCount IS '조회수';
COMMENT ON COLUMN Board.replyCount IS '댓글수';

-- DROP TABLE DONATION;

CREATE TABLE DONATION (
	DNUMBER NUMBER NOT NULL,
	ABNUMBER NUMBER NOT NULL,
	MNUMBER NUMBER NOT NULL,
	DONASUM NUMBER NOT NULL,
	DONAPERIOD NUMBER NULL,
	BANK VARCHAR2(30) NOT NULL,
	ACCOUNTHOLDER VARCHAR2(100) NOT NULL,
	ACCOUNTNUMBER VARCHAR2(50) NOT NULL,
	CONSTRAINT PK_DONATION PRIMARY KEY (DNUMBER)
);

COMMENT ON TABLE Donation IS '후원';
COMMENT ON COLUMN Donation.dNumber IS '후원 번호';
COMMENT ON COLUMN Donation.abNumber IS '유기동물 번호';
COMMENT ON COLUMN Donation.mNumber IS '회원 번호';
COMMENT ON COLUMN Donation.donaSum IS '후원 금액';
COMMENT ON COLUMN Donation.donaPeriod IS '후원 기간';
COMMENT ON COLUMN Donation.bank IS '은행';
COMMENT ON COLUMN Donation.accountHolder IS '예금주';
COMMENT ON COLUMN Donation.accountNumber IS '계좌번호';

-- DROP TABLE FINDANIMALBOARD;

CREATE TABLE FINDANIMALBOARD (
	FANUMBER NUMBER NOT NULL,
	MNUMBER NUMBER NOT NULL,
	SPECIES VARCHAR2(10) DEFAULT '기타' NOT NULL,
	KIND VARCHAR2(50) NULL,
	LOCATION VARCHAR2(50) NOT NULL,
	ANIMALSTATE VARCHAR2(10) DEFAULT '실종' NOT NULL,
	IMGPATH VARCHAR2(300) DEFAULT 'no_image.png' NOT NULL,
	WRTIME DATE DEFAULT SYSDATE+0.375 NOT NULL,
	TITLE VARCHAR2(60) NOT NULL,
	CONTENT VARCHAR2(1100) NOT NULL,
	VIEWCOUNT NUMBER DEFAULT 0  NOT NULL,
	CONSTRAINT PK_FINDANIMALBOARD PRIMARY KEY (FANUMBER)
);

COMMENT ON TABLE FindAnimalBoard IS '반려동물 찾아요 게시판';
COMMENT ON COLUMN FindAnimalBoard.faNumber IS '글 번호';
COMMENT ON COLUMN FindAnimalBoard.mNumber IS '회원 번호';
COMMENT ON COLUMN FindAnimalBoard.species IS '종';
COMMENT ON COLUMN FindAnimalBoard.kind IS '품종';
COMMENT ON COLUMN FindAnimalBoard.location IS '위치';
COMMENT ON COLUMN FindAnimalBoard.animalState IS '반려동물 상태';
COMMENT ON COLUMN FindAnimalBoard.imgPath IS '사진';
COMMENT ON COLUMN FindAnimalBoard.wrTime IS '작성 시간';
COMMENT ON COLUMN FindAnimalBoard.title IS '제목';
COMMENT ON COLUMN FindAnimalBoard.content IS '내용';
COMMENT ON COLUMN FindAnimalBoard.viewCount IS '조회수';

-- DROP TABLE LOOKANIMALBOARD;

CREATE TABLE LOOKANIMALBOARD (
	LANUMBER NUMBER NOT NULL,
	MNUMBER NUMBER NOT NULL,
	SPECIES VARCHAR2(10) DEFAULT '기타' NOT NULL,
	KIND VARCHAR2(50) NULL,
	LOCATION VARCHAR2(50) NOT NULL,
	ANIMALSTATE VARCHAR2(10) DEFAULT '보호' NOT NULL,
	IMGPATH VARCHAR2(300) DEFAULT 'no_image.png' NOT NULL,
	WRTIME DATE DEFAULT SYSDATE+0.375 NOT NULL,
	TITLE VARCHAR2(60) NOT NULL,
	CONTENT VARCHAR2(1100) NOT NULL,
	VIEWCOUNT NUMBER DEFAULT 0  NOT NULL,
	CONSTRAINT PK_LOOKANIMALBOARD PRIMARY KEY (LANUMBER)
);

COMMENT ON TABLE LookAnimalBoard IS '유기동물 봤어요 게시판';
COMMENT ON COLUMN LookAnimalBoard.laNumber IS '글 번호';
COMMENT ON COLUMN LookAnimalBoard.mNumber IS '회원 번호';
COMMENT ON COLUMN LookAnimalBoard.species IS '종';
COMMENT ON COLUMN LookAnimalBoard.kind IS '품종';
COMMENT ON COLUMN LookAnimalBoard.location IS '위치';
COMMENT ON COLUMN LookAnimalBoard.animalState IS '유기동물 상태';
COMMENT ON COLUMN LookAnimalBoard.imgPath IS '사진';
COMMENT ON COLUMN LookAnimalBoard.wrTime IS '작성 시간';
COMMENT ON COLUMN LookAnimalBoard.title IS '제목';
COMMENT ON COLUMN LookAnimalBoard.content IS '내용';
COMMENT ON COLUMN LookAnimalBoard.viewCount IS '조회수';

-- DROP TABLE MEMBER;

CREATE TABLE MEMBER (
	MNUMBER NUMBER NOT NULL,
	ID VARCHAR2(50) NOT NULL,
	PW VARCHAR2(50) NOT NULL,
	NAME VARCHAR2(50) NOT NULL,
	BIRTH VARCHAR2(50) NOT NULL,
	GENDER CHAR(1) NOT NULL,
	EMAIL VARCHAR2(50) NOT NULL,
	PHONE VARCHAR2(40) NOT NULL,
	GRADE VARCHAR2(10) DEFAULT '일반' NOT NULL,
	CONSTRAINT M_GENDER_CHECK CHECK (gender IN ('M', 'F')),
	CONSTRAINT M_GRADE_CHECK CHECK (grade IN ('일반', '관리자')),
	CONSTRAINT PK_MEMBER PRIMARY KEY (MNUMBER),
	CONSTRAINT M_CK_MNUMBER_NOTNULL CHECK ("MNUMBER" IS NOT NULL),
	CONSTRAINT M_CK_ID_NOTNULL CHECK ("ID" IS NOT NULL),
	CONSTRAINT M_CK_PW_NOTNULL CHECK ("PW" IS NOT NULL),
	CONSTRAINT M_CK_NAME_NOTNULL CHECK ("NAME" IS NOT NULL),
	CONSTRAINT M_CK_BIRTH_NOTNULL CHECK ("BIRTH" IS NOT NULL),
	CONSTRAINT M_CK_GENDER_NOTNULL CHECK ("GENDER" IS NOT NULL),
	CONSTRAINT M_CK_EMAIL_NOTNULL CHECK ("EMAIL" IS NOT NULL),
	CONSTRAINT M_CK_PHONE_NOTNULL CHECK ("PHONE" IS NOT NULL),
	CONSTRAINT M_CK_GRADE_NOTNULL CHECK ("GRADE" IS NOT NULL)
);

COMMENT ON TABLE MEMBER IS '회원';
COMMENT ON COLUMN MEMBER.mNumber IS '회원 번호';
COMMENT ON COLUMN MEMBER.id IS '아이디';
COMMENT ON COLUMN MEMBER.pw IS '비밀번호';
COMMENT ON COLUMN MEMBER.name IS '회원 이름';
COMMENT ON COLUMN MEMBER.birth IS '생년월일';
COMMENT ON COLUMN MEMBER.gender IS '성별';
COMMENT ON COLUMN MEMBER.email IS '이메일';
COMMENT ON COLUMN MEMBER.phone IS '연락처';
COMMENT ON COLUMN MEMBER.grade IS '등급';

-- DROP TABLE SHELTER;

CREATE TABLE SHELTER (
    SNUMBER NUMBER NOT NULL,
    GROUPNAME VARCHAR2(30) NOT NULL,
    LOCATION VARCHAR2(30) NOT NULL,
    PHONE VARCHAR2(40) NOT NULL,
    CONSTRAINT PK_SHELTER PRIMARY KEY (SNUMBER),
    CONSTRAINT SYS_C0029968 CHECK ("SNUMBER" IS NOT NULL),
    CONSTRAINT SYS_C0029969 CHECK ("GROUPNAME" IS NOT NULL),
    CONSTRAINT SYS_C0029970 CHECK ("LOCATION" IS NOT NULL),
    CONSTRAINT SYS_C0033921 CHECK ("PHONE" IS NOT NULL)
);

COMMENT ON TABLE Shelter IS '보호소';
COMMENT ON COLUMN Shelter.sNumber IS '보호소 번호';
COMMENT ON COLUMN Shelter.groupName IS '단체명';
COMMENT ON COLUMN Shelter.location IS '지역';
COMMENT ON COLUMN Shelter.phone IS '연락처';

-- DROP TABLE BOARDREPLY;

CREATE TABLE BOARDREPLY (
    BRNUMBER NUMBER NOT NULL,
    BNUMBER NUMBER NOT NULL,
    MNUMBER NUMBER NOT NULL,
    REPLY VARCHAR2(900) NOT NULL,
    WRTIME DATE DEFAULT SYSDATE+0.375 NOT NULL,
    CONSTRAINT PK_BOARDREPLY PRIMARY KEY (BRNUMBER)
);

COMMENT ON TABLE BoardReply IS '게시판 댓글';
COMMENT ON COLUMN BoardReply.brNumber IS '댓글 번호';
COMMENT ON COLUMN BoardReply.bNumber IS '글 번호';
COMMENT ON COLUMN BoardReply.mNumber IS '회원 번호';
COMMENT ON COLUMN BoardReply.reply IS '댓글 내용';
COMMENT ON COLUMN BoardReply.wrTime IS '작성 시간';

-- DROP TABLE TEMPPET;

CREATE TABLE TEMPPET (
	TNUMBER NUMBER NOT NULL,
	ABNUMBER NUMBER NOT NULL,
	MNUMBER NUMBER NOT NULL,
	TEMPDATE DATE NOT NULL,
	TEMPPERIOD NUMBER NOT NULL,
	RESIDENCE VARCHAR2(300) NOT NULL,
	MARITALSTATUS VARCHAR2(100) NOT NULL,
	JOB VARCHAR2(100) NOT NULL,
	STATUS VARCHAR2(100) DEFAULT '처리중'  NOT NULL,
	CONSTRAINT PK_TEMPPET PRIMARY KEY (TNUMBER)
);

COMMENT ON TABLE TempPet IS '임시보호';
COMMENT ON COLUMN TempPet.tNumber IS '임시보호 번호';
COMMENT ON COLUMN TempPet.abNumber IS '유기동물 번호';
COMMENT ON COLUMN TempPet.mNumber IS '회원 번호';
COMMENT ON COLUMN TempPet.tempDate IS '임시보호 시작날짜';
COMMENT ON COLUMN TempPet.tempPeriod IS '임시보호 기간';
COMMENT ON COLUMN TempPet.residence IS '거주지';
COMMENT ON COLUMN TempPet.maritalStatus IS '결혼 여부';
COMMENT ON COLUMN TempPet.job IS '직업';
COMMENT ON COLUMN TempPet.status IS '처리 상태';




-- FK
-- ALTER TABLE Donation DROP CONSTRAINT FK_AB_TO_D;

-- cascade 추가
ALTER TABLE Donation
	ADD
		CONSTRAINT FK_AB_TO_D
		FOREIGN KEY (
			abNumber
		)
		REFERENCES AbandonedAnimal (
			abNumber
		)
        ON DELETE CASCADE;

ALTER TABLE Donation
	ADD
		CONSTRAINT FK_M_TO_D
		FOREIGN KEY (
			mNumber
		)
		REFERENCES Member (
			mNumber
		)
		ON DELETE CASCADE;

ALTER TABLE Adopt
	ADD
		CONSTRAINT FK_M_TO_AD
		FOREIGN KEY (
			mNumber
		)
		REFERENCES Member (
			mNumber
		)
		ON DELETE CASCADE;

-- cascade 추가
ALTER TABLE Adopt
	ADD
		CONSTRAINT FK_AB_TO_AD
		FOREIGN KEY (
			abNumber
		)
		REFERENCES AbandonedAnimal (
			abNumber
		)
        ON DELETE CASCADE;

-- cascade 추가
ALTER TABLE TempPet
	ADD
		CONSTRAINT FK_AB_TO_T
		FOREIGN KEY (
			abNumber
		)
		REFERENCES AbandonedAnimal (
			abNumber
		)
        ON DELETE CASCADE;

ALTER TABLE TempPet
	ADD
		CONSTRAINT FK_M_TO_T
		FOREIGN KEY (
			mNumber
		)
		REFERENCES Member (
			mNumber
		)
		ON DELETE CASCADE;

-- cascade 추가
ALTER TABLE AbandonedAnimal
	ADD
		CONSTRAINT FK_S_TO_AB
		FOREIGN KEY (
			sNumber
		)
		REFERENCES Shelter (
			sNumber
		)
        ON DELETE CASCADE;

ALTER TABLE FindAnimalBoard
	ADD
		CONSTRAINT FK_M_TO_FA
		FOREIGN KEY (
			mNumber
		)
		REFERENCES Member (
			mNumber
		)
		ON DELETE CASCADE;

ALTER TABLE LookAnimalBoard
	ADD
		CONSTRAINT FK_M_TO_LA
		FOREIGN KEY (
			mNumber
		)
		REFERENCES Member (
			mNumber
		)
		ON DELETE CASCADE;

ALTER TABLE Board
	ADD
		CONSTRAINT FK_M_TO_B
		FOREIGN KEY (
			mNumber
		)
		REFERENCES Member (
			mNumber
		)
		ON DELETE CASCADE;

ALTER TABLE BoardReply
	ADD
		CONSTRAINT FK_M_TO_BR
		FOREIGN KEY (
			mNumber
		)
		REFERENCES Member (
			mNumber
		)
		ON DELETE CASCADE;

ALTER TABLE BoardReply
	ADD
		CONSTRAINT FK_B_TO_BR
		FOREIGN KEY (
			bNumber
		)
		REFERENCES Board (
			bNumber
		)
		ON DELETE CASCADE;



-- SEQUENCE 생성
-- DROP SEQUENCE M_NUMBER_SEQ;
CREATE SEQUENCE M_NUMBER_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOCYCLE;

-- DROP SEQUENCE AB_NUMBER_SEQ;
CREATE SEQUENCE AB_NUMBER_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOCYCLE;

-- DROP SEQUENCE AD_NUMBER_SEQ;
CREATE SEQUENCE AD_NUMBER_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOCYCLE;

-- DROP SEQUENCE T_NUMBER_SEQ;
CREATE SEQUENCE T_NUMBER_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOCYCLE;

-- DROP SEQUENCE D_NUMBER_SEQ;
CREATE SEQUENCE D_NUMBER_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOCYCLE;

-- DROP SEQUENCE S_NUMBER_SEQ;
CREATE SEQUENCE S_NUMBER_SEQ
INCREMENT BY 1
START WITH 0
MINVALUE 0
NOCYCLE;

-- DROP SEQUENCE FA_NUMBER_SEQ;
CREATE SEQUENCE FA_NUMBER_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOCYCLE;

-- DROP SEQUENCE LA_NUMBER_SEQ;
CREATE SEQUENCE LA_NUMBER_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOCYCLE;

-- DROP SEQUENCE B_NUMBER_SEQ;
CREATE SEQUENCE B_NUMBER_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOCYCLE;

-- DROP SEQUENCE BR_NUMBER_SEQ;
CREATE SEQUENCE BR_NUMBER_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
NOCYCLE;



-- 트리거 생성(각 번호 자동 증가)
CREATE OR REPLACE TRIGGER M_INSERT_TRIGGER
BEFORE
INSERT ON Member
FOR EACH ROW
BEGIN
    SELECT M_NUMBER_SEQ.NEXTVAL INTO :new.mNumber FROM DUAL;
END;

CREATE OR REPLACE TRIGGER AB_INSERT_TRIGGER
BEFORE
INSERT ON AbandonedAnimal
FOR EACH ROW
BEGIN
    SELECT AB_NUMBER_SEQ.NEXTVAL INTO :new.abNumber FROM DUAL;
END;

CREATE OR REPLACE TRIGGER AD_INSERT_TRIGGER
BEFORE
INSERT ON Adopt
FOR EACH ROW
BEGIN
    SELECT AD_NUMBER_SEQ.NEXTVAL INTO :new.adNumber FROM DUAL;
END;

CREATE OR REPLACE TRIGGER T_INSERT_TRIGGER
BEFORE
INSERT ON TempPet
FOR EACH ROW
BEGIN
    SELECT T_NUMBER_SEQ.NEXTVAL INTO :new.tNumber FROM DUAL;
END;

CREATE OR REPLACE TRIGGER D_INSERT_TRIGGER
BEFORE
INSERT ON Donation
FOR EACH ROW
BEGIN
    SELECT D_NUMBER_SEQ.NEXTVAL INTO :new.dNumber FROM DUAL;
END;

CREATE OR REPLACE TRIGGER S_INSERT_TRIGGER
BEFORE
INSERT ON Shelter
FOR EACH ROW
BEGIN
    SELECT S_NUMBER_SEQ.NEXTVAL INTO :new.sNumber FROM DUAL;
END;

CREATE OR REPLACE TRIGGER FA_INSERT_TRIGGER
BEFORE
INSERT ON FindAnimalBoard
FOR EACH ROW
BEGIN
    SELECT FA_NUMBER_SEQ.NEXTVAL INTO :new.faNumber FROM DUAL;
END;

CREATE OR REPLACE TRIGGER LA_INSERT_TRIGGER
BEFORE
INSERT ON LookAnimalBoard
FOR EACH ROW
BEGIN
    SELECT LA_NUMBER_SEQ.NEXTVAL INTO :new.laNumber FROM DUAL;
END;

CREATE OR REPLACE TRIGGER B_INSERT_TRIGGER
BEFORE
INSERT ON Board
FOR EACH ROW
BEGIN
    SELECT B_NUMBER_SEQ.NEXTVAL INTO :new.bNumber FROM DUAL;
END;

CREATE OR REPLACE TRIGGER BR_INSERT_TRIGGER
BEFORE
INSERT ON BoardReply
FOR EACH ROW
BEGIN
    SELECT BR_NUMBER_SEQ.NEXTVAL INTO :new.brNumber FROM DUAL;
END;



-- DML, DQL

-- BASE SHELTER INSERT
INSERT INTO SHELTER (GROUPNAME, LOCATION, PHONE) VALUES ('없음', '없음', '없음');


-- 예시 데이터 추가
-- MEMBER INSERT
INSERT INTO MEMBER (ID, PW, NAME, BIRTH, GENDER, EMAIL, PHONE, GRADE)
VALUES ('admin', 'admin', 'admin', '1995-12-30', 'M', 'ABC@DEF.COM', '01000000000', '관리자');

INSERT INTO MEMBER (ID, PW, NAME, BIRTH, GENDER, EMAIL, PHONE)
VALUES ('aaa', '111','홍길동','2000-01-09', 'F', 'aaa@email.com', '01011111111');

INSERT INTO MEMBER (ID, PW, NAME, BIRTH, GENDER, EMAIL, PHONE)
VALUES ('bbb', '222','팀쓰리','1995-07-16', 'F', 'bbb@email.com', '01012345678');

-- ABANDONEDANIMAL INSERT
INSERT INTO ABANDONEDANIMAL (SNUMBER, NAME, SPECIES, KIND, GENDER, AGE, WEIGHT, IMGPATH, LOCATION, ADMISSIONDATE, UNIQUENESS, DESCRIPTION, ANIMALSTATE)
VALUES (0,'지구','개','불독','F',10,5.2,'no_image.png','서초구','2022-08-22','건강하고 튼튼함, 즉시 입양 가능','사람을 잘 따릅니다.  지구의 가족을 찾고 있어요', '보호');

INSERT INTO ABANDONEDANIMAL (SNUMBER, NAME, SPECIES, KIND, GENDER, AGE, WEIGHT, IMGPATH, LOCATION, ADMISSIONDATE, UNIQUENESS, DESCRIPTION, ANIMALSTATE)
VALUES (1,'하늘','고양이','러시안블루','M',1,2.2,'img.png','강남','2022-08-15','건강하지만 사람을 무서워함','아픔이 있는 아이를 사랑으로 보듬어줄 가족을 찾고 있어요', '보호');

-- FINDANIMALBOARD INSERT
INSERT INTO FINDANIMALBOARD (MNUMBER, SPECIES, KIND, LOCATION, TITLE, CONTENT)
VALUES (2, '개', '비숑', '구로', '비숑을 잃어버렸습니다ㅠㅠ!', '구로에서 산책 중 잠시 한눈 판 사이 저희 집 몽실이가 사라졌습니다ㅠㅠ');

-- SHELTER INSERT
INSERT INTO SHELTER (GROUPNAME, LOCATION, PHONE)
VALUES ('보호소1', '강남', '02-000-0000');

INSERT INTO Shelter (groupName,location,phone)
VALUES('보호소2', '성남', '010-3232-1122');

COMMIT;
