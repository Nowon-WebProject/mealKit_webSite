create table item(
    item_num number,
    item_category varchar2(20),
    item_name varchar2(20),
    item_content varchar2(255),
    item_price number,
    item_quantity number,
    item_date varchar2(50),
    item_total varchar2(50),
    item_time varchar2(50),
    item_pictureUrl varchar2(50),
    primary key(item_num)
);

create sequence item_seq start with 1 increment by 1;

-- drop table item;
-- drop sequence item_seq;

select * from item;

select * from user_sequences;



-- 행 100개 넣기 과정 (보기 - DBMS 출력 - 여기의 + 버튼 클릭 - scott 계정 접속
set serveroutput on;

DECLARE
NUM1 NUMBER :=1;

BEGIN
    LOOP
    DBMS_OUTPUT.PUT_LINE(NUM1); -- 출력
    insert into item values(item_seq.nextval, '양식', '으', '하하', 5, 1, sysdate, '2', '2', null);
    NUM1 := NUM1+1; -- NUM = NUM +1
    EXIT WHEN NUM1 >100; -- NUM1이 100보다 크면 LOOP 종료
    END LOOP;
END;

commit;
