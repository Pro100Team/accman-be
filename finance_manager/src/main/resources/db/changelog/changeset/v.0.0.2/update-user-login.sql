--liquibase formatted sql
--changeset "ivkam":3

UPDATE public.users SET u_email='sergey@exadel.com' WHERE u_email='Sergey@exadel.com';
UPDATE public.users SET u_email='katia@exadel.com' WHERE u_email='Katia@exadel.com';
UPDATE public.users SET u_email='jura@exadel.com' WHERE u_email='Jura@exadel.com';
UPDATE public.users SET u_email='anna@exadel.com' WHERE u_email='Anna@exadel.com';
