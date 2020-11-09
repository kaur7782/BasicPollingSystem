create table poll(
pollId Long primary key auto_increment,
title varchar(255),
question varchar(255),
answer1 varchar(255),
answer2 varchar(255),
answer3 varchar(255),
votes1 int,
votes2 int,
votes3 int
);