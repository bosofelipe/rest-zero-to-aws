CREATE TABLE books (
  id serial PRIMARY KEY,
  author text,
  launch_date timestamp NOT NULL,
  price numeric(65,2) NOT NULL,
  title text
);