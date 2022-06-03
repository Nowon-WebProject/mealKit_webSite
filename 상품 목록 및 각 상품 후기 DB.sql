-- 접속: scottDB

-- 상품 목록 DB. 파일 안 올리는 버전
--create table item(
--    item_num number, -- 상품 번호
--    item_category varchar2(20), -- 카테고리
--    item_name varchar2(20), -- 상품명
--    item_content varchar2(255), -- 내용
--    item_price number,   -- 가격
--    item_quantity number, -- 재고
--    item_date varchar2(50), -- 등록한 날짜
--    item_total varchar2(50), -- 인분
--    item_time varchar2(50), -- 조리 시간
--    primary key(item_num)
-- );

-- 상품 목록 DB. 파일 한 개 올리는 버전
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

-- 상품 목록 DB. 파일 두 개 및 기타 칼럼 추가 버전
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
    item_main varchar2(50), -- 메인 등장 여부
    item_sales number, -- 판매량
    item_discount number(4,2), -- 적용될 할인율
    item_starsAvg number(4,2), -- 후기 평점의 평균
    primary key(item_num)
);

-- 특정 상품 내 후기 게시판 목록 DB
create table postScript(
    post_num number, -- 글 번호
    post_subject nvarchar2(100), -- 글 제목
    post_writer nvarchar2(30), -- 작성자
    post_date varchar2(10), -- 작성일
    post_help number, -- 도움
    post_hits number, -- 조회
    post_stars number(4,2), -- 평점
    post_content nvarchar2(255), -- 내용
    primary key(post_num)
);

-- 상품 목록의 번호 시퀀스
create sequence item_seq start with 1 increment by 1;
-- 특정 상품 내 후기 게시판의 번호 시퀀스
create sequence postScript_seq start with 1 increment by 1;

-- 상품 목록 데이터 입력
insert into item values(null, null, item_seq.nextval, '한식', '불고기볶음', '맛있는 불고기? 뭘 봐',
                        13000, 100, sysdate, '1', '10', '0', 50, 10.0, 5);
insert into item values(null, null, item_seq.nextval, '양식', '스테이크', '맛있는 스테이크? 뭘 봐',
                        20000, 70, sysdate, '2', '5', '0', 89, 10.0, 5);
insert into item values(null, null, item_seq.nextval, '중식', '전갈볶음', '맛있는 전갈? 뭘 봐',
                        25000, 30, sysdate, '3', '20', '0', 5, 10.0, 5);
insert into item values(null, null, item_seq.nextval, '일식', '초밥무침', '맛있는 초밥? 뭘 봐',
                        20000, 10, sysdate, '10', '5', '0', 0, 10.0, 5);
insert into item values(null, null, item_seq.nextval, '샐러드', '참깨샐러드', '맛있는 샐러드? 뭘 봐',
                        100000, 5, sysdate, '5', '30', '0', 0, 10.0, 5);

-- 특정 상품 내 후기 게시판 데이터 입력
tiger



-- drop table item;
-- drop sequence item_seq;



select * from item;
select * from postScript;

select * from user_sequences;

commit; -- 행 100개 넣고 꼭 커밋할 것



-- 상품 목록 행 100개 넣기 과정 (보기 - DBMS 출력 - 여기의 + 버튼 클릭 - scott 계정 접속)
set serveroutput on;

DECLARE
NUM1 NUMBER :=1;

BEGIN
    LOOP
    DBMS_OUTPUT.PUT_LINE(NUM1); -- 출력
    insert into item values(null, null, item_seq.nextval, '한식', '불고기볶음', '맛있는 불고기? 뭘 봐',
                        13000, 100, sysdate, '1', '10', '0', 50, 33, 3.9);
    NUM1 := NUM1+1; -- NUM = NUM +1
    EXIT WHEN NUM1 >100; -- NUM1이 100보다 크면 LOOP 종료
    END LOOP;
END;



