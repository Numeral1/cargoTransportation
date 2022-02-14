create table "client"
(
    id     bigserial
        constraint client_pk
            primary key,
    name   varchar(30) not null,
    status varchar(20) not null
);