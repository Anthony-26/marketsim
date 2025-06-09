package fr.marketsim.infrastructure.out.entity.portfolio;

import fr.marketsim.infrastructure.out.entity.asset.AssetEntity;
import fr.marketsim.infrastructure.out.entity.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import static fr.marketsim.application.util.ApplicationConstants.*;

@Entity
@Table(name = "positions", uniqueConstraints = @UniqueConstraint(columnNames = {"portfolio_id", "asset_id"}))
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PositionEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_seq")
    @SequenceGenerator(name = "position_seq", sequenceName = "position_id_seq", allocationSize = 10)
    private Long id;

    @Column(name = "public_id", nullable = false, updatable = false, unique = true)
    private UUID publicId;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private PortfolioEntity portfolio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "asset_id", nullable = false)
    private AssetEntity asset;

    @Column(name = "quantity", nullable = false, precision = QUANTITY_PRECISION, scale = QUANTITY_PRECISION)
    private BigDecimal quantity;

    @Column(name = "average_bought_price", nullable = false, precision = PRICE_PRECISION, scale = PRICE_SCALE)
    private BigDecimal averageBoughtPrice;

    @Override
    protected void onCreateCustom() {
        if (this.publicId == null) {
            this.publicId = UUID.randomUUID();
        }
    }

}
