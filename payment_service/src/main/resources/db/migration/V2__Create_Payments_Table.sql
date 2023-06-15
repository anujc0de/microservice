CREATE TABLE payments (
  id UUID NOT NULL PRIMARY KEY,
  customer_id INT NOT NULL,
  amount float ,
  payment_status text NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp
);
