CREATE TABLE app_assets (
    id          BIGSERIAL    PRIMARY KEY,
    key         VARCHAR(100) NOT NULL UNIQUE,
    url         VARCHAR(500) NOT NULL,
    description VARCHAR(255),
    updated_at  TIMESTAMP    NOT NULL DEFAULT NOW()
);

INSERT INTO app_assets (key, url, description) VALUES
('auth.login.background',  '/images/auth/login-background.png',  'Background image shown on the login screen'),
('auth.signup.background', '/images/auth/signup-background.png', 'Background image shown on the sign up screen'),
('branding.logo',          '/images/branding/logo.png',          'The Vault V logo');
