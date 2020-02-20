create database library;

use library;

create table employees(
	id int not null auto_increment,
    employeeName varchar(40) not null,
    employeeRole varchar(10) not null,
    employeePassword blob not null,
    employeeEmail varchar(30) not null,
    primary key(id)
);

alter table employees
modify column employeePassword varchar(50);

create table books(
	id int not null auto_increment,
    bookTitle varchar(30) not null,
    bookAuthor varchar(30) not null,
    bookDescription varchar(50) not null,
    bookAddedDate datetime not null,
	primary key(id)
);
insert into books values(2,"Moby Dick","Herman Melville","awesome novel","2019-01-01 11:11:11");
select * from books;
create table tags(
	id int not null auto_increment,
    tagDescription varchar(20) not null,
    primary key(id)
);

create table bookstags(
	id int not null auto_increment,
    bookID int not null,
    tagID int not null,
	foreign key(bookID) references books(id) on delete cascade,
	foreign key(tagID) references tags(id) on delete cascade,
    primary key(id)
);

create table copies(
	id int not null auto_increment,
    copyFlag bool not null,
    copyStatus varchar(10) not null,
    bookID int not null,
    foreign key(bookID) references books(id) on delete cascade,
    primary key(id)
);


create table bookrents(
	id int not null auto_increment,
    bookrentRentalDate datetime not null,
    bookrentReturnDate datetime not null,
    bookrentStatus varchar(10) not null,
    bookrentNote double not null,
    employeeID int not null,
    bookID int not null,
    copyID int not null,
	foreign key(employeeID) references employees(id) on delete cascade,
	foreign key(bookID) references books(id) on delete cascade,
	foreign key(copyID) references copies(id) on delete cascade,
    primary key(id)
);

create table rentrequests(
	id int not null auto_increment,
    rentrequestRequestDate datetime not null,
    rentrequestStatus varchar(50) not null,
    employeeID int not null,
    bookID int not null,
	foreign key(employeeID) references employees(id) on delete cascade,
	foreign key(bookID) references books(id) on delete cascade,
    primary key(id)
);


create table bookrequests(
	id int not null auto_increment,
    bookrequestTitle varchar(30) not null,
    bookrequestAuthor varchar(30) not null,
    bookrequestPublishingCompany varchar(30) not null,
    bookrequestLink varchar(100),
    bookrequestCopiesNeeded int not null,
    bookrequestTotalCost double not null,
    bookrequestStatus varchar(10) not null,
    employeeID int not null,
	foreign key(employeeID) references employees(id) on delete cascade,
    primary key(id)
);
    
    
