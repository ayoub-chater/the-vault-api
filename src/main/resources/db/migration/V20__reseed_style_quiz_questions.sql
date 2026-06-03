TRUNCATE TABLE style_quiz_options, style_quiz_questions RESTART IDENTITY;

-- ─────────────────────────────────────────────────────────────────────────────
-- SECTION 1 - Text / Select questions
-- ─────────────────────────────────────────────────────────────────────────────

INSERT INTO style_quiz_questions
    (question_number, section, question_text, question_type, placeholder_text, is_required, metadata, display_order)
VALUES
-- Q1
(1,  'SECTION_1', 'Which best describes your body type?',
     'SINGLE_SELECT',  NULL, false, NULL, 1),

-- Q2
(2,  'SECTION_1', 'Which of these best describes your overall approach to fashion?',
     'SINGLE_SELECT',  NULL, true,  NULL, 2),

-- Q3
(3,  'SECTION_1', 'What are your top style goals right now?',
     'MULTI_SELECT',   NULL, true,  NULL, 3),

-- Q4
(4,  'SECTION_1', 'What type of clothing do you usually wear?',
     'MULTI_SELECT',   NULL, true,  NULL, 4),

-- Q5-Q8: Budget sliders (one per category matching Figma screen)
(5,  'SECTION_1', 'Monthly budget for tops',
     'BUDGET_SLIDER',  NULL, false, '{"min":50,"max":1000}', 5),

(6,  'SECTION_1', 'Monthly budget for dresses',
     'BUDGET_SLIDER',  NULL, false, '{"min":50,"max":1000}', 6),

(7,  'SECTION_1', 'Monthly budget for bottoms',
     'BUDGET_SLIDER',  NULL, false, '{"min":50,"max":1000}', 7),

(8,  'SECTION_1', 'Monthly budget for bags',
     'BUDGET_SLIDER',  NULL, false, '{"min":50,"max":2000}', 8),

(9,  'SECTION_1', 'Monthly budget for shoes',
     'BUDGET_SLIDER',  NULL, false, '{"min":50,"max":1000}', 9),

-- Q10-Q11: Physical stats
(10, 'SECTION_1', 'How tall are you?',
     'TEXT',           'e.g. 165 cm or 5''5"', false, NULL, 10),

(11, 'SECTION_1', 'What is your weight?',
     'TEXT',           'e.g. 60 kg or 130 lbs', false, NULL, 11),

-- Q12-Q13: Modesty / Hijab
(12, 'SECTION_1', 'Do you dress modestly?',
     'SINGLE_SELECT',  NULL, false, NULL, 12),

(13, 'SECTION_1', 'Are you hijabi?',
     'SINGLE_SELECT',  NULL, false, NULL, 13),

-- Q14-Q16: Openness & attitude
(14, 'SECTION_1', 'Are you open to exploring new brands and styles?',
     'SINGLE_SELECT',  NULL, true,  NULL, 14),

(15, 'SECTION_1', 'How adventurous are you with fashion trends?',
     'SINGLE_SELECT',  NULL, true,  NULL, 15),

(16, 'SECTION_1', 'Would you like us to prioritize your sustainable and ethical fashion options?',
     'SINGLE_SELECT',  NULL, false, NULL, 16),

-- Q17: Fashion dilemma
(17, 'SECTION_1', 'What is your current fashion dilemma?',
     'MULTI_SELECT',   NULL, false, NULL, 17),

-- Q18-Q20: Stylist preferences
(18, 'SECTION_1', 'How involved would you like to be in the styling process?',
     'SINGLE_SELECT',  NULL, true,  NULL, 18),

(19, 'SECTION_1', 'Which of these scenarios best describes your current style needs?',
     'SINGLE_SELECT',  NULL, true,  NULL, 19),

(20, 'SECTION_1', 'How hands-on would you like your stylist to be?',
     'SINGLE_SELECT',  NULL, true,  NULL, 20),

