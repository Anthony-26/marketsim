package fr.marketsim.infrastructure.out.entity.asset;

import fr.marketsim.domain.model.Sector;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static fr.marketsim.application.util.ApplicationConstants.PRICE_PRECISION;
import static fr.marketsim.application.util.ApplicationConstants.PRICE_SCALE;

@Entity
@Table(name = "stocks")
@DiscriminatorValue("STOCK")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StockEntity extends AssetEntity {

    @Column(name = "sector", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sector sector;

    @Column(name = "dividend", precision = PRICE_PRECISION, scale = PRICE_SCALE)
    private BigDecimal dividend;

}
