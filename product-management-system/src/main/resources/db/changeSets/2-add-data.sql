-- Добавляем роли
INSERT INTO roles (name)
VALUES
    ('USER'),
    ('ADMIN');

-- Добавляем пользователей
INSERT INTO users (password, email, role)
VALUES
    ('$2a$12$N14/uJb.6z98xXRouBn2UeZtobh4lN7EvWj39j8Oa.NtI.u8M3Jk.', 'user@gmail.com', 'USER'),    -- password: 12345
    ('$2a$12$L8XNbxgE1QDX.E3CrpHLIuU8gwc1QmsfnbHjQOuPlaV6cwBYBja4G', 'admin@gmail.com', 'ADMIN');  -- password: 123456

-- Связываем пользователей с ролями
INSERT INTO users_roles (user_id, role_id)
VALUES
    (1, 1),    -- user: USER
    (2, 2);    -- admin: ADMIN

-- Добавляем категории
INSERT INTO categories (name, description)
VALUES
    ('Категория не выбрана', 'Необходимо выбрать категорию'),
    ('Потолочные светильники', 'Выбрана категория потолочных светильников'),
    ('Напольные светильники', 'Выбрана категория напольных светильников');

-- Добавляем продукты
INSERT INTO products (name, description, price, category_id, status)
VALUES
    ('Люстра', 'Светодиодная', 5785.50, 2, 'активен'),
    ('Торшер', 'Люминесцентный', 7120.99, 3, 'не активен');