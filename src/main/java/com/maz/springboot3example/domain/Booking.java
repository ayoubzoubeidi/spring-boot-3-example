package com.maz.springboot3example.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "booker_id")
    private ApplicationUser booker;
    @ManyToMany
    @JoinTable(
            name = "guests_booking",
            joinColumns = {@JoinColumn(name = "booking_id")},
            inverseJoinColumns = {@JoinColumn(name = "application_user_id")}
    )
    private Set<ApplicationUser> guests = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "resource_booking",
            joinColumns = {@JoinColumn(name = "booking_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id")}
    )
    private Set<Resource> resources = new HashSet<>();
    private OffsetDateTime startDateTime;
    private OffsetDateTime endDateTime;
    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
