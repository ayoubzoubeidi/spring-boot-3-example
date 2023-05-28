package com.maz.springboot3example.repository;

import com.maz.springboot3example.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, UUID> {
    Optional<ApplicationUser> findByEmailOrFirstNameOrLastName(String email, String firstName, String lastName);
}
