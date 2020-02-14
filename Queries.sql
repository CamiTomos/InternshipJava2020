CREATE DATABASE SPORTEVENTS;

USE  SPORTEVENTS;

CREATE TABLE Events(
	ID INT NOT NULL,
    EventName VARCHAR(30),
    Location VARCHAR(20),
    PRIMARY KEY(ID)
);

CREATE TABLE teams(
	ID INT NOT NULL,
    TeamName VARCHAR(30),
    OriginCountry VARCHAR(20),
    PRIMARY KEY(ID)
);
    
CREATE TABLE participations(
	ID INT NOT NULL auto_increment,
	EventID INT NOT NULL,
    TeamID INT NOT NULL,
    primary key(ID),
    foreign key (EventID) references events(ID), 
    foreign key (TeamID) references teams(ID)
);

insert into events (ID, EventName, Location)
values (1,'WorldCup','Spain'), (2,'FedCup', 'Romania'),(3,'DavisCup','England');
    
select * from events;

insert into teams (ID, TeamName, OriginCountry)
values (1,'Romania Fed Cup Team','Romania'), (2,'Real Madrid','Spain'), (3,'France Fed Cup Team','France'), (4,'Dormund', 'Germany'),
	   (5,'Croatia Davis Cup Team','Croatia');
       
select* from teams;

insert into participations (EventID,TeamID)
values (1,2),(1,4),(2,1),(2,3),(3,5);

select * from participations;

update sportevents.events
set EventName='Davis Cup'
where ID=3;

select * from events;

insert into events (ID, EventName, Location)
values (4,'World Cup','Spain');

delete from sportevents.events
where ID=4;



