-- ============================================
-- MarketSim MVP - Core Tables
-- Version: 1.0
-- Description: Users, Accounts, Portfolios
-- ============================================

CREATE TABLE users
(
    id            BIGINT PRIMARY KEY,
    public_id     UUID         NOT NULL UNIQUE,
    email         VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role          VARCHAR(20)  NOT NULL CHECK (role IN ('USER', 'ADMIN')),
    created_at    TIMESTAMP    NOT NULL,
    updated_at    TIMESTAMP    NOT NULL
);

CREATE TABLE accounts
(
    id         BIGINT PRIMARY KEY,
    public_id  UUID           NOT NULL UNIQUE,
    user_id    BIGINT         NOT NULL,
    balance    DECIMAL(19, 4) NOT NULL DEFAULT 0.0000,
    created_at TIMESTAMP      NOT NULL,
    updated_at TIMESTAMP      NOT NULL
);

CREATE TABLE portfolios
(
    id         BIGINT PRIMARY KEY,
    public_id  UUID         NOT NULL UNIQUE,
    user_id    BIGINT         NOT NULL,
    name       VARCHAR(100) NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL
);