package fr.marketsim.infrastructure.out.entity.audit;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@MappedSuperclass
public class AuditableEntity {

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    @PrePersist
    private void onCreate() {
        onCreateCustom();
    }

    @PreUpdate
    private void onUpdate() {
        onUpdateCustom();
    }

    protected void onCreateCustom() {}

    protected void onUpdateCustom() {}

}
