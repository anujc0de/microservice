CREATE TABLE inventories (
  id uuid       not null PRIMARY KEY,
  product_id uuid not null,
  quantity int,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp
);
