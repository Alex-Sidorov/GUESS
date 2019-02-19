CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "citext";

DROP TYPE IF EXISTS USER_ROLE CASCADE;
CREATE TYPE USER_ROLE AS ENUM
('USER', 'ADMIN');

DROP TABLE IF EXISTS guess_user CASCADE;
CREATE TABLE guess_user (
  id            UUID        PRIMARY KEY,
  first_name    TEXT        NOT NULL,
  last_name     TEXT        NOT NULL,
  email         CITEXT      NOT NULL,
  hash_password TEXT        NOT NULL,
  role          USER_ROLE   NOT NULL,
  created_at    TIMESTAMPTZ NOT NULL,
  updated_at    TIMESTAMPTZ,
  deleted_at    TIMESTAMPTZ
);