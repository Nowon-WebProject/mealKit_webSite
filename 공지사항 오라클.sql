-- 전체 드래그 후 실행 --

-- 테이블 생성 --
create table bbs (
bbsid number(30),
userid varchar2(30),
bbstitle varchar2(50),
bbscontent varchar2(4000),
bbsdate date default sysdate,
bbsimg varchar(30)
);
-- 자동 증가 시퀀스 --
create sequence bbs_seq increment by 1 start with 1;
-- 데이터 삽입 100개 --
BEGIN
FOR i IN 1..100 LOOP
INSERT INTO bbs VALUES (bbs_seq.nextval, '관리자', 'test','내용',default,null);
END LOOP;
COMMIT;
END;
