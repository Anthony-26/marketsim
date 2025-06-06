-- ============================================
-- MarketSim MVP - Price Tables
-- Version: 1.0
-- Description: Current prices and historical data
-- ============================================

CREATE TABLE current_prices
(
    id          BIGINT PRIMARY KEY,
    asset_id    BIGINT         NOT NULL UNIQUE,
    price       DECIMAL(19, 4) NOT NULL,
    last_update TIMESTAMP      NOT NULL,
    created_at  TIMESTAMP      NOT NULL,
    updated_at  TIMESTAMP      NOT NULL
);

CREATE TABLE price_history
(
    id         BIGINT PRIMARY KEY,
    asset_id   BIGINT         NOT NULL,
    price      DECIMAL(19, 4) NOT NULL,
    timestamp  TIMESTAMP      NOT NULL,
    created_at TIMESTAMP      NOT NULL,
    updated_at TIMESTAMP      NOT NULL
);