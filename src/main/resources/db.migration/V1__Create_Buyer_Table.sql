CREATE TABLE IF NOT EXISTS buyer (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    title VARCHAR(100)
)