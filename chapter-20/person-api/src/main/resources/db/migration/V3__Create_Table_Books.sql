CREATE TABLE books (
  id bigserial NOT NULL PRIMARY KEY,
  author text,
  launch_date timestamp NOT NULL,
  price numeric(65,2) NOT NULL,
  title text
);