--liquibase formatted sql
--changeset "ivkam":3

INSERT INTO public.users(u_email, u_password, u_role) VALUES ('Sergey@exadel.com', 'Sergey!2345', 0);
INSERT INTO public.users(u_email, u_password, u_role) VALUES ('Katia@exadel.com', 'Katia!2345', 1);
INSERT INTO public.users(u_email, u_password, u_role) VALUES ('Jura@exadel.com', 'Jura!2345',  0);
INSERT INTO public.users(u_email, u_password, u_role) VALUES ('Anna@exadel.com', 'Anna!2345',  0);
