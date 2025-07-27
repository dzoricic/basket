CREATE TABLE IF NOT EXISTS favourites (
    userId INT NOT NULL,
    productId INT NOT NULL,
    PRIMARY KEY (userId, productId),
    CONSTRAINT fk_buyer
        FOREIGN KEY (userId)
        REFERENCES buyer(id)
        ON DELETE CASCADE,
)