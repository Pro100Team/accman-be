--liquibase formatted sql
--changeset "Gapserg":3

INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Salary', '#FF0000', 0, true);
INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Child support received', '#FF0000', 0, true);
INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Gift', '#FF0000', 0, true);
INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Partner’s salary', '#FF0000', 0, true);


INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Home & utilities', '#FF0000', 1, true);
INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Insurance', '#FF0000', 1, true);
INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Food', '#FF0000', 1, true);
INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Entertainment & Hobby', '#FF0000', 1, true);
INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Health & Sport', '#FF0000', 1, true);
INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Beauty', '#FF0000', 1, true);
INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Clothes', '#FF0000', 1, true);
INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Transport', '#FF0000', 1, true);
INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Education', '#FF0000', 1, true);
INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Pets', '#FF0000', 1, true);
INSERT INTO public.categories(
    cat_name, cat_color, cat_type, is_default)
VALUES ('Children', '#FF0000', 1, true);