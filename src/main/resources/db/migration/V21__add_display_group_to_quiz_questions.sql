ALTER TABLE style_quiz_questions ADD COLUMN display_group VARCHAR(50);

-- Height and weight share one screen
UPDATE style_quiz_questions SET display_group = 'height_weight'
WHERE question_number IN (10, 11);

-- All budget sliders share one screen
UPDATE style_quiz_questions SET display_group = 'budget'
WHERE question_number IN (5, 6, 7, 8, 9);
