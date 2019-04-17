CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "citext";

DROP TYPE IF EXISTS USER_ROLE CASCADE;
CREATE TYPE USER_ROLE AS ENUM
  ('USER', 'ADMIN');

DROP TABLE IF EXISTS user_ CASCADE;
CREATE TABLE user_
(
  id            UUID                     DEFAULT uuid_generate_v4() NOT NULL
    CONSTRAINT user_pkey PRIMARY KEY,
  first_name    TEXT                                                NOT NULL,
  last_name     TEXT                                                NOT NULL,
  email         CITEXT                                              NOT NULL,
  hash_password TEXT                                                NOT NULL,
  role          USER_ROLE                DEFAULT 'USER'             NOT NULL,
  created_at    TIMESTAMP WITH TIME ZONE DEFAULT now()              NOT NULL,
  updated_at    TIMESTAMP WITH TIME ZONE,
  deleted_at    TIMESTAMP WITH TIME ZONE
);

DROP TABLE IF EXISTS image CASCADE;
CREATE TABLE image
(
  id         UUID                     DEFAULT uuid_generate_v4() NOT NULL
    CONSTRAINT image_pkey PRIMARY KEY,
  user_id    UUID                                                NOT NULL
    CONSTRAINT image_user_id_fkey REFERENCES user_,
  filename   TEXT                                                NOT NULL,
  url        TEXT                                                NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()              NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE,
  deleted_at TIMESTAMP WITH TIME ZONE
);