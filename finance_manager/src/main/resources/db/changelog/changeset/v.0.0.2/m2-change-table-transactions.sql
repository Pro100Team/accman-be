--liquibase formatted sql
--changeset "Gapserg":2

ALTER TABLE public.transactions
    DROP COLUMN tr_name,
    ADD tr_type int NOT NULL,
    ADD tr_amount int NOT NULL,
    ADD tr_category varchar(255) NOT NULL,
    ADD tr_sub_category varchar(255),
    ADD tr_payer varchar(255),
    ADD tr_notes varchar(255);

