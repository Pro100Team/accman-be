--liquibase formatted sql
--changeset "Gapserg":3

CREATE TABLE IF NOT EXISTS public.categories
(
    cat_id  bigserial NOT NULL,
    cat_name varchar(255) NOT NULL,
    cat_color varchar(255) NOT NULL,
    cat_type int NOT NULL,
    CONSTRAINT CATEGORIES_PRIMARY_KEY PRIMARY KEY (cat_id)
)