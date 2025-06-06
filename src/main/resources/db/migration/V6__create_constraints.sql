-- ============================================
-- MarketSim MVP - Foreign Key Constraints
-- Version: 1.0
-- Description: Referential integrity
-- ============================================

ALTER TABLE accounts
    ADD CONSTRAINT fk_accounts_user_id
        FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE accounts
    ADD CONSTRAINT uk_accounts_user_id
        UNIQUE (user_id);

ALTER TABLE portfolios
    ADD CONSTRAINT fk_portfolios_user_id
        FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE portfolios
    ADD CONSTRAINT uk_portfolios_user_name
        UNIQUE (user_id, name);

ALTER TABLE positions
    ADD CONSTRAINT fk_positions_portfolio_id
        FOREIGN KEY (portfolio_id) REFERENCES portfolios (id);

ALTER TABLE positions
    ADD CONSTRAINT fk_positions_asset_id
        FOREIGN KEY (asset_id) REFERENCES assets (id);

ALTER TABLE positions
    ADD CONSTRAINT uk_positions_portfolio_asset
        UNIQUE (portfolio_id, asset_id);

ALTER TABLE transactions
    ADD CONSTRAINT fk_transactions_user_id
        FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE transactions
    ADD CONSTRAINT fk_transactions_portfolio_id
        FOREIGN KEY (portfolio_id) REFERENCES portfolios (id);

ALTER TABLE transactions
    ADD CONSTRAINT fk_transactions_asset_id
        FOREIGN KEY (asset_id) REFERENCES assets (id);

ALTER TABLE current_prices
    ADD CONSTRAINT fk_current_prices_asset_id
        FOREIGN KEY (asset_id) REFERENCES assets (id);

ALTER TABLE price_history
    ADD CONSTRAINT fk_price_history_asset_id
        FOREIGN KEY (asset_id) REFERENCES assets (id);