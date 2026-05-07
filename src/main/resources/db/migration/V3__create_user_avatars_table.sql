CREATE TABLE user_avatars (
    id              BIGSERIAL   PRIMARY KEY,
    user_id         BIGINT      NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
    style_config    JSONB,
    hair_config     JSONB,
    makeup_config   JSONB,
    face_config     JSONB,
    body_config     JSONB,
    updated_at      TIMESTAMP
);
