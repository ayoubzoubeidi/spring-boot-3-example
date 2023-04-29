package com.maz.springboot3example.repository;

import com.maz.springboot3example.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResourceRepository extends JpaRepository<Resource, UUID> {
}
