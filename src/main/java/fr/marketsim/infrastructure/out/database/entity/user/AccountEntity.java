package fr.marketsim.infrastructure.out.database.entity.user;

import fr.marketsim.infrastructure.out.database.entity.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

import static fr.marketsim.application.utilities.ApplicationConstants.PRICE_PRECISION;
import static fr.marketsim.application.utilities.ApplicationConstants.PRICE_SCALE;

@Entity
@Table(name = "accounts")
@Getter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountEntity extends AuditableEntity {

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

}
