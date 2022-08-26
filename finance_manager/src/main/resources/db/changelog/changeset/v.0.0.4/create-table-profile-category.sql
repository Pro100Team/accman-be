--liquibase formatted sql
--changeset "ivkam":3

CREATE TABLE IF NOT EXISTS public.profile_category
(
    pr_cat_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY
            ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    pr_cat_color character varying(255) COLLATE pg_catalog."default" NOT NULL,
    pr_cat_is_deleted boolean NOT NULL,
    pr_cat_type integer NOT NULL,
    pr_cat_category_id bigint NOT NULL,
    pr_cat_profile_id bigint NOT NULL,
    CONSTRAINT profile_category_pkey PRIMARY KEY (pr_cat_id),
    CONSTRAINT FOREIGN_KEY_PROFILE_ID FOREIGN KEY (pr_cat_profile_id)
    REFERENCES public.profiles (p_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT FOREING_KEY_CATEGORY_ID FOREIGN KEY (pr_cat_category_id)
    REFERENCES public.categories (cat_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
)