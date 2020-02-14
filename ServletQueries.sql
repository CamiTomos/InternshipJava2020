create database shop;

use shop;

CREATE TABLE users(
	ID INT NOT NULL auto_increment,
    username VARCHAR(20),
    password VARCHAR(20),
    PRIMARY KEY(ID)
);

CREATE TABLE products(
	ID INT NOT NULL auto_increment,
    name VARCHAR(20),
    price double,
    PRIMARY KEY(ID)
);

CREATE TABLE items(
	ID INT NOT NULL auto_increment,
	productID INT NOT NULL,
    quantity INT NOT NULL,
    primary key(ID),
    foreign key (productID) references products(ID)
);

CREATE TABLE sales(
	ID INT NOT NULL auto_increment,
	userID INT NOT NULL,
	itemID INT NOT NULL,
    quantity INT NOT NULL,
    primary key(ID),
    foreign key (userID) references users(ID),
    foreign key (itemID) references items(ID)
);

insert into users (username, password)
values ('ana','ana'),('kate','kate'),('paul','paul'),('tessa','tessa');

select * from users;

insert into products (name,price)
values ("milk", 100),("beef",50.50),("salad",39.20),("carrot",24.60),("water",10);

select * from products;

insert into items (productID,quantity)
values (1,10),(2,30),(3,20),(4,10),(5,10);

insert into sales (userID,itemID,quantity)
value (1,1,3);

select * from sales;

select p.ID, p.name, p.price, i.quantity 
from products p inner join items i
on p.ID=i.productID;

select * from sales;
