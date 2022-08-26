--liquibase formatted sql
--changeset "ivkam":3

INSERT INTO public.default_category(id, def_cat_type, def_cat_color, def_cat_name)
VALUES (1, 1, 'FF0000', 'Salary'),
       (2, 1, 'FF0000', 'Child support received'),
       (3, 1, 'FF0000', 'Gift'),
       (4, 1, 'FF0000', 'Partner’s salary'),
       (5, 0, 'FF0000', 'Home & utilities'),
       (6, 0, 'FF0000', 'Insurance'),
       (7, 0, 'FF0000', 'Food'),
       (8, 0, 'FF0000', 'Entertainment & Hobby'),
       (9, 0, 'FF0000', 'Health & Sport'),
       (10, 0, 'FF0000', 'Beauty'),
       (11, 0, 'FF0000', 'Clothes'),
       (12, 0, 'FF0000', 'Transport'),
       (13, 0, 'FF0000', 'Education'),
       (14, 0, 'FF0000', 'Pets'),
       (15, 0, 'FF0000', 'Children');
