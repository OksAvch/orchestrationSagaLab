CREATE TABLE orders (
    id UUID DEFAULT GEN_RANDOM_UUID() PRIMARY KEY,
    user_id INT,
    product_id INT,
    price NUMERIC(10, 2),
    status VARCHAR(50)
);

INSERT INTO orders (id, user_id, product_id, price, status) VALUES
(gen_random_uuid(), 1, 101, 19.99, 'ORDER_CREATED'),
(gen_random_uuid(), 2, 102, 45.50, 'ORDER_CANCELLED'),
(gen_random_uuid(), 3, 103, 12.75, 'ORDER_CANCELLED'),
(gen_random_uuid(), 1, 104, 99.99, 'ORDER_COMPLETED'),
(gen_random_uuid(), 4, 105, 55.00, 'ORDER_CREATED'),
(gen_random_uuid(), 2, 106, 22.30, 'ORDER_CANCELLED'),
(gen_random_uuid(), 5, 107, 78.80, 'ORDER_CREATED'),
(gen_random_uuid(), 3, 108, 35.99, 'ORDER_COMPLETED'),
(gen_random_uuid(), 6, 109, 120.49, 'ORDER_CREATED'),
(gen_random_uuid(), 4, 110, 89.90, 'ORDER_CANCELLED');