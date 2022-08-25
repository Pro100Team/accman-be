--liquibase formatted sql
--changeset "ivkam":3

UPDATE public.users
SET u_password='$2a$10$E6v02X3aApbpSm54jBazQO7E1KvTaLkuAKoOhcPwH1CLJQ/Ve7nlm'
WHERE u_email = 'sergey@exadel.com';
UPDATE public.users
SET u_password='$2a$10$R8tgHocPYCxQ0d2n96aRL.y5RmbIJQe513t3iF7.RvqLhurX1Fam2'
WHERE u_email = 'katia@exadel.com';
UPDATE public.users
SET u_password='$2a$10$Z3bVv8w/CEoNl2DOpMHIcu2NtTRDG4GaCyeERK469ILGGk/OaednW'
WHERE u_email = 'jura@exadel.com';
UPDATE public.users
SET u_password='$2a$10$iEYewRS/yZ1E4QE8QPcpYegEn5EUcg6jPrJw4VLB8mA6TlgKxRKOG'
WHERE u_email = 'anna@exadel.com';
