create table "storage"
(
    id     bigserial
        constraint storage_pk
            primary key,
    name   varchar(20) not null,
    address varchar(40) not null,
    client_id bigint
);

alter table "storage"
    add constraint client_id_fk foreign key (client_id) references client (id);