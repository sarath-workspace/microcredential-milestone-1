USE online_order;

CREATE TABLE IF NOT EXISTS checkout (
  userid int NOT NULL,
  code VARCHAR(8) NOT NULL,
  productId int NOT NULL,
  productName VARCHAR(8) NOT NULL,
  quantity int NOT NULL,
  price double NOT NULL,
  offer double NOT NULL,
  PRIMARY KEY (userid, code, productId),
  UNIQUE KEY composit_checkout (userid, code, productId)
);