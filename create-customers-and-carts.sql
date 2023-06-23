set num.customers to :num_customers; -- Name should contains the dot, don't ask me why

DO
$$
    DECLARE
        i             INT;
        customer_name TEXT;
        cart_id uuid;
        num_customers INT; -- Access the passed variable
    BEGIN
--         EXECUTE format('SET num_customers TO %s', $1);
        SELECT current_setting('num.customers')::INT INTO num_customers;
        FOR i IN 1..num_customers
            LOOP
                -- Use the num_customers variable in the loop
                customer_name := 'customer' || i;

                INSERT INTO customers (id, name, created_at, updated_at)
                VALUES (i, customer_name, now(), now());

                cart_id := gen_random_uuid();

                INSERT INTO carts (id, customer_id, created_at, updated_at)
                VALUES (cart_id, i, now(), now());

                INSERT INTO cart_items (id, cart_id, product_id, quantity, price,  created_at, updated_at)
                VALUES (gen_random_uuid(), cart_id, '62c8f971-db3d-45e6-aa6c-25472bf5c94b', 100, 200.00, now(), now());
            END LOOP;
    END
$$;
