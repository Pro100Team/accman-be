#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER dbu WITH ENCRYPTED PASSWORD '${DBU_PASSWORD}';
    ALTER user dbu with superuser;
    CREATE DATABASE accman
        WITH OWNER = dbu
            ENCODING = 'UTF8'
            TABLESPACE = pg_default
            LC_COLLATE = 'en_US.utf8'
            LC_CTYPE = 'en_US.utf8'
            CONNECTION LIMIT = -1;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "accman" <<-EOSQL
    grant ALL on SCHEMA public to dbu;
EOSQL

