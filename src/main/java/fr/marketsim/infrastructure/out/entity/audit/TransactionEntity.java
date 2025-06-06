package fr.marketsim.infrastructure.out.entity.audit;

import fr.marketsim.infrastructure.out.entity.asset.AssetEntity;
import fr.marketsim.infrastructure.out.entity.portfolio.PortfolioEntity;
import fr.marketsim.infrastructure.out.entity.user.UserEntity;
import fr.marketsim.domain.model.OrderType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static fr.marketsim.application.util.ApplicationConstants.*;

@Entity
@Table(name = "transactions")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_id_seq", allocationSize = 50)
    private Long id;

    @Column(name = "public_id", nullable = false, updatable = false, unique = true)
    private UUID publicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false, updatable = false)
    private PortfolioEntity portfolio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "asset_id", nullable = false, updatable = false)
    private AssetEntity asset;

    @Column(name = "quantity", nullable = false, updatable = false,
            precision = QUANTITY_PRECISION, scale = QUANTITY_SCALE)
    private BigDecimal quantity;

    @Column(name = "execution_price", nullable = false, updatable = false,
            precision = PRICE_PRECISION, scale = PRICE_SCALE)
    private BigDecimal executionPrice;

    @Column(name = "total_amount", nullable = false, updatable = false,
            precision = PRICE_PRECISION, scale = PRICE_SCALE)
    private BigDecimal totalAmount;

    @Column(name = "order_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Column(name = "executed_at", nullable = false, updatable = false)
    private Instant executedAt;

    @Override
    protected void onCreateCustom() {
        if (publicId == null)
            publicId = UUID.randomUUID();
        if (totalAmount == null && quantity != null && executionPrice != null) {
            totalAmount = quantity.multiply(executionPrice);
        }
    }

}
