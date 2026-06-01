CREATE TABLE style_swipe_images (
    id            BIGSERIAL    PRIMARY KEY,
    image_url     VARCHAR(500) NOT NULL,
    tags          JSONB,
    display_order INTEGER      NOT NULL DEFAULT 0,
    active        BOOLEAN      NOT NULL DEFAULT TRUE
);
