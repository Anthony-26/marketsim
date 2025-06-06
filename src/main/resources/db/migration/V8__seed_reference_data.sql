-- ============================================
-- MarketSim MVP - Reference Data
-- Version: 1.0
-- Description: Initial assets for MVP testing
-- ============================================

INSERT INTO assets (id, public_id, asset_type, name, ticker, created_at, updated_at)
VALUES
    (nextval('asset_id_seq'), gen_random_uuid(), 'STOCK', 'Apple Inc.', 'AAPL', NOW(), NOW()),
    (nextval('asset_id_seq'), gen_random_uuid(), 'STOCK', 'Microsoft Corporation', 'MSFT', NOW(), NOW()),
    (nextval('asset_id_seq'), gen_random_uuid(), 'STOCK', 'NVIDIA Corporation', 'NVDA', NOW(), NOW()),
    (nextval('asset_id_seq'), gen_random_uuid(), 'STOCK', 'Tesla Inc.', 'TSLA', NOW(), NOW());

-- Stock details
INSERT INTO stocks (id, sector, dividend)
SELECT id, 'TECHNOLOGY',
       CASE
           WHEN ticker = 'AAPL' THEN 0.96
           WHEN ticker = 'MSFT' THEN 3.00
           WHEN ticker = 'NVDA' THEN 0.16
           WHEN ticker = 'TSLA' THEN 0.00
           END
FROM assets
WHERE asset_type = 'STOCK';
