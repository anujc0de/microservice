CREATE TABLE order_items (
  id UUID NOT NULL PRIMARY KEY,
  order_id UUID NOT NULL references orders(id),
  product_id UUID,
  quantity INT,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp
);
