USE online_order;

CREATE TABLE IF NOT EXISTS products (
  id int NOT NULL AUTO_INCREMENT,
  name VARCHAR(40) NOT NULL,
  stock INT,
  price DOUBLE,
  offer DOUBLE,
  PRIMARY KEY (id),
  UNIQUE(id)
);