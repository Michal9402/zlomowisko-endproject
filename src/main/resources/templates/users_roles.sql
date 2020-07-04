create table if not exists users_roles
(
    user_id int(5) auto_increment primary key unique,
    role_id int(5)
);