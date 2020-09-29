USE online_order;

CREATE TABLE IF NOT EXISTS products (
  id int NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(25) NOT NULL,
  lastname VARCHAR(25) NOT NULL,
  email VARCHAR(35) NOT NULL,
  dateOfBirth DATE,
  PRIMARY KEY (id),
  UNIQUE(id),
  unique(email)
);