CREATE TABLE products (
  id uuid       not null PRIMARY KEY,
  name text,
  description text,
  price DECIMAL(10,2),
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp
);
