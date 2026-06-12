CREATE TABLE consultant_portfolio_photos (
    id            BIGSERIAL    PRIMARY KEY,
    consultant_id BIGINT       NOT NULL REFERENCES image_consultants(id),
    image_url     VARCHAR(500) NOT NULL,
    display_order INTEGER      NOT NULL DEFAULT 0
);

INSERT INTO users (email, first_name, last_name, role, is_email_verified, profile_type, key_balance, created_at) VALUES
    ('alhiane.youssra@thevault.io', 'Alhiane', 'Youssra', 'IMAGE_CONSULTANT', TRUE, 'NONE', 0, now()),
    ('sophie.whitman@thevault.io',  'Sophie',  'Whitman', 'IMAGE_CONSULTANT', TRUE, 'NONE', 0, now()),
    ('marcus.bellamy@thevault.io',  'Marcus',  'Bellamy', 'IMAGE_CONSULTANT', TRUE, 'NONE', 0, now()),
    ('elena.castillo@thevault.io',  'Elena',   'Castillo','IMAGE_CONSULTANT', TRUE, 'NONE', 0, now());

INSERT INTO image_consultants (user_id, display_name, avatar_url, bio, specialties, is_available) VALUES
    ((SELECT id FROM users WHERE email = 'alhiane.youssra@thevault.io'),
     'Alhiane Youssra',
     '/images/consultants/alhiane-youssra/avatar.jpg',
     'Image consultant specialising in luxury eveningwear and red-carpet styling, with an eye for timeless silhouettes.',
     '["LUXURY","SIGNATURE","EVENING_WEAR"]',
     TRUE),
    ((SELECT id FROM users WHERE email = 'sophie.whitman@thevault.io'),
     'Sophie Whitman',
     '/images/consultants/sophie-whitman/avatar.jpg',
     'Curates minimalist, contemporary wardrobes for busy professionals who want effortless polish.',
     '["CONTEMPORARY","MINIMALIST","OFFICEWEAR"]',
     TRUE),
    ((SELECT id FROM users WHERE email = 'marcus.bellamy@thevault.io'),
     'Marcus Bellamy',
     '/images/consultants/marcus-bellamy/avatar.jpg',
     'Streetwear and sportswear specialist, blending statement pieces with everyday comfort.',
     '["STREETWEAR","CASUAL","SPORTSWEAR"]',
     TRUE),
    ((SELECT id FROM users WHERE email = 'elena.castillo@thevault.io'),
     'Elena Castillo',
     '/images/consultants/elena-castillo/avatar.jpg',
     'Champions sustainable and vintage-inspired wardrobes built around quality and longevity.',
     '["SUSTAINABLE","BOHEMIAN","VINTAGE"]',
     TRUE);

INSERT INTO consultant_portfolio_photos (consultant_id, image_url, display_order) VALUES
    ((SELECT id FROM image_consultants WHERE display_name = 'Alhiane Youssra'), '/images/consultants/alhiane-youssra/portfolio-1.jpg', 1),
    ((SELECT id FROM image_consultants WHERE display_name = 'Alhiane Youssra'), '/images/consultants/alhiane-youssra/portfolio-2.jpg', 2),
    ((SELECT id FROM image_consultants WHERE display_name = 'Alhiane Youssra'), '/images/consultants/alhiane-youssra/portfolio-3.jpg', 3),
    ((SELECT id FROM image_consultants WHERE display_name = 'Sophie Whitman'),  '/images/consultants/sophie-whitman/portfolio-1.jpg',  1),
    ((SELECT id FROM image_consultants WHERE display_name = 'Sophie Whitman'),  '/images/consultants/sophie-whitman/portfolio-2.jpg',  2),
    ((SELECT id FROM image_consultants WHERE display_name = 'Sophie Whitman'),  '/images/consultants/sophie-whitman/portfolio-3.jpg',  3),
    ((SELECT id FROM image_consultants WHERE display_name = 'Marcus Bellamy'),  '/images/consultants/marcus-bellamy/portfolio-1.jpg',  1),
    ((SELECT id FROM image_consultants WHERE display_name = 'Marcus Bellamy'),  '/images/consultants/marcus-bellamy/portfolio-2.jpg',  2),
    ((SELECT id FROM image_consultants WHERE display_name = 'Marcus Bellamy'),  '/images/consultants/marcus-bellamy/portfolio-3.jpg',  3),
    ((SELECT id FROM image_consultants WHERE display_name = 'Elena Castillo'),  '/images/consultants/elena-castillo/portfolio-1.jpg',  1),
    ((SELECT id FROM image_consultants WHERE display_name = 'Elena Castillo'),  '/images/consultants/elena-castillo/portfolio-2.jpg',  2),
    ((SELECT id FROM image_consultants WHERE display_name = 'Elena Castillo'),  '/images/consultants/elena-castillo/portfolio-3.jpg',  3);
