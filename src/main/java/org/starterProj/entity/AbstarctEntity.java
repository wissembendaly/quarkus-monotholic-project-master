package org.starterProj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.inject.Inject;
import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstarctEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CREATED_DATE", nullable = false)
    private Instant createdDate;

    @Column(name = "LAST_MODIFIED_DATE")
    private Instant lastModifiedDate;
}
