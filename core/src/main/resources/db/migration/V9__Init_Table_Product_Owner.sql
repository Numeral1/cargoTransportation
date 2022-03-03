create table "product_owner"
(
    id     bigserial
        constraint product_owner_pk
            primary key,
    name   varchar(30) not null,
    address varchar(40) not null,
    client_id bigint
);

alter table "product_owner"
    add constraint client_id_fk foreign key (client_id) references client (id);