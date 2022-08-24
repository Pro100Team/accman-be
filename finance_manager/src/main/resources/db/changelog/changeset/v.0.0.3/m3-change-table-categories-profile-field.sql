--liquibase formatted sql
--changeset "Gapserg":4

ALTER TABLE public.categories ADD cat_profile_id int8;

ALTER TABLE public.categories
   ADD CONSTRAINT FK_CATEGORIES_BY_PROFILE_ID FOREIGN KEY (cat_profile_id)
       REFERENCES public.profiles (p_id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION;