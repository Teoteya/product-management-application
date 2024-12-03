-- Таблица пользователей
create TABLE users (
    id         BIGSERIAL PRIMARY KEY,
    password   VARCHAR(80) NOT NULL,
    email      VARCHAR(50) UNIQUE,
    role       VARCHAR(30) NOT NULL
);

-- Таблица ролей
create TABLE roles (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(50) NOT NULL UNIQUE
);

-- Связующая таблица для пользователей и ролей
create TABLE users_roles (
    user_id     BIGINT NOT NULL,
    role_id     BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON delete CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON delete CASCADE
);

-- Таблица категорий
create TABLE categories (
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    description   VARCHAR(1000)
);

-- Таблица продуктов
create TABLE products (
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    description   VARCHAR(2000),
    price         DOUBLE PRECISION,
    image         OID,
    category_id   BIGINT NOT NULL,
    created_at    TIMESTAMP,
    status        VARCHAR(100) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);