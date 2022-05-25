--create table item(
--    item_num number, -- 상품 번호
--    item_category varchar2(20), -- 카테고리
--    item_name varchar2(20), -- 상품명
--    item_content varchar2(255), -- 내용
--    item_price number, -- 가격
--    item_quantity number, -- 재고
--    item_date varchar2(50), -- 등록한 날짜
--    item_total varchar2(50), -- 인분
--    item_time varchar2(50), -- 조리 시간
--    primary key(item_num)
-- );

--create table item(
--    item_pictureUrl varchar2(50), -- 사진
--    item_num number, -- 상품 번호
--    item_category varchar2(20), -- 카테고리
--    item_name varchar2(20), -- 상품명
--    item_content varchar2(255), -- 내용
--    item_price number, -- 가격
--    item_quantity number, -- 재고
--    item_date varchar2(50), -- 등록한 날짜
--    item_total varchar2(50), -- 인분
--    item_time varchar2(50), -- 조리 시간
--    primary key(item_num)
--);

create table item(
    item_pictureUrl1 varchar2(50), -- 사진1
    item_pictureUrl2 varchar2(50), -- 사진2
    item_num number, -- 상품 번호
    item_category varchar2(20), -- 카테고리
    item_name varchar2(100), -- 상품명
    item_content varchar2(255), -- 내용
    item_price number, -- 가격
    item_quantity number, -- 재고
    item_date varchar2(50), -- 등록한 날짜
    item_total varchar2(50), -- 인분
    item_time varchar2(50), -- 조리 시간
    item_main char(1), -- 메인 등장 여부
    item_sales varchar2(50), -- 판매량
    primary key(item_num)
);

create sequence item_seq start with 1 increment by 1;

-- drop table item;
-- drop sequence item_seq;

select * from item;

select * from user_sequences;

commit; -- 행 100개 넣고 꼭 커밋하기

-- 행 100개 넣기 과정 (보기 - DBMS 출력 - 여기의 + 버튼 클릭 - scott 계정 접속
set serveroutput on;

DECLARE
NUM1 NUMBER :=1;

BEGIN
    LOOP
    DBMS_OUTPUT.PUT_LINE(NUM1); -- 출력
    insert into item values(null, null, item_seq.nextval, '양식', '으', '하하', 5, 1, sysdate, '2', '2');
    NUM1 := NUM1+1; -- NUM = NUM +1
    EXIT WHEN NUM1 >100; -- NUM1이 100보다 크면 LOOP 종료
    END LOOP;
END;
