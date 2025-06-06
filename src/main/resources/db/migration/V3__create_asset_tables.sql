-- ============================================
-- MarketSim MVP - Asset Tables
-- Version: 1.0
-- Description: Assets hierarchy (JOINED inheritance)
-- ============================================

CREATE TABLE assets
(
    id         BIGINT PRIMARY KEY,
    public_id  UUID         NOT NULL UNIQUE,
    asset_type VARCHAR(50)  NOT NULL,
    name       VARCHAR(255) NOT NULL,
    ticker     VARCHAR(50)  NOT NULL UNIQUE,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL
);

CREATE TABLE stocks
(
    id       BIGINT PRIMARY KEY,
    sector   VARCHAR(50) NOT NULL,
    dividend DECIMAL(19, 4),
    FOREIGN KEY (id) REFERENCES assets (id)
);