-- Q21-Q23: Social / Media
(21, 'SECTION_1', 'Share your Instagram or Pinterest board so we can get a better sense of your style',
     'TEXT',           'https://instagram.com/...', false, NULL, 21),

(22, 'SECTION_1', 'Upload a full-body photo to help your stylist with sizing and fit',
     'TEXT',           'Paste an image URL...', false, NULL, 22),

(23, 'SECTION_1', 'Anything else you would like your stylist to know?',
     'TEXT',           'Free text for additional notes...', false, NULL, 23),

-- ─────────────────────────────────────────────────────────────────────────────
-- SECTION 2 - Image-based questions
-- ─────────────────────────────────────────────────────────────────────────────

-- Q24: Body type (silhouettes)
(24, 'SECTION_2', 'What is your body type?',
     'SINGLE_SELECT_IMAGE', NULL, true, NULL, 24),

-- Q25: Skin tone (face/neck photos)
(25, 'SECTION_2', 'What is your skin tone?',
     'SINGLE_SELECT_IMAGE', NULL, true, NULL, 25),

-- Q26: Style personality (collage images)
(26, 'SECTION_2', 'Which of these styles best reflect your personality?',
     'MULTI_SELECT_IMAGE', NULL, true, NULL, 26),

-- Q27: Color palettes (palette collage images)
(27, 'SECTION_2', 'What colors do you gravitate towards?',
     'MULTI_SELECT_IMAGE', NULL, true, NULL, 27),

-- Q28: Fabric textures (Cotton, Silk, Linen, etc.)
(28, 'SECTION_2', 'Do you have any specific fabric preferences or aversions?',
     'MULTI_SELECT_IMAGE', NULL, false, NULL, 28),

-- Q29: Fabric patterns (large swatch grid)
(29, 'SECTION_2', 'What type of pattern or print do you prefer?',
     'MULTI_SELECT_IMAGE', NULL, false, NULL, 29),

-- Q30: Emphasis areas (body part photos)
(30, 'SECTION_2', 'What do you typically emphasize in your outfits?',
     'MULTI_SELECT_IMAGE', NULL, false, NULL, 30),

-- Q31: Downplay areas (body part photos)
(31, 'SECTION_2', 'Are there any areas you prefer to downplay?',
     'MULTI_SELECT_IMAGE', NULL, false, NULL, 31),

-- Q32: Clothing fit (outfit photos)
(32, 'SECTION_2', 'How do you prefer your clothes to fit?',
     'SINGLE_SELECT_IMAGE', NULL, true, NULL, 32);

-- ─────────────────────────────────────────────────────────────────────────────
-- OPTIONS
-- ─────────────────────────────────────────────────────────────────────────────

-- Q1: Body type description
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 1)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Curvy',     'CURVY',     1),
((SELECT id FROM q), 'Petite',    'PETITE',    2),
((SELECT id FROM q), 'Tall',      'TALL',      3),
((SELECT id FROM q), 'Plus Size', 'PLUS_SIZE', 4),
((SELECT id FROM q), 'Fit',       'FIT',       5),
((SELECT id FROM q), 'Expecting', 'EXPECTING', 6);

-- Q2: Fashion approach
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 2)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'I love following the latest trends',                    'TREND_FOLLOWER',  1),
((SELECT id FROM q), 'I prefer to invest in timeless, quality pieces',        'TIMELESS',        2),
((SELECT id FROM q), 'I mix high-end with more accessible brands',            'MIX_HIGH_LOW',    3),
((SELECT id FROM q), 'I enjoy experimenting with different styles',           'EXPERIMENTALIST', 4);

-- Q3: Style goals
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 3)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Elevate my current wardrobe',                  'ELEVATE',    1),
((SELECT id FROM q), 'Introduce more color and bold pieces',         'MORE_COLOR', 2),
((SELECT id FROM q), 'Simplify my wardrobe with key staples',        'SIMPLIFY',   3),
((SELECT id FROM q), 'Experiment with new trends and styles',        'EXPERIMENT', 4),
((SELECT id FROM q), 'Find pieces that better suit my body shape',   'BODY_FIT',   5);

