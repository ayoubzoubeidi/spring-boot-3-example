package com.maz.springboot3example.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
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
@SuperBuilder
@Entity
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @ManyToMany(mappedBy = "guests")
    private Set<Booking> reservations = new HashSet<>();
    @OneToMany(mappedBy = "booker")
    private Set<Booking> bookings = new HashSet<>();
    @ManyToMany(mappedBy = "application_users")
    private Set<Role> roles = new HashSet<>();
    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
