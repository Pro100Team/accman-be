--liquibase formatted sql
--changeset "Gapserg":2

ALTER TABLE public.transactions ALTER COLUMN tr_last_updated TYPE date;