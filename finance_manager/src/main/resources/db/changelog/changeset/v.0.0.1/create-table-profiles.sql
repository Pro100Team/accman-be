--liquibase formatted sql
--changeset "gs":2

CREATE TABLE IF NOT EXISTS public.profiles
(
    p_id  bigserial NOT NULL,
    p_country int4,
    p_dt_update timestamp,
    p_first_name varchar(255),
    is_deleted boolean,
    p_last_name varchar(255),
    p_user_id int8,
    CONSTRAINT PROFILES_PRIMARY_KEY PRIMARY KEY (p_id),
    CONSTRAINT FK_PROFILES_BY_USER_ID FOREIGN KEY (p_user_id)
            REFERENCES public.users (u_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
)