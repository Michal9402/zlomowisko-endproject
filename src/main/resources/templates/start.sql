create table if not exists zlom
(
    id int(5) auto_increment primary key unique,
    name varchar(20) not null,
    price varchar(30) not null
);

insert into zlom values (1, 'Aluminium', '30,00 pln/kg')