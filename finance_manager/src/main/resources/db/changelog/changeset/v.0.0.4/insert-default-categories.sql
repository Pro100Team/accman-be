--liquibase formatted sql
--changeset "ivkam":3

INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Salary');
INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Child support received');
INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Gift');
INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Partner’s salary');

INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Home & utilities');
INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Insurance');
INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Food');
INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Entertainment & Hobby');
INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Health & Sport');
INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Beauty');
INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Clothes');
INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Transport');
INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Education');
INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Pets');
INSERT INTO public.categories(cat_is_default, cat_name)
VALUES (true, 'Children');
