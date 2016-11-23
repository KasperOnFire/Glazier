DROP TABLE IF EXISTS pricelist;
drop table if exists orders;

CREATE TABLE pricelist (
product varchar(255) NOT NULL,
price int not null,
PRIMARY KEY (`product`)
);

create table orders(
order_id varchar(255) NOT NULL,
dato date,
order_amount double not null,
order_currency varchar(255) Not null
);

insert into PRICELIST values ('glass', 300);
insert into PRICELIST values ('wooden frame', 100);
insert into PRICELIST values ('vinyl frame', 200);
insert into PRICELIST values ('fiberglass frame', 350);
