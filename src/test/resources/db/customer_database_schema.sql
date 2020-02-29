create schema if not exists db_plain;
create table if not exists db_plain.customer (
    id varchar(40),
    first_name varchar(40),
    last_name varchar(40)
);