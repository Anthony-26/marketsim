package fr.marketsim.infrastructure.out.database.entity.price;

import fr.marketsim.infrastructure.out.database.entity.asset.AssetEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

import static fr.marketsim.application.utilities.ApplicationConstants.PRICE_PRECISION;

@Entity
@Table(name = "price_history")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssetPriceHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_history_seq")
    @SequenceGenerator(
            name = "price_history_seq",
            sequenceName = "price_history_id_seq",
            allocationSize = 100
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false, updatable = false)
    private AssetEntity asset;

    @Column(name = "price", nullable = false, updatable = false, precision = PRICE_PRECISION, scale = PRICE_PRECISION)
    private BigDecimal price;

    @Column(name = "timestamp", nullable = false, updatable = false)
    private Instant timestamp;
}
