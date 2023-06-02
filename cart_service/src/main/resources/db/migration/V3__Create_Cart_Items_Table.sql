CREATE TABLE cart_items (
  id UUID NOT NULL PRIMARY KEY,
  cart_id UUID NOT NULL,
  product_id UUID,
  quantity INT,
  price DECIMAL(10,2),
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp
);
