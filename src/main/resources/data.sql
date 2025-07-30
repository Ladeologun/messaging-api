
CREATE TABLE IF NOT EXISTS platform_users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--INSERT INTO platform_users (first_name, last_name,role, email, password)
--SELECT 'John',
--       'flowers',
--       'john.doe@example.com',
--       '123 Main St, Springfield',
--       '1985-06-15',
--       '2024-01-10'
--WHERE NOT EXISTS (SELECT email
--                  FROM platform_users
--                  WHERE email = 'john.doe@example.com');


INSERT INTO platform_users (first_name, last_name, role, email, password)
VALUES
  ('John', 'Flowers', 'USER', 'john.doe@example.com', '$2a$10$2WypCdYeS6kSIA8M4pbzge91xilI7KnQmNKoYkW6IZYAWNGI6hV/q'),
  ('Jane', 'Smith', 'ADMIN', 'jane.smith@example.com', '$2a$10$2WypCdYeS6kSIA8M4pbzge91xilI7KnQmNKoYkW6IZYAWNGI6hV/q'),
  ('Mike', 'Brown', 'USER', 'mike.brown@example.com', '$2a$10$2WypCdYeS6kSIA8M4pbzge91xilI7KnQmNKoYkW6IZYAWNGI6hV/q')
ON CONFLICT (email) DO NOTHING;
