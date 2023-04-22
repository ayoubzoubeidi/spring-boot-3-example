package com.maz.springboot3example.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Booking {

    /**
     * Long type for id in order to make it faster in DB,
     * because a Numeric Data Types clustered index is faster than UUID in MySQL (this will be the most used table).
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "booker_id")
    private ApplicationUser booker;
    @ManyToMany
    @JoinTable(
            name = "guests_booking",
            joinColumns = {@JoinColumn(name = "booking_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")}
    )
    private Set<Person> guests = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;
    private OffsetDateTime startDateTime;
    private OffsetDateTime endDateTime;
    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
