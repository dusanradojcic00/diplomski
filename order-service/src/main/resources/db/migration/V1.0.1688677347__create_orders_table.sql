CREATE TABLE t_order
(
    id          BIGSERIAL PRIMARY KEY,
    customer_id BIGINT                      NOT NULL,
    status      VARCHAR(20)                 NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL
);


CREATE TABLE t_order_item
(
    order_id       BIGINT,
    item_id        BIGINT,
    quantity       INT,
    price_per_unit DOUBLE PRECISION NOT NULL,
    total_price    DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (order_id, item_id),
    FOREIGN KEY (order_id) REFERENCES t_order (id)
);