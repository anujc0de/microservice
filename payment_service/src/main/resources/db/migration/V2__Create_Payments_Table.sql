CREATE TABLE payments (
  id UUID NOT NULL PRIMARY KEY,
  customer_id INT NOT NULL,
  amount float not null,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp
);
