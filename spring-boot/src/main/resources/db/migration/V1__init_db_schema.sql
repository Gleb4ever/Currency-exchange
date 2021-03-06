create table exchange_requests (
    id int8 generated by default as identity,
    user_email varchar(255) not null,
    reference_currency varchar(3) not null,
    target_currency varchar(3) not null,
    amount real not null,
    result real not null,
    rate real not null,
    primary key (id)
);