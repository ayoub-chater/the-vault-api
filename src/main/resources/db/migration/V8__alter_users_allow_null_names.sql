-- first_name and last_name are collected during profile completion,
-- not at registration time. Allow null until the user completes their profile.
ALTER TABLE users
    ALTER COLUMN first_name DROP NOT NULL,
    ALTER COLUMN last_name  DROP NOT NULL;