-- Q4: Clothing type
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 4)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Casual',           'CASUAL',           1),
((SELECT id FROM q), 'Workwear',         'WORKWEAR',         2),
((SELECT id FROM q), 'Social Occasions', 'SOCIAL_OCCASIONS', 3),
((SELECT id FROM q), 'On trend',         'ON_TREND',         4),
((SELECT id FROM q), 'Parent-Friendly',  'PARENT_FRIENDLY',  5);

-- Q12: Modest dressing
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 12)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Yes', 'YES', 1),
((SELECT id FROM q), 'No',  'NO',  2);

-- Q13: Hijabi
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 13)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Yes', 'YES', 1),
((SELECT id FROM q), 'No',  'NO',  2);

-- Q14: Open to new brands
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 14)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Yes, definitely',               'YES_DEFINITELY', 1),
((SELECT id FROM q), 'I''m curious',                  'CURIOUS',        2),
((SELECT id FROM q), 'I prefer sticking to what I know', 'PREFER_KNOWN', 3);

-- Q15: Adventurous with trends
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 15)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Not at all', 'NOT_AT_ALL', 1),
((SELECT id FROM q), 'Somewhat',   'SOMEWHAT',   2),
((SELECT id FROM q), 'Very',       'VERY',       3);

-- Q16: Sustainable options
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 16)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Yes, sustainability is important', 'YES',          1),
((SELECT id FROM q), 'No preference',                    'NO_PREFERENCE',2);

-- Q17: Fashion dilemma
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 17)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'I struggle with finding the perfect fit',                       'FIT_STRUGGLE',     1),
((SELECT id FROM q), 'I want to incorporate more color into my wardrobe',             'MORE_COLOR',       2),
((SELECT id FROM q), 'I''m looking to upgrade my style without losing comfort',       'UPGRADE_COMFORT',  3),
((SELECT id FROM q), 'My time is valuable, so I leave it in the hands of the experts','DELEGATE',         4),
((SELECT id FROM q), 'I want to make sustainable fashion choices',                   'SUSTAINABLE',      5),
((SELECT id FROM q), 'I''m not sure how to accessorize my outfits',                  'ACCESSORIZE',      6);

-- Q18: Styling involvement
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 18)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'I''d love to be part of the journey, collaborating on every detail',    'COLLABORATIVE',  1),
((SELECT id FROM q), 'I prefer a curated selection without too much back-and-forth',          'CURATED',        2),
((SELECT id FROM q), 'I''m happy to be surprised with what you come up with',                 'SURPRISE',       3);

-- Q19: Style needs scenario
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 19)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Elevating my daily wardrobe with a touch of luxury',              'ELEVATE_DAILY',  1),
((SELECT id FROM q), 'Curating a dream wardrobe for an upcoming event',                 'EVENT_WARDROBE', 2),
((SELECT id FROM q), 'Refreshing my closet with seasonal must-haves',                   'SEASONAL_REFRESH',3),
((SELECT id FROM q), 'Completely transforming my style; I''m ready for a new look',     'FULL_TRANSFORM', 4);

-- Q20: Stylist hands-on level
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 20)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'I trust them completely; I''m open to new ideas',              'FULL_TRUST',    1),
((SELECT id FROM q), 'I''d like to collaborate closely; let''s create magic together','COLLABORATE',   2),
((SELECT id FROM q), 'I prefer subtle guidance; just refining my current style',     'SUBTLE_GUIDE',  3);

-- ─── SECTION 2 OPTIONS (image-based) ─────────────────────────────────────────

