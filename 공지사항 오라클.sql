-- ���̺� ���� --
create table bbs (
bbsid number(30),
userid varchar2(30),
bbstitle varchar2(50),
bbscontent varchar2(4000),
bbsdate date default sysdate,
bbsimg varchar(30),
bbscount number(30) default '0'
);
commit;
-- �ڵ� ���� ������ --
create sequence bbs_seq increment by 1 start with 1;
commit;
-- ������ ���� 100�� --
BEGIN
FOR i IN 1..100 LOOP
INSERT INTO bbs VALUES (bbs_seq.nextval, '������', 'test','����',default,null,default);
END LOOP;
COMMIT;
END;
