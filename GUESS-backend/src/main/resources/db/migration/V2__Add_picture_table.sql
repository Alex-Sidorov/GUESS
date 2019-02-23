DROP TABLE IF EXISTS picture;
CREATE TABLE picture (
  id          UUID        PRIMARY KEY,
  user_id     UUID        NOT NULL REFERENCES guess_user(id),
  url         TEXT        NOT NULL,
  created_at  TIMESTAMPTZ NOT NULL,
  updated_at  TIMESTAMPTZ,
  deleted_at  TIMESTAMPTZ
);

CREATE INDEX picture_user_id_fkey ON picture(user_id);