-- Q24: Body type (silhouettes — shape-01 to shape-05)
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 24)
INSERT INTO style_quiz_options (question_id, option_text, option_value, image_url, display_order) VALUES
((SELECT id FROM q), 'Hourglass',          'HOURGLASS',         '/images/quiz/body-shapes/shape-01.png', 1),
((SELECT id FROM q), 'Rectangle',          'RECTANGLE',         '/images/quiz/body-shapes/shape-02.png', 2),
((SELECT id FROM q), 'Pear',               'PEAR',              '/images/quiz/body-shapes/shape-03.png', 3),
((SELECT id FROM q), 'Inverted Triangle',  'INVERTED_TRIANGLE', '/images/quiz/body-shapes/shape-04.png', 4),
((SELECT id FROM q), 'Athletic',           'ATHLETIC',          '/images/quiz/body-shapes/shape-05.png', 5);

WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 25)
INSERT INTO style_quiz_options (question_id, option_text, option_value, image_url, display_order) VALUES
((SELECT id FROM q), 'Porcelain',   'PORCELAIN',    '/images/quiz/skin-tones/skin-01.png', 1),
((SELECT id FROM q), 'Bisque',      'BISQUE',       '/images/quiz/skin-tones/skin-02.png', 2),
((SELECT id FROM q), 'Ivory',       'IVORY',        '/images/quiz/skin-tones/skin-03.png', 3),
((SELECT id FROM q), 'Buff',        'BUFF',         '/images/quiz/skin-tones/skin-04.png', 4),
((SELECT id FROM q), 'Birch',       'BIRCH',        '/images/quiz/skin-tones/skin-05.png', 5),
((SELECT id FROM q), 'Beige',       'BEIGE',        '/images/quiz/skin-tones/skin-06.png', 6),
((SELECT id FROM q), 'Warm Beige',  'WARM_BEIGE',   '/images/quiz/skin-tones/skin-07.png', 7),
((SELECT id FROM q), 'Rose Beige',  'ROSE_BEIGE',   '/images/quiz/skin-tones/skin-08.png', 8),
((SELECT id FROM q), 'Desert',      'DESERT',       '/images/quiz/skin-tones/skin-09.png', 9),
((SELECT id FROM q), 'Nude',        'NUDE',         '/images/quiz/skin-tones/skin-10.png', 10),
((SELECT id FROM q), 'Olive Nude',  'OLIVE_NUDE',   '/images/quiz/skin-tones/skin-11.png', 11),
((SELECT id FROM q), 'Sandalwood',  'SANDALWOOD',   '/images/quiz/skin-tones/skin-12.png', 12),
((SELECT id FROM q), 'Tawny',       'TAWNY',        '/images/quiz/skin-tones/skin-13.png', 13),
((SELECT id FROM q), 'Caramel',     'CARAMEL',      '/images/quiz/skin-tones/skin-14.png', 14),
((SELECT id FROM q), 'Chestnut',    'CHESTNUT',     '/images/quiz/skin-tones/skin-15.png', 15),
((SELECT id FROM q), 'Rosewood',    'ROSEWOOD',     '/images/quiz/skin-tones/skin-16.png', 16),
((SELECT id FROM q), 'Toffee',      'TOFFEE',       '/images/quiz/skin-tones/skin-17.png', 17),
((SELECT id FROM q), 'Cocoa',       'COCOA',        '/images/quiz/skin-tones/skin-18.png', 18),
((SELECT id FROM q), 'Neutro',      'NEUTRO',       '/images/quiz/skin-tones/skin-19.png', 19),
((SELECT id FROM q), 'Quente',      'QUENTE',       '/images/quiz/skin-tones/skin-20.png', 20);

