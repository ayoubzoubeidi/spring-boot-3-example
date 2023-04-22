package com.maz.springboot3example.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ApplicationUser extends Person {
    private String password;
    @ManyToMany(mappedBy = "applicationUsers")
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "booker")
    private Set<Booking> bookings = new HashSet<>();
}
