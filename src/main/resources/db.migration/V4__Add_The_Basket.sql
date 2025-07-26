CREATE TABLE IF NOT EXISTS basket (
    id SERIAL PRIMARY KEY,
    userId INT NOT NULL,
    status INT NOT NULL DEFAULT 0,
    createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS basketEntry (
    basketId INT NOT NULL,
    productId INT NOT NULL,
    productName TEXT NOT NULL,
    productPrice NUMERIC(10, 2) NOT NULL,
    PRIMARY KEY (basketId, productId)
    CONSTRAINT fk_basket
        FOREIGN KEY (basketId)
        REFERENCES basket(id)
        ON DELETE CASCADE
);