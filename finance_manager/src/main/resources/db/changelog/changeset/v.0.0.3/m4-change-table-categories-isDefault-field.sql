--liquibase formatted sql
--changeset "Gapserg":4

ALTER TABLE public.categories ADD cat_is_default boolean;

