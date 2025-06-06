-- ============================================
-- MarketSim MVP - Transaction Tables
-- Version: 1.0
-- Description: Positions and Transactions (immutable audit)
-- ============================================

CREATE TABLE positions
(
    id                   BIGINT PRIMARY KEY,
    public_id            UUID            NOT NULL UNIQUE,
    portfolio_id         BIGINT          NOT NULL,
    asset_id             BIGINT          NOT NULL,
    quantity             DECIMAL(19, 10) NOT NULL,
    average_bought_price DECIMAL(19, 4)  NOT NULL,
    created_at           TIMESTAMP       NOT NULL,
    updated_at           TIMESTAMP       NOT NULL
);

CREATE TABLE transactions
(
    id              BIGINT PRIMARY KEY,
    public_id       UUID            NOT NULL UNIQUE,
    user_id         BIGINT          NOT NULL,
    portfolio_id    BIGINT          NOT NULL,
    asset_id        BIGINT          NOT NULL,
    quantity        DECIMAL(19, 10) NOT NULL,
    execution_price DECIMAL(19, 4)  NOT NULL,
    total_amount    DECIMAL(19, 4)  NOT NULL,
    order_type      VARCHAR(10)     NOT NULL,
    executed_at     TIMESTAMP       NOT NULL,
    created_at      TIMESTAMP       NOT NULL,
    updated_at      TIMESTAMP       NOT NULL
);