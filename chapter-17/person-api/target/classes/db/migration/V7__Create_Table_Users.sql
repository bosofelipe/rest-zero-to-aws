CREATE TABLE IF NOT EXISTS users (
  id serial not null,
  user_name varchar(255) DEFAULT NULL unique,
  full_name varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  account_non_expired bit(1) DEFAULT NULL,
  account_non_locked bit(1) DEFAULT NULL,
  credentials_non_expired bit(1) DEFAULT NULL,
  enabled bit(1) DEFAULT NULL,
  PRIMARY KEY (id)
);