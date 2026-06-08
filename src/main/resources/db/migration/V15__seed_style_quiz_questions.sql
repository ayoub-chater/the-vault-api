-- Section 1 (Q1-Q24): Text, select, and budget questions
INSERT INTO style_quiz_questions (question_number, section, question_text, question_type, placeholder_text, is_required, metadata, display_order) VALUES
(1,  'SECTION_1', 'What is your height?',                                  'TEXT',          'e.g. 165 cm',                    false, NULL,                          1),
(2,  'SECTION_1', 'What is your weight?',                                  'TEXT',          'e.g. 60 kg',                     false, NULL,                          2),
(3,  'SECTION_1', 'How would you describe your body type?',                'TEXT',          'Describe in your own words...',  false, NULL,                          3),
(4,  'SECTION_1', 'What is your body shape?',                              'SINGLE_SELECT', NULL,                             true,  NULL,                          4),
(5,  'SECTION_1', 'What is your skin tone group?',                         'SINGLE_SELECT', NULL,                             true,  NULL,                          5),
(6,  'SECTION_1', 'Which best describes your fashion approach?',            'MULTI_SELECT',  NULL,                             true,  NULL,                          6),
(7,  'SECTION_1', 'Choose the style personas that resonate with you',       'MULTI_SELECT',  NULL,                             true,  NULL,                          7),
(8,  'SECTION_1', 'Which colour palettes speak to you?',                   'MULTI_SELECT',  NULL,                             true,  NULL,                          8),
(9,  'SECTION_1', 'What fabrics do you prefer wearing?',                   'MULTI_SELECT',  NULL,                             true,  NULL,                          9),
(10, 'SECTION_1', 'Monthly budget for tops and bottoms',                   'BUDGET_SLIDER', NULL,                             true,  '{"min":50,"max":1000}',        10),
(11, 'SECTION_1', 'Monthly budget for shoes',                              'BUDGET_SLIDER', NULL,                             true,  '{"min":50,"max":1000}',        11),
(12, 'SECTION_1', 'Monthly budget for accessories',                        'BUDGET_SLIDER', NULL,                             true,  '{"min":20,"max":500}',         12),
(13, 'SECTION_1', 'Monthly budget for bags',                               'BUDGET_SLIDER', NULL,                             true,  '{"min":50,"max":2000}',        13),
(14, 'SECTION_1', 'What is your top size?',                                'SINGLE_SELECT', NULL,                             false, NULL,                          14),
(15, 'SECTION_1', 'What is your bottom size?',                             'SINGLE_SELECT', NULL,                             false, NULL,                          15),
(16, 'SECTION_1', 'What is your dress size?',                              'SINGLE_SELECT', NULL,                             false, NULL,                          16),
(17, 'SECTION_1', 'What is your shoe size?',                               'SINGLE_SELECT', NULL,                             false, NULL,                          17),
(18, 'SECTION_1', 'Which areas of your body do you like to emphasise?',    'MULTI_SELECT',  NULL,                             false, NULL,                          18),
(19, 'SECTION_1', 'How would you describe your lifestyle?',                'MULTI_SELECT',  NULL,                             true,  NULL,                          19),
(20, 'SECTION_1', 'What occasions do you dress for most?',                 'MULTI_SELECT',  NULL,                             true,  NULL,                          20),
(21, 'SECTION_1', 'Where do you usually shop?',                            'MULTI_SELECT',  NULL,                             false, NULL,                          21),
(22, 'SECTION_1', 'Upload a photo of your current wardrobe',               'TEXT',          'Paste an image URL...',          false, NULL,                          22),
(23, 'SECTION_1', 'Your Instagram profile (optional)',                     'TEXT',          'https://instagram.com/...',      false, NULL,                          23),
(24, 'SECTION_1', 'Your Pinterest profile (optional)',                     'TEXT',          'https://pinterest.com/...',      false, NULL,                          24),

