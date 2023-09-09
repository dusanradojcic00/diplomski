CREATE TABLE t_order
(
    id          BIGSERIAL PRIMARY KEY,
    customer_id BIGINT                      NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL
);


CREATE TABLE t_order_item
(
    order_id BIGINT,
    item_id  BIGINT,
    quantity INT,
    PRIMARY KEY (order_id, item_id),
    FOREIGN KEY (order_id) REFERENCES t_order (id)
);