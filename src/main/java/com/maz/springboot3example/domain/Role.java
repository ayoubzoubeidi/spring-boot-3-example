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
public class Role {
    @Id
    private String name;

    @ManyToMany
    @JoinTable(name = "application_user_roles",
            joinColumns = {@JoinColumn(name = "role_name")},
            inverseJoinColumns = {@JoinColumn(name = "application_user_id")}
    )
    private Set<ApplicationUser> applicationUsers = new HashSet<>();

    @ManyToMany(mappedBy = "roles")
    private Set<Authority> authorities = new HashSet<>();
    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
