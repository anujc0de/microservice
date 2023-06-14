CREATE TYPE payment_status_enum AS ENUM ('STARTED', 'COMPLETED', 'FAILED');
CREATE TABLE payments (
  id UUID NOT NULL PRIMARY KEY,
  customer_id INT NOT NULL,
  amount float ,
  payment_status payment_status_enum NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp
);
