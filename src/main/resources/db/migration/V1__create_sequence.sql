-- ============================================
-- MarketSim MVP - Sequences Creation
-- Version: 1.0
-- Description: Create all sequences for ID generation
-- ============================================

CREATE SEQUENCE user_id_seq START WITH 1 INCREMENT BY 10;
CREATE SEQUENCE account_id_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE portfolio_id_seq START WITH 1 INCREMENT BY 10;
CREATE SEQUENCE position_id_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE asset_id_seq START WITH 1 INCREMENT BY 10;

CREATE SEQUENCE transaction_id_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE current_price_id_seq START WITH 1 INCREMENT BY 10;
CREATE SEQUENCE price_history_id_seq START WITH 1 INCREMENT BY 100;