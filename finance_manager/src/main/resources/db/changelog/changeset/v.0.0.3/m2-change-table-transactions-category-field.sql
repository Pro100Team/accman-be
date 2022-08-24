--liquibase formatted sql
--changeset "Gapserg":3

ALTER TABLE public.transactions DROP COLUMN tr_category;
ALTER TABLE public.transactions ADD tr_category_id bigserial NOT NULL;

ALTER TABLE public.transactions
   ADD CONSTRAINT FK_TRANSACTION_BY_CATEGORY_ID FOREIGN KEY (tr_category_id)
       REFERENCES public.categories (cat_id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION;