CREATE TABLE customers (
  id SERIAL      PRIMARY KEY,
  name text not null,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp
);
