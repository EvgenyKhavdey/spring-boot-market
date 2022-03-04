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
    manager               BIGINT REFERENCES users (id),
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

INSERT INTO categories (title, image_url)
VALUES ('Смартфоны и гаджеты', '/media/phones.jpeg'),
       ('Телевизоры, аудио, Hi-Fi', '/media/TV.jpeg'),
       ('Ноутбуки и компьютеры', '/media/Nout_Comp.jpeg'),
       ('Техника для кухни', '/media/KBT.png');

INSERT INTO products(title, price, category_id, image_url)
VALUES ('Смартфон Apple iPhone 11', 53999, 1, '/media/30052942bb.jpeg'),
       ('Смартфон Apple iPhone 12', 89999, 1, '/media/30052900bb.jpeg'),
       ('Смартфон Xiaomi Redmi 9T', 16999, 1, '/media/30055853bb.jpeg'),
       ('Смартфон Samsung Galaxy A32', 19999, 1, '/media/30056051bb.jpeg'),
       ('Смартфон Samsung Galaxy A12', 12999, 1, '/media/30054732bb.jpeg'),
       ('Смартфон Samsung Galaxy M12', 13999, 1, '/media/30057036bb.jpeg'),

       ('Телевизор Novex NWX-24H121MSY', 13999, 2, '/media/10025188bb.jpeg'),
       ('Телевизор Novex NWX-40F171MSY', 23999, 2, '/media/10025189bb.jpeg'),
       ('Телевизор Novex NWX-24H121WSY', 15299, 2, '/media/10025187bb.jpeg'),
       ('Телевизор Sony XR65A80J', 279999, 2, '/media/10026988bb.jpeg'),

       ('Ноутбук игровой ASUS TUF Gaming F15', 96999, 3, '/media/30058616bb.jpeg'),
       ('Ноутбук игровой ASUS TUF F15', 84999, 3, '/media/30058060bb.jpeg'),
       ('Ноутбук игровой ASUS ROG Strix G15', 99999, 3, '/media/30058617bb.jpeg'),
       ('Ноутбук игровой Acer Nitro 5', 94999, 3, '/media/30059619bb.jpeg'),
       ('Ноутбук игровой Acer Aspire 7', 75999, 3, '/media/30057652bb.jpeg'),
       ('Ноутбук игровой Lenovo Legion 5', 135999, 3, '/media/30061622bb.jpeg'),
       ('Ноутбук игровой MSI GF66 Katana ', 154999, 3, '/media/30058702bb.jpeg'),
       ('Ноутбук игровой ASUS ROG', 149999, 3, '/media/30057548bb.jpeg'),

       ('Встраиваемая индукционная панель Bosch Serie', 84999, 4, '/media/20048704bb.jpeg'),
       ('Микроволновая печь соло Gorenje', 3999, 4, '/media/20062339bb.jpeg'),
       ('Электрический духовой шкаф Gorenje', 14999, 4, '/media/20062243bb.jpeg'),
       ('Мультиварка Redmond RMC', 4199, 4, '/media/20052086bb.jpeg'),
       ('Погружной блендер Braun', 5999, 4, '/media/20064347bb.jpeg'),
       ('Морозильная камера Haier', 18999, 4, '/media/20038672b.jpeg');

INSERT INTO orders(customer_id, manager, price, order_status, shipping_method, address, contact_email, creation_time, deliver_time, details)
VALUES (4, null, 44990, 0, 0, 'Москва, улицы Твекрсая 5', 'user1@user.ru',  '2022-02-18 17:33:34', '2022-02-21 09:22:00', 'До двери'),
       (4, 2, 32290, 3, 1, 'Москва, улицы Твекрсая 5', 'user1@user.ru',  '2021-12-01 12:01:52', '2021-12-19 12:00:00', 'До двери'),
       (4, 3, 32290, 2, 1, 'Москва, улицы Твекрсая 5', 'user1@user.ru',  '2022-01-30 14:01:12', '2022-02-21 09:22:00', 'До двери'),
       (5, null, 44990, 0, 0, 'Москва, улицы Охотный ряд 1', 'user2@user.ru',  '2022-02-18 17:33:34', '2022-02-21 09:22:00', 'До двери'),
       (5, 2, 32290, 3, 1, 'Москва, улицы Охотный ряд 1', 'user2@user.ru',  '2021-12-01 12:01:52', '2021-12-19 12:00:00', 'До двери'),
       (5, 3, 32290, 2, 1, 'Москва, улицы Охотный ряд 1', 'user2@user.ru',  '2022-01-30 14:01:12', '2022-02-21 09:22:00', 'До двери'),
       (6, null, 44990, 0, 0, 'Москва, Красная площадь 1', 'user3@user.ru',  '2022-02-18 17:33:34', '2022-02-21 09:22:00', 'До двери'),
       (6, 2, 32290, 3, 1, 'Москва, Красная площадь 1', 'user3@user.ru',  '2021-12-01 12:01:52', '2021-12-19 12:00:00', 'До двери'),
       (6, 3, 32290, 2, 1, 'Москва, Красная площадь 1', 'user3@user.ru',  '2022-01-30 14:01:12', '2022-02-21 09:22:00', 'До двери');
