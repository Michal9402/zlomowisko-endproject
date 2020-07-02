create table if not exists cennik
(
    id    int(5) auto_increment primary key unique,
    name  varchar(20) not null,
    price varchar(30) not null
);

insert into cennik values (1, 'Aluminium','35 pln/kg');
insert into cennik values (2, 'Miedź','90 pln/kg');


