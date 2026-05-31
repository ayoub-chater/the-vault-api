CREATE TABLE users (
    id                  BIGSERIAL       PRIMARY KEY,
    email               VARCHAR(255)    NOT NULL UNIQUE,
    password_hash       VARCHAR(255),
    first_name          VARCHAR(100)    NOT NULL,
    last_name           VARCHAR(100)    NOT NULL,
    phone               VARCHAR(20),
    phone_country_code  VARCHAR(10),
    country             VARCHAR(100),
    date_of_birth       DATE,
    gender              VARCHAR(10),
    role                VARCHAR(20)     NOT NULL DEFAULT 'USER',
    is_email_verified   BOOLEAN         NOT NULL DEFAULT FALSE,
    profile_type        VARCHAR(10)     NOT NULL DEFAULT 'NONE',
    profile_picture_url VARCHAR(500),
    key_balance         INTEGER         NOT NULL DEFAULT 0,
    created_at          TIMESTAMP       NOT NULL,
    updated_at          TIMESTAMP
);
