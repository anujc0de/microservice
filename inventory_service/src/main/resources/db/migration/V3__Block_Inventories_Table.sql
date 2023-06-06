CREATE TABLE block_inventories (
  id uuid       not null PRIMARY KEY,
  product_id uuid not null,
  expiry_time  TIMESTAMP WITH TIME ZONE,
  quantity int,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp
);
