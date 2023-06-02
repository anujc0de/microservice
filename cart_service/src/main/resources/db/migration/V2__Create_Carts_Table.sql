CREATE TABLE carts (
  id UUID NOT NULL PRIMARY KEY,
  customer_id INT NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  FOREIGN KEY (customer_id) REFERENCES customers(id)
);
