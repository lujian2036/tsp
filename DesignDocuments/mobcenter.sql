-- create mobcenter database


drop database if exists mobcenter;
create database if not exists mobcenter;
use mobcenter;

create table Boh(
	ID	int primary key AUTO_INCREMENT,
	Name text not null,
	BohName text ,
	BohMethod text not null,
	BohRoutePath text not null,
	BohParameter text,
	ParameterDecode tinyint default 1,
	ReturnDecode  tinyint  default 1,
	SampleTxt  text
);


create table Tsp_server(
	ID int primary key AUTO_INCREMENT,
	Name text,
	NoteInformation text
);

create table TspServer_Boh_relation(
	ID int primary key AUTO_INCREMENT,
	Boh_ID int,
	Tsp_server_ID	int,
	ServiceHost text
);


-- dump data

insert into Tsp_server values (null ,'tsp','tsp for main');
insert into Tsp_server values (null ,'tsp1','tsp for slave1');
insert into Tsp_server values (null ,'tsp2','tsp for slave2');
