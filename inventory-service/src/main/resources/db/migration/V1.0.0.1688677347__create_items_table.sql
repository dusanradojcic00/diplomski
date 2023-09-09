CREATE TABLE t_items
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255)     NOT NULL,
    quantity INT              NOT NULL,
    price    DOUBLE PRECISION NOT NULL
);


