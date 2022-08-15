create table customer
(
    id            bigint auto_increment primary key,
    first_name    varchar(64) not null,
    last_name     varchar(64) not null,
    email_address varchar(64) not null,
    address       varchar(64) not null,
    country       varchar(32) not null,
    state         varchar(12) not null,
    phone_number  varchar(24) not null
);
