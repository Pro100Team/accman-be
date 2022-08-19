--liquibase formatted sql
--changeset "Gapserg":2

ALTER TABLE public.wallets ADD w_amount int;