-- Section 2 (Q25-Q37): Visual image grid questions
(25, 'SECTION_2', 'Select the fits that appeal to you most',               'IMAGE_GRID',    NULL,                             true,  NULL,                          25),
(26, 'SECTION_2', 'Select the patterns you are drawn to',                  'IMAGE_GRID',    NULL,                             true,  NULL,                          26),
(27, 'SECTION_2', 'Choose the silhouettes you feel most comfortable in',   'IMAGE_GRID',    NULL,                             true,  NULL,                          27),
(28, 'SECTION_2', 'Select the necklines you prefer',                       'IMAGE_GRID',    NULL,                             false, NULL,                          28),
(29, 'SECTION_2', 'Which trouser styles appeal to you?',                   'IMAGE_GRID',    NULL,                             false, NULL,                          29),
(30, 'SECTION_2', 'Select your preferred shoe styles',                     'IMAGE_GRID',    NULL,                             false, NULL,                          30),
(31, 'SECTION_2', 'Which bag styles do you prefer?',                       'IMAGE_GRID',    NULL,                             false, NULL,                          31),
(32, 'SECTION_2', 'Select the jewellery styles you gravitate towards',     'IMAGE_GRID',    NULL,                             false, NULL,                          32),
(33, 'SECTION_2', 'Which outerwear styles appeal to you?',                 'IMAGE_GRID',    NULL,                             false, NULL,                          33),
(34, 'SECTION_2', 'Select looks that match your overall aesthetic',        'IMAGE_GRID',    NULL,                             true,  NULL,                          34),
(35, 'SECTION_2', 'Which fabric textures do you prefer?',                  'IMAGE_GRID',    NULL,                             false, NULL,                          35),
(36, 'SECTION_2', 'Select prints and patterns you love',                   'IMAGE_GRID',    NULL,                             false, NULL,                          36),
(37, 'SECTION_2', 'Choose the colour combinations you are drawn to',       'IMAGE_GRID',    NULL,                             false, NULL,                          37);

-- Options for Q4: Body shape
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 4)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Hourglass',          'HOURGLASS',         1),
((SELECT id FROM q), 'Pear',               'PEAR',              2),
((SELECT id FROM q), 'Apple',              'APPLE',             3),
((SELECT id FROM q), 'Rectangle',          'RECTANGLE',         4),
((SELECT id FROM q), 'Inverted Triangle',  'INVERTED_TRIANGLE', 5);

-- Options for Q5: Skin tone group
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 5)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Cool (FRIO)',    'FRIO',   1),
((SELECT id FROM q), 'Neutral',       'NEUTRO', 2),
((SELECT id FROM q), 'Warm (QUENTE)', 'QUENTE', 3);

-- Options for Q6: Fashion approach
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 6)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Classic & Timeless', 'CLASSIC',      1),
((SELECT id FROM q), 'Trendy & Fashion-forward', 'TRENDY', 2),
((SELECT id FROM q), 'Minimalist',         'MINIMALIST',   3),
((SELECT id FROM q), 'Bohemian',           'BOHEMIAN',     4),
((SELECT id FROM q), 'Eclectic & Bold',    'ECLECTIC',     5),
((SELECT id FROM q), 'Sporty & Casual',    'SPORTY',       6),
((SELECT id FROM q), 'Elegant & Luxurious','ELEGANT',      7);

-- Options for Q7: Style personas
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 7)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'The Professional',   'PROFESSIONAL', 1),
((SELECT id FROM q), 'The Romantic',       'ROMANTIC',     2),
((SELECT id FROM q), 'The Minimalist',     'MINIMALIST',   3),
((SELECT id FROM q), 'The Street Style',   'STREET',       4),
((SELECT id FROM q), 'The Bohemian',       'BOHEMIAN',     5),
((SELECT id FROM q), 'The Edgy',           'EDGY',         6),
((SELECT id FROM q), 'The Classic',        'CLASSIC',      7);

-- Options for Q8: Colour palettes
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 8)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Neutrals (beige, camel, white)',  'NEUTRALS',    1),
((SELECT id FROM q), 'Earth Tones (terracotta, olive)', 'EARTH_TONES', 2),
((SELECT id FROM q), 'Pastels',                         'PASTELS',     3),
((SELECT id FROM q), 'Bold & Vibrant',                  'BOLD',        4),
((SELECT id FROM q), 'Monochrome',                      'MONOCHROME',  5),
((SELECT id FROM q), 'Black & White',                   'BW',          6);

