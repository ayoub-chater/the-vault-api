CREATE TABLE service_catalog (
    id              BIGSERIAL       PRIMARY KEY,
    slug            VARCHAR(100)    NOT NULL UNIQUE,
    name            VARCHAR(100)    NOT NULL,
    tagline         VARCHAR(255),
    description     TEXT,
    note            TEXT,
    cover_image_url VARCHAR(500),
    display_order   INTEGER         NOT NULL
);
