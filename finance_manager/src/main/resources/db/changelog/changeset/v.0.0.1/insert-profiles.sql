--liquibase formatted sql
--changeset "gs":3

INSERT INTO public.profiles(p_country, p_dt_update, p_first_name, is_deleted, p_last_name, p_user_id)
	VALUES (0, current_timestamp(0), 'Sergey', false, 'Semashko', 1);
INSERT INTO public.profiles(p_country, p_dt_update, p_first_name, is_deleted, p_last_name, p_user_id)
	VALUES (1, current_timestamp(0), 'Katia', false, 'Popova', 2);
INSERT INTO public.profiles(p_country, p_dt_update, p_first_name, is_deleted, p_last_name, p_user_id)
	VALUES (2, current_timestamp(0), 'Jura', false, 'Khmelev', 3);
INSERT INTO public.profiles(p_country, p_dt_update, p_first_name, is_deleted, p_last_name, p_user_id)
	VALUES (3, current_timestamp(0), 'Anna', false, 'Melnokiva', 4);
