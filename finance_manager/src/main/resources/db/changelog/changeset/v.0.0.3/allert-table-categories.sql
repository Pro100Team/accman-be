--liquibase formatted sql
--changeset "Gapserg":3

ALTER TABLE public.categories ADD COLUMN is_default boolean;
ALTER TABLE public.categories DROP COLUMN cat_profile_id;