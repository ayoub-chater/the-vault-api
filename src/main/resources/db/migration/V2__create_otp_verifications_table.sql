CREATE TABLE otp_verifications (
    id          BIGSERIAL       PRIMARY KEY,
    user_id     BIGINT          NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    code        VARCHAR(4)      NOT NULL,
    expires_at  TIMESTAMP       NOT NULL,
    is_used     BOOLEAN         NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP       NOT NULL
);
