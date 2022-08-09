--liquibase formatted sql
--changeset "gs":3

INSERT INTO public.users(u_email, u_password, u_role) VALUES ('Sergey@exadel.com', 'Sergey!2345', 1) ON CONFLICT DO NOTHING;
INSERT INTO public.users(u_email, u_password, u_role) VALUES ('Katia@exadel.com', 'Katia!2345', 2) ON CONFLICT DO NOTHING;
INSERT INTO public.users(u_email, u_password, u_role) VALUES ('Jura@exadel.com', 'Jura!2345',  1) ON CONFLICT DO NOTHING;
INSERT INTO public.users(u_email, u_password, u_role) VALUES ('Anna@exadel.com', 'Anna!2345',  1) ON CONFLICT DO NOTHING;
