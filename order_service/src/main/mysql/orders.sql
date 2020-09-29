USE online_order;

CREATE TABLE IF NOT EXISTS orders (
  userid int NOT NULL,
  productId int NOT NULL,
  quantity int NOT NULL,
  PRIMARY KEY (userid, productId),
  UNIQUE KEY composit_order (userid, productId)
);