-- Options for Q9: Fabrics
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 9)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Cotton',   'COTTON',   1),
((SELECT id FROM q), 'Silk',     'SILK',     2),
((SELECT id FROM q), 'Linen',    'LINEN',    3),
((SELECT id FROM q), 'Wool',     'WOOL',     4),
((SELECT id FROM q), 'Denim',    'DENIM',    5),
((SELECT id FROM q), 'Leather',  'LEATHER',  6),
((SELECT id FROM q), 'Velvet',   'VELVET',   7),
((SELECT id FROM q), 'Knit',     'KNIT',     8);

-- Options for Q14-Q17: Sizes
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 14)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'XS', 'XS', 1),
((SELECT id FROM q), 'S',  'S',  2),
((SELECT id FROM q), 'M',  'M',  3),
((SELECT id FROM q), 'L',  'L',  4),
((SELECT id FROM q), 'XL', 'XL', 5),
((SELECT id FROM q), 'XXL','XXL',6);

WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 15)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), '34', '34', 1),
((SELECT id FROM q), '36', '36', 2),
((SELECT id FROM q), '38', '38', 3),
((SELECT id FROM q), '40', '40', 4),
((SELECT id FROM q), '42', '42', 5),
((SELECT id FROM q), '44', '44', 6),
((SELECT id FROM q), '46', '46', 7);

WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 16)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'XS', 'XS', 1),
((SELECT id FROM q), 'S',  'S',  2),
((SELECT id FROM q), 'M',  'M',  3),
((SELECT id FROM q), 'L',  'L',  4),
((SELECT id FROM q), 'XL', 'XL', 5);

WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 17)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'UK 3 / EU 36', '36', 1),
((SELECT id FROM q), 'UK 4 / EU 37', '37', 2),
((SELECT id FROM q), 'UK 5 / EU 38', '38', 3),
((SELECT id FROM q), 'UK 6 / EU 39', '39', 4),
((SELECT id FROM q), 'UK 7 / EU 40', '40', 5),
((SELECT id FROM q), 'UK 8 / EU 41', '41', 6),
((SELECT id FROM q), 'UK 9 / EU 42', '42', 7);

-- Options for Q18: Emphasis areas
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 18)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Neckline',   'NECKLINE',   1),
((SELECT id FROM q), 'Shoulders',  'SHOULDERS',  2),
((SELECT id FROM q), 'Waist',      'WAIST',      3),
((SELECT id FROM q), 'Hips',       'HIPS',       4),
((SELECT id FROM q), 'Legs',       'LEGS',       5),
((SELECT id FROM q), 'Arms',       'ARMS',       6);

-- Options for Q19: Lifestyle
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 19)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Corporate / Office', 'CORPORATE', 1),
((SELECT id FROM q), 'Creative / Artistic', 'CREATIVE', 2),
((SELECT id FROM q), 'Social & Events',    'SOCIAL',    3),
((SELECT id FROM q), 'Active & Outdoors',  'ACTIVE',    4),
((SELECT id FROM q), 'Travel & Remote',    'TRAVEL',    5),
((SELECT id FROM q), 'Homebody & Casual',  'CASUAL',    6);

-- Options for Q20: Occasions
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 20)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Work / Office',       'WORK',    1),
((SELECT id FROM q), 'Casual everyday',     'CASUAL',  2),
((SELECT id FROM q), 'Evenings & Dinners',  'EVENING', 3),
((SELECT id FROM q), 'Formal events',       'FORMAL',  4),
((SELECT id FROM q), 'Sports & Wellness',   'SPORTS',  5),
((SELECT id FROM q), 'Travel',              'TRAVEL',  6);

-- Options for Q21: Shopping habits
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 21)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Designer & Luxury',  'LUXURY',      1),
((SELECT id FROM q), 'Contemporary brands','CONTEMPORARY', 2),
((SELECT id FROM q), 'High street',        'HIGH_STREET', 3),
((SELECT id FROM q), 'Vintage & Secondhand','VINTAGE',    4),
((SELECT id FROM q), 'Sustainable brands', 'SUSTAINABLE', 5);
