-- ��ü �巡�� �� ���� --

-- ���̺� ���� --
create table bbs (
bbsid number(30),
userid varchar2(30),
bbstitle varchar2(50),
bbscontent varchar2(4000),
bbsdate date default sysdate,
bbsimg varchar(30)
);
-- �ڵ� ���� ������ --
create sequence bbs_seq increment by 1 start with 1;
-- ������ ���� 100�� --
BEGIN
FOR i IN 1..100 LOOP
INSERT INTO bbs VALUES (bbs_seq.nextval, '������', 'test','����',default,null);
END LOOP;
COMMIT;
END;
