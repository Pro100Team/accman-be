--liquibase formatted sql
--changeset "Gapserg":2

ALTER TABLE public.transactions DROP COLUMN tr_name;
ALTER TABLE public.transactions ADD tr_type int NOT NULL;
ALTER TABLE public.transactions ADD tr_amount int NOT NULL;
ALTER TABLE public.transactions ADD tr_category varchar(255) NOT NULL;
ALTER TABLE public.transactions ADD tr_sub_category varchar(255);
ALTER TABLE public.transactions ADD tr_payer varchar(255);
ALTER TABLE public.transactions ADD tr_notes varchar(255);