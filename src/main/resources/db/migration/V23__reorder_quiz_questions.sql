-- ─────────────────────────────────────────────────────────────────────────────
-- Step 1: Offset all question_numbers by +1000 to avoid update conflicts
-- ─────────────────────────────────────────────────────────────────────────────
UPDATE style_quiz_questions SET question_number = question_number + 1000;

-- ─────────────────────────────────────────────────────────────────────────────
-- Step 2: Set correct question_number and display_order per Figma screen order
-- ─────────────────────────────────────────────────────────────────────────────

-- Screen 1
UPDATE style_quiz_questions SET question_number = 1,  display_order = 1  WHERE question_number = 1001;
-- Screen 2
UPDATE style_quiz_questions SET question_number = 2,  display_order = 2  WHERE question_number = 1002;
-- Screen 3
UPDATE style_quiz_questions SET question_number = 3,  display_order = 3  WHERE question_number = 1003;
-- Screen 4: How do you prefer your clothes to fit? (was Q32)
UPDATE style_quiz_questions SET question_number = 4,  display_order = 4  WHERE question_number = 1032;
-- Screen 5: What do you typically emphasize? (was Q30)
UPDATE style_quiz_questions SET question_number = 5,  display_order = 5  WHERE question_number = 1030;
-- Screen 6: Areas to downplay (was Q31)
UPDATE style_quiz_questions SET question_number = 6,  display_order = 6  WHERE question_number = 1031;
-- Screen 7: Fabric preferences (was Q28)
UPDATE style_quiz_questions SET question_number = 7,  display_order = 7  WHERE question_number = 1028;
-- Screen 8 (color_jewelry group): colors gravitate TEXT (was Q33)
UPDATE style_quiz_questions SET question_number = 8,  display_order = 8  WHERE question_number = 1033;
-- Screen 8 (color_jewelry group): colors avoid TEXT (was Q34)
UPDATE style_quiz_questions SET question_number = 9,  display_order = 9  WHERE question_number = 1034;
-- Screen 8 (color_jewelry group): jewelry (was Q35)
UPDATE style_quiz_questions SET question_number = 10, display_order = 10 WHERE question_number = 1035;
-- Screen 9: Accessories to avoid (was Q42)
UPDATE style_quiz_questions SET question_number = 11, display_order = 11 WHERE question_number = 1042;
-- Screen 10: Adventurous with trends (was Q15)
UPDATE style_quiz_questions SET question_number = 12, display_order = 12 WHERE question_number = 1015;
-- Screen 11: Sustainable options (was Q16)
UPDATE style_quiz_questions SET question_number = 13, display_order = 13 WHERE question_number = 1016;
-- Screen 12: Share wardrobe photos (was Q37)
UPDATE style_quiz_questions SET question_number = 14, display_order = 14 WHERE question_number = 1037;
-- Screen 13: Q15 NEW — inserted in Step 3 below
-- Screen 14: Style personality (was Q26)
UPDATE style_quiz_questions SET question_number = 16, display_order = 16 WHERE question_number = 1026;
-- Screen 15 [Brands — not a question, skipped]
-- Screen 16: Clothing type (was Q4)
UPDATE style_quiz_questions SET question_number = 17, display_order = 17 WHERE question_number = 1004;
-- Screen 17: Open to new brands (was Q14)
UPDATE style_quiz_questions SET question_number = 18, display_order = 18 WHERE question_number = 1014;
-- Screen 18: Fit for tops (was Q40)
UPDATE style_quiz_questions SET question_number = 19, display_order = 19 WHERE question_number = 1040;
-- Screen 19: Q20 NEW (fit for bottoms) — inserted in Step 3 below
-- Screen 20: Fit concerns (was Q38)
UPDATE style_quiz_questions SET question_number = 21, display_order = 21 WHERE question_number = 1038;
-- Screen 21: Personalized analysis (was Q39)
UPDATE style_quiz_questions SET question_number = 22, display_order = 22 WHERE question_number = 1039;
-- Screen 22: Stylist expertise (was Q41)
UPDATE style_quiz_questions SET question_number = 23, display_order = 23 WHERE question_number = 1041;
-- Screen 23: Hands-on stylist (was Q20)
UPDATE style_quiz_questions SET question_number = 24, display_order = 24 WHERE question_number = 1020;
-- Screen 24: Style needs scenarios (was Q19)
UPDATE style_quiz_questions SET question_number = 25, display_order = 25 WHERE question_number = 1019;
-- Screen 25: Colors gravitate IMAGE palettes (was Q27)
UPDATE style_quiz_questions SET question_number = 26, display_order = 26 WHERE question_number = 1027;
-- Screen 26: Colors dislike (was Q36)
UPDATE style_quiz_questions SET question_number = 27, display_order = 27 WHERE question_number = 1036;
-- Screen 27: Fashion dilemma (was Q17)
UPDATE style_quiz_questions SET question_number = 28, display_order = 28 WHERE question_number = 1017;
-- Screen 28: Styling involvement (was Q18)
UPDATE style_quiz_questions SET question_number = 29, display_order = 29 WHERE question_number = 1018;
-- Screen 29: Fabric patterns (was Q29)
UPDATE style_quiz_questions SET question_number = 30, display_order = 30 WHERE question_number = 1029;
-- Screen 30: Body type silhouettes (was Q24)
UPDATE style_quiz_questions SET question_number = 31, display_order = 31 WHERE question_number = 1024;
-- Screen 31 (height_weight group): height (was Q10)
UPDATE style_quiz_questions SET question_number = 32, display_order = 32 WHERE question_number = 1010;
-- Screen 31 (height_weight group): weight (was Q11)
UPDATE style_quiz_questions SET question_number = 33, display_order = 33 WHERE question_number = 1011;
-- Screen 32: Skin tone (was Q25)
UPDATE style_quiz_questions SET question_number = 34, display_order = 34 WHERE question_number = 1025;
-- Screen 33: Modestly (was Q12)
UPDATE style_quiz_questions SET question_number = 35, display_order = 35 WHERE question_number = 1012;
-- Screen 34: Hijabi (was Q13)
UPDATE style_quiz_questions SET question_number = 36, display_order = 36 WHERE question_number = 1013;
-- Screen 35 (budget group): tops (was Q5)
UPDATE style_quiz_questions SET question_number = 37, display_order = 37 WHERE question_number = 1005;
-- Screen 35 (budget group): dresses (was Q6)
UPDATE style_quiz_questions SET question_number = 38, display_order = 38 WHERE question_number = 1006;
-- Screen 35 (budget group): bottoms (was Q7)
UPDATE style_quiz_questions SET question_number = 39, display_order = 39 WHERE question_number = 1007;
-- Screen 35 (budget group): bags (was Q8)
UPDATE style_quiz_questions SET question_number = 40, display_order = 40 WHERE question_number = 1008;
-- Screen 35 (budget group): shoes (was Q9)
UPDATE style_quiz_questions SET question_number = 41, display_order = 41 WHERE question_number = 1009;
-- Screen 36: Instagram/Pinterest (was Q21)
UPDATE style_quiz_questions SET question_number = 42, display_order = 42 WHERE question_number = 1021;
-- Screen 37: Upload photo (was Q22)
UPDATE style_quiz_questions SET question_number = 43, display_order = 43 WHERE question_number = 1022;
-- Screen 38: Anything else general (was Q23)
UPDATE style_quiz_questions SET question_number = 44, display_order = 44 WHERE question_number = 1023;

