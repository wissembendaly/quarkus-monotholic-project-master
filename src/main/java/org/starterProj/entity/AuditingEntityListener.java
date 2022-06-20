package org.starterProj.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

public class AuditingEntityListener {

    @PrePersist
    void preCreate(AbstarctEntity auditable){
        Instant now = Instant.now();
        auditable.setCreatedDate(now);
        auditable.setLastModifiedDate(now);
    }

    @PreUpdate
    void preUpdate(AbstarctEntity auditable) {
        Instant now = Instant.now();
        auditable.setLastModifiedDate(now);
    }
}
