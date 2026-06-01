CREATE TABLE style_profiles (
    id                      BIGSERIAL       PRIMARY KEY,
    user_id                 BIGINT          NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,

    -- Body measurements & shape
    height                  VARCHAR(20),
    weight                  VARCHAR(20),
    body_type               VARCHAR(100),
    body_shape              VARCHAR(50),

    -- Skin
    skin_tone_group         VARCHAR(20),

    -- Style preferences stored as JSONB arrays for flexibility.
    -- This avoids a proliferation of join tables for list fields
    -- and allows the quiz to evolve without schema migrations.
    fashion_approaches      JSONB,
    style_personas          JSONB,
    color_palettes          JSONB,
    fabric_preferences      JSONB,
    pattern_preferences     JSONB,
    silhouette_preferences  JSONB,
    emphasis_areas          JSONB,

    -- Clothing & shoe sizes
    size_top                VARCHAR(10),
    size_bottom             VARCHAR(10),
    size_dress              VARCHAR(10),
    size_shoes              VARCHAR(10),

    -- Monthly budget estimates (GBP)
    budget_tops_bottoms     INTEGER,
    budget_shoes            INTEGER,
    budget_accessories      INTEGER,
    budget_bags             INTEGER,

    -- Style Swipe results (IDs of liked/disliked swipe images)
    swipe_liked_image_ids   JSONB,
    swipe_disliked_image_ids JSONB,

    -- Optional media uploaded during quiz
    wardrobe_photo_url      VARCHAR(500),

    -- Social inspiration links
    instagram_url           VARCHAR(255),
    pinterest_url           VARCHAR(255),

    -- Quiz completion status
    is_completed            BOOLEAN         NOT NULL DEFAULT FALSE,
    completed_at            TIMESTAMP,

    created_at              TIMESTAMP       NOT NULL,
    updated_at              TIMESTAMP
);