-- ─────────────────────────────────────────────────────────────────────────────
-- Step 3: Insert the 2 new questions
-- ─────────────────────────────────────────────────────────────────────────────

-- Q15: Mid-quiz fashion preferences note (screen 13)
INSERT INTO style_quiz_questions
    (question_number, section, question_text, question_type, placeholder_text, is_required, metadata, display_group, display_order)
VALUES
(15, 'SECTION_1',
 'Is there anything else you''d like your stylist to know about your fashion preferences?',
 'TEXT', 'Text box for additional notes', false, NULL, NULL, 15);

-- Q20: Preferred fit for bottoms (screen 19)
INSERT INTO style_quiz_questions
    (question_number, section, question_text, question_type, placeholder_text, is_required, metadata, display_group, display_order)
VALUES
(20, 'SECTION_2', 'What is your preferred fit for bottoms?',
 'SINGLE_SELECT_IMAGE', NULL, false, NULL, NULL, 20);

WITH q AS (SELECT id FROM style_quiz_questions WHERE question_number = 20)
INSERT INTO style_quiz_options (question_id, option_text, option_value, image_url, display_order) VALUES
((SELECT id FROM q), 'Fitted',   'FITTED',   '/images/quiz/fits/fit-01.png', 1),
((SELECT id FROM q), 'Straight', 'STRAIGHT', '/images/quiz/fits/fit-02.png', 2),
((SELECT id FROM q), 'Loose',    'LOOSE',    '/images/quiz/fits/fit-03.png', 3);

-- ─────────────────────────────────────────────────────────────────────────────
-- Step 4: Fix style persona options (now question_number = 16)
-- Figma order: 1 Timeless, 2 Modern Min, 3 Structured+Sharp, 4 Effortless Chic,
--              5 Bohemian, 6 Loose, 7 Street Smart, 8 Glamorous+Sexy, 9 Eclectic Trendsetter
-- Current DB:  3 = Effortless Chic, 4 = Glamorous+Sexy, 8 = Structured+Sharp, 9 = Classic+Refined
-- ─────────────────────────────────────────────────────────────────────────────

-- Use negative temps to avoid collisions during reorder
UPDATE style_quiz_options
SET display_order = -display_order
WHERE question_id = (SELECT id FROM style_quiz_questions WHERE question_number = 16)
  AND display_order IN (3, 4, 8);

UPDATE style_quiz_options SET display_order = 3
WHERE question_id = (SELECT id FROM style_quiz_questions WHERE question_number = 16)
  AND display_order = -8;

UPDATE style_quiz_options SET display_order = 4
WHERE question_id = (SELECT id FROM style_quiz_questions WHERE question_number = 16)
  AND display_order = -3;

UPDATE style_quiz_options SET display_order = 8
WHERE question_id = (SELECT id FROM style_quiz_questions WHERE question_number = 16)
  AND display_order = -4;

-- Fix 9th option: Classic and Refined → Eclectic Trendsetter
UPDATE style_quiz_options
SET option_text = 'Eclectic Trendsetter', option_value = 'ECLECTIC_TRENDSETTER'
WHERE question_id = (SELECT id FROM style_quiz_questions WHERE question_number = 16)
  AND display_order = 9;
