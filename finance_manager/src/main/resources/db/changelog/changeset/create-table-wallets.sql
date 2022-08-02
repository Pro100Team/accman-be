--liquibase formatted sql
--changeset "gs":4

CREATE TABLE IF NOT EXISTS wallets
(
    w_id            bigserial PRIMARY KEY,
    w_name          varchar(255) NOT NULL,
    w_is_default    boolean      NOT NULL,
    w_users_id      int8         NOT NULL REFERENCES users (u_id),
    w_currencies_id int4         NOT NULL REFERENCES currencies (cur_id),
    w_updated       timestamp    NOT NULL
);
