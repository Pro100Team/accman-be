--liquibase formatted sql
--changeset "Gapserg":3

ALTER TABLE public.transactions DROP COLUMN tr_category;
ALTER TABLE public.transactions ADD tr_pr_category bigserial NOT NULL;
ALTER TABLE public.transactions ADD tr_pr_sub_category bigserial NOT NULL;

ALTER TABLE public.transactions
   ADD CONSTRAINT FK_TRANSACTION_BY_CATEGORY_ID FOREIGN KEY (tr_pr_category)
       REFERENCES public.profile_category (pr_cat_id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION;
ALTER TABLE public.transactions
ADD CONSTRAINT FK_TRANSACTION_BY_SUBCATEGORY_ID FOREIGN KEY (tr_pr_sub_category)
       REFERENCES public.profile_sub_cat (pr_sub_cat_id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION;