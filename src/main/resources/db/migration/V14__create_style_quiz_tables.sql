CREATE TABLE style_quiz_questions (
    id               BIGSERIAL    PRIMARY KEY,
    question_number  INTEGER      NOT NULL,
    section          VARCHAR(20)  NOT NULL,
    question_text    TEXT         NOT NULL,
    question_type    VARCHAR(30)  NOT NULL,
    placeholder_text VARCHAR(255),
    is_required      BOOLEAN      NOT NULL DEFAULT TRUE,
    metadata         JSONB,
    display_order    INTEGER      NOT NULL
);

CREATE TABLE style_quiz_options (
    id             BIGSERIAL    PRIMARY KEY,
    question_id    BIGINT       NOT NULL REFERENCES style_quiz_questions(id) ON DELETE CASCADE,
    option_text    VARCHAR(255) NOT NULL,
    option_value   VARCHAR(100) NOT NULL,
    image_url      VARCHAR(500),
    display_order  INTEGER      NOT NULL
);
