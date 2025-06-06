-- ============================================
-- MarketSim MVP - Performance Indexes
-- Version: 1.0
-- Description: Query optimization indexes
-- ============================================

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_public_id ON users(public_id);

CREATE INDEX idx_portfolios_user_id ON portfolios(user_id);
CREATE INDEX idx_portfolios_name ON portfolios(name);

CREATE INDEX idx_positions_portfolio_id ON positions(portfolio_id);
CREATE INDEX idx_positions_asset_id ON positions(asset_id);

CREATE INDEX idx_assets_ticker ON assets(ticker);
CREATE INDEX idx_assets_asset_type ON assets(asset_type);

CREATE INDEX idx_transactions_user_id ON transactions(user_id);
CREATE INDEX idx_transactions_portfolio_id ON transactions(portfolio_id);
CREATE INDEX idx_transactions_asset_id ON transactions(asset_id);
CREATE INDEX idx_transactions_executed_at ON transactions(executed_at DESC);
CREATE INDEX idx_transactions_user_executed ON transactions(user_id, executed_at DESC);

CREATE INDEX idx_price_history_asset_id ON price_history(asset_id);
CREATE INDEX idx_price_history_timestamp ON price_history(timestamp DESC);
CREATE INDEX idx_price_history_asset_timestamp ON price_history(asset_id, timestamp DESC);