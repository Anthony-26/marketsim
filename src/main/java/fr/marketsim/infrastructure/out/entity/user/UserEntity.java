package fr.marketsim.infrastructure.out.entity.user;

import fr.marketsim.infrastructure.out.entity.audit.AuditableEntity;
import fr.marketsim.domain.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", allocationSize = 10)
    private Long id;

    @Column(name = "public_id", nullable = false, updatable = false, unique = true)
    private UUID publicId;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email format invalid")
    @NotBlank(message = "Email required")
    private String email;

    @Column(name = "password_hash", nullable = false, length = 40)
    @NotBlank(message = "Password required")
    private String passwordHash;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AccountEntity account;

    @Override
    protected void onCreateCustom() {
        if(this.publicId == null)
            this.publicId = UUID.randomUUID();
    }

}
