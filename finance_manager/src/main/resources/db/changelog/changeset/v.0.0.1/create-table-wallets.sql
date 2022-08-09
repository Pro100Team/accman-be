--liquibase formatted sql
--changeset "gs":4

CREATE TABLE IF NOT EXISTS public.wallets
(
    w_id bigserial NOT NULL,
    w_currency integer NOT NULL,
    w_is_default boolean,
    w_is_deleted boolean,
    w_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    w_used_at timestamp without time zone,
    w_user_id bigint,
    PRIMARY KEY (w_id),
    CONSTRAINT FK_WALLETS_BY_USER_ID FOREIGN KEY (w_user_id)
        REFERENCES public.users (u_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
