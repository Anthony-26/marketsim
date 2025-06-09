package fr.marketsim.infrastructure.out.database.entity.asset;

import fr.marketsim.infrastructure.out.database.entity.audit.AuditableEntity;
import fr.marketsim.infrastructure.out.database.entity.price.CurrentPriceEntity;
import fr.marketsim.infrastructure.out.database.entity.price.AssetPriceHistoryEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "assets")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "asset_type")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class AssetEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_seq")
    @SequenceGenerator(name = "asset_seq", sequenceName = "asset_id_seq", allocationSize = 10)
    private Long id;

    @Column(name = "public_id", nullable = false, updatable = false)
    private UUID publicId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ticker", nullable = false, unique = true)
    private String ticker;

    @OneToOne(mappedBy = "asset", cascade = CascadeType.ALL)
    private CurrentPriceEntity currentPrice;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("timestamp DESC")
    private List<AssetPriceHistoryEntity> priceHistory;

    @Override
    protected void onCreateCustom() {
        if (this.publicId == null)
            this.publicId = UUID.randomUUID();
    }

}
