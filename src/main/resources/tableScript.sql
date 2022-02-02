drop database if exists user_db;
create database user_db;
use user_db;
create table users(
	id int auto_increment,
  email varchar(256),
  password varchar(256),
  first_name varchar(256),
  last_name varchar(256),
  constraint pk_id primary key (id));
commit;