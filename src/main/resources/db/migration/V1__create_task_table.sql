CREATE TABLE task
(
    id            BIGSERIAL PRIMARY KEY,
    description   VARCHAR(255),
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    due_date      DATE
);
