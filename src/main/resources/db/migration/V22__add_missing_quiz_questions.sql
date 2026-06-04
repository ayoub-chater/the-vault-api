-- ─────────────────────────────────────────────────────────────────────────────
-- Missing questions identified from Figma screens
-- ─────────────────────────────────────────────────────────────────────────────

INSERT INTO style_quiz_questions
    (question_number, section, question_text, question_type, placeholder_text, is_required, metadata, display_group, display_order)
VALUES

-- Q33-Q35: Colors gravitate/avoid + jewelry — all on the same screen
(33, 'SECTION_1', 'Which colors do you gravitate towards?',
     'TEXT', 'Neutrals, Jewel Tones, Pastels, etc.', false, NULL, 'color_jewelry', 33),

(34, 'SECTION_1', 'Which colors do you prefer to avoid?',
     'TEXT', 'Reds, Yellows, Blues, etc.', false, NULL, 'color_jewelry', 34),

(35, 'SECTION_1', 'What type of jewelry do you prefer?',
     'TEXT', 'Gold tones, Silver tones, etc.', false, NULL, 'color_jewelry', 35),

-- Q36: Specific color dislikes (multi-select with color circles)
(36, 'SECTION_1', 'Which colors do you dislike?',
     'MULTI_SELECT', NULL, false, NULL, NULL, 36),

-- Q37: Wardrobe photo sharing
(37, 'SECTION_1', 'Would you like to share photos of your favorite styles or items in your wardrobe?',
     'SINGLE_SELECT', NULL, false, NULL, NULL, 37),

-- Q38: Fit concerns (free text)
(38, 'SECTION_1', 'Do you have any specific fit concerns?',
     'TEXT', 'e.g. sleeve length, pant length, chest fit', false, NULL, NULL, 38),

-- Q39: Personalized analysis
(39, 'SECTION_1', 'Would you be interested in a personalized body and color analysis?',
     'SINGLE_SELECT', NULL, false, NULL, NULL, 39),

-- Q40: Preferred fit for tops (image-based)
(40, 'SECTION_2', 'What is your preferred fit for tops?',
     'SINGLE_SELECT_IMAGE', NULL, false, NULL, NULL, 40),

-- Q41: Stylist expertise areas (image-based)
(41, 'SECTION_2', 'Stylist Expertise Areas',
     'MULTI_SELECT_IMAGE', 'Select the areas you need the most help with', false, NULL, NULL, 41),

-- Q42: Accessories to avoid (image-based)
(42, 'SECTION_2', 'Are there any accessories you''d like to avoid?',
     'MULTI_SELECT_IMAGE', NULL, false, NULL, NULL, 42);

-- ─────────────────────────────────────────────────────────────────────────────
-- OPTIONS
-- ─────────────────────────────────────────────────────────────────────────────

-- Q36: Color dislikes (16 color circles)
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 36)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Red',      'RED',      1),
((SELECT id FROM q), 'Blue',     'BLUE',     2),
((SELECT id FROM q), 'Green',    'GREEN',    3),
((SELECT id FROM q), 'Yellow',   'YELLOW',   4),
((SELECT id FROM q), 'Cyan',     'CYAN',     5),
((SELECT id FROM q), 'Teal',     'TEAL',     6),
((SELECT id FROM q), 'Maroon',   'MAROON',   7),
((SELECT id FROM q), 'Lavender', 'LAVENDER', 8),
((SELECT id FROM q), 'Ivory',    'IVORY',    9),
((SELECT id FROM q), 'Gold',     'GOLD',     10),
((SELECT id FROM q), 'Black',    'BLACK',    11),
((SELECT id FROM q), 'Coral',    'CORAL',    12),
((SELECT id FROM q), 'Magenta',  'MAGENTA',  13),
((SELECT id FROM q), 'White',    'WHITE',    14),
((SELECT id FROM q), 'Beige',    'BEIGE',    15),
((SELECT id FROM q), 'Tan',      'TAN',      16);

-- Q37: Wardrobe photo sharing
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 37)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Yes, I''d love to share',   'YES',           1),
((SELECT id FROM q), 'No preference',             'NO_PREFERENCE', 2);

-- Q39: Personalized analysis
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 39)
INSERT INTO style_quiz_options (question_id, option_text, option_value, display_order) VALUES
((SELECT id FROM q), 'Yes',           'YES',     1),
((SELECT id FROM q), 'I''m curious',  'CURIOUS', 2);

-- Q40: Fit for tops (outfit photos)
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 40)
INSERT INTO style_quiz_options (question_id, option_text, option_value, image_url, display_order) VALUES
((SELECT id FROM q), 'Fitted',   'FITTED',   '/images/quiz/fits/fit-tops-fitted.png',   1),
((SELECT id FROM q), 'Straight', 'STRAIGHT', '/images/quiz/fits/fit-tops-straight.png', 2),
((SELECT id FROM q), 'Loose',    'LOOSE',    '/images/quiz/fits/fit-tops-loose.png',    3);

-- Q41: Stylist expertise areas (6 wired, 2 pending export from Figma)
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 41)
INSERT INTO style_quiz_options (question_id, option_text, option_value, image_url, display_order) VALUES
((SELECT id FROM q), 'Capsule Wardrobe Essentials', 'CAPSULE_WARDROBE',  '/images/quiz/expertise/expertise-capsule-wardrobe.png',      1),
((SELECT id FROM q), 'Everyday Elegance',           'EVERYDAY_ELEGANCE', '/images/quiz/expertise/expertise-everyday-elegance.png',     2),
((SELECT id FROM q), 'Business Professional',       'BUSINESS_PRO',      '/images/quiz/expertise/expertise-business-professional.png', 3),
((SELECT id FROM q), 'Travel & Holiday Wardrobe',   'TRAVEL_HOLIDAY',    '/images/quiz/expertise/expertise-travel-holiday.png',        4),
((SELECT id FROM q), 'Jewelry & Accessories',        'JEWELRY_ACC', '/images/quiz/expertise/expertise-jewelry-accessories.png', 5),
((SELECT id FROM q), 'Night Out & Special Occasions','NIGHT_OUT',   '/images/quiz/expertise/expertise-night-out.png',           6),
((SELECT id FROM q), 'Vintage Finds',               'VINTAGE_FINDS',     '/images/quiz/expertise/expertise-vintage-finds.png',         7),
((SELECT id FROM q), 'Red Carpet & Events',         'RED_CARPET',        '/images/quiz/expertise/expertise-red-carpet.png',            8);

-- Q42: Accessories to avoid
WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 42)
INSERT INTO style_quiz_options (question_id, option_text, option_value, image_url, display_order) VALUES
((SELECT id FROM q), 'Earrings',  'EARRINGS',  '/images/quiz/accessories/accessory-earrings.png',   1),
((SELECT id FROM q), 'Necklaces', 'NECKLACES', '/images/quiz/accessories/accessory-necklaces.png',  2),
((SELECT id FROM q), 'Bracelets', 'BRACELETS', '/images/quiz/accessories/accessory-bracelets.png',  3),
((SELECT id FROM q), 'Rings',     'RINGS',     '/images/quiz/accessories/accessory-rings.png',      4),
((SELECT id FROM q), 'Hats',      'HATS',      '/images/quiz/accessories/accessory-hats.png',       5),
((SELECT id FROM q), 'Scarves',   'SCARVES',   '/images/quiz/accessories/accessory-scarves.png',    6);
