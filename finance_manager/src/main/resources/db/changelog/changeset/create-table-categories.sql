--liquibase formatted sql
--changeset "gs":5

CREATE TABLE IF NOT EXISTS category_types
(
    ct_id    bigserial PRIMARY KEY,
    ct_types varchar(255) NOT NULL
);

CREATE TABLE categories
(
    cat_id                bigserial PRIMARY KEY,
    cat_name              varchar(255) NOT NULL,
    cat_category_types_id int8         NOT NULL REFERENCES category_types (ct_id)
);

CREATE TABLE sub_categories
(
    sc_id            bigserial PRIMARY KEY,
    sc_name          varchar(255) NOT NULL,
    sc_categories_id int8         NOT NULL REFERENCES categories (cat_id)
);