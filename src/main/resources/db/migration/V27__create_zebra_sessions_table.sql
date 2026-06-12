CREATE TABLE zebra_sessions (
    id                    BIGSERIAL    PRIMARY KEY,
    user_id               BIGINT       NOT NULL REFERENCES users(id),
    status                VARCHAR(20)  NOT NULL DEFAULT 'IN_PROGRESS',
    matched_consultant_id BIGINT       REFERENCES image_consultants(id),
    style_profile_ref     BIGINT       REFERENCES style_profiles(id),
    created_at            TIMESTAMP    NOT NULL DEFAULT now(),
    completed_at          TIMESTAMP
);