-- Q26: Style personality (all 9 individual outfit photos)
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 26)
INSERT INTO style_quiz_options (question_id, option_text, option_value, image_url, display_order) VALUES
((SELECT id FROM q), 'Timeless Elegance',    'TIMELESS_ELEGANCE',  '/images/quiz/styles/style-01.png', 1),
((SELECT id FROM q), 'Modern Minimalism',    'MODERN_MINIMALISM',  '/images/quiz/styles/style-02.png', 2),
((SELECT id FROM q), 'Effortless Chic',      'EFFORTLESS_CHIC',    '/images/quiz/styles/style-03.png', 3),
((SELECT id FROM q), 'Glamorous and Sexy',   'GLAMOROUS_SEXY',     '/images/quiz/styles/style-04.png', 4),
((SELECT id FROM q), 'Bohemian Dreamer',     'BOHEMIAN_DREAMER',   '/images/quiz/styles/style-05.png', 5),
((SELECT id FROM q), 'Loose and Comfortable','LOOSE_COMFORTABLE',  '/images/quiz/styles/style-06.png', 6),
((SELECT id FROM q), 'Street Smart',         'STREET_SMART',       '/images/quiz/styles/style-07.png', 7),
((SELECT id FROM q), 'Structured and Sharp', 'STRUCTURED_SHARP',   '/images/quiz/styles/style-08.png', 8),
((SELECT id FROM q), 'Classic and Refined',  'CLASSIC_REFINED',    '/images/quiz/styles/style-09.png', 9);

-- Q27: Color palettes
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 27)
INSERT INTO style_quiz_options (question_id, option_text, option_value, image_url, display_order) VALUES
((SELECT id FROM q), 'Classic Neutrals', 'CLASSIC_NEUTRALS', '/images/quiz/color-palettes/palette-04.png', 1),
((SELECT id FROM q), 'Jewel Tones',      'JEWEL_TONES',      '/images/quiz/color-palettes/palette-02.png', 2),
((SELECT id FROM q), 'Earthy Tones',     'EARTHY_TONES',     '/images/quiz/color-palettes/palette-01.png', 3),
((SELECT id FROM q), 'Pastel Dreams',    'PASTEL_DREAMS',    '/images/quiz/color-palettes/palette-03.png', 4),
((SELECT id FROM q), 'Bold and Bright',  'BOLD_BRIGHT',      '/images/quiz/color-palettes/palette-06.png', 5),
((SELECT id FROM q), 'Monochrome',       'MONOCHROME',       '/images/quiz/color-palettes/palette-05.png', 6),
((SELECT id FROM q), 'Playful Prints',   'PLAYFUL_PRINTS',   '/images/quiz/color-palettes/palette-07.png', 7);

-- Q28: Fabric textures
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 28)
INSERT INTO style_quiz_options (question_id, option_text, option_value, image_url, display_order) VALUES
((SELECT id FROM q), 'Cotton',   'COTTON',   '/images/quiz/fabric-textures/fabric-cotton.png',   1),
((SELECT id FROM q), 'Silk',     'SILK',     '/images/quiz/fabric-textures/fabric-silk.png',     2),
((SELECT id FROM q), 'Linen',    'LINEN',    '/images/quiz/fabric-textures/fabric-linen.png',    3),
((SELECT id FROM q), 'Wool',     'WOOL',     '/images/quiz/fabric-textures/fabric-wool.png',     4),
((SELECT id FROM q), 'Faux Fur', 'FAUX_FUR', '/images/quiz/fabric-textures/fabric-faux-fur.png', 5),
((SELECT id FROM q), 'Leather',  'LEATHER',  '/images/quiz/fabric-textures/fabric-leather.png',  6);

