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

select * from item;

select * from user_sequences;
