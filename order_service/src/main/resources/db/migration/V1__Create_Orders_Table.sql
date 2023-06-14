CREATE TYPE order_status_enum AS ENUM ('PENDING', 'COMPLETED', 'FAILED');
CREATE TABLE orders (
  id UUID NOT NULL PRIMARY KEY,
  customer_id INT NOT NULL,
  payment_id UUID NOT NULL,
  order_status order_status_enum NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp
);