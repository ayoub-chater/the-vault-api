CREATE TABLE social_providers (
    id          BIGSERIAL       PRIMARY KEY,
    user_id     BIGINT          NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    provider    VARCHAR(20)     NOT NULL,
    provider_id VARCHAR(255)    NOT NULL,
    created_at  TIMESTAMP       NOT NULL,
    UNIQUE (provider, provider_id)
);
