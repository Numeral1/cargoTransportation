create table "user"
(
    id           bigserial
        constraint users_pk
            primary key,
    name         varchar(20),
    surname      varchar(20)  not null,
    patronymic   varchar(20),
    born_date    date,
    email        varchar(50)  not null,
    town         varchar(20),
    street       varchar(20),
    house        varchar(5),
    flat         varchar(5),
    login        varchar(15)  not null
        constraint users_un
            unique,
    password     varchar(100) not null,
    passport_num varchar(30)
        constraint users_passport_un
            unique,
    issued_by    varchar(50),
    client_id    bigint

);


