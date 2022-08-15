create table reservation
(
    id          bigint auto_increment primary key,
    room_id     bigint not null,
    customer_id bigint not null,
    res_date    date   not null
);
