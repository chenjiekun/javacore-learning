--创建用户表
create table users(
	id int unsigned not null primary key auto_increment,
	username varchar(30) not null,
	password  varchar(50) not null
);
--不用插入数据
 
create table photo(
	id int unsigned not null primary key auto_increment,
	name  varchar(30) not null,
	ph   blob not null
);



