-- Insert roles if they do not exists
INSERT INTO roles (id, name) VALUES (1, 'ADMIN') ON CONFLICT (id) DO NOTHING;
INSERT INTO roles (id, name) VALUES (2, 'USER_READ') ON CONFLICT (id) DO NOTHING;
INSERT INTO roles (id, name) VALUES (3, 'USER_WRITE') ON CONFLICT (id) DO NOTHING;
INSERT INTO roles (id, name) VALUES (4, 'USER_DELETE') ON CONFLICT (id) DO NOTHING;

-- Insert admin user with encrypted password using bcrypt (admin123)
INSERT INTO users (id, name, last_name, email, password, creation_date) VALUES (1, 'Admin', 'User', 'admin@example.com', '$2a$10$gP1mYwvKjXjYoPSu4P/6VOsMNCZGIRY.DTlj3ac3ebqXqFqJqQjZy', NOW()) ON CONFLICT DO NOTHING;

-- Assign ADMIN role to user created
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1) ON CONFLICT DO NOTHING;