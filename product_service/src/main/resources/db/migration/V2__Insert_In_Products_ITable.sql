insert into products(id, name, description, price, created_at, updated_at)
values
(gen_random_uuid(), 'iphone', 'this is iphone', 100.00, now(), now()),
(gen_random_uuid(), 'shoes', 'this is shoes', 200.00, now(), now()),
(gen_random_uuid(), 'laptop', 'this is laptop', 500.00, now(), now());


