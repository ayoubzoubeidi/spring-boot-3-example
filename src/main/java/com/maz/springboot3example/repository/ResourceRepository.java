package com.maz.springboot3example.repository;

import com.maz.springboot3example.domain.Resource;
import com.maz.springboot3example.web.model.ResourceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ResourceRepository extends JpaRepository<Resource, UUID> {

    @Query("""
            SELECT new com.maz.springboot3example.web.model.ResourceDetails(r.id, r.name, r.capacity, r.type)
            FROM Resource r
            WHERE r.id = :resourceId
            """)
    Optional<ResourceDetails> findResultDetailById(@Param("resourceId") UUID resourceId);

}
