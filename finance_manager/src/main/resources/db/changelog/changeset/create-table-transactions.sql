--liquibase formatted sql
--changeset "gs":6

CREATE TABLE IF NOT EXISTS transactions
(
    t_id            bigserial PRIMARY KEY,
    t_amount        int4      NOT NULL,
    t_wallets_id    int8      NOT NULL REFERENCES wallets (w_id),
    t_categories_id int4      NOT NULL REFERENCES categories (cat_id),
    t_created       timestamp NOT NULL,
    t_notes         varchar(200)
);
