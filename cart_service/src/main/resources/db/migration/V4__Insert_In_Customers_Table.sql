DO $$
DECLARE
    i INT;
    customer_name TEXT;
BEGIN
    FOR i IN 1..200 LOOP
        customer_name := 'customer' || i;

        INSERT INTO customers (name, created_at, updated_at)
        VALUES (customer_name, now(), now());

        INSERT INTO carts(id, customer_id, created_at, updated_at)
        VALUES (gen_random_uuid(), i, now(), now());
    END LOOP;
END $$;
