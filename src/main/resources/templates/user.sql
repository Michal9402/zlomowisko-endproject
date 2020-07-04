create table if not exists user
(
    user_id int(5) auto_increment primary key unique,
    name varchar(20) not null,
    password varchar(30) not null,
    enabled TINYINT null
    );