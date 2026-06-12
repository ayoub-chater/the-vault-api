CREATE TABLE image_consultants (
    id           BIGSERIAL    PRIMARY KEY,
    user_id      BIGINT       NOT NULL REFERENCES users(id),
    display_name VARCHAR(150) NOT NULL,
    avatar_url   VARCHAR(500),
    bio          TEXT,
    specialties  JSONB,
    is_available BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at   TIMESTAMP    NOT NULL DEFAULT now()
);
