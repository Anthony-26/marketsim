package fr.marketsim.infrastructure.out.entity.portfolio;

import fr.marketsim.infrastructure.out.entity.audit.AuditableEntity;
import fr.marketsim.infrastructure.out.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "portfolios")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PortfolioEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "portfolio_seq")
    @SequenceGenerator(name = "portfolio_seq", sequenceName = "portfolio_id_seq", allocationSize = 10)
    private Long id;

    @Column(name = "public_id", nullable = false, updatable = false)
    private UUID publicId;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<PositionEntity> positions;

    @Override
    protected void onCreateCustom() {
        if (this.publicId == null) {
            this.publicId = UUID.randomUUID();
        }
    }

}