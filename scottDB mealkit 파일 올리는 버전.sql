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
    item_content varchar2(255), -- 내용 --
    item_price number, -- 가격
    item_quantity number, -- 재고
    item_date varchar2(50), -- 등록한 날짜 --
    item_total varchar2(50), -- 인분
    item_time varchar2(50), -- 조리 시간
    item_main char(1), -- 메인 등장 여부
    item_sales number, -- 판매량
    primary key(item_num)
);

create sequence item_seq start with 1 increment by 1;

insert into item values(null, null, item_seq.nextval, '한식', '불고기볶음', '맛있는 불고기? 뭘 봐',
                        13000, 100, sysdate, '1', '10', '0', 50);
insert into item values(null, null, item_seq.nextval, '양식', '스테이크', '맛있는 스테이크? 뭘 봐',
                        20000, 70, sysdate, '2', '5', '0', 89);
insert into item values(null, null, item_seq.nextval, '중식', '전갈볶음', '맛있는 전갈? 뭘 봐',
                        25000, 30, sysdate, '3', '20', '0', 5);
insert into item values(null, null, item_seq.nextval, '일식', '초밥무침', '맛있는 초밥? 뭘 봐',
                        20000, 10, sysdate, '10', '5', '0', 0);
insert into item values(null, null, item_seq.nextval, '샐러드', '참깨샐러드', '맛있는 샐러드? 뭘 봐',
                        100000, 5, sysdate, '5', '30', '0', 0);

-- drop table item;
-- drop sequence item_seq;
-- create sequence item_seq start with 1 increment by 1;


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
    insert into item values(null, null, item_seq.nextval, '한식', '불고기볶음', '맛있는 불고기? 뭘 봐',
                        13000, 100, sysdate, '1', '10', '0', 50);
    NUM1 := NUM1+1; -- NUM = NUM +1
    EXIT WHEN NUM1 >100; -- NUM1이 100보다 크면 LOOP 종료
    END LOOP;
END;

