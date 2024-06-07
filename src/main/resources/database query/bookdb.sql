create database bookshop_db;
use bookshop_db;

create table category(
cid varchar(15) Primary key not null,
cname varchar(20));

create table author(
aid varchar(15) Primary key not null,
aname varchar(20));

create table book(
 bcode varchar(15)Primary key not null,
 name varchar(30) not null,
 qty int,
 price int not null,
 cid varchar(15) not null,
 aid varchar(15) not null,
 FOREIGN KEY (cid) references category(cid),
 FOREIGN KEY (aid) references author(aid));

create table supplier(
    suid varchar(15) Primary key not null,
    suname varchar(45),
    suphone varchar(20),
    suaddress varchar(30));

create table stockin(
    stockinid varchar(15) not null,
    stockdate Date not null,
    bookcode varchar(15),
    bookcategory varchar(15),
    bookauthor varchar(15),
    supplierid varchar(15),
    qty int,
    price int,
    Primary key(stockinid,bookcode,supplierid),
  FOREIGN KEY (bookcode) references book(bcode),
  FOREIGN KEY (supplierid) references supplier(suid));

create table sale(

orid varchar(15) Primary key not null,
ordate date not null,
cuname varchar(20),
cuphone varchar(20),
total int);

create table stockout(

orid varchar(15) not null,
bcode varchar(15) not null,
qty int,
price int,
Primary key(orid,bcode),
FOREIGN KEY (bcode) references book(bcode),
FOREIGN KEY (orid) references sale(orid)
);