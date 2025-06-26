CREATE TABLE category
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

ALTER TABLE task
    ADD COLUMN category_id BIGINT REFERENCES category (id);
