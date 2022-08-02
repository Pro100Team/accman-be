--liquibase formatted sql
--changeset "gs":1

CREATE TYPE code AS ENUM ('USD', 'EUR', 'PLN','GEL' );

CREATE TABLE IF NOT EXISTS currencies
(
    cur_id   serial PRIMARY KEY,
    cur_code code NOT NULL
);
