CREATE TABLE "user" (
                     id int NOT NULL,
                     name varchar (50),
                     surname varchar (50),
                     patronymic varchar (50),
                     client_id int,
                     born_date DATE,
                     email varchar (50),
                     town varchar (50),
                     street varchar (50),
                     house varchar (10),
                     flat varchar (10),
                     login varchar (50),
                     password varchar (50),
                     passport_num varchar (30),
                     issued_by varchar (50),
                     PRIMARY KEY(id)
);