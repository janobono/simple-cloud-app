create table room
(
    id          bigint auto_increment primary key,
    name        varchar(16) not null,
    room_number char(2)     not null unique,
    bed_info    char(2)     not null
);
