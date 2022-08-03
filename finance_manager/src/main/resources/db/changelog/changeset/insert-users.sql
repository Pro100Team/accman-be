--liquibase formatted sql
--changeset "gs":3

INSERT INTO users (u_email, u_password, u_is_admin)
VALUES ('Sergey@exadel.com', 'Sergey!2345', true),
       ('Katia@exadel.com', 'Katia!2345', false),
       ('Jura@exadel.com', 'Jura!2345', true),
       ('Anna@exadel.com', 'Anna!2345', false)
ON CONFLICT (u_email) DO NOTHING;