-- Q29: Fabric patterns (pattern swatches — pattern-01 to pattern-45)
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 29)
INSERT INTO style_quiz_options (question_id, option_text, option_value, image_url, display_order) VALUES
((SELECT id FROM q), 'Tartan',        'TARTAN',         '/images/quiz/patterns/pattern-01.png', 1),
((SELECT id FROM q), 'Gingham',       'GINGHAM',        '/images/quiz/patterns/pattern-02.png', 2),
((SELECT id FROM q), 'Madras Check',  'MADRAS_CHECK',   '/images/quiz/patterns/pattern-03.png', 3),
((SELECT id FROM q), 'Buffalo Check', 'BUFFALO_CHECK',  '/images/quiz/patterns/pattern-04.png', 4),
((SELECT id FROM q), 'Houndstooth',   'HOUNDSTOOTH',    '/images/quiz/patterns/pattern-05.png', 5),
((SELECT id FROM q), 'Windowpane',    'WINDOWPANE',     '/images/quiz/patterns/pattern-06.png', 6),
((SELECT id FROM q), 'Chevron',       'CHEVRON',        '/images/quiz/patterns/pattern-07.png', 7),
((SELECT id FROM q), 'Ikat',          'IKAT',           '/images/quiz/patterns/pattern-08.png', 8),
((SELECT id FROM q), 'Herringbone',   'HERRINGBONE',    '/images/quiz/patterns/pattern-09.png', 9),
((SELECT id FROM q), 'Toile',         'TOILE',          '/images/quiz/patterns/pattern-10.png', 10),
((SELECT id FROM q), 'Greek Key',     'GREEK_KEY',      '/images/quiz/patterns/pattern-11.png', 11),
((SELECT id FROM q), 'Damask',        'DAMASK',         '/images/quiz/patterns/pattern-12.png', 12),
((SELECT id FROM q), 'Lattice',       'LATTICE',        '/images/quiz/patterns/pattern-13.png', 13),
((SELECT id FROM q), 'Trellis',       'TRELLIS',        '/images/quiz/patterns/pattern-14.png', 14),
((SELECT id FROM q), 'Ogee',          'OGEE',           '/images/quiz/patterns/pattern-15.png', 15),
((SELECT id FROM q), 'Argyle',        'ARGYLE',         '/images/quiz/patterns/pattern-16.png', 16),
((SELECT id FROM q), 'Moorish',       'MOORISH',        '/images/quiz/patterns/pattern-17.png', 17),
((SELECT id FROM q), 'Suzani',        'SUZANI',         '/images/quiz/patterns/pattern-18.png', 18),
((SELECT id FROM q), 'Scallop',       'SCALLOP',        '/images/quiz/patterns/pattern-19.png', 19),
((SELECT id FROM q), 'Stripe',        'STRIPE',         '/images/quiz/patterns/pattern-20.png', 20),
((SELECT id FROM q), 'Chinoiserie',   'CHINOISERIE',    '/images/quiz/patterns/pattern-21.png', 21),
((SELECT id FROM q), 'Quatrefoil',    'QUATREFOIL',     '/images/quiz/patterns/pattern-22.png', 22),
((SELECT id FROM q), 'Polka Dot',     'POLKA_DOT',      '/images/quiz/patterns/pattern-23.png', 23),
((SELECT id FROM q), 'Pinstripe',     'PINSTRIPE',      '/images/quiz/patterns/pattern-24.png', 24),
((SELECT id FROM q), 'Cabana Stripe', 'CABANA_STRIPE',  '/images/quiz/patterns/pattern-25.png', 25),
((SELECT id FROM q), 'Leopard',       'LEOPARD',        '/images/quiz/patterns/pattern-26.png', 26),
((SELECT id FROM q), 'Zebra',         'ZEBRA',          '/images/quiz/patterns/pattern-27.png', 27),
((SELECT id FROM q), 'Palm Leaf',     'PALM_LEAF',      '/images/quiz/patterns/pattern-28.png', 28),
((SELECT id FROM q), 'Banana Leaf',   'BANANA_LEAF',    '/images/quiz/patterns/pattern-29.png', 29),
((SELECT id FROM q), 'Fern Leaf',     'FERN_LEAF',      '/images/quiz/patterns/pattern-30.png', 30),
((SELECT id FROM q), 'Ditsy Floral',  'DITSY_FLORAL',   '/images/quiz/patterns/pattern-31.png', 31),
((SELECT id FROM q), 'Medallion',     'MEDALLION',      '/images/quiz/patterns/pattern-32.png', 32),
((SELECT id FROM q), 'Tie-Dye',       'TIE_DYE',        '/images/quiz/patterns/pattern-33.png', 33),
((SELECT id FROM q), 'Crosshatch',    'CROSSHATCH',     '/images/quiz/patterns/pattern-34.png', 34),
((SELECT id FROM q), 'Flame Stitch',  'FLAME_STITCH',   '/images/quiz/patterns/pattern-35.png', 35),
((SELECT id FROM q), 'Firework',      'FIREWORK',       '/images/quiz/patterns/pattern-36.png', 36),
((SELECT id FROM q), 'Ikat Weave',    'IKAT_WEAVE',     '/images/quiz/patterns/pattern-37.png', 37),
((SELECT id FROM q), 'Buta',          'BUTA',           '/images/quiz/patterns/pattern-38.png', 38),
((SELECT id FROM q), 'Dabu',          'DABU',           '/images/quiz/patterns/pattern-39.png', 39),
((SELECT id FROM q), 'Kalamkari',     'KALAMKARI',      '/images/quiz/patterns/pattern-40.png', 40),
((SELECT id FROM q), 'Mughal Floral', 'MUGHAL_FLORAL',  '/images/quiz/patterns/pattern-41.png', 41),
((SELECT id FROM q), 'Block Print',   'BLOCK_PRINT',    '/images/quiz/patterns/pattern-42.png', 42),
((SELECT id FROM q), 'Shibori',       'SHIBORI',        '/images/quiz/patterns/pattern-43.png', 43),
((SELECT id FROM q), 'Bagru',         'BAGRU',          '/images/quiz/patterns/pattern-44.png', 44),
((SELECT id FROM q), 'Jaal',          'JAAL',           '/images/quiz/patterns/pattern-45.png', 45);

