CREATE TABLE roles (
                            id   BIGSERIAL PRIMARY KEY,
                            name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
                            id       BIGSERIAL PRIMARY KEY,
                            username VARCHAR(50) NOT NULL UNIQUE,
                            password VARCHAR(100) NOT NULL
);

CREATE TABLE users_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id) ON delete CASCADE,
                            FOREIGN KEY (role_id) REFERENCES roles(id) ON delete CASCADE
);

INSERT INTO roles (name)
VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN');

INSERT INTO users (username, password)
VALUES
    ('user', '$2a$12$N14/uJb.6z98xXRouBn2UeZtobh4lN7EvWj39j8Oa.NtI.u8M3Jk.'),   -- password: 12345
    ('admin', '$2a$12$L8XNbxgE1QDX.E3CrpHLIuU8gwc1QmsfnbHjQOuPlaV6cwBYBja4G');  -- password: 123456

INSERT INTO users_roles (user_id, role_id)
VALUES
    ((SELECT id FROM users WHERE username = 'user'), (SELECT id FROM roles WHERE name = 'ROLE_USER')),
    ((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'));
