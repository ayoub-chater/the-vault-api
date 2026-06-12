CREATE TABLE subscription_plans (
    id                   BIGSERIAL      PRIMARY KEY,
    tier                 VARCHAR(20)    NOT NULL UNIQUE,
    display_name         VARCHAR(100)   NOT NULL,
    monthly_price        NUMERIC(10,2)  NOT NULL,
    yearly_price         NUMERIC(10,2)  NOT NULL,
    currency             VARCHAR(3)     NOT NULL DEFAULT 'GBP',
    consultation_minutes INTEGER        NOT NULL,
    look_board_limit     INTEGER        NOT NULL,
    chat_days            INTEGER        NOT NULL,
    features             JSONB,
    is_popular           BOOLEAN        NOT NULL DEFAULT FALSE,
    display_order        INTEGER        NOT NULL,
    is_active            BOOLEAN        NOT NULL DEFAULT TRUE
);

INSERT INTO subscription_plans
    (tier, display_name, monthly_price, yearly_price, currency, consultation_minutes, look_board_limit, chat_days, features, is_popular, display_order)
VALUES
    ('ESSENTIALS', 'The Styling Edit | Essentials', 250.00, 2400.00, 'GBP', 30, 3, 10,
     '["30-min consultation","3 look boards","Shopping links","5 wardrobe pieces incorporated","10-day direct chat"]',
     FALSE, 1),
    ('REFINED', 'The Styling Edit | Refined', 450.00, 4320.00, 'GBP', 45, 6, 24,
     '["45-min consultation","6 look boards","10 wardrobe pieces incorporated","1 seasonal capsule wardrobe","24-day unlimited chat","Vault luxury retail network access"]',
     TRUE, 2),
    ('SIGNATURE', 'The Styling Edit | Signature', 750.00, 7200.00, 'GBP', 60, 12, 45,
     '["1-hr virtual consultation","12 look boards","2 seasonal capsule wardrobes","Color + Body Analysis","Trend Adaptation","45-day unlimited chat","Seamstress access","Luxury retail pre-orders"]',
     FALSE, 3);
