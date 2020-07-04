create table if not exists role
(
    role_id int(5) auto_increment primary key unique,
    name varchar(20) not null
);