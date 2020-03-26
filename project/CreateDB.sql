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
add isBanned bool not null;

alter table employees
add lastDayOfBan datetime;

alter table employees
modify column employeePassword varchar(50);
select * from employees;
create table books(
	id int not null auto_increment,
    bookTitle varchar(30) not null,
    bookAuthor varchar(30) not null,
    bookDescription varchar(50) not null,
    bookAddedDate datetime not null,
	primary key(id)
);

ALTER TABLE employees 
ADD CONSTRAINT unique_email UNIQUE(employeeEmail);

ALTER TABLE tags 
ADD CONSTRAINT unique_tag UNIQUE(tagDescription);

delete from employees where id=4;


insert into books values(2,"Moby Dick","Herman Melville","awesome novel","2019-01-01 11:11:11");
select * from books;
create table tags(
	id int not null auto_increment,
    tagDescription varchar(20) not null,
    primary key(id)
);
select * from tags;
select * from books;
select * from bookstags;

create table bookstags(
	id int not null auto_increment,
    bookID int not null,
    tagID int not null,
	foreign key(bookID) references books(id) on delete cascade,
	foreign key(tagID) references tags(id) on delete cascade,
    primary key(id)
);
select * from bookstags;

alter table bookstags
drop column id;
select * from bookstags;

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

-- alter table bookrents
-- drop constraint bookrents_ibfk_2;

alter table bookrents
drop column bookID;

alter table bookrents
add bookID int not null;

ALTER TABLE bookrents
ADD FOREIGN KEY (bookID) REFERENCES books(id) on delete cascade on update cascade;

select * from bookrents;

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

alter table rentrequests
add emailSentDate datetime;



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
    
    

-- asta e ok for 1
select b.bookTitle, count(*) 
from books b inner join bookrents r on b.id=r.bookID
where r.bookrentRentalDate between '2020-03-23 22:54:53' and '2020-03-23 23:02:39'
group by b.bookTitle
order by count(*) desc
limit 3;

-- asta e ok for 2
select e.employeeName, COUNT(*)
from employees e inner join bookrents r on e.id=r.employeeID
where r.bookrentReturnDate between '2020-03-23 22:07:49' and '2020-03-23 23:17:37'
	  and r.bookrentStatus='returned'
group by e.employeeName
order by COUNT(*) DESC
limit 2;


-- asta e ok for 3
 select e.employeeName
 from employees e inner join bookrents r on e.id=r.employeeID
 where r.bookrentStatus='late';


