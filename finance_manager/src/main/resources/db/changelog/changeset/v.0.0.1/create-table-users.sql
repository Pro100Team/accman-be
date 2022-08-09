--liquibase formatted sql
--changeset "gs":2

CREATE TABLE IF NOT EXISTS public.users
(
    u_id  bigserial NOT NULL,
    u_email varchar(255) NOT NULL,
    u_password varchar(255) NOT NULL,
    u_role int4 NOT NULL,
    PRIMARY KEY (u_id)
)