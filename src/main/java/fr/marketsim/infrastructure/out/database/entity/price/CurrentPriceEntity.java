package fr.marketsim.infrastructure.out.database.entity.price;

import fr.marketsim.infrastructure.out.database.entity.asset.AssetEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

import static fr.marketsim.application.utilities.ApplicationConstants.PRICE_PRECISION;

@Entity
@Table(name = "current_prices")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CurrentPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "current_price_seq")
    @SequenceGenerator(
            name = "current_price_seq",
            sequenceName = "current_price_id_seq",
            allocationSize = 10
    )
    private Long id;

    @OneToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private AssetEntity asset;

    @Column(name = "price", nullable = false, precision = PRICE_PRECISION, scale = PRICE_PRECISION)
    private BigDecimal price;

    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

}
