package com.maz.springboot3example.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reservation {

    @EmbeddedId
    private ReservationId id = new ReservationId();
    @ManyToOne
    @MapsId("resourceId")
    private Resource resource;
    @ManyToOne
    @MapsId("applicationUserId")
    private ApplicationUser applicationUser;
    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
