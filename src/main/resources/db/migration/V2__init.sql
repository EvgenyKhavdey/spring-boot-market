CREATE TABLE IF NOT EXISTS categories
(
    id                    BIGSERIAL PRIMARY KEY,
    title                 VARCHAR(255) NOT NULL UNIQUE,
    description           TEXT,
    image_url             VARCHAR(255),
    parent_id             BIGINT REFERENCES categories (id)
);

CREATE TABLE IF NOT EXISTS attributes (
    id                    BIGSERIAL PRIMARY KEY,
    title                 VARCHAR(255) NOT NULL

);

CREATE TABLE IF NOT EXISTS attributes_values (
    id                    BIGSERIAL PRIMARY KEY,
    title                 VARCHAR(255) NOT NULL,
    attributes_id         BIGINT REFERENCES attributes_values (id)

);

CREATE TABLE IF NOT EXISTS  products
(
    id                    BIGSERIAL PRIMARY KEY,
    title                 VARCHAR(255) NOT NULL,
    description           TEXT,
    price                 FLOAT NOT NULL,
    image_url             VARCHAR(255),
    category_id           BIGINT REFERENCES categories (id)
);

CREATE TABLE IF NOT EXISTS product_attributes (
    product_id            BIGINT REFERENCES products (id),
    attributes_value_id   BIGINT REFERENCES attributes_values (id)
);

CREATE TABLE IF NOT EXISTS  orders(
    id                    BIGSERIAL PRIMARY KEY,
    customer_id           BIGINT REFERENCES users (id),
    price                 FLOAT NOT NULL,
    order_status          SMALLINT,
    shipping_method       SMALLINT,
    address               TEXT,
    contact_email         VARCHAR(255),
    creation_time         TIMESTAMP,
    deliver_time          TIMESTAMP,
    details               TEXT
);

CREATE TABLE IF NOT EXISTS  order_items (
    id                    BIGSERIAL PRIMARY KEY,
    order_id              BIGINT REFERENCES orders (id),
    product_id            BIGINT REFERENCES products (id),
    price                 FLOAT NOT NULL,
    quantity              INTEGER
);

INSERT INTO categories (title)
VALUES ('Электроника'),
       ('Бытовая техника');

INSERT INTO products(title, price, category_id, image_url)
VALUES ('Ноутбук Lenovo', 44990, 1, '/media/lenovo.png'),
       ('Телефон iPhone', 66490, 1, '/media/iphone.png'),
       ('Стиральная машинка LG', 32290, 2, '/media/lg.png'),
       ('Телевизор Samsung', 32290, 1, '/media/samsung.png');

-- INSERT INTO orders(customer_id, price, order_status, shipping_method, address, contact_email, creation_time, deliver_time, details)
-- VALUES (4, 44990, 1, 1, 'Москва, улицы Твекрсая 5', 'user@user.ru',  '2022-02-18 17:33:34', '2022-02-21 09:22:00', 'До двети'),
--        (4, 32290, 4, 2, 'Москва, улицы Твекрсая 5', 'user@user.ru',  '2021-12-01 12:01:52', '2021-12-19 12:00:00', 'До двети'),
--        (4, 32290, 3, 2, 'Москва, улицы Твекрсая 5', 'user@user.ru',  '2022-01-30 14:01:12', '2022-02-21 09:22:00', 'До двети');
