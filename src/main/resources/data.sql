--bcrypt.online tanem
INSERT INTO blog_user (user_name, password_hash) VALUES ('Tanem', '$2y$12$b3dRMr9HWefJuYyuUYeSTODO0Exl31lcT8pn6jo5dTMAzP1LeQfkm') ON CONFLICT DO NOTHING;
--bcrypt.online greta
INSERT INTO blog_user (user_name, password_hash) VALUES ('Greta', '$2y$12$r19OWflLgKo8FgnuW6brHOc9s5VnKYNVFmj2eKtZ.0UFP5j2vhYSC') ON CONFLICT DO NOTHING;

