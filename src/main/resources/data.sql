CREATE TABLE IF NOT EXISTS product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    price DOUBLE,
    release_date DATE
);

-- 데이터가 이미 존재하는지 확인한 후 데이터 삽입
INSERT INTO product (name, description, price, release_date)
SELECT 'Example Product 1', 'This is an example product description 1.', 19.99, '2024-01-01'
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Example Product 1');

INSERT INTO product (name, description, price, release_date)
SELECT 'Example Product 2', 'This is an example product description 2.', 29.99, '2024-02-01'
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Example Product 2');