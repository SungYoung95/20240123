drop table member purge;

create table member(
	id			varchar2(20) primary key,
	password	varchar2(20),
	name		varchar2(20),
	role		varchar2(5)
);

select * from member;

insert into member values('admin','admin','관리자','Admin');
insert into member values('hongkd','1234','홍길동','User');