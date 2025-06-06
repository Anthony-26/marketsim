package fr.marketsim.infrastructure.out.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import static fr.marketsim.application.util.ApplicationConstants.PRICE_PRECISION;
import static fr.marketsim.application.util.ApplicationConstants.PRICE_SCALE;

@Entity
@Table(name = "accounts")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_id_seq", allocationSize = 10)
    private Long id;

    @Column(name = "public_id", nullable = false, updatable = false)
    private UUID publicId;

    @Column(name = "balance", nullable = false, precision = PRICE_PRECISION, scale = PRICE_SCALE)
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @PrePersist
    private void onCreate() {
        if (this.publicId == null)
            this.publicId = UUID.randomUUID();
    }

}
