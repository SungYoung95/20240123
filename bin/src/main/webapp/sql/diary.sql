create table diary
(
    dID        NUMBER(10) not null primary key, -- 글아이디, 전화번호(uTel)+sysdate
    dTITLE     VARCHAR2(100) not null, -- 글제목
    dFROMID    VARCHAR2(20) not null, -- 보내는사람, 사용자아이디(uID)
    dGROUPID   NUMBER(10), -- 받는 그룹 번호, 그룹 번호(dsGROUPID)
    dDATE      DATE not null, -- 작성날짜
    dCONTENT   VARCHAR2(3000) not null, -- 글내용
    dEMOTION   NUMBER(2), -- 감정, 0:즐거움 1:슬픔...
    dWEATHER   NUMBER(2), -- 날씨, 0:맑음 1:흐림...
    dCNT       NUMBER(10), -- 조회수, DEFAULT:0
    dLIKE      NUMBER(10), -- 추천수, DEFAULT:0
    dREADCHECK NUMBER(1), -- 읽음 체크, DEFAULT:0 안읽음
    dFROMCHECK NUMBER(1), -- 송신자 삭제 체크, DEFAULT:0 보유 1:삭제
    dTOCHECK   NUMBER(1) -- 수신자 삭제 체크, DEFAULT:0 보유 1:삭제
);

insert into diary(dID,dTITLE,dFROMID,dDATE,dCONTENT)
    values(11111111,'첫글','USER1',SYSDATE,'안녕하세요~ 제가 첫빠입니다ㅎ');
insert into diary(dID,dTITLE,dFROMID,dDATE,dCONTENT)
    values(22222222,'두번째글','USER2',SYSDATE,'안녕하세요~ 제가 두번째입니다ㅎ');
insert into diary(dID,dTITLE,dFROMID,dDATE,dCONTENT)
    values(33333333,'세번째글','USER3',SYSDATE,'안녕하세요~ 제가 세번째입니다ㅎ');
insert into diary(dID,dTITLE,dFROMID,dDATE,dCONTENT)
    values(44444444,'네번째글','USER1',SYSDATE,'네번째글입니다.');
    
commit;
select * from diary where did = 11111111;
select * from diary where dfromid = 'USER1';
