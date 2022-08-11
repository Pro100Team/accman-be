--liquibase formatted sql
--changeset "gs":2

CREATE TABLE IF NOT EXISTS public.transactions
(
    tr_id  bigserial NOT NULL,
    tr_currency int4 NOT NULL,
    tr_is_deleted boolean,
    tr_last_updated timestamp,
    tr_name varchar(255) NOT NULL,
    tr_wallet_id int8 NOT NULL,
    CONSTRAINT TRANSACTION_PRIMARY_KEY PRIMARY KEY (tr_id),
    CONSTRAINT FK_TRANSACTION_BY_WALLET_ID FOREIGN KEY (tr_wallet_id)
            REFERENCES public.wallets (w_id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
)