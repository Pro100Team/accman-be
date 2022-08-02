--liquibase formatted sql
--changeset "gs":2

CREATE TABLE IF NOT EXISTS users
(
    u_id       bigserial PRIMARY KEY,
    u_email    varchar(255) NOT NULL,
    u_password varchar(100) NOT NULL,
    u_is_admin boolean      NOT NULL
);