-- Q30: Emphasis areas
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 30)
INSERT INTO style_quiz_options (question_id, option_text, option_value, image_url, display_order) VALUES
((SELECT id FROM q), 'Arms',       'ARMS',       '/images/quiz/body-areas/area-arms.png',      1),
((SELECT id FROM q), 'Shoulders',  'SHOULDERS',  '/images/quiz/body-areas/area-shoulders.png', 2),
((SELECT id FROM q), 'Cleavage',   'CLEAVAGE',   '/images/quiz/body-areas/area-cleavage.png',  3),
((SELECT id FROM q), 'Waist',      'WAIST',      '/images/quiz/body-areas/area-waist.png',     4),
((SELECT id FROM q), 'Legs',       'LEGS',       '/images/quiz/body-areas/area-legs.png',      5),
((SELECT id FROM q), 'Back',       'BACK',       '/images/quiz/body-areas/area-back.png',      6);

-- Q31: Downplay areas
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 31)
INSERT INTO style_quiz_options (question_id, option_text, option_value, image_url, display_order) VALUES
((SELECT id FROM q), 'Arms',        'ARMS',       '/images/quiz/body-areas/area-arms.png',       1),
((SELECT id FROM q), 'Cleavage',    'CLEAVAGE',   '/images/quiz/body-areas/area-cleavage.png',   2),
((SELECT id FROM q), 'Midsection',  'MIDSECTION', '/images/quiz/body-areas/area-midsection.png', 3),
((SELECT id FROM q), 'Rear',        'REAR',       '/images/quiz/body-areas/area-rear.png',       4);

-- Q32: Clothing fit (outfit photos from swipe folder)
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 32)
INSERT INTO style_quiz_options (question_id, option_text, option_value, image_url, display_order) VALUES
((SELECT id FROM q), 'Fitted and Tailored',   'FITTED',   '/images/quiz/fits/fit-01.png', 1),
((SELECT id FROM q), 'Straight and Relaxed',  'STRAIGHT', '/images/quiz/fits/fit-02.png', 2),
((SELECT id FROM q), 'Loose and Comfortable', 'LOOSE',    '/images/quiz/fits/fit-03.png', 3);
