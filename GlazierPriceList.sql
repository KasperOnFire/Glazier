DROP TABLE IF EXISTS `PRICELIST`;

CREATE TABLE `PRICELIST` (
product varchar(255) NOT NULL,
price int not null,
PRIMARY KEY (`product`)
);

insert into PRICELIST values ('glass', 300);
insert into PRICELIST values ('wooden frame', 100);
insert into PRICELIST values ('vinyl frame', 200);
insert into PRICELIST values ('fiberglass frame', 350);
