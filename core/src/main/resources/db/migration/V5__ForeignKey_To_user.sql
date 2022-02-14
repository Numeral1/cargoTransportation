alter table "user"
    add constraint client_id_fk foreign key (client_id) references client (id);