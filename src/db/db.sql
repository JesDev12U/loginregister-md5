drop database if exists loginMD5;
create database loginMD5;
use loginMD5;

create table users(
	id int primary key auto_increment,
    nom varchar(30) not null,
    ap_pat varchar(30) not null,
    ap_mat varchar(30) not null,
    username varchar(10) not null,
    password_usr varchar(50) not null
);