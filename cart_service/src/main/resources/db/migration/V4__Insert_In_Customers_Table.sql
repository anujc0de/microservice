DO $$
DECLARE
    i INT;
    customer_name TEXT;
BEGIN
    FOR i IN 1..200 LOOP
        customer_name := 'customer' || i;

        INSERT INTO customers (name, created_at, updated_at)
        VALUES (customer_name, now(), now());
    END LOOP;
END $$;
