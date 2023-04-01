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
public class Authority {
    @Id
    private String permission;

    @ManyToMany
    @JoinTable(name = "roles_authorities",
            joinColumns = {@JoinColumn(name = "authority_permission")},
            inverseJoinColumns = {@JoinColumn(name = "role_name")}
    )
    private Set<Role> roles = new HashSet<>();
    